package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzvq {
    public final List<zzvp> zza;
    public final long zzb;
    public final List<String> zzc;
    public final List<String> zzd;
    public final List<String> zze;
    public final List<String> zzf;
    public final boolean zzg;
    public final String zzh;
    public final long zzi;
    public final String zzj;
    public final int zzk;
    public final int zzl;
    public final long zzm;
    public final boolean zzn;
    public final boolean zzo;
    public final boolean zzp;
    public int zzq;
    public int zzr;
    public boolean zzs;

    public zzvq(String str) throws JSONException {
        this(new JSONObject(str));
    }

    public zzvq(List<zzvp> list, long j, List<String> list2, List<String> list3, List<String> list4, List<String> list5, boolean z, String str, long j2, int i, int i2, String str2, int i3, int i4, long j3, boolean z2) {
        this.zza = list;
        this.zzb = j;
        this.zzc = list2;
        this.zzd = list3;
        this.zze = list4;
        this.zzf = list5;
        this.zzg = z;
        this.zzh = str;
        this.zzi = -1L;
        this.zzq = 0;
        this.zzr = 1;
        this.zzj = null;
        this.zzk = 0;
        this.zzl = -1;
        this.zzm = -1L;
        this.zzn = false;
        this.zzo = false;
        this.zzp = false;
        this.zzs = false;
    }

    public zzvq(JSONObject jSONObject) throws JSONException {
        if (zzahw.zza(2)) {
            String strValueOf = String.valueOf(jSONObject.toString(2));
            zzahw.zza(strValueOf.length() != 0 ? "Mediation Response JSON: ".concat(strValueOf) : new String("Mediation Response JSON: "));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("ad_networks");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int i = -1;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            zzvp zzvpVar = new zzvp(jSONArray.getJSONObject(i2));
            if (zzvpVar.zza()) {
                this.zzs = true;
            }
            arrayList.add(zzvpVar);
            if (i < 0 && zza(zzvpVar)) {
                i = i2;
            }
        }
        this.zzq = i;
        this.zzr = jSONArray.length();
        this.zza = Collections.unmodifiableList(arrayList);
        this.zzh = jSONObject.optString("qdata");
        this.zzl = jSONObject.optInt("fs_model_type", -1);
        this.zzm = jSONObject.optLong("timeout_ms", -1L);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("settings");
        if (jSONObjectOptJSONObject == null) {
            this.zzb = -1L;
            this.zzc = null;
            this.zzd = null;
            this.zze = null;
            this.zzf = null;
            this.zzi = -1L;
            this.zzj = null;
            this.zzk = 0;
            this.zzn = false;
            this.zzg = false;
            this.zzo = false;
            this.zzp = false;
            return;
        }
        this.zzb = jSONObjectOptJSONObject.optLong("ad_network_timeout_millis", -1L);
        zzbt.zzw();
        this.zzc = zzvy.zza(jSONObjectOptJSONObject, "click_urls");
        zzbt.zzw();
        this.zzd = zzvy.zza(jSONObjectOptJSONObject, "imp_urls");
        zzbt.zzw();
        this.zze = zzvy.zza(jSONObjectOptJSONObject, "nofill_urls");
        zzbt.zzw();
        this.zzf = zzvy.zza(jSONObjectOptJSONObject, "remote_ping_urls");
        this.zzg = jSONObjectOptJSONObject.optBoolean("render_in_browser", false);
        long jOptLong = jSONObjectOptJSONObject.optLong("refresh", -1L);
        this.zzi = jOptLong > 0 ? 1000 * jOptLong : -1L;
        zzagd zzagdVarZza = zzagd.zza(jSONObjectOptJSONObject.optJSONArray("rewards"));
        if (zzagdVarZza == null) {
            this.zzj = null;
            this.zzk = 0;
        } else {
            this.zzj = zzagdVarZza.zza;
            this.zzk = zzagdVarZza.zzb;
        }
        this.zzn = jSONObjectOptJSONObject.optBoolean("use_displayed_impression", false);
        this.zzo = jSONObjectOptJSONObject.optBoolean("allow_pub_rendered_attribution", false);
        this.zzp = jSONObjectOptJSONObject.optBoolean("allow_pub_owned_ad_view", false);
    }

    private static boolean zza(zzvp zzvpVar) {
        Iterator<String> it = zzvpVar.zzc.iterator();
        while (it.hasNext()) {
            if (it.next().equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                return true;
            }
        }
        return false;
    }
}
