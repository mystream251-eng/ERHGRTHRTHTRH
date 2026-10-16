package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public abstract class zzzi extends zzahs {
    protected final zzzn zza;
    protected final Context zzb;
    protected final Object zzc;
    protected final Object zzd;
    protected final zzahe zze;
    protected zzacj zzf;

    protected zzzi(Context context, zzahe zzaheVar, zzzn zzznVar) {
        super(true);
        this.zzc = new Object();
        this.zzd = new Object();
        this.zzb = context;
        this.zze = zzaheVar;
        this.zzf = zzaheVar.zzb;
        this.zza = zzznVar;
    }

    @Override // com.google.android.gms.internal.zzahs
    public void b_() {
    }

    protected abstract zzahd zza(int i);

    @Override // com.google.android.gms.internal.zzahs
    public final void zza() {
        synchronized (this.zzc) {
            zzahw.zzb("AdRendererBackgroundTask started.");
            int i = this.zze.zze;
            try {
                zza(SystemClock.elapsedRealtime());
            } catch (zzzl e) {
                int iZza = e.zza();
                if (iZza == 3 || iZza == -1) {
                    zzahw.zzd(e.getMessage());
                } else {
                    zzahw.zze(e.getMessage());
                }
                this.zzf = this.zzf == null ? new zzacj(iZza) : new zzacj(iZza, this.zzf.zzj);
                zzaij.zza.post(new zzzj(this));
                i = iZza;
            }
            zzaij.zza.post(new zzzk(this, zza(i)));
        }
    }

    protected abstract void zza(long j) throws zzzl;
}
