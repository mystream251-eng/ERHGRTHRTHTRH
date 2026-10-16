package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzys extends zzev implements zzyq {
    zzys(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zza(int i, int i2, Intent intent) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeInt(i);
        parcelA_.writeInt(i2);
        zzex.zza(parcelA_, intent);
        zzb(12, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zza(Bundle bundle) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, bundle);
        zzb(1, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(13, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzb(Bundle bundle) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, bundle);
        Parcel parcelZza = zza(6, parcelA_);
        if (parcelZza.readInt() != 0) {
            bundle.readFromParcel(parcelZza);
        }
        parcelZza.recycle();
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzd() throws RemoteException {
        zzb(10, a_());
    }

    @Override // com.google.android.gms.internal.zzyq
    public final boolean zze() throws RemoteException {
        Parcel parcelZza = zza(11, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzf() throws RemoteException {
        zzb(2, a_());
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzg() throws RemoteException {
        zzb(3, a_());
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzh() throws RemoteException {
        zzb(4, a_());
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzi() throws RemoteException {
        zzb(5, a_());
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzj() throws RemoteException {
        zzb(7, a_());
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzk() throws RemoteException {
        zzb(8, a_());
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzl() throws RemoteException {
        zzb(9, a_());
    }
}
