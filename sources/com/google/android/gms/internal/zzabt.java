package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzabt implements Runnable {
    private /* synthetic */ zzamf zza;
    private /* synthetic */ zzabk zzb;

    zzabt(zzabk zzabkVar, zzamf zzamfVar) {
        this.zzb = zzabkVar;
        this.zza = zzamfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzd) {
            this.zzb.zza = this.zzb.zza(this.zzb.zzc.zzj, this.zza);
            if (this.zzb.zza == null) {
                this.zzb.zza(0, "Could not start the ad request service.");
                zzaij.zza.removeCallbacks(this.zzb.zzi);
            }
        }
    }
}
