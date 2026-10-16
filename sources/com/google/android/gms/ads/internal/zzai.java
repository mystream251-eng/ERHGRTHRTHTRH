package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzkk;

/* JADX INFO: loaded from: classes2.dex */
final class zzai implements Runnable {
    private /* synthetic */ zzkk zza;
    private /* synthetic */ int zzb;
    private /* synthetic */ zzag zzc;

    zzai(zzag zzagVar, zzkk zzkkVar, int i) {
        this.zzc = zzagVar;
        this.zza = zzkkVar;
        this.zzb = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzc.zzs) {
            this.zzc.zzb(this.zza, this.zzb);
        }
    }
}
