package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzacx implements Parcelable.Creator<zzacv> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzacv createFromParcel(Parcel parcel) {
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
        return new zzacv(parcelFileDescriptor);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzacv[] newArray(int i) {
        return new zzacv[i];
    }
}
