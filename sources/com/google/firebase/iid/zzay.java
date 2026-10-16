package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import com.viaccessorca.analytics.fma.FmaDatabaseHelper;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzay {
    private static final long zzb = TimeUnit.DAYS.toMillis(7);
    final String zza;
    private final String zzc;
    private final long zzd;

    private zzay(String str, String str2, long j) {
        this.zza = str;
        this.zzc = str2;
        this.zzd = j;
    }

    static zzay zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("{")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new zzay(jSONObject.getString("token"), jSONObject.getString("appVersion"), jSONObject.getLong(FmaDatabaseHelper.CONST_TIMESTAMP));
            } catch (JSONException e) {
                String strValueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 23);
                sb.append("Failed to parse token: ");
                sb.append(strValueOf);
                Log.w("FirebaseInstanceId", sb.toString());
                return null;
            }
        }
        return new zzay(str, null, 0L);
    }

    static String zza(String str, String str2, long j) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("token", str);
            jSONObject.put("appVersion", str2);
            jSONObject.put(FmaDatabaseHelper.CONST_TIMESTAMP, j);
            return jSONObject.toString();
        } catch (JSONException e) {
            String strValueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 24);
            sb.append("Failed to encode token: ");
            sb.append(strValueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }

    static String zza(zzay zzayVar) {
        if (zzayVar == null) {
            return null;
        }
        return zzayVar.zza;
    }

    final boolean zzb(String str) {
        return System.currentTimeMillis() > this.zzd + zzb || !str.equals(this.zzc);
    }
}
