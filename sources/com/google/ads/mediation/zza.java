package com.google.ads.mediation;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/* JADX INFO: loaded from: classes.dex */
final class zza implements RewardedVideoAdListener {
    private /* synthetic */ AbstractAdViewAdapter zza;

    zza(AbstractAdViewAdapter abstractAdViewAdapter) {
        this.zza = abstractAdViewAdapter;
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewarded(RewardItem rewardItem) {
        this.zza.zzf.onRewarded(this.zza, rewardItem);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdClosed() {
        this.zza.zzf.onAdClosed(this.zza);
        AbstractAdViewAdapter.zza(this.zza, (InterstitialAd) null);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdFailedToLoad(int i) {
        this.zza.zzf.onAdFailedToLoad(this.zza, i);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdLeftApplication() {
        this.zza.zzf.onAdLeftApplication(this.zza);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdLoaded() {
        this.zza.zzf.onAdLoaded(this.zza);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdOpened() {
        this.zza.zzf.onAdOpened(this.zza);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoCompleted() {
        this.zza.zzf.onVideoCompleted(this.zza);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoStarted() {
        this.zza.zzf.onVideoStarted(this.zza);
    }
}
