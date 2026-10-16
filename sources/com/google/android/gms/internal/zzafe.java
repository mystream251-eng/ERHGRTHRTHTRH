package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzafe extends zzev implements zzafc {
    zzafe(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zza() throws RemoteException {
        zzb(1, a_());
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zza(int i) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeInt(i);
        zzb(7, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zza(zzaeu zzaeuVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzaeuVar);
        zzb(5, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zzb() throws RemoteException {
        zzb(2, a_());
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zzc() throws RemoteException {
        zzb(3, a_());
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zzd() throws RemoteException {
        zzb(4, a_());
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zze() throws RemoteException {
        zzb(6, a_());
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zzf() throws RemoteException {
        zzb(8, a_());
    }
}
