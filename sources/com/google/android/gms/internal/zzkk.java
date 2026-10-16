package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzkk extends zzbgl {
    public static final Parcelable.Creator<zzkk> CREATOR = new zzkm();
    public final int zza;
    public final long zzb;
    public final Bundle zzc;
    public final int zzd;
    public final List<String> zze;
    public final boolean zzf;
    public final int zzg;
    public final boolean zzh;
    public final String zzi;
    public final zzno zzj;
    public final Location zzk;
    public final String zzl;
    public final Bundle zzm;
    public final Bundle zzn;
    public final List<String> zzo;
    public final String zzp;
    public final String zzq;
    public final boolean zzr;

    public zzkk(int i, long j, Bundle bundle, int i2, List<String> list, boolean z, int i3, boolean z2, String str, zzno zznoVar, Location location, String str2, Bundle bundle2, Bundle bundle3, List<String> list2, String str3, String str4, boolean z3) {
        this.zza = i;
        this.zzb = j;
        this.zzc = bundle == null ? new Bundle() : bundle;
        this.zzd = i2;
        this.zze = list;
        this.zzf = z;
        this.zzg = i3;
        this.zzh = z2;
        this.zzi = str;
        this.zzj = zznoVar;
        this.zzk = location;
        this.zzl = str2;
        this.zzm = bundle2 == null ? new Bundle() : bundle2;
        this.zzn = bundle3;
        this.zzo = list2;
        this.zzp = str3;
        this.zzq = str4;
        this.zzr = z3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzkk)) {
            return false;
        }
        zzkk zzkkVar = (zzkk) obj;
        return this.zza == zzkkVar.zza && this.zzb == zzkkVar.zzb && com.google.android.gms.common.internal.zzbg.zza(this.zzc, zzkkVar.zzc) && this.zzd == zzkkVar.zzd && com.google.android.gms.common.internal.zzbg.zza(this.zze, zzkkVar.zze) && this.zzf == zzkkVar.zzf && this.zzg == zzkkVar.zzg && this.zzh == zzkkVar.zzh && com.google.android.gms.common.internal.zzbg.zza(this.zzi, zzkkVar.zzi) && com.google.android.gms.common.internal.zzbg.zza(this.zzj, zzkkVar.zzj) && com.google.android.gms.common.internal.zzbg.zza(this.zzk, zzkkVar.zzk) && com.google.android.gms.common.internal.zzbg.zza(this.zzl, zzkkVar.zzl) && com.google.android.gms.common.internal.zzbg.zza(this.zzm, zzkkVar.zzm) && com.google.android.gms.common.internal.zzbg.zza(this.zzn, zzkkVar.zzn) && com.google.android.gms.common.internal.zzbg.zza(this.zzo, zzkkVar.zzo) && com.google.android.gms.common.internal.zzbg.zza(this.zzp, zzkkVar.zzp) && com.google.android.gms.common.internal.zzbg.zza(this.zzq, zzkkVar.zzq) && this.zzr == zzkkVar.zzr;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zza), Long.valueOf(this.zzb), this.zzc, Integer.valueOf(this.zzd), this.zze, Boolean.valueOf(this.zzf), Integer.valueOf(this.zzg), Boolean.valueOf(this.zzh), this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzo, this.zzp, this.zzq, Boolean.valueOf(this.zzr)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zza);
        zzbgo.zza(parcel, 2, this.zzb);
        zzbgo.zza(parcel, 3, this.zzc, false);
        zzbgo.zza(parcel, 4, this.zzd);
        zzbgo.zzb(parcel, 5, this.zze, false);
        zzbgo.zza(parcel, 6, this.zzf);
        zzbgo.zza(parcel, 7, this.zzg);
        zzbgo.zza(parcel, 8, this.zzh);
        zzbgo.zza(parcel, 9, this.zzi, false);
        zzbgo.zza(parcel, 10, this.zzj, i, false);
        zzbgo.zza(parcel, 11, this.zzk, i, false);
        zzbgo.zza(parcel, 12, this.zzl, false);
        zzbgo.zza(parcel, 13, this.zzm, false);
        zzbgo.zza(parcel, 14, this.zzn, false);
        zzbgo.zzb(parcel, 15, this.zzo, false);
        zzbgo.zza(parcel, 16, this.zzp, false);
        zzbgo.zza(parcel, 17, this.zzq, false);
        zzbgo.zza(parcel, 18, this.zzr);
        zzbgo.zza(parcel, iZza);
    }

    public final zzkk zza() {
        Bundle bundle = this.zzm.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle == null) {
            bundle = this.zzc;
            this.zzm.putBundle("com.google.ads.mediation.admob.AdMobAdapter", bundle);
        }
        return new zzkk(this.zza, this.zzb, bundle, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzo, this.zzp, this.zzq, this.zzr);
    }
}
