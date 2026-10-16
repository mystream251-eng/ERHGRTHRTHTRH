package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzrz extends zzew implements zzry {
    public zzrz() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }

    public static zzry zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
        return iInterfaceQueryLocalInterface instanceof zzry ? (zzry) iInterfaceQueryLocalInterface : new zzsa(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzro zzrqVar;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        IBinder strongBinder = parcel.readStrongBinder();
        if (strongBinder == null) {
            zzrqVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
            zzrqVar = iInterfaceQueryLocalInterface instanceof zzro ? (zzro) iInterfaceQueryLocalInterface : new zzrq(strongBinder);
        }
        zza(zzrqVar, parcel.readString());
        parcel2.writeNoException();
        return true;
    }
}
