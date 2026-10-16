package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzss extends zzrz {
    private final NativeCustomTemplateAd.OnCustomClickListener zza;

    public zzss(NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
        this.zza = onCustomClickListener;
    }

    @Override // com.google.android.gms.internal.zzry
    public final void zza(zzro zzroVar, String str) {
        this.zza.onCustomClick(zzrr.zza(zzroVar), str);
    }
}
