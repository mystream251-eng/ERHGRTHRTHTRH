package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.js.JavascriptEngineFactory;
import com.google.android.gms.ads.internal.js.zzn;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzw;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaar extends zzzw<zzaar> {
    private static final long zzc = TimeUnit.SECONDS.toMillis(60);
    private static final Object zzd = new Object();
    private static boolean zze = false;
    private static zzn zzf = null;
    private JavascriptEngineFactory zza;
    private zzalt<zzaof> zzb;
    private final zzz zzg;
    private final zzqa zzh;
    private final Context zzi;
    private final zzala zzj;
    private final com.google.android.gms.ads.internal.zzbb zzk;
    private final zzcv zzl;
    private final Object zzm = new Object();
    private String zzn;

    public zzaar(Context context, com.google.android.gms.ads.internal.zzbb zzbbVar, String str, zzcv zzcvVar, zzala zzalaVar) {
        zzahw.zzd("Webview loading for native ads.");
        this.zzi = context;
        this.zzk = zzbbVar;
        this.zzl = zzcvVar;
        this.zzj = zzalaVar;
        this.zzn = str;
        this.zza = new JavascriptEngineFactory();
        zzbt.zzf();
        zzalt<zzaof> zzaltVarZza = zzaol.zza(this.zzi, this.zzj, (String) zzlc.zzf().zza(zzoi.zzeq), this.zzl, this.zzk.zza());
        this.zzg = new zzz(this.zzi);
        this.zzh = new zzqa(zzbbVar, str);
        zzalt<zzaof> zzaltVarZza2 = zzali.zza(zzaltVarZza, new zzald(this) { // from class: com.google.android.gms.internal.zzaas
            private final zzaar zza;

            {
                this.zza = this;
            }

            @Override // com.google.android.gms.internal.zzald
            public final zzalt zza(Object obj) {
                return this.zza.zza((zzaof) obj);
            }
        }, zzaly.zzb);
        this.zzb = zzaltVarZza2;
        zzalg.zza(zzaltVarZza2, "WebViewNativeAdsUtil.constructor");
    }

    final /* synthetic */ zzalt zza(zzaof zzaofVar) throws Exception {
        zzahw.zzd("Javascript has loaded for native ads.");
        zzapu zzapuVarZzv = zzaofVar.zzv();
        com.google.android.gms.ads.internal.zzbb zzbbVar = this.zzk;
        zzapuVarZzv.zza(zzbbVar, zzbbVar, zzbbVar, zzbbVar, false, null, new zzw(this.zzi, null, null), null, null);
        zzaofVar.zzv().zza("/logScionEvent", this.zzg);
        zzaofVar.zzv().zza("/logScionEvent", this.zzh);
        return zzali.zza(zzaofVar);
    }

    @Override // com.google.android.gms.internal.zzaan
    public final zzalt<JSONObject> zza(final JSONObject jSONObject) {
        return zzali.zza(this.zzb, new zzald(this, jSONObject) { // from class: com.google.android.gms.internal.zzaat
            private final zzaar zza;
            private final JSONObject zzb;

            {
                this.zza = this;
                this.zzb = jSONObject;
            }

            @Override // com.google.android.gms.internal.zzald
            public final zzalt zza(Object obj) {
                return this.zza.zzc(this.zzb, (zzaof) obj);
            }
        }, zzaly.zza);
    }

    final /* synthetic */ zzalt zza(JSONObject jSONObject, zzaof zzaofVar) throws Exception {
        jSONObject.put("ads_id", this.zzn);
        zzaofVar.zzb("google.afma.nativeAds.handleImpressionPing", jSONObject);
        return zzali.zza(new JSONObject());
    }

    @Override // com.google.android.gms.internal.zzaan
    public final void zza() {
        zzali.zza(this.zzb, new zzaba(this), zzaly.zza);
    }

    final /* synthetic */ void zza(zzaof zzaofVar, zzzx zzzxVar, zzamd zzamdVar, zzaof zzaofVar2, Map map) {
        JSONObject jSONObject;
        boolean z;
        try {
            String str = (String) map.get("success");
            String str2 = (String) map.get("failure");
            if (TextUtils.isEmpty(str2)) {
                jSONObject = new JSONObject(str);
                z = true;
            } else {
                jSONObject = new JSONObject(str2);
                z = false;
            }
            if (this.zzn.equals(jSONObject.optString("ads_id", ""))) {
                zzaofVar.zzb("/nativeAdPreProcess", zzzxVar.zza);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("success", z);
                jSONObject2.put("json", jSONObject);
                zzamdVar.zzb(jSONObject2);
            }
        } catch (Throwable th) {
            zzahw.zzb("Error while preprocessing json.", th);
            zzamdVar.zza(th);
        }
    }

    @Override // com.google.android.gms.internal.zzzw, com.google.android.gms.internal.zzaan
    public final void zza(String str, zzt zztVar) {
        zzali.zza(this.zzb, new zzaax(this, str, zztVar), zzaly.zza);
    }

    @Override // com.google.android.gms.internal.zzaan
    public final void zza(String str, JSONObject jSONObject) {
        zzali.zza(this.zzb, new zzaaz(this, str, jSONObject), zzaly.zza);
    }

    @Override // com.google.android.gms.internal.zzaan
    public final zzalt<JSONObject> zzb(final JSONObject jSONObject) {
        return zzali.zza(this.zzb, new zzald(this, jSONObject) { // from class: com.google.android.gms.internal.zzaau
            private final zzaar zza;
            private final JSONObject zzb;

            {
                this.zza = this;
                this.zzb = jSONObject;
            }

            @Override // com.google.android.gms.internal.zzald
            public final zzalt zza(Object obj) {
                return this.zza.zzb(this.zzb, (zzaof) obj);
            }
        }, zzaly.zza);
    }

    final /* synthetic */ zzalt zzb(JSONObject jSONObject, zzaof zzaofVar) throws Exception {
        jSONObject.put("ads_id", this.zzn);
        zzaofVar.zzb("google.afma.nativeAds.handleClickGmsg", jSONObject);
        return zzali.zza(new JSONObject());
    }

    @Override // com.google.android.gms.internal.zzzw, com.google.android.gms.internal.zzaan
    public final void zzb(String str, zzt zztVar) {
        zzali.zza(this.zzb, new zzaay(this, str, zztVar), zzaly.zza);
    }

    @Override // com.google.android.gms.internal.zzaan
    public final zzalt<JSONObject> zzc(final JSONObject jSONObject) {
        return zzali.zza(this.zzb, new zzald(this, jSONObject) { // from class: com.google.android.gms.internal.zzaav
            private final zzaar zza;
            private final JSONObject zzb;

            {
                this.zza = this;
                this.zzb = jSONObject;
            }

            @Override // com.google.android.gms.internal.zzald
            public final zzalt zza(Object obj) {
                return this.zza.zza(this.zzb, (zzaof) obj);
            }
        }, zzaly.zza);
    }

    final /* synthetic */ zzalt zzc(JSONObject jSONObject, final zzaof zzaofVar) throws Exception {
        jSONObject.put("ads_id", this.zzn);
        final zzamd zzamdVar = new zzamd();
        final zzzx zzzxVar = new zzzx();
        zzt<? super zzaof> zztVar = new zzt(this, zzaofVar, zzzxVar, zzamdVar) { // from class: com.google.android.gms.internal.zzaaw
            private final zzaar zza;
            private final zzaof zzb;
            private final zzzx zzc;
            private final zzamd zzd;

            {
                this.zza = this;
                this.zzb = zzaofVar;
                this.zzc = zzzxVar;
                this.zzd = zzamdVar;
            }

            @Override // com.google.android.gms.ads.internal.gmsg.zzt
            public final void zza(Object obj, Map map) {
                this.zza.zza(this.zzb, this.zzc, this.zzd, (zzaof) obj, map);
            }
        };
        zzzxVar.zza = zztVar;
        zzaofVar.zza("/nativeAdPreProcess", zztVar);
        zzaofVar.zzb("google.afma.nativeAds.preProcessJsonGmsg", jSONObject);
        return zzamdVar;
    }
}
