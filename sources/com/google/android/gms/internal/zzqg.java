package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzqg implements zzt<Object> {
    private /* synthetic */ zzaan zza;
    private /* synthetic */ zzqb zzb;

    zzqg(zzqb zzqbVar, zzaan zzaanVar) {
        this.zzb = zzqbVar;
        this.zza = zzaanVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : map.keySet()) {
                jSONObject.put(str, map.get(str));
            }
            jSONObject.put("id", this.zzb.zzb);
            this.zza.zza("sendMessageToNativeJs", jSONObject);
        } catch (JSONException e) {
            zzahw.zzb("Unable to dispatch sendMessageToNativeJs event", e);
        }
    }
}
