package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public interface zzmb extends IInterface {
    zzlo createAdLoaderBuilder(IObjectWrapper iObjectWrapper, String str, zzwf zzwfVar, int i) throws RemoteException;

    zzyq createAdOverlay(IObjectWrapper iObjectWrapper) throws RemoteException;

    zzlt createBannerAdManager(IObjectWrapper iObjectWrapper, zzko zzkoVar, String str, zzwf zzwfVar, int i) throws RemoteException;

    zzza createInAppPurchaseManager(IObjectWrapper iObjectWrapper) throws RemoteException;

    zzlt createInterstitialAdManager(IObjectWrapper iObjectWrapper, zzko zzkoVar, String str, zzwf zzwfVar, int i) throws RemoteException;

    zzqw createNativeAdViewDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException;

    zzrb createNativeAdViewHolderDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException;

    zzaex createRewardedVideoAd(IObjectWrapper iObjectWrapper, zzwf zzwfVar, int i) throws RemoteException;

    zzlt createSearchAdManager(IObjectWrapper iObjectWrapper, zzko zzkoVar, String str, int i) throws RemoteException;

    zzmh getMobileAdsSettingsManager(IObjectWrapper iObjectWrapper) throws RemoteException;

    zzmh getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper iObjectWrapper, int i) throws RemoteException;
}
