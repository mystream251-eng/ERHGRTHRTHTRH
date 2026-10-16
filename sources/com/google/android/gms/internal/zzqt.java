package com.google.android.gms.internal;

import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzqt extends zzew implements zzqs {
    public zzqt() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeAdImage");
    }

    public static zzqs zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        return iInterfaceQueryLocalInterface instanceof zzqs ? (zzqs) iInterfaceQueryLocalInterface : new zzqu(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            IObjectWrapper iObjectWrapperZza = zza();
            parcel2.writeNoException();
            zzex.zza(parcel2, iObjectWrapperZza);
        } else if (i == 2) {
            Uri uriZzb = zzb();
            parcel2.writeNoException();
            zzex.zzb(parcel2, uriZzb);
        } else {
            if (i != 3) {
                return false;
            }
            double dZzc = zzc();
            parcel2.writeNoException();
            parcel2.writeDouble(dZzc);
        }
        return true;
    }
}
