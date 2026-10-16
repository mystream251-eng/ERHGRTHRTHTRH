package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzsj extends zzev implements zzsh {
    zzsj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnUnifiedNativeAdLoadedListener");
    }

    @Override // com.google.android.gms.internal.zzsh
    public final void zza(zzsk zzskVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzex.zza(parcelA_, zzskVar);
        zzb(1, parcelA_);
    }
}
