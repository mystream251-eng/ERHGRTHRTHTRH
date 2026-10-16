package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzafi extends zzbgl {
    public static final Parcelable.Creator<zzafi> CREATOR = new zzafj();
    public final zzkk zza;
    public final String zzb;

    public zzafi(zzkk zzkkVar, String str) {
        this.zza = zzkkVar;
        this.zzb = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza, i, false);
        zzbgo.zza(parcel, 3, this.zzb, false);
        zzbgo.zza(parcel, iZza);
    }
}
