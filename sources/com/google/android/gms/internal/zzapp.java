package com.google.android.gms.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzapp {
    private static final Pattern zza = Pattern.compile("<!DOCTYPE(\\s)*html(|(\\s)+[^>]*)>", 2);
    private static final Pattern zzb = Pattern.compile("<!DOCTYPE[^>]*>", 2);

    public static String zza() {
        String str = (String) zzlc.zzf().zza(zzoi.zzah);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", str);
            jSONObject.put("sdk", "Google Mobile Ads");
            jSONObject.put("sdkVersion", "12.2.10-000");
            return "<script>Object.defineProperty(window,'MRAID_ENV',{get:function(){return " + jSONObject.toString() + "}});</script>";
        } catch (JSONException e) {
            zzahw.zzc("Unable to build MRAID_ENV", e);
            return null;
        }
    }

    public static String zza(String str, String... strArr) {
        if (strArr.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        Matcher matcher = zza.matcher(str);
        int i = 0;
        if (matcher.find()) {
            int iEnd = matcher.end();
            sb.append(str.substring(0, iEnd));
            int length = strArr.length;
            while (i < length) {
                String str2 = strArr[i];
                if (str2 != null) {
                    sb.append(str2);
                }
                i++;
            }
            str = str.substring(iEnd);
        } else if (!zzb.matcher(str).find()) {
            int length2 = strArr.length;
            while (i < length2) {
                String str3 = strArr[i];
                if (str3 != null) {
                    sb.append(str3);
                }
                i++;
            }
        }
        sb.append(str);
        return sb.toString();
    }
}
