package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public abstract class zzzg implements zzajb<Void>, zzapv {
    protected final Context zza;
    protected final zzaof zzb;
    protected zzacj zzc;
    private zzzn zzd;
    private zzahe zze;
    private Runnable zzf;
    private Object zzg = new Object();
    private AtomicBoolean zzh = new AtomicBoolean(true);

    protected zzzg(Context context, zzahe zzaheVar, zzaof zzaofVar, zzzn zzznVar) {
        this.zza = context;
        this.zze = zzaheVar;
        this.zzc = zzaheVar.zzb;
        this.zzb = zzaofVar;
        this.zzd = zzznVar;
    }

    protected abstract void zza();

    protected void zza(int i) {
        if (i != -2) {
            this.zzc = new zzacj(i, this.zzc.zzj);
        }
        this.zzb.zzp();
        zzzn zzznVar = this.zzd;
        zzacf zzacfVar = this.zze.zza;
        zzznVar.zzb(new zzahd(zzacfVar.zzc, this.zzb, this.zzc.zzc, i, this.zzc.zze, this.zzc.zzi, this.zzc.zzk, this.zzc.zzj, zzacfVar.zzi, this.zzc.zzg, null, null, null, null, null, this.zzc.zzh, this.zze.zzd, this.zzc.zzf, this.zze.zzf, this.zzc.zzm, this.zzc.zzn, this.zze.zzh, null, this.zzc.zzaa, this.zzc.zzab, this.zzc.zzac, this.zzc.zzad, this.zzc.zzae, null, this.zzc.zzah, this.zzc.zzal, this.zze.zzi, this.zze.zzb.zzao, this.zze.zzj));
    }

    @Override // com.google.android.gms.internal.zzapv
    public final void zza(zzaof zzaofVar, boolean z) {
        zzahw.zzb("WebView finished loading.");
        if (this.zzh.getAndSet(false)) {
            zza(z ? -2 : 0);
            zzaij.zza.removeCallbacks(this.zzf);
        }
    }

    @Override // com.google.android.gms.internal.zzajb
    public void zzb() {
        if (this.zzh.getAndSet(false)) {
            this.zzb.stopLoading();
            zzbt.zzg();
            zzaip.zza(this.zzb);
            zza(-1);
            zzaij.zza.removeCallbacks(this.zzf);
        }
    }

    @Override // com.google.android.gms.internal.zzajb
    public final /* synthetic */ Void zzc() {
        zzbq.zzb("Webview render task needs to be called on UI thread.");
        this.zzf = new zzzh(this);
        zzaij.zza.postDelayed(this.zzf, ((Long) zzlc.zzf().zza(zzoi.zzdt)).longValue());
        zza();
        return null;
    }
}
