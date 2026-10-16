package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzwm extends zzew implements zzwl {
    public zzwm() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzwo zzwqVar;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza();
                break;
            case 2:
                zzb();
                break;
            case 3:
                zza(parcel.readInt());
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
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder == null) {
                    zzwqVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
                    zzwqVar = iInterfaceQueryLocalInterface instanceof zzwo ? (zzwo) iInterfaceQueryLocalInterface : new zzwq(strongBinder);
                }
                zza(zzwqVar);
                break;
            case 8:
                zzf();
                break;
            case 9:
                zza(parcel.readString(), parcel.readString());
                break;
            case 10:
                zza(zzrp.zza(parcel.readStrongBinder()), parcel.readString());
                break;
            case 11:
                zzg();
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
