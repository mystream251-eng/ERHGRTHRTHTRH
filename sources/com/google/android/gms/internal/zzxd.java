package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.common.internal.zzbq;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzxd implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener {
    private final zzwl zza;
    private NativeAdMapper zzb;
    private zza zzc;
    private NativeCustomTemplateAd zzd;

    public zzxd(zzwl zzwlVar) {
        this.zza = zzwlVar;
    }

    private static void zza(MediationNativeAdapter mediationNativeAdapter, zza zzaVar, NativeAdMapper nativeAdMapper) {
        if (mediationNativeAdapter instanceof AdMobAdapter) {
            return;
        }
        VideoController videoController = new VideoController();
        videoController.zza(new zzxa());
        if (nativeAdMapper == null || !nativeAdMapper.hasVideoContent()) {
            return;
        }
        nativeAdMapper.zza(videoController);
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdClicked(MediationBannerAdapter mediationBannerAdapter) {
        zzbq.zzb("onAdClicked must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdClicked.");
        try {
            this.zza.zza();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdClicked.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdClicked(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzbq.zzb("onAdClicked must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdClicked.");
        try {
            this.zza.zza();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdClicked.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdClicked(MediationNativeAdapter mediationNativeAdapter) {
        zzbq.zzb("onAdClicked must be called on the main UI thread.");
        NativeAdMapper nativeAdMapper = this.zzb;
        zza zzaVar = this.zzc;
        if (this.zzd == null) {
            if (nativeAdMapper == null && zzaVar == null) {
                zzaky.zze("Could not call onAdClicked since mapper is null.");
                return;
            }
            if (zzaVar != null && !zzaVar.zzn()) {
                zzaky.zzb("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            } else if (nativeAdMapper != null && !nativeAdMapper.getOverrideClickHandling()) {
                zzaky.zzb("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            }
        }
        zzaky.zzb("Adapter called onAdClicked.");
        try {
            this.zza.zza();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdClicked.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdClosed(MediationBannerAdapter mediationBannerAdapter) {
        zzbq.zzb("onAdClosed must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdClosed.");
        try {
            this.zza.zzb();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdClosed.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdClosed(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzbq.zzb("onAdClosed must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdClosed.");
        try {
            this.zza.zzb();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdClosed.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdClosed(MediationNativeAdapter mediationNativeAdapter) {
        zzbq.zzb("onAdClosed must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdClosed.");
        try {
            this.zza.zzb();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdClosed.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdFailedToLoad(MediationBannerAdapter mediationBannerAdapter, int i) {
        zzbq.zzb("onAdFailedToLoad must be called on the main UI thread.");
        StringBuilder sb = new StringBuilder(55);
        sb.append("Adapter called onAdFailedToLoad with error. ");
        sb.append(i);
        zzaky.zzb(sb.toString());
        try {
            this.zza.zza(i);
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdFailedToLoad(MediationInterstitialAdapter mediationInterstitialAdapter, int i) {
        zzbq.zzb("onAdFailedToLoad must be called on the main UI thread.");
        StringBuilder sb = new StringBuilder(55);
        sb.append("Adapter called onAdFailedToLoad with error ");
        sb.append(i);
        sb.append(".");
        zzaky.zzb(sb.toString());
        try {
            this.zza.zza(i);
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdFailedToLoad(MediationNativeAdapter mediationNativeAdapter, int i) {
        zzbq.zzb("onAdFailedToLoad must be called on the main UI thread.");
        StringBuilder sb = new StringBuilder(55);
        sb.append("Adapter called onAdFailedToLoad with error ");
        sb.append(i);
        sb.append(".");
        zzaky.zzb(sb.toString());
        try {
            this.zza.zza(i);
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdImpression(MediationNativeAdapter mediationNativeAdapter) {
        zzbq.zzb("onAdImpression must be called on the main UI thread.");
        NativeAdMapper nativeAdMapper = this.zzb;
        zza zzaVar = this.zzc;
        if (this.zzd == null) {
            if (nativeAdMapper == null && zzaVar == null) {
                zzaky.zze("Could not call onAdImpression since AdMapper is null. ");
                return;
            }
            if (zzaVar != null && !zzaVar.zzm()) {
                zzaky.zzb("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            } else if (nativeAdMapper != null && !nativeAdMapper.getOverrideImpressionRecording()) {
                zzaky.zzb("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            }
        }
        zzaky.zzb("Adapter called onAdImpression.");
        try {
            this.zza.zzf();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdImpression.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdLeftApplication(MediationBannerAdapter mediationBannerAdapter) {
        zzbq.zzb("onAdLeftApplication must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdLeftApplication.");
        try {
            this.zza.zzc();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdLeftApplication(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzbq.zzb("onAdLeftApplication must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdLeftApplication.");
        try {
            this.zza.zzc();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdLeftApplication(MediationNativeAdapter mediationNativeAdapter) {
        zzbq.zzb("onAdLeftApplication must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdLeftApplication.");
        try {
            this.zza.zzc();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdLoaded(MediationBannerAdapter mediationBannerAdapter) {
        zzbq.zzb("onAdLoaded must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdLoaded.");
        try {
            this.zza.zze();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLoaded.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdLoaded(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzbq.zzb("onAdLoaded must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdLoaded.");
        try {
            this.zza.zze();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLoaded.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdLoaded(MediationNativeAdapter mediationNativeAdapter, NativeAdMapper nativeAdMapper) {
        zzbq.zzb("onAdLoaded must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdLoaded.");
        this.zzb = nativeAdMapper;
        this.zzc = null;
        zza(mediationNativeAdapter, (zza) null, nativeAdMapper);
        try {
            this.zza.zze();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLoaded.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdOpened(MediationBannerAdapter mediationBannerAdapter) {
        zzbq.zzb("onAdOpened must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdOpened.");
        try {
            this.zza.zzd();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdOpened.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdOpened(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzbq.zzb("onAdOpened must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdOpened.");
        try {
            this.zza.zzd();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdOpened.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdOpened(MediationNativeAdapter mediationNativeAdapter) {
        zzbq.zzb("onAdOpened must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdOpened.");
        try {
            this.zza.zzd();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdOpened.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onVideoEnd(MediationNativeAdapter mediationNativeAdapter) {
        zzbq.zzb("onVideoEnd must be called on the main UI thread.");
        zzaky.zzb("Adapter called onVideoEnd.");
        try {
            this.zza.zzg();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onVideoEnd.", e);
        }
    }

    public final NativeAdMapper zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void zza(MediationBannerAdapter mediationBannerAdapter, String str, String str2) {
        zzbq.zzb("onAppEvent must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAppEvent.");
        try {
            this.zza.zza(str, str2);
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAppEvent.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void zza(MediationNativeAdapter mediationNativeAdapter, NativeCustomTemplateAd nativeCustomTemplateAd) {
        zzbq.zzb("onAdLoaded must be called on the main UI thread.");
        String strValueOf = String.valueOf(nativeCustomTemplateAd.getCustomTemplateId());
        zzaky.zzb(strValueOf.length() != 0 ? "Adapter called onAdLoaded with template id ".concat(strValueOf) : new String("Adapter called onAdLoaded with template id "));
        this.zzd = nativeCustomTemplateAd;
        try {
            this.zza.zze();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLoaded.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void zza(MediationNativeAdapter mediationNativeAdapter, NativeCustomTemplateAd nativeCustomTemplateAd, String str) {
        if (!(nativeCustomTemplateAd instanceof zzrr)) {
            zzaky.zze("Unexpected native custom template ad type.");
            return;
        }
        try {
            this.zza.zza(((zzrr) nativeCustomTemplateAd).zza(), str);
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onCustomClick.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void zza(MediationNativeAdapter mediationNativeAdapter, zza zzaVar) {
        zzbq.zzb("onAdLoaded must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdLoaded.");
        this.zzc = zzaVar;
        this.zzb = null;
        zza(mediationNativeAdapter, zzaVar, (NativeAdMapper) null);
        try {
            this.zza.zze();
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLoaded.", e);
        }
    }

    public final zza zzb() {
        return this.zzc;
    }

    public final NativeCustomTemplateAd zzc() {
        return this.zzd;
    }
}
