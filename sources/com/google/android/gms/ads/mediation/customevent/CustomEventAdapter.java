package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.internal.zzaky;

/* JADX INFO: loaded from: classes2.dex */
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
    private View zza;
    private CustomEventBanner zzb;
    private CustomEventInterstitial zzc;
    private CustomEventNative zzd;

    static final class zza implements CustomEventBannerListener {
        private final CustomEventAdapter zza;
        private final MediationBannerListener zzb;

        public zza(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
            this.zza = customEventAdapter;
            this.zzb = mediationBannerListener;
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClicked() {
            zzaky.zzb("Custom event adapter called onAdClicked.");
            this.zzb.onAdClicked(this.zza);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClosed() {
            zzaky.zzb("Custom event adapter called onAdClosed.");
            this.zzb.onAdClosed(this.zza);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdFailedToLoad(int i) {
            zzaky.zzb("Custom event adapter called onAdFailedToLoad.");
            this.zzb.onAdFailedToLoad(this.zza, i);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdLeftApplication() {
            zzaky.zzb("Custom event adapter called onAdLeftApplication.");
            this.zzb.onAdLeftApplication(this.zza);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener
        public final void onAdLoaded(View view) {
            zzaky.zzb("Custom event adapter called onAdLoaded.");
            this.zza.zza(view);
            this.zzb.onAdLoaded(this.zza);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdOpened() {
            zzaky.zzb("Custom event adapter called onAdOpened.");
            this.zzb.onAdOpened(this.zza);
        }
    }

    class zzb implements CustomEventInterstitialListener {
        private final CustomEventAdapter zza;
        private final MediationInterstitialListener zzb;

        public zzb(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
            this.zza = customEventAdapter;
            this.zzb = mediationInterstitialListener;
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClicked() {
            zzaky.zzb("Custom event adapter called onAdClicked.");
            this.zzb.onAdClicked(this.zza);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClosed() {
            zzaky.zzb("Custom event adapter called onAdClosed.");
            this.zzb.onAdClosed(this.zza);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdFailedToLoad(int i) {
            zzaky.zzb("Custom event adapter called onFailedToReceiveAd.");
            this.zzb.onAdFailedToLoad(this.zza, i);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdLeftApplication() {
            zzaky.zzb("Custom event adapter called onAdLeftApplication.");
            this.zzb.onAdLeftApplication(this.zza);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener
        public final void onAdLoaded() {
            zzaky.zzb("Custom event adapter called onReceivedAd.");
            this.zzb.onAdLoaded(CustomEventAdapter.this);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdOpened() {
            zzaky.zzb("Custom event adapter called onAdOpened.");
            this.zzb.onAdOpened(this.zza);
        }
    }

    static class zzc implements CustomEventNativeListener {
        private final CustomEventAdapter zza;
        private final MediationNativeListener zzb;

        public zzc(CustomEventAdapter customEventAdapter, MediationNativeListener mediationNativeListener) {
            this.zza = customEventAdapter;
            this.zzb = mediationNativeListener;
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClicked() {
            zzaky.zzb("Custom event adapter called onAdClicked.");
            this.zzb.onAdClicked(this.zza);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClosed() {
            zzaky.zzb("Custom event adapter called onAdClosed.");
            this.zzb.onAdClosed(this.zza);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdFailedToLoad(int i) {
            zzaky.zzb("Custom event adapter called onAdFailedToLoad.");
            this.zzb.onAdFailedToLoad(this.zza, i);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventNativeListener
        public final void onAdImpression() {
            zzaky.zzb("Custom event adapter called onAdImpression.");
            this.zzb.onAdImpression(this.zza);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdLeftApplication() {
            zzaky.zzb("Custom event adapter called onAdLeftApplication.");
            this.zzb.onAdLeftApplication(this.zza);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventNativeListener
        public final void onAdLoaded(NativeAdMapper nativeAdMapper) {
            zzaky.zzb("Custom event adapter called onAdLoaded.");
            this.zzb.onAdLoaded(this.zza, nativeAdMapper);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdOpened() {
            zzaky.zzb("Custom event adapter called onAdOpened.");
            this.zzb.onAdOpened(this.zza);
        }
    }

    private static <T> T zza(String str) {
        try {
            return (T) Class.forName(str).newInstance();
        } catch (Throwable th) {
            String message = th.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 46 + String.valueOf(message).length());
            sb.append("Could not instantiate custom event adapter: ");
            sb.append(str);
            sb.append(". ");
            sb.append(message);
            zzaky.zze(sb.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(View view) {
        this.zza = view;
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public final View getBannerView() {
        return this.zza;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onDestroy() {
        CustomEventBanner customEventBanner = this.zzb;
        if (customEventBanner != null) {
            customEventBanner.onDestroy();
        }
        CustomEventInterstitial customEventInterstitial = this.zzc;
        if (customEventInterstitial != null) {
            customEventInterstitial.onDestroy();
        }
        CustomEventNative customEventNative = this.zzd;
        if (customEventNative != null) {
            customEventNative.onDestroy();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onPause() {
        CustomEventBanner customEventBanner = this.zzb;
        if (customEventBanner != null) {
            customEventBanner.onPause();
        }
        CustomEventInterstitial customEventInterstitial = this.zzc;
        if (customEventInterstitial != null) {
            customEventInterstitial.onPause();
        }
        CustomEventNative customEventNative = this.zzd;
        if (customEventNative != null) {
            customEventNative.onPause();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onResume() {
        CustomEventBanner customEventBanner = this.zzb;
        if (customEventBanner != null) {
            customEventBanner.onResume();
        }
        CustomEventInterstitial customEventInterstitial = this.zzc;
        if (customEventInterstitial != null) {
            customEventInterstitial.onResume();
        }
        CustomEventNative customEventNative = this.zzd;
        if (customEventNative != null) {
            customEventNative.onResume();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public final void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        CustomEventBanner customEventBanner = (CustomEventBanner) zza(bundle.getString("class_name"));
        this.zzb = customEventBanner;
        if (customEventBanner == null) {
            mediationBannerListener.onAdFailedToLoad(this, 0);
        } else {
            this.zzb.requestBannerAd(context, new zza(this, mediationBannerListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), adSize, mediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        CustomEventInterstitial customEventInterstitial = (CustomEventInterstitial) zza(bundle.getString("class_name"));
        this.zzc = customEventInterstitial;
        if (customEventInterstitial == null) {
            mediationInterstitialListener.onAdFailedToLoad(this, 0);
        } else {
            this.zzc.requestInterstitialAd(context, new zzb(this, mediationInterstitialListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), mediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeAdapter
    public final void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        CustomEventNative customEventNative = (CustomEventNative) zza(bundle.getString("class_name"));
        this.zzd = customEventNative;
        if (customEventNative == null) {
            mediationNativeListener.onAdFailedToLoad(this, 0);
        } else {
            this.zzd.requestNativeAd(context, new zzc(this, mediationNativeListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), nativeMediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void showInterstitial() {
        this.zzc.showInterstitial();
    }
}
