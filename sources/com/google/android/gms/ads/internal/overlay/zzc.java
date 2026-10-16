package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzc extends zzbgl {
    public static final Parcelable.Creator<zzc> CREATOR = new zzb();
    public final String zza;
    public final String zzb;
    public final String zzc;
    public final String zzd;
    public final String zze;
    public final Intent zzf;
    private String zzg;
    private String zzh;

    public zzc(Intent intent) {
        this(null, null, null, null, null, null, null, intent);
    }

    public zzc(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this(str, str2, str3, str4, str5, str6, str7, null);
    }

    public zzc(String str, String str2, String str3, String str4, String str5, String str6, String str7, Intent intent) {
        this.zzg = str;
        this.zza = str2;
        this.zzb = str3;
        this.zzc = str4;
        this.zzd = str5;
        this.zze = str6;
        this.zzh = str7;
        this.zzf = intent;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zzg, false);
        zzbgo.zza(parcel, 3, this.zza, false);
        zzbgo.zza(parcel, 4, this.zzb, false);
        zzbgo.zza(parcel, 5, this.zzc, false);
        zzbgo.zza(parcel, 6, this.zzd, false);
        zzbgo.zza(parcel, 7, this.zze, false);
        zzbgo.zza(parcel, 8, this.zzh, false);
        zzbgo.zza(parcel, 9, this.zzf, i, false);
        zzbgo.zza(parcel, iZza);
    }
}
