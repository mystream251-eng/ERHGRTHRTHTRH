package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzadj extends zzbgl {
    public static final Parcelable.Creator<zzadj> CREATOR = new zzadk();
    String zza;

    public zzadj(String str) {
        this.zza = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza, false);
        zzbgo.zza(parcel, iZza);
    }
}
