package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzap extends zzbgl {
    public static final Parcelable.Creator<zzap> CREATOR = new zzaq();
    public final boolean zza;
    public final boolean zzb;
    public final boolean zzc;
    public final float zzd;
    public final int zze;
    public final boolean zzf;
    public final boolean zzg;
    private String zzh;

    zzap(boolean z, boolean z2, String str, boolean z3, float f, int i, boolean z4, boolean z5) {
        this.zza = z;
        this.zzb = z2;
        this.zzh = str;
        this.zzc = z3;
        this.zzd = f;
        this.zze = i;
        this.zzf = z4;
        this.zzg = z5;
    }

    public zzap(boolean z, boolean z2, boolean z3, float f, int i, boolean z4, boolean z5) {
        this(z, z2, null, z3, f, i, z4, z5);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza);
        zzbgo.zza(parcel, 3, this.zzb);
        zzbgo.zza(parcel, 4, this.zzh, false);
        zzbgo.zza(parcel, 5, this.zzc);
        zzbgo.zza(parcel, 6, this.zzd);
        zzbgo.zza(parcel, 7, this.zze);
        zzbgo.zza(parcel, 8, this.zzf);
        zzbgo.zza(parcel, 9, this.zzg);
        zzbgo.zza(parcel, iZza);
    }
}
