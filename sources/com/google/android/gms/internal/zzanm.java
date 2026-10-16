package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzanm {
    public final String zza;
    private boolean zzb;
    private int zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private boolean zzg;

    public zzanm(String str) {
        JSONObject jSONObject = null;
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException unused) {
            }
        }
        this.zzb = zza(jSONObject, "aggressive_media_codec_release", zzoi.zzx);
        this.zza = zzc(jSONObject, "exo_player_version", zzoi.zzf);
        this.zzc = zzb(jSONObject, "exo_cache_buffer_size", zzoi.zzl);
        this.zzd = zzb(jSONObject, "exo_connect_timeout_millis", zzoi.zzg);
        this.zze = zzb(jSONObject, "exo_read_timeout_millis", zzoi.zzh);
        this.zzf = zzb(jSONObject, "load_check_interval_bytes", zzoi.zzi);
        this.zzg = zza(jSONObject, "use_cache_data_source", zzoi.zzfx);
    }

    private static boolean zza(JSONObject jSONObject, String str, zzny<Boolean> zznyVar) {
        if (jSONObject != null) {
            try {
                return jSONObject.getBoolean(str);
            } catch (JSONException unused) {
            }
        }
        return ((Boolean) zzlc.zzf().zza(zznyVar)).booleanValue();
    }

    private static int zzb(JSONObject jSONObject, String str, zzny<Integer> zznyVar) {
        if (jSONObject != null) {
            try {
                return jSONObject.getInt(str);
            } catch (JSONException unused) {
            }
        }
        return ((Integer) zzlc.zzf().zza(zznyVar)).intValue();
    }

    private static String zzc(JSONObject jSONObject, String str, zzny<String> zznyVar) {
        if (jSONObject != null) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException unused) {
            }
        }
        return (String) zzlc.zzf().zza(zznyVar);
    }
}
