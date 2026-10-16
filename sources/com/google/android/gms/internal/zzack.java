package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class zzack implements Parcelable.Creator<zzacj> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzacj createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        long jZzi = 0;
        long jZzi2 = 0;
        long jZzi3 = 0;
        long jZzi4 = 0;
        int iZzg = 0;
        int iZzg2 = 0;
        boolean zZzc = false;
        int iZzg3 = 0;
        boolean zZzc2 = false;
        boolean zZzc3 = false;
        boolean zZzc4 = false;
        boolean zZzc5 = false;
        boolean zZzc6 = false;
        boolean zZzc7 = false;
        boolean zZzc8 = false;
        boolean zZzc9 = false;
        boolean zZzc10 = false;
        boolean zZzc11 = false;
        boolean zZzc12 = false;
        boolean zZzc13 = false;
        boolean zZzc14 = false;
        boolean zZzc15 = false;
        int iZzg4 = 0;
        String strZzq = null;
        String strZzq2 = null;
        ArrayList arrayListZzac = null;
        ArrayList arrayListZzac2 = null;
        ArrayList arrayListZzac3 = null;
        String strZzq3 = null;
        String strZzq4 = null;
        String strZzq5 = null;
        String strZzq6 = null;
        zzacv zzacvVar = null;
        String strZzq7 = null;
        String strZzq8 = null;
        zzagd zzagdVar = null;
        ArrayList arrayListZzac4 = null;
        ArrayList arrayListZzac5 = null;
        zzacl zzaclVar = null;
        String strZzq9 = null;
        ArrayList arrayListZzac6 = null;
        String strZzq10 = null;
        zzagn zzagnVar = null;
        String strZzq11 = null;
        Bundle bundleZzs = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbgm.zzg(parcel, i);
                    break;
                case 2:
                    strZzq = zzbgm.zzq(parcel, i);
                    break;
                case 3:
                    strZzq2 = zzbgm.zzq(parcel, i);
                    break;
                case 4:
                    arrayListZzac = zzbgm.zzac(parcel, i);
                    break;
                case 5:
                    iZzg2 = zzbgm.zzg(parcel, i);
                    break;
                case 6:
                    arrayListZzac2 = zzbgm.zzac(parcel, i);
                    break;
                case 7:
                    jZzi = zzbgm.zzi(parcel, i);
                    break;
                case 8:
                    zZzc = zzbgm.zzc(parcel, i);
                    break;
                case 9:
                    jZzi2 = zzbgm.zzi(parcel, i);
                    break;
                case 10:
                    arrayListZzac3 = zzbgm.zzac(parcel, i);
                    break;
                case 11:
                    jZzi3 = zzbgm.zzi(parcel, i);
                    break;
                case 12:
                    iZzg3 = zzbgm.zzg(parcel, i);
                    break;
                case 13:
                    strZzq3 = zzbgm.zzq(parcel, i);
                    break;
                case 14:
                    jZzi4 = zzbgm.zzi(parcel, i);
                    break;
                case 15:
                    strZzq4 = zzbgm.zzq(parcel, i);
                    break;
                case 16:
                case 17:
                case 20:
                case 27:
                case 41:
                default:
                    zzbgm.zzb(parcel, i);
                    break;
                case 18:
                    zZzc2 = zzbgm.zzc(parcel, i);
                    break;
                case 19:
                    strZzq5 = zzbgm.zzq(parcel, i);
                    break;
                case 21:
                    strZzq6 = zzbgm.zzq(parcel, i);
                    break;
                case 22:
                    zZzc3 = zzbgm.zzc(parcel, i);
                    break;
                case 23:
                    zZzc4 = zzbgm.zzc(parcel, i);
                    break;
                case 24:
                    zZzc5 = zzbgm.zzc(parcel, i);
                    break;
                case 25:
                    zZzc6 = zzbgm.zzc(parcel, i);
                    break;
                case 26:
                    zZzc7 = zzbgm.zzc(parcel, i);
                    break;
                case 28:
                    zzacvVar = (zzacv) zzbgm.zza(parcel, i, zzacv.CREATOR);
                    break;
                case 29:
                    strZzq7 = zzbgm.zzq(parcel, i);
                    break;
                case 30:
                    strZzq8 = zzbgm.zzq(parcel, i);
                    break;
                case 31:
                    zZzc8 = zzbgm.zzc(parcel, i);
                    break;
                case 32:
                    zZzc9 = zzbgm.zzc(parcel, i);
                    break;
                case 33:
                    zzagdVar = (zzagd) zzbgm.zza(parcel, i, zzagd.CREATOR);
                    break;
                case 34:
                    arrayListZzac4 = zzbgm.zzac(parcel, i);
                    break;
                case 35:
                    arrayListZzac5 = zzbgm.zzac(parcel, i);
                    break;
                case 36:
                    zZzc10 = zzbgm.zzc(parcel, i);
                    break;
                case 37:
                    zzaclVar = (zzacl) zzbgm.zza(parcel, i, zzacl.CREATOR);
                    break;
                case 38:
                    zZzc11 = zzbgm.zzc(parcel, i);
                    break;
                case 39:
                    strZzq9 = zzbgm.zzq(parcel, i);
                    break;
                case 40:
                    arrayListZzac6 = zzbgm.zzac(parcel, i);
                    break;
                case 42:
                    zZzc12 = zzbgm.zzc(parcel, i);
                    break;
                case 43:
                    strZzq10 = zzbgm.zzq(parcel, i);
                    break;
                case 44:
                    zzagnVar = (zzagn) zzbgm.zza(parcel, i, zzagn.CREATOR);
                    break;
                case 45:
                    strZzq11 = zzbgm.zzq(parcel, i);
                    break;
                case 46:
                    zZzc13 = zzbgm.zzc(parcel, i);
                    break;
                case 47:
                    zZzc14 = zzbgm.zzc(parcel, i);
                    break;
                case 48:
                    bundleZzs = zzbgm.zzs(parcel, i);
                    break;
                case 49:
                    zZzc15 = zzbgm.zzc(parcel, i);
                    break;
                case 50:
                    iZzg4 = zzbgm.zzg(parcel, i);
                    break;
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzacj(iZzg, strZzq, strZzq2, arrayListZzac, iZzg2, arrayListZzac2, jZzi, zZzc, jZzi2, arrayListZzac3, jZzi3, iZzg3, strZzq3, jZzi4, strZzq4, zZzc2, strZzq5, strZzq6, zZzc3, zZzc4, zZzc5, zZzc6, zZzc7, zzacvVar, strZzq7, strZzq8, zZzc8, zZzc9, zzagdVar, arrayListZzac4, arrayListZzac5, zZzc10, zzaclVar, zZzc11, strZzq9, arrayListZzac6, zZzc12, strZzq10, zzagnVar, strZzq11, zZzc13, zZzc14, bundleZzs, zZzc15, iZzg4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzacj[] newArray(int i) {
        return new zzacj[i];
    }
}
