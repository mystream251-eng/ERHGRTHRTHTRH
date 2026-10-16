package com.google.android.gms.internal;

import android.content.SharedPreferences;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzob extends zzny<Long> {
    zzob(int i, String str, Long l) {
        super(i, str, l, null);
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ Long zza(SharedPreferences sharedPreferences) {
        return Long.valueOf(sharedPreferences.getLong(zza(), zzb().longValue()));
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ Long zza(JSONObject jSONObject) {
        return Long.valueOf(jSONObject.optLong(zza(), zzb().longValue()));
    }

    @Override // com.google.android.gms.internal.zzny
    public final /* synthetic */ void zza(SharedPreferences.Editor editor, Long l) {
        editor.putLong(zza(), l.longValue());
    }
}
