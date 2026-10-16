package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzsl extends zzew implements zzsk {
    public zzsl() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        String strZza;
        IInterface iInterfaceZzd;
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
                strZza = zzq();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 13:
                zzs();
                parcel2.writeNoException();
                return true;
            case 14:
                iInterfaceZzd = zzr();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 15:
                zza((Bundle) zzex.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 16:
                boolean zZzb = zzb((Bundle) zzex.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                zzex.zza(parcel2, zZzb);
                return true;
            case 17:
                zzc((Bundle) zzex.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 18:
                iInterfaceZzd = zzn();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            case 19:
                iInterfaceZzd = zzp();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzd);
                return true;
            default:
                return false;
        }
    }
}
