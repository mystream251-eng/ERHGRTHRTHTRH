package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbm;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzv;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zzaoo implements Callable<zzaof> {
    private /* synthetic */ Context zza;
    private /* synthetic */ zzaqa zzb;
    private /* synthetic */ String zzc;
    private /* synthetic */ boolean zzd;
    private /* synthetic */ boolean zze;
    private /* synthetic */ zzcv zzf;
    private /* synthetic */ zzala zzg;
    private /* synthetic */ zzov zzh;
    private /* synthetic */ zzbm zzi;
    private /* synthetic */ zzv zzj;
    private /* synthetic */ zziu zzk;

    zzaoo(zzaol zzaolVar, Context context, zzaqa zzaqaVar, String str, boolean z, boolean z2, zzcv zzcvVar, zzala zzalaVar, zzov zzovVar, zzbm zzbmVar, zzv zzvVar, zziu zziuVar) {
        this.zza = context;
        this.zzb = zzaqaVar;
        this.zzc = str;
        this.zzd = z;
        this.zze = z2;
        this.zzf = zzcvVar;
        this.zzg = zzalaVar;
        this.zzh = zzovVar;
        this.zzi = zzbmVar;
        this.zzj = zzvVar;
        this.zzk = zziuVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ zzaof call() throws Exception {
        zzaoq zzaoqVar = new zzaoq(zzaor.zza(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk));
        zzaoqVar.setWebViewClient(zzbt.zzg().zza(zzaoqVar, this.zze));
        zzaoqVar.setWebChromeClient(zzbt.zzg().zzc((zzaof) zzaoqVar));
        return zzaoqVar;
    }
}
