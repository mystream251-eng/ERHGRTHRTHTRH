package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzwh extends zzev implements zzwf {
    zzwh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }

    @Override // com.google.android.gms.internal.zzwf
    public final zzwi zza(String str) throws RemoteException {
        zzwi zzwkVar;
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        Parcel parcelZza = zza(1, parcelA_);
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzwkVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            zzwkVar = iInterfaceQueryLocalInterface instanceof zzwi ? (zzwi) iInterfaceQueryLocalInterface : new zzwk(strongBinder);
        }
        parcelZza.recycle();
        return zzwkVar;
    }

    @Override // com.google.android.gms.internal.zzwf
    public final boolean zzb(String str) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        Parcel parcelZza = zza(2, parcelA_);
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }
}
