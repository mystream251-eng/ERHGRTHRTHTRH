package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zznt implements Parcelable.Creator<zzns> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzns createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        boolean zZzc = false;
        boolean zZzc2 = false;
        boolean zZzc3 = false;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 2) {
                zZzc = zzbgm.zzc(parcel, i);
            } else if (i2 == 3) {
                zZzc2 = zzbgm.zzc(parcel, i);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, i);
            } else {
                zZzc3 = zzbgm.zzc(parcel, i);
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzns(zZzc, zZzc2, zZzc3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzns[] newArray(int i) {
        return new zzns[i];
    }
}
