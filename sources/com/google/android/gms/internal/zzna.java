package com.google.android.gms.internal;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
final class zzna implements Runnable {
    private /* synthetic */ Context zza;
    private /* synthetic */ zzmz zzb;

    zzna(zzmz zzmzVar, Context context) {
        this.zzb = zzmzVar;
        this.zza = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
    }
}
