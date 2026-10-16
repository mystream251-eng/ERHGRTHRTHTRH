package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzmt implements Parcelable.Creator<zzms> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzms createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        int iZzg = 0;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            if ((65535 & i) != 2) {
                zzbgm.zzb(parcel, i);
            } else {
                iZzg = zzbgm.zzg(parcel, i);
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzms(iZzg);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzms[] newArray(int i) {
        return new zzms[i];
    }
}
