package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzmj extends zzev implements zzmh {
    zzmj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
    }

    @Override // com.google.android.gms.internal.zzmh
    public final void zza() throws RemoteException {
        zzb(1, a_());
    }

    @Override // com.google.android.gms.internal.zzmh
    public final void zza(float f) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeFloat(f);
        zzb(2, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzmh
    public final void zza(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        parcelA_.writeString(str);
        zzb(5, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzmh
    public final void zza(String str) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        zzb(3, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzmh
    public final void zza(String str, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(6, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzmh
    public final void zza(boolean z) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, z);
        zzb(4, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzmh
    public final float zzb() throws RemoteException {
        Parcel parcelZza = zza(7, a_());
        float f = parcelZza.readFloat();
        parcelZza.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.zzmh
    public final boolean zzc() throws RemoteException {
        Parcel parcelZza = zza(8, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }
}
