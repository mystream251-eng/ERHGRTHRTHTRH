package com.google.android.gms.internal;

import android.content.SharedPreferences;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzod extends zzny<String> {
    zzod(int i, String str, String str2) {
        super(i, str, str2, null);
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ String zza(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(zza(), zzb());
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ String zza(JSONObject jSONObject) {
        return jSONObject.optString(zza(), zzb());
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ void zza(SharedPreferences.Editor editor, String str) {
        editor.putString(zza(), str);
    }
}
