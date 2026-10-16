package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzald;
import com.google.android.gms.internal.zzali;
import com.google.android.gms.internal.zzalt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzad implements zzald {
    static final zzald zza = new zzad();

    private zzad() {
    }

    @Override // com.google.android.gms.internal.zzald
    public final zzalt zza(Object obj) throws JSONException {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject.optBoolean("isSuccessful", false)) {
            zzbt.zzi().zzl().zzf(jSONObject.getString("appSettingsJson"));
        }
        return zzali.zza((Object) null);
    }
}
