package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.zzbt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaef extends zzaeh {
    private final Object zza = new Object();
    private final Context zzb;
    private SharedPreferences zzc;
    private final zzux<JSONObject, JSONObject> zzd;

    public zzaef(Context context, zzux<JSONObject, JSONObject> zzuxVar) {
        this.zzb = context.getApplicationContext();
        this.zzd = zzuxVar;
    }

    @Override // com.google.android.gms.internal.zzaeh
    public final zzalt<Void> zza() {
        synchronized (this.zza) {
            if (this.zzc == null) {
                this.zzc = this.zzb.getSharedPreferences("google_ads_flags_meta", 0);
            }
        }
        if (zzbt.zzl().zza() - this.zzc.getLong("js_last_update", 0L) < ((Long) zzlc.zzf().zza(zzoi.zzen)).longValue()) {
            return zzali.zza((Object) null);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("js", zzala.zza().zza);
            jSONObject.put("mf", zzlc.zzf().zza(zzoi.zzeo));
            jSONObject.put("cl", "189396757");
            jSONObject.put("rapid_rc", "dev");
            jSONObject.put("rapid_rollup", "HEAD");
            return zzali.zza(this.zzd.zzb(jSONObject), new zzale(this) { // from class: com.google.android.gms.internal.zzaeg
                private final zzaef zza;

                {
                    this.zza = this;
                }

                @Override // com.google.android.gms.internal.zzale
                public final Object zza(Object obj) {
                    return this.zza.zza((JSONObject) obj);
                }
            }, zzaly.zzb);
        } catch (JSONException e) {
            zzahw.zzb("Unable to populate SDK Core Constants parameters.", e);
            return zzali.zza((Object) null);
        }
    }

    final /* synthetic */ Void zza(JSONObject jSONObject) {
        zzoi.zza(this.zzb, 1, jSONObject);
        this.zzc.edit().putLong("js_last_update", zzbt.zzl().zza()).apply();
        return null;
    }
}
