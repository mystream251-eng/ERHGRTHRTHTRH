package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzagc implements MediationRewardedVideoAdListener {
    private final zzafz zza;

    public zzagc(zzafz zzafzVar) {
        this.zza = zzafzVar;
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onAdClicked(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzbq.zzb("onAdClicked must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdClicked.");
        try {
            this.zza.zzf(zzn.zza(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdClicked.", e);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onAdClosed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzbq.zzb("onAdClosed must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdClosed.");
        try {
            this.zza.zze(zzn.zza(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdClosed.", e);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onAdFailedToLoad(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        zzbq.zzb("onAdFailedToLoad must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdFailedToLoad.");
        try {
            this.zza.zzb(zzn.zza(mediationRewardedVideoAdAdapter), i);
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onAdLeftApplication(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzbq.zzb("onAdLeftApplication must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdLeftApplication.");
        try {
            this.zza.zzg(zzn.zza(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onAdLoaded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzbq.zzb("onAdLoaded must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdLoaded.");
        try {
            this.zza.zzb(zzn.zza(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdLoaded.", e);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onAdOpened(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzbq.zzb("onAdOpened must be called on the main UI thread.");
        zzaky.zzb("Adapter called onAdOpened.");
        try {
            this.zza.zzc(zzn.zza(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onAdOpened.", e);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onInitializationFailed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        zzbq.zzb("onInitializationFailed must be called on the main UI thread.");
        zzaky.zzb("Adapter called onInitializationFailed.");
        try {
            this.zza.zza(zzn.zza(mediationRewardedVideoAdAdapter), i);
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onInitializationFailed.", e);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onInitializationSucceeded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzbq.zzb("onInitializationSucceeded must be called on the main UI thread.");
        zzaky.zzb("Adapter called onInitializationSucceeded.");
        try {
            this.zza.zza(zzn.zza(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onInitializationSucceeded.", e);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onRewarded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, RewardItem rewardItem) {
        zzbq.zzb("onRewarded must be called on the main UI thread.");
        zzaky.zzb("Adapter called onRewarded.");
        try {
            if (rewardItem != null) {
                this.zza.zza(zzn.zza(mediationRewardedVideoAdAdapter), new zzagd(rewardItem));
            } else {
                this.zza.zza(zzn.zza(mediationRewardedVideoAdAdapter), new zzagd("", 1));
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onRewarded.", e);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onVideoCompleted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzbq.zzb("onVideoCompleted must be called on the main UI thread.");
        zzaky.zzb("Adapter called onVideoCompleted.");
        try {
            this.zza.zzh(zzn.zza(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onVideoCompleted.", e);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
    public final void onVideoStarted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzbq.zzb("onVideoStarted must be called on the main UI thread.");
        zzaky.zzb("Adapter called onVideoStarted.");
        try {
            this.zza.zzd(zzn.zza(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzaky.zzc("Could not call onVideoStarted.", e);
        }
    }
}
