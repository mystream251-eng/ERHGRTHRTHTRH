package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzage implements Parcelable.Creator<zzagd> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzagd createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        String strZzq = null;
        int iZzg = 0;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 2) {
                strZzq = zzbgm.zzq(parcel, i);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, i);
            } else {
                iZzg = zzbgm.zzg(parcel, i);
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzagd(strZzq, iZzg);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzagd[] newArray(int i) {
        return new zzagd[i];
    }
}
