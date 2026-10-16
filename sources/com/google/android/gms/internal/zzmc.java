package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzmc extends zzew implements zzmb {
    public zzmc() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IClientApi");
    }

    public static zzmb asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
        return iInterfaceQueryLocalInterface instanceof zzmb ? (zzmb) iInterfaceQueryLocalInterface : new zzmd(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IInterface iInterfaceCreateBannerAdManager;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                iInterfaceCreateBannerAdManager = createBannerAdManager(IObjectWrapper.zza.zza(parcel.readStrongBinder()), (zzko) zzex.zza(parcel, zzko.CREATOR), parcel.readString(), zzwg.zza(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 2:
                iInterfaceCreateBannerAdManager = createInterstitialAdManager(IObjectWrapper.zza.zza(parcel.readStrongBinder()), (zzko) zzex.zza(parcel, zzko.CREATOR), parcel.readString(), zzwg.zza(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 3:
                iInterfaceCreateBannerAdManager = createAdLoaderBuilder(IObjectWrapper.zza.zza(parcel.readStrongBinder()), parcel.readString(), zzwg.zza(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 4:
                iInterfaceCreateBannerAdManager = getMobileAdsSettingsManager(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            case 5:
                iInterfaceCreateBannerAdManager = createNativeAdViewDelegate(IObjectWrapper.zza.zza(parcel.readStrongBinder()), IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            case 6:
                iInterfaceCreateBannerAdManager = createRewardedVideoAd(IObjectWrapper.zza.zza(parcel.readStrongBinder()), zzwg.zza(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 7:
                iInterfaceCreateBannerAdManager = createInAppPurchaseManager(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            case 8:
                iInterfaceCreateBannerAdManager = createAdOverlay(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            case 9:
                iInterfaceCreateBannerAdManager = getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper.zza.zza(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 10:
                iInterfaceCreateBannerAdManager = createSearchAdManager(IObjectWrapper.zza.zza(parcel.readStrongBinder()), (zzko) zzex.zza(parcel, zzko.CREATOR), parcel.readString(), parcel.readInt());
                break;
            case 11:
                iInterfaceCreateBannerAdManager = createNativeAdViewHolderDelegate(IObjectWrapper.zza.zza(parcel.readStrongBinder()), IObjectWrapper.zza.zza(parcel.readStrongBinder()), IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        zzex.zza(parcel2, iInterfaceCreateBannerAdManager);
        return true;
    }
}
