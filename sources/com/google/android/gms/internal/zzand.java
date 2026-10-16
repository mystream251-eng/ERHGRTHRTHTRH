package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzand implements Runnable {
    private /* synthetic */ zzanb zza;

    zzand(zzanb zzanbVar) {
        this.zza = zzanbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza("surfaceDestroyed", new String[0]);
    }
}
