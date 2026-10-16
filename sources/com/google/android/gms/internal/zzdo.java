package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzdo implements Runnable {
    private /* synthetic */ int zza;
    private /* synthetic */ boolean zzb;
    private /* synthetic */ zzdm zzc;

    zzdo(zzdm zzdmVar, int i, boolean z) {
        this.zzc = zzdmVar;
        this.zza = i;
        this.zzb = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzba zzbaVarZzb = this.zzc.zzb(this.zza, this.zzb);
        this.zzc.zzk = zzbaVarZzb;
        if (zzdm.zzb(this.zza, zzbaVarZzb)) {
            this.zzc.zza(this.zza + 1, this.zzb);
        }
    }
}
