package com.google.android.gms.ads.internal.overlay;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes2.dex */
final class zzk implements Runnable {
    private /* synthetic */ Drawable zza;
    private /* synthetic */ zzj zzb;

    zzk(zzj zzjVar, Drawable drawable) {
        this.zzb = zzjVar;
        this.zza = drawable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zza.getWindow().setBackgroundDrawable(this.zza);
    }
}
