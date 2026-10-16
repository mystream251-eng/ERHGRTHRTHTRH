package com.google.android.gms.internal;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzft {
    private final String zza;
    private final JSONObject zzb;
    private final String zzc;
    private final String zzd;
    private final boolean zze;
    private final boolean zzf;

    public zzft(String str, zzala zzalaVar, String str2, JSONObject jSONObject, boolean z, boolean z2) {
        this.zzd = zzalaVar.zza;
        this.zzb = jSONObject;
        this.zzc = str;
        this.zza = str2;
        this.zze = z;
        this.zzf = z2;
    }

    public final String zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzd;
    }

    public final JSONObject zzc() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zzc;
    }

    public final boolean zze() {
        return this.zze;
    }

    public final boolean zzf() {
        return this.zzf;
    }
}
