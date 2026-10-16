package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzabk;
import com.google.android.gms.internal.zzacg;
import com.google.android.gms.internal.zzada;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahf;
import com.google.android.gms.internal.zzahh;
import com.google.android.gms.internal.zzahs;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaid;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzaip;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzalt;
import com.google.android.gms.internal.zzaly;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzbih;
import com.google.android.gms.internal.zzhm;
import com.google.android.gms.internal.zziw;
import com.google.android.gms.internal.zziz;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzms;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzro;
import com.google.android.gms.internal.zzry;
import com.google.android.gms.internal.zzus;
import com.google.android.gms.internal.zzvr;
import com.google.android.gms.internal.zzvy;
import com.google.android.gms.internal.zzwf;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public abstract class zzd extends zza implements com.google.android.gms.ads.internal.overlay.zzn, zzbm, zzvr {
    protected final zzwf zzi;
    private transient boolean zzj;

    public zzd(Context context, zzko zzkoVar, String str, zzwf zzwfVar, zzala zzalaVar, zzv zzvVar) {
        this(new zzbu(context, zzkoVar, str, zzalaVar), zzwfVar, null, zzvVar);
    }

    private zzd(zzbu zzbuVar, zzwf zzwfVar, zzbj zzbjVar, zzv zzvVar) {
        super(zzbuVar, null, zzvVar);
        this.zzi = zzwfVar;
        this.zzj = false;
    }

    private final zzacg zza(zzkk zzkkVar, Bundle bundle, zzahh zzahhVar, int i) {
        PackageInfo packageInfoZzb;
        Bundle bundle2;
        JSONArray jSONArrayOptJSONArray;
        ApplicationInfo applicationInfo = this.zze.zzc.getApplicationInfo();
        String string = null;
        try {
            packageInfoZzb = zzbih.zza(this.zze.zzc).zzb(applicationInfo.packageName, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfoZzb = null;
        }
        DisplayMetrics displayMetrics = this.zze.zzc.getResources().getDisplayMetrics();
        if (this.zze.zzf == null || this.zze.zzf.getParent() == null) {
            bundle2 = null;
        } else {
            int[] iArr = new int[2];
            this.zze.zzf.getLocationOnScreen(iArr);
            int i2 = iArr[0];
            int i3 = iArr[1];
            int width = this.zze.zzf.getWidth();
            int height = this.zze.zzf.getHeight();
            int i4 = (!this.zze.zzf.isShown() || i2 + width <= 0 || i3 + height <= 0 || i2 > displayMetrics.widthPixels || i3 > displayMetrics.heightPixels) ? 0 : 1;
            Bundle bundle3 = new Bundle(5);
            bundle3.putInt("x", i2);
            bundle3.putInt("y", i3);
            bundle3.putInt(ToolboxAnalyticsModuleKt.M_WIDTH, width);
            bundle3.putInt(ToolboxAnalyticsModuleKt.M_HEIGHT, height);
            bundle3.putInt("visible", i4);
            bundle2 = bundle3;
        }
        String strZza = zzbt.zzi().zza().zza();
        this.zze.zzl = new zzahf(strZza, this.zze.zzb);
        this.zze.zzl.zza(zzkkVar);
        zzbt.zze();
        String strZza2 = zzaij.zza(this.zze.zzc, this.zze.zzf, this.zze.zzi);
        long jZzb = 0;
        if (this.zze.zzp != null) {
            try {
                jZzb = this.zze.zzp.zzb();
            } catch (RemoteException unused2) {
                zzahw.zze("Cannot get correlation id, default to 0.");
            }
        }
        long j = jZzb;
        String string2 = UUID.randomUUID().toString();
        Bundle bundleZza = zzbt.zzj().zza(this.zze.zzc, this, strZza);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i5 = 0; i5 < this.zze.zzu.size(); i5++) {
            String strKeyAt = this.zze.zzu.keyAt(i5);
            arrayList.add(strKeyAt);
            if (this.zze.zzt.containsKey(strKeyAt) && this.zze.zzt.get(strKeyAt) != null) {
                arrayList2.add(strKeyAt);
            }
        }
        zzalt zzaltVarZza = zzaid.zza(zzaid.zza, new zzg(this));
        zzalt zzaltVarZza2 = zzaid.zza(zzaid.zza, new zzh(this));
        String strZzc = zzahhVar != null ? zzahhVar.zzc() : null;
        if (this.zze.zzad != null && this.zze.zzad.size() > 0) {
            int i6 = packageInfoZzb != null ? packageInfoZzb.versionCode : 0;
            if (i6 > zzbt.zzi().zzl().zzg()) {
                zzbt.zzi().zzl().zzm();
                zzbt.zzi().zzl().zza(i6);
            } else {
                JSONObject jSONObjectZzl = zzbt.zzi().zzl().zzl();
                if (jSONObjectZzl != null && (jSONArrayOptJSONArray = jSONObjectZzl.optJSONArray(this.zze.zzb)) != null) {
                    string = jSONArrayOptJSONArray.toString();
                }
            }
        }
        zzko zzkoVar = this.zze.zzi;
        String str = this.zze.zzb;
        String strZzc2 = zzlc.zzc();
        zzala zzalaVar = this.zze.zze;
        List<String> list = this.zze.zzad;
        boolean zZza = zzbt.zzi().zzl().zza();
        int i7 = displayMetrics.widthPixels;
        int i8 = displayMetrics.heightPixels;
        float f = displayMetrics.density;
        List<String> listZza = zzoi.zza();
        String str2 = this.zze.zza;
        zzqh zzqhVar = this.zze.zzv;
        String strZzf = this.zze.zzf();
        float fZza = zzbt.zzac().zza();
        boolean zZzb = zzbt.zzac().zzb();
        zzbt.zze();
        int iZzi = zzaij.zzi(this.zze.zzc);
        zzbt.zze();
        int iZzd = zzaij.zzd(this.zze.zzf);
        boolean z = this.zze.zzc instanceof Activity;
        boolean zZzf = zzbt.zzi().zzl().zzf();
        boolean zZzd = zzbt.zzi().zzd();
        int iZza = zzbt.zzy().zza();
        zzbt.zze();
        Bundle bundleZzc = zzaij.zzc();
        String strZza3 = zzbt.zzo().zza();
        zzms zzmsVar = this.zze.zzx;
        boolean zZzb2 = zzbt.zzo().zzb();
        Bundle bundleZzj = zzus.zza().zzj();
        boolean zZze = zzbt.zzi().zzl().zze(this.zze.zzb);
        List<Integer> list2 = this.zze.zzz;
        boolean zZza2 = zzbih.zza(this.zze.zzc).zza();
        boolean zZze2 = zzbt.zzi().zze();
        zzbt.zzg();
        return new zzacg(bundle2, zzkkVar, zzkoVar, str, applicationInfo, packageInfoZzb, strZza, strZzc2, zzalaVar, bundleZza, list, arrayList, bundle, zZza, i7, i8, f, strZza2, j, string2, listZza, str2, zzqhVar, strZzf, fZza, zZzb, iZzi, iZzd, z, zZzf, zzaltVarZza, strZzc, zZzd, iZza, bundleZzc, strZza3, zzmsVar, zZzb2, bundleZzj, zZze, zzaltVarZza2, list2, string, arrayList2, i, zZza2, zZze2, zzaip.zze());
    }

    static String zzc(zzahd zzahdVar) {
        if (zzahdVar == null) {
            return null;
        }
        String str = zzahdVar.zzp;
        if (("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) && zzahdVar.zzn != null) {
            try {
                return new JSONObject(zzahdVar.zzn.zzj).getString("class_name");
            } catch (NullPointerException | JSONException unused) {
            }
        }
        return str;
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzkf
    public void onAdClicked() {
        if (this.zze.zzj == null) {
            zzahw.zze("Ad state was null when trying to ping click URLs.");
            return;
        }
        if (this.zze.zzj.zzq != null && this.zze.zzj.zzq.zzc != null) {
            zzbt.zzw();
            zzvy.zza(this.zze.zzc, this.zze.zze.zza, this.zze.zzj, this.zze.zzb, false, zza(this.zze.zzj.zzq.zzc));
        }
        if (this.zze.zzj.zzn != null && this.zze.zzj.zzn.zzf != null) {
            zzbt.zzw();
            zzvy.zza(this.zze.zzc, this.zze.zze.zza, this.zze.zzj, this.zze.zzb, false, this.zze.zzj.zzn.zzf);
        }
        super.onAdClicked();
    }

    protected void zza(zzahd zzahdVar, boolean z) {
        if (zzahdVar == null) {
            zzahw.zze("Ad state was null when trying to ping impression URLs.");
            return;
        }
        if (zzahdVar == null) {
            zzahw.zze("Ad state was null when trying to ping impression URLs.");
        } else {
            zzahw.zzb("Pinging Impression URLs.");
            if (this.zze.zzl != null) {
                this.zze.zzl.zza();
            }
            zzahdVar.zzah.zza(zziw.zza.zzb.AD_IMPRESSION);
            if (zzahdVar.zze != null && !zzahdVar.zzac) {
                zzbt.zze();
                zzaij.zza(this.zze.zzc, this.zze.zze.zza, zza(zzahdVar.zze));
                zzahdVar.zzac = true;
            }
        }
        if (!zzahdVar.zzad || z) {
            if (zzahdVar.zzq != null && zzahdVar.zzq.zzd != null) {
                zzbt.zzw();
                zzvy.zza(this.zze.zzc, this.zze.zze.zza, zzahdVar, this.zze.zzb, z, zza(zzahdVar.zzq.zzd));
            }
            if (zzahdVar.zzn != null && zzahdVar.zzn.zzg != null) {
                zzbt.zzw();
                zzvy.zza(this.zze.zzc, this.zze.zze.zza, zzahdVar, this.zze.zzb, z, zzahdVar.zzn.zzg);
            }
            zzahdVar.zzad = true;
        }
    }

    @Override // com.google.android.gms.internal.zzvr
    public final void zza(zzro zzroVar, String str) {
        String strZzl;
        zzry zzryVar = null;
        if (zzroVar != null) {
            try {
                strZzl = zzroVar.zzl();
            } catch (RemoteException e) {
                zzahw.zzc("Unable to call onCustomClick.", e);
                return;
            }
        } else {
            strZzl = null;
        }
        if (this.zze.zzt != null && strZzl != null) {
            zzryVar = this.zze.zzt.get(strZzl);
        }
        if (zzryVar == null) {
            zzahw.zze("Mediation adapter invoked onCustomClick but no listeners were set.");
        } else {
            zzryVar.zza(zzroVar, str);
        }
    }

    public final boolean zza(zzacg zzacgVar, zzov zzovVar) {
        this.zza = zzovVar;
        zzovVar.zza("seq_num", zzacgVar.zzg);
        zzovVar.zza("request_id", zzacgVar.zzv);
        zzovVar.zza("session_id", zzacgVar.zzh);
        if (zzacgVar.zzf != null) {
            zzovVar.zza("app_version", String.valueOf(zzacgVar.zzf.versionCode));
        }
        zzbu zzbuVar = this.zze;
        zzbt.zza();
        Context context = this.zze.zzc;
        zziz zzizVar = this.zzh.zzd;
        zzahs zzadaVar = zzacgVar.zzb.zzc.getBundle("sdk_less_server_data") != null ? new zzada(context, zzacgVar, this, zzizVar) : new zzabk(context, zzacgVar, this, zzizVar);
        zzadaVar.zzh();
        zzbuVar.zzg = zzadaVar;
        return true;
    }

    @Override // com.google.android.gms.ads.internal.zza
    final boolean zza(zzahd zzahdVar) {
        zzkk zzkkVar;
        boolean z = false;
        if (this.zzf != null) {
            zzkkVar = this.zzf;
            this.zzf = null;
        } else {
            zzkkVar = zzahdVar.zza;
            if (zzkkVar.zzc != null) {
                z = zzkkVar.zzc.getBoolean("_noRefresh", false);
            }
        }
        return zza(zzkkVar, zzahdVar, z);
    }

    @Override // com.google.android.gms.ads.internal.zza
    protected boolean zza(zzahd zzahdVar, zzahd zzahdVar2) {
        int i;
        if (zzahdVar != null && zzahdVar.zzr != null) {
            zzahdVar.zzr.zza((zzvr) null);
        }
        if (zzahdVar2.zzr != null) {
            zzahdVar2.zzr.zza(this);
        }
        int i2 = 0;
        if (zzahdVar2.zzq != null) {
            i2 = zzahdVar2.zzq.zzq;
            i = zzahdVar2.zzq.zzr;
        } else {
            i = 0;
        }
        this.zze.zzae.zza(i2, i);
        return true;
    }

    protected boolean zza(zzkk zzkkVar, zzahd zzahdVar, boolean z) {
        zzbj zzbjVar;
        long j;
        if (!z && this.zze.zzd()) {
            if (zzahdVar.zzh > 0) {
                zzbjVar = this.zzd;
                j = zzahdVar.zzh;
            } else if (zzahdVar.zzq != null && zzahdVar.zzq.zzi > 0) {
                zzbjVar = this.zzd;
                j = zzahdVar.zzq.zzi;
            } else if (!zzahdVar.zzm && zzahdVar.zzd == 2) {
                this.zzd.zzb(zzkkVar);
            }
            zzbjVar.zza(zzkkVar, j);
        }
        return this.zzd.zze();
    }

    @Override // com.google.android.gms.ads.internal.zza
    public boolean zza(zzkk zzkkVar, zzov zzovVar) {
        return zza(zzkkVar, zzovVar, 1);
    }

    public final boolean zza(zzkk zzkkVar, zzov zzovVar, int i) {
        if (!zzy()) {
            return false;
        }
        zzbt.zze();
        zzhm zzhmVarZza = zzbt.zzi().zza(this.zze.zzc);
        zzahh zzahhVar = null;
        Bundle bundleZza = zzhmVarZza == null ? null : zzaij.zza(zzhmVarZza);
        this.zzd.zza();
        this.zze.zzag = 0;
        if (((Boolean) zzlc.zzf().zza(zzoi.zzfi)).booleanValue()) {
            zzahh zzahhVarZzh = zzbt.zzi().zzl().zzh();
            zzbt.zzm().zza(this.zze.zzc, this.zze.zze, false, zzahhVarZzh, zzahhVarZzh != null ? zzahhVarZzh.zzd() : null, this.zze.zzb, null);
            zzahhVar = zzahhVarZzh;
        }
        return zza(zza(zzkkVar, bundleZza, zzahhVar, i), zzovVar);
    }

    public void zzaa() {
        this.zzj = true;
        zzo();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzab() {
        this.zzg.zzc(this.zze.zzj);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzac() {
        this.zzg.zzd(this.zze.zzj);
    }

    public void zzad() {
        zzahw.zze("Mediated ad does not support onVideoEnd callback");
    }

    public void zzae() {
        onAdClicked();
    }

    @Override // com.google.android.gms.internal.zzvr
    public final void zzaf() {
        zzz();
    }

    @Override // com.google.android.gms.internal.zzvr
    public final void zzag() {
        zzn();
    }

    @Override // com.google.android.gms.internal.zzvr
    public final void zzah() {
        zzaa();
    }

    @Override // com.google.android.gms.internal.zzvr
    public final void zzai() {
        if (this.zze.zzj != null) {
            String str = this.zze.zzj.zzp;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 74);
            sb.append("Mediation adapter ");
            sb.append(str);
            sb.append(" refreshed, but mediation adapters should never refresh.");
            zzahw.zze(sb.toString());
        }
        zza(this.zze.zzj, true);
        zzp();
    }

    public void zzaj() {
        zzak();
    }

    public final void zzak() {
        zza(this.zze.zzj, false);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final String zzal() {
        if (this.zze.zzj == null) {
            return null;
        }
        return this.zze.zzj.zzp;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final String zzam() {
        if (this.zze.zzj == null) {
            return null;
        }
        return zzc(this.zze.zzj);
    }

    public void zzan() {
        zzahw.zze("showInterstitial is not supported for current ad type");
    }

    @Override // com.google.android.gms.ads.internal.zzbm
    public final void zzao() {
        Executor executor = zzaly.zza;
        zzbj zzbjVar = this.zzd;
        zzbjVar.getClass();
        executor.execute(zze.zza(zzbjVar));
    }

    @Override // com.google.android.gms.ads.internal.zzbm
    public final void zzap() {
        Executor executor = zzaly.zza;
        zzbj zzbjVar = this.zzd;
        zzbjVar.getClass();
        executor.execute(zzf.zza(zzbjVar));
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzzn
    public final void zzb(zzahd zzahdVar) {
        super.zzb(zzahdVar);
        if (zzahdVar.zzn != null) {
            zzahw.zzb("Disable the debug gesture detector on the mediation ad frame.");
            if (this.zze.zzf != null) {
                this.zze.zzf.zzd();
            }
            zzahw.zzb("Pinging network fill URLs.");
            zzbt.zzw();
            zzvy.zza(this.zze.zzc, this.zze.zze.zza, zzahdVar, this.zze.zzb, false, zzahdVar.zzn.zzi);
            if (zzahdVar.zzq != null && zzahdVar.zzq.zzf != null && zzahdVar.zzq.zzf.size() > 0) {
                zzahw.zzb("Pinging urls remotely");
                zzbt.zze().zza(this.zze.zzc, zzahdVar.zzq.zzf);
            }
        } else {
            zzahw.zzb("Enable the debug gesture detector on the admob ad frame.");
            if (this.zze.zzf != null) {
                this.zze.zzf.zzc();
            }
        }
        if (zzahdVar.zzd != 3 || zzahdVar.zzq == null || zzahdVar.zzq.zze == null) {
            return;
        }
        zzahw.zzb("Pinging no fill URLs.");
        zzbt.zzw();
        zzvy.zza(this.zze.zzc, this.zze.zze.zza, zzahdVar, this.zze.zzb, false, zzahdVar.zzq.zze);
    }

    @Override // com.google.android.gms.internal.zzvr
    public final void zzb(String str, String str2) {
        zza(str, str2);
    }

    @Override // com.google.android.gms.ads.internal.zza
    protected final boolean zzc(zzkk zzkkVar) {
        return super.zzc(zzkkVar) && !this.zzj;
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public void zzh() {
        com.google.android.gms.common.internal.zzbq.zzb("pause must be called on the main UI thread.");
        if (this.zze.zzj != null && this.zze.zzj.zzb != null && this.zze.zzd()) {
            zzbt.zzg();
            zzaip.zza(this.zze.zzj.zzb);
        }
        if (this.zze.zzj != null && this.zze.zzj.zzo != null) {
            try {
                this.zze.zzj.zzo.zzd();
            } catch (RemoteException unused) {
                zzahw.zze("Could not pause mediation adapter.");
            }
        }
        this.zzg.zzc(this.zze.zzj);
        this.zzd.zzb();
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public void zzi() {
        com.google.android.gms.common.internal.zzbq.zzb("resume must be called on the main UI thread.");
        zzaof zzaofVar = (this.zze.zzj == null || this.zze.zzj.zzb == null) ? null : this.zze.zzj.zzb;
        if (zzaofVar != null && this.zze.zzd()) {
            zzbt.zzg();
            zzaip.zzb(this.zze.zzj.zzb);
        }
        if (this.zze.zzj != null && this.zze.zzj.zzo != null) {
            try {
                this.zze.zzj.zzo.zze();
            } catch (RemoteException unused) {
                zzahw.zze("Could not resume mediation adapter.");
            }
        }
        if (zzaofVar == null || !zzaofVar.zzad()) {
            this.zzd.zzc();
        }
        this.zzg.zzd(this.zze.zzj);
    }

    protected boolean zzy() {
        zzbt.zze();
        if (zzaij.zza(this.zze.zzc, this.zze.zzc.getPackageName(), "android.permission.INTERNET")) {
            zzbt.zze();
            if (zzaij.zza(this.zze.zzc)) {
                return true;
            }
        }
        return false;
    }

    public void zzz() {
        this.zzj = false;
        zzm();
        this.zze.zzl.zzc();
    }
}
