package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzsz implements Parcelable.Creator<zzsy> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzsy createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        String strZzq = null;
        String[] strArrZzaa = null;
        String[] strArrZzaa2 = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                strZzq = zzbgm.zzq(parcel, i);
            } else if (i2 == 2) {
                strArrZzaa = zzbgm.zzaa(parcel, i);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, i);
            } else {
                strArrZzaa2 = zzbgm.zzaa(parcel, i);
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzsy(strZzq, strArrZzaa, strArrZzaa2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzsy[] newArray(int i) {
        return new zzsy[i];
    }
}
