package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzmy;

/* JADX INFO: loaded from: classes2.dex */
public final class PublisherInterstitialAd {
    private final zzmy zza;

    public PublisherInterstitialAd(Context context) {
        this.zza = new zzmy(context, this);
        zzbq.zza(context, "Context cannot be null");
    }

    public final AdListener getAdListener() {
        return this.zza.zza();
    }

    public final String getAdUnitId() {
        return this.zza.zzb();
    }

    public final AppEventListener getAppEventListener() {
        return this.zza.zzc();
    }

    public final String getMediationAdapterClassName() {
        return this.zza.zzg();
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zza.zzd();
    }

    public final boolean isLoaded() {
        return this.zza.zze();
    }

    public final boolean isLoading() {
        return this.zza.zzf();
    }

    public final void loadAd(PublisherAdRequest publisherAdRequest) {
        this.zza.zza(publisherAdRequest.zza());
    }

    public final void setAdListener(AdListener adListener) {
        this.zza.zza(adListener);
    }

    public final void setAdUnitId(String str) {
        this.zza.zza(str);
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        this.zza.zza(appEventListener);
    }

    public final void setCorrelator(Correlator correlator) {
        this.zza.zza(correlator);
    }

    public final void setImmersiveMode(boolean z) {
        this.zza.zzb(z);
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zza.zza(onCustomRenderedAdLoadedListener);
    }

    public final void show() {
        this.zza.zzh();
    }
}
