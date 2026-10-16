package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzabc implements Thread.UncaughtExceptionHandler {
    private /* synthetic */ Thread.UncaughtExceptionHandler zza;
    private /* synthetic */ zzabb zzb;

    zzabc(zzabb zzabbVar, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzb = zzabbVar;
        this.zza = uncaughtExceptionHandler;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        try {
            try {
                this.zzb.zza(thread, th);
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.zza;
                if (uncaughtExceptionHandler != null) {
                    uncaughtExceptionHandler.uncaughtException(thread, th);
                }
            } finally {
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.zza;
                if (uncaughtExceptionHandler2 != null) {
                    uncaughtExceptionHandler2.uncaughtException(thread, th);
                }
            }
        } catch (Throwable unused) {
            zzaky.zzc("AdMob exception reporter failed reporting the exception.");
        }
    }
}
