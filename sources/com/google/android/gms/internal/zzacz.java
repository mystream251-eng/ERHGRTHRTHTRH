package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class zzacz implements Parcelable.Creator<zzacy> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzacy createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        Bundle bundleZzs = null;
        zzala zzalaVar = null;
        ApplicationInfo applicationInfo = null;
        String strZzq = null;
        ArrayList arrayListZzac = null;
        PackageInfo packageInfo = null;
        String strZzq2 = null;
        String strZzq3 = null;
        boolean zZzc = false;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    bundleZzs = zzbgm.zzs(parcel, i);
                    break;
                case 2:
                    zzalaVar = (zzala) zzbgm.zza(parcel, i, zzala.CREATOR);
                    break;
                case 3:
                    applicationInfo = (ApplicationInfo) zzbgm.zza(parcel, i, ApplicationInfo.CREATOR);
                    break;
                case 4:
                    strZzq = zzbgm.zzq(parcel, i);
                    break;
                case 5:
                    arrayListZzac = zzbgm.zzac(parcel, i);
                    break;
                case 6:
                    packageInfo = (PackageInfo) zzbgm.zza(parcel, i, PackageInfo.CREATOR);
                    break;
                case 7:
                    strZzq2 = zzbgm.zzq(parcel, i);
                    break;
                case 8:
                    zZzc = zzbgm.zzc(parcel, i);
                    break;
                case 9:
                    strZzq3 = zzbgm.zzq(parcel, i);
                    break;
                default:
                    zzbgm.zzb(parcel, i);
                    break;
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzacy(bundleZzs, zzalaVar, applicationInfo, strZzq, arrayListZzac, packageInfo, strZzq2, zZzc, strZzq3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzacy[] newArray(int i) {
        return new zzacy[i];
    }
}
