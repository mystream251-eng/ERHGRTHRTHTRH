package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.search.SearchAdRequest;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzno extends zzbgl {
    public static final Parcelable.Creator<zzno> CREATOR = new zznp();
    public final String zza;

    public zzno(SearchAdRequest searchAdRequest) {
        this.zza = searchAdRequest.getQuery();
    }

    zzno(String str) {
        this.zza = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 15, this.zza, false);
        zzbgo.zza(parcel, iZza);
    }
}
