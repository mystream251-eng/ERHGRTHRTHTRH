package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import java.util.List;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzacg {
    public final Bundle zza;
    public final float zzaa;
    public final boolean zzab;
    public final int zzac;
    public final int zzad;
    public final boolean zzae;
    public final boolean zzaf;
    public final Future<String> zzag;
    public final String zzah;
    public final boolean zzai;
    public final int zzaj;
    public final Bundle zzak;
    public final String zzal;
    public final zzms zzam;
    public final boolean zzan;
    public final Bundle zzao;
    public final boolean zzap;
    public final Future<String> zzaq;
    public final List<Integer> zzar;
    public final String zzas;
    public final int zzat;
    public final boolean zzau;
    public final boolean zzav;
    public final boolean zzaw;
    public final zzkk zzb;
    public final zzko zzc;
    public final String zzd;
    public final ApplicationInfo zze;
    public final PackageInfo zzf;
    public final String zzg;
    public final String zzh;
    public final Bundle zzi;
    public final zzala zzj;
    public final int zzk;
    public final List<String> zzl;
    public final List<String> zzm;
    public final List<String> zzn;
    public final Bundle zzo;
    public final boolean zzp;
    public final int zzq;
    public final int zzr;
    public final float zzs;
    public final String zzt;
    public final long zzu;
    public final String zzv;
    public final List<String> zzw;
    public final String zzx;
    public final zzqh zzy;
    public final String zzz;

    public zzacg(Bundle bundle, zzkk zzkkVar, zzko zzkoVar, String str, ApplicationInfo applicationInfo, PackageInfo packageInfo, String str2, String str3, zzala zzalaVar, Bundle bundle2, List<String> list, List<String> list2, Bundle bundle3, boolean z, int i, int i2, float f, String str4, long j, String str5, List<String> list3, String str6, zzqh zzqhVar, String str7, float f2, boolean z2, int i3, int i4, boolean z3, boolean z4, Future<String> future, String str8, boolean z5, int i5, Bundle bundle4, String str9, zzms zzmsVar, boolean z6, Bundle bundle5, boolean z7, Future<String> future2, List<Integer> list4, String str10, List<String> list5, int i6, boolean z8, boolean z9, boolean z10) {
        List<String> list6;
        this.zza = bundle;
        this.zzb = zzkkVar;
        this.zzc = zzkoVar;
        this.zzd = str;
        this.zze = applicationInfo;
        this.zzf = packageInfo;
        this.zzg = str2;
        this.zzh = str3;
        this.zzj = zzalaVar;
        this.zzi = bundle2;
        this.zzp = z;
        this.zzq = i;
        this.zzr = i2;
        this.zzs = f;
        if (list == null || list.size() <= 0) {
            this.zzk = 0;
            list6 = null;
            this.zzl = null;
        } else {
            this.zzk = 3;
            this.zzl = list;
            list6 = list2;
        }
        this.zzm = list6;
        this.zzo = bundle3;
        this.zzt = str4;
        this.zzu = j;
        this.zzv = str5;
        this.zzw = list3;
        this.zzx = str6;
        this.zzy = zzqhVar;
        this.zzz = str7;
        this.zzaa = f2;
        this.zzab = z2;
        this.zzac = i3;
        this.zzad = i4;
        this.zzae = z3;
        this.zzaf = z4;
        this.zzag = future;
        this.zzah = str8;
        this.zzai = z5;
        this.zzaj = i5;
        this.zzak = bundle4;
        this.zzal = str9;
        this.zzam = zzmsVar;
        this.zzan = z6;
        this.zzao = bundle5;
        this.zzap = z7;
        this.zzaq = future2;
        this.zzar = list4;
        this.zzas = str10;
        this.zzn = list5;
        this.zzat = i6;
        this.zzau = z8;
        this.zzav = z9;
        this.zzaw = z10;
    }
}
