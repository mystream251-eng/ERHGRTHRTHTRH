package com.google.android.gms.ads.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzalg;
import com.google.android.gms.internal.zzali;
import com.google.android.gms.internal.zzalt;
import com.google.android.gms.internal.zzaly;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzux;
import com.google.android.gms.internal.zzvc;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzac {
    private Context zzb;
    private final Object zza = new Object();
    private long zzc = 0;

    public final void zza(Context context, zzala zzalaVar, String str, Runnable runnable) {
        zza(context, zzalaVar, true, null, str, null, runnable);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0025  */
    final void zza(Context context, zzala zzalaVar, boolean z, zzahh zzahhVar, String str, String str2, Runnable runnable) {
        boolean z2;
        if (zzbt.zzl().zzb() - this.zzc < 5000) {
            zzahw.zze("Not retrying to fetch app settings");
            return;
        }
        this.zzc = zzbt.zzl().zzb();
        if (zzahhVar != null) {
            z2 = (((zzbt.zzl().zza() - zzahhVar.zza()) > ((Long) zzlc.zzf().zza(zzoi.zzfj)).longValue() ? 1 : ((zzbt.zzl().zza() - zzahhVar.zza()) == ((Long) zzlc.zzf().zza(zzoi.zzfj)).longValue() ? 0 : -1)) > 0) || !zzahhVar.zzb();
        }
        if (z2) {
            if (context == null) {
                zzahw.zze("Context not provided to fetch application settings");
                return;
            }
            if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
                zzahw.zze("App settings could not be fetched. Required parameters missing");
                return;
            }
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
            this.zzb = applicationContext;
            zzux zzuxVarZza = zzbt.zzs().zza(this.zzb, zzalaVar).zza("google.afma.config.fetchAppSettings", zzvc.zza, zzvc.zza);
            try {
                JSONObject jSONObject = new JSONObject();
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put("app_id", str);
                } else if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("ad_unit_id", str2);
                }
                jSONObject.put("is_init", z);
                jSONObject.put("pn", context.getPackageName());
                zzalt zzaltVarZzb = zzuxVarZza.zzb(jSONObject);
                zzalt zzaltVarZza = zzali.zza(zzaltVarZzb, zzad.zza, zzaly.zzb);
                if (runnable != null) {
                    zzaltVarZzb.zza(runnable, zzaly.zzb);
                }
                zzalg.zza(zzaltVarZza, "ConfigLoader.maybeFetchNewAppSettings");
            } catch (Exception e) {
                zzahw.zzb("Error requesting application settings", e);
            }
        }
    }
}
