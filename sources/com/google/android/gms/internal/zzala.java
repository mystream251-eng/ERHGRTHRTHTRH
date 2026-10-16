package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.dotscreen.tv.common.search.LocalSearchProvider;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzala extends zzbgl {
    public static final Parcelable.Creator<zzala> CREATOR = new zzalb();
    public String zza;
    public int zzb;
    public int zzc;
    public boolean zzd;
    public boolean zze;

    public zzala(int i, int i2, boolean z) {
        this(i, i2, z, false, false);
    }

    public zzala(int i, int i2, boolean z, boolean z2) {
        this(12210000, i2, true, false, z2);
    }

    private zzala(int i, int i2, boolean z, boolean z2, boolean z3) {
        String str = z ? LocalSearchProvider.NO_LIVE_CONTENTS : LocalSearchProvider.LIVE_CONTENTS;
        StringBuilder sb = new StringBuilder(str.length() + 36);
        sb.append("afma-sdk-a-v");
        sb.append(i);
        sb.append(".");
        sb.append(i2);
        sb.append(".");
        sb.append(str);
        this(sb.toString(), i, i2, z, z3);
    }

    zzala(String str, int i, int i2, boolean z, boolean z2) {
        this.zza = str;
        this.zzb = i;
        this.zzc = i2;
        this.zzd = z;
        this.zze = z2;
    }

    public static zzala zza() {
        return new zzala(12210278, 12210278, true);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza, false);
        zzbgo.zza(parcel, 3, this.zzb);
        zzbgo.zza(parcel, 4, this.zzc);
        zzbgo.zza(parcel, 5, this.zzd);
        zzbgo.zza(parcel, 6, this.zze);
        zzbgo.zza(parcel, iZza);
    }
}
