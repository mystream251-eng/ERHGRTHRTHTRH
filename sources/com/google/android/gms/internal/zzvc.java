package com.google.android.gms.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzvc {
    private static final Charset zzb = Charset.forName("UTF-8");
    public static final zzvb<JSONObject> zza = new zzve();
    private static zzuz<InputStream> zzc = zzvd.zza;

    static final /* synthetic */ InputStream zza(JSONObject jSONObject) throws JSONException {
        return new ByteArrayInputStream(jSONObject.toString().getBytes(zzb));
    }
}
