package com.google.firebase.messaging;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import com.google.firebase.iid.zzao;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzx implements Runnable {
    private static final Object zzf = new Object();
    private static Boolean zzg = null;
    private static Boolean zzh = null;
    private final Context zza;
    private final zzao zzb;
    private final PowerManager.WakeLock zzc;
    private final zzu zzd;
    private final long zze;

    zzx(zzu zzuVar, Context context, zzao zzaoVar, long j) {
        this.zzd = zzuVar;
        this.zza = context;
        this.zze = j;
        this.zzb = zzaoVar;
        this.zzc = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "wake:com.google.firebase.messaging");
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zZza;
        if (zza(this.zza)) {
            this.zzc.acquire(zzd.zza);
        }
        try {
            boolean z = true;
            this.zzd.zza(true);
            if (!this.zzb.zza()) {
                this.zzd.zza(false);
                if (zZza) {
                    try {
                        return;
                    } catch (RuntimeException unused) {
                        return;
                    }
                }
                return;
            }
            if (zzb(this.zza) && !zzb()) {
                zzw zzwVar = new zzw(this, this);
                if (!Log.isLoggable("FirebaseMessaging", 3) && (Build.VERSION.SDK_INT != 23 || !Log.isLoggable("FirebaseMessaging", 3))) {
                    z = false;
                }
                if (z) {
                    Log.d("FirebaseMessaging", "Connectivity change received registered");
                }
                zzwVar.zza.zza.registerReceiver(zzwVar, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                if (zZza) {
                    try {
                        return;
                    } catch (RuntimeException unused2) {
                        return;
                    }
                }
                return;
            }
            if (this.zzd.zzb()) {
                this.zzd.zza(false);
            } else {
                this.zzd.zza(this.zze);
            }
        } catch (IOException e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.e("FirebaseMessaging", strValueOf.length() != 0 ? "Failed to sync topics. Won't retry sync. ".concat(strValueOf) : new String("Failed to sync topics. Won't retry sync. "));
            this.zzd.zza(false);
        } finally {
            if (zza(this.zza)) {
                try {
                    this.zzc.release();
                } catch (RuntimeException unused3) {
                    Log.i("FirebaseMessaging", "TopicsSyncTask's wakelock was already released due to timeout.");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized boolean zzb() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.zza.getSystemService("connectivity");
        activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzc() {
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            return true;
        }
        return Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseMessaging", 3);
    }

    private static boolean zza(Context context) {
        boolean zBooleanValue;
        boolean zBooleanValue2;
        synchronized (zzf) {
            if (zzg == null) {
                zBooleanValue = zza(context, "android.permission.WAKE_LOCK", zzg);
            } else {
                zBooleanValue = zzg.booleanValue();
            }
            Boolean boolValueOf = Boolean.valueOf(zBooleanValue);
            zzg = boolValueOf;
            zBooleanValue2 = boolValueOf.booleanValue();
        }
        return zBooleanValue2;
    }

    private static boolean zzb(Context context) {
        boolean zBooleanValue;
        boolean zBooleanValue2;
        synchronized (zzf) {
            if (zzh == null) {
                zBooleanValue = zza(context, "android.permission.ACCESS_NETWORK_STATE", zzh);
            } else {
                zBooleanValue = zzh.booleanValue();
            }
            Boolean boolValueOf = Boolean.valueOf(zBooleanValue);
            zzh = boolValueOf;
            zBooleanValue2 = boolValueOf.booleanValue();
        }
        return zBooleanValue2;
    }

    private static boolean zza(Context context, String str, Boolean bool) {
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean z = context.checkCallingOrSelfPermission(str) == 0;
        if (!z && Log.isLoggable("FirebaseMessaging", 3)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 142);
            sb.append("Missing Permission: ");
            sb.append(str);
            sb.append(". This permission should normally be included by the manifest merger, but may needed to be manually added to your manifest");
            Log.d("FirebaseMessaging", sb.toString());
        }
        return z;
    }
}
