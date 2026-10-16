package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzmn extends zzew implements zzmm {
    public zzmn() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IVideoController");
    }

    public static zzmm zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
        return iInterfaceQueryLocalInterface instanceof zzmm ? (zzmm) iInterfaceQueryLocalInterface : new zzmo(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        boolean zZzc;
        float fZze;
        zzmp zzmrVar;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza();
                parcel2.writeNoException();
                return true;
            case 2:
                zzb();
                parcel2.writeNoException();
                return true;
            case 3:
                zza(zzex.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 4:
                zZzc = zzc();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZzc);
                return true;
            case 5:
                int iZzd = zzd();
                parcel2.writeNoException();
                parcel2.writeInt(iZzd);
                return true;
            case 6:
                fZze = zze();
                parcel2.writeNoException();
                parcel2.writeFloat(fZze);
                return true;
            case 7:
                fZze = zzf();
                parcel2.writeNoException();
                parcel2.writeFloat(fZze);
                return true;
            case 8:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder == null) {
                    zzmrVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
                    zzmrVar = iInterfaceQueryLocalInterface instanceof zzmp ? (zzmp) iInterfaceQueryLocalInterface : new zzmr(strongBinder);
                }
                zza(zzmrVar);
                parcel2.writeNoException();
                return true;
            case 9:
                fZze = zzg();
                parcel2.writeNoException();
                parcel2.writeFloat(fZze);
                return true;
            case 10:
                zZzc = zzh();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZzc);
                return true;
            case 11:
                zzmp zzmpVarZzi = zzi();
                parcel2.writeNoException();
                zzex.zza(parcel2, zzmpVarZzi);
                return true;
            case 12:
                zZzc = zzj();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZzc);
                return true;
            default:
                return false;
        }
    }
}
