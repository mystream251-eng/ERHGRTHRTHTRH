package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzrp extends zzew implements zzro {
    public zzrp() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }

    public static zzro zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        return iInterfaceQueryLocalInterface instanceof zzro ? (zzro) iInterfaceQueryLocalInterface : new zzrq(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        String strZza;
        IInterface iInterfaceZzb;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                strZza = zza(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 2:
                iInterfaceZzb = zzb(parcel.readString());
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzb);
                return true;
            case 3:
                List<String> listZza = zza();
                parcel2.writeNoException();
                parcel2.writeStringList(listZza);
                return true;
            case 4:
                strZza = zzl();
                parcel2.writeNoException();
                parcel2.writeString(strZza);
                return true;
            case 5:
                zzc(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                zzd();
                parcel2.writeNoException();
                return true;
            case 7:
                iInterfaceZzb = zzc();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzb);
                return true;
            case 8:
                zzf();
                parcel2.writeNoException();
                return true;
            case 9:
                iInterfaceZzb = zze();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzb);
                return true;
            case 10:
                boolean zZza = zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzex.zza(parcel2, zZza);
                return true;
            case 11:
                iInterfaceZzb = zzb();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzb);
                return true;
            default:
                return false;
        }
    }
}
