package com.google.android.gms.internal;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzahh {
    private final long zza;
    private String zze;
    private String zzf;
    private final List<String> zzb = new ArrayList();
    private final List<String> zzc = new ArrayList();
    private final Map<String, zzvq> zzd = new HashMap();
    private boolean zzg = false;

    public zzahh(String str, long j) {
        this.zzf = str;
        this.zza = j;
        zza(str);
    }

    private final void zza(String str) {
        JSONObject jSONObjectOptJSONObject;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(NotificationCompat.CATEGORY_STATUS, -1) != 1) {
                this.zzg = false;
                zzahw.zze("App settings could not be fetched successfully.");
                return;
            }
            this.zzg = true;
            this.zze = jSONObject.optString("app_id");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("ad_unit_id_settings");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                    String strOptString = jSONObject2.optString("format");
                    String strOptString2 = jSONObject2.optString("ad_unit_id");
                    if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                        if ("interstitial".equalsIgnoreCase(strOptString)) {
                            this.zzc.add(strOptString2);
                        } else if ("rewarded".equalsIgnoreCase(strOptString) && (jSONObjectOptJSONObject = jSONObject2.optJSONObject("mediation_config")) != null) {
                            this.zzd.put(strOptString2, new zzvq(jSONObjectOptJSONObject));
                        }
                    }
                }
            }
            zza(jSONObject);
        } catch (JSONException e) {
            zzahw.zzc("Exception occurred while processing app setting json", e);
            zzbt.zzi().zza(e, "AppSettings.parseAppSettingsJson");
        }
    }

    private final void zza(JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("persistable_banner_ad_unit_ids");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                this.zzb.add(jSONArrayOptJSONArray.optString(i));
            }
        }
    }

    public final long zza() {
        return this.zza;
    }

    public final boolean zzb() {
        return this.zzg;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zze;
    }

    public final Map<String, zzvq> zze() {
        return this.zzd;
    }
}
