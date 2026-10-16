package com.google.android.gms.internal;

import android.content.Context;
import android.os.StrictMode;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzakg {
    public static <T> T zza(Context context, Callable<T> callable) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitDiskReads().permitDiskWrites().build());
            T tCall = callable.call();
            StrictMode.setThreadPolicy(threadPolicy);
            return tCall;
        } catch (Throwable th) {
            try {
                zzaky.zzb("Unexpected exception.", th);
                zzabb.zza(context).zza(th, "StrictModeUtil.runWithLaxStrictMode");
                return null;
            } finally {
                StrictMode.setThreadPolicy(threadPolicy);
            }
        }
    }

    public static <T> T zzb(Context context, Callable<T> callable) throws Exception {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitDiskReads().permitDiskWrites().build());
            return callable.call();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }
}
