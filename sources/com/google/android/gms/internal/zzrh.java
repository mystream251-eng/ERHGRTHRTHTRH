package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzrh extends zzew implements zzrg {
    public zzrh() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IInterface iInterfaceZzj;
        String strZza;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 2:
                iInterfaceZzj = zzj();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzj);
                return true;
            case 3:
                strZza = zza();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 4:
                List listZzb = zzb();
                parcel2.writeNoException();
                parcel2.writeList(listZzb);
                return true;
            case 5:
                strZza = zzc();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 6:
                iInterfaceZzj = zzd();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzj);
                return true;
            case 7:
                strZza = zze();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 8:
                double dZzf = zzf();
                parcel2.writeNoException();
                parcel2.writeDouble(dZzf);
                return true;
            case 9:
                strZza = zzg();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 10:
                strZza = zzh();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 11:
                Bundle bundleZzn = zzn();
                parcel2.writeNoException();
                zzex.zzb(parcel2, bundleZzn);
                return true;
            case 12:
                zzs();
                parcel2.writeNoException();
                return true;
            case 13:
                iInterfaceZzj = zzi();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzj);
                return true;
            case 14:
                zza((Bundle) zzex.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 15:
                boolean zZzb = zzb((Bundle) zzex.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                zzex.zza(parcel2, zZzb);
                return true;
            case 16:
                zzc((Bundle) zzex.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 17:
                iInterfaceZzj = zzr();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzj);
                return true;
            case 18:
                iInterfaceZzj = zzp();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzj);
                return true;
            case 19:
                strZza = zzq();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            default:
                return false;
        }
    }
}
