package com.google.android.gms.internal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzame {
    public static HttpURLConnection zza(String str, int i) throws IOException {
        URL url = new URL(str);
        int i2 = 0;
        while (true) {
            i2++;
            if (i2 > 20) {
                throw new IOException("Too many redirects (20)");
            }
            URLConnection uRLConnectionOpenConnection = url.openConnection();
            uRLConnectionOpenConnection.setConnectTimeout(i);
            uRLConnectionOpenConnection.setReadTimeout(i);
            if (!(uRLConnectionOpenConnection instanceof HttpURLConnection)) {
                throw new IOException("Invalid protocol.");
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            zzaks zzaksVar = new zzaks();
            zzaksVar.zza(httpURLConnection, (byte[]) null);
            httpURLConnection.setInstanceFollowRedirects(false);
            int responseCode = httpURLConnection.getResponseCode();
            zzaksVar.zza(httpURLConnection, responseCode);
            if (responseCode / 100 != 3) {
                return httpURLConnection;
            }
            String headerField = httpURLConnection.getHeaderField("Location");
            if (headerField == null) {
                throw new IOException("Missing Location header in redirect");
            }
            URL url2 = new URL(url, headerField);
            String protocol = url2.getProtocol();
            if (protocol == null) {
                throw new IOException("Protocol is null");
            }
            if (!protocol.equals("http") && !protocol.equals("https")) {
                String strValueOf = String.valueOf(protocol);
                throw new IOException(strValueOf.length() != 0 ? "Unsupported scheme: ".concat(strValueOf) : new String("Unsupported scheme: "));
            }
            String strValueOf2 = String.valueOf(headerField);
            zzahw.zzb(strValueOf2.length() != 0 ? "Redirecting to ".concat(strValueOf2) : new String("Redirecting to "));
            httpURLConnection.disconnect();
            url = url2;
        }
    }
}
