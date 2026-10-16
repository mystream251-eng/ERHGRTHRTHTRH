package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.internal.gmsg.HttpClient;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.gmsg.zzy;
import com.google.android.gms.ads.internal.js.zzaa;
import com.google.android.gms.ads.internal.js.zzc;
import com.google.android.gms.ads.internal.js.zzn;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzada extends zzahs {
    private static long zza = TimeUnit.SECONDS.toMillis(10);
    private static final Object zzb = new Object();
    private static boolean zzc = false;
    private static zzn zzd = null;
    private static HttpClient zze = null;
    private static zzy zzf = null;
    private static zzt<Object> zzg = null;
    private final zzabj zzh;
    private final zzacg zzi;
    private final Object zzj;
    private final Context zzk;
    private zzaa zzl;
    private zziz zzm;

    public zzada(Context context, zzacg zzacgVar, zzabj zzabjVar, zziz zzizVar) {
        super(true);
        this.zzj = new Object();
        this.zzh = zzabjVar;
        this.zzk = context;
        this.zzi = zzacgVar;
        this.zzm = zzizVar;
        synchronized (zzb) {
            if (!zzc) {
                zzf = new zzy();
                zze = new HttpClient(context.getApplicationContext(), zzacgVar.zzj);
                zzg = new zzadi();
                zzd = new zzn(this.zzk.getApplicationContext(), this.zzi.zzj, (String) zzlc.zzf().zza(zzoi.zza), new zzadh(), new zzadg());
                zzc = true;
            }
        }
    }

    private final zzacj zza(zzacf zzacfVar) {
        zzbt.zze();
        String strZza = zzaij.zza();
        JSONObject jSONObjectZza = zza(zzacfVar, strZza);
        if (jSONObjectZza == null) {
            return new zzacj(0);
        }
        long jZzb = zzbt.zzl().zzb();
        Future<JSONObject> futureZza = zzf.zza(strZza);
        zzako.zza.post(new zzadc(this, jSONObjectZza, strZza));
        try {
            JSONObject jSONObject = futureZza.get(zza - (zzbt.zzl().zzb() - jZzb), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new zzacj(-1);
            }
            zzacj zzacjVarZza = zzads.zza(this.zzk, zzacfVar, jSONObject.toString());
            return (zzacjVarZza.zzd == -3 || !TextUtils.isEmpty(zzacjVarZza.zzb)) ? zzacjVarZza : new zzacj(3);
        } catch (InterruptedException | CancellationException unused) {
            return new zzacj(-1);
        } catch (ExecutionException unused2) {
            return new zzacj(0);
        } catch (TimeoutException unused3) {
            return new zzacj(2);
        }
    }

    private final JSONObject zza(zzacf zzacfVar, String str) {
        zzaea zzaeaVar;
        AdvertisingIdClient.Info advertisingIdInfo;
        Bundle bundle = zzacfVar.zzc.zzc.getBundle("sdk_less_server_data");
        if (bundle == null) {
            return null;
        }
        try {
            zzaeaVar = zzbt.zzp().zza(this.zzk).get();
        } catch (Exception e) {
            zzahw.zzc("Error grabbing device info: ", e);
            zzaeaVar = null;
        }
        Context context = this.zzk;
        zzadl zzadlVar = new zzadl();
        zzadlVar.zzi = zzacfVar;
        zzadlVar.zzj = zzaeaVar;
        JSONObject jSONObjectZza = zzads.zza(context, zzadlVar);
        if (jSONObjectZza == null) {
            return null;
        }
        try {
            advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.zzk);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e2) {
            zzahw.zzc("Cannot get advertising id info", e2);
            advertisingIdInfo = null;
        }
        HashMap map = new HashMap();
        map.put("request_id", str);
        map.put("request_param", jSONObjectZza);
        map.put("data", bundle);
        if (advertisingIdInfo != null) {
            map.put("adid", advertisingIdInfo.getId());
            map.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 1 : 0));
        }
        try {
            return zzbt.zze().zza(map);
        } catch (JSONException unused) {
            return null;
        }
    }

    protected static void zza(zzc zzcVar) {
        zzcVar.zza("/loadAd", zzf);
        zzcVar.zza("/fetchHttpRequest", zze);
        zzcVar.zza("/invalidRequest", zzg);
    }

    protected static void zzb(zzc zzcVar) {
        zzcVar.zzb("/loadAd", zzf);
        zzcVar.zzb("/fetchHttpRequest", zze);
        zzcVar.zzb("/invalidRequest", zzg);
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void b_() {
        synchronized (this.zzj) {
            zzako.zza.post(new zzadf(this));
        }
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void zza() {
        zzahw.zzb("SdkLessAdLoaderBackgroundTask started.");
        String strZzj = zzbt.zzaa().zzj(this.zzk);
        zzacf zzacfVar = new zzacf(this.zzi, -1L, zzbt.zzaa().zzh(this.zzk), zzbt.zzaa().zzi(this.zzk), strZzj);
        zzbt.zzaa().zzf(this.zzk, strZzj);
        zzacj zzacjVarZza = zza(zzacfVar);
        zzako.zza.post(new zzadb(this, new zzahe(zzacfVar, zzacjVarZza, null, null, zzacjVarZza.zzd, zzbt.zzl().zzb(), zzacjVarZza.zzm, null, this.zzm)));
    }
}
