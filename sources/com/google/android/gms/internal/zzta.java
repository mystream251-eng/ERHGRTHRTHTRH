package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzta extends zzbgl {
    public static final Parcelable.Creator<zzta> CREATOR = new zztb();
    public final boolean zza;
    public final String zzb;
    public final int zzc;
    public final byte[] zzd;
    public final String[] zze;
    public final String[] zzf;
    public final boolean zzg;
    public final long zzh;

    zzta(boolean z, String str, int i, byte[] bArr, String[] strArr, String[] strArr2, boolean z2, long j) {
        this.zza = z;
        this.zzb = str;
        this.zzc = i;
        this.zzd = bArr;
        this.zze = strArr;
        this.zzf = strArr2;
        this.zzg = z2;
        this.zzh = j;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zza);
        zzbgo.zza(parcel, 2, this.zzb, false);
        zzbgo.zza(parcel, 3, this.zzc);
        zzbgo.zza(parcel, 4, this.zzd, false);
        zzbgo.zza(parcel, 5, this.zze, false);
        zzbgo.zza(parcel, 6, this.zzf, false);
        zzbgo.zza(parcel, 7, this.zzg);
        zzbgo.zza(parcel, 8, this.zzh);
        zzbgo.zza(parcel, iZza);
    }
}
