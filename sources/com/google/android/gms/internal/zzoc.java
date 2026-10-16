package com.google.android.gms.internal;

import android.content.SharedPreferences;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzoc extends zzny<Float> {
    zzoc(int i, String str, Float f) {
        super(i, str, f, null);
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ Float zza(SharedPreferences sharedPreferences) {
        return Float.valueOf(sharedPreferences.getFloat(zza(), zzb().floatValue()));
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ Float zza(JSONObject jSONObject) {
        return Float.valueOf((float) jSONObject.optDouble(zza(), zzb().floatValue()));
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ void zza(SharedPreferences.Editor editor, Float f) {
        editor.putFloat(zza(), f.floatValue());
    }
}
