package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzacr extends zzew implements zzacq {
    public zzacr() {
        attachInterface(this, "com.google.android.gms.ads.internal.request.IAdResponseListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zza((zzacj) zzex.zza(parcel, zzacj.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
