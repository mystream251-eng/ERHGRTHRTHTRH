package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzln extends zzev implements zzll {
    zzln(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdLoader");
    }

    @Override // com.google.android.gms.internal.zzll
    public final String zza() throws RemoteException {
        Parcel parcelZza = zza(2, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzll
    public final void zza(zzkk zzkkVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzkkVar);
        zzb(1, parcelA_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzll
    public final void zza(zzkk zzkkVar, int i) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzkkVar);
        parcelA_.writeInt(i);
        zzb(5, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzll
    public final String zzb() throws RemoteException {
        Parcel parcelZza = zza(4, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzll
    public final boolean zzc() throws RemoteException {
        Parcel parcelZza = zza(3, a_());
        boolean zZza = zzex.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }
}
