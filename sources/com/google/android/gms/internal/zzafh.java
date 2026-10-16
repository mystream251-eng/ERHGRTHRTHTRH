package com.google.android.gms.internal;

import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzafh extends zzafd {
    private final RewardedVideoAdListener zza;

    public zzafh(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zza = rewardedVideoAdListener;
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zza() {
        RewardedVideoAdListener rewardedVideoAdListener = this.zza;
        if (rewardedVideoAdListener != null) {
            rewardedVideoAdListener.onRewardedVideoAdLoaded();
        }
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zza(int i) {
        RewardedVideoAdListener rewardedVideoAdListener = this.zza;
        if (rewardedVideoAdListener != null) {
            rewardedVideoAdListener.onRewardedVideoAdFailedToLoad(i);
        }
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zza(zzaeu zzaeuVar) {
        RewardedVideoAdListener rewardedVideoAdListener = this.zza;
        if (rewardedVideoAdListener != null) {
            rewardedVideoAdListener.onRewarded(new zzaff(zzaeuVar));
        }
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zzb() {
        RewardedVideoAdListener rewardedVideoAdListener = this.zza;
        if (rewardedVideoAdListener != null) {
            rewardedVideoAdListener.onRewardedVideoAdOpened();
        }
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zzc() {
        RewardedVideoAdListener rewardedVideoAdListener = this.zza;
        if (rewardedVideoAdListener != null) {
            rewardedVideoAdListener.onRewardedVideoStarted();
        }
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zzd() {
        RewardedVideoAdListener rewardedVideoAdListener = this.zza;
        if (rewardedVideoAdListener != null) {
            rewardedVideoAdListener.onRewardedVideoAdClosed();
        }
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zze() {
        RewardedVideoAdListener rewardedVideoAdListener = this.zza;
        if (rewardedVideoAdListener != null) {
            rewardedVideoAdListener.onRewardedVideoAdLeftApplication();
        }
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zzf() {
        RewardedVideoAdListener rewardedVideoAdListener = this.zza;
        if (rewardedVideoAdListener != null) {
            rewardedVideoAdListener.onRewardedVideoCompleted();
        }
    }
}
