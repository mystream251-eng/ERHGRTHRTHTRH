package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzyr extends zzew implements zzyq {
    public zzyr() {
        attachInterface(this, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }

    public static zzyq zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        return iInterfaceQueryLocalInterface instanceof zzyq ? (zzyq) iInterfaceQueryLocalInterface : new zzys(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza((Bundle) zzex.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 2:
                zzf();
                parcel2.writeNoException();
                return true;
            case 3:
                zzg();
                parcel2.writeNoException();
                return true;
            case 4:
                zzh();
                parcel2.writeNoException();
                return true;
            case 5:
                zzi();
                parcel2.writeNoException();
                return true;
            case 6:
                Bundle bundle = (Bundle) zzex.zza(parcel, Bundle.CREATOR);
                zzb(bundle);
                parcel2.writeNoException();
                zzex.zzb(parcel2, bundle);
                return true;
            case 7:
                zzj();
                parcel2.writeNoException();
                return true;
            case 8:
                zzk();
                parcel2.writeNoException();
                return true;
            case 9:
                zzl();
                parcel2.writeNoException();
                return true;
            case 10:
                zzd();
                parcel2.writeNoException();
                return true;
            case 11:
                boolean zZze = zze();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZze);
                return true;
            case 12:
                zza(parcel.readInt(), parcel.readInt(), (Intent) zzex.zza(parcel, Intent.CREATOR));
                parcel2.writeNoException();
                return true;
            case 13:
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
