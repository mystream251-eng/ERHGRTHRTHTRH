package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzqx extends zzew implements zzqw {
    public zzqx() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
    }

    public static zzqw zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
        return iInterfaceQueryLocalInterface instanceof zzqw ? (zzqw) iInterfaceQueryLocalInterface : new zzqy(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            if (i == 2) {
                IObjectWrapper iObjectWrapperZza = zza(parcel.readString());
                parcel2.writeNoException();
                zzex.zza(parcel2, iObjectWrapperZza);
            } else if (i == 3) {
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
            } else if (i == 4) {
                zza();
            } else {
                if (i != 5) {
                    return false;
                }
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()), parcel.readInt());
            }
            return true;
        }
        zza(parcel.readString(), IObjectWrapper.zza.zza(parcel.readStrongBinder()));
        parcel2.writeNoException();
        return true;
    }
}
