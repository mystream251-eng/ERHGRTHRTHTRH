package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzwg extends zzew implements zzwf {
    public zzwg() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }

    public static zzwf zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        return iInterfaceQueryLocalInterface instanceof zzwf ? (zzwf) iInterfaceQueryLocalInterface : new zzwh(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zzwi zzwiVarZza = zza(parcel.readString());
            parcel2.writeNoException();
            zzex.zza(parcel2, zzwiVarZza);
        } else {
            if (i != 2) {
                return false;
            }
            boolean zZzb = zzb(parcel.readString());
            parcel2.writeNoException();
            zzex.zza(parcel2, zZzb);
        }
        return true;
    }
}
