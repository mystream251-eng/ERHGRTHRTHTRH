package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzwv extends zzew implements zzwu {
    public zzwv() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        String strZza;
        IInterface iInterfaceZzd;
        boolean zZzh;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 2:
                strZza = zza();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 3:
                List listZzb = zzb();
                parcel2.writeNoException();
                parcel2.writeList(listZzb);
                return true;
            case 4:
                strZza = zzc();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 5:
                iInterfaceZzd = zzd();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 6:
                strZza = zze();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 7:
                strZza = zzf();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 8:
                zzg();
                parcel2.writeNoException();
                return true;
            case 9:
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 10:
                zzb(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 11:
                zZzh = zzh();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZzh);
                return true;
            case 12:
                zZzh = zzi();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZzh);
                return true;
            case 13:
                Bundle bundleZzj = zzj();
                parcel2.writeNoException();
                zzex.zzb(parcel2, bundleZzj);
                return true;
            case 14:
                zzc(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 15:
                iInterfaceZzd = zzk();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 16:
                iInterfaceZzd = zzl();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 17:
            case 18:
            default:
                return false;
            case 19:
                iInterfaceZzd = zzm();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 20:
                iInterfaceZzd = zzn();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 21:
                iInterfaceZzd = zzo();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 22:
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()), IObjectWrapper.zza.zza(parcel.readStrongBinder()), IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
        }
    }
}
