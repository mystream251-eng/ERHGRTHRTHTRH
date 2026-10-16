package com.google.android.gms.internal;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
final class zzail implements Runnable {
    private /* synthetic */ Context zza;
    private /* synthetic */ zzaij zzb;

    zzail(zzaij zzaijVar, Context context) {
        this.zzb = zzaijVar;
        this.zza = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzb) {
            this.zzb.zzd = zzaij.zzd(this.zza);
            this.zzb.zzb.notifyAll();
        }
    }
}
