package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzpn implements Runnable {
    private /* synthetic */ zzpm zza;

    zzpn(zzpm zzpmVar) {
        this.zza = zzpmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zzn != null) {
            this.zza.zzn.zzg();
            this.zza.zzn.zzf();
        }
        zzpm.zza(this.zza, null);
    }
}
