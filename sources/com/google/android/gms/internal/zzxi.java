package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzxi<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    private final zzwl zza;

    public zzxi(zzwl zzwlVar) {
        this.zza = zzwlVar;
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public final void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzaky.zzb("Adapter called onClick.");
        zzlc.zza();
        if (!zzako.zzb()) {
            zzaky.zze("onClick must be called on the main UI thread.");
            zzako.zza.post(new zzxj(this));
        } else {
            try {
                this.zza.zza();
            } catch (RemoteException e) {
                zzaky.zzc("Could not call onAdClicked.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public final void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzaky.zzb("Adapter called onDismissScreen.");
        zzlc.zza();
        if (!zzako.zzb()) {
            zzaky.zze("onDismissScreen must be called on the main UI thread.");
            zzako.zza.post(new zzxm(this));
        } else {
            try {
                this.zza.zzb();
            } catch (RemoteException e) {
                zzaky.zzc("Could not call onAdClosed.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public final void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzaky.zzb("Adapter called onDismissScreen.");
        zzlc.zza();
        if (!zzako.zzb()) {
            zzaky.zze("onDismissScreen must be called on the main UI thread.");
            zzako.zza.post(new zzxr(this));
        } else {
            try {
                this.zza.zzb();
            } catch (RemoteException e) {
                zzaky.zzc("Could not call onAdClosed.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public final void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, AdRequest.ErrorCode errorCode) {
        String strValueOf = String.valueOf(errorCode);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 47);
        sb.append("Adapter called onFailedToReceiveAd with error. ");
        sb.append(strValueOf);
        zzaky.zzb(sb.toString());
        zzlc.zza();
        if (!zzako.zzb()) {
            zzaky.zze("onFailedToReceiveAd must be called on the main UI thread.");
            zzako.zza.post(new zzxn(this, errorCode));
        } else {
            try {
                this.zza.zza(zzxu.zza(errorCode));
            } catch (RemoteException e) {
                zzaky.zzc("Could not call onAdFailedToLoad.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public final void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, AdRequest.ErrorCode errorCode) {
        String strValueOf = String.valueOf(errorCode);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 47);
        sb.append("Adapter called onFailedToReceiveAd with error ");
        sb.append(strValueOf);
        sb.append(".");
        zzaky.zzb(sb.toString());
        zzlc.zza();
        if (!zzako.zzb()) {
            zzaky.zze("onFailedToReceiveAd must be called on the main UI thread.");
            zzako.zza.post(new zzxs(this, errorCode));
        } else {
            try {
                this.zza.zza(zzxu.zza(errorCode));
            } catch (RemoteException e) {
                zzaky.zzc("Could not call onAdFailedToLoad.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public final void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzaky.zzb("Adapter called onLeaveApplication.");
        zzlc.zza();
        if (!zzako.zzb()) {
            zzaky.zze("onLeaveApplication must be called on the main UI thread.");
            zzako.zza.post(new zzxo(this));
        } else {
            try {
                this.zza.zzc();
            } catch (RemoteException e) {
                zzaky.zzc("Could not call onAdLeftApplication.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public final void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzaky.zzb("Adapter called onLeaveApplication.");
        zzlc.zza();
        if (!zzako.zzb()) {
            zzaky.zze("onLeaveApplication must be called on the main UI thread.");
            zzako.zza.post(new zzxt(this));
        } else {
            try {
                this.zza.zzc();
            } catch (RemoteException e) {
                zzaky.zzc("Could not call onAdLeftApplication.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public final void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzaky.zzb("Adapter called onPresentScreen.");
        zzlc.zza();
        if (!zzako.zzb()) {
            zzaky.zze("onPresentScreen must be called on the main UI thread.");
            zzako.zza.post(new zzxp(this));
        } else {
            try {
                this.zza.zzd();
            } catch (RemoteException e) {
                zzaky.zzc("Could not call onAdOpened.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public final void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzaky.zzb("Adapter called onPresentScreen.");
        zzlc.zza();
        if (!zzako.zzb()) {
            zzaky.zze("onPresentScreen must be called on the main UI thread.");
            zzako.zza.post(new zzxk(this));
        } else {
            try {
                this.zza.zzd();
            } catch (RemoteException e) {
                zzaky.zzc("Could not call onAdOpened.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public final void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzaky.zzb("Adapter called onReceivedAd.");
        zzlc.zza();
        if (!zzako.zzb()) {
            zzaky.zze("onReceivedAd must be called on the main UI thread.");
            zzako.zza.post(new zzxq(this));
        } else {
            try {
                this.zza.zze();
            } catch (RemoteException e) {
                zzaky.zzc("Could not call onAdLoaded.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public final void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzaky.zzb("Adapter called onReceivedAd.");
        zzlc.zza();
        if (!zzako.zzb()) {
            zzaky.zze("onReceivedAd must be called on the main UI thread.");
            zzako.zza.post(new zzxl(this));
        } else {
            try {
                this.zza.zze();
            } catch (RemoteException e) {
                zzaky.zzc("Could not call onAdLoaded.", e);
            }
        }
    }
}
