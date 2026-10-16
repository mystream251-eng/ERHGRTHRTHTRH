package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzvs extends zzwm {
    private final Object zza = new Object();
    private zzvx zzb;
    private zzvr zzc;

    @Override // com.google.android.gms.internal.zzwl
    public final void zza() {
        synchronized (this.zza) {
            if (this.zzc != null) {
                this.zzc.zzae();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zza(int i) {
        synchronized (this.zza) {
            if (this.zzb != null) {
                this.zzb.zza(i == 3 ? 1 : 2);
                this.zzb = null;
            }
        }
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zza(zzro zzroVar, String str) {
        synchronized (this.zza) {
            if (this.zzc != null) {
                this.zzc.zza(zzroVar, str);
            }
        }
    }

    public final void zza(zzvr zzvrVar) {
        synchronized (this.zza) {
            this.zzc = zzvrVar;
        }
    }

    public final void zza(zzvx zzvxVar) {
        synchronized (this.zza) {
            this.zzb = zzvxVar;
        }
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zza(zzwo zzwoVar) {
        synchronized (this.zza) {
            if (this.zzb != null) {
                this.zzb.zza(0, zzwoVar);
                this.zzb = null;
            } else {
                if (this.zzc != null) {
                    this.zzc.zzai();
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zza(String str, String str2) {
        synchronized (this.zza) {
            if (this.zzc != null) {
                this.zzc.zzb(str, str2);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zzb() {
        synchronized (this.zza) {
            if (this.zzc != null) {
                this.zzc.zzaf();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zzc() {
        synchronized (this.zza) {
            if (this.zzc != null) {
                this.zzc.zzag();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zzd() {
        synchronized (this.zza) {
            if (this.zzc != null) {
                this.zzc.zzah();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zze() {
        synchronized (this.zza) {
            if (this.zzb != null) {
                this.zzb.zza(0);
                this.zzb = null;
            } else {
                if (this.zzc != null) {
                    this.zzc.zzai();
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zzf() {
        synchronized (this.zza) {
            if (this.zzc != null) {
                this.zzc.zzaj();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzwl
    public final void zzg() {
        synchronized (this.zza) {
            if (this.zzc != null) {
                this.zzc.zzad();
            }
        }
    }
}
