package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.zzbt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzabk extends zzahs implements zzabx {
    zzajb zza;
    private final zzabj zzb;
    private final zzacg zzc;
    private final Object zzd = new Object();
    private final Context zze;
    private final zziu zzf;
    private final zziz zzg;
    private zzacf zzh;
    private Runnable zzi;
    private zzacj zzj;
    private zzvq zzk;

    public zzabk(Context context, zzacg zzacgVar, zzabj zzabjVar, zziz zzizVar) {
        zziu zziuVar;
        zziv zzivVar;
        this.zzb = zzabjVar;
        this.zze = context;
        this.zzc = zzacgVar;
        this.zzg = zzizVar;
        zziu zziuVar2 = new zziu(this.zzg);
        this.zzf = zziuVar2;
        zziuVar2.zza(new zziv(this) { // from class: com.google.android.gms.internal.zzabl
            private final zzabk zza;

            {
                this.zza = this;
            }

            @Override // com.google.android.gms.internal.zziv
            public final void zza(zzjk zzjkVar) {
                this.zza.zzb(zzjkVar);
            }
        });
        final zzjv zzjvVar = new zzjv();
        zzjvVar.zza = Integer.valueOf(this.zzc.zzj.zzb);
        zzjvVar.zzb = Integer.valueOf(this.zzc.zzj.zzc);
        zzjvVar.zzc = Integer.valueOf(this.zzc.zzj.zzd ? 0 : 2);
        this.zzf.zza(new zziv(zzjvVar) { // from class: com.google.android.gms.internal.zzabm
            private final zzjv zza;

            {
                this.zza = zzjvVar;
            }

            @Override // com.google.android.gms.internal.zziv
            public final void zza(zzjk zzjkVar) {
                zzjkVar.zzc.zzb = this.zza;
            }
        });
        if (this.zzc.zzf != null) {
            this.zzf.zza(new zziv(this) { // from class: com.google.android.gms.internal.zzabn
                private final zzabk zza;

                {
                    this.zza = this;
                }

                @Override // com.google.android.gms.internal.zziv
                public final void zza(zzjk zzjkVar) {
                    this.zza.zza(zzjkVar);
                }
            });
        }
        zzko zzkoVar = this.zzc.zzc;
        if (zzkoVar.zzd && "interstitial_mb".equals(zzkoVar.zza)) {
            zziuVar = this.zzf;
            zzivVar = zzabo.zza;
        } else if (zzkoVar.zzd && "reward_mb".equals(zzkoVar.zza)) {
            zziuVar = this.zzf;
            zzivVar = zzabp.zza;
        } else if (zzkoVar.zzh || zzkoVar.zzd) {
            zziuVar = this.zzf;
            zzivVar = zzabr.zza;
        } else {
            zziuVar = this.zzf;
            zzivVar = zzabq.zza;
        }
        zziuVar.zza(zzivVar);
        this.zzf.zza(zziw.zza.zzb.AD_REQUEST);
    }

    private final zzko zza(zzacf zzacfVar) throws zzabu {
        zzvq zzvqVar;
        zzacf zzacfVar2 = this.zzh;
        if (((zzacfVar2 == null || zzacfVar2.zzav == null || this.zzh.zzav.size() <= 1) ? false : true) && (zzvqVar = this.zzk) != null && !zzvqVar.zzs) {
            return null;
        }
        if (this.zzj.zzy) {
            for (zzko zzkoVar : zzacfVar.zzd.zzg) {
                if (zzkoVar.zzi) {
                    return new zzko(zzkoVar, zzacfVar.zzd.zzg);
                }
            }
        }
        if (this.zzj.zzl == null) {
            throw new zzabu("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] strArrSplit = this.zzj.zzl.split("x");
        if (strArrSplit.length != 2) {
            String strValueOf = String.valueOf(this.zzj.zzl);
            throw new zzabu(strValueOf.length() != 0 ? "Invalid ad size format from the ad response: ".concat(strValueOf) : new String("Invalid ad size format from the ad response: "), 0);
        }
        try {
            int i = Integer.parseInt(strArrSplit[0]);
            int i2 = Integer.parseInt(strArrSplit[1]);
            for (zzko zzkoVar2 : zzacfVar.zzd.zzg) {
                float f = this.zze.getResources().getDisplayMetrics().density;
                int i3 = zzkoVar2.zze == -1 ? (int) (zzkoVar2.zzf / f) : zzkoVar2.zze;
                int i4 = zzkoVar2.zzb == -2 ? (int) (zzkoVar2.zzc / f) : zzkoVar2.zzb;
                if (i == i3 && i2 == i4 && !zzkoVar2.zzi) {
                    return new zzko(zzkoVar2, zzacfVar.zzd.zzg);
                }
            }
            String strValueOf2 = String.valueOf(this.zzj.zzl);
            throw new zzabu(strValueOf2.length() != 0 ? "The ad size from the ad response was not one of the requested sizes: ".concat(strValueOf2) : new String("The ad size from the ad response was not one of the requested sizes: "), 0);
        } catch (NumberFormatException unused) {
            String strValueOf3 = String.valueOf(this.zzj.zzl);
            throw new zzabu(strValueOf3.length() != 0 ? "Invalid ad size number from the ad response: ".concat(strValueOf3) : new String("Invalid ad size number from the ad response: "), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i, String str) {
        if (i == 3 || i == -1) {
            zzahw.zzd(str);
        } else {
            zzahw.zze(str);
        }
        this.zzj = this.zzj == null ? new zzacj(i) : new zzacj(i, this.zzj.zzj);
        zzacf zzacfVar = this.zzh;
        if (zzacfVar == null) {
            zzacfVar = new zzacf(this.zzc, -1L, null, null, null);
        }
        zzacj zzacjVar = this.zzj;
        this.zzb.zza(new zzahe(zzacfVar, zzacjVar, this.zzk, null, i, -1L, zzacjVar.zzm, null, this.zzf, null));
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void b_() {
        synchronized (this.zzd) {
            if (this.zza != null) {
                this.zza.zzb();
            }
        }
    }

    final zzajb zza(zzala zzalaVar, zzamf<zzacf> zzamfVar) {
        Context context = this.zze;
        if (new zzabw(context).zza(zzalaVar)) {
            zzahw.zzb("Fetching ad response from local ad request service.");
            zzacc zzaccVar = new zzacc(context, zzamfVar, this);
            zzaccVar.zzc();
            return zzaccVar;
        }
        zzahw.zzb("Fetching ad response from remote ad request service.");
        zzlc.zza();
        if (zzako.zzc(context)) {
            return new zzacd(context, zzalaVar, zzamfVar, this);
        }
        zzahw.zze("Failed to connect to remote ad request service.");
        return null;
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void zza() {
        String string;
        zzahw.zzb("AdLoaderBackgroundTask started.");
        this.zzi = new zzabs(this);
        zzaij.zza.postDelayed(this.zzi, ((Long) zzlc.zzf().zza(zzoi.zzds)).longValue());
        long jZzb = zzbt.zzl().zzb();
        if (((Boolean) zzlc.zzf().zza(zzoi.zzdq)).booleanValue() && this.zzc.zzb.zzc != null && (string = this.zzc.zzb.zzc.getString("_ad")) != null) {
            zzacf zzacfVar = new zzacf(this.zzc, jZzb, null, null, null);
            this.zzh = zzacfVar;
            zza(zzads.zza(this.zze, zzacfVar, string));
            return;
        }
        zzamj zzamjVar = new zzamj();
        zzaid.zza(new zzabt(this, zzamjVar));
        String strZzh = zzbt.zzaa().zzh(this.zze);
        String strZzi = zzbt.zzaa().zzi(this.zze);
        String strZzj = zzbt.zzaa().zzj(this.zze);
        zzbt.zzaa().zzf(this.zze, strZzj);
        zzacf zzacfVar2 = new zzacf(this.zzc, jZzb, strZzh, strZzi, strZzj);
        this.zzh = zzacfVar2;
        zzamjVar.zza(zzacfVar2);
    }

    @Override // com.google.android.gms.internal.zzabx
    public final void zza(zzacj zzacjVar) {
        Boolean bool;
        JSONObject jSONObject;
        Bundle bundle;
        zzahw.zzb("Received ad response.");
        this.zzj = zzacjVar;
        long jZzb = zzbt.zzl().zzb();
        synchronized (this.zzd) {
            bool = null;
            this.zza = null;
        }
        zzbt.zzi().zzl().zzd(this.zzj.zzaf);
        if (((Boolean) zzlc.zzf().zza(zzoi.zzcl)).booleanValue()) {
            if (this.zzj.zzan) {
                zzbt.zzi().zzl().zzc(this.zzh.zze);
            } else {
                zzbt.zzi().zzl().zzd(this.zzh.zze);
            }
        }
        try {
            if (this.zzj.zzd != -2 && this.zzj.zzd != -3) {
                int i = this.zzj.zzd;
                StringBuilder sb = new StringBuilder(66);
                sb.append("There was a problem getting an ad response. ErrorCode: ");
                sb.append(i);
                throw new zzabu(sb.toString(), this.zzj.zzd);
            }
            if (this.zzj.zzd != -3) {
                if (TextUtils.isEmpty(this.zzj.zzb)) {
                    throw new zzabu("No fill from ad server.", 3);
                }
                zzbt.zzi().zzl().zza(this.zzj.zzt);
                if (this.zzj.zzg) {
                    try {
                        this.zzk = new zzvq(this.zzj.zzb);
                        zzbt.zzi().zza(this.zzk.zzg);
                    } catch (JSONException e) {
                        zzahw.zzb("Could not parse mediation config.", e);
                        String strValueOf = String.valueOf(this.zzj.zzb);
                        throw new zzabu(strValueOf.length() != 0 ? "Could not parse mediation config: ".concat(strValueOf) : new String("Could not parse mediation config: "), 0);
                    }
                } else {
                    zzbt.zzi().zza(this.zzj.zzai);
                }
                if (!TextUtils.isEmpty(this.zzj.zzag)) {
                    if (((Boolean) zzlc.zzf().zza(zzoi.zzfs)).booleanValue()) {
                        zzahw.zzb("Received cookie from server. Setting webview cookie in CookieManager.");
                        CookieManager cookieManagerZzc = zzbt.zzg().zzc(this.zze);
                        if (cookieManagerZzc != null) {
                            cookieManagerZzc.setCookie("googleads.g.doubleclick.net", this.zzj.zzag);
                        }
                    }
                }
            }
            zzko zzkoVarZza = this.zzh.zzd.zzg != null ? zza(this.zzh) : null;
            zzbt.zzi().zzl().zzb(this.zzj.zzu);
            zzbt.zzi().zzl().zzc(this.zzj.zzam);
            if (TextUtils.isEmpty(this.zzj.zzq)) {
                jSONObject = null;
            } else {
                try {
                    jSONObject = new JSONObject(this.zzj.zzq);
                } catch (Exception e2) {
                    zzahw.zzb("Error parsing the JSON for Active View.", e2);
                    jSONObject = null;
                }
            }
            if (this.zzj.zzap == 2) {
                bool = true;
                zzkk zzkkVar = this.zzh.zzc;
                Bundle bundle2 = zzkkVar.zzm != null ? zzkkVar.zzm : new Bundle();
                if (bundle2.getBundle(AdMobAdapter.class.getName()) != null) {
                    bundle = bundle2.getBundle(AdMobAdapter.class.getName());
                } else {
                    Bundle bundle3 = new Bundle();
                    bundle2.putBundle(AdMobAdapter.class.getName(), bundle3);
                    bundle = bundle3;
                }
                bundle.putBoolean("render_test_label", true);
            }
            if (this.zzj.zzap == 1) {
                bool = false;
            }
            Boolean boolValueOf = this.zzj.zzap == 0 ? Boolean.valueOf(zzakh.zza(this.zzh.zzc)) : bool;
            zzacf zzacfVar = this.zzh;
            zzacj zzacjVar2 = this.zzj;
            this.zzb.zza(new zzahe(zzacfVar, zzacjVar2, this.zzk, zzkoVarZza, -2, jZzb, zzacjVar2.zzm, jSONObject, this.zzf, boolValueOf));
        } catch (zzabu e3) {
            zza(e3.zza(), e3.getMessage());
        }
        zzaij.zza.removeCallbacks(this.zzi);
    }

    final /* synthetic */ void zza(zzjk zzjkVar) {
        zzjkVar.zzc.zza = this.zzc.zzf.packageName;
    }

    final /* synthetic */ void zzb(zzjk zzjkVar) {
        zzjkVar.zza = this.zzc.zzv;
    }
}
