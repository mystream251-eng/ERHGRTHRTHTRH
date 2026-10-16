package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzmg extends zzev implements zzme {
    zzmg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
    }

    @Override // com.google.android.gms.internal.zzme
    public final long zzb() throws RemoteException {
        Parcel parcelZza = zza(1, a_());
        long j = parcelZza.readLong();
        parcelZza.recycle();
        return j;
    }
}
