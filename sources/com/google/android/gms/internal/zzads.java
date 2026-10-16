package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.text.TextUtils;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.internal.ImagesContract;
import io.sentry.protocol.Browser;
import io.sentry.protocol.Device;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import kotlinx.coroutines.DebugKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzads {
    private static final SimpleDateFormat zza = new SimpleDateFormat("yyyyMMdd", Locale.US);

    /* JADX WARN: Code duplicated, block: B:35:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:38:0x00dc A[Catch: JSONException -> 0x0239, TryCatch #0 {JSONException -> 0x0239, blocks: (B:3:0x0007, B:5:0x002a, B:9:0x0033, B:11:0x003b, B:13:0x0043, B:15:0x004b, B:17:0x0053, B:18:0x0057, B:20:0x006b, B:22:0x007c, B:24:0x008b, B:30:0x00a9, B:32:0x00af, B:34:0x00b5, B:38:0x00dc, B:40:0x00e2, B:45:0x00f0, B:46:0x00f5, B:51:0x0103, B:52:0x0108, B:57:0x0116, B:60:0x0121, B:62:0x0125, B:63:0x0129, B:65:0x0131, B:67:0x0137, B:69:0x0145, B:71:0x0150, B:55:0x0112, B:49:0x00ff, B:43:0x00ec, B:26:0x0096, B:28:0x009e), top: B:80:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00e2 A[Catch: JSONException -> 0x0239, TryCatch #0 {JSONException -> 0x0239, blocks: (B:3:0x0007, B:5:0x002a, B:9:0x0033, B:11:0x003b, B:13:0x0043, B:15:0x004b, B:17:0x0053, B:18:0x0057, B:20:0x006b, B:22:0x007c, B:24:0x008b, B:30:0x00a9, B:32:0x00af, B:34:0x00b5, B:38:0x00dc, B:40:0x00e2, B:45:0x00f0, B:46:0x00f5, B:51:0x0103, B:52:0x0108, B:57:0x0116, B:60:0x0121, B:62:0x0125, B:63:0x0129, B:65:0x0131, B:67:0x0137, B:69:0x0145, B:71:0x0150, B:55:0x0112, B:49:0x00ff, B:43:0x00ec, B:26:0x0096, B:28:0x009e), top: B:80:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:43:0x00ec A[Catch: JSONException -> 0x0239, TryCatch #0 {JSONException -> 0x0239, blocks: (B:3:0x0007, B:5:0x002a, B:9:0x0033, B:11:0x003b, B:13:0x0043, B:15:0x004b, B:17:0x0053, B:18:0x0057, B:20:0x006b, B:22:0x007c, B:24:0x008b, B:30:0x00a9, B:32:0x00af, B:34:0x00b5, B:38:0x00dc, B:40:0x00e2, B:45:0x00f0, B:46:0x00f5, B:51:0x0103, B:52:0x0108, B:57:0x0116, B:60:0x0121, B:62:0x0125, B:63:0x0129, B:65:0x0131, B:67:0x0137, B:69:0x0145, B:71:0x0150, B:55:0x0112, B:49:0x00ff, B:43:0x00ec, B:26:0x0096, B:28:0x009e), top: B:80:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x00f0 A[Catch: JSONException -> 0x0239, TryCatch #0 {JSONException -> 0x0239, blocks: (B:3:0x0007, B:5:0x002a, B:9:0x0033, B:11:0x003b, B:13:0x0043, B:15:0x004b, B:17:0x0053, B:18:0x0057, B:20:0x006b, B:22:0x007c, B:24:0x008b, B:30:0x00a9, B:32:0x00af, B:34:0x00b5, B:38:0x00dc, B:40:0x00e2, B:45:0x00f0, B:46:0x00f5, B:51:0x0103, B:52:0x0108, B:57:0x0116, B:60:0x0121, B:62:0x0125, B:63:0x0129, B:65:0x0131, B:67:0x0137, B:69:0x0145, B:71:0x0150, B:55:0x0112, B:49:0x00ff, B:43:0x00ec, B:26:0x0096, B:28:0x009e), top: B:80:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:48:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:49:0x00ff A[Catch: JSONException -> 0x0239, TryCatch #0 {JSONException -> 0x0239, blocks: (B:3:0x0007, B:5:0x002a, B:9:0x0033, B:11:0x003b, B:13:0x0043, B:15:0x004b, B:17:0x0053, B:18:0x0057, B:20:0x006b, B:22:0x007c, B:24:0x008b, B:30:0x00a9, B:32:0x00af, B:34:0x00b5, B:38:0x00dc, B:40:0x00e2, B:45:0x00f0, B:46:0x00f5, B:51:0x0103, B:52:0x0108, B:57:0x0116, B:60:0x0121, B:62:0x0125, B:63:0x0129, B:65:0x0131, B:67:0x0137, B:69:0x0145, B:71:0x0150, B:55:0x0112, B:49:0x00ff, B:43:0x00ec, B:26:0x0096, B:28:0x009e), top: B:80:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0103 A[Catch: JSONException -> 0x0239, TryCatch #0 {JSONException -> 0x0239, blocks: (B:3:0x0007, B:5:0x002a, B:9:0x0033, B:11:0x003b, B:13:0x0043, B:15:0x004b, B:17:0x0053, B:18:0x0057, B:20:0x006b, B:22:0x007c, B:24:0x008b, B:30:0x00a9, B:32:0x00af, B:34:0x00b5, B:38:0x00dc, B:40:0x00e2, B:45:0x00f0, B:46:0x00f5, B:51:0x0103, B:52:0x0108, B:57:0x0116, B:60:0x0121, B:62:0x0125, B:63:0x0129, B:65:0x0131, B:67:0x0137, B:69:0x0145, B:71:0x0150, B:55:0x0112, B:49:0x00ff, B:43:0x00ec, B:26:0x0096, B:28:0x009e), top: B:80:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x0110  */
    /* JADX WARN: Code duplicated, block: B:55:0x0112 A[Catch: JSONException -> 0x0239, TryCatch #0 {JSONException -> 0x0239, blocks: (B:3:0x0007, B:5:0x002a, B:9:0x0033, B:11:0x003b, B:13:0x0043, B:15:0x004b, B:17:0x0053, B:18:0x0057, B:20:0x006b, B:22:0x007c, B:24:0x008b, B:30:0x00a9, B:32:0x00af, B:34:0x00b5, B:38:0x00dc, B:40:0x00e2, B:45:0x00f0, B:46:0x00f5, B:51:0x0103, B:52:0x0108, B:57:0x0116, B:60:0x0121, B:62:0x0125, B:63:0x0129, B:65:0x0131, B:67:0x0137, B:69:0x0145, B:71:0x0150, B:55:0x0112, B:49:0x00ff, B:43:0x00ec, B:26:0x0096, B:28:0x009e), top: B:80:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:57:0x0116 A[Catch: JSONException -> 0x0239, TryCatch #0 {JSONException -> 0x0239, blocks: (B:3:0x0007, B:5:0x002a, B:9:0x0033, B:11:0x003b, B:13:0x0043, B:15:0x004b, B:17:0x0053, B:18:0x0057, B:20:0x006b, B:22:0x007c, B:24:0x008b, B:30:0x00a9, B:32:0x00af, B:34:0x00b5, B:38:0x00dc, B:40:0x00e2, B:45:0x00f0, B:46:0x00f5, B:51:0x0103, B:52:0x0108, B:57:0x0116, B:60:0x0121, B:62:0x0125, B:63:0x0129, B:65:0x0131, B:67:0x0137, B:69:0x0145, B:71:0x0150, B:55:0x0112, B:49:0x00ff, B:43:0x00ec, B:26:0x0096, B:28:0x009e), top: B:80:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:58:0x011d  */
    /* JADX WARN: Code duplicated, block: B:60:0x0121 A[Catch: JSONException -> 0x0239, TryCatch #0 {JSONException -> 0x0239, blocks: (B:3:0x0007, B:5:0x002a, B:9:0x0033, B:11:0x003b, B:13:0x0043, B:15:0x004b, B:17:0x0053, B:18:0x0057, B:20:0x006b, B:22:0x007c, B:24:0x008b, B:30:0x00a9, B:32:0x00af, B:34:0x00b5, B:38:0x00dc, B:40:0x00e2, B:45:0x00f0, B:46:0x00f5, B:51:0x0103, B:52:0x0108, B:57:0x0116, B:60:0x0121, B:62:0x0125, B:63:0x0129, B:65:0x0131, B:67:0x0137, B:69:0x0145, B:71:0x0150, B:55:0x0112, B:49:0x00ff, B:43:0x00ec, B:26:0x0096, B:28:0x009e), top: B:80:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x0125 A[Catch: JSONException -> 0x0239, TryCatch #0 {JSONException -> 0x0239, blocks: (B:3:0x0007, B:5:0x002a, B:9:0x0033, B:11:0x003b, B:13:0x0043, B:15:0x004b, B:17:0x0053, B:18:0x0057, B:20:0x006b, B:22:0x007c, B:24:0x008b, B:30:0x00a9, B:32:0x00af, B:34:0x00b5, B:38:0x00dc, B:40:0x00e2, B:45:0x00f0, B:46:0x00f5, B:51:0x0103, B:52:0x0108, B:57:0x0116, B:60:0x0121, B:62:0x0125, B:63:0x0129, B:65:0x0131, B:67:0x0137, B:69:0x0145, B:71:0x0150, B:55:0x0112, B:49:0x00ff, B:43:0x00ec, B:26:0x0096, B:28:0x009e), top: B:80:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x0131 A[Catch: JSONException -> 0x0239, TryCatch #0 {JSONException -> 0x0239, blocks: (B:3:0x0007, B:5:0x002a, B:9:0x0033, B:11:0x003b, B:13:0x0043, B:15:0x004b, B:17:0x0053, B:18:0x0057, B:20:0x006b, B:22:0x007c, B:24:0x008b, B:30:0x00a9, B:32:0x00af, B:34:0x00b5, B:38:0x00dc, B:40:0x00e2, B:45:0x00f0, B:46:0x00f5, B:51:0x0103, B:52:0x0108, B:57:0x0116, B:60:0x0121, B:62:0x0125, B:63:0x0129, B:65:0x0131, B:67:0x0137, B:69:0x0145, B:71:0x0150, B:55:0x0112, B:49:0x00ff, B:43:0x00ec, B:26:0x0096, B:28:0x009e), top: B:80:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:66:0x0135 A[PHI: r18
  0x0135: PHI (r18v2 int) = (r18v1 int), (r18v4 int) binds: [B:59:0x011f, B:64:0x012f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:69:0x0145 A[Catch: JSONException -> 0x0239, TryCatch #0 {JSONException -> 0x0239, blocks: (B:3:0x0007, B:5:0x002a, B:9:0x0033, B:11:0x003b, B:13:0x0043, B:15:0x004b, B:17:0x0053, B:18:0x0057, B:20:0x006b, B:22:0x007c, B:24:0x008b, B:30:0x00a9, B:32:0x00af, B:34:0x00b5, B:38:0x00dc, B:40:0x00e2, B:45:0x00f0, B:46:0x00f5, B:51:0x0103, B:52:0x0108, B:57:0x0116, B:60:0x0121, B:62:0x0125, B:63:0x0129, B:65:0x0131, B:67:0x0137, B:69:0x0145, B:71:0x0150, B:55:0x0112, B:49:0x00ff, B:43:0x00ec, B:26:0x0096, B:28:0x009e), top: B:80:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:70:0x014e  */
    public static zzacj zza(Context context, zzacf zzacfVar, String str) {
        int i;
        int iZza;
        String str2;
        zzacj zzacjVarZza;
        long j;
        String str3;
        JSONArray jSONArrayOptJSONArray;
        List<String> listZza;
        JSONArray jSONArrayOptJSONArray2;
        List<String> listZza2;
        JSONArray jSONArrayOptJSONArray3;
        List<String> list;
        List<String> listZza3;
        long j2;
        boolean zOptBoolean;
        String strOptString;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString2 = jSONObject.optString("ad_base_url", null);
            String strOptString3 = jSONObject.optString("ad_url", null);
            String strOptString4 = jSONObject.optString("ad_size", null);
            String strOptString5 = jSONObject.optString("ad_slot_size", strOptString4);
            boolean z = (zzacfVar == null || zzacfVar.zzm == 0) ? false : true;
            String strOptString6 = jSONObject.optString("ad_json", null);
            if (strOptString6 == null) {
                strOptString6 = jSONObject.optString("ad_html", null);
            }
            if (strOptString6 == null) {
                strOptString6 = jSONObject.optString(TtmlNode.TAG_BODY, null);
            }
            if (strOptString6 == null && jSONObject.has("ads")) {
                strOptString6 = jSONObject.toString();
            }
            String strOptString7 = jSONObject.optString("debug_dialog", null);
            String strOptString8 = jSONObject.optString("debug_signals", null);
            long j3 = jSONObject.has("interstitial_timeout") ? (long) (jSONObject.getDouble("interstitial_timeout") * 1000.0d) : -1L;
            String strOptString9 = jSONObject.optString("orientation", null);
            if (!"portrait".equals(strOptString9)) {
                if ("landscape".equals(strOptString9)) {
                    iZza = zzbt.zzg().zza();
                } else {
                    i = -1;
                }
                if (TextUtils.isEmpty(strOptString6) || TextUtils.isEmpty(strOptString3)) {
                    str2 = strOptString6;
                    zzacjVarZza = null;
                    j = -1;
                } else {
                    zzacjVarZza = zzadn.zza(zzacfVar, context, zzacfVar.zzk.zza, strOptString3, null, null, null, null);
                    strOptString2 = zzacjVarZza.zza;
                    String str4 = zzacjVarZza.zzb;
                    j = zzacjVarZza.zzm;
                    str2 = str4;
                }
                str3 = strOptString2;
                if (str2 == null) {
                    return new zzacj(0);
                }
                jSONArrayOptJSONArray = jSONObject.optJSONArray("click_urls");
                if (zzacjVarZza == null) {
                    listZza = null;
                } else {
                    listZza = zzacjVarZza.zzc;
                }
                if (jSONArrayOptJSONArray != null) {
                    listZza = zza(jSONArrayOptJSONArray, listZza);
                }
                jSONArrayOptJSONArray2 = jSONObject.optJSONArray("impression_urls");
                if (zzacjVarZza == null) {
                    listZza2 = null;
                } else {
                    listZza2 = zzacjVarZza.zze;
                }
                if (jSONArrayOptJSONArray2 != null) {
                    listZza2 = zza(jSONArrayOptJSONArray2, listZza2);
                }
                jSONArrayOptJSONArray3 = jSONObject.optJSONArray("manual_impression_urls");
                if (zzacjVarZza == null) {
                    list = null;
                } else {
                    list = zzacjVarZza.zzi;
                }
                if (jSONArrayOptJSONArray3 != null) {
                    listZza3 = zza(jSONArrayOptJSONArray3, list);
                } else {
                    listZza3 = list;
                }
                if (zzacjVarZza == null) {
                    j2 = j3;
                } else {
                    if (zzacjVarZza.zzk != -1) {
                        i = zzacjVarZza.zzk;
                    }
                    if (zzacjVarZza.zzf > 0) {
                        j2 = zzacjVarZza.zzf;
                    } else {
                        j2 = j3;
                    }
                }
                String strOptString10 = jSONObject.optString("active_view");
                zOptBoolean = jSONObject.optBoolean("ad_is_javascript", false);
                if (zOptBoolean) {
                    strOptString = jSONObject.optString("ad_passback_url", null);
                } else {
                    strOptString = null;
                }
                boolean zOptBoolean2 = jSONObject.optBoolean("mediation", false);
                boolean zOptBoolean3 = jSONObject.optBoolean("custom_render_allowed", false);
                boolean zOptBoolean4 = jSONObject.optBoolean("content_url_opted_out", true);
                boolean zOptBoolean5 = jSONObject.optBoolean("content_vertical_opted_out", true);
                boolean zOptBoolean6 = jSONObject.optBoolean("prefetch", false);
                long jOptLong = jSONObject.optLong("refresh_interval_milliseconds", -1L);
                return new zzacj(zzacfVar, str3, str2, listZza, listZza2, j2, zOptBoolean2, jSONObject.optLong("mediation_config_cache_time_milliseconds", -1L), listZza3, jOptLong, i, strOptString4, j, strOptString7, zOptBoolean, strOptString, strOptString10, zOptBoolean3, z, zzacfVar.zzp, zOptBoolean4, zOptBoolean6, jSONObject.optString("gws_query_id", ""), ToolboxAnalyticsModuleKt.M_HEIGHT.equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), zzagd.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), (List<String>) null), zza(jSONObject.optJSONArray("video_complete_urls"), (List<String>) null), jSONObject.optBoolean("use_displayed_impression", false), zzacl.zza(jSONObject.optJSONObject("auto_protection_configuration")), zzacfVar.zzag, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), (List<String>) null), jSONObject.optBoolean("render_in_browser", zzacfVar.zzak), strOptString5, zzagn.zza(jSONObject.optJSONObject("safe_browsing")), strOptString8, zOptBoolean5, zzacfVar.zzau, jSONObject.optBoolean("custom_close_blocked"), 0);
            }
            iZza = zzbt.zzg().zzb();
            i = iZza;
            if (TextUtils.isEmpty(strOptString6)) {
                str2 = strOptString6;
                zzacjVarZza = null;
                j = -1;
            } else {
                str2 = strOptString6;
                zzacjVarZza = null;
                j = -1;
            }
            str3 = strOptString2;
            if (str2 == null) {
                return new zzacj(0);
            }
            jSONArrayOptJSONArray = jSONObject.optJSONArray("click_urls");
            if (zzacjVarZza == null) {
                listZza = null;
            } else {
                listZza = zzacjVarZza.zzc;
            }
            if (jSONArrayOptJSONArray != null) {
                listZza = zza(jSONArrayOptJSONArray, listZza);
            }
            jSONArrayOptJSONArray2 = jSONObject.optJSONArray("impression_urls");
            if (zzacjVarZza == null) {
                listZza2 = null;
            } else {
                listZza2 = zzacjVarZza.zze;
            }
            if (jSONArrayOptJSONArray2 != null) {
                listZza2 = zza(jSONArrayOptJSONArray2, listZza2);
            }
            jSONArrayOptJSONArray3 = jSONObject.optJSONArray("manual_impression_urls");
            if (zzacjVarZza == null) {
                list = null;
            } else {
                list = zzacjVarZza.zzi;
            }
            if (jSONArrayOptJSONArray3 != null) {
                listZza3 = zza(jSONArrayOptJSONArray3, list);
            } else {
                listZza3 = list;
            }
            if (zzacjVarZza == null) {
                j2 = j3;
            } else {
                if (zzacjVarZza.zzk != -1) {
                    i = zzacjVarZza.zzk;
                }
                if (zzacjVarZza.zzf > 0) {
                    j2 = zzacjVarZza.zzf;
                } else {
                    j2 = j3;
                }
            }
            String strOptString11 = jSONObject.optString("active_view");
            zOptBoolean = jSONObject.optBoolean("ad_is_javascript", false);
            if (zOptBoolean) {
                strOptString = jSONObject.optString("ad_passback_url", null);
            } else {
                strOptString = null;
            }
            boolean zOptBoolean7 = jSONObject.optBoolean("mediation", false);
            boolean zOptBoolean8 = jSONObject.optBoolean("custom_render_allowed", false);
            boolean zOptBoolean9 = jSONObject.optBoolean("content_url_opted_out", true);
            boolean zOptBoolean10 = jSONObject.optBoolean("content_vertical_opted_out", true);
            boolean zOptBoolean11 = jSONObject.optBoolean("prefetch", false);
            long jOptLong2 = jSONObject.optLong("refresh_interval_milliseconds", -1L);
            return new zzacj(zzacfVar, str3, str2, listZza, listZza2, j2, zOptBoolean7, jSONObject.optLong("mediation_config_cache_time_milliseconds", -1L), listZza3, jOptLong2, i, strOptString4, j, strOptString7, zOptBoolean, strOptString, strOptString11, zOptBoolean8, z, zzacfVar.zzp, zOptBoolean9, zOptBoolean11, jSONObject.optString("gws_query_id", ""), ToolboxAnalyticsModuleKt.M_HEIGHT.equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), zzagd.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), (List<String>) null), zza(jSONObject.optJSONArray("video_complete_urls"), (List<String>) null), jSONObject.optBoolean("use_displayed_impression", false), zzacl.zza(jSONObject.optJSONObject("auto_protection_configuration")), zzacfVar.zzag, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), (List<String>) null), jSONObject.optBoolean("render_in_browser", zzacfVar.zzak), strOptString5, zzagn.zza(jSONObject.optJSONObject("safe_browsing")), strOptString8, zOptBoolean10, zzacfVar.zzau, jSONObject.optBoolean("custom_close_blocked"), 0);
        } catch (JSONException e) {
            String strValueOf = String.valueOf(e.getMessage());
            zzahw.zze(strValueOf.length() != 0 ? "Could not parse the inline ad response: ".concat(strValueOf) : new String("Could not parse the inline ad response: "));
            return new zzacj(0);
        }
    }

    private static Integer zza(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }

    private static List<String> zza(JSONArray jSONArray, List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new LinkedList<>();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    private static JSONArray zza(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        return jSONArray;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v35 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7, types: [int] */
    public static JSONObject zza(Context context, zzadl zzadlVar) {
        boolean z;
        Integer numValueOf;
        String str;
        Object objZza;
        boolean zIsLimitAdTrackingEnabled;
        String id;
        String str2;
        String str3;
        String str4;
        boolean z2;
        int i;
        int i2;
        String str5;
        Object obj;
        zzacf zzacfVar = zzadlVar.zzi;
        Location location = zzadlVar.zzd;
        zzaea zzaeaVar = zzadlVar.zzj;
        Bundle bundle = zzadlVar.zza;
        JSONObject jSONObject = zzadlVar.zzk;
        try {
            HashMap map = new HashMap();
            map.put("extra_caps", zzlc.zzf().zza(zzoi.zzem));
            if (zzadlVar.zzc.size() > 0) {
                map.put("eid", TextUtils.join(",", zzadlVar.zzc));
            }
            if (zzacfVar.zzb != null) {
                map.put("ad_pos", zzacfVar.zzb);
            }
            zzkk zzkkVar = zzacfVar.zzc;
            String strZza = zzahr.zza();
            if (strZza != null) {
                map.put("abf", strZza);
            }
            if (zzkkVar.zzb != -1) {
                map.put("cust_age", zza.format(new Date(zzkkVar.zzb)));
            }
            if (zzkkVar.zzc != null) {
                map.put("extras", zzkkVar.zzc);
            }
            if (zzkkVar.zzd != -1) {
                map.put("cust_gender", Integer.valueOf(zzkkVar.zzd));
            }
            if (zzkkVar.zze != null) {
                map.put("kw", zzkkVar.zze);
            }
            if (zzkkVar.zzg != -1) {
                map.put("tag_for_child_directed_treatment", Integer.valueOf(zzkkVar.zzg));
            }
            if (zzkkVar.zzf) {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzgw)).booleanValue()) {
                    str5 = "test_request";
                    obj = true;
                } else {
                    str5 = "adtest";
                    obj = DebugKt.DEBUG_PROPERTY_VALUE_ON;
                }
                map.put(str5, obj);
            }
            if (zzkkVar.zza >= 2) {
                if (zzkkVar.zzh) {
                    map.put("d_imp_hdr", 1);
                }
                if (!TextUtils.isEmpty(zzkkVar.zzi)) {
                    map.put("ppid", zzkkVar.zzi);
                }
            }
            if (zzkkVar.zza >= 3 && zzkkVar.zzl != null) {
                map.put(ImagesContract.URL, zzkkVar.zzl);
            }
            if (zzkkVar.zza >= 5) {
                if (zzkkVar.zzn != null) {
                    map.put("custom_targeting", zzkkVar.zzn);
                }
                if (zzkkVar.zzo != null) {
                    map.put("category_exclusions", zzkkVar.zzo);
                }
                if (zzkkVar.zzp != null) {
                    map.put("request_agent", zzkkVar.zzp);
                }
            }
            if (zzkkVar.zza >= 6 && zzkkVar.zzq != null) {
                map.put("request_pkg", zzkkVar.zzq);
            }
            if (zzkkVar.zza >= 7) {
                map.put("is_designed_for_families", Boolean.valueOf(zzkkVar.zzr));
            }
            if (zzacfVar.zzd.zzg != null) {
                zzko[] zzkoVarArr = zzacfVar.zzd.zzg;
                int length = zzkoVarArr.length;
                int i3 = 0;
                boolean z3 = false;
                boolean z4 = false;
                while (i3 < length) {
                    int i4 = length;
                    zzko zzkoVar = zzkoVarArr[i3];
                    zzko[] zzkoVarArr2 = zzkoVarArr;
                    if (!zzkoVar.zzi && !z3) {
                        map.put("format", zzkoVar.zza);
                        z3 = true;
                    }
                    if (zzkoVar.zzi && !z4) {
                        map.put("fluid", ToolboxAnalyticsModuleKt.M_HEIGHT);
                        z4 = true;
                    }
                    if (z3 && z4) {
                        break;
                    }
                    i3++;
                    length = i4;
                    zzkoVarArr = zzkoVarArr2;
                }
            } else {
                map.put("format", zzacfVar.zzd.zza);
                if (zzacfVar.zzd.zzi) {
                    map.put("fluid", ToolboxAnalyticsModuleKt.M_HEIGHT);
                }
            }
            if (zzacfVar.zzd.zze == -1) {
                map.put("smart_w", "full");
            }
            if (zzacfVar.zzd.zzb == -2) {
                map.put("smart_h", "auto");
            }
            if (zzacfVar.zzd.zzg != null) {
                StringBuilder sb = new StringBuilder();
                zzko[] zzkoVarArr3 = zzacfVar.zzd.zzg;
                int length2 = zzkoVarArr3.length;
                boolean z5 = false;
                int i5 = 0;
                while (i5 < length2) {
                    int i6 = length2;
                    zzko zzkoVar2 = zzkoVarArr3[i5];
                    zzko[] zzkoVarArr4 = zzkoVarArr3;
                    if (zzkoVar2.zzi) {
                        z5 = true;
                    } else {
                        if (sb.length() != 0) {
                            sb.append("|");
                        }
                        sb.append(zzkoVar2.zze == -1 ? (int) (zzkoVar2.zzf / zzaeaVar.zzs) : zzkoVar2.zze);
                        sb.append("x");
                        sb.append(zzkoVar2.zzb == -2 ? (int) (zzkoVar2.zzc / zzaeaVar.zzs) : zzkoVar2.zzb);
                    }
                    i5++;
                    length2 = i6;
                    zzkoVarArr3 = zzkoVarArr4;
                }
                if (z5) {
                    if (sb.length() != 0) {
                        i2 = 0;
                        sb.insert(0, "|");
                    } else {
                        i2 = 0;
                    }
                    sb.insert(i2, "320x50");
                }
                map.put("sz", sb);
            }
            if (zzacfVar.zzm != 0) {
                map.put("native_version", Integer.valueOf(zzacfVar.zzm));
                map.put("native_templates", zzacfVar.zzn);
                zzqh zzqhVar = zzacfVar.zzy;
                String str6 = "any";
                if (zzqhVar != null && (i = zzqhVar.zzc) != 0) {
                    str6 = i != 1 ? i != 2 ? "not_set" : "landscape" : "portrait";
                }
                map.put("native_image_orientation", str6);
                if (!zzacfVar.zzz.isEmpty()) {
                    map.put("native_custom_templates", zzacfVar.zzz);
                }
                if (zzacfVar.zza >= 24) {
                    map.put("max_num_ads", Integer.valueOf(zzacfVar.zzay));
                }
                if (!TextUtils.isEmpty(zzacfVar.zzaw)) {
                    try {
                        map.put("native_advanced_settings", new JSONArray(zzacfVar.zzaw));
                    } catch (JSONException e) {
                        zzahw.zzc("Problem creating json from native advanced settings", e);
                    }
                }
            }
            if (zzacfVar.zzav != null && zzacfVar.zzav.size() > 0) {
                for (Integer num : zzacfVar.zzav) {
                    if (num.intValue() == 2) {
                        str4 = "iba";
                        z2 = true;
                    } else if (num.intValue() == 1) {
                        str4 = "ina";
                        z2 = true;
                    }
                    map.put(str4, z2);
                }
            }
            if (zzacfVar.zzd.zzj) {
                map.put("ene", true);
            }
            if (((Boolean) zzlc.zzf().zza(zzoi.zzbt)).booleanValue()) {
                map.put("xsrve", true);
            }
            if (zzacfVar.zzao != null) {
                map.put("is_icon_ad", true);
                map.put("icon_ad_expansion_behavior", Integer.valueOf(zzacfVar.zzao.zza));
            }
            map.put("slotname", zzacfVar.zze);
            map.put("pn", zzacfVar.zzf.packageName);
            if (zzacfVar.zzg != null) {
                map.put("vc", Integer.valueOf(zzacfVar.zzg.versionCode));
            }
            map.put("ms", zzadlVar.zzg);
            map.put("seq_num", zzacfVar.zzi);
            map.put("session_id", zzacfVar.zzj);
            map.put("js", zzacfVar.zzk.zza);
            zzaek zzaekVar = zzadlVar.zze;
            Bundle bundle2 = zzacfVar.zzam;
            Bundle bundle3 = zzadlVar.zzb;
            map.put("am", Integer.valueOf(zzaeaVar.zza));
            map.put("cog", zza(zzaeaVar.zzb));
            map.put("coh", zza(zzaeaVar.zzc));
            if (!TextUtils.isEmpty(zzaeaVar.zzd)) {
                map.put("carrier", zzaeaVar.zzd);
            }
            map.put("gl", zzaeaVar.zze);
            if (zzaeaVar.zzf) {
                map.put("simulator", 1);
            }
            if (zzaeaVar.zzg) {
                map.put("is_sidewinder", 1);
            }
            map.put("ma", zza(zzaeaVar.zzh));
            map.put("sp", zza(zzaeaVar.zzi));
            map.put("hl", zzaeaVar.zzj);
            if (!TextUtils.isEmpty(zzaeaVar.zzk)) {
                map.put("mv", zzaeaVar.zzk);
            }
            map.put("muv", Integer.valueOf(zzaeaVar.zzm));
            if (zzaeaVar.zzn != -2) {
                map.put("cnt", Integer.valueOf(zzaeaVar.zzn));
            }
            map.put("gnt", Integer.valueOf(zzaeaVar.zzo));
            map.put("pt", Integer.valueOf(zzaeaVar.zzp));
            map.put("rm", Integer.valueOf(zzaeaVar.zzq));
            map.put("riv", Integer.valueOf(zzaeaVar.zzr));
            Bundle bundle4 = new Bundle();
            bundle4.putString("build_build", zzaeaVar.zzz);
            bundle4.putString("build_device", zzaeaVar.zzaa);
            Bundle bundle5 = new Bundle();
            bundle5.putBoolean("is_charging", zzaeaVar.zzw);
            bundle5.putDouble("battery_level", zzaeaVar.zzv);
            bundle4.putBundle("battery", bundle5);
            Bundle bundle6 = new Bundle();
            bundle6.putInt("active_network_state", zzaeaVar.zzy);
            bundle6.putBoolean("active_network_metered", zzaeaVar.zzx);
            if (zzaekVar != null) {
                Bundle bundle7 = new Bundle();
                bundle7.putInt("predicted_latency_micros", zzaekVar.zza);
                bundle7.putLong("predicted_down_throughput_bps", zzaekVar.zzb);
                bundle7.putLong("predicted_up_throughput_bps", zzaekVar.zzc);
                bundle6.putBundle("predictions", bundle7);
            }
            bundle4.putBundle("network", bundle6);
            Bundle bundle8 = new Bundle();
            bundle8.putBoolean("is_browser_custom_tabs_capable", zzaeaVar.zzab);
            bundle4.putBundle(Browser.TYPE, bundle8);
            if (bundle2 != null) {
                Bundle bundle9 = new Bundle();
                bundle9.putString("runtime_free", Long.toString(bundle2.getLong("runtime_free_memory", -1L)));
                bundle9.putString("runtime_max", Long.toString(bundle2.getLong("runtime_max_memory", -1L)));
                bundle9.putString("runtime_total", Long.toString(bundle2.getLong("runtime_total_memory", -1L)));
                z = false;
                bundle9.putString("web_view_count", Integer.toString(bundle2.getInt("web_view_count", 0)));
                Debug.MemoryInfo memoryInfo = (Debug.MemoryInfo) bundle2.getParcelable("debug_memory_info");
                if (memoryInfo != null) {
                    bundle9.putString("debug_info_dalvik_private_dirty", Integer.toString(memoryInfo.dalvikPrivateDirty));
                    bundle9.putString("debug_info_dalvik_pss", Integer.toString(memoryInfo.dalvikPss));
                    bundle9.putString("debug_info_dalvik_shared_dirty", Integer.toString(memoryInfo.dalvikSharedDirty));
                    bundle9.putString("debug_info_native_private_dirty", Integer.toString(memoryInfo.nativePrivateDirty));
                    bundle9.putString("debug_info_native_pss", Integer.toString(memoryInfo.nativePss));
                    bundle9.putString("debug_info_native_shared_dirty", Integer.toString(memoryInfo.nativeSharedDirty));
                    bundle9.putString("debug_info_other_private_dirty", Integer.toString(memoryInfo.otherPrivateDirty));
                    bundle9.putString("debug_info_other_pss", Integer.toString(memoryInfo.otherPss));
                    bundle9.putString("debug_info_other_shared_dirty", Integer.toString(memoryInfo.otherSharedDirty));
                }
                bundle4.putBundle("android_mem_info", bundle9);
            } else {
                z = false;
            }
            Bundle bundle10 = new Bundle();
            bundle10.putBundle("parental_controls", bundle3);
            if (!TextUtils.isEmpty(zzaeaVar.zzl)) {
                bundle10.putString("package_version", zzaeaVar.zzl);
            }
            bundle4.putBundle("play_store", bundle10);
            map.put(Device.TYPE, bundle4);
            Bundle bundle11 = new Bundle();
            bundle11.putString("doritos", zzadlVar.zzf);
            if (((Boolean) zzlc.zzf().zza(zzoi.zzcc)).booleanValue()) {
                if (zzadlVar.zzh != null) {
                    id = zzadlVar.zzh.getId();
                    zIsLimitAdTrackingEnabled = zzadlVar.zzh.isLimitAdTrackingEnabled();
                } else {
                    zIsLimitAdTrackingEnabled = z;
                    id = null;
                }
                if (TextUtils.isEmpty(id)) {
                    zzlc.zza();
                    bundle11.putString("pdid", zzako.zzb(context));
                    str2 = "pdidtype";
                    str3 = "ssaid";
                } else {
                    bundle11.putString("rdid", id);
                    bundle11.putBoolean("is_lat", zIsLimitAdTrackingEnabled);
                    str2 = "idtype";
                    str3 = "adid";
                }
                bundle11.putString(str2, str3);
            }
            map.put("pii", bundle11);
            map.put("platform", Build.MANUFACTURER);
            map.put("submodel", Build.MODEL);
            if (location != null) {
                zza((HashMap<String, Object>) map, location);
            } else if (zzacfVar.zzc.zza >= 2 && zzacfVar.zzc.zzk != null) {
                zza((HashMap<String, Object>) map, zzacfVar.zzc.zzk);
            }
            if (zzacfVar.zza >= 2) {
                map.put("quality_signals", zzacfVar.zzl);
            }
            if (zzacfVar.zza >= 4 && zzacfVar.zzp) {
                map.put("forceHttps", Boolean.valueOf(zzacfVar.zzp));
            }
            if (r15 != 0) {
                map.put("content_info", bundle);
            }
            if (zzacfVar.zza >= 5) {
                map.put("u_sd", Float.valueOf(zzacfVar.zzs));
                map.put("sh", Integer.valueOf(zzacfVar.zzr));
                numValueOf = Integer.valueOf(zzacfVar.zzq);
            } else {
                map.put("u_sd", Float.valueOf(zzaeaVar.zzs));
                map.put("sh", Integer.valueOf(zzaeaVar.zzu));
                numValueOf = Integer.valueOf(zzaeaVar.zzt);
            }
            map.put("sw", numValueOf);
            if (zzacfVar.zza >= 6) {
                if (!TextUtils.isEmpty(zzacfVar.zzt)) {
                    try {
                        map.put("view_hierarchy", new JSONObject(zzacfVar.zzt));
                    } catch (JSONException e2) {
                        zzahw.zzc("Problem serializing view hierarchy to JSON", e2);
                    }
                }
                map.put("correlation_id", Long.valueOf(zzacfVar.zzu));
            }
            if (zzacfVar.zza >= 7) {
                map.put("request_id", zzacfVar.zzv);
            }
            if (zzacfVar.zza >= 12 && !TextUtils.isEmpty(zzacfVar.zzab)) {
                map.put("anchor", zzacfVar.zzab);
            }
            if (zzacfVar.zza >= 13) {
                map.put("android_app_volume", Float.valueOf(zzacfVar.zzac));
            }
            if (zzacfVar.zza >= 18) {
                map.put("android_app_muted", Boolean.valueOf(zzacfVar.zzai));
            }
            if (zzacfVar.zza >= 14 && zzacfVar.zzad > 0) {
                map.put("target_api", Integer.valueOf(zzacfVar.zzad));
            }
            if (zzacfVar.zza >= 15) {
                map.put("scroll_index", Integer.valueOf(zzacfVar.zzae == -1 ? -1 : zzacfVar.zzae));
            }
            if (zzacfVar.zza >= 16) {
                map.put("_activity_context", Boolean.valueOf(zzacfVar.zzaf));
            }
            if (zzacfVar.zza >= 18) {
                if (!TextUtils.isEmpty(zzacfVar.zzaj)) {
                    try {
                        map.put("app_settings", new JSONObject(zzacfVar.zzaj));
                    } catch (JSONException e3) {
                        zzahw.zzc("Problem creating json from app settings", e3);
                    }
                }
                map.put("render_in_browser", Boolean.valueOf(zzacfVar.zzak));
            }
            if (zzacfVar.zza >= 18) {
                map.put("android_num_video_cache_tasks", Integer.valueOf(zzacfVar.zzal));
            }
            zzala zzalaVar = zzacfVar.zzk;
            boolean z6 = zzacfVar.zzaz;
            boolean z7 = zzadlVar.zzl;
            boolean z8 = zzacfVar.zzbb;
            Bundle bundle12 = new Bundle();
            Bundle bundle13 = new Bundle();
            bundle13.putString("cl", "189396757");
            bundle13.putString("rapid_rc", "dev");
            bundle13.putString("rapid_rollup", "HEAD");
            bundle12.putBundle("build_meta", bundle13);
            bundle12.putString("mf", Boolean.toString(((Boolean) zzlc.zzf().zza(zzoi.zzeo)).booleanValue()));
            bundle12.putBoolean("instant_app", z6);
            bundle12.putBoolean("lite", zzalaVar.zze);
            bundle12.putBoolean("local_service", z7);
            bundle12.putBoolean("is_privileged_process", z8);
            map.put("sdk_env", bundle12);
            map.put("cache_state", jSONObject);
            if (zzacfVar.zza >= 19) {
                map.put("gct", zzacfVar.zzan);
            }
            if (zzacfVar.zza >= 21 && zzacfVar.zzap) {
                map.put("de", LocalSearchProvider.LIVE_CONTENTS);
            }
            if (((Boolean) zzlc.zzf().zza(zzoi.zzcl)).booleanValue()) {
                String str7 = zzacfVar.zzd.zza;
                boolean z9 = (str7.equals("interstitial_mb") || str7.equals("reward_mb")) ? true : z;
                Bundle bundle14 = zzacfVar.zzaq;
                boolean z10 = bundle14 != null ? true : z;
                if (z9 && z10) {
                    Bundle bundle15 = new Bundle();
                    bundle15.putBundle("interstitial_pool", bundle14);
                    map.put("counters", bundle15);
                }
            }
            if (zzacfVar.zzar != null) {
                map.put("gmp_app_id", zzacfVar.zzar);
            }
            if (zzacfVar.zzas == null) {
                str = "fbs_aiid";
                objZza = "";
            } else if ("TIME_OUT".equals(zzacfVar.zzas)) {
                str = "sai_timeout";
                objZza = zzlc.zzf().zza(zzoi.zzbr);
            } else {
                str = "fbs_aiid";
                objZza = zzacfVar.zzas;
            }
            map.put(str, objZza);
            if (zzacfVar.zzat != null) {
                map.put("fbs_aeid", zzacfVar.zzat);
            }
            if (zzacfVar.zza >= 24) {
                map.put("disable_ml", Boolean.valueOf(zzacfVar.zzba));
            }
            String str8 = (String) zzlc.zzf().zza(zzoi.zzaa);
            if (str8 != null && !str8.isEmpty()) {
                if (Build.VERSION.SDK_INT >= ((Integer) zzlc.zzf().zza(zzoi.zzab)).intValue()) {
                    HashMap map2 = new HashMap();
                    String[] strArrSplit = str8.split(",");
                    int length3 = strArrSplit.length;
                    for (?? r13 = z; r13 < length3; r13++) {
                        String str9 = strArrSplit[r13];
                        map2.put(str9, zzakm.zza(str9));
                    }
                    map.put("video_decoders", map2);
                }
            }
            if (zzahw.zza(2)) {
                String strValueOf = String.valueOf(zzbt.zze().zza(map).toString(2));
                zzahw.zza(strValueOf.length() != 0 ? "Ad Request JSON: ".concat(strValueOf) : new String("Ad Request JSON: "));
            }
            return zzbt.zze().zza(map);
        } catch (JSONException e4) {
            String strValueOf2 = String.valueOf(e4.getMessage());
            zzahw.zze(strValueOf2.length() != 0 ? "Problem serializing ad request to JSON: ".concat(strValueOf2) : new String("Problem serializing ad request to JSON: "));
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0085  */
    /* JADX WARN: Code duplicated, block: B:33:0x0094  */
    /* JADX WARN: Code duplicated, block: B:36:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:39:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:42:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:45:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:48:0x0101  */
    /* JADX WARN: Code duplicated, block: B:51:0x0110  */
    /* JADX WARN: Code duplicated, block: B:54:0x011b  */
    /* JADX WARN: Code duplicated, block: B:55:0x011e  */
    /* JADX WARN: Code duplicated, block: B:58:0x0130  */
    /* JADX WARN: Code duplicated, block: B:61:0x013f  */
    /* JADX WARN: Code duplicated, block: B:64:0x014e  */
    public static JSONObject zza(zzacj zzacjVar) throws JSONException {
        String str;
        String str2;
        String str3;
        String str4;
        JSONObject jSONObject = new JSONObject();
        if (zzacjVar.zza != null) {
            jSONObject.put("ad_base_url", zzacjVar.zza);
        }
        if (zzacjVar.zzl != null) {
            jSONObject.put("ad_size", zzacjVar.zzl);
        }
        jSONObject.put("native", zzacjVar.zzs);
        if (zzacjVar.zzs) {
            str = zzacjVar.zzb;
            str2 = "ad_json";
        } else {
            str = zzacjVar.zzb;
            str2 = "ad_html";
        }
        jSONObject.put(str2, str);
        if (zzacjVar.zzn != null) {
            jSONObject.put("debug_dialog", zzacjVar.zzn);
        }
        if (zzacjVar.zzal != null) {
            jSONObject.put("debug_signals", zzacjVar.zzal);
        }
        if (zzacjVar.zzf != -1) {
            jSONObject.put("interstitial_timeout", zzacjVar.zzf / 1000.0d);
        }
        if (zzacjVar.zzk != zzbt.zzg().zzb()) {
            str4 = zzacjVar.zzk == zzbt.zzg().zza() ? "landscape" : "portrait";
            if (zzacjVar.zzc != null) {
                jSONObject.put("click_urls", zza(zzacjVar.zzc));
            }
            if (zzacjVar.zze != null) {
                jSONObject.put("impression_urls", zza(zzacjVar.zze));
            }
            if (zzacjVar.zzi != null) {
                jSONObject.put("manual_impression_urls", zza(zzacjVar.zzi));
            }
            if (zzacjVar.zzq != null) {
                jSONObject.put("active_view", zzacjVar.zzq);
            }
            jSONObject.put("ad_is_javascript", zzacjVar.zzo);
            if (zzacjVar.zzp != null) {
                jSONObject.put("ad_passback_url", zzacjVar.zzp);
            }
            jSONObject.put("mediation", zzacjVar.zzg);
            jSONObject.put("custom_render_allowed", zzacjVar.zzr);
            jSONObject.put("content_url_opted_out", zzacjVar.zzu);
            jSONObject.put("content_vertical_opted_out", zzacjVar.zzam);
            jSONObject.put("prefetch", zzacjVar.zzv);
            if (zzacjVar.zzj != -1) {
                jSONObject.put("refresh_interval_milliseconds", zzacjVar.zzj);
            }
            if (zzacjVar.zzh != -1) {
                jSONObject.put("mediation_config_cache_time_milliseconds", zzacjVar.zzh);
            }
            if (!TextUtils.isEmpty(zzacjVar.zzx)) {
                jSONObject.put("gws_query_id", zzacjVar.zzx);
            }
            if (zzacjVar.zzy) {
                str3 = ToolboxAnalyticsModuleKt.M_HEIGHT;
            } else {
                str3 = "";
            }
            jSONObject.put("fluid", str3);
            jSONObject.put("native_express", zzacjVar.zzz);
            if (zzacjVar.zzab != null) {
                jSONObject.put("video_start_urls", zza(zzacjVar.zzab));
            }
            if (zzacjVar.zzac != null) {
                jSONObject.put("video_complete_urls", zza(zzacjVar.zzac));
            }
            if (zzacjVar.zzaa != null) {
                zzagd zzagdVar = zzacjVar.zzaa;
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("rb_type", zzagdVar.zza);
                jSONObject2.put("rb_amount", zzagdVar.zzb);
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject2);
                jSONObject.put("rewards", jSONArray);
            }
            jSONObject.put("use_displayed_impression", zzacjVar.zzad);
            jSONObject.put("auto_protection_configuration", zzacjVar.zzae);
            jSONObject.put("render_in_browser", zzacjVar.zzai);
            return jSONObject;
        }
        jSONObject.put("orientation", str4);
        if (zzacjVar.zzc != null) {
            jSONObject.put("click_urls", zza(zzacjVar.zzc));
        }
        if (zzacjVar.zze != null) {
            jSONObject.put("impression_urls", zza(zzacjVar.zze));
        }
        if (zzacjVar.zzi != null) {
            jSONObject.put("manual_impression_urls", zza(zzacjVar.zzi));
        }
        if (zzacjVar.zzq != null) {
            jSONObject.put("active_view", zzacjVar.zzq);
        }
        jSONObject.put("ad_is_javascript", zzacjVar.zzo);
        if (zzacjVar.zzp != null) {
            jSONObject.put("ad_passback_url", zzacjVar.zzp);
        }
        jSONObject.put("mediation", zzacjVar.zzg);
        jSONObject.put("custom_render_allowed", zzacjVar.zzr);
        jSONObject.put("content_url_opted_out", zzacjVar.zzu);
        jSONObject.put("content_vertical_opted_out", zzacjVar.zzam);
        jSONObject.put("prefetch", zzacjVar.zzv);
        if (zzacjVar.zzj != -1) {
            jSONObject.put("refresh_interval_milliseconds", zzacjVar.zzj);
        }
        if (zzacjVar.zzh != -1) {
            jSONObject.put("mediation_config_cache_time_milliseconds", zzacjVar.zzh);
        }
        if (!TextUtils.isEmpty(zzacjVar.zzx)) {
            jSONObject.put("gws_query_id", zzacjVar.zzx);
        }
        if (zzacjVar.zzy) {
            str3 = ToolboxAnalyticsModuleKt.M_HEIGHT;
        } else {
            str3 = "";
        }
        jSONObject.put("fluid", str3);
        jSONObject.put("native_express", zzacjVar.zzz);
        if (zzacjVar.zzab != null) {
            jSONObject.put("video_start_urls", zza(zzacjVar.zzab));
        }
        if (zzacjVar.zzac != null) {
            jSONObject.put("video_complete_urls", zza(zzacjVar.zzac));
        }
        if (zzacjVar.zzaa != null) {
            zzagd zzagdVar2 = zzacjVar.zzaa;
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("rb_type", zzagdVar2.zza);
            jSONObject3.put("rb_amount", zzagdVar2.zzb);
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(jSONObject3);
            jSONObject.put("rewards", jSONArray2);
        }
        jSONObject.put("use_displayed_impression", zzacjVar.zzad);
        jSONObject.put("auto_protection_configuration", zzacjVar.zzae);
        jSONObject.put("render_in_browser", zzacjVar.zzai);
        return jSONObject;
    }

    private static void zza(HashMap<String, Object> map, Location location) {
        HashMap map2 = new HashMap();
        Float fValueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long lValueOf = Long.valueOf(location.getTime() * 1000);
        Long lValueOf2 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long lValueOf3 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        map2.put("radius", fValueOf);
        map2.put("lat", lValueOf2);
        map2.put("long", lValueOf3);
        map2.put("time", lValueOf);
        map.put("uule", map2);
    }
}
