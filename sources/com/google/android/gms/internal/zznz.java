package com.google.android.gms.internal;

import android.content.SharedPreferences;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zznz extends zzny<Boolean> {
    zznz(int i, String str, Boolean bool) {
        super(i, str, bool, null);
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ Boolean zza(SharedPreferences sharedPreferences) {
        return Boolean.valueOf(sharedPreferences.getBoolean(zza(), zzb().booleanValue()));
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ Boolean zza(JSONObject jSONObject) {
        return Boolean.valueOf(jSONObject.optBoolean(zza(), zzb().booleanValue()));
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ void zza(SharedPreferences.Editor editor, Boolean bool) {
        editor.putBoolean(zza(), bool.booleanValue());
    }
}
