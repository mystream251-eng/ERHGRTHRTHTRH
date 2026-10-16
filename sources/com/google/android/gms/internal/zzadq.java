package com.google.android.gms.internal;

import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
final class zzadq implements Runnable {
    private /* synthetic */ Future zza;

    zzadq(zzadn zzadnVar, Future future) {
        this.zza = future;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.isDone()) {
            return;
        }
        this.zza.cancel(true);
    }
}
