package com.google.android.gms.ads.internal;

import android.content.Context;
import android.view.View;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzagq;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzaop;
import com.google.android.gms.internal.zzaqa;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzwf;
import com.google.android.gms.internal.zzyo;
import com.google.android.gms.internal.zzzm;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public abstract class zzi extends zzd implements zzae, zzyo {
    private boolean zzj;

    public zzi(Context context, zzko zzkoVar, String str, zzwf zzwfVar, zzala zzalaVar, zzv zzvVar) {
        super(context, zzkoVar, str, zzwfVar, zzalaVar, zzvVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    protected zzaof zza(zzahe zzaheVar, zzw zzwVar, zzagq zzagqVar) throws zzaop {
        View nextView = this.zze.zzf.getNextView();
        if (nextView instanceof zzaof) {
            ((zzaof) nextView).destroy();
        }
        if (nextView != 0) {
            this.zze.zzf.removeView(nextView);
        }
        zzaof zzaofVarZza = zzbt.zzf().zza(this.zze.zzc, zzaqa.zza(this.zze.zzi), this.zze.zzi.zza, false, false, this.zze.zzd, this.zze.zze, this.zza, this, this.zzh, zzaheVar.zzi);
        if (this.zze.zzi.zzg == null) {
            zza(zzaofVarZza.zzm());
        }
        zzaofVarZza.zzv().zza(this, this, this, this, false, null, zzwVar, this, zzagqVar);
        zza(zzaofVarZza);
        zzaofVarZza.zza(zzaheVar.zza.zzv);
        return zzaofVarZza;
    }

    @Override // com.google.android.gms.internal.zzyo
    public final void zza(int i, int i2, int i3, int i4) {
        zzo();
    }

    @Override // com.google.android.gms.ads.internal.zza
    protected void zza(zzahe zzaheVar, zzov zzovVar) {
        if (zzaheVar.zze != -2) {
            zzaij.zza.post(new zzk(this, zzaheVar));
            return;
        }
        if (zzaheVar.zzd != null) {
            this.zze.zzi = zzaheVar.zzd;
        }
        if (!zzaheVar.zzb.zzg || zzaheVar.zzb.zzz) {
            zzaij.zza.post(new zzl(this, zzaheVar, this.zzh.zzc.zza(this.zze.zzc, this.zze.zze, zzaheVar.zzb), zzovVar));
        } else {
            this.zze.zzag = 0;
            zzbu zzbuVar = this.zze;
            zzbt.zzd();
            zzbuVar.zzh = zzzm.zza(this.zze.zzc, this, zzaheVar, this.zze.zzd, null, this.zzi, this, zzovVar);
        }
    }

    protected final void zza(zzaof zzaofVar) {
        zzaofVar.zza("/trackActiveViewUnit", new zzj(this));
    }

    @Override // com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzlt
    public final void zza(zzpb zzpbVar) {
        com.google.android.gms.common.internal.zzbq.zzb("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zze.zzaa = zzpbVar;
    }

    @Override // com.google.android.gms.ads.internal.zzd, com.google.android.gms.ads.internal.zza
    protected boolean zza(zzahd zzahdVar, zzahd zzahdVar2) {
        if (this.zze.zzd() && this.zze.zzf != null) {
            this.zze.zzf.zza().zzc(zzahdVar2.zzz);
        }
        try {
            if (zzahdVar2.zzb != null && !zzahdVar2.zzm && zzahdVar2.zzaj) {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzgx)).booleanValue()) {
                    String str = zzahdVar2.zza.zzq;
                    if ((str == null || "com.google.ads.mediation.AbstractAdViewAdapter".equals(str)) && !zzahdVar2.zza.zzc.containsKey("sdk_less_server_data")) {
                        try {
                            zzahdVar2.zzb.zzak();
                        } catch (Throwable unused) {
                            zzahw.zza("Could not render test Ad label.");
                        }
                    }
                }
            }
        } catch (RuntimeException unused2) {
            zzahw.zza("Could not render test AdLabel.");
        }
        return super.zza(zzahdVar, zzahdVar2);
    }

    @Override // com.google.android.gms.ads.internal.zzae
    public final void zzaq() {
        onAdClicked();
    }

    @Override // com.google.android.gms.ads.internal.zzae
    public final void zzar() {
        zzak();
        zzg();
    }

    @Override // com.google.android.gms.internal.zzyo
    public final void zzas() {
        zzm();
    }

    @Override // com.google.android.gms.ads.internal.zzae
    public final void zzb(View view) {
        this.zze.zzaf = view;
        zzb(new zzahd(this.zze.zzk, null, null, null, null, null, null, null));
    }

    final void zzb(zzaof zzaofVar) {
        if (this.zze.zzj != null) {
            this.zzg.zza(this.zze.zzi, this.zze.zzj, zzaofVar.zzm(), zzaofVar);
            this.zzj = false;
        } else {
            this.zzj = true;
            zzahw.zze("Request to enable ActiveView before adState is available.");
        }
    }

    @Override // com.google.android.gms.ads.internal.zza
    protected void zzp() {
        super.zzp();
        if (this.zzj) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzey)).booleanValue()) {
                zzb(this.zze.zzj.zzb);
            }
        }
    }
}
