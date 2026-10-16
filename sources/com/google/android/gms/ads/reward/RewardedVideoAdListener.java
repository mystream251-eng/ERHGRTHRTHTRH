package com.google.android.gms.ads.reward;

/* JADX INFO: loaded from: classes2.dex */
public interface RewardedVideoAdListener {
    void onRewarded(RewardItem rewardItem);

    void onRewardedVideoAdClosed();

    void onRewardedVideoAdFailedToLoad(int i);

    void onRewardedVideoAdLeftApplication();

    void onRewardedVideoAdLoaded();

    void onRewardedVideoAdOpened();

    void onRewardedVideoCompleted();

    void onRewardedVideoStarted();
}
