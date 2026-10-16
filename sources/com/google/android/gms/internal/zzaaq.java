package com.google.android.gms.internal;

import androidx.collection.SimpleArrayMap;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaaq implements zzaae<zzpo> {
    private final boolean zza;

    public zzaaq(boolean z) {
        this.zza = z;
    }

    private static <K, V> SimpleArrayMap<K, V> zza(SimpleArrayMap<K, Future<V>> simpleArrayMap) throws ExecutionException, InterruptedException {
        SimpleArrayMap<K, V> simpleArrayMap2 = new SimpleArrayMap<>();
        for (int i = 0; i < simpleArrayMap.size(); i++) {
            simpleArrayMap2.put(simpleArrayMap.keyAt(i), simpleArrayMap.valueAt(i).get());
        }
        return simpleArrayMap2;
    }

    @Override // com.google.android.gms.internal.zzaae
    public final /* synthetic */ zzpx zza(zzzy zzzyVar, JSONObject jSONObject) throws ExecutionException, JSONException, InterruptedException {
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        zzalt<zzph> zzaltVarZza = zzzyVar.zza(jSONObject);
        zzalt<zzaof> zzaltVarZza2 = zzzyVar.zza(jSONObject, MimeTypes.BASE_TYPE_VIDEO);
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString("type");
            if ("string".equals(string)) {
                simpleArrayMap2.put(jSONObject2.getString("name"), jSONObject2.getString("string_value"));
            } else if (TtmlNode.TAG_IMAGE.equals(string)) {
                simpleArrayMap.put(jSONObject2.getString("name"), zzzyVar.zza(jSONObject2, "image_value", this.zza));
            } else {
                String strValueOf = String.valueOf(string);
                zzahw.zze(strValueOf.length() != 0 ? "Unknown custom asset type: ".concat(strValueOf) : new String("Unknown custom asset type: "));
            }
        }
        zzaof zzaofVarZza = zzzy.zza(zzaltVarZza2);
        return new zzpo(jSONObject.getString("custom_template_id"), zza(simpleArrayMap), simpleArrayMap2, zzaltVarZza.get(), zzaofVarZza != null ? zzaofVarZza.zzb() : null, zzaofVarZza != null ? zzaofVarZza.zzm() : null);
    }
}
