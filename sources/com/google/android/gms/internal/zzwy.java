package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzwy extends zzew implements zzwx {
    public zzwy() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        String strZza;
        IInterface iInterfaceZzd;
        boolean zZzp;
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
                double dZzg = zzg();
                parcel2.writeNoException();
                parcel2.writeDouble(dZzg);
                return true;
            case 9:
                strZza = zzh();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 10:
                strZza = zzi();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 11:
                iInterfaceZzd = zzj();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 12:
                iInterfaceZzd = zzk();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 13:
                iInterfaceZzd = zzl();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 14:
                iInterfaceZzd = zzm();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 15:
                iInterfaceZzd = zzn();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 16:
                Bundle bundleZzo = zzo();
                parcel2.writeNoException();
                zzex.zzb(parcel2, bundleZzo);
                return true;
            case 17:
                zZzp = zzp();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZzp);
                return true;
            case 18:
                zZzp = zzq();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZzp);
                return true;
            case 19:
                zzr();
                parcel2.writeNoException();
                return true;
            case 20:
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 21:
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()), IObjectWrapper.zza.zza(parcel.readStrongBinder()), IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 22:
                zzb(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
