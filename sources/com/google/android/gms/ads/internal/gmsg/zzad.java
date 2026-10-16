package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzad implements zzt<Object> {
    private final Object zza = new Object();
    private final Map<String, zzae> zzb = new HashMap();

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final void zza(Object obj, Map<String, String> map) {
        String strConcat;
        String str = map.get("id");
        String str2 = map.get("fail");
        String str3 = map.get("fail_reason");
        String str4 = map.get("fail_stack");
        String str5 = map.get("result");
        if (TextUtils.isEmpty(str4)) {
            str3 = "Unknown Fail Reason.";
        }
        if (TextUtils.isEmpty(str4)) {
            strConcat = "";
        } else {
            String strValueOf = String.valueOf(str4);
            strConcat = strValueOf.length() != 0 ? "\n".concat(strValueOf) : new String("\n");
        }
        synchronized (this.zza) {
            zzae zzaeVarRemove = this.zzb.remove(str);
            if (zzaeVarRemove == null) {
                String strValueOf2 = String.valueOf(str);
                zzahw.zze(strValueOf2.length() != 0 ? "Received result for unexpected method invocation: ".concat(strValueOf2) : new String("Received result for unexpected method invocation: "));
                return;
            }
            if (!TextUtils.isEmpty(str2)) {
                String strValueOf3 = String.valueOf(str3);
                String strValueOf4 = String.valueOf(strConcat);
                zzaeVarRemove.zza(strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3));
            } else {
                if (str5 == null) {
                    zzaeVarRemove.zza((JSONObject) null);
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(str5);
                    if (zzahw.zza()) {
                        String strValueOf5 = String.valueOf(jSONObject.toString(2));
                        zzahw.zza(strValueOf5.length() != 0 ? "Result GMSG: ".concat(strValueOf5) : new String("Result GMSG: "));
                    }
                    zzaeVarRemove.zza(jSONObject);
                } catch (JSONException e) {
                    zzaeVarRemove.zza(e.getMessage());
                }
            }
        }
    }

    public final void zza(String str, zzae zzaeVar) {
        synchronized (this.zza) {
            this.zzb.put(str, zzaeVar);
        }
    }
}
