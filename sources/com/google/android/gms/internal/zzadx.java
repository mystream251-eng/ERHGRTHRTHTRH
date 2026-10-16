package com.google.android.gms.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzadx {
    private String zza;
    private zzacl zzaa;
    private String zzac;
    private List<String> zzad;
    private boolean zzae;
    private String zzaf;
    private zzagn zzag;
    private boolean zzah;
    private boolean zzai;
    private final zzacf zzaj;
    private String zzb;
    private String zzc;
    private List<String> zzd;
    private String zze;
    private String zzf;
    private String zzg;
    private List<String> zzh;
    private List<String> zzl;
    private zzagd zzw;
    private List<String> zzx;
    private List<String> zzy;
    private long zzi = -1;
    private boolean zzj = false;
    private final long zzk = -1;
    private long zzm = -1;
    private int zzn = -1;
    private boolean zzo = false;
    private boolean zzp = false;
    private boolean zzq = false;
    private boolean zzr = true;
    private boolean zzs = true;
    private String zzt = "";
    private boolean zzu = false;
    private boolean zzv = false;
    private boolean zzz = false;
    private boolean zzab = false;

    public zzadx(zzacf zzacfVar, String str) {
        this.zzb = str;
        this.zzaj = zzacfVar;
    }

    private static String zza(Map<String, List<String>> map, String str) {
        List<String> list = map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static long zzb(Map<String, List<String>> map, String str) {
        List<String> list = map.get(str);
        if (list == null || list.isEmpty()) {
            return -1L;
        }
        String str2 = list.get(0);
        try {
            return (long) (Float.parseFloat(str2) * 1000.0f);
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 36 + String.valueOf(str2).length());
            sb.append("Could not parse float from ");
            sb.append(str);
            sb.append(" header: ");
            sb.append(str2);
            zzahw.zze(sb.toString());
            return -1L;
        }
    }

    private static List<String> zzc(Map<String, List<String>> map, String str) {
        String str2;
        List<String> list = map.get(str);
        if (list == null || list.isEmpty() || (str2 = list.get(0)) == null) {
            return null;
        }
        return Arrays.asList(str2.trim().split("\\s+"));
    }

    private static boolean zzd(Map<String, List<String>> map, String str) {
        List<String> list = map.get(str);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return Boolean.valueOf(list.get(0)).booleanValue();
    }

    public final zzacj zza(long j, boolean z) {
        return new zzacj(this.zzaj, this.zzb, this.zzc, this.zzd, this.zzh, this.zzi, this.zzj, -1L, this.zzl, this.zzm, this.zzn, this.zza, j, this.zzf, this.zzg, this.zzo, this.zzp, this.zzq, this.zzr, false, this.zzt, this.zzu, this.zzv, this.zzw, this.zzx, this.zzy, this.zzz, this.zzaa, this.zzab, this.zzac, this.zzad, this.zzae, this.zzaf, this.zzag, this.zze, this.zzs, this.zzah, this.zzai, z ? 2 : 1);
    }

    public final void zza(String str, Map<String, List<String>> map, String str2) {
        this.zzc = str2;
        zza(map);
    }

    public final void zza(Map<String, List<String>> map) {
        int iZza;
        this.zza = zza(map, "X-Afma-Ad-Size");
        this.zzaf = zza(map, "X-Afma-Ad-Slot-Size");
        List<String> listZzc = zzc(map, "X-Afma-Click-Tracking-Urls");
        if (listZzc != null) {
            this.zzd = listZzc;
        }
        this.zze = zza(map, "X-Afma-Debug-Signals");
        List<String> list = map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.zzf = list.get(0);
        }
        List<String> listZzc2 = zzc(map, "X-Afma-Tracking-Urls");
        if (listZzc2 != null) {
            this.zzh = listZzc2;
        }
        long jZzb = zzb(map, "X-Afma-Interstitial-Timeout");
        if (jZzb != -1) {
            this.zzi = jZzb;
        }
        this.zzj |= zzd(map, "X-Afma-Mediation");
        List<String> listZzc3 = zzc(map, "X-Afma-Manual-Tracking-Urls");
        if (listZzc3 != null) {
            this.zzl = listZzc3;
        }
        long jZzb2 = zzb(map, "X-Afma-Refresh-Rate");
        if (jZzb2 != -1) {
            this.zzm = jZzb2;
        }
        List<String> list2 = map.get("X-Afma-Orientation");
        if (list2 != null && !list2.isEmpty()) {
            String str = list2.get(0);
            if ("portrait".equalsIgnoreCase(str)) {
                iZza = zzbt.zzg().zzb();
            } else if ("landscape".equalsIgnoreCase(str)) {
                iZza = zzbt.zzg().zza();
            }
            this.zzn = iZza;
        }
        this.zzg = zza(map, "X-Afma-ActiveView");
        List<String> list3 = map.get("X-Afma-Use-HTTPS");
        if (list3 != null && !list3.isEmpty()) {
            this.zzq = Boolean.valueOf(list3.get(0)).booleanValue();
        }
        this.zzo |= zzd(map, "X-Afma-Custom-Rendering-Allowed");
        this.zzp = "native".equals(zza(map, "X-Afma-Ad-Format"));
        List<String> list4 = map.get("X-Afma-Content-Url-Opted-Out");
        if (list4 != null && !list4.isEmpty()) {
            this.zzr = Boolean.valueOf(list4.get(0)).booleanValue();
        }
        List<String> list5 = map.get("X-Afma-Content-Vertical-Opted-Out");
        if (list5 != null && !list5.isEmpty()) {
            this.zzs = Boolean.valueOf(list5.get(0)).booleanValue();
        }
        List<String> list6 = map.get("X-Afma-Gws-Query-Id");
        if (list6 != null && !list6.isEmpty()) {
            this.zzt = list6.get(0);
        }
        String strZza = zza(map, "X-Afma-Fluid");
        if (strZza != null && strZza.equals(ToolboxAnalyticsModuleKt.M_HEIGHT)) {
            this.zzu = true;
        }
        this.zzv = "native_express".equals(zza(map, "X-Afma-Ad-Format"));
        this.zzw = zzagd.zza(zza(map, "X-Afma-Rewards"));
        if (this.zzx == null) {
            this.zzx = zzc(map, "X-Afma-Reward-Video-Start-Urls");
        }
        if (this.zzy == null) {
            this.zzy = zzc(map, "X-Afma-Reward-Video-Complete-Urls");
        }
        this.zzz |= zzd(map, "X-Afma-Use-Displayed-Impression");
        this.zzab |= zzd(map, "X-Afma-Auto-Collect-Location");
        this.zzac = zza(map, "Set-Cookie");
        String strZza2 = zza(map, "X-Afma-Auto-Protection-Configuration");
        if (strZza2 == null || TextUtils.isEmpty(strZza2)) {
            Uri.Builder builderBuildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
            builderBuildUpon.appendQueryParameter("id", "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty(this.zzf)) {
                builderBuildUpon.appendQueryParameter("debugDialog", this.zzf);
            }
            boolean zBooleanValue = ((Boolean) zzlc.zzf().zza(zzoi.zze)).booleanValue();
            String string = builderBuildUpon.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 31);
            sb.append(string);
            sb.append("&navigationURL={NAVIGATION_URL}");
            this.zzaa = new zzacl(zBooleanValue, Arrays.asList(sb.toString()));
        } else {
            try {
                this.zzaa = zzacl.zza(new JSONObject(strZza2));
            } catch (JSONException e) {
                zzahw.zzc("Error parsing configuration JSON", e);
                this.zzaa = new zzacl();
            }
        }
        List<String> listZzc4 = zzc(map, "X-Afma-Remote-Ping-Urls");
        if (listZzc4 != null) {
            this.zzad = listZzc4;
        }
        String strZza3 = zza(map, "X-Afma-Safe-Browsing");
        if (!TextUtils.isEmpty(strZza3)) {
            try {
                this.zzag = zzagn.zza(new JSONObject(strZza3));
            } catch (JSONException e2) {
                zzahw.zzc("Error parsing safe browsing header", e2);
            }
        }
        this.zzae |= zzd(map, "X-Afma-Render-In-Browser");
        String strZza4 = zza(map, "X-Afma-Pool");
        if (!TextUtils.isEmpty(strZza4)) {
            try {
                this.zzah = new JSONObject(strZza4).getBoolean("never_pool");
            } catch (JSONException e3) {
                zzahw.zzc("Error parsing interstitial pool header", e3);
            }
        }
        this.zzai = zzd(map, "X-Afma-Custom-Close-Blocked");
    }
}
