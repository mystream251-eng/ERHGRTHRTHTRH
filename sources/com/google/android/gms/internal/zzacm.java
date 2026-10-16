package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class zzacm implements Parcelable.Creator<zzacl> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzacl createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        boolean zZzc = false;
        ArrayList arrayListZzac = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 2) {
                zZzc = zzbgm.zzc(parcel, i);
            } else if (i2 != 3) {
                zzbgm.zzb(parcel, i);
            } else {
                arrayListZzac = zzbgm.zzac(parcel, i);
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzacl(zZzc, arrayListZzac);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzacl[] newArray(int i) {
        return new zzacl[i];
    }
}
