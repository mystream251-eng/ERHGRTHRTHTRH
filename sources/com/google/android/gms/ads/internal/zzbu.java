package com.google.android.gms.ads.internal;

import android.R;
import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzafc;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahf;
import com.google.android.gms.internal.zzahp;
import com.google.android.gms.internal.zzahs;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzajb;
import com.google.android.gms.internal.zzake;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzcr;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzlf;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzms;
import com.google.android.gms.internal.zzns;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzrs;
import com.google.android.gms.internal.zzrv;
import com.google.android.gms.internal.zzry;
import com.google.android.gms.internal.zzsb;
import com.google.android.gms.internal.zzse;
import com.google.android.gms.internal.zzsh;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzbu implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    final String zza;
    zzpb zzaa;
    zzafc zzab;
    public String zzac;
    List<String> zzad;
    public zzahp zzae;
    View zzaf;
    public int zzag;
    boolean zzah;
    private HashSet<zzahf> zzai;
    private int zzaj;
    private int zzak;
    private zzake zzal;
    private boolean zzam;
    private boolean zzan;
    private boolean zzao;
    public String zzb;
    public final Context zzc;
    final zzcv zzd;
    public final zzala zze;
    zzbv zzf;
    public zzahs zzg;
    public zzajb zzh;
    public zzko zzi;
    public zzahd zzj;
    public zzahe zzk;
    public zzahf zzl;
    zzlf zzm;
    zzli zzn;
    zzly zzo;
    zzme zzp;
    zzrs zzq;
    zzrv zzr;
    zzsh zzs;
    SimpleArrayMap<String, zzry> zzt;
    SimpleArrayMap<String, zzsb> zzu;
    zzqh zzv;
    zzns zzw;
    zzms zzx;
    zzse zzy;
    List<Integer> zzz;

    public zzbu(Context context, zzko zzkoVar, String str, zzala zzalaVar) {
        this(context, zzkoVar, str, zzalaVar, null);
    }

    private zzbu(Context context, zzko zzkoVar, String str, zzala zzalaVar, zzcv zzcvVar) {
        this.zzae = null;
        this.zzaf = null;
        this.zzag = 0;
        this.zzah = false;
        this.zzai = null;
        this.zzaj = -1;
        this.zzak = -1;
        this.zzam = true;
        this.zzan = true;
        this.zzao = false;
        zzoi.zza(context);
        if (zzbt.zzi().zzb() != null) {
            List<String> listZzb = zzoi.zzb();
            if (zzalaVar.zzb != 0) {
                listZzb.add(Integer.toString(zzalaVar.zzb));
            }
            zzbt.zzi().zzb().zza(listZzb);
        }
        this.zza = UUID.randomUUID().toString();
        if (zzkoVar.zzd || zzkoVar.zzh) {
            this.zzf = null;
        } else {
            zzbv zzbvVar = new zzbv(context, str, zzalaVar.zza, this, this);
            this.zzf = zzbvVar;
            zzbvVar.setMinimumWidth(zzkoVar.zzf);
            this.zzf.setMinimumHeight(zzkoVar.zzc);
            this.zzf.setVisibility(4);
        }
        this.zzi = zzkoVar;
        this.zzb = str;
        this.zzc = context;
        this.zze = zzalaVar;
        this.zzd = new zzcv(new zzaf(this));
        this.zzal = new zzake(200L);
        this.zzu = new SimpleArrayMap<>();
    }

    private final void zzb(boolean z) {
        zzahd zzahdVar;
        View viewFindViewById;
        if (this.zzf == null || (zzahdVar = this.zzj) == null || zzahdVar.zzb == null || this.zzj.zzb.zzv() == null) {
            return;
        }
        if (!z || this.zzal.zza()) {
            if (this.zzj.zzb.zzv().zzb()) {
                int[] iArr = new int[2];
                this.zzf.getLocationOnScreen(iArr);
                zzlc.zza();
                int iZzb = zzako.zzb(this.zzc, iArr[0]);
                zzlc.zza();
                int iZzb2 = zzako.zzb(this.zzc, iArr[1]);
                if (iZzb != this.zzaj || iZzb2 != this.zzak) {
                    this.zzaj = iZzb;
                    this.zzak = iZzb2;
                    this.zzj.zzb.zzv().zza(this.zzaj, this.zzak, !z);
                }
            }
            zzbv zzbvVar = this.zzf;
            if (zzbvVar == null || (viewFindViewById = zzbvVar.getRootView().findViewById(R.id.content)) == null) {
                return;
            }
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            this.zzf.getGlobalVisibleRect(rect);
            viewFindViewById.getGlobalVisibleRect(rect2);
            if (rect.top != rect2.top) {
                this.zzam = false;
            }
            if (rect.bottom != rect2.bottom) {
                this.zzan = false;
            }
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        zzb(false);
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        zzb(true);
        this.zzao = true;
    }

    public final HashSet<zzahf> zza() {
        return this.zzai;
    }

    final void zza(View view) {
        zzcr zzcrVarZza;
        if (((Boolean) zzlc.zzf().zza(zzoi.zzdz)).booleanValue() && (zzcrVarZza = this.zzd.zza()) != null) {
            zzcrVarZza.zza(view);
        }
    }

    public final void zza(HashSet<zzahf> hashSet) {
        this.zzai = hashSet;
    }

    public final void zza(boolean z) {
        zzahd zzahdVar;
        if (this.zzag == 0 && (zzahdVar = this.zzj) != null && zzahdVar.zzb != null) {
            this.zzj.zzb.stopLoading();
        }
        zzahs zzahsVar = this.zzg;
        if (zzahsVar != null) {
            zzahsVar.zzb();
        }
        zzajb zzajbVar = this.zzh;
        if (zzajbVar != null) {
            zzajbVar.zzb();
        }
        if (z) {
            this.zzj = null;
        }
    }

    public final void zzb() {
        zzahd zzahdVar = this.zzj;
        if (zzahdVar == null || zzahdVar.zzb == null) {
            return;
        }
        this.zzj.zzb.destroy();
    }

    public final void zzc() {
        zzahd zzahdVar = this.zzj;
        if (zzahdVar == null || zzahdVar.zzo == null) {
            return;
        }
        try {
            this.zzj.zzo.zzc();
        } catch (RemoteException unused) {
            zzahw.zze("Could not destroy mediation adapter.");
        }
    }

    public final boolean zzd() {
        return this.zzag == 0;
    }

    public final boolean zze() {
        return this.zzag == 1;
    }

    public final String zzf() {
        if (this.zzam && this.zzan) {
            return "";
        }
        if (this.zzam) {
            return this.zzao ? "top-scrollable" : "top-locked";
        }
        if (this.zzan) {
            return this.zzao ? "bottom-scrollable" : "bottom-locked";
        }
        return "";
    }
}
