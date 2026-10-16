package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzamd;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzy implements zzt<Object> {
    private HashMap<String, zzamd<JSONObject>> zza = new HashMap<>();

    public final Future<JSONObject> zza(String str) {
        zzamd<JSONObject> zzamdVar = new zzamd<>();
        this.zza.put(str, zzamdVar);
        return zzamdVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("request_id");
        String str2 = map.get("fetched_ad");
        zzahw.zzb("Received ad from the cache.");
        zzamd<JSONObject> zzamdVar = this.zza.get(str);
        try {
            if (zzamdVar == null) {
                zzahw.zzc("Could not find the ad request for the corresponding ad response.");
            } else {
                zzamdVar.zzb(new JSONObject(str2));
            }
        } catch (JSONException e) {
            zzahw.zzb("Failed constructing JSON object from value passed from javascript", e);
            zzamdVar.zzb(null);
        } finally {
            this.zza.remove(str);
        }
    }

    public final void zzb(String str) {
        zzamd<JSONObject> zzamdVar = this.zza.get(str);
        if (zzamdVar == null) {
            zzahw.zzc("Could not find the ad request for the corresponding ad response.");
            return;
        }
        if (!zzamdVar.isDone()) {
            zzamdVar.cancel(true);
        }
        this.zza.remove(str);
    }
}
