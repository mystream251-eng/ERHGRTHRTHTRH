package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public abstract class zzabz implements zzabx, zzajb<Void> {
    private final zzamf<zzacf> zza;
    private final zzabx zzb;
    private final Object zzc = new Object();

    public zzabz(zzamf<zzacf> zzamfVar, zzabx zzabxVar) {
        this.zza = zzamfVar;
        this.zzb = zzabxVar;
    }

    public abstract void zza();

    @Override // com.google.android.gms.internal.zzabx
    public final void zza(zzacj zzacjVar) {
        synchronized (this.zzc) {
            this.zzb.zza(zzacjVar);
            zza();
        }
    }

    final boolean zza(zzacn zzacnVar, zzacf zzacfVar) {
        try {
            zzacnVar.zza(zzacfVar, new zzaci(this));
            return true;
        } catch (Throwable th) {
            zzahw.zzc("Could not fetch ad response from ad request service due to an Exception.", th);
            zzbt.zzi().zza(th, "AdRequestClientTask.getAdResponseFromService");
            this.zzb.zza(new zzacj(0));
            return false;
        }
    }

    @Override // com.google.android.gms.internal.zzajb
    public final void zzb() {
        zza();
    }

    @Override // com.google.android.gms.internal.zzajb
    public final /* synthetic */ Void zzc() {
        zzacn zzacnVarZzd = zzd();
        if (zzacnVarZzd != null) {
            this.zza.zza(new zzaca(this, zzacnVarZzd), new zzacb(this));
            return null;
        }
        this.zzb.zza(new zzacj(0));
        zza();
        return null;
    }

    public abstract zzacn zzd();
}
