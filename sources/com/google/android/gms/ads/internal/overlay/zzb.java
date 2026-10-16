package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

/* JADX INFO: loaded from: classes2.dex */
public final class zzb implements Parcelable.Creator<zzc> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzc createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        String strZzq = null;
        String strZzq2 = null;
        String strZzq3 = null;
        String strZzq4 = null;
        String strZzq5 = null;
        String strZzq6 = null;
        String strZzq7 = null;
        Intent intent = null;
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
                    strZzq3 = zzbgm.zzq(parcel, i);
                    break;
                case 5:
                    strZzq4 = zzbgm.zzq(parcel, i);
                    break;
                case 6:
                    strZzq5 = zzbgm.zzq(parcel, i);
                    break;
                case 7:
                    strZzq6 = zzbgm.zzq(parcel, i);
                    break;
                case 8:
                    strZzq7 = zzbgm.zzq(parcel, i);
                    break;
                case 9:
                    intent = (Intent) zzbgm.zza(parcel, i, Intent.CREATOR);
                    break;
                default:
                    zzbgm.zzb(parcel, i);
                    break;
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new zzc(strZzq, strZzq2, strZzq3, strZzq4, strZzq5, strZzq6, strZzq7, intent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzc[] newArray(int i) {
        return new zzc[i];
    }
}
