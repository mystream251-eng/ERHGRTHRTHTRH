package com.google.android.gms.internal;

import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
final class zzaih implements Runnable {
    private /* synthetic */ zzamd zza;
    private /* synthetic */ Future zzb;

    zzaih(zzamd zzamdVar, Future future) {
        this.zza = zzamdVar;
        this.zzb = future;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.isCancelled()) {
            this.zzb.cancel(true);
        }
    }
}
