package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzmw;

/* JADX INFO: loaded from: classes2.dex */
class BaseAdView extends ViewGroup {
    protected final zzmw zza;

    public BaseAdView(Context context, int i) {
        super(context);
        this.zza = new zzmw(this, i);
    }

    public BaseAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.zza = new zzmw(this, attributeSet, false, i);
    }

    public BaseAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.zza = new zzmw(this, attributeSet, false, i2);
    }

    public void destroy() {
        this.zza.zza();
    }

    public AdListener getAdListener() {
        return this.zza.zzb();
    }

    public AdSize getAdSize() {
        return this.zza.zzc();
    }

    public String getAdUnitId() {
        return this.zza.zze();
    }

    public String getMediationAdapterClassName() {
        return this.zza.zzk();
    }

    public boolean isLoading() {
        return this.zza.zzl();
    }

    public void loadAd(AdRequest adRequest) {
        this.zza.zza(adRequest.zza());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
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
    protected void onMeasure(int i, int i2) {
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

    public void pause() {
        this.zza.zzh();
    }

    public void resume() {
        this.zza.zzj();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setAdListener(AdListener adListener) {
        this.zza.zza(adListener);
        if (adListener == 0) {
            this.zza.zza((zzkf) null);
            this.zza.zza((AppEventListener) null);
            return;
        }
        if (adListener instanceof zzkf) {
            this.zza.zza((zzkf) adListener);
        }
        if (adListener instanceof AppEventListener) {
            this.zza.zza((AppEventListener) adListener);
        }
    }

    public void setAdSize(AdSize adSize) {
        this.zza.zza(adSize);
    }

    public void setAdUnitId(String str) {
        this.zza.zza(str);
    }
}
