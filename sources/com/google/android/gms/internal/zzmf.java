package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzmf extends zzew implements zzme {
    public zzmf() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        long jZzb = zzb();
        parcel2.writeNoException();
        parcel2.writeLong(jZzb);
        return true;
    }
}
