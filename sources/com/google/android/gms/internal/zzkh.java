package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzkh extends zzlj {
    private final AdListener zza;

    public zzkh(AdListener adListener) {
        this.zza = adListener;
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zza() {
        this.zza.onAdClosed();
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zza(int i) {
        this.zza.onAdFailedToLoad(i);
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzb() {
        this.zza.onAdLeftApplication();
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzc() {
        this.zza.onAdLoaded();
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzd() {
        this.zza.onAdOpened();
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zze() {
        this.zza.onAdClicked();
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzf() {
        this.zza.onAdImpression();
    }

    public final AdListener zzg() {
        return this.zza;
    }
}
