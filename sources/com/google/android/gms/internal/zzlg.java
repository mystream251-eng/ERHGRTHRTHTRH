package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzlg extends zzew implements zzlf {
    public zzlg() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IAdClickListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zza();
        parcel2.writeNoException();
        return true;
    }
}
