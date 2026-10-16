package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzv {
    private final SharedPreferences zza;

    zzv(Context context) {
        this.zza = context.getSharedPreferences("com.google.android.gms.appid", 0);
    }

    final synchronized String zza() {
        return this.zza.getString("topic_operation_queue", "");
    }

    final synchronized void zza(String str) {
        this.zza.edit().putString("topic_operation_queue", str).apply();
    }

    final synchronized boolean zzb(String str) {
        String strZza = zza();
        String strValueOf = String.valueOf(str);
        String strConcat = strValueOf.length() != 0 ? ",".concat(strValueOf) : new String(",");
        if (strZza.startsWith(strConcat)) {
            zza(strZza.substring(strConcat.length()));
            return true;
        }
        if (zzu.zzc()) {
            StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 105);
            sb.append("Illegal topic operation queue: ");
            sb.append(strZza);
            sb.append(". Illegal topic operation queue will lead to any topic operations to fail.");
            Log.d("FirebaseMessaging", sb.toString());
        }
        return false;
    }
}
