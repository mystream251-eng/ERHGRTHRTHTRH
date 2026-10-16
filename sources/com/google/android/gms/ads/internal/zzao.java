package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;

/* JADX INFO: loaded from: classes2.dex */
final class zzao implements Runnable {
    private /* synthetic */ AdOverlayInfoParcel zza;
    private /* synthetic */ zzan zzb;

    zzao(zzan zzanVar, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzb = zzanVar;
        this.zza = adOverlayInfoParcel;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbt.zzc();
        com.google.android.gms.ads.internal.overlay.zzl.zza(this.zzb.zza.zze.zzc, this.zza, true);
    }
}
