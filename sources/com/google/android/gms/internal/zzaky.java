package com.google.android.gms.internal;

import android.util.Log;
import com.google.ads.AdRequest;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzaky {
    public static void zza(String str, Throwable th) {
        if (zza(3)) {
            Log.d(AdRequest.LOGTAG, str, th);
        }
    }

    public static boolean zza(int i) {
        return i >= 5 || Log.isLoggable(AdRequest.LOGTAG, i);
    }

    public static void zzb(String str) {
        if (zza(3)) {
            Log.d(AdRequest.LOGTAG, str);
        }
    }

    public static void zzb(String str, Throwable th) {
        if (zza(6)) {
            Log.e(AdRequest.LOGTAG, str, th);
        }
    }

    public static void zzc(String str) {
        if (zza(6)) {
            Log.e(AdRequest.LOGTAG, str);
        }
    }

    public static void zzc(String str, Throwable th) {
        if (zza(5)) {
            Log.w(AdRequest.LOGTAG, str, th);
        }
    }

    public static void zzd(String str) {
        if (zza(4)) {
            Log.i(AdRequest.LOGTAG, str);
        }
    }

    public static void zze(String str) {
        if (zza(5)) {
            Log.w(AdRequest.LOGTAG, str);
        }
    }
}
