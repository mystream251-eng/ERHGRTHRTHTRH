package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.ads.internal.zzbt;
import com.viaccessorca.drm.impl.JniCDxJavaHttpAgent;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaji {
    private final Object zza = new Object();
    private String zzb = "";
    private String zzc = "";
    private boolean zzd = false;
    private String zze = "";

    private final String zza(Context context) {
        String str;
        synchronized (this.zza) {
            if (TextUtils.isEmpty(this.zzb)) {
                zzbt.zze();
                String strZzb = zzaij.zzb(context, "debug_signals_id.txt");
                this.zzb = strZzb;
                if (TextUtils.isEmpty(strZzb)) {
                    zzbt.zze();
                    this.zzb = zzaij.zza();
                    zzbt.zze();
                    zzaij.zzc(context, "debug_signals_id.txt", this.zzb);
                }
            }
            str = this.zzb;
        }
        return str;
    }

    private final void zza(Context context, String str, boolean z, boolean z2) {
        if (context instanceof Activity) {
            zzaij.zza.post(new zzajj(this, context, str, z, z2));
        } else {
            zzahw.zzd("Can not create dialog without Activity Context");
        }
    }

    private final boolean zzb(Context context, String str, String str2) {
        String strZzd = zzd(context, zzc(context, (String) zzlc.zzf().zza(zzoi.zzgh), str, str2).toString(), str2);
        if (TextUtils.isEmpty(strZzd)) {
            zzahw.zzb("Not linked for in app preview.");
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(strZzd.trim());
            String strOptString = jSONObject.optString("gct");
            this.zze = jSONObject.optString(NotificationCompat.CATEGORY_STATUS);
            synchronized (this.zza) {
                this.zzc = strOptString;
            }
            return true;
        } catch (JSONException e) {
            zzahw.zzc("Fail to get in app preview response json.", e);
            return false;
        }
    }

    private final Uri zzc(Context context, String str, String str2, String str3) {
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        builderBuildUpon.appendQueryParameter("linkedDeviceId", zza(context));
        builderBuildUpon.appendQueryParameter("adSlotPath", str2);
        builderBuildUpon.appendQueryParameter("afmaVersion", str3);
        return builderBuildUpon.build();
    }

    private final boolean zzc(Context context, String str, String str2) {
        String strZzd = zzd(context, zzc(context, (String) zzlc.zzf().zza(zzoi.zzgi), str, str2).toString(), str2);
        if (TextUtils.isEmpty(strZzd)) {
            zzahw.zzb("Not linked for debug signals.");
            return false;
        }
        try {
            boolean zEquals = LocalSearchProvider.LIVE_CONTENTS.equals(new JSONObject(strZzd.trim()).optString("debug_mode"));
            synchronized (this.zza) {
                this.zzd = zEquals;
            }
            return zEquals;
        } catch (JSONException e) {
            zzahw.zzc("Fail to get debug mode response json.", e);
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0072 A[PHI: r0 r1 r4
  0x0072: PHI (r0v3 'e' java.lang.Throwable) = (r0v2 'e' java.lang.Throwable), (r0v5 'e' java.lang.Throwable) binds: [B:18:0x0070, B:13:0x005c] A[DONT_GENERATE, DONT_INLINE]
  0x0072: PHI (r1v3 java.lang.String) = (r1v2 java.lang.String), (r1v4 java.lang.String) binds: [B:18:0x0070, B:13:0x005c] A[DONT_GENERATE, DONT_INLINE]
  0x0072: PHI (r4v7 java.lang.String) = (r4v5 java.lang.String), (r4v10 java.lang.String) binds: [B:18:0x0070, B:13:0x005c] A[DONT_GENERATE, DONT_INLINE]] */
    private static String zzd(Context context, String str, String str2) {
        String str3;
        String strValueOf;
        String str4;
        HashMap map = new HashMap();
        map.put(JniCDxJavaHttpAgent.DX_USER_AGENT_HDR, zzbt.zze().zza(context, str2));
        zzalt<String> zzaltVarZza = new zzajr(context).zza(str, map);
        try {
            return zzaltVarZza.get(((Integer) zzlc.zzf().zza(zzoi.zzgk)).intValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e = e;
            str3 = "Interrupted while retriving a response from: ";
            strValueOf = String.valueOf(str);
            if (strValueOf.length() != 0) {
                str4 = str3.concat(strValueOf);
            } else {
                str4 = new String("Interrupted while retriving a response from: ");
            }
            zzahw.zzb(str4, e);
            zzaltVarZza.cancel(true);
            return null;
        } catch (TimeoutException e2) {
            e = e2;
            str3 = "Timeout while retriving a response from: ";
            strValueOf = String.valueOf(str);
            if (strValueOf.length() != 0) {
                str4 = str3.concat(strValueOf);
            } else {
                str4 = new String("Timeout while retriving a response from: ");
            }
            zzahw.zzb(str4, e);
            zzaltVarZza.cancel(true);
            return null;
        } catch (Exception e3) {
            String strValueOf2 = String.valueOf(str);
            zzahw.zzb(strValueOf2.length() != 0 ? "Error retriving a response from: ".concat(strValueOf2) : new String("Error retriving a response from: "), e3);
            return null;
        }
    }

    private final void zze(Context context, String str, String str2) {
        zzbt.zze();
        zzaij.zza(context, zzc(context, (String) zzlc.zzf().zza(zzoi.zzgg), str, str2));
    }

    public final String zza() {
        String str;
        synchronized (this.zza) {
            str = this.zzc;
        }
        return str;
    }

    public final void zza(Context context, String str, String str2) {
        if (!zzb(context, str, str2)) {
            zza(context, "In-app preview failed to load because of a system error. Please try again later.", true, true);
            return;
        }
        if (ExifInterface.GPS_MEASUREMENT_2D.equals(this.zze)) {
            zzahw.zzb("Creative is not pushed for this device.");
            zza(context, "There was no creative pushed from DFP to the device.", false, false);
        } else if (LocalSearchProvider.LIVE_CONTENTS.equals(this.zze)) {
            zzahw.zzb("The app is not linked for creative preview.");
            zze(context, str, str2);
        } else if (LocalSearchProvider.NO_LIVE_CONTENTS.equals(this.zze)) {
            zzahw.zzb("Device is linked for in app preview.");
            zza(context, "The device is successfully linked for creative preview.", false, true);
        }
    }

    public final void zza(Context context, String str, String str2, String str3) {
        boolean zZzb = zzb();
        if (!zzc(context, str, str2)) {
            zze(context, str, str2);
            return;
        }
        if (!zZzb && !TextUtils.isEmpty(str3)) {
            zzb(context, str2, str3, str);
        }
        zzahw.zzb("Device is linked for debug signals.");
        zza(context, "The device is successfully linked for troubleshooting.", false, true);
    }

    public final void zzb(Context context, String str, String str2, String str3) {
        Uri.Builder builderBuildUpon = zzc(context, (String) zzlc.zzf().zza(zzoi.zzgj), str3, str).buildUpon();
        builderBuildUpon.appendQueryParameter("debugData", str2);
        zzbt.zze();
        zzaij.zzb(context, str, builderBuildUpon.build().toString());
    }

    public final boolean zzb() {
        boolean z;
        synchronized (this.zza) {
            z = this.zzd;
        }
        return z;
    }
}
