package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzaew extends zzev implements zzaeu {
    zzaew(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.reward.client.IRewardItem");
    }

    @Override // com.google.android.gms.internal.zzaeu
    public final String zza() throws RemoteException {
        Parcel parcelZza = zza(1, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzaeu
    public final int zzb() throws RemoteException {
        Parcel parcelZza = zza(2, a_());
        int i = parcelZza.readInt();
        parcelZza.recycle();
        return i;
    }
}
