package com.google.android.gms.internal;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
final class zzhn implements Runnable {
    private /* synthetic */ View zza;
    private /* synthetic */ zzhm zzb;

    zzhn(zzhm zzhmVar, View view) {
        this.zzb = zzhmVar;
        this.zza = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
    }
}
