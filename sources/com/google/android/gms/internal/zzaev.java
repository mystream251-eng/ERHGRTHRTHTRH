package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzaev extends zzew implements zzaeu {
    public zzaev() {
        attachInterface(this, "com.google.android.gms.ads.internal.reward.client.IRewardItem");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            String strZza = zza();
            parcel2.writeNoException();
            parcel2.writeString(strZza);
        } else {
            if (i != 2) {
                return false;
            }
            int iZzb = zzb();
            parcel2.writeNoException();
            parcel2.writeInt(iZzb);
        }
        return true;
    }
}
