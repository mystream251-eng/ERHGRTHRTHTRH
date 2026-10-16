package com.google.android.gms.ads.internal.overlay;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzap;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzbgm;

/* JADX INFO: loaded from: classes2.dex */
public final class zzm implements Parcelable.Creator<AdOverlayInfoParcel> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ AdOverlayInfoParcel createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        boolean zZzc = false;
        int iZzg = 0;
        int iZzg2 = 0;
        zzc zzcVar = null;
        IBinder iBinderZzr = null;
        IBinder iBinderZzr2 = null;
        IBinder iBinderZzr3 = null;
        IBinder iBinderZzr4 = null;
        String strZzq = null;
        String strZzq2 = null;
        IBinder iBinderZzr5 = null;
        String strZzq3 = null;
        zzala zzalaVar = null;
        String strZzq4 = null;
        zzap zzapVar = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    zzcVar = (zzc) zzbgm.zza(parcel, i, zzc.CREATOR);
                    break;
                case 3:
                    iBinderZzr = zzbgm.zzr(parcel, i);
                    break;
                case 4:
                    iBinderZzr2 = zzbgm.zzr(parcel, i);
                    break;
                case 5:
                    iBinderZzr3 = zzbgm.zzr(parcel, i);
                    break;
                case 6:
                    iBinderZzr4 = zzbgm.zzr(parcel, i);
                    break;
                case 7:
                    strZzq = zzbgm.zzq(parcel, i);
                    break;
                case 8:
                    zZzc = zzbgm.zzc(parcel, i);
                    break;
                case 9:
                    strZzq2 = zzbgm.zzq(parcel, i);
                    break;
                case 10:
                    iBinderZzr5 = zzbgm.zzr(parcel, i);
                    break;
                case 11:
                    iZzg = zzbgm.zzg(parcel, i);
                    break;
                case 12:
                    iZzg2 = zzbgm.zzg(parcel, i);
                    break;
                case 13:
                    strZzq3 = zzbgm.zzq(parcel, i);
                    break;
                case 14:
                    zzalaVar = (zzala) zzbgm.zza(parcel, i, zzala.CREATOR);
                    break;
                case 15:
                default:
                    zzbgm.zzb(parcel, i);
                    break;
                case 16:
                    strZzq4 = zzbgm.zzq(parcel, i);
                    break;
                case 17:
                    zzapVar = (zzap) zzbgm.zza(parcel, i, zzap.CREATOR);
                    break;
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new AdOverlayInfoParcel(zzcVar, iBinderZzr, iBinderZzr2, iBinderZzr3, iBinderZzr4, strZzq, zZzc, strZzq2, iBinderZzr5, iZzg, iZzg2, strZzq3, zzalaVar, strZzq4, zzapVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ AdOverlayInfoParcel[] newArray(int i) {
        return new AdOverlayInfoParcel[i];
    }
}
