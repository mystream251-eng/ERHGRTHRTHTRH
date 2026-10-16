package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzpe extends zzpc {
    private final OnCustomRenderedAdLoadedListener zza;

    public zzpe(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zza = onCustomRenderedAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.zzpb
    public final void zza(zzoy zzoyVar) {
        this.zza.onCustomRenderedAdLoaded(new zzox(zzoyVar));
    }
}
