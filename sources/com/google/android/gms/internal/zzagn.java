package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzagn extends zzbgl {
    public static final Parcelable.Creator<zzagn> CREATOR = new zzago();
    public final String zza;
    public final String zzb;
    public final boolean zzc;
    public final boolean zzd;
    public final List<String> zze;
    public final boolean zzf;
    public final boolean zzg;

    public zzagn(String str, String str2, boolean z, boolean z2, List<String> list, boolean z3, boolean z4) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = z2;
        this.zze = list;
        this.zzf = z3;
        this.zzg = z4;
    }

    public static zzagn zza(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        String strOptString = jSONObject.optString("click_string", "");
        String strOptString2 = jSONObject.optString("report_url", "");
        boolean zOptBoolean = jSONObject.optBoolean("rendered_ad_enabled", false);
        boolean zOptBoolean2 = jSONObject.optBoolean("non_malicious_reporting_enabled", false);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("allowed_headers");
        if (jSONArrayOptJSONArray == null) {
            jSONArrayOptJSONArray = new JSONArray();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            String strOptString3 = jSONArrayOptJSONArray.optString(i);
            if (!TextUtils.isEmpty(strOptString3)) {
                arrayList.add(strOptString3.toLowerCase(Locale.ENGLISH));
            }
        }
        return new zzagn(strOptString, strOptString2, zOptBoolean, zOptBoolean2, arrayList, jSONObject.optBoolean("protection_enabled", false), jSONObject.optBoolean("malicious_reporting_enabled", false));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza, false);
        zzbgo.zza(parcel, 3, this.zzb, false);
        zzbgo.zza(parcel, 4, this.zzc);
        zzbgo.zza(parcel, 5, this.zzd);
        zzbgo.zzb(parcel, 6, this.zze, false);
        zzbgo.zza(parcel, 7, this.zzf);
        zzbgo.zza(parcel, 8, this.zzg);
        zzbgo.zza(parcel, iZza);
    }
}
