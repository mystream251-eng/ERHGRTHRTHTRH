package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzqy extends zzev implements zzqw {
    zzqy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
    }

    @Override // com.google.android.gms.internal.zzqw
    public final IObjectWrapper zza(String str) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        Parcel parcelZza = zza(2, parcelA_);
        IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZza;
    }

    @Override // com.google.android.gms.internal.zzqw
    public final void zza() throws RemoteException {
        zzb(4, a_());
    }

    @Override // com.google.android.gms.internal.zzqw
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(3, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzqw
    public final void zza(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        parcelA_.writeInt(i);
        zzb(5, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzqw
    public final void zza(String str, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(1, parcelA_);
    }
}
