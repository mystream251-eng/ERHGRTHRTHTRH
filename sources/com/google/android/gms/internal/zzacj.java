package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzacj extends zzbgl {
    public static final Parcelable.Creator<zzacj> CREATOR = new zzack();
    public final String zza;
    public final zzagd zzaa;
    public final List<String> zzab;
    public final List<String> zzac;
    public final boolean zzad;
    public final zzacl zzae;
    public final boolean zzaf;
    public String zzag;
    public final List<String> zzah;
    public final boolean zzai;
    public final String zzaj;
    public final zzagn zzak;
    public final String zzal;
    public final boolean zzam;
    public final boolean zzan;
    public final boolean zzao;
    public final int zzap;
    private zzacf zzaq;
    private int zzar;
    private zzacv zzas;
    private Bundle zzat;
    public String zzb;
    public final List<String> zzc;
    public final int zzd;
    public final List<String> zze;
    public final long zzf;
    public final boolean zzg;
    public final long zzh;
    public final List<String> zzi;
    public final long zzj;
    public final int zzk;
    public final String zzl;
    public final long zzm;
    public final String zzn;
    public final boolean zzo;
    public final String zzp;
    public final String zzq;
    public final boolean zzr;
    public final boolean zzs;
    public final boolean zzt;
    public final boolean zzu;
    public final boolean zzv;
    public String zzw;
    public final String zzx;
    public final boolean zzy;
    public final boolean zzz;

    public zzacj(int i) {
        this(19, null, null, null, i, null, -1L, false, -1L, null, -1L, -1, null, -1L, null, false, null, null, false, false, false, true, false, null, null, null, false, false, null, null, null, false, null, false, null, null, false, null, null, null, true, false, null, false, 0);
    }

    public zzacj(int i, long j) {
        this(19, null, null, null, i, null, -1L, false, -1L, null, j, -1, null, -1L, null, false, null, null, false, false, false, true, false, null, null, null, false, false, null, null, null, false, null, false, null, null, false, null, null, null, true, false, null, false, 0);
    }

    zzacj(int i, String str, String str2, List<String> list, int i2, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i3, String str3, long j4, String str4, boolean z2, String str5, String str6, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, zzacv zzacvVar, String str7, String str8, boolean z8, boolean z9, zzagd zzagdVar, List<String> list4, List<String> list5, boolean z10, zzacl zzaclVar, boolean z11, String str9, List<String> list6, boolean z12, String str10, zzagn zzagnVar, String str11, boolean z13, boolean z14, Bundle bundle, boolean z15, int i4) {
        zzadj zzadjVarZza;
        this.zzar = i;
        this.zza = str;
        this.zzb = str2;
        this.zzc = list != null ? Collections.unmodifiableList(list) : null;
        this.zzd = i2;
        this.zze = list2 != null ? Collections.unmodifiableList(list2) : null;
        this.zzf = j;
        this.zzg = z;
        this.zzh = j2;
        this.zzi = list3 != null ? Collections.unmodifiableList(list3) : null;
        this.zzj = j3;
        this.zzk = i3;
        this.zzl = str3;
        this.zzm = j4;
        this.zzn = str4;
        this.zzo = z2;
        this.zzp = str5;
        this.zzq = str6;
        this.zzr = z3;
        this.zzs = z4;
        this.zzt = z5;
        this.zzu = z6;
        this.zzam = z13;
        this.zzv = z7;
        this.zzas = zzacvVar;
        this.zzw = str7;
        this.zzx = str8;
        if (this.zzb == null && zzacvVar != null && (zzadjVarZza = zzacvVar.zza(zzadj.CREATOR)) != null && !TextUtils.isEmpty(zzadjVarZza.zza)) {
            this.zzb = zzadjVarZza.zza;
        }
        this.zzy = z8;
        this.zzz = z9;
        this.zzaa = zzagdVar;
        this.zzab = list4;
        this.zzac = list5;
        this.zzad = z10;
        this.zzae = zzaclVar;
        this.zzaf = z11;
        this.zzag = str9;
        this.zzah = list6;
        this.zzai = z12;
        this.zzaj = str10;
        this.zzak = zzagnVar;
        this.zzal = str11;
        this.zzan = z14;
        this.zzat = bundle;
        this.zzao = z15;
        this.zzap = i4;
    }

    public zzacj(zzacf zzacfVar, String str, String str2, List<String> list, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i, String str3, long j4, String str4, String str5, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str6, boolean z7, boolean z8, zzagd zzagdVar, List<String> list4, List<String> list5, boolean z9, zzacl zzaclVar, boolean z10, String str7, List<String> list6, boolean z11, String str8, zzagn zzagnVar, String str9, boolean z12, boolean z13, boolean z14, int i2) {
        this(19, str, str2, list, -2, list2, j, z, -1L, list3, j3, i, str3, j4, str4, false, null, str5, z2, z3, z4, z5, false, null, null, str6, z7, z8, zzagdVar, list4, list5, z9, zzaclVar, z10, str7, list6, z11, str8, zzagnVar, str9, z12, z13, null, z14, i2);
        this.zzaq = zzacfVar;
    }

    public zzacj(zzacf zzacfVar, String str, String str2, List<String> list, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i, String str3, long j4, String str4, boolean z2, String str5, String str6, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str7, boolean z8, boolean z9, zzagd zzagdVar, List<String> list4, List<String> list5, boolean z10, zzacl zzaclVar, boolean z11, String str8, List<String> list6, boolean z12, String str9, zzagn zzagnVar, String str10, boolean z13, boolean z14, boolean z15, int i2) {
        this(19, str, str2, list, -2, list2, j, z, j2, list3, j3, i, str3, j4, str4, z2, str5, str6, z3, z4, z5, z6, z7, null, null, str7, z8, z9, zzagdVar, list4, list5, z10, zzaclVar, z11, str8, list6, z12, str9, zzagnVar, str10, z13, z14, null, z15, 0);
        this.zzaq = zzacfVar;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        zzacf zzacfVar = this.zzaq;
        if (zzacfVar != null && zzacfVar.zza >= 9 && !TextUtils.isEmpty(this.zzb)) {
            this.zzas = new zzacv((zzbgp) new zzadj(this.zzb));
            this.zzb = null;
        }
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zzar);
        zzbgo.zza(parcel, 2, this.zza, false);
        zzbgo.zza(parcel, 3, this.zzb, false);
        zzbgo.zzb(parcel, 4, this.zzc, false);
        zzbgo.zza(parcel, 5, this.zzd);
        zzbgo.zzb(parcel, 6, this.zze, false);
        zzbgo.zza(parcel, 7, this.zzf);
        zzbgo.zza(parcel, 8, this.zzg);
        zzbgo.zza(parcel, 9, this.zzh);
        zzbgo.zzb(parcel, 10, this.zzi, false);
        zzbgo.zza(parcel, 11, this.zzj);
        zzbgo.zza(parcel, 12, this.zzk);
        zzbgo.zza(parcel, 13, this.zzl, false);
        zzbgo.zza(parcel, 14, this.zzm);
        zzbgo.zza(parcel, 15, this.zzn, false);
        zzbgo.zza(parcel, 18, this.zzo);
        zzbgo.zza(parcel, 19, this.zzp, false);
        zzbgo.zza(parcel, 21, this.zzq, false);
        zzbgo.zza(parcel, 22, this.zzr);
        zzbgo.zza(parcel, 23, this.zzs);
        zzbgo.zza(parcel, 24, this.zzt);
        zzbgo.zza(parcel, 25, this.zzu);
        zzbgo.zza(parcel, 26, this.zzv);
        zzbgo.zza(parcel, 28, this.zzas, i, false);
        zzbgo.zza(parcel, 29, this.zzw, false);
        zzbgo.zza(parcel, 30, this.zzx, false);
        zzbgo.zza(parcel, 31, this.zzy);
        zzbgo.zza(parcel, 32, this.zzz);
        zzbgo.zza(parcel, 33, this.zzaa, i, false);
        zzbgo.zzb(parcel, 34, this.zzab, false);
        zzbgo.zzb(parcel, 35, this.zzac, false);
        zzbgo.zza(parcel, 36, this.zzad);
        zzbgo.zza(parcel, 37, this.zzae, i, false);
        zzbgo.zza(parcel, 38, this.zzaf);
        zzbgo.zza(parcel, 39, this.zzag, false);
        zzbgo.zzb(parcel, 40, this.zzah, false);
        zzbgo.zza(parcel, 42, this.zzai);
        zzbgo.zza(parcel, 43, this.zzaj, false);
        zzbgo.zza(parcel, 44, this.zzak, i, false);
        zzbgo.zza(parcel, 45, this.zzal, false);
        zzbgo.zza(parcel, 46, this.zzam);
        zzbgo.zza(parcel, 47, this.zzan);
        zzbgo.zza(parcel, 48, this.zzat, false);
        zzbgo.zza(parcel, 49, this.zzao);
        zzbgo.zza(parcel, 50, this.zzap);
        zzbgo.zza(parcel, iZza);
    }
}
