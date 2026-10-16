package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzcdi extends zzev implements zzcdh {
    zzcdi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gass.internal.IGassService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzcdh
    public final zzcdf zza(zzcdd zzcddVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzcddVar);
        Parcel parcelZza = zza(1, parcelA_);
        zzcdf zzcdfVar = (zzcdf) zzex.zza(parcelZza, zzcdf.CREATOR);
        parcelZza.recycle();
        return zzcdfVar;
    }
}
