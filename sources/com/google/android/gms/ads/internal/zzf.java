package com.google.android.gms.ads.internal;

/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzf implements Runnable {
    private final zzbj zza;

    private zzf(zzbj zzbjVar) {
        this.zza = zzbjVar;
    }

    static Runnable zza(zzbj zzbjVar) {
        return new zzf(zzbjVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzc();
    }
}
