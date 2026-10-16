package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzmr extends zzev implements zzmp {
    zzmr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
    }

    @Override // com.google.android.gms.internal.zzmp
    public final void zza() throws RemoteException {
        zzb(1, a_());
    }

    @Override // com.google.android.gms.internal.zzmp
    public final void zza(boolean z) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, z);
        zzb(5, parcelA_);
    }

    @Override // com.google.android.gms.internal.zzmp
    public final void zzb() throws RemoteException {
        zzb(2, a_());
    }

    @Override // com.google.android.gms.internal.zzmp
    public final void zzc() throws RemoteException {
        zzb(3, a_());
    }

    @Override // com.google.android.gms.internal.zzmp
    public final void zzd() throws RemoteException {
        zzb(4, a_());
    }
}
