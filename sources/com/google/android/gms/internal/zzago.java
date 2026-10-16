package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class zzago implements Parcelable.Creator<zzagn> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzagn createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        String strZzq = null;
        String strZzq2 = null;
        ArrayList arrayListZzac = null;
        boolean zZzc = false;
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
                    strZzq2 = zzbgm.zzq(parcel, i);
                    break;
                case 4:
                    zZzc = zzbgm.zzc(parcel, i);
                    break;
                case 5:
                    zZzc2 = zzbgm.zzc(parcel, i);
                    break;
                case 6:
                    arrayListZzac = zzbgm.zzac(parcel, i);
                    break;
                case 7:
                    zZzc3 = zzbgm.zzc(parcel, i);
                    break;
                case 8:
                    zZzc4 = zzbgm.zzc(parcel, i);
                    break;
                default:
                    zzbgm.zzb(parcel, i);
                    break;
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzagn(strZzq, strZzq2, zZzc, zZzc2, arrayListZzac, zZzc3, zZzc4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzagn[] newArray(int i) {
        return new zzagn[i];
    }
}
