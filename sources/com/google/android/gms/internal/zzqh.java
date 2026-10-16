package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.formats.NativeAdOptions;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzqh extends zzbgl {
    public static final Parcelable.Creator<zzqh> CREATOR = new zzqi();
    public final int zza;
    public final boolean zzb;
    public final int zzc;
    public final boolean zzd;
    public final int zze;
    public final zzns zzf;

    public zzqh(int i, boolean z, int i2, boolean z2, int i3, zzns zznsVar) {
        this.zza = i;
        this.zzb = z;
        this.zzc = i2;
        this.zzd = z2;
        this.zze = i3;
        this.zzf = zznsVar;
    }

    public zzqh(NativeAdOptions nativeAdOptions) {
        this(3, nativeAdOptions.shouldReturnUrlsForImageAssets(), nativeAdOptions.getImageOrientation(), nativeAdOptions.shouldRequestMultipleImages(), nativeAdOptions.getAdChoicesPlacement(), nativeAdOptions.getVideoOptions() != null ? new zzns(nativeAdOptions.getVideoOptions()) : null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zza);
        zzbgo.zza(parcel, 2, this.zzb);
        zzbgo.zza(parcel, 3, this.zzc);
        zzbgo.zza(parcel, 4, this.zzd);
        zzbgo.zza(parcel, 5, this.zze);
        zzbgo.zza(parcel, 6, this.zzf, i, false);
        zzbgo.zza(parcel, iZza);
    }
}
