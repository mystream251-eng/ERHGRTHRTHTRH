package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class zzkm implements Parcelable.Creator<zzkk> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzkk createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        int iZzg = 0;
        int iZzg2 = 0;
        boolean zZzc = false;
        int iZzg3 = 0;
        boolean zZzc2 = false;
        boolean zZzc3 = false;
        Bundle bundleZzs = null;
        ArrayList arrayListZzac = null;
        String strZzq = null;
        zzno zznoVar = null;
        Location location = null;
        String strZzq2 = null;
        Bundle bundleZzs2 = null;
        Bundle bundleZzs3 = null;
        ArrayList arrayListZzac2 = null;
        String strZzq3 = null;
        String strZzq4 = null;
        long jZzi = 0;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbgm.zzg(parcel, i);
                    break;
                case 2:
                    jZzi = zzbgm.zzi(parcel, i);
                    break;
                case 3:
                    bundleZzs = zzbgm.zzs(parcel, i);
                    break;
                case 4:
                    iZzg2 = zzbgm.zzg(parcel, i);
                    break;
                case 5:
                    arrayListZzac = zzbgm.zzac(parcel, i);
                    break;
                case 6:
                    zZzc = zzbgm.zzc(parcel, i);
                    break;
                case 7:
                    iZzg3 = zzbgm.zzg(parcel, i);
                    break;
                case 8:
                    zZzc2 = zzbgm.zzc(parcel, i);
                    break;
                case 9:
                    strZzq = zzbgm.zzq(parcel, i);
                    break;
                case 10:
                    zznoVar = (zzno) zzbgm.zza(parcel, i, zzno.CREATOR);
                    break;
                case 11:
                    location = (Location) zzbgm.zza(parcel, i, Location.CREATOR);
                    break;
                case 12:
                    strZzq2 = zzbgm.zzq(parcel, i);
                    break;
                case 13:
                    bundleZzs2 = zzbgm.zzs(parcel, i);
                    break;
                case 14:
                    bundleZzs3 = zzbgm.zzs(parcel, i);
                    break;
                case 15:
                    arrayListZzac2 = zzbgm.zzac(parcel, i);
                    break;
                case 16:
                    strZzq3 = zzbgm.zzq(parcel, i);
                    break;
                case 17:
                    strZzq4 = zzbgm.zzq(parcel, i);
                    break;
                case 18:
                    zZzc3 = zzbgm.zzc(parcel, i);
                    break;
                default:
                    zzbgm.zzb(parcel, i);
                    break;
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzkk(iZzg, jZzi, bundleZzs, iZzg2, arrayListZzac, zZzc, iZzg3, zZzc2, strZzq, zznoVar, location, strZzq2, bundleZzs2, bundleZzs3, arrayListZzac2, strZzq3, strZzq4, zZzc3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzkk[] newArray(int i) {
        return new zzkk[i];
    }
}
