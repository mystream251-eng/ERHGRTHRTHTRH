package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzacl extends zzbgl {
    public static final Parcelable.Creator<zzacl> CREATOR = new zzacm();
    public final boolean zza;
    public final List<String> zzb;

    public zzacl() {
        this(false, Collections.emptyList());
    }

    public zzacl(boolean z, List<String> list) {
        this.zza = z;
        this.zzb = list;
    }

    public static zzacl zza(JSONObject jSONObject) {
        if (jSONObject == null) {
            return new zzacl();
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("reporting_urls");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                try {
                    arrayList.add(jSONArrayOptJSONArray.getString(i));
                } catch (JSONException e) {
                    zzahw.zzc("Error grabbing url from json.", e);
                }
            }
        }
        return new zzacl(jSONObject.optBoolean("enable_protection"), arrayList);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza);
        zzbgo.zzb(parcel, 3, this.zzb, false);
        zzbgo.zza(parcel, iZza);
    }
}
