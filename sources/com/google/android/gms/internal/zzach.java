package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class zzach implements Parcelable.Creator<zzacf> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzacf createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        long jZzi = 0;
        long jZzi2 = 0;
        float fZzl = 0.0f;
        float fZzl2 = 0.0f;
        int iZzg = 0;
        int iZzg2 = 0;
        boolean zZzc = false;
        int iZzg3 = 0;
        int iZzg4 = 0;
        boolean zZzc2 = false;
        int iZzg5 = 0;
        int iZzg6 = 0;
        boolean zZzc3 = false;
        boolean zZzc4 = false;
        boolean zZzc5 = false;
        int iZzg7 = 0;
        boolean zZzc6 = false;
        boolean zZzc7 = false;
        int iZzg8 = 0;
        boolean zZzc8 = false;
        boolean zZzc9 = false;
        boolean zZzc10 = false;
        Bundle bundleZzs = null;
        zzkk zzkkVar = null;
        zzko zzkoVar = null;
        String strZzq = null;
        ApplicationInfo applicationInfo = null;
        PackageInfo packageInfo = null;
        String strZzq2 = null;
        String strZzq3 = null;
        String strZzq4 = null;
        zzala zzalaVar = null;
        Bundle bundleZzs2 = null;
        ArrayList arrayListZzac = null;
        Bundle bundleZzs3 = null;
        String strZzq5 = null;
        String strZzq6 = null;
        ArrayList arrayListZzac2 = null;
        String strZzq7 = null;
        zzqh zzqhVar = null;
        ArrayList arrayListZzac3 = null;
        String strZzq8 = null;
        String strZzq9 = null;
        String strZzq10 = null;
        Bundle bundleZzs4 = null;
        String strZzq11 = null;
        zzms zzmsVar = null;
        Bundle bundleZzs5 = null;
        String strZzq12 = null;
        String strZzq13 = null;
        String strZzq14 = null;
        ArrayList arrayListZzab = null;
        String strZzq15 = null;
        ArrayList arrayListZzac4 = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbgm.zzg(parcel, i);
                    break;
                case 2:
                    bundleZzs = zzbgm.zzs(parcel, i);
                    break;
                case 3:
                    zzkkVar = (zzkk) zzbgm.zza(parcel, i, zzkk.CREATOR);
                    break;
                case 4:
                    zzkoVar = (zzko) zzbgm.zza(parcel, i, zzko.CREATOR);
                    break;
                case 5:
                    strZzq = zzbgm.zzq(parcel, i);
                    break;
                case 6:
                    applicationInfo = (ApplicationInfo) zzbgm.zza(parcel, i, ApplicationInfo.CREATOR);
                    break;
                case 7:
                    packageInfo = (PackageInfo) zzbgm.zza(parcel, i, PackageInfo.CREATOR);
                    break;
                case 8:
                    strZzq2 = zzbgm.zzq(parcel, i);
                    break;
                case 9:
                    strZzq3 = zzbgm.zzq(parcel, i);
                    break;
                case 10:
                    strZzq4 = zzbgm.zzq(parcel, i);
                    break;
                case 11:
                    zzalaVar = (zzala) zzbgm.zza(parcel, i, zzala.CREATOR);
                    break;
                case 12:
                    bundleZzs2 = zzbgm.zzs(parcel, i);
                    break;
                case 13:
                    iZzg2 = zzbgm.zzg(parcel, i);
                    break;
                case 14:
                    arrayListZzac = zzbgm.zzac(parcel, i);
                    break;
                case 15:
                    bundleZzs3 = zzbgm.zzs(parcel, i);
                    break;
                case 16:
                    zZzc = zzbgm.zzc(parcel, i);
                    break;
                case 17:
                case 22:
                case 23:
                case 24:
                case 32:
                default:
                    zzbgm.zzb(parcel, i);
                    break;
                case 18:
                    iZzg3 = zzbgm.zzg(parcel, i);
                    break;
                case 19:
                    iZzg4 = zzbgm.zzg(parcel, i);
                    break;
                case 20:
                    fZzl = zzbgm.zzl(parcel, i);
                    break;
                case 21:
                    strZzq5 = zzbgm.zzq(parcel, i);
                    break;
                case 25:
                    jZzi = zzbgm.zzi(parcel, i);
                    break;
                case 26:
                    strZzq6 = zzbgm.zzq(parcel, i);
                    break;
                case 27:
                    arrayListZzac2 = zzbgm.zzac(parcel, i);
                    break;
                case 28:
                    strZzq7 = zzbgm.zzq(parcel, i);
                    break;
                case 29:
                    zzqhVar = (zzqh) zzbgm.zza(parcel, i, zzqh.CREATOR);
                    break;
                case 30:
                    arrayListZzac3 = zzbgm.zzac(parcel, i);
                    break;
                case 31:
                    jZzi2 = zzbgm.zzi(parcel, i);
                    break;
                case 33:
                    strZzq8 = zzbgm.zzq(parcel, i);
                    break;
                case 34:
                    fZzl2 = zzbgm.zzl(parcel, i);
                    break;
                case 35:
                    iZzg5 = zzbgm.zzg(parcel, i);
                    break;
                case 36:
                    iZzg6 = zzbgm.zzg(parcel, i);
                    break;
                case 37:
                    zZzc3 = zzbgm.zzc(parcel, i);
                    break;
                case 38:
                    zZzc4 = zzbgm.zzc(parcel, i);
                    break;
                case 39:
                    strZzq9 = zzbgm.zzq(parcel, i);
                    break;
                case 40:
                    zZzc2 = zzbgm.zzc(parcel, i);
                    break;
                case 41:
                    strZzq10 = zzbgm.zzq(parcel, i);
                    break;
                case 42:
                    zZzc5 = zzbgm.zzc(parcel, i);
                    break;
                case 43:
                    iZzg7 = zzbgm.zzg(parcel, i);
                    break;
                case 44:
                    bundleZzs4 = zzbgm.zzs(parcel, i);
                    break;
                case 45:
                    strZzq11 = zzbgm.zzq(parcel, i);
                    break;
                case 46:
                    zzmsVar = (zzms) zzbgm.zza(parcel, i, zzms.CREATOR);
                    break;
                case 47:
                    zZzc6 = zzbgm.zzc(parcel, i);
                    break;
                case 48:
                    bundleZzs5 = zzbgm.zzs(parcel, i);
                    break;
                case 49:
                    strZzq12 = zzbgm.zzq(parcel, i);
                    break;
                case 50:
                    strZzq13 = zzbgm.zzq(parcel, i);
                    break;
                case 51:
                    strZzq14 = zzbgm.zzq(parcel, i);
                    break;
                case 52:
                    zZzc7 = zzbgm.zzc(parcel, i);
                    break;
                case 53:
                    arrayListZzab = zzbgm.zzab(parcel, i);
                    break;
                case 54:
                    strZzq15 = zzbgm.zzq(parcel, i);
                    break;
                case 55:
                    arrayListZzac4 = zzbgm.zzac(parcel, i);
                    break;
                case 56:
                    iZzg8 = zzbgm.zzg(parcel, i);
                    break;
                case 57:
                    zZzc8 = zzbgm.zzc(parcel, i);
                    break;
                case 58:
                    zZzc9 = zzbgm.zzc(parcel, i);
                    break;
                case 59:
                    zZzc10 = zzbgm.zzc(parcel, i);
                    break;
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzacf(iZzg, bundleZzs, zzkkVar, zzkoVar, strZzq, applicationInfo, packageInfo, strZzq2, strZzq3, strZzq4, zzalaVar, bundleZzs2, iZzg2, arrayListZzac, bundleZzs3, zZzc, iZzg3, iZzg4, fZzl, strZzq5, jZzi, strZzq6, arrayListZzac2, strZzq7, zzqhVar, arrayListZzac3, jZzi2, strZzq8, fZzl2, zZzc2, iZzg5, iZzg6, zZzc3, zZzc4, strZzq9, strZzq10, zZzc5, iZzg7, bundleZzs4, strZzq11, zzmsVar, zZzc6, bundleZzs5, strZzq12, strZzq13, strZzq14, zZzc7, arrayListZzab, strZzq15, arrayListZzac4, iZzg8, zZzc8, zZzc9, zZzc10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzacf[] newArray(int i) {
        return new zzacf[i];
    }
}
