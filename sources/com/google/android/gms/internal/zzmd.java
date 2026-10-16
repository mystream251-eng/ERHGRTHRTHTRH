package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzmd extends zzev implements zzmb {
    zzmd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IClientApi");
    }

    @Override // com.google.android.gms.internal.zzmb
    public final zzlo createAdLoaderBuilder(IObjectWrapper iObjectWrapper, String str, zzwf zzwfVar, int i) throws RemoteException {
        zzlo zzlqVar;
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        parcelA_.writeString(str);
        zzex.zza(parcelA_, zzwfVar);
        parcelA_.writeInt(i);
        Parcel parcelZza = zza(3, parcelA_);
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzlqVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            zzlqVar = iInterfaceQueryLocalInterface instanceof zzlo ? (zzlo) iInterfaceQueryLocalInterface : new zzlq(strongBinder);
        }
        parcelZza.recycle();
        return zzlqVar;
    }

    @Override // com.google.android.gms.internal.zzmb
    public final zzyq createAdOverlay(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        Parcel parcelZza = zza(8, parcelA_);
        zzyq zzyqVarZza = zzyr.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzyqVarZza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzmb
    public final zzlt createBannerAdManager(IObjectWrapper iObjectWrapper, zzko zzkoVar, String str, zzwf zzwfVar, int i) throws RemoteException {
        zzlt zzlvVar;
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzkoVar);
        parcelA_.writeString(str);
        zzex.zza(parcelA_, zzwfVar);
        parcelA_.writeInt(i);
        Parcel parcelZza = zza(1, parcelA_);
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzlvVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzlvVar = iInterfaceQueryLocalInterface instanceof zzlt ? (zzlt) iInterfaceQueryLocalInterface : new zzlv(strongBinder);
        }
        parcelZza.recycle();
        return zzlvVar;
    }

    @Override // com.google.android.gms.internal.zzmb
    public final zzza createInAppPurchaseManager(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        Parcel parcelZza = zza(7, parcelA_);
        zzza zzzaVarZza = zzzb.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzzaVarZza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzmb
    public final zzlt createInterstitialAdManager(IObjectWrapper iObjectWrapper, zzko zzkoVar, String str, zzwf zzwfVar, int i) throws RemoteException {
        zzlt zzlvVar;
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzkoVar);
        parcelA_.writeString(str);
        zzex.zza(parcelA_, zzwfVar);
        parcelA_.writeInt(i);
        Parcel parcelZza = zza(2, parcelA_);
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzlvVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzlvVar = iInterfaceQueryLocalInterface instanceof zzlt ? (zzlt) iInterfaceQueryLocalInterface : new zzlv(strongBinder);
        }
        parcelZza.recycle();
        return zzlvVar;
    }

    @Override // com.google.android.gms.internal.zzmb
    public final zzqw createNativeAdViewDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, iObjectWrapper2);
        Parcel parcelZza = zza(5, parcelA_);
        zzqw zzqwVarZza = zzqx.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzqwVarZza;
    }

    @Override // com.google.android.gms.internal.zzmb
    public final zzrb createNativeAdViewHolderDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, iObjectWrapper2);
        zzex.zza(parcelA_, iObjectWrapper3);
        Parcel parcelZza = zza(11, parcelA_);
        zzrb zzrbVarZza = zzrc.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzrbVarZza;
    }

    @Override // com.google.android.gms.internal.zzmb
    public final zzaex createRewardedVideoAd(IObjectWrapper iObjectWrapper, zzwf zzwfVar, int i) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzwfVar);
        parcelA_.writeInt(i);
        Parcel parcelZza = zza(6, parcelA_);
        zzaex zzaexVarZza = zzaey.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return zzaexVarZza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzmb
    public final zzlt createSearchAdManager(IObjectWrapper iObjectWrapper, zzko zzkoVar, String str, int i) throws RemoteException {
        zzlt zzlvVar;
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzkoVar);
        parcelA_.writeString(str);
        parcelA_.writeInt(i);
        Parcel parcelZza = zza(10, parcelA_);
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzlvVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzlvVar = iInterfaceQueryLocalInterface instanceof zzlt ? (zzlt) iInterfaceQueryLocalInterface : new zzlv(strongBinder);
        }
        parcelZza.recycle();
        return zzlvVar;
    }

    @Override // com.google.android.gms.internal.zzmb
    public final zzmh getMobileAdsSettingsManager(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzmh zzmjVar;
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        Parcel parcelZza = zza(4, parcelA_);
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzmjVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            zzmjVar = iInterfaceQueryLocalInterface instanceof zzmh ? (zzmh) iInterfaceQueryLocalInterface : new zzmj(strongBinder);
        }
        parcelZza.recycle();
        return zzmjVar;
    }

    @Override // com.google.android.gms.internal.zzmb
    public final zzmh getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        zzmh zzmjVar;
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        parcelA_.writeInt(i);
        Parcel parcelZza = zza(9, parcelA_);
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzmjVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            zzmjVar = iInterfaceQueryLocalInterface instanceof zzmh ? (zzmh) iInterfaceQueryLocalInterface : new zzmj(strongBinder);
        }
        parcelZza.recycle();
        return zzmjVar;
    }
}
