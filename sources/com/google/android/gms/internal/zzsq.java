package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeAppInstallAd;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzsq extends zzrt {
    private final NativeAppInstallAd.OnAppInstallAdLoadedListener zza;

    public zzsq(NativeAppInstallAd.OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
        this.zza = onAppInstallAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.zzrs
    public final void zza(zzrg zzrgVar) {
        this.zza.onAppInstallAdLoaded(new zzrj(zzrgVar));
    }
}
