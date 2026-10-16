package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzmw;

/* JADX INFO: loaded from: classes2.dex */
public final class PublisherAdView extends ViewGroup {
    private final zzmw zza;

    public PublisherAdView(Context context) {
        super(context);
        this.zza = new zzmw(this);
    }

    public PublisherAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zza = new zzmw(this, attributeSet, true);
        zzbq.zza(context, "Context cannot be null");
    }

    public PublisherAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zza = new zzmw(this, attributeSet, true);
    }

    public final void destroy() {
        this.zza.zza();
    }

    public final AdListener getAdListener() {
        return this.zza.zzb();
    }

    public final AdSize getAdSize() {
        return this.zza.zzc();
    }

    public final AdSize[] getAdSizes() {
        return this.zza.zzd();
    }

    public final String getAdUnitId() {
        return this.zza.zze();
    }

    public final AppEventListener getAppEventListener() {
        return this.zza.zzf();
    }

    public final String getMediationAdapterClassName() {
        return this.zza.zzk();
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zza.zzg();
    }

    public final VideoController getVideoController() {
        return this.zza.zzm();
    }

    public final VideoOptions getVideoOptions() {
        return this.zza.zzo();
    }

    public final boolean isLoading() {
        return this.zza.zzl();
    }

    public final void loadAd(PublisherAdRequest publisherAdRequest) {
        this.zza.zza(publisherAdRequest.zza());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            return;
        }
        int measuredWidth = childAt.getMeasuredWidth();
        int measuredHeight = childAt.getMeasuredHeight();
        int i5 = ((i3 - i) - measuredWidth) / 2;
        int i6 = ((i4 - i2) - measuredHeight) / 2;
        childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int heightInPixels;
        int measuredWidth = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            AdSize adSize = null;
            try {
                adSize = getAdSize();
            } catch (NullPointerException e) {
                zzaky.zzb("Unable to retrieve ad size.", e);
            }
            if (adSize != null) {
                Context context = getContext();
                int widthInPixels = adSize.getWidthInPixels(context);
                heightInPixels = adSize.getHeightInPixels(context);
                measuredWidth = widthInPixels;
            } else {
                heightInPixels = 0;
            }
        } else {
            measureChild(childAt, i, i2);
            measuredWidth = childAt.getMeasuredWidth();
            heightInPixels = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(measuredWidth, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(heightInPixels, getSuggestedMinimumHeight()), i2));
    }

    public final void pause() {
        this.zza.zzh();
    }

    public final void recordManualImpression() {
        this.zza.zzi();
    }

    public final void resume() {
        this.zza.zzj();
    }

    public final void setAdListener(AdListener adListener) {
        this.zza.zza(adListener);
    }

    public final void setAdSizes(AdSize... adSizeArr) {
        if (adSizeArr == null || adSizeArr.length <= 0) {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
        }
        this.zza.zzb(adSizeArr);
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

    public final void setManualImpressionsEnabled(boolean z) {
        this.zza.zza(z);
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zza.zza(onCustomRenderedAdLoadedListener);
    }

    public final void setVideoOptions(VideoOptions videoOptions) {
        this.zza.zza(videoOptions);
    }

    public final boolean zza(zzlt zzltVar) {
        return this.zza.zza(zzltVar);
    }
}
