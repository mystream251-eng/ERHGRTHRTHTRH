package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzsa extends zzev implements zzry {
    zzsa(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }

    @Override // com.google.android.gms.internal.zzry
    public final void zza(zzro zzroVar, String str) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzroVar);
        parcelA_.writeString(str);
        zzb(1, parcelA_);
    }
}
