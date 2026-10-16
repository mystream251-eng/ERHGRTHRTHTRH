package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzcdg implements Parcelable.Creator<zzcdf> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcdf createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        int iZzg = 0;
        byte[] bArrZzt = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                iZzg = zzbgm.zzg(parcel, i);
            } else if (i2 != 2) {
                zzbgm.zzb(parcel, i);
            } else {
                bArrZzt = zzbgm.zzt(parcel, i);
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzcdf(iZzg, bArrZzt);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcdf[] newArray(int i) {
        return new zzcdf[i];
    }
}
