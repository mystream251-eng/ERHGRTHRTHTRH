package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzwn extends zzev implements zzwl {
    zzwn(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zza() throws RemoteException {
        zzb(1, a_());
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zza(int i) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeInt(i);
        zzb(3, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zza(zzro zzroVar, String str) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzroVar);
        parcelA_.writeString(str);
        zzb(10, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zza(zzwo zzwoVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzwoVar);
        zzb(7, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zza(String str, String str2) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzb(9, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zzb() throws RemoteException {
        zzb(2, a_());
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zzc() throws RemoteException {
        zzb(4, a_());
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zzd() throws RemoteException {
        zzb(5, a_());
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zze() throws RemoteException {
        zzb(6, a_());
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zzf() throws RemoteException {
        zzb(8, a_());
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zzg() throws RemoteException {
        zzb(11, a_());
    }
}
