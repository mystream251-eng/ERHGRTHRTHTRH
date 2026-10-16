package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zzlx extends zzev implements zzlw {
    zzlx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdManagerCreator");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzlw
    public final IBinder zza(IObjectWrapper iObjectWrapper, zzko zzkoVar, String str, zzwf zzwfVar, int i, int i2) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, iObjectWrapper);
        zzex.zza(parcelA_, zzkoVar);
        parcelA_.writeString(str);
        zzex.zza(parcelA_, zzwfVar);
        parcelA_.writeInt(12210000);
        parcelA_.writeInt(i2);
        Parcel parcelZza = zza(2, parcelA_);
        IBinder strongBinder = parcelZza.readStrongBinder();
        parcelZza.recycle();
        return strongBinder;
    }
}
