package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzo extends com.google.android.gms.internal.common.zzb implements IGmsCallbacks {
    zzo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeInt(i);
        parcelA_.writeStrongBinder(iBinder);
        com.google.android.gms.internal.common.zzd.zza(parcelA_, bundle);
        zzb(1, parcelA_);
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zza(int i, Bundle bundle) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeInt(i);
        com.google.android.gms.internal.common.zzd.zza(parcelA_, bundle);
        zzb(2, parcelA_);
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zza(int i, IBinder iBinder, zzc zzcVar) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeInt(i);
        parcelA_.writeStrongBinder(iBinder);
        com.google.android.gms.internal.common.zzd.zza(parcelA_, zzcVar);
        zzb(3, parcelA_);
    }
}
