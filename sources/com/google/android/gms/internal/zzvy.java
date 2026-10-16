package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzvy {
    private static String zza(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        return str.replaceAll(str2, str3);
    }

    public static List<String> zza(JSONObject jSONObject, String str) throws JSONException {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
        if (jSONArrayOptJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(jSONArrayOptJSONArray.length());
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            arrayList.add(jSONArrayOptJSONArray.getString(i));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static void zza(Context context, String str, zzahd zzahdVar, String str2, boolean z, List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        String str3 = z ? LocalSearchProvider.LIVE_CONTENTS : LocalSearchProvider.NO_LIVE_CONTENTS;
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String strZza = zza(zza(zza(zza(zza(zza(zza(it.next(), "@gw_adlocid@", str2), "@gw_adnetrefresh@", str3), "@gw_qdata@", zzahdVar.zzq.zzh), "@gw_sdkver@", str), "@gw_sessid@", zzlc.zzc()), "@gw_seqnum@", zzahdVar.zzi), "@gw_adnetstatus@", zzahdVar.zzs);
            if (zzahdVar.zzn != null) {
                strZza = zza(zza(strZza, "@gw_adnetid@", zzahdVar.zzn.zzb), "@gw_allocid@", zzahdVar.zzn.zzd);
            }
            String strZza2 = zzagx.zza(strZza, context);
            zzbt.zze();
            zzaij.zzb(context, str, strZza2);
        }
    }

    public static void zza(Context context, String str, List<String> list, String str2, zzagd zzagdVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (!TextUtils.isEmpty(str2) && zzaks.zzc()) {
            str2 = "fakeUserForAdDebugLog";
        }
        long jZza = zzbt.zzl().zza();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String strZza = zza(zza(it.next(), "@gw_rwd_userid@", Uri.encode(str2)), "@gw_tmstmp@", Long.toString(jZza));
            if (zzagdVar != null) {
                strZza = zza(zza(strZza, "@gw_rwd_itm@", Uri.encode(zzagdVar.zza)), "@gw_rwd_amt@", Integer.toString(zzagdVar.zzb));
            }
            zzbt.zze();
            zzaij.zzb(context, str, strZza);
        }
    }

    public static boolean zza(String str, int[] iArr) {
        if (TextUtils.isEmpty(str) || iArr.length != 2) {
            return false;
        }
        String[] strArrSplit = str.split("x");
        if (strArrSplit.length != 2) {
            return false;
        }
        try {
            iArr[0] = Integer.parseInt(strArrSplit[0]);
            iArr[1] = Integer.parseInt(strArrSplit[1]);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
