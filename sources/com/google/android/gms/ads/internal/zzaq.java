package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

/* JADX INFO: loaded from: classes2.dex */
public final class zzaq implements Parcelable.Creator<zzap> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzap createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        boolean zZzc = false;
        boolean zZzc2 = false;
        boolean zZzc3 = false;
        int iZzg = 0;
        boolean zZzc4 = false;
        boolean zZzc5 = false;
        String strZzq = null;
        float fZzl = 0.0f;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    zZzc = zzbgm.zzc(parcel, i);
                    break;
                case 3:
                    zZzc2 = zzbgm.zzc(parcel, i);
                    break;
                case 4:
                    strZzq = zzbgm.zzq(parcel, i);
                    break;
                case 5:
                    zZzc3 = zzbgm.zzc(parcel, i);
                    break;
                case 6:
                    fZzl = zzbgm.zzl(parcel, i);
                    break;
                case 7:
                    iZzg = zzbgm.zzg(parcel, i);
                    break;
                case 8:
                    zZzc4 = zzbgm.zzc(parcel, i);
                    break;
                case 9:
                    zZzc5 = zzbgm.zzc(parcel, i);
                    break;
                default:
                    zzbgm.zzb(parcel, i);
                    break;
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzap(zZzc, zZzc2, strZzq, zZzc3, fZzl, iZzg, zZzc4, zZzc5);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzap[] newArray(int i) {
        return new zzap[i];
    }
}
