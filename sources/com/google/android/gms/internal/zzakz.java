package com.google.android.gms.internal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzakz implements zzakr {
    private final String zza;

    public zzakz() {
        this(null);
    }

    public zzakz(String str) {
        this.zza = str;
    }

    @Override // com.google.android.gms.internal.zzakr
    public final void zza(String str) {
        String message;
        StringBuilder sb;
        String string;
        try {
            String strValueOf = String.valueOf(str);
            zzaky.zzb(strValueOf.length() != 0 ? "Pinging URL: ".concat(strValueOf) : new String("Pinging URL: "));
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                zzlc.zza();
                zzako.zza(true, httpURLConnection, this.zza);
                zzaks zzaksVar = new zzaks();
                zzaksVar.zza(httpURLConnection, (byte[]) null);
                int responseCode = httpURLConnection.getResponseCode();
                zzaksVar.zza(httpURLConnection, responseCode);
                if (responseCode < 200 || responseCode >= 300) {
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 65);
                    sb2.append("Received non-success response code ");
                    sb2.append(responseCode);
                    sb2.append(" from pinging URL: ");
                    sb2.append(str);
                    zzaky.zze(sb2.toString());
                }
            } finally {
                httpURLConnection.disconnect();
            }
        } catch (IOException e) {
            message = e.getMessage();
            sb = new StringBuilder(String.valueOf(str).length() + 27 + String.valueOf(message).length());
            sb.append("Error while pinging URL: ");
            sb.append(str);
            sb.append(". ");
            sb.append(message);
            string = sb.toString();
            zzaky.zze(string);
        } catch (IndexOutOfBoundsException e2) {
            String message2 = e2.getMessage();
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(message2).length());
            sb3.append("Error while parsing ping URL: ");
            sb3.append(str);
            sb3.append(". ");
            sb3.append(message2);
            string = sb3.toString();
            zzaky.zze(string);
        } catch (RuntimeException e3) {
            message = e3.getMessage();
            sb = new StringBuilder(String.valueOf(str).length() + 27 + String.valueOf(message).length());
            sb.append("Error while pinging URL: ");
            sb.append(str);
            sb.append(". ");
            sb.append(message);
            string = sb.toString();
            zzaky.zze(string);
        }
    }
}
