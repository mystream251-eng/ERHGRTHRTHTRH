package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzaga extends zzew implements zzafz {
    public zzaga() {
        attachInterface(this, "com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
    }

    public static zzafz zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
        return iInterfaceQueryLocalInterface instanceof zzafz ? (zzafz) iInterfaceQueryLocalInterface : new zzagb(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            case 2:
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 3:
                zzb(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            case 4:
                zzc(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            case 5:
                zzd(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            case 6:
                zze(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            case 7:
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()), (zzagd) zzex.zza(parcel, zzagd.CREATOR));
                break;
            case 8:
                zzf(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            case 9:
                zzb(IObjectWrapper.zza.zza(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 10:
                zzg(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            case 11:
                zzh(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
