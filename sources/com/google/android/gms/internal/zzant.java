package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import tv.broadpeak.smartlib.ad.AdInteractionType;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzant implements zzt<zzann> {
    private boolean zza;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = map.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            zzlc.zza();
            return zzako.zza(context, Integer.parseInt(str2));
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34 + String.valueOf(str2).length());
            sb.append("Could not parse ");
            sb.append(str);
            sb.append(" in a video GMSG: ");
            sb.append(str2);
            zzahw.zze(sb.toString());
            return i;
        }
    }

    private static void zza(zzanb zzanbVar, Map<String, String> map) {
        String str = map.get("minBufferMs");
        String str2 = map.get("maxBufferMs");
        String str3 = map.get("bufferForPlaybackMs");
        String str4 = map.get("bufferForPlaybackAfterRebufferMs");
        if (str != null) {
            try {
                Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                zzahw.zze(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", str, str2));
                return;
            }
        }
        if (str2 != null) {
            Integer.parseInt(str2);
        }
        if (str3 != null) {
            Integer.parseInt(str3);
        }
        if (str4 != null) {
            Integer.parseInt(str4);
        }
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzann zzannVar, Map map) {
        int iMin;
        zzann zzannVar2 = zzannVar;
        String str = (String) map.get("action");
        if (str == null) {
            zzahw.zze("Action missing from video GMSG.");
            return;
        }
        if (zzahw.zza(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String string = jSONObject.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(string).length());
            sb.append("Video GMSG: ");
            sb.append(str);
            sb.append(" ");
            sb.append(string);
            zzahw.zzb(sb.toString());
        }
        if ("background".equals(str)) {
            String str2 = (String) map.get(TtmlNode.ATTR_TTS_COLOR);
            if (TextUtils.isEmpty(str2)) {
                zzahw.zze("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                zzannVar2.setBackgroundColor(Color.parseColor(str2));
                return;
            } catch (IllegalArgumentException unused) {
                zzahw.zze("Invalid color parameter in video GMSG.");
                return;
            }
        }
        int i = 0;
        if ("decoderProps".equals(str)) {
            String str3 = (String) map.get("mimeTypes");
            if (str3 == null) {
                zzahw.zze("No MIME types specified for decoder properties inspection.");
                zzanb.zza(zzannVar2, "missingMimeTypes");
                return;
            }
            if (Build.VERSION.SDK_INT < 16) {
                zzahw.zze("Video decoder properties available on API versions >= 16.");
                zzanb.zza(zzannVar2, "deficientApiVersion");
                return;
            }
            HashMap map2 = new HashMap();
            String[] strArrSplit = str3.split(",");
            int length = strArrSplit.length;
            while (i < length) {
                String str4 = strArrSplit[i];
                map2.put(str4, zzakm.zza(str4.trim()));
                i++;
            }
            zzanb.zza(zzannVar2, map2);
            return;
        }
        zzane zzaneVarZza = zzannVar2.zza();
        if (zzaneVarZza == null) {
            zzahw.zze("Could not get underlay container for a video GMSG.");
            return;
        }
        boolean zEquals = "new".equals(str);
        boolean zEquals2 = "position".equals(str);
        if (zEquals || zEquals2) {
            Context context = zzannVar2.getContext();
            int iZza = zza(context, map, "x", 0);
            int iZza2 = zza(context, map, "y", 0);
            int iZza3 = zza(context, map, "w", -1);
            int iZza4 = zza(context, map, "h", -1);
            if (((Boolean) zzlc.zzf().zza(zzoi.zzex)).booleanValue()) {
                iZza3 = Math.min(iZza3, zzannVar2.zzk() - iZza);
                iMin = Math.min(iZza4, zzannVar2.zzj() - iZza2);
            } else {
                iMin = iZza4;
            }
            try {
                i = Integer.parseInt((String) map.get("player"));
            } catch (NumberFormatException unused2) {
            }
            int i2 = i;
            boolean z = Boolean.parseBoolean((String) map.get("spherical"));
            if (!zEquals || zzaneVarZza.zza() != null) {
                zzaneVarZza.zza(iZza, iZza2, iZza3, iMin);
                return;
            }
            zzaneVarZza.zza(iZza, iZza2, iZza3, iMin, i2, z, new zzanm((String) map.get(NotificationsContract.COLUMN_FLAGS)));
            zzanb zzanbVarZza = zzaneVarZza.zza();
            if (zzanbVarZza != null) {
                zza(zzanbVarZza, (Map<String, String>) map);
                return;
            }
            return;
        }
        zzanb zzanbVarZza2 = zzaneVarZza.zza();
        if (zzanbVarZza2 == null) {
            zzanb.zza(zzannVar2);
            return;
        }
        if (AdInteractionType.CLICK.equals(str)) {
            Context context2 = zzannVar2.getContext();
            int iZza5 = zza(context2, map, "x", 0);
            int iZza6 = zza(context2, map, "y", 0);
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, iZza5, iZza6, 0);
            zzanbVarZza2.zza(motionEventObtain);
            motionEventObtain.recycle();
            return;
        }
        if ("currentTime".equals(str)) {
            String str5 = (String) map.get("time");
            if (str5 == null) {
                zzahw.zze("Time parameter missing from currentTime video GMSG.");
                return;
            }
            try {
                zzanbVarZza2.zza((int) (Float.parseFloat(str5) * 1000.0f));
                return;
            } catch (NumberFormatException unused3) {
                String strValueOf = String.valueOf(str5);
                zzahw.zze(strValueOf.length() != 0 ? "Could not parse time parameter from currentTime video GMSG: ".concat(strValueOf) : new String("Could not parse time parameter from currentTime video GMSG: "));
                return;
            }
        }
        if ("hide".equals(str)) {
            zzanbVarZza2.setVisibility(4);
            return;
        }
        if ("load".equals(str)) {
            zzanbVarZza2.zzh();
            return;
        }
        if ("loadControl".equals(str)) {
            zza(zzanbVarZza2, (Map<String, String>) map);
            return;
        }
        if ("muted".equals(str)) {
            if (Boolean.parseBoolean((String) map.get("muted"))) {
                zzanbVarZza2.zzk();
                return;
            } else {
                zzanbVarZza2.zzl();
                return;
            }
        }
        if (ToolboxAnalyticsModuleKt.EVENT_PAUSE.equals(str)) {
            zzanbVarZza2.zzi();
            return;
        }
        if ("play".equals(str)) {
            zzanbVarZza2.zzj();
            return;
        }
        if ("show".equals(str)) {
            zzanbVarZza2.setVisibility(0);
            return;
        }
        if ("src".equals(str)) {
            zzanbVarZza2.zza((String) map.get("src"));
            return;
        }
        if ("touchMove".equals(str)) {
            Context context3 = zzannVar2.getContext();
            zzanbVarZza2.zza(zza(context3, map, "dx", 0), zza(context3, map, "dy", 0));
            if (this.zza) {
                return;
            }
            zzannVar2.zzf();
            this.zza = true;
            return;
        }
        if (!"volume".equals(str)) {
            if ("watermark".equals(str)) {
                zzanbVarZza2.zzm();
                return;
            } else {
                String strValueOf2 = String.valueOf(str);
                zzahw.zze(strValueOf2.length() != 0 ? "Unknown video action: ".concat(strValueOf2) : new String("Unknown video action: "));
                return;
            }
        }
        String str6 = (String) map.get("volume");
        if (str6 == null) {
            zzahw.zze("Level parameter missing from volume video GMSG.");
            return;
        }
        try {
            zzanbVarZza2.zza(Float.parseFloat(str6));
        } catch (NumberFormatException unused4) {
            String strValueOf3 = String.valueOf(str6);
            zzahw.zze(strValueOf3.length() != 0 ? "Could not parse volume parameter from volume video GMSG: ".concat(strValueOf3) : new String("Could not parse volume parameter from volume video GMSG: "));
        }
    }
}
