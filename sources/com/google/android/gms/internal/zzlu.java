package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzlu extends zzew implements zzlt {
    public zzlu() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IAdManager");
    }

    public static zzlt zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        return iInterfaceQueryLocalInterface instanceof zzlt ? (zzlt) iInterfaceQueryLocalInterface : new zzlv(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IInterface iInterfaceZzc;
        boolean zZze;
        String strZzal;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        zzev zzlkVar = null;
        switch (i) {
            case 1:
                iInterfaceZzc = zzc();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzc);
                return true;
            case 2:
                zzb();
                parcel2.writeNoException();
                return true;
            case 3:
                zZze = zze();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZze);
                return true;
            case 4:
                zZze = zzb((zzkk) zzex.zza(parcel, zzkk.CREATOR));
                parcel2.writeNoException();
                zzex.zza(parcel2, zZze);
                return true;
            case 5:
                zzh();
                parcel2.writeNoException();
                return true;
            case 6:
                zzi();
                parcel2.writeNoException();
                return true;
            case 7:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    zzev zzevVarQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    zzlkVar = zzevVarQueryLocalInterface instanceof zzli ? (zzli) zzevVarQueryLocalInterface : new zzlk(strongBinder);
                }
                zza((zzli) zzlkVar);
                parcel2.writeNoException();
                return true;
            case 8:
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    zzev zzevVarQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
                    zzlkVar = zzevVarQueryLocalInterface2 instanceof zzly ? (zzly) zzevVarQueryLocalInterface2 : new zzma(strongBinder2);
                }
                zza((zzly) zzlkVar);
                parcel2.writeNoException();
                return true;
            case 9:
                zzan();
                parcel2.writeNoException();
                return true;
            case 10:
                zzj();
                parcel2.writeNoException();
                return true;
            case 11:
                zzg();
                parcel2.writeNoException();
                return true;
            case 12:
                zzbgl zzbglVarZzd = zzd();
                parcel2.writeNoException();
                zzex.zzb(parcel2, zzbglVarZzd);
                return true;
            case 13:
                zza((zzko) zzex.zza(parcel, zzko.CREATOR));
                parcel2.writeNoException();
                return true;
            case 14:
                zza(zzyy.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 15:
                zza(zzze.zza(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 16:
            case 17:
            case 27:
            case 28:
            default:
                return false;
            case 18:
                strZzal = zzal();
                parcel2.writeNoException();
                parcel2.writeString(strZzal);
                return true;
            case 19:
                zza(zzpc.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 20:
                IBinder strongBinder3 = parcel.readStrongBinder();
                if (strongBinder3 != null) {
                    zzev zzevVarQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdClickListener");
                    zzlkVar = zzevVarQueryLocalInterface3 instanceof zzlf ? (zzlf) zzevVarQueryLocalInterface3 : new zzlh(strongBinder3);
                }
                zza((zzlf) zzlkVar);
                parcel2.writeNoException();
                return true;
            case 21:
                IBinder strongBinder4 = parcel.readStrongBinder();
                if (strongBinder4 != null) {
                    zzev zzevVarQueryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    zzlkVar = zzevVarQueryLocalInterface4 instanceof zzme ? (zzme) zzevVarQueryLocalInterface4 : new zzmg(strongBinder4);
                }
                zza((zzme) zzlkVar);
                parcel2.writeNoException();
                return true;
            case 22:
                zza(zzex.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 23:
                zZze = zzk();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZze);
                return true;
            case 24:
                zza(zzafd.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 25:
                zza(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 26:
                iInterfaceZzc = zzl();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzc);
                return true;
            case 29:
                zza((zzns) zzex.zza(parcel, zzns.CREATOR));
                parcel2.writeNoException();
                return true;
            case 30:
                zza((zzms) zzex.zza(parcel, zzms.CREATOR));
                parcel2.writeNoException();
                return true;
            case 31:
                strZzal = zzv();
                parcel2.writeNoException();
                parcel2.writeString(strZzal);
                return true;
            case 32:
                iInterfaceZzc = zzw();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzc);
                return true;
            case 33:
                iInterfaceZzc = zzx();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZzc);
                return true;
            case 34:
                zzb(zzex.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 35:
                strZzal = zzam();
                parcel2.writeNoException();
                parcel2.writeString(strZzal);
                return true;
        }
    }
}
