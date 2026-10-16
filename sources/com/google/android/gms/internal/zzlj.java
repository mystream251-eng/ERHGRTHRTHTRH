package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzlj extends zzew implements zzli {
    public zzlj() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IAdListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza();
                break;
            case 2:
                zza(parcel.readInt());
                break;
            case 3:
                zzb();
                break;
            case 4:
                zzc();
                break;
            case 5:
                zzd();
                break;
            case 6:
                zze();
                break;
            case 7:
                zzf();
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
