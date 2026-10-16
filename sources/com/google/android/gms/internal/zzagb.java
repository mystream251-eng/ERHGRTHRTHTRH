package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzagb extends zzev implements zzafz {
    zzagb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(1, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zza(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        parcelA_.writeInt(i);
        zzb(2, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzafz
    public final void zza(IObjectWrapper iObjectWrapper, zzagd zzagdVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzagdVar);
        zzb(7, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzb(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(3, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzb(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        parcelA_.writeInt(i);
        zzb(9, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzc(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(4, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(5, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zze(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(6, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzf(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(8, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzg(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(10, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzafz
    public final void zzh(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(11, parcelA_);
    }
}
