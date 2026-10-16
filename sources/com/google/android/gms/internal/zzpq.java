package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzpq implements Runnable {
    private /* synthetic */ zzpo zza;

    zzpq(zzpo zzpoVar) {
        this.zza = zzpoVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zzh != null) {
            this.zza.zzh.zzg();
            this.zza.zzh.zzf();
        }
        zzpo.zza(this.zza, null);
    }
}
