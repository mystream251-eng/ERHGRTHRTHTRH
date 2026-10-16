package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzmi extends zzew implements zzmh {
    public zzmi() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza();
                parcel2.writeNoException();
                return true;
            case 2:
                zza(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 3:
                zza(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 4:
                zza(zzex.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 5:
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                zza(parcel.readString(), IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 7:
                float fZzb = zzb();
                parcel2.writeNoException();
                parcel2.writeFloat(fZzb);
                return true;
            case 8:
                boolean zZzc = zzc();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZzc);
                return true;
            default:
                return false;
        }
    }
}
