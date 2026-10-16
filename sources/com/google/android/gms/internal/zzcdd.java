package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class zzcdd extends zzbgl {
    public static final Parcelable.Creator<zzcdd> CREATOR = new zzcde();
    private int zza;
    private String zzb;
    private String zzc;

    zzcdd(int i, String str, String str2) {
        this.zza = i;
        this.zzb = str;
        this.zzc = str2;
    }

    public zzcdd(String str, String str2) {
        this(1, str, str2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zza);
        zzbgo.zza(parcel, 2, this.zzb, false);
        zzbgo.zza(parcel, 3, this.zzc, false);
        zzbgo.zza(parcel, iZza);
    }
}
