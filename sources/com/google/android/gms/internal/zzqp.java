package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzqp extends zzew implements zzqo {
    public zzqp() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
    }

    public static zzqo zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
        return iInterfaceQueryLocalInterface instanceof zzqo ? (zzqo) iInterfaceQueryLocalInterface : new zzqq(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 2) {
            String strZza = zza();
            parcel2.writeNoException();
            parcel2.writeString(strZza);
        } else {
            if (i != 3) {
                return false;
            }
            List<zzqs> listZzb = zzb();
            parcel2.writeNoException();
            parcel2.writeList(listZzb);
        }
        return true;
    }
}
