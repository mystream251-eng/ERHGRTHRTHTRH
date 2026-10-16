package com.google.android.gms.internal;

import android.content.SharedPreferences;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public abstract class zzny<T> {
    private final int zza;
    private final String zzb;
    private final T zzc;

    private zzny(int i, String str, T t) {
        this.zza = i;
        this.zzb = str;
        this.zzc = t;
        zzlc.zze().zza(this);
    }

    /* synthetic */ zzny(int i, String str, Object obj, zznz zznzVar) {
        this(i, str, obj);
    }

    public static zzny<String> zza(int i, String str) {
        zzny<String> zznyVarZza = zza(i, str, (String) null);
        zzlc.zze().zzb(zznyVarZza);
        return zznyVarZza;
    }

    public static zzny<Float> zza(int i, String str, float f) {
        return new zzoc(i, str, Float.valueOf(0.0f));
    }

    public static zzny<Integer> zza(int i, String str, int i2) {
        return new zzoa(i, str, Integer.valueOf(i2));
    }

    public static zzny<Long> zza(int i, String str, long j) {
        return new zzob(i, str, Long.valueOf(j));
    }

    public static zzny<Boolean> zza(int i, String str, Boolean bool) {
        return new zznz(i, str, bool);
    }

    public static zzny<String> zza(int i, String str, String str2) {
        return new zzod(i, str, str2);
    }

    public static zzny<String> zzb(int i, String str) {
        zzny<String> zznyVarZza = zza(i, str, (String) null);
        zzlc.zze().zzc(zznyVarZza);
        return zznyVarZza;
    }

    protected abstract T zza(SharedPreferences sharedPreferences);

    protected abstract T zza(JSONObject jSONObject);

    public final String zza() {
        return this.zzb;
    }

    public abstract void zza(SharedPreferences.Editor editor, T t);

    public final T zzb() {
        return this.zzc;
    }

    public final int zzc() {
        return this.zza;
    }
}
