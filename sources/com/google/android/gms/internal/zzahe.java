package com.google.android.gms.internal;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzahe {
    public final zzacf zza;
    public final zzacj zzb;
    public final zzvq zzc;
    public final zzko zzd;
    public final int zze;
    public final long zzf;
    public final long zzg;
    public final JSONObject zzh;
    public final zziu zzi;
    public final boolean zzj;

    public zzahe(zzacf zzacfVar, zzacj zzacjVar, zzvq zzvqVar, zzko zzkoVar, int i, long j, long j2, JSONObject jSONObject, zziu zziuVar, Boolean bool) {
        this.zza = zzacfVar;
        this.zzb = zzacjVar;
        this.zzc = zzvqVar;
        this.zzd = zzkoVar;
        this.zze = i;
        this.zzf = j;
        this.zzg = j2;
        this.zzh = jSONObject;
        this.zzi = zziuVar;
        this.zzj = bool != null ? bool.booleanValue() : zzakh.zza(zzacfVar.zzc);
    }

    public zzahe(zzacf zzacfVar, zzacj zzacjVar, zzvq zzvqVar, zzko zzkoVar, int i, long j, long j2, JSONObject jSONObject, zziz zzizVar) {
        this.zza = zzacfVar;
        this.zzb = zzacjVar;
        this.zzc = null;
        this.zzd = null;
        this.zze = i;
        this.zzf = j;
        this.zzg = j2;
        this.zzh = null;
        this.zzi = new zziu(zzizVar);
        this.zzj = false;
    }
}
