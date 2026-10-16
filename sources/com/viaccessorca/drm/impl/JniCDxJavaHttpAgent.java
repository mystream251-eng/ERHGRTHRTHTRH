package com.viaccessorca.drm.impl;

import android.content.Context;
import android.util.Base64;
import com.viaccessorca.common.VOUtils;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
final class JniCDxJavaHttpAgent {
    public static final String DX_USER_AGENT_HDR = "User-Agent";
    public static final String DX_WWW_AUTHENTICATE_HDR = "WWW-Authenticate";
    private String e;
    private String f;
    private int g;
    private Map<String, Integer> h;
    private int b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HttpURLConnection f17a = null;
    private InputStream c = null;
    private Map<String, List<String>> d = null;

    public JniCDxJavaHttpAgent(int i) {
        this.g = i;
    }

    private void a() throws VOException {
        Map<String, List<String>> map;
        String key;
        try {
            if (this.e != null && this.f != null) {
                this.f17a.setRequestProperty("Authorization", "Basic " + new String(Base64.encode((this.e + ":" + this.f).getBytes(), 0)));
            }
            this.b = this.f17a.getResponseCode();
            Map<String, List<String>> headerFields = this.f17a.getHeaderFields();
            this.d = new HashMap();
            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                if (entry.getKey() == null && Pattern.compile("^HTTP/[0-9]\\.[0-9] [0-9]{3} .*$").matcher(entry.getValue().get(0)).find()) {
                    map = this.d;
                    key = "Status Message";
                } else {
                    map = this.d;
                    key = entry.getKey();
                }
                map.put(key, entry.getValue());
            }
            this.c = null;
            this.c = this.b != 200 ? this.f17a.getErrorStream() : this.f17a.getInputStream();
        } catch (IOException unused) {
            throw new VOException(VOStatusCode.ERROR_NETWORK_SERVER_NOT_REACHABLE);
        }
    }

    private void a(c cVar) {
        long jE = cVar.e();
        for (int i = 0; i < jE; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            cVar.a(i, stringBuffer, stringBuffer2);
            if (!this.h.containsKey(stringBuffer.toString())) {
                this.f17a.addRequestProperty(stringBuffer.toString(), stringBuffer2.toString());
                this.h.put(stringBuffer.toString(), 1);
            }
        }
    }

    private void a(String str) throws VOException {
        URLConnection uRLConnectionOpenConnection;
        this.c = null;
        this.d = null;
        try {
            URL url = new URL(str);
            this.h = new HashMap();
            Context context = DrmHelperUtils.getContext();
            String property = System.getProperty("http.proxyHost");
            if (context == null || property == null || property.isEmpty()) {
                uRLConnectionOpenConnection = url.openConnection(Proxy.NO_PROXY);
            } else {
                uRLConnectionOpenConnection = url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(property, Integer.parseInt(System.getProperty("http.proxyPort")))));
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            this.f17a = httpURLConnection;
            httpURLConnection.setConnectTimeout(this.g);
        } catch (MalformedURLException e) {
            VOException vOException = new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
            vOException.initCause(e);
            throw vOException;
        } catch (IOException e2) {
            VOException vOException2 = new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
            vOException2.initCause(e2);
            throw vOException2;
        }
    }

    private void b(c cVar) throws VOException {
        if (cVar.c() != JniEDxHttpMethod.DX_HTTP_METHOD_POST) {
            return;
        }
        long jA = cVar.a();
        if (jA == 0) {
            return;
        }
        byte[] bArr = new byte[(int) jA];
        if (!cVar.a(bArr)) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        this.f17a.setDoOutput(true);
        this.f17a.setReadTimeout(60000);
        try {
            this.f17a.getOutputStream().write(bArr);
        } catch (IOException e) {
            VOException vOException = new VOException(VOStatusCode.ERROR_NETWORK_COMMUNICATION_FAILURE);
            vOException.initCause(e);
            throw vOException;
        }
    }

    public void closeResponse() {
        InputStream inputStream;
        if (this.f17a == null || (inputStream = this.c) == null) {
            return;
        }
        try {
            inputStream.close();
            this.f17a.disconnect();
        } catch (Exception unused) {
        }
    }

    public String getHost() {
        HttpURLConnection httpURLConnection = this.f17a;
        if (httpURLConnection == null) {
            return null;
        }
        return httpURLConnection.getURL().getHost();
    }

    public int getHttpResult() {
        return this.b;
    }

    public String getRealm() {
        return null;
    }

    public InputStream getResponseBody() {
        return this.c;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.d;
    }

    public void getUrl(String str, Map<String, List<String>> map) throws VOException {
        if (str == null) {
            throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
        }
        a(str);
        if (map != null) {
            Set<Map.Entry<String, List<String>>> setEntrySet = map.entrySet();
            if (!setEntrySet.isEmpty()) {
                for (Map.Entry<String, List<String>> entry : setEntrySet) {
                    if (!this.h.containsKey(entry.getKey())) {
                        String strConcatenateHeaders = JniIDxDataReader.concatenateHeaders(entry.getValue());
                        entry.getKey();
                        this.f17a.setRequestProperty(entry.getKey(), strConcatenateHeaders);
                        this.h.put(entry.getKey(), 1);
                    }
                }
            }
        }
        if (!this.h.containsKey(DX_USER_AGENT_HDR)) {
            this.f17a.setRequestProperty(DX_USER_AGENT_HDR, "Mozilla/5.0 (Linux; U; Android 0.5; en-us) AppleWebKit/522+ (KHTML, like Gecko) Safari/419.3");
        }
        a();
    }

    public void sendRequest(c cVar, Map<String, List<String>> map) throws VOException {
        String strD = cVar.d();
        if (strD == null) {
            throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
        }
        a(strD);
        if (cVar.c() == JniEDxHttpMethod.DX_HTTP_METHOD_GET) {
            try {
                this.f17a.setRequestMethod(VOUtils.HTTP_GET);
            } catch (ProtocolException e) {
                VOException vOException = new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
                vOException.initCause(e);
                throw vOException;
            }
        } else {
            if (cVar.c() != JniEDxHttpMethod.DX_HTTP_METHOD_POST) {
                throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
            }
            try {
                this.f17a.setRequestMethod(VOUtils.HTTP_POST);
            } catch (ProtocolException e2) {
                VOException vOException2 = new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
                vOException2.initCause(e2);
                throw vOException2;
            }
        }
        a(cVar);
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                if (!this.h.containsKey(entry.getKey())) {
                    String strConcatenateHeaders = JniIDxDataReader.concatenateHeaders(entry.getValue());
                    entry.getKey();
                    this.f17a.setRequestProperty(entry.getKey(), strConcatenateHeaders);
                    this.h.put(entry.getKey(), 1);
                }
            }
        }
        if (!this.h.containsKey(DX_USER_AGENT_HDR)) {
            this.f17a.setRequestProperty(DX_USER_AGENT_HDR, "Mozilla/5.0 (Linux; U; Android 0.5; en-us) AppleWebKit/522+ (KHTML, like Gecko) Safari/419.3");
        }
        b(cVar);
        a();
    }

    public void setHttpCredentials(String str, String str2, String str3) {
        this.e = str2;
        this.f = str3;
    }
}
