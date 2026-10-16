package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzacp extends zzev implements zzacn {
    zzacp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzacn
    public final zzacj zza(zzacf zzacfVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzacfVar);
        Parcel parcelZza = zza(1, parcelA_);
        zzacj zzacjVar = (zzacj) zzex.zza(parcelZza, zzacj.CREATOR);
        parcelZza.recycle();
        return zzacjVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzacn
    public final void zza(zzacf zzacfVar, zzacq zzacqVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzacfVar);
        zzex.zza(parcelA_, zzacqVar);
        zzb(2, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzacn
    public final void zza(zzacy zzacyVar, zzact zzactVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzacyVar);
        zzex.zza(parcelA_, zzactVar);
        zzb(4, parcelA_);
    }
}
