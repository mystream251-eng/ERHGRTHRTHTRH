package com.google.android.gms.internal;

import android.content.SharedPreferences;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzoa extends zzny<Integer> {
    zzoa(int i, String str, Integer num) {
        super(i, str, num, null);
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ Integer zza(SharedPreferences sharedPreferences) {
        return Integer.valueOf(sharedPreferences.getInt(zza(), zzb().intValue()));
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ Integer zza(JSONObject jSONObject) {
        return Integer.valueOf(jSONObject.optInt(zza(), zzb().intValue()));
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ void zza(SharedPreferences.Editor editor, Integer num) {
        editor.putInt(zza(), num.intValue());
    }
}
