package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzrd extends zzev implements zzrb {
    zzrd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegate");
    }

    @Override // com.google.android.gms.internal.zzrb
    public final void zza() throws RemoteException {
        zzb(2, a_());
    }

    @Override // com.google.android.gms.internal.zzrb
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzb(1, parcelA_);
    }
}
