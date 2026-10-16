package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzwq extends zzev implements zzwo {
    zzwq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
    }

    @Override // com.google.android.gms.internal.zzwo
    public final int zza() throws RemoteException {
        Parcel parcelZza = zza(1, a_());
        int i = parcelZza.readInt();
        parcelZza.recycle();
        return i;
    }
}
