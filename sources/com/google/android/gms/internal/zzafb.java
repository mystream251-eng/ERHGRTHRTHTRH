package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzafb extends zzev implements zzafa {
    zzafb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
    }

    @Override // com.google.android.gms.internal.zzafa
    public final IBinder zza(IObjectWrapper iObjectWrapper, zzwf zzwfVar, int i) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzwfVar);
        parcelA_.writeInt(12210000);
        Parcel parcelZza = zza(1, parcelA_);
        IBinder strongBinder = parcelZza.readStrongBinder();
        parcelZza.recycle();
        return strongBinder;
    }
}
