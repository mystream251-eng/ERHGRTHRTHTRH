package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzn;

/* JADX INFO: loaded from: classes2.dex */
final class zzxy implements zzn {
    private /* synthetic */ zzxx zza;

    zzxy(zzxx zzxxVar) {
        this.zza = zzxxVar;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzaa() {
        zzaky.zzb("Opening AdMobCustomTabsAdapter overlay.");
        this.zza.zzb.onAdOpened(this.zza);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzab() {
        zzaky.zzb("AdMobCustomTabsAdapter overlay is paused.");
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzac() {
        zzaky.zzb("AdMobCustomTabsAdapter overlay is resumed.");
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzz() {
        zzaky.zzb("AdMobCustomTabsAdapter overlay is closed.");
        this.zza.zzb.onAdClosed(this.zza);
    }
}
