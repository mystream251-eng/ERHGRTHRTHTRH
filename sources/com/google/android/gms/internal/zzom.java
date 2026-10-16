package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzom implements Runnable {
    private /* synthetic */ zzol zza;

    zzom(zzol zzolVar) {
        this.zza = zzolVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        this.zza.zza();
    }
}
