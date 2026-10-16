package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzamu implements Runnable {
    private /* synthetic */ int zza;
    private /* synthetic */ int zzb;
    private /* synthetic */ zzamp zzc;

    zzamu(zzamp zzampVar, int i, int i2) {
        this.zzc = zzampVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzc.zzr != null) {
            this.zzc.zzr.zza(this.zza, this.zzb);
        }
    }
}
