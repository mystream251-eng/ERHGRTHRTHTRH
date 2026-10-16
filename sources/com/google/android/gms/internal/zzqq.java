package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzqq extends zzev implements zzqo {
    zzqq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
    }

    @Override // com.google.android.gms.internal.zzqo
    public final String zza() throws RemoteException {
        Parcel parcelZza = zza(2, a_());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzqo
    public final List<zzqs> zzb() throws RemoteException {
        Parcel parcelZza = zza(3, a_());
        ArrayList arrayListZzb = zzex.zzb(parcelZza);
        parcelZza.recycle();
        return arrayListZzb;
    }
}
