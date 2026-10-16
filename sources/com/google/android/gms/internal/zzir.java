package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzir extends zzev implements zziq {
    zzir(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.cache.ICacheService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zziq
    public final zzik zza(zzin zzinVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzinVar);
        Parcel parcelZza = zza(1, parcelA_);
        zzik zzikVar = (zzik) zzex.zza(parcelZza, zzik.CREATOR);
        parcelZza.recycle();
        return zzikVar;
    }
}
