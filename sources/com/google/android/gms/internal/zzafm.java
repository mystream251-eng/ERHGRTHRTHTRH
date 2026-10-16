package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzafm implements Runnable {
    private /* synthetic */ zzkk zza;
    private /* synthetic */ zzwi zzb;
    private /* synthetic */ zzafl zzc;

    zzafm(zzafl zzaflVar, zzkk zzkkVar, zzwi zzwiVar) {
        this.zzc = zzaflVar;
        this.zza = zzkkVar;
        this.zzb = zzwiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza(this.zza, this.zzb);
    }
}
