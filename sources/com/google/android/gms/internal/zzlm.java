package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzlm extends zzew implements zzll {
    public zzlm() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IAdLoader");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        String strZza;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    boolean zZzc = zzc();
                    parcel2.writeNoException();
                    zzex.zza(parcel2, zZzc);
                } else if (i == 4) {
                    strZza = zzb();
                } else {
                    if (i != 5) {
                        return false;
                    }
                    zza((zzkk) zzex.zza(parcel, zzkk.CREATOR), parcel.readInt());
                }
                return true;
            }
            strZza = zza();
            parcel2.writeNoException();
            parcel2.writeString(strZza);
            return true;
        }
        zza((zzkk) zzex.zza(parcel, zzkk.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
