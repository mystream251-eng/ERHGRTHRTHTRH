package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzlz extends zzew implements zzly {
    public zzlz() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IAppEventListener");
    }

    public static zzly zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
        return iInterfaceQueryLocalInterface instanceof zzly ? (zzly) iInterfaceQueryLocalInterface : new zzma(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zza(parcel.readString(), parcel.readString());
        parcel2.writeNoException();
        return true;
    }
}
