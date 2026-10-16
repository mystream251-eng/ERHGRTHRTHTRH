package com.google.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeAdViewHolder;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzaqk;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzmm;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@zzabh
public abstract class AbstractAdViewAdapter implements com.google.android.gms.ads.mediation.MediationBannerAdapter, MediationNativeAdapter, OnImmersiveModeUpdatedListener, com.google.android.gms.ads.mediation.zzb, MediationRewardedVideoAdAdapter, zzaqk {
    public static final String AD_UNIT_ID_PARAMETER = "pubid";
    private AdView zza;
    private InterstitialAd zzb;
    private AdLoader zzc;
    private Context zzd;
    private InterstitialAd zze;
    private MediationRewardedVideoAdListener zzf;
    private RewardedVideoAdListener zzg = new com.google.ads.mediation.zza(this);

    static class zza extends NativeAppInstallAdMapper {
        private final NativeAppInstallAd zza;

        public zza(NativeAppInstallAd nativeAppInstallAd) {
            this.zza = nativeAppInstallAd;
            setHeadline(nativeAppInstallAd.getHeadline().toString());
            setImages(nativeAppInstallAd.getImages());
            setBody(nativeAppInstallAd.getBody().toString());
            setIcon(nativeAppInstallAd.getIcon());
            setCallToAction(nativeAppInstallAd.getCallToAction().toString());
            if (nativeAppInstallAd.getStarRating() != null) {
                setStarRating(nativeAppInstallAd.getStarRating().doubleValue());
            }
            if (nativeAppInstallAd.getStore() != null) {
                setStore(nativeAppInstallAd.getStore().toString());
            }
            if (nativeAppInstallAd.getPrice() != null) {
                setPrice(nativeAppInstallAd.getPrice().toString());
            }
            setOverrideImpressionRecording(true);
            setOverrideClickHandling(true);
            zza(nativeAppInstallAd.getVideoController());
        }

        @Override // com.google.android.gms.ads.mediation.NativeAdMapper
        public final void trackView(View view) {
            if (view instanceof NativeAdView) {
                ((NativeAdView) view).setNativeAd(this.zza);
            }
            NativeAdViewHolder nativeAdViewHolder = NativeAdViewHolder.zza.get(view);
            if (nativeAdViewHolder != null) {
                nativeAdViewHolder.setNativeAd(this.zza);
            }
        }
    }

    static class zzb extends NativeContentAdMapper {
        private final NativeContentAd zza;

        public zzb(NativeContentAd nativeContentAd) {
            this.zza = nativeContentAd;
            setHeadline(nativeContentAd.getHeadline().toString());
            setImages(nativeContentAd.getImages());
            setBody(nativeContentAd.getBody().toString());
            if (nativeContentAd.getLogo() != null) {
                setLogo(nativeContentAd.getLogo());
            }
            setCallToAction(nativeContentAd.getCallToAction().toString());
            setAdvertiser(nativeContentAd.getAdvertiser().toString());
            setOverrideImpressionRecording(true);
            setOverrideClickHandling(true);
            zza(nativeContentAd.getVideoController());
        }

        @Override // com.google.android.gms.ads.mediation.NativeAdMapper
        public final void trackView(View view) {
            if (view instanceof NativeAdView) {
                ((NativeAdView) view).setNativeAd(this.zza);
            }
            NativeAdViewHolder nativeAdViewHolder = NativeAdViewHolder.zza.get(view);
            if (nativeAdViewHolder != null) {
                nativeAdViewHolder.setNativeAd(this.zza);
            }
        }
    }

    static class zzc extends com.google.android.gms.ads.mediation.zza {
        private final UnifiedNativeAd zza;

