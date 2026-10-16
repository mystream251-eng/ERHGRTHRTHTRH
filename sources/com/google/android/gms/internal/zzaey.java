package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzaey extends zzew implements zzaex {
    public zzaey() {
        attachInterface(this, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    public static zzaex zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        return iInterfaceQueryLocalInterface instanceof zzaex ? (zzaex) iInterfaceQueryLocalInterface : new zzaez(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzafc zzafeVar;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zza((zzafi) zzex.zza(parcel, zzafi.CREATOR));
        } else if (i == 2) {
            zza();
        } else if (i == 3) {
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder == null) {
                zzafeVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                zzafeVar = iInterfaceQueryLocalInterface instanceof zzafc ? (zzafc) iInterfaceQueryLocalInterface : new zzafe(strongBinder);
            }
            zza(zzafeVar);
        } else if (i != 34) {
            switch (i) {
                case 5:
                    boolean zZzb = zzb();
                    parcel2.writeNoException();
                    zzex.zza(parcel2, zZzb);
                    return true;
                case 6:
                    zzc();
                    break;
                case 7:
                    zzd();
                    break;
                case 8:
                    zze();
                    break;
                case 9:
                    zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                    break;
                case 10:
                    zzb(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                    break;
                case 11:
                    zzc(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                    break;
                case 12:
                    String strZzf = zzf();
                    parcel2.writeNoException();
                    parcel2.writeString(strZzf);
                    return true;
                case 13:
                    zza(parcel.readString());
                    break;
                default:
                    return false;
            }
        } else {
            zza(zzex.zza(parcel));
        }
        parcel2.writeNoException();
        return true;
    }
}
