package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzrc extends zzew implements zzrb {
    public zzrc() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegate");
    }

    public static zzrb zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegate");
        return iInterfaceQueryLocalInterface instanceof zzrb ? (zzrb) iInterfaceQueryLocalInterface : new zzrd(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
        } else {
            if (i != 2) {
                return false;
            }
            zza();
        }
        parcel2.writeNoException();
        return true;
    }
}