        public zzc(UnifiedNativeAd unifiedNativeAd) {
            this.zza = unifiedNativeAd;
            zza(unifiedNativeAd.zza());
            zza(unifiedNativeAd.zzb());
            zzb(unifiedNativeAd.zzc());
            zza(unifiedNativeAd.zzd());
            zzc(unifiedNativeAd.zze());
            zzd(unifiedNativeAd.zzf());
            zza(unifiedNativeAd.zzg());
            zze(unifiedNativeAd.zzh());
            zzf(unifiedNativeAd.zzi());
            zza(unifiedNativeAd.zzk());
            zza(true);
            zzb(true);
            zza(unifiedNativeAd.zzj());
        }

        @Override // com.google.android.gms.ads.mediation.zza
        public final void zza(View view, Map<String, View> map, Map<String, View> map2) {
            if (view instanceof com.google.android.gms.ads.formats.zzd) {
                com.google.android.gms.ads.formats.zzd.zza(this.zza);
            }
        }
    }

    static final class zzd extends AdListener implements AppEventListener, zzkf {
        private AbstractAdViewAdapter zza;
        private com.google.android.gms.ads.mediation.MediationBannerListener zzb;

        public zzd(AbstractAdViewAdapter abstractAdViewAdapter, com.google.android.gms.ads.mediation.MediationBannerListener mediationBannerListener) {
            this.zza = abstractAdViewAdapter;
            this.zzb = mediationBannerListener;
        }

