package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzil implements Parcelable.Creator<zzik> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzik createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        ParcelFileDescriptor parcelFileDescriptor = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            if ((65535 & i) != 2) {
                zzbgm.zzb(parcel, i);
            } else {
                parcelFileDescriptor = (ParcelFileDescriptor) zzbgm.zza(parcel, i, ParcelFileDescriptor.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzik(parcelFileDescriptor);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzik[] newArray(int i) {
        return new zzik[i];
    }
}
