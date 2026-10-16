package io.sentry.transport;

import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.viaccessorca.common.VOUtils;
import io.sentry.RequestDetails;
import io.sentry.SentryEnvelope;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
final class HttpConnection {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final SentryOptions options;
    private final Proxy proxy;
    private final RateLimiter rateLimiter;
    private final RequestDetails requestDetails;

    private boolean isSuccessfulResponseCode(int i) {
        return i == 200;
    }

    public HttpConnection(SentryOptions sentryOptions, RequestDetails requestDetails, RateLimiter rateLimiter) {
        this(sentryOptions, requestDetails, AuthenticatorWrapper.getInstance(), rateLimiter);
    }

    HttpConnection(SentryOptions sentryOptions, RequestDetails requestDetails, AuthenticatorWrapper authenticatorWrapper, RateLimiter rateLimiter) {
        this.requestDetails = requestDetails;
        this.options = sentryOptions;
        this.rateLimiter = rateLimiter;
        Proxy proxyResolveProxy = resolveProxy(sentryOptions.getProxy());
        this.proxy = proxyResolveProxy;
        if (proxyResolveProxy == null || sentryOptions.getProxy() == null) {
            return;
        }
        String user = sentryOptions.getProxy().getUser();
        String pass = sentryOptions.getProxy().getPass();
        if (user == null || pass == null) {
            return;
        }
        authenticatorWrapper.setDefault(new ProxyAuthenticator(user, pass));
    }

    private Proxy resolveProxy(SentryOptions.Proxy proxy) {
        if (proxy != null) {
            String port = proxy.getPort();
            String host = proxy.getHost();
            if (port != null && host != null) {
                try {
                    return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, Integer.parseInt(port)));
                } catch (NumberFormatException e) {
                    this.options.getLogger().log(SentryLevel.ERROR, e, "Failed to parse Sentry Proxy port: " + proxy.getPort() + ". Proxy is ignored", new Object[0]);
                }
            }
        }
        return null;
    }

    HttpURLConnection open() throws IOException {
        URLConnection uRLConnectionOpenConnection;
        if (this.proxy == null) {
            uRLConnectionOpenConnection = this.requestDetails.getUrl().openConnection();
        } else {
            uRLConnectionOpenConnection = this.requestDetails.getUrl().openConnection(this.proxy);
        }
        return (HttpURLConnection) uRLConnectionOpenConnection;
    }

    private HttpURLConnection createConnection() throws IOException {
        HttpURLConnection httpURLConnectionOpen = open();
        for (Map.Entry<String, String> entry : this.requestDetails.getHeaders().entrySet()) {
            httpURLConnectionOpen.setRequestProperty(entry.getKey(), entry.getValue());
        }
        httpURLConnectionOpen.setRequestMethod(VOUtils.HTTP_POST);
        httpURLConnectionOpen.setDoOutput(true);
        httpURLConnectionOpen.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnectionOpen.setRequestProperty("Content-Type", "application/x-sentry-envelope");
        httpURLConnectionOpen.setRequestProperty("Accept", "application/json");
        httpURLConnectionOpen.setRequestProperty("Connection", ToolboxAnalyticsModuleKt.EVENT_CLOSE);
        httpURLConnectionOpen.setConnectTimeout(this.options.getConnectionTimeoutMillis());
        httpURLConnectionOpen.setReadTimeout(this.options.getReadTimeoutMillis());
        HostnameVerifier hostnameVerifier = this.options.getHostnameVerifier();
        boolean z = httpURLConnectionOpen instanceof HttpsURLConnection;
        if (z && hostnameVerifier != null) {
            ((HttpsURLConnection) httpURLConnectionOpen).setHostnameVerifier(hostnameVerifier);
        }
        SSLSocketFactory sslSocketFactory = this.options.getSslSocketFactory();
        if (z && sslSocketFactory != null) {
            ((HttpsURLConnection) httpURLConnectionOpen).setSSLSocketFactory(sslSocketFactory);
        }
        httpURLConnectionOpen.connect();
        return httpURLConnectionOpen;
    }

    public TransportResult send(SentryEnvelope sentryEnvelope) throws IOException {
        HttpURLConnection httpURLConnectionCreateConnection = createConnection();
        try {
            OutputStream outputStream = httpURLConnectionCreateConnection.getOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                try {
                    this.options.getSerializer().serialize(sentryEnvelope, gZIPOutputStream);
                    gZIPOutputStream.close();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (Throwable th) {
                    try {
                        gZIPOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                }
                throw th3;
            }
        } catch (Throwable th5) {
            try {
                this.options.getLogger().log(SentryLevel.ERROR, th5, "An exception occurred while submitting the envelope to the Sentry server.", new Object[0]);
            } catch (Throwable th6) {
                readAndLog(httpURLConnectionCreateConnection);
                throw th6;
            }
        }
        return readAndLog(httpURLConnectionCreateConnection);
    }

    private TransportResult readAndLog(HttpURLConnection httpURLConnection) {
        try {
            int responseCode = httpURLConnection.getResponseCode();
            updateRetryAfterLimits(httpURLConnection, responseCode);
            if (isSuccessfulResponseCode(responseCode)) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Envelope sent successfully.", new Object[0]);
                return TransportResult.success();
            }
            this.options.getLogger().log(SentryLevel.ERROR, "Request failed, API returned %s", Integer.valueOf(responseCode));
            if (this.options.isDebug()) {
                this.options.getLogger().log(SentryLevel.ERROR, getErrorMessageFromStream(httpURLConnection), new Object[0]);
            }
            return TransportResult.error(responseCode);
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.ERROR, e, "Error reading and logging the response stream", new Object[0]);
            return TransportResult.error();
        } finally {
            closeAndDisconnect(httpURLConnection);
        }
    }

    public void updateRetryAfterLimits(HttpURLConnection httpURLConnection, int i) {
        String headerField = httpURLConnection.getHeaderField("Retry-After");
        this.rateLimiter.updateRetryAfterLimits(httpURLConnection.getHeaderField("X-Sentry-Rate-Limits"), headerField, i);
    }

    private void closeAndDisconnect(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.getInputStream().close();
        } catch (IOException unused) {
        } finally {
            httpURLConnection.disconnect();
        }
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private String getErrorMessageFromStream(HttpURLConnection httpURLConnection) {
        try {
            InputStream errorStream = httpURLConnection.getErrorStream();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, UTF_8));
                try {
                    StringBuilder sb = new StringBuilder();
                    boolean z = true;
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (!z) {
                            sb.append("\n");
                        }
                        sb.append(line);
                        z = false;
                        if (errorStream != null) {
                            try {
                                errorStream.close();
                            } catch (Throwable th) {
                                th.addSuppressed(th);
                            }
                        }
                        throw th;
                    }
                    String string = sb.toString();
                    bufferedReader.close();
                    if (errorStream != null) {
                        errorStream.close();
                    }
                    return string;
                } catch (Throwable th2) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                if (errorStream != null) {
                    errorStream.close();
                }
                throw th4;
            }
        } catch (IOException unused) {
            return "Failed to obtain error message while analyzing send failure.";
        }
    }

    Proxy getProxy() {
        return this.proxy;
    }
}
