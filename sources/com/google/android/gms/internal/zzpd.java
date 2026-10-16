package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzpd extends zzev implements zzpb {
    zzpd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.customrenderedad.client.IOnCustomRenderedAdLoadedListener");
    }

    @Override // com.google.android.gms.internal.zzpb
    public final void zza(zzoy zzoyVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzoyVar);
        zzb(1, parcelA_);
    }
}
