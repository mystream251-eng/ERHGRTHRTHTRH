package com.google.android.gms.internal;

import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzadz {
    private final List<String> zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final boolean zze;
    private final String zzf;
    private final String zzg;
    private String zzh;
    private final int zzi;
    private final boolean zzj;
    private final JSONObject zzk;

    public zzadz(int i, Map<String, String> map) {
        this.zzh = map.get(ImagesContract.URL);
        this.zzb = map.get("base_uri");
        this.zzc = map.get("post_parameters");
        this.zze = zzb(map.get("drt_include"));
        this.zzf = map.get("request_id");
        this.zzd = map.get("type");
        this.zza = zzc(map.get("errors"));
        this.zzi = i;
        this.zzg = map.get("fetched_ad");
        this.zzj = zzb(map.get("render_test_ad_label"));
        this.zzk = new JSONObject();
    }

    public zzadz(JSONObject jSONObject) {
        this.zzh = jSONObject.optString(ImagesContract.URL);
        this.zzb = jSONObject.optString("base_uri");
        this.zzc = jSONObject.optString("post_parameters");
        this.zze = zzb(jSONObject.optString("drt_include"));
        this.zzf = jSONObject.optString("request_id");
        this.zzd = jSONObject.optString("type");
        this.zza = zzc(jSONObject.optString("errors"));
        this.zzi = jSONObject.optInt("valid", 0) == 1 ? -2 : 1;
        this.zzg = jSONObject.optString("fetched_ad");
        this.zzj = jSONObject.optBoolean("render_test_ad_label");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("preprocessor_flags");
        this.zzk = jSONObjectOptJSONObject == null ? new JSONObject() : jSONObjectOptJSONObject;
    }

    private static boolean zzb(String str) {
        if (str != null) {
            return str.equals(LocalSearchProvider.LIVE_CONTENTS) || str.equals("true");
        }
        return false;
    }

    private static List<String> zzc(String str) {
        if (str == null) {
            return null;
        }
        return Arrays.asList(str.split(","));
    }

    public final int zza() {
        return this.zzi;
    }

    public final void zza(String str) {
        this.zzh = str;
    }

    public final List<String> zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zzc;
    }

    public final String zze() {
        return this.zzh;
    }

    public final String zzf() {
        return this.zzd;
    }

    public final boolean zzg() {
        return this.zze;
    }

    public final String zzh() {
        return this.zzf;
    }

    public final String zzi() {
        return this.zzg;
    }

    public final boolean zzj() {
        return this.zzj;
    }
}
