package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public abstract class zzahs implements zzajb<zzalt> {
    private volatile Thread zzb;
    private final Runnable zza = new zzaht(this);
    private boolean zzc = false;

    public zzahs() {
    }

    public zzahs(boolean z) {
    }

    public abstract void b_();

    public abstract void zza();

    @Override // com.google.android.gms.internal.zzajb
    public final void zzb() {
        b_();
        if (this.zzb != null) {
            this.zzb.interrupt();
        }
    }

    @Override // com.google.android.gms.internal.zzajb
    public final /* synthetic */ zzalt zzc() {
        return this.zzc ? zzaid.zza(1, this.zza) : zzaid.zza(this.zza);
    }

    public final zzalt zzh() {
        return this.zzc ? zzaid.zza(1, this.zza) : zzaid.zza(this.zza);
    }
}
