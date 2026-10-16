package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
final class zzxz implements Runnable {
    private /* synthetic */ AdOverlayInfoParcel zza;
    private /* synthetic */ zzxx zzb;

    zzxz(zzxx zzxxVar, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzb = zzxxVar;
        this.zza = adOverlayInfoParcel;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbt.zzc();
        zzl.zza(this.zzb.zza, this.zza, true);
    }
}
