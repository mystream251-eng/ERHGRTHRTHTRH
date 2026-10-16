package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import androidx.collection.SimpleArrayMap;
import com.google.ads.AdRequest;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzaan;
import com.google.android.gms.internal.zzaar;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaid;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzalt;
import com.google.android.gms.internal.zzamd;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzaop;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzmp;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpm;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzpr;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzpu;
import com.google.android.gms.internal.zzpv;
import com.google.android.gms.internal.zzpw;
import com.google.android.gms.internal.zzpx;
import com.google.android.gms.internal.zzpz;
import com.google.android.gms.internal.zzro;
import com.google.android.gms.internal.zzry;
import com.google.android.gms.internal.zzsb;
import com.google.android.gms.internal.zzvq;
import com.google.android.gms.internal.zzwf;
import com.google.android.gms.internal.zzwi;
import com.google.android.gms.internal.zzwr;
import com.google.android.gms.internal.zzwu;
import com.google.android.gms.internal.zzyx;
import com.google.android.gms.internal.zzzm;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzbb extends zzd implements zzpw {
    private final Object zzj;
    private boolean zzk;
    private zzamd<zzpx> zzl;
    private zzaof zzm;
    private boolean zzn;
    private int zzo;
    private zzaan zzp;
    private final String zzq;

    public zzbb(Context context, zzv zzvVar, zzko zzkoVar, String str, zzwf zzwfVar, zzala zzalaVar) {
        this(context, zzvVar, zzkoVar, str, zzwfVar, zzalaVar, false);
    }

    public zzbb(Context context, zzv zzvVar, zzko zzkoVar, String str, zzwf zzwfVar, zzala zzalaVar, boolean z) {
        super(context, zzkoVar, str, zzwfVar, zzalaVar, zzvVar);
        this.zzj = new Object();
        this.zzl = new zzamd<>();
        this.zzo = 1;
        this.zzq = UUID.randomUUID().toString();
        this.zzk = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(zzbu zzbuVar, zzbu zzbuVar2) {
        if (zzbuVar2.zzq == null) {
            zzbuVar2.zzq = zzbuVar.zzq;
        }
        if (zzbuVar2.zzr == null) {
            zzbuVar2.zzr = zzbuVar.zzr;
        }
        if (zzbuVar2.zzt == null) {
            zzbuVar2.zzt = zzbuVar.zzt;
        }
        if (zzbuVar2.zzu == null) {
            zzbuVar2.zzu = zzbuVar.zzu;
        }
        if (zzbuVar2.zzw == null) {
            zzbuVar2.zzw = zzbuVar.zzw;
        }
        if (zzbuVar2.zzv == null) {
            zzbuVar2.zzv = zzbuVar.zzv;
        }
        if (zzbuVar2.zzad == null) {
            zzbuVar2.zzad = zzbuVar.zzad;
        }
        if (zzbuVar2.zzl == null) {
            zzbuVar2.zzl = zzbuVar.zzl;
        }
        if (zzbuVar2.zzae == null) {
            zzbuVar2.zzae = zzbuVar.zzae;
        }
        if (zzbuVar2.zzm == null) {
            zzbuVar2.zzm = zzbuVar.zzm;
        }
        if (zzbuVar2.zzn == null) {
            zzbuVar2.zzn = zzbuVar.zzn;
        }
        if (zzbuVar2.zzi == null) {
            zzbuVar2.zzi = zzbuVar.zzi;
        }
        if (zzbuVar2.zzj == null) {
            zzbuVar2.zzj = zzbuVar.zzj;
        }
        if (zzbuVar2.zzk == null) {
            zzbuVar2.zzk = zzbuVar.zzk;
        }
    }

    private final void zza(zzpk zzpkVar) {
        zzaij.zza.post(new zzbf(this, zzpkVar));
    }

    private final void zza(zzpm zzpmVar) {
        zzaij.zza.post(new zzbg(this, zzpmVar));
    }

    private final void zza(zzpr zzprVar) {
        try {
            if (this.zze.zzs != null) {
                this.zze.zzs.zza(zzprVar);
            }
        } catch (RemoteException e) {
            zzahw.zzc("Could not call onUnifiedNativeAdLoadedListener.onUnifiedNativeAdLoaded().", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzpr zzb(zzpx zzpxVar) {
        zzpr zzprVar;
        IObjectWrapper iObjectWrapperZzj;
        Object objZza = null;
        if (zzpxVar instanceof zzpm) {
            zzpm zzpmVar = (zzpm) zzpxVar;
            zzprVar = new zzpr(zzpmVar.zza(), zzpmVar.zzb(), zzpmVar.zze(), zzpmVar.zzf(), zzpmVar.zzg(), zzpmVar.zzh(), -1.0d, null, null, zzpmVar.zzm(), zzpmVar.zzi(), zzpmVar.zzo(), zzpmVar.zzc(), zzpmVar.zzd());
            if (zzpmVar.zzj() != null) {
                iObjectWrapperZzj = zzpmVar.zzj();
                objZza = com.google.android.gms.dynamic.zzn.zza(iObjectWrapperZzj);
            }
        } else if (zzpxVar instanceof zzpk) {
            zzpk zzpkVar = (zzpk) zzpxVar;
            zzprVar = new zzpr(zzpkVar.zza(), zzpkVar.zzb(), zzpkVar.zzc(), zzpkVar.zzd(), zzpkVar.zze(), null, zzpkVar.zzf(), zzpkVar.zzg(), zzpkVar.zzh(), zzpkVar.zzm(), zzpkVar.zzi(), zzpkVar.zzo(), zzpkVar.zzp(), zzpkVar.zzq());
            if (zzpkVar.zzj() != null) {
                iObjectWrapperZzj = zzpkVar.zzj();
                objZza = com.google.android.gms.dynamic.zzn.zza(iObjectWrapperZzj);
            }
        } else {
            zzprVar = null;
        }
        if (objZza instanceof zzpz) {
            zzprVar.zza((zzpz) objZza);
        }
        return zzprVar;
    }

    private final zzvq zzbb() {
        if (this.zze.zzj == null || !this.zze.zzj.zzm) {
            return null;
        }
        return this.zze.zzj.zzq;
    }

    private final void zzbc() {
        zzaan zzaanVarZzav = zzav();
        if (zzaanVarZzav != null) {
            zzaanVarZzav.zza();
        }
    }

    @Override // com.google.android.gms.ads.internal.zza
    protected final void zza(int i) {
        zza(i, false);
    }

    @Override // com.google.android.gms.ads.internal.zza
    protected final void zza(int i, boolean z) {
        zzbc();
        super.zza(i, z);
    }

    @Override // com.google.android.gms.ads.internal.zza
    public final void zza(zzahe zzaheVar, zzov zzovVar) {
        if (zzaheVar.zzd != null) {
            this.zze.zzi = zzaheVar.zzd;
        }
        if (zzaheVar.zze != -2) {
            zzaij.zza.post(new zzbc(this, zzaheVar));
            return;
        }
        int i = zzaheVar.zza.zzay;
        if (i == 1) {
            this.zze.zzag = 0;
            zzbu zzbuVar = this.zze;
            zzbt.zzd();
            zzbuVar.zzh = zzzm.zza(this.zze.zzc, this, zzaheVar, this.zze.zzd, null, this.zzi, this, zzovVar);
            String strValueOf = String.valueOf(this.zze.zzh.getClass().getName());
            zzahw.zzb(strValueOf.length() != 0 ? "AdRenderer: ".concat(strValueOf) : new String("AdRenderer: "));
            return;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            JSONArray jSONArray2 = new JSONObject(zzaheVar.zzb.zzb).getJSONArray("slots");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                JSONArray jSONArray3 = jSONArray2.getJSONObject(i2).getJSONArray("ads");
                for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                    jSONArray.put(jSONArray3.get(i3));
                }
            }
            zzbc();
            ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(i);
            ArrayList arrayList = new ArrayList();
            for (int i4 = 0; i4 < i; i4++) {
                arrayList.add(zzaid.zza(executorServiceNewFixedThreadPool, new zzbd(this, i4, jSONArray, i, zzaheVar)));
            }
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                try {
                    zzaij.zza.post(new zzbe(this, (zzpx) ((zzalt) arrayList.get(i5)).get(((Long) zzlc.zzf().zza(zzoi.zzdt)).longValue(), TimeUnit.MILLISECONDS), i5, arrayList));
                } catch (InterruptedException e) {
                    zzahw.zzc("Exception occurred while getting an ad response", e);
                    Thread.currentThread().interrupt();
                } catch (CancellationException e2) {
                    e = e2;
                    zzahw.zzc("Exception occurred while getting an ad response", e);
                } catch (ExecutionException e3) {
                    e = e3;
                    zzahw.zzc("Exception occurred while getting an ad response", e);
                } catch (TimeoutException e4) {
                    e = e4;
                    zzahw.zzc("Exception occurred while getting an ad response", e);
                }
            }
        } catch (JSONException e5) {
            zzahw.zzc("Malformed native ad response", e5);
            zza(0);
        }
    }

    public final void zza(zzaof zzaofVar) {
        this.zzm = zzaofVar;
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zza(zzpb zzpbVar) {
        throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
    }

    @Override // com.google.android.gms.internal.zzpw
    public final void zza(zzpt zzptVar) {
        zzaof zzaofVar = this.zzm;
        if (zzaofVar != null) {
            zzaofVar.zza(zzptVar);
        }
    }

    @Override // com.google.android.gms.internal.zzpw
    public final void zza(zzpv zzpvVar) {
        if (this.zze.zzj.zzj != null) {
            zzbt.zzi().zzg().zza(this.zze.zzi, this.zze.zzj, new zzfx(zzpvVar), (zzaof) null);
        }
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zza(zzyx zzyxVar) {
        throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
    }

    /* JADX WARN: Code duplicated, block: B:59:0x0169  */
    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza
    protected final boolean zza(zzahd zzahdVar, zzahd zzahdVar2) {
        zzb((List<String>) null);
        if (!this.zze.zzd()) {
            throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
        }
        if (zzahdVar2.zzm) {
            zzbc();
            try {
                zzwr zzwrVarZzh = zzahdVar2.zzo != null ? zzahdVar2.zzo.zzh() : null;
                zzwu zzwuVarZzi = zzahdVar2.zzo != null ? zzahdVar2.zzo.zzi() : null;
                zzro zzroVarZzn = zzahdVar2.zzo != null ? zzahdVar2.zzo.zzn() : null;
                String strZzc = zzc(zzahdVar2);
                if (zzwrVarZzh != null && this.zze.zzq != null) {
                    zzpk zzpkVar = new zzpk(zzwrVarZzh.zza(), zzwrVarZzh.zzb(), zzwrVarZzh.zzc(), zzwrVarZzh.zzd() != null ? zzwrVarZzh.zzd() : null, zzwrVarZzh.zze(), zzwrVarZzh.zzf(), zzwrVarZzh.zzg(), zzwrVarZzh.zzh(), null, zzwrVarZzh.zzl(), zzwrVarZzh.zzm(), zzwrVarZzh.zzp() != null ? (View) com.google.android.gms.dynamic.zzn.zza(zzwrVarZzh.zzp()) : null, zzwrVarZzh.zzq(), strZzc);
                    zzpkVar.zza(new zzpu(this.zze.zzc, this, this.zze.zzd, zzwrVarZzh, zzpkVar));
                    zza(zzpkVar);
                } else if (zzwuVarZzi != null && this.zze.zzr != null) {
                    zzpm zzpmVar = new zzpm(zzwuVarZzi.zza(), zzwuVarZzi.zzb(), zzwuVarZzi.zzc(), zzwuVarZzi.zzd() != null ? zzwuVarZzi.zzd() : null, zzwuVarZzi.zze(), zzwuVarZzi.zzf(), null, zzwuVarZzi.zzj(), zzwuVarZzi.zzl(), zzwuVarZzi.zzn() != null ? (View) com.google.android.gms.dynamic.zzn.zza(zzwuVarZzi.zzn()) : null, zzwuVarZzi.zzo(), strZzc);
                    zzpmVar.zza(new zzpu(this.zze.zzc, this, this.zze.zzd, zzwuVarZzi, zzpmVar));
                    zza(zzpmVar);
                } else {
                    if (zzroVarZzn == null || this.zze.zzu == null || this.zze.zzu.get(zzroVarZzn.zzl()) == null) {
                        zzahw.zze("No matching mapper/listener for retrieved native ad template.");
                        zza(0);
                        return false;
                    }
                    zzaij.zza.post(new zzbi(this, zzroVarZzn));
                }
            } catch (RemoteException e) {
                zzahw.zzc("Failed to get native ad mapper", e);
            }
        } else {
            zzpx zzpxVar = zzahdVar2.zzab;
            if (this.zzk) {
                this.zzl.zzb(zzpxVar);
            } else {
                boolean z = zzpxVar instanceof zzpm;
                if (z && this.zze.zzs != null) {
                    zza(zzb(zzahdVar2.zzab));
                } else if (!z || this.zze.zzr == null) {
                    boolean z2 = zzpxVar instanceof zzpk;
                    if (z2 && this.zze.zzs != null) {
                        zza(zzb(zzahdVar2.zzab));
                    } else {
                        if (!z2 || this.zze.zzq == null) {
                            if ((zzpxVar instanceof zzpo) && this.zze.zzu != null) {
                                zzpo zzpoVar = (zzpo) zzpxVar;
                                if (this.zze.zzu.get(zzpoVar.zzl()) != null) {
                                    zzaij.zza.post(new zzbh(this, zzpoVar.zzl(), zzahdVar2));
                                }
                            }
                            zzahw.zze("No matching listener for retrieved native ad template.");
                            zza(0);
                            return false;
                        }
                        zza((zzpk) zzahdVar2.zzab);
                    }
                } else {
                    zza((zzpm) zzahdVar2.zzab);
                }
            }
        }
        return super.zza(zzahdVar, zzahdVar2);
    }

    @Override // com.google.android.gms.ads.internal.zzd
    protected final boolean zza(zzkk zzkkVar, zzahd zzahdVar, boolean z) {
        return this.zzd.zze();
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza
    public final boolean zza(zzkk zzkkVar, zzov zzovVar) {
        try {
            zzau();
            return super.zza(zzkkVar, zzovVar, this.zzo);
        } catch (Exception e) {
            if (!zzaky.zza(4)) {
                return false;
            }
            Log.i(AdRequest.LOGTAG, "Error initializing webview.", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.internal.zzvr
    public final void zzad() {
        zzmp zzmpVarZzi;
        zzahd zzahdVar = this.zze.zzj;
        if (zzahdVar.zzo == null) {
            super.zzad();
            return;
        }
        try {
            zzwi zzwiVar = zzahdVar.zzo;
            zzmm zzmmVarZzc = null;
            zzwr zzwrVarZzh = zzwiVar.zzh();
            if (zzwrVarZzh != null) {
                zzmmVarZzc = zzwrVarZzh.zzm();
            } else {
                zzwu zzwuVarZzi = zzwiVar.zzi();
                if (zzwuVarZzi != null) {
                    zzmmVarZzc = zzwuVarZzi.zzl();
                } else {
                    zzro zzroVarZzn = zzwiVar.zzn();
                    if (zzroVarZzn != null) {
                        zzmmVarZzc = zzroVarZzn.zzc();
                    }
                }
            }
            if (zzmmVarZzc == null || (zzmpVarZzi = zzmmVarZzc.zzi()) == null) {
                return;
            }
            zzmpVarZzi.zzd();
        } catch (RemoteException e) {
            zzahw.zzc("Unable to call onVideoEnd()", e);
        }
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.internal.zzvr
    public final void zzae() {
        if (this.zze.zzj == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zze.zzj.zzp)) {
            super.zzae();
        } else {
            zzr();
        }
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.internal.zzvr
    public final void zzaj() {
        if (this.zze.zzj == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zze.zzj.zzp)) {
            super.zzaj();
        } else {
            zzq();
        }
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.internal.zzlt
    public final void zzan() {
        throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
    }

    @Override // com.google.android.gms.internal.zzpw
    public final void zzaq() {
        zzaof zzaofVar = this.zzm;
        if (zzaofVar != null) {
            zzaofVar.destroy();
            this.zzm = null;
        }
    }

    @Override // com.google.android.gms.internal.zzpw
    public final boolean zzar() {
        if (zzbb() != null) {
            return zzbb().zzo;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.zzpw
    public final boolean zzas() {
        if (zzbb() != null) {
            return zzbb().zzp;
        }
        return false;
    }

    public final String zzat() {
        return this.zzq;
    }

    final void zzau() throws zzaop {
        synchronized (this.zzj) {
            zzahw.zza("Initializing webview native ads utills");
            this.zzp = new zzaar(this.zze.zzc, this, this.zzq, this.zze.zzd, this.zze.zze);
        }
    }

    public final zzaan zzav() {
        zzaan zzaanVar;
        synchronized (this.zzj) {
            zzaanVar = this.zzp;
        }
        return zzaanVar;
    }

    protected final Future<zzpx> zzaw() {
        return this.zzl;
    }

    public final void zzax() {
        if (this.zze.zzj == null || this.zzm == null) {
            this.zzn = true;
            zzahw.zze("Request to enable ActiveView before adState is available.");
        } else {
            zzbt.zzi().zzg().zza(this.zze.zzi, this.zze.zzj, this.zzm.zzm(), this.zzm);
            this.zzn = false;
        }
    }

    public final void zzay() {
        this.zzn = false;
        if (this.zze.zzj == null || this.zzm == null) {
            zzahw.zze("Request to enable ActiveView before adState is available.");
        } else {
            zzbt.zzi().zzg().zza(this.zze.zzj);
        }
    }

    public final SimpleArrayMap<String, zzsb> zzaz() {
        com.google.android.gms.common.internal.zzbq.zzb("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        return this.zze.zzu;
    }

    @Override // com.google.android.gms.internal.zzpw
    public final zzry zzb(String str) {
        com.google.android.gms.common.internal.zzbq.zzb("getOnCustomClickListener must be called on the main UI thread.");
        if (this.zze.zzt == null) {
            return null;
        }
        return this.zze.zzt.get(str);
    }

    public final void zzb(int i) {
        com.google.android.gms.common.internal.zzbq.zzb("setMaxNumberOfAds must be called on the main UI thread.");
        this.zzo = i;
    }

    public final void zzb(List<String> list) {
        com.google.android.gms.common.internal.zzbq.zzb("setNativeTemplates must be called on the main UI thread.");
        this.zze.zzad = list;
    }

    public final void zzba() {
        zzaof zzaofVar = this.zzm;
        if (zzaofVar == null || zzaofVar.zzb() == null || this.zze.zzv == null || this.zze.zzv.zzf == null) {
            return;
        }
        this.zzm.zzb().zza(this.zze.zzv.zzf);
    }

    @Override // com.google.android.gms.ads.internal.zza
    protected final void zzc(boolean z) {
        super.zzc(z);
        if (this.zzn) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzey)).booleanValue()) {
                zzax();
            }
        }
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zzh() {
        throw new IllegalStateException("Native Ad DOES NOT support pause().");
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zzi() {
        throw new IllegalStateException("Native Ad DOES NOT support resume().");
    }

    @Override // com.google.android.gms.ads.internal.zza
    protected final void zzp() {
        zzc(false);
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final String zzv() {
        return this.zze.zzb;
    }
}
