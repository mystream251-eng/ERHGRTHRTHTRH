package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzqi implements Parcelable.Creator<zzqh> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzqh createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        int iZzg = 0;
        boolean zZzc = false;
        int iZzg2 = 0;
        boolean zZzc2 = false;
        int iZzg3 = 0;
        zzns zznsVar = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbgm.zzg(parcel, i);
                    break;
                case 2:
                    zZzc = zzbgm.zzc(parcel, i);
                    break;
                case 3:
                    iZzg2 = zzbgm.zzg(parcel, i);
                    break;
                case 4:
                    zZzc2 = zzbgm.zzc(parcel, i);
                    break;
                case 5:
                    iZzg3 = zzbgm.zzg(parcel, i);
                    break;
                case 6:
                    zznsVar = (zzns) zzbgm.zza(parcel, i, zzns.CREATOR);
                    break;
                default:
                    zzbgm.zzb(parcel, i);
                    break;
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzqh(iZzg, zZzc, iZzg2, zZzc2, iZzg3, zznsVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzqh[] newArray(int i) {
        return new zzqh[i];
    }
}
