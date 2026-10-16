package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzsd extends zzev implements zzsb {
    zzsd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnCustomTemplateAdLoadedListener");
    }

    @Override // com.google.android.gms.internal.zzsb
    public final void zza(zzro zzroVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzroVar);
        zzb(1, parcelA_);
    }
}
