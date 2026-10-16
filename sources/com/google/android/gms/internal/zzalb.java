package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzalb implements Parcelable.Creator<zzala> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzala createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        int iZzg = 0;
        int iZzg2 = 0;
        boolean zZzc = false;
        boolean zZzc2 = false;
        String strZzq = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 2) {
                strZzq = zzbgm.zzq(parcel, i);
            } else if (i2 == 3) {
                iZzg = zzbgm.zzg(parcel, i);
            } else if (i2 == 4) {
                iZzg2 = zzbgm.zzg(parcel, i);
            } else if (i2 == 5) {
                zZzc = zzbgm.zzc(parcel, i);
            } else if (i2 != 6) {
                zzbgm.zzb(parcel, i);
            } else {
                zZzc2 = zzbgm.zzc(parcel, i);
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzala(strZzq, iZzg, iZzg2, zZzc, zZzc2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzala[] newArray(int i) {
        return new zzala[i];
    }
}
