package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzafd extends zzew implements zzafc {
    public zzafd() {
        attachInterface(this, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }

    public static zzafc zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        return iInterfaceQueryLocalInterface instanceof zzafc ? (zzafc) iInterfaceQueryLocalInterface : new zzafe(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzaeu zzaewVar;
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
                zzc();
                break;
            case 4:
                zzd();
                break;
            case 5:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder == null) {
                    zzaewVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardItem");
                    zzaewVar = iInterfaceQueryLocalInterface instanceof zzaeu ? (zzaeu) iInterfaceQueryLocalInterface : new zzaew(strongBinder);
                }
                zza(zzaewVar);
                break;
            case 6:
                zze();
                break;
            case 7:
                zza(parcel.readInt());
                break;
            case 8:
                zzf();
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
