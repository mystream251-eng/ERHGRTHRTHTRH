package com.google.android.gms.internal;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
final class zzaoi implements Runnable {
    private /* synthetic */ View zza;
    private /* synthetic */ zzagq zzb;
    private /* synthetic */ int zzc;
    private /* synthetic */ zzaog zzd;

    zzaoi(zzaog zzaogVar, View view, zzagq zzagqVar, int i) {
        this.zzd = zzaogVar;
        this.zza = view;
        this.zzb = zzagqVar;
        this.zzc = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzd.zza(this.zza, this.zzb, this.zzc - 1);
    }
}
