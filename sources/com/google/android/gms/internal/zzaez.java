package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzaez extends zzev implements zzaex {
    zzaez(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza() throws RemoteException {
        zzb(2, a_());
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(9, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(zzafc zzafcVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzafcVar);
        zzb(3, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzaex
    public final void zza(zzafi zzafiVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzafiVar);
        zzb(1, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(String str) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        zzb(13, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(boolean z) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, z);
        zzb(34, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zzb(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(10, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzaex
    public final boolean zzb() throws RemoteException {
        Parcel parcelZza = zza(5, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zzc() throws RemoteException {
        zzb(6, a_());
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zzc(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(11, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zzd() throws RemoteException {
        zzb(7, a_());
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zze() throws RemoteException {
        zzb(8, a_());
    }

    @Override // com.google.android.gms.internal.zzaex
    public final String zzf() throws RemoteException {
        Parcel parcelZza = zza(12, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }
}
