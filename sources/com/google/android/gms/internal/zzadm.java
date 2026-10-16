package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzadm {
    public final zzip zzb;
    public final zzagy zzc;
    public final zznx zzd;
    public final zzaeh zze;
    public final zzvm zzf;
    public final zzaei zzg;
    public final zzaej zzh;
    public final zzyw zzi;
    public final zzahc zzj;
    public final zzadr zzl;
    public final zzady zza = null;
    public final boolean zzk = true;

    private zzadm(zzady zzadyVar, zzip zzipVar, zzagy zzagyVar, zznx zznxVar, zzaeh zzaehVar, zzvm zzvmVar, zzaei zzaeiVar, zzaej zzaejVar, zzyw zzywVar, zzahc zzahcVar, boolean z, zzadr zzadrVar) {
        this.zzb = zzipVar;
        this.zzc = zzagyVar;
        this.zzd = zznxVar;
        this.zze = zzaehVar;
        this.zzf = zzvmVar;
        this.zzg = zzaeiVar;
        this.zzh = zzaejVar;
        this.zzi = zzywVar;
        this.zzj = zzahcVar;
        this.zzl = zzadrVar;
    }

    public static zzadm zza(Context context) {
        zzbt.zzab().zza(context);
        zzaen zzaenVar = new zzaen(context);
        return new zzadm(null, new zzis(), new zzagz(), new zznw(), new zzaef(context, zzaenVar.zzb()), new zzvn(), new zzael(), new zzaem(), new zzyv(), new zzaha(), true, zzaenVar);
    }
}
