package com.google.android.gms.ads.internal;

/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zze implements Runnable {
    private final zzbj zza;

    private zze(zzbj zzbjVar) {
        this.zza = zzbjVar;
    }

    static Runnable zza(zzbj zzbjVar) {
        return new zze(zzbjVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzb();
    }
}
