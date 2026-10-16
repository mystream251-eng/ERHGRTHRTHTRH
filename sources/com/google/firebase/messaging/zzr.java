package com.google.firebase.messaging;

import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzr {
    private final Bundle zza;

    public zzr(Bundle bundle) {
        if (bundle == null) {
            throw new NullPointerException("data");
        }
        this.zza = new Bundle(bundle);
    }

    public final String zza(String str) {
        Bundle bundle = this.zza;
        if (!bundle.containsKey(str) && str.startsWith("gcm.n.")) {
            String strZzi = zzi(str);
            if (this.zza.containsKey(strZzi)) {
                str = strZzi;
            }
        }
        return bundle.getString(str);
    }

    public final boolean zzb(String str) {
        String strZza = zza(str);
        return LocalSearchProvider.LIVE_CONTENTS.equals(strZza) || Boolean.parseBoolean(strZza);
    }

    public final Integer zzc(String str) {
        String strZza = zza(str);
        if (TextUtils.isEmpty(strZza)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(strZza));
        } catch (NumberFormatException unused) {
            String strZzh = zzh(str);
            StringBuilder sb = new StringBuilder(String.valueOf(strZzh).length() + 38 + String.valueOf(strZza).length());
            sb.append("Couldn't parse value of ");
            sb.append(strZzh);
            sb.append("(");
            sb.append(strZza);
            sb.append(") into an int");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    public final Long zzd(String str) {
        String strZza = zza(str);
        if (TextUtils.isEmpty(strZza)) {
            return null;
        }
        try {
            return Long.valueOf(Long.parseLong(strZza));
        } catch (NumberFormatException unused) {
            String strZzh = zzh(str);
            StringBuilder sb = new StringBuilder(String.valueOf(strZzh).length() + 38 + String.valueOf(strZza).length());
            sb.append("Couldn't parse value of ");
            sb.append(strZzh);
            sb.append("(");
            sb.append(strZza);
            sb.append(") into a long");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    public final String zze(String str) {
        String strValueOf = String.valueOf(str);
        return zza("_loc_key".length() != 0 ? strValueOf.concat("_loc_key") : new String(strValueOf));
    }

    public final Object[] zzf(String str) {
        String strValueOf = String.valueOf(str);
        JSONArray jSONArrayZzg = zzg("_loc_args".length() != 0 ? strValueOf.concat("_loc_args") : new String(strValueOf));
        if (jSONArrayZzg == null) {
            return null;
        }
        int length = jSONArrayZzg.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = jSONArrayZzg.optString(i);
        }
        return strArr;
    }

    private final JSONArray zzg(String str) {
        String strZza = zza(str);
        if (TextUtils.isEmpty(strZza)) {
            return null;
        }
        try {
            return new JSONArray(strZza);
        } catch (JSONException unused) {
            String strZzh = zzh(str);
            StringBuilder sb = new StringBuilder(String.valueOf(strZzh).length() + 50 + String.valueOf(strZza).length());
            sb.append("Malformed JSON for key ");
            sb.append(strZzh);
            sb.append(": ");
            sb.append(strZza);
            sb.append(", falling back to default");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    private static String zzh(String str) {
        return str.startsWith("gcm.n.") ? str.substring(6) : str;
    }

    public final Uri zza() {
        String strZza = zza("gcm.n.link_android");
        if (TextUtils.isEmpty(strZza)) {
            strZza = zza("gcm.n.link");
        }
        if (TextUtils.isEmpty(strZza)) {
            return null;
        }
        return Uri.parse(strZza);
    }

    public final String zzb() {
        String strZza = zza("gcm.n.sound2");
        return TextUtils.isEmpty(strZza) ? zza("gcm.n.sound") : strZza;
    }

    public final long[] zzc() {
        JSONArray jSONArrayZzg = zzg("gcm.n.vibrate_timings");
        if (jSONArrayZzg == null) {
            return null;
        }
        try {
            if (jSONArrayZzg.length() <= 1) {
                throw new JSONException("vibrateTimings have invalid length");
            }
            int length = jSONArrayZzg.length();
            long[] jArr = new long[length];
            for (int i = 0; i < length; i++) {
                jArr[i] = jSONArrayZzg.optLong(i);
            }
            return jArr;
        } catch (NumberFormatException | JSONException unused) {
            String strValueOf = String.valueOf(jSONArrayZzg);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 74);
            sb.append("User defined vibrateTimings is invalid: ");
            sb.append(strValueOf);
            sb.append(". Skipping setting vibrateTimings.");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    final int[] zzd() {
        JSONArray jSONArrayZzg = zzg("gcm.n.light_settings");
        if (jSONArrayZzg == null) {
            return null;
        }
        int[] iArr = new int[3];
        try {
            if (jSONArrayZzg.length() != 3) {
                throw new JSONException("lightSettings don't have all three fields");
            }
            int color = Color.parseColor(jSONArrayZzg.optString(0));
            if (color == -16777216) {
                throw new IllegalArgumentException("Transparent color is invalid");
            }
            iArr[0] = color;
            iArr[1] = jSONArrayZzg.optInt(1);
            iArr[2] = jSONArrayZzg.optInt(2);
            return iArr;
        } catch (IllegalArgumentException e) {
            String strValueOf = String.valueOf(jSONArrayZzg);
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 60 + String.valueOf(message).length());
            sb.append("LightSettings is invalid: ");
            sb.append(strValueOf);
            sb.append(". ");
            sb.append(message);
            sb.append(". Skipping setting LightSettings");
            Log.w("NotificationParams", sb.toString());
            return null;
        } catch (JSONException unused) {
            String strValueOf2 = String.valueOf(jSONArrayZzg);
            StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 58);
            sb2.append("LightSettings is invalid: ");
            sb2.append(strValueOf2);
            sb2.append(". Skipping setting LightSettings");
            Log.w("NotificationParams", sb2.toString());
            return null;
        }
    }

    public final Bundle zze() {
        Bundle bundle = new Bundle(this.zza);
        for (String str : this.zza.keySet()) {
            if (str.startsWith("google.c.") || str.startsWith("gcm.n.") || str.startsWith("gcm.notification.")) {
                bundle.remove(str);
            }
        }
        return bundle;
    }

    public final Bundle zzf() {
        Bundle bundle = new Bundle(this.zza);
        for (String str : this.zza.keySet()) {
            if (!(str.startsWith("google.c.a.") || str.equals("from"))) {
                bundle.remove(str);
            }
        }
        return bundle;
    }

    private final String zzb(Resources resources, String str, String str2) {
        String strZze = zze(str2);
        if (TextUtils.isEmpty(strZze)) {
            return null;
        }
        int identifier = resources.getIdentifier(strZze, "string", str);
        if (identifier == 0) {
            String strValueOf = String.valueOf(str2);
            String strZzh = zzh("_loc_key".length() != 0 ? strValueOf.concat("_loc_key") : new String(strValueOf));
            StringBuilder sb = new StringBuilder(String.valueOf(strZzh).length() + 49 + String.valueOf(str2).length());
            sb.append(strZzh);
            sb.append(" resource not found: ");
            sb.append(str2);
            sb.append(" Default value will be used.");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
        Object[] objArrZzf = zzf(str2);
        if (objArrZzf == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, objArrZzf);
        } catch (MissingFormatArgumentException e) {
            String strZzh2 = zzh(str2);
            String string = Arrays.toString(objArrZzf);
            StringBuilder sb2 = new StringBuilder(String.valueOf(strZzh2).length() + 58 + String.valueOf(string).length());
            sb2.append("Missing format argument for ");
            sb2.append(strZzh2);
            sb2.append(": ");
            sb2.append(string);
            sb2.append(" Default value will be used.");
            Log.w("NotificationParams", sb2.toString(), e);
            return null;
        }
    }

    public final String zza(Resources resources, String str, String str2) {
        String strZza = zza(str2);
        return !TextUtils.isEmpty(strZza) ? strZza : zzb(resources, str, str2);
    }

    public static boolean zza(Bundle bundle) {
        return LocalSearchProvider.LIVE_CONTENTS.equals(bundle.getString("gcm.n.e")) || LocalSearchProvider.LIVE_CONTENTS.equals(bundle.getString(zzi("gcm.n.e")));
    }

    private static String zzi(String str) {
        return !str.startsWith("gcm.n.") ? str : str.replace("gcm.n.", "gcm.notification.");
    }
}
