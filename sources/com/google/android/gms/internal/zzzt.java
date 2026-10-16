package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzar;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes2.dex */
final class zzzt implements Runnable {
    private /* synthetic */ CountDownLatch zza;
    private /* synthetic */ zzzs zzb;

    zzzt(zzzs zzzsVar, CountDownLatch countDownLatch) {
        this.zzb = zzzsVar;
        this.zza = countDownLatch;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzd) {
            this.zzb.zzm = zzar.zza(this.zzb.zzl, this.zzb.zzg, this.zza);
        }
    }
}
