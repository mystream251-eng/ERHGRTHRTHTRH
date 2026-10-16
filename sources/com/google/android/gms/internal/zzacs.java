package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzacs extends zzev implements zzacq {
    zzacs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.request.IAdResponseListener");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzacq
    public final void zza(zzacj zzacjVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzacjVar);
        zzb(1, parcelA_);
    }
}
