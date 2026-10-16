package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzpa extends zzev implements zzoy {
    zzpa(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
    }

    @Override // com.google.android.gms.internal.zzoy
    public final String zza() throws RemoteException {
        Parcel parcelZza = zza(1, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzoy
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(3, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzoy
    public final String zzb() throws RemoteException {
        Parcel parcelZza = zza(2, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzoy
    public final void zzc() throws RemoteException {
        zzb(4, a_());
    }

    @Override // com.google.android.gms.internal.zzoy
    public final void zzd() throws RemoteException {
        zzb(5, a_());
    }
}
