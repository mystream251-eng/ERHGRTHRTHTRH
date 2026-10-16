package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzrx extends zzev implements zzrv {
    zzrx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnContentAdLoadedListener");
    }

    @Override // com.google.android.gms.internal.zzrv
    public final void zza(zzrk zzrkVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzrkVar);
        zzb(1, parcelA_);
    }
}
