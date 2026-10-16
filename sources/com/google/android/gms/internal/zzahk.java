package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
final class zzahk {
    private final Object zza;
    private volatile int zzb;
    private volatile long zzc;

    private zzahk() {
        this.zza = new Object();
        this.zzb = zzahl.zza;
        this.zzc = 0L;
    }

    private final void zza(int i, int i2) {
        zzd();
        long jZza = zzbt.zzl().zza();
        synchronized (this.zza) {
            if (this.zzb != i) {
                return;
            }
            this.zzb = i2;
            if (this.zzb == zzahl.zzc) {
                this.zzc = jZza;
            }
        }
    }

    private final void zzd() {
        long jZza = zzbt.zzl().zza();
        synchronized (this.zza) {
            if (this.zzb == zzahl.zzc) {
                if (this.zzc + ((Long) zzlc.zzf().zza(zzoi.zzgu)).longValue() <= jZza) {
                    this.zzb = zzahl.zza;
                }
            }
        }
    }

    public final void zza(boolean z) {
        int i;
        int i2;
        if (z) {
            i = zzahl.zza;
            i2 = zzahl.zzb;
        } else {
            i = zzahl.zzb;
            i2 = zzahl.zza;
        }
        zza(i, i2);
    }

    public final boolean zza() {
        zzd();
        return this.zzb == zzahl.zzb;
    }

    public final boolean zzb() {
        zzd();
        return this.zzb == zzahl.zzc;
    }

    public final void zzc() {
        zza(zzahl.zzb, zzahl.zzc);
    }
}
