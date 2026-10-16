package com.google.android.gms.ads.formats;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

/* JADX INFO: loaded from: classes2.dex */
public final class zzc implements Parcelable.Creator<PublisherAdViewOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PublisherAdViewOptions createFromParcel(Parcel parcel) {
        int iZza = zzbgm.zza(parcel);
        boolean zZzc = false;
        IBinder iBinderZzr = null;
        while (parcel.dataPosition() < iZza) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                zZzc = zzbgm.zzc(parcel, i);
            } else if (i2 != 2) {
                zzbgm.zzb(parcel, i);
            } else {
                iBinderZzr = zzbgm.zzr(parcel, i);
            }
        }
        zzbgm.zzaf(parcel, iZza);
        return new PublisherAdViewOptions(zZzc, iBinderZzr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PublisherAdViewOptions[] newArray(int i) {
        return new PublisherAdViewOptions[i];
    }
}