        @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.zzkf
        public final void onAdClicked() {
            this.zzb.onAdClicked(this.zza);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdClosed() {
            this.zzb.onAdClosed(this.zza);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdFailedToLoad(int i) {
            this.zzb.onAdFailedToLoad(this.zza, i);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdLeftApplication() {
            this.zzb.onAdLeftApplication(this.zza);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdLoaded() {
            this.zzb.onAdLoaded(this.zza);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdOpened() {
            this.zzb.onAdOpened(this.zza);
        }

        @Override // com.google.android.gms.ads.doubleclick.AppEventListener
        public final void onAppEvent(String str, String str2) {
            this.zzb.zza(this.zza, str, str2);
        }
    }

    static final class zze extends AdListener implements zzkf {
        private AbstractAdViewAdapter zza;
        private com.google.android.gms.ads.mediation.MediationInterstitialListener zzb;

        public zze(AbstractAdViewAdapter abstractAdViewAdapter, com.google.android.gms.ads.mediation.MediationInterstitialListener mediationInterstitialListener) {
            this.zza = abstractAdViewAdapter;
            this.zzb = mediationInterstitialListener;
        }

        @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.zzkf
        public final void onAdClicked() {
            this.zzb.onAdClicked(this.zza);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdClosed() {
            this.zzb.onAdClosed(this.zza);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdFailedToLoad(int i) {
            this.zzb.onAdFailedToLoad(this.zza, i);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdLeftApplication() {
            this.zzb.onAdLeftApplication(this.zza);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdLoaded() {
            this.zzb.onAdLoaded(this.zza);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdOpened() {
            this.zzb.onAdOpened(this.zza);
        }
    }

    static final class zzf extends AdListener implements NativeAppInstallAd.OnAppInstallAdLoadedListener, NativeContentAd.OnContentAdLoadedListener, NativeCustomTemplateAd.OnCustomClickListener, NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener, UnifiedNativeAd.zza {
        private AbstractAdViewAdapter zza;
        private MediationNativeListener zzb;

        public zzf(AbstractAdViewAdapter abstractAdViewAdapter, MediationNativeListener mediationNativeListener) {
            this.zza = abstractAdViewAdapter;
            this.zzb = mediationNativeListener;
        }

        @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.zzkf
        public final void onAdClicked() {
            this.zzb.onAdClicked(this.zza);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdClosed() {
            this.zzb.onAdClosed(this.zza);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdFailedToLoad(int i) {
            this.zzb.onAdFailedToLoad(this.zza, i);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdImpression() {
            this.zzb.onAdImpression(this.zza);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdLeftApplication() {
            this.zzb.onAdLeftApplication(this.zza);
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdLoaded() {
        }

        @Override // com.google.android.gms.ads.AdListener
        public final void onAdOpened() {
            this.zzb.onAdOpened(this.zza);
        }

        @Override // com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener
        public final void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
            this.zzb.onAdLoaded(this.zza, new zza(nativeAppInstallAd));
        }

        @Override // com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener
        public final void onContentAdLoaded(NativeContentAd nativeContentAd) {
            this.zzb.onAdLoaded(this.zza, new zzb(nativeContentAd));
        }

        @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener
        public final void onCustomClick(NativeCustomTemplateAd nativeCustomTemplateAd, String str) {
            this.zzb.zza(this.zza, nativeCustomTemplateAd, str);
        }

        @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener
        public final void onCustomTemplateAdLoaded(NativeCustomTemplateAd nativeCustomTemplateAd) {
            this.zzb.zza(this.zza, nativeCustomTemplateAd);
        }

        @Override // com.google.android.gms.ads.formats.UnifiedNativeAd.zza
        public final void zza(UnifiedNativeAd unifiedNativeAd) {
            this.zzb.zza(this.zza, new zzc(unifiedNativeAd));
        }
    }

    private final AdRequest zza(Context context, com.google.android.gms.ads.mediation.MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        AdRequest.Builder builder = new AdRequest.Builder();
        Date birthday = mediationAdRequest.getBirthday();
        if (birthday != null) {
            builder.setBirthday(birthday);
        }
        int gender = mediationAdRequest.getGender();
        if (gender != 0) {
            builder.setGender(gender);
        }
        Set<String> keywords = mediationAdRequest.getKeywords();
        if (keywords != null) {
            Iterator<String> it = keywords.iterator();
            while (it.hasNext()) {
                builder.addKeyword(it.next());
            }
        }
        Location location = mediationAdRequest.getLocation();
        if (location != null) {
            builder.setLocation(location);
        }
        if (mediationAdRequest.isTesting()) {
            zzlc.zza();
            builder.addTestDevice(zzako.zza(context));
        }
        if (mediationAdRequest.taggedForChildDirectedTreatment() != -1) {
            builder.tagForChildDirectedTreatment(mediationAdRequest.taggedForChildDirectedTreatment() == 1);
        }
        builder.setIsDesignedForFamilies(mediationAdRequest.isDesignedForFamilies());
        builder.addNetworkExtrasBundle(AdMobAdapter.class, zza(bundle, bundle2));
        return builder.build();
    }

    static /* synthetic */ InterstitialAd zza(AbstractAdViewAdapter abstractAdViewAdapter, InterstitialAd interstitialAd) {
        abstractAdViewAdapter.zze = null;
        return null;
    }

    public String getAdUnitId(Bundle bundle) {
        return bundle.getString(AD_UNIT_ID_PARAMETER);
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public View getBannerView() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.zzaqk
    public Bundle getInterstitialAdapterInfo() {
        return new com.google.android.gms.ads.mediation.MediationAdapter.zza().zza(1).zza();
    }

    @Override // com.google.android.gms.ads.mediation.zzb
    public zzmm getVideoController() {
        VideoController videoControllerZza;
        AdView adView = this.zza;
        if (adView == null || (videoControllerZza = adView.zza()) == null) {
            return null;
        }
        return videoControllerZza.zza();
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void initialize(Context context, com.google.android.gms.ads.mediation.MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        this.zzd = context.getApplicationContext();
        this.zzf = mediationRewardedVideoAdListener;
        mediationRewardedVideoAdListener.onInitializationSucceeded(this);
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public boolean isInitialized() {
        return this.zzf != null;
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void loadAd(com.google.android.gms.ads.mediation.MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        if (this.zzd == null || this.zzf == null) {
            zzaky.zzc("AdMobAdapter.loadAd called before initialize.");
            return;
        }
        InterstitialAd interstitialAd = new InterstitialAd(this.zzd);
        this.zze = interstitialAd;
        interstitialAd.zza(true);
        this.zze.setAdUnitId(getAdUnitId(bundle));
        this.zze.zza(this.zzg);
        this.zze.loadAd(zza(this.zzd, mediationAdRequest, bundle2, bundle));
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
        AdView adView = this.zza;
        if (adView != null) {
            adView.destroy();
            this.zza = null;
        }
        if (this.zzb != null) {
            this.zzb = null;
        }
        if (this.zzc != null) {
            this.zzc = null;
        }
        if (this.zze != null) {
            this.zze = null;
        }
    }

    @Override // com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener
    public void onImmersiveModeUpdated(boolean z) {
        InterstitialAd interstitialAd = this.zzb;
        if (interstitialAd != null) {
            interstitialAd.setImmersiveMode(z);
        }
        InterstitialAd interstitialAd2 = this.zze;
        if (interstitialAd2 != null) {
            interstitialAd2.setImmersiveMode(z);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
        AdView adView = this.zza;
        if (adView != null) {
            adView.pause();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
        AdView adView = this.zza;
        if (adView != null) {
            adView.resume();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(Context context, com.google.android.gms.ads.mediation.MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, com.google.android.gms.ads.mediation.MediationAdRequest mediationAdRequest, Bundle bundle2) {
        AdView adView = new AdView(context);
        this.zza = adView;
        adView.setAdSize(new AdSize(adSize.getWidth(), adSize.getHeight()));
        this.zza.setAdUnitId(getAdUnitId(bundle));
        this.zza.setAdListener(new zzd(this, mediationBannerListener));
        this.zza.loadAd(zza(context, mediationAdRequest, bundle2, bundle));
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(Context context, com.google.android.gms.ads.mediation.MediationInterstitialListener mediationInterstitialListener, Bundle bundle, com.google.android.gms.ads.mediation.MediationAdRequest mediationAdRequest, Bundle bundle2) {
        InterstitialAd interstitialAd = new InterstitialAd(context);
        this.zzb = interstitialAd;
        interstitialAd.setAdUnitId(getAdUnitId(bundle));
        this.zzb.setAdListener(new zze(this, mediationInterstitialListener));
        this.zzb.loadAd(zza(context, mediationAdRequest, bundle2, bundle));
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        zzf zzfVar = new zzf(this, mediationNativeListener);
        AdLoader.Builder builderWithAdListener = new AdLoader.Builder(context, bundle.getString(AD_UNIT_ID_PARAMETER)).withAdListener(zzfVar);
        NativeAdOptions nativeAdOptions = nativeMediationAdRequest.getNativeAdOptions();
        if (nativeAdOptions != null) {
            builderWithAdListener.withNativeAdOptions(nativeAdOptions);
        }
        if (nativeMediationAdRequest.zza()) {
            builderWithAdListener.zza(zzfVar);
        }
        if (nativeMediationAdRequest.isAppInstallAdRequested()) {
            builderWithAdListener.forAppInstallAd(zzfVar);
        }
        if (nativeMediationAdRequest.isContentAdRequested()) {
            builderWithAdListener.forContentAd(zzfVar);
        }
        if (nativeMediationAdRequest.zzb()) {
            for (String str : nativeMediationAdRequest.zzc().keySet()) {
                builderWithAdListener.forCustomTemplateAd(str, zzfVar, nativeMediationAdRequest.zzc().get(str).booleanValue() ? zzfVar : null);
            }
        }
        AdLoader adLoaderBuild = builderWithAdListener.build();
        this.zzc = adLoaderBuild;
        adLoaderBuild.loadAd(zza(context, nativeMediationAdRequest, bundle2, bundle));
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        this.zzb.show();
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void showVideo() {
        this.zze.show();
    }

    protected abstract Bundle zza(Bundle bundle, Bundle bundle2);
}
