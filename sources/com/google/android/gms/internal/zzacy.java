package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzacy extends zzbgl {
    public static final Parcelable.Creator<zzacy> CREATOR = new zzacz();
    private Bundle zza;
    private zzala zzb;
    private ApplicationInfo zzc;
    private String zzd;
    private List<String> zze;
    private PackageInfo zzf;
    private String zzg;
    private boolean zzh;
    private String zzi;

    public zzacy(Bundle bundle, zzala zzalaVar, ApplicationInfo applicationInfo, String str, List<String> list, PackageInfo packageInfo, String str2, boolean z, String str3) {
        this.zza = bundle;
        this.zzb = zzalaVar;
        this.zzd = str;
        this.zzc = applicationInfo;
        this.zze = list;
        this.zzf = packageInfo;
        this.zzg = str2;
        this.zzh = z;
        this.zzi = str3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zza, false);
        zzbgo.zza(parcel, 2, this.zzb, i, false);
        zzbgo.zza(parcel, 3, this.zzc, i, false);
        zzbgo.zza(parcel, 4, this.zzd, false);
        zzbgo.zzb(parcel, 5, this.zze, false);
        zzbgo.zza(parcel, 6, this.zzf, i, false);
        zzbgo.zza(parcel, 7, this.zzg, false);
        zzbgo.zza(parcel, 8, this.zzh);
        zzbgo.zza(parcel, 9, this.zzi, false);
        zzbgo.zza(parcel, iZza);
    }
}
