package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.viaccessorca.vodownloader.NanoHTTPD;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzqc implements zzt<Object> {
    final /* synthetic */ zzqb zza;
    private /* synthetic */ zzaan zzb;

    zzqc(zzqb zzqbVar, zzaan zzaanVar) {
        this.zza = zzqbVar;
        this.zzb = zzaanVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, final Map<String, String> map) {
        zzaof zzaofVar = (zzaof) this.zza.zza.get();
        if (zzaofVar == null) {
            this.zzb.zzb("/loadHtml", this);
            return;
        }
        zzapu zzapuVarZzv = zzaofVar.zzv();
        final zzaan zzaanVar = this.zzb;
        zzapuVarZzv.zza(new zzapv(this, map, zzaanVar) { // from class: com.google.android.gms.internal.zzqd
            private final zzqc zza;
            private final Map zzb;
            private final zzaan zzc;

            {
                this.zza = this;
                this.zzb = map;
                this.zzc = zzaanVar;
            }

            @Override // com.google.android.gms.internal.zzapv
            public final void zza(zzaof zzaofVar2, boolean z) {
                zzqc zzqcVar = this.zza;
                Map map2 = this.zzb;
                zzaan zzaanVar2 = this.zzc;
                zzqcVar.zza.zzb = (String) map2.get("id");
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("messageType", "htmlLoaded");
                    jSONObject.put("id", zzqcVar.zza.zzb);
                    zzaanVar2.zza("sendMessageToNativeJs", jSONObject);
                } catch (JSONException e) {
                    zzahw.zzb("Unable to dispatch sendMessageToNativeJs event", e);
                }
            }
        });
        String str = map.get("overlayHtml");
        String str2 = map.get("baseUrl");
        if (TextUtils.isEmpty(str2)) {
            zzaofVar.loadData(str, NanoHTTPD.MIME_HTML, "UTF-8");
        } else {
            zzaofVar.loadDataWithBaseURL(str2, str, NanoHTTPD.MIME_HTML, "UTF-8", null);
        }
    }
}
