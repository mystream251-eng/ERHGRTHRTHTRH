package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzadk implements Parcelable.Creator<zzadj> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzadj createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        String strZzq = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            if ((65535 & i) != 2) {
                zzbgm.zzb(parcel, i);
            } else {
                strZzq = zzbgm.zzq(parcel, i);
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzadj(strZzq);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzadj[] newArray(int i) {
        return new zzadj[i];
    }
}
