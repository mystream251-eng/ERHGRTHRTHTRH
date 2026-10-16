package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzwj extends zzew implements zzwi {
    public zzwj() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IInterface iInterfaceZza;
        boolean zZzg;
        Bundle bundleZzj;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        zzwl zzwnVar = null;
        switch (i) {
            case 1:
                IObjectWrapper iObjectWrapperZza = IObjectWrapper.zza.zza(parcel.readStrongBinder());
                zzko zzkoVar = (zzko) zzex.zza(parcel, zzko.CREATOR);
                zzkk zzkkVar = (zzkk) zzex.zza(parcel, zzkk.CREATOR);
                String string = parcel.readString();
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzwnVar = iInterfaceQueryLocalInterface instanceof zzwl ? (zzwl) iInterfaceQueryLocalInterface : new zzwn(strongBinder);
                }
                zza(iObjectWrapperZza, zzkoVar, zzkkVar, string, zzwnVar);
                parcel2.writeNoException();
                return true;
            case 2:
                iInterfaceZza = zza();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZza);
                return true;
            case 3:
                IObjectWrapper iObjectWrapperZza2 = IObjectWrapper.zza.zza(parcel.readStrongBinder());
                zzkk zzkkVar2 = (zzkk) zzex.zza(parcel, zzkk.CREATOR);
                String string2 = parcel.readString();
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzwnVar = iInterfaceQueryLocalInterface2 instanceof zzwl ? (zzwl) iInterfaceQueryLocalInterface2 : new zzwn(strongBinder2);
                }
                zza(iObjectWrapperZza2, zzkkVar2, string2, zzwnVar);
                parcel2.writeNoException();
                return true;
            case 4:
                zzb();
                parcel2.writeNoException();
                return true;
            case 5:
                zzc();
                parcel2.writeNoException();
                return true;
            case 6:
                IObjectWrapper iObjectWrapperZza3 = IObjectWrapper.zza.zza(parcel.readStrongBinder());
                zzko zzkoVar2 = (zzko) zzex.zza(parcel, zzko.CREATOR);
                zzkk zzkkVar3 = (zzkk) zzex.zza(parcel, zzkk.CREATOR);
                String string3 = parcel.readString();
                String string4 = parcel.readString();
                IBinder strongBinder3 = parcel.readStrongBinder();
                if (strongBinder3 != null) {
                    IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzwnVar = iInterfaceQueryLocalInterface3 instanceof zzwl ? (zzwl) iInterfaceQueryLocalInterface3 : new zzwn(strongBinder3);
                }
                zza(iObjectWrapperZza3, zzkoVar2, zzkkVar3, string3, string4, zzwnVar);
                parcel2.writeNoException();
                return true;
            case 7:
                IObjectWrapper iObjectWrapperZza4 = IObjectWrapper.zza.zza(parcel.readStrongBinder());
                zzkk zzkkVar4 = (zzkk) zzex.zza(parcel, zzkk.CREATOR);
                String string5 = parcel.readString();
                String string6 = parcel.readString();
                IBinder strongBinder4 = parcel.readStrongBinder();
                if (strongBinder4 != null) {
                    IInterface iInterfaceQueryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzwnVar = iInterfaceQueryLocalInterface4 instanceof zzwl ? (zzwl) iInterfaceQueryLocalInterface4 : new zzwn(strongBinder4);
                }
                zza(iObjectWrapperZza4, zzkkVar4, string5, string6, zzwnVar);
                parcel2.writeNoException();
                return true;
            case 8:
                zzd();
                parcel2.writeNoException();
                return true;
            case 9:
                zze();
                parcel2.writeNoException();
                return true;
            case 10:
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()), (zzkk) zzex.zza(parcel, zzkk.CREATOR), parcel.readString(), zzaga.zza(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 11:
                zza((zzkk) zzex.zza(parcel, zzkk.CREATOR), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 12:
                zzf();
                parcel2.writeNoException();
                return true;
            case 13:
                zZzg = zzg();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZzg);
                return true;
            case 14:
                IObjectWrapper iObjectWrapperZza5 = IObjectWrapper.zza.zza(parcel.readStrongBinder());
                zzkk zzkkVar5 = (zzkk) zzex.zza(parcel, zzkk.CREATOR);
                String string7 = parcel.readString();
                String string8 = parcel.readString();
                IBinder strongBinder5 = parcel.readStrongBinder();
                if (strongBinder5 != null) {
                    IInterface iInterfaceQueryLocalInterface5 = strongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzwnVar = iInterfaceQueryLocalInterface5 instanceof zzwl ? (zzwl) iInterfaceQueryLocalInterface5 : new zzwn(strongBinder5);
                }
                zza(iObjectWrapperZza5, zzkkVar5, string7, string8, zzwnVar, (zzqh) zzex.zza(parcel, zzqh.CREATOR), parcel.createStringArrayList());
                parcel2.writeNoException();
                return true;
            case 15:
                iInterfaceZza = zzh();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZza);
                return true;
            case 16:
                iInterfaceZza = zzi();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZza);
                return true;
            case 17:
                bundleZzj = zzj();
                parcel2.writeNoException();
                zzex.zzb(parcel2, bundleZzj);
                return true;
            case 18:
                bundleZzj = zzk();
                parcel2.writeNoException();
                zzex.zzb(parcel2, bundleZzj);
                return true;
            case 19:
                bundleZzj = zzl();
                parcel2.writeNoException();
                zzex.zzb(parcel2, bundleZzj);
                return true;
            case 20:
                zza((zzkk) zzex.zza(parcel, zzkk.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 21:
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 22:
                zZzg = zzm();
                parcel2.writeNoException();
                zzex.zza(parcel2, zZzg);
                return true;
            case 23:
                zza(IObjectWrapper.zza.zza(parcel.readStrongBinder()), zzaga.zza(parcel.readStrongBinder()), parcel.createStringArrayList());
                parcel2.writeNoException();
                return true;
            case 24:
                iInterfaceZza = zzn();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZza);
                return true;
            case 25:
                zza(zzex.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 26:
                iInterfaceZza = zzo();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZza);
                return true;
            case 27:
                iInterfaceZza = zzp();
                parcel2.writeNoException();
                zzex.zza(parcel2, iInterfaceZza);
                return true;
            default:
                return false;
        }
    }
}
