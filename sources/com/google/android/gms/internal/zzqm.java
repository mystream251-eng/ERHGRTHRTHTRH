package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzqm implements Runnable {
    private /* synthetic */ zzpz zza;
    private /* synthetic */ zzql zzb;

    zzqm(zzql zzqlVar, zzpz zzpzVar) {
        this.zzb = zzqlVar;
        this.zza = zzpzVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
    }
}
