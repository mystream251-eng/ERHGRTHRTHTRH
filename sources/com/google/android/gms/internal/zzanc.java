package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzanc implements Runnable {
    private /* synthetic */ zzanb zza;

    zzanc(zzanb zzanbVar) {
        this.zza = zzanbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza("surfaceCreated", new String[0]);
    }
}
