package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeContentAd;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzsr extends zzrw {
    private final NativeContentAd.OnContentAdLoadedListener zza;

    public zzsr(NativeContentAd.OnContentAdLoadedListener onContentAdLoadedListener) {
        this.zza = onContentAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.zzrv
    public final void zza(zzrk zzrkVar) {
        this.zza.onContentAdLoaded(new zzrn(zzrkVar));
    }
}
