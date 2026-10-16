package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzst extends zzsc {
    private final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zza;

    public zzst(NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener) {
        this.zza = onCustomTemplateAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.zzsb
    public final void zza(zzro zzroVar) {
        this.zza.onCustomTemplateAdLoaded(zzrr.zza(zzroVar));
    }
}
