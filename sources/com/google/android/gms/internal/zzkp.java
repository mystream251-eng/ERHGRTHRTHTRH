package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzkp implements Parcelable.Creator<zzko> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzko createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        String strZzq = null;
        zzko[] zzkoVarArr = null;
        int iZzg = 0;
        int iZzg2 = 0;
        boolean zZzc = false;
        int iZzg3 = 0;
        int iZzg4 = 0;
        boolean zZzc2 = false;
        boolean zZzc3 = false;
        boolean zZzc4 = false;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    strZzq = zzbgm.zzq(parcel, i);
                    break;
                case 3:
                    iZzg = zzbgm.zzg(parcel, i);
                    break;
                case 4:
                    iZzg2 = zzbgm.zzg(parcel, i);
                    break;
                case 5:
                    zZzc = zzbgm.zzc(parcel, i);
                    break;
                case 6:
                    iZzg3 = zzbgm.zzg(parcel, i);
                    break;
                case 7:
                    iZzg4 = zzbgm.zzg(parcel, i);
                    break;
                case 8:
                    zzkoVarArr = (zzko[]) zzbgm.zzb(parcel, i, zzko.CREATOR);
                    break;
                case 9:
                    zZzc2 = zzbgm.zzc(parcel, i);
                    break;
                case 10:
                    zZzc3 = zzbgm.zzc(parcel, i);
                    break;
                case 11:
                    zZzc4 = zzbgm.zzc(parcel, i);
                    break;
                default:
                    zzbgm.zzb(parcel, i);
                    break;
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzko(strZzq, iZzg, iZzg2, zZzc, iZzg3, iZzg4, zzkoVarArr, zZzc2, zZzc3, zZzc4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzko[] newArray(int i) {
        return new zzko[i];
    }
}
