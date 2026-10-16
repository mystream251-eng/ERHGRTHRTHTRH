package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzkk;

/* JADX INFO: loaded from: classes2.dex */
final class zzah implements Runnable {
    private /* synthetic */ zzkk zza;
    private /* synthetic */ zzag zzb;

    zzah(zzag zzagVar, zzkk zzkkVar) {
        this.zzb = zzagVar;
        this.zza = zzkkVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzs) {
            if (this.zzb.zzd()) {
                this.zzb.zzb(this.zza);
            } else {
                this.zzb.zzb(this.zza, 1);
            }
        }
    }
}
