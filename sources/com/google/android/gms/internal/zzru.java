package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzru extends zzev implements zzrs {
    zzru(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnAppInstallAdLoadedListener");
    }

    @Override // com.google.android.gms.internal.zzrs
    public final void zza(zzrg zzrgVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzrgVar);
        zzb(1, parcelA_);
    }
}
