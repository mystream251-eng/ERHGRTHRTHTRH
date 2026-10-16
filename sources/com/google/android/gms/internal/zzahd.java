package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzahd {
    public final zzkk zza;
    public final String zzaa;
    public final zzpx zzab;
    public boolean zzac;
    public boolean zzad;
    public boolean zzae;
    public boolean zzaf;
    public final List<String> zzag;
    public final zziu zzah;
    public final boolean zzai;
    public final boolean zzaj;
    private zzacl zzak;
    private long zzal;
    private long zzam;
    public final zzaof zzb;
    public final List<String> zzc;
    public final int zzd;
    public final List<String> zze;
    public final List<String> zzf;
    public final int zzg;
    public final long zzh;
    public final String zzi;
    public final JSONObject zzj;
    public final boolean zzk;
    public boolean zzl;
    public final boolean zzm;
    public final zzvp zzn;
    public final zzwi zzo;
    public final String zzp;
    public final zzvq zzq;
    public final zzvs zzr;
    public final String zzs;
    public final zzko zzt;
    public final zzagd zzu;
    public final List<String> zzv;
    public final List<String> zzw;
    public final long zzx;
    public final long zzy;
    public final String zzz;

    public zzahd(zzahe zzaheVar, zzaof zzaofVar, zzvp zzvpVar, zzwi zzwiVar, String str, zzvs zzvsVar, zzpx zzpxVar, String str2) {
        this(zzaheVar.zza.zzc, null, zzaheVar.zzb.zzc, zzaheVar.zze, zzaheVar.zzb.zze, zzaheVar.zzb.zzi, zzaheVar.zzb.zzk, zzaheVar.zzb.zzj, zzaheVar.zza.zzi, zzaheVar.zzb.zzg, null, null, null, zzaheVar.zzc, null, zzaheVar.zzb.zzh, zzaheVar.zzd, zzaheVar.zzb.zzf, zzaheVar.zzf, zzaheVar.zzg, zzaheVar.zzb.zzn, zzaheVar.zzh, null, zzaheVar.zzb.zzaa, zzaheVar.zzb.zzab, zzaheVar.zzb.zzab, zzaheVar.zzb.zzad, zzaheVar.zzb.zzae, null, zzaheVar.zzb.zzah, zzaheVar.zzb.zzal, zzaheVar.zzi, zzaheVar.zzb.zzao, zzaheVar.zzj);
    }

    public zzahd(zzkk zzkkVar, zzaof zzaofVar, List<String> list, int i, List<String> list2, List<String> list3, int i2, long j, String str, boolean z, zzvp zzvpVar, zzwi zzwiVar, String str2, zzvq zzvqVar, zzvs zzvsVar, long j2, zzko zzkoVar, long j3, long j4, long j5, String str3, JSONObject jSONObject, zzpx zzpxVar, zzagd zzagdVar, List<String> list4, List<String> list5, boolean z2, zzacl zzaclVar, String str4, List<String> list6, String str5, zziu zziuVar, boolean z3, boolean z4) {
        this.zzac = false;
        this.zzad = false;
        this.zzae = false;
        this.zzaf = false;
        this.zza = zzkkVar;
        this.zzb = zzaofVar;
        this.zzc = zza(list);
        this.zzd = i;
        this.zze = zza(list2);
        this.zzf = zza(list3);
        this.zzg = i2;
        this.zzh = j;
        this.zzi = str;
        this.zzm = z;
        this.zzn = zzvpVar;
        this.zzo = zzwiVar;
        this.zzp = str2;
        this.zzq = zzvqVar;
        this.zzr = zzvsVar;
        this.zzal = j2;
        this.zzt = zzkoVar;
        this.zzam = j3;
        this.zzx = j4;
        this.zzy = j5;
        this.zzz = str3;
        this.zzj = jSONObject;
        this.zzab = zzpxVar;
        this.zzu = zzagdVar;
        this.zzv = zza(list4);
        this.zzw = zza(list5);
        this.zzk = z2;
        this.zzak = zzaclVar;
        this.zzs = str4;
        this.zzag = zza(list6);
        this.zzaa = str5;
        this.zzah = zziuVar;
        this.zzai = z3;
        this.zzaj = z4;
    }

    private static <T> List<T> zza(List<T> list) {
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public final boolean zza() {
        zzaof zzaofVar = this.zzb;
        if (zzaofVar == null || zzaofVar.zzv() == null) {
            return false;
        }
        return this.zzb.zzv().zzb();
    }
}
