package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzaco extends zzew implements zzacn {
    public zzaco() {
        attachInterface(this, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            zzev zzacsVar = null;
            if (i == 2) {
                zzacf zzacfVar = (zzacf) zzex.zza(parcel, zzacf.CREATOR);
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    zzev zzevVarQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdResponseListener");
                    zzacsVar = zzevVarQueryLocalInterface instanceof zzacq ? (zzacq) zzevVarQueryLocalInterface : new zzacs(strongBinder);
                }
                zza(zzacfVar, (zzacq) zzacsVar);
            } else {
                if (i != 4) {
                    return false;
                }
                zzacy zzacyVar = (zzacy) zzex.zza(parcel, zzacy.CREATOR);
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    zzev zzevVarQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    zzacsVar = zzevVarQueryLocalInterface2 instanceof zzact ? (zzact) zzevVarQueryLocalInterface2 : new zzacu(strongBinder2);
                }
                zza(zzacyVar, (zzact) zzacsVar);
            }
            parcel2.writeNoException();
        } else {
            zzbgl zzbglVarZza = zza((zzacf) zzex.zza(parcel, zzacf.CREATOR));
            parcel2.writeNoException();
            zzex.zzb(parcel2, zzbglVarZza);
        }
        return true;
    }
}
