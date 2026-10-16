package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzlp extends zzew implements zzlo {
    public zzlp() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        zzev zzlkVar = null;
        switch (i) {
            case 1:
                zzll zzllVarZza = zza();
                parcel2.writeNoException();
                zzex.zza(parcel2, zzllVarZza);
                return true;
            case 2:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    zzev zzevVarQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    zzlkVar = zzevVarQueryLocalInterface instanceof zzli ? (zzli) zzevVarQueryLocalInterface : new zzlk(strongBinder);
                }
                zza((zzli) zzlkVar);
                parcel2.writeNoException();
                return true;
            case 3:
                zza(zzrt.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 4:
                zza(zzrw.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 5:
                zza(parcel.readString(), zzsc.zza(parcel.readStrongBinder()), zzrz.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 6:
                zza((zzqh) zzex.zza(parcel, zzqh.CREATOR));
                parcel2.writeNoException();
                return true;
            case 7:
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    zzev zzevVarQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    zzlkVar = zzevVarQueryLocalInterface2 instanceof zzme ? (zzme) zzevVarQueryLocalInterface2 : new zzmg(strongBinder2);
                }
                zza((zzme) zzlkVar);
                parcel2.writeNoException();
                return true;
            case 8:
                zza(zzsf.zza(parcel.readStrongBinder()), (zzko) zzex.zza(parcel, zzko.CREATOR));
                parcel2.writeNoException();
                return true;
            case 9:
                zza((PublisherAdViewOptions) zzex.zza(parcel, PublisherAdViewOptions.CREATOR));
                parcel2.writeNoException();
                return true;
            case 10:
                zza(zzsi.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
