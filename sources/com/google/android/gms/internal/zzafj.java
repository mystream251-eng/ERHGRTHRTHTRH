package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzafj implements Parcelable.Creator<zzafi> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzafi createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        zzkk zzkkVar = null;
        String strZzq = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 2) {
                zzkkVar = (zzkk) zzbgm.zza(parcel, i, zzkk.CREATOR);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, i);
            } else {
                strZzq = zzbgm.zzq(parcel, i);
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzafi(zzkkVar, strZzq);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzafi[] newArray(int i) {
        return new zzafi[i];
    }
}
