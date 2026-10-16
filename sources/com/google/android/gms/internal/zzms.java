package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzms extends zzbgl {
    public static final Parcelable.Creator<zzms> CREATOR = new zzmt();
    public final int zza;

    public zzms(int i) {
        this.zza = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza);
        zzbgo.zza(parcel, iZza);
    }
}
