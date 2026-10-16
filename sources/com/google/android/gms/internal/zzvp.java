package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzvp {
    public final String zza;
    public final String zzb;
    public final List<String> zzc;
    public final String zzd;
    public final String zze;
    public final List<String> zzf;
    public final List<String> zzg;
    public final List<String> zzh;
    public final List<String> zzi;
    public final String zzj;
    public final List<String> zzk;
    public final List<String> zzl;
    public final List<String> zzm;
    public final String zzn;
    public final String zzo;
    public final String zzp;
    public final List<String> zzq;
    public final String zzr;
    public final long zzs;
    private String zzt;

    public zzvp(String str, String str2, List<String> list, String str3, String str4, List<String> list2, List<String> list3, List<String> list4, String str5, String str6, List<String> list5, List<String> list6, List<String> list7, String str7, String str8, String str9, List<String> list8, String str10, List<String> list9, String str11, long j) {
        this.zza = str;
        this.zzb = null;
        this.zzc = list;
        this.zzd = null;
        this.zze = null;
        this.zzf = list2;
        this.zzg = list3;
        this.zzh = list4;
        this.zzj = str5;
        this.zzk = list5;
        this.zzl = list6;
        this.zzm = list7;
        this.zzn = null;
        this.zzo = null;
        this.zzp = null;
        this.zzq = null;
        this.zzr = null;
        this.zzi = list9;
        this.zzt = null;
        this.zzs = -1L;
    }

    public zzvp(JSONObject jSONObject) throws JSONException {
        List<String> listZza;
        this.zzb = jSONObject.optString("id");
        JSONArray jSONArray = jSONObject.getJSONArray("adapters");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        this.zzc = Collections.unmodifiableList(arrayList);
        this.zzd = jSONObject.optString("allocation_id", null);
        zzbt.zzw();
        this.zzf = zzvy.zza(jSONObject, "clickurl");
        zzbt.zzw();
        this.zzg = zzvy.zza(jSONObject, "imp_urls");
        zzbt.zzw();
        this.zzi = zzvy.zza(jSONObject, "fill_urls");
        zzbt.zzw();
        this.zzk = zzvy.zza(jSONObject, "video_start_urls");
        zzbt.zzw();
        this.zzm = zzvy.zza(jSONObject, "video_complete_urls");
        zzbt.zzw();
        this.zzl = ((Boolean) zzlc.zzf().zza(zzoi.zzbt)).booleanValue() ? zzvy.zza(jSONObject, "video_reward_urls") : this.zzm;
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("ad");
        if (jSONObjectOptJSONObject != null) {
            zzbt.zzw();
            listZza = zzvy.zza(jSONObjectOptJSONObject, "manual_impression_urls");
        } else {
            listZza = null;
        }
        this.zzh = listZza;
        this.zza = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.toString() : null;
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("data");
        this.zzj = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.toString() : null;
        this.zze = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.optString("class_name") : null;
        this.zzn = jSONObject.optString("html_template", null);
        this.zzo = jSONObject.optString("ad_base_url", null);
        JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("assets");
        this.zzp = jSONObjectOptJSONObject3 != null ? jSONObjectOptJSONObject3.toString() : null;
        zzbt.zzw();
        this.zzq = zzvy.zza(jSONObject, "template_ids");
        JSONObject jSONObjectOptJSONObject4 = jSONObject.optJSONObject("ad_loader_options");
        this.zzr = jSONObjectOptJSONObject4 != null ? jSONObjectOptJSONObject4.toString() : null;
        this.zzt = jSONObject.optString("response_type", null);
        this.zzs = jSONObject.optLong("ad_network_timeout_millis", -1L);
    }

    public final boolean zza() {
        return "banner".equalsIgnoreCase(this.zzt);
    }

    public final boolean zzb() {
        return "native".equalsIgnoreCase(this.zzt);
    }
}
