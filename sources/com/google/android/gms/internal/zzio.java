package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzio implements Parcelable.Creator<zzin> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzin createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        long jZzi = 0;
        long jZzi2 = 0;
        String strZzq = null;
        String strZzq2 = null;
        String strZzq3 = null;
        String strZzq4 = null;
        Bundle bundleZzs = null;
        boolean zZzc = false;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    strZzq = zzbgm.zzq(parcel, i);
                    break;
                case 3:
                    jZzi = zzbgm.zzi(parcel, i);
                    break;
                case 4:
                    strZzq2 = zzbgm.zzq(parcel, i);
                    break;
                case 5:
                    strZzq3 = zzbgm.zzq(parcel, i);
                    break;
                case 6:
                    strZzq4 = zzbgm.zzq(parcel, i);
                    break;
                case 7:
                    bundleZzs = zzbgm.zzs(parcel, i);
                    break;
                case 8:
                    zZzc = zzbgm.zzc(parcel, i);
                    break;
                case 9:
                    jZzi2 = zzbgm.zzi(parcel, i);
                    break;
                default:
                    zzbgm.zzb(parcel, i);
                    break;
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzin(strZzq, jZzi, strZzq2, strZzq3, strZzq4, bundleZzs, zZzc, jZzi2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzin[] newArray(int i) {
        return new zzin[i];
    }
}
