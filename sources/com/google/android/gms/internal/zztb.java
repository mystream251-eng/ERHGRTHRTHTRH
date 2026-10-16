package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zztb implements Parcelable.Creator<zzta> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzta createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        boolean zZzc = false;
        int iZzg = 0;
        boolean zZzc2 = false;
        String strZzq = null;
        byte[] bArrZzt = null;
        String[] strArrZzaa = null;
        String[] strArrZzaa2 = null;
        long jZzi = 0;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    zZzc = zzbgm.zzc(parcel, i);
                    break;
                case 2:
                    strZzq = zzbgm.zzq(parcel, i);
                    break;
                case 3:
                    iZzg = zzbgm.zzg(parcel, i);
                    break;
                case 4:
                    bArrZzt = zzbgm.zzt(parcel, i);
                    break;
                case 5:
                    strArrZzaa = zzbgm.zzaa(parcel, i);
                    break;
                case 6:
                    strArrZzaa2 = zzbgm.zzaa(parcel, i);
                    break;
                case 7:
                    zZzc2 = zzbgm.zzc(parcel, i);
                    break;
                case 8:
                    jZzi = zzbgm.zzi(parcel, i);
                    break;
                default:
                    zzbgm.zzb(parcel, i);
                    break;
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzta(zZzc, strZzq, iZzg, bArrZzt, strArrZzaa, strArrZzaa2, zZzc2, jZzi);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzta[] newArray(int i) {
        return new zzta[i];
    }
}
