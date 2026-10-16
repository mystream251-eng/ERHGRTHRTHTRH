package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzoz extends zzew implements zzoy {
    public zzoz() {
        attachInterface(this, "com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        String strZza;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                } else if (i == 4) {
                    zzc();
                } else {
                    if (i != 5) {
                        return false;
                    }
                    zzd();
                }
                parcel2.writeNoException();
            } else {
                strZza = zzb();
            }
            return true;
        }
        strZza = zza();
        parcel2.writeNoException();
        parcel2.writeString(strZza);
        return true;
    }
}
