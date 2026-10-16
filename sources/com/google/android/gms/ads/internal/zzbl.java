package com.google.android.gms.ads.internal;

import android.os.Handler;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbl {
    private final Handler zza;

    public zzbl(Handler handler) {
        this.zza = handler;
    }

    public final void zza(Runnable runnable) {
        this.zza.removeCallbacks(runnable);
    }

    public final boolean zza(Runnable runnable, long j) {
        return this.zza.postDelayed(runnable, j);
    }
}
