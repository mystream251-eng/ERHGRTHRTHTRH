package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfo extends zzev implements zzfm {
    zzfo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.clearcut.IClearcut");
    }

    @Override // com.google.android.gms.internal.zzfm
    public final void zza() throws RemoteException {
        zzb(3, a_());
    }

    @Override // com.google.android.gms.internal.zzfm
    public final void zza(int i) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeInt(i);
        zzb(6, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzfm
    public final void zza(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        parcelA_.writeString(str);
        zzb(2, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzfm
    public final void zza(IObjectWrapper iObjectWrapper, String str, String str2) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        parcelA_.writeString(str);
        parcelA_.writeString(null);
        zzb(8, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzfm
    public final void zza(byte[] bArr) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeByteArray(bArr);
        zzb(5, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzfm
    public final void zza(int[] iArr) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeIntArray(null);
        zzb(4, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzfm
    public final void zzb(int i) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeInt(i);
        zzb(7, parcelA_);
    }
}
