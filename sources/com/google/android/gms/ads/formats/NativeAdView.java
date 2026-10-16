package com.google.android.gms.ads.formats;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzqw;

/* JADX INFO: loaded from: classes2.dex */
public class NativeAdView extends FrameLayout {
    private final FrameLayout zza;
    private final zzqw zzb;

    public NativeAdView(Context context) {
        super(context);
        this.zza = zza(context);
        this.zzb = zza();
    }

    public NativeAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zza = zza(context);
        this.zzb = zza();
    }

    public NativeAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zza = zza(context);
        this.zzb = zza();
    }

    public NativeAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.zza = zza(context);
        this.zzb = zza();
    }

    private final FrameLayout zza(Context context) {
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(frameLayout);
        return frameLayout;
    }

    private final zzqw zza() {
        zzbq.zza(this.zza, "createDelegate must be called after mOverlayFrame has been created");
        if (isInEditMode()) {
            return null;
        }
        return zzlc.zzb().zza(this.zza.getContext(), this, this.zza);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        super.bringChildToFront(this.zza);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void bringChildToFront(View view) {
        super.bringChildToFront(view);
        FrameLayout frameLayout = this.zza;
        if (frameLayout != view) {
            super.bringChildToFront(frameLayout);
        }
    }

    public void destroy() {
        try {
            this.zzb.zza();
        } catch (RemoteException e) {
            zzaky.zzb("Unable to destroy native ad view", e);
        }
    }

    public AdChoicesView getAdChoicesView() {
        View viewZza = zza(NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW);
        if (viewZza instanceof AdChoicesView) {
            return (AdChoicesView) viewZza;
        }
        return null;
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        zzqw zzqwVar = this.zzb;
        if (zzqwVar != null) {
            try {
                zzqwVar.zza(zzn.zza(view), i);
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call onVisibilityChanged on delegate", e);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        super.removeAllViews();
        super.addView(this.zza);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.zza == view) {
            return;
        }
        super.removeView(view);
    }

    public void setAdChoicesView(AdChoicesView adChoicesView) {
        zza(NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, adChoicesView);
    }

    public void setNativeAd(NativeAd nativeAd) {
        try {
            this.zzb.zza((IObjectWrapper) nativeAd.zza());
        } catch (RemoteException e) {
            zzaky.zzb("Unable to call setNativeAd on delegate", e);
        }
    }

    protected final View zza(String str) {
        try {
            IObjectWrapper iObjectWrapperZza = this.zzb.zza(str);
            if (iObjectWrapperZza != null) {
                return (View) zzn.zza(iObjectWrapperZza);
            }
            return null;
        } catch (RemoteException e) {
            zzaky.zzb("Unable to call getAssetView on delegate", e);
            return null;
        }
    }

    protected final void zza(String str, View view) {
        try {
            this.zzb.zza(str, zzn.zza(view));
        } catch (RemoteException e) {
            zzaky.zzb("Unable to call setAssetView on delegate", e);
        }
    }
}
