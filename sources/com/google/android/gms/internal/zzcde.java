package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzcde implements Parcelable.Creator<zzcdd> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcdd createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        String strZzq = null;
        int iZzg = 0;
        String strZzq2 = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                iZzg = zzbgm.zzg(parcel, i);
            } else if (i2 == 2) {
                strZzq = zzbgm.zzq(parcel, i);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, i);
            } else {
                strZzq2 = zzbgm.zzq(parcel, i);
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzcdd(iZzg, strZzq, strZzq2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcdd[] newArray(int i) {
        return new zzcdd[i];
    }
}
