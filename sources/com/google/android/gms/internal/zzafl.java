package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbt;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzafl extends zzahs implements zzafr, zzafu {
    public final String zza;
    private final zzahe zzb;
    private final Context zzc;
    private final zzafy zzd;
    private final zzafu zze;
    private final String zzg;
    private final zzvp zzh;
    private final long zzi;
    private zzafo zzl;
    private Future zzm;
    private int zzj = 0;
    private int zzk = 3;
    private final Object zzf = new Object();

    public zzafl(Context context, String str, String str2, zzvp zzvpVar, zzahe zzaheVar, zzafy zzafyVar, zzafu zzafuVar, long j) {
        this.zzc = context;
        this.zza = str;
        this.zzg = str2;
        this.zzh = zzvpVar;
        this.zzb = zzaheVar;
        this.zzd = zzafyVar;
        this.zze = zzafuVar;
        this.zzi = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzkk zzkkVar, zzwi zzwiVar) {
        this.zzd.zzb().zza((zzafu) this);
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zza)) {
                zzwiVar.zza(zzkkVar, this.zzg, this.zzh.zza);
            } else {
                zzwiVar.zza(zzkkVar, this.zzg);
            }
        } catch (RemoteException e) {
            zzahw.zzc("Fail to load ad from adapter.", e);
            zza(this.zza, 0);
        }
    }

    private final boolean zza(long j) {
        int i;
        long jZzb = this.zzi - (zzbt.zzl().zzb() - j);
        if (jZzb <= 0) {
            i = 4;
        } else {
            try {
                this.zzf.wait(jZzb);
                return true;
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                i = 5;
            }
        }
        this.zzk = i;
        return false;
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void b_() {
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void zza() {
        long jZzb;
        Handler handler;
        Runnable zzafnVar;
        zzafy zzafyVar = this.zzd;
        if (zzafyVar == null || zzafyVar.zzb() == null || this.zzd.zza() == null) {
            return;
        }
        zzaft zzaftVarZzb = this.zzd.zzb();
        zzaftVarZzb.zza((zzafu) null);
        zzaftVarZzb.zza((zzafr) this);
        zzkk zzkkVar = this.zzb.zza.zzc;
        zzwi zzwiVarZza = this.zzd.zza();
        try {
            if (zzwiVarZza.zzg()) {
                handler = zzako.zza;
                zzafnVar = new zzafm(this, zzkkVar, zzwiVarZza);
            } else {
                handler = zzako.zza;
                zzafnVar = new zzafn(this, zzwiVarZza, zzkkVar, zzaftVarZzb);
            }
            handler.post(zzafnVar);
            while (true) {
                synchronized (this.zzf) {
                    if (this.zzj == 0) {
                        if (!zza(jZzb)) {
                            this.zzl = new zzafq().zza(this.zzk).zza(zzbt.zzl().zzb() - jZzb).zza(this.zza).zzb(this.zzh.zzd).zza();
                            break;
                        }
                    } else {
                        this.zzl = new zzafq().zza(zzbt.zzl().zzb() - jZzb).zza(1 == this.zzj ? 6 : this.zzk).zza(this.zza).zzb(this.zzh.zzd).zza();
                        break;
                    }
                }
            }
        } catch (RemoteException e) {
            zzahw.zzc("Fail to check if adapter is initialized.", e);
            zza(this.zza, 0);
        }
        jZzb = zzbt.zzl().zzb();
        zzaftVarZzb.zza((zzafu) null);
        zzaftVarZzb.zza((zzafr) null);
        if (this.zzj == 1) {
            this.zze.zza(this.zza);
        } else {
            this.zze.zza(this.zza, this.zzk);
        }
    }

    @Override // com.google.android.gms.internal.zzafr
    public final void zza(int i) {
        zza(this.zza, 0);
    }

    @Override // com.google.android.gms.internal.zzafu
    public final void zza(String str) {
        synchronized (this.zzf) {
            this.zzj = 1;
            this.zzf.notify();
        }
    }

    @Override // com.google.android.gms.internal.zzafu
    public final void zza(String str, int i) {
        synchronized (this.zzf) {
            this.zzj = 2;
            this.zzk = i;
            this.zzf.notify();
        }
    }

    public final Future zzd() {
        Future future = this.zzm;
        if (future != null) {
            return future;
        }
        zzalt zzaltVar = (zzalt) zzc();
        this.zzm = zzaltVar;
        return zzaltVar;
    }

    public final zzafo zze() {
        zzafo zzafoVar;
        synchronized (this.zzf) {
            zzafoVar = this.zzl;
        }
        return zzafoVar;
    }

    public final zzvp zzf() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.zzafr
    public final void zzg() {
        zza(this.zzb.zza.zzc, this.zzd.zza());
    }
}
