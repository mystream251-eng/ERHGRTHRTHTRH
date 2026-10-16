package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzlk extends zzev implements zzli {
    zzlk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdListener");
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zza() throws RemoteException {
        zzb(1, a_());
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zza(int i) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeInt(i);
        zzb(2, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzb() throws RemoteException {
        zzb(3, a_());
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzc() throws RemoteException {
        zzb(4, a_());
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzd() throws RemoteException {
        zzb(5, a_());
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zze() throws RemoteException {
        zzb(6, a_());
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzf() throws RemoteException {
        zzb(7, a_());
    }
}
