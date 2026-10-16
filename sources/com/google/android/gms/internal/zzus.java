package com.google.android.gms.internal;

import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzus {
    private static zzus zza = new zzus();
    private int zzb;
    private int zzc;
    private int zzd;
    private int zze;
    private int zzf;

    public static zzus zza() {
        return zza;
    }

    final void zza(int i) {
        this.zzb += i;
    }

    final void zzb() {
        this.zzc++;
    }

    final void zzc() {
        this.zzd++;
    }

    final void zzd() {
        this.zze++;
    }

    final void zze() {
        this.zzf++;
    }

    public final int zzf() {
        return this.zzc;
    }

    public final int zzg() {
        return this.zzd;
    }

    public final int zzh() {
        return this.zze;
    }

    public final int zzi() {
        return this.zzf;
    }

    public final Bundle zzj() {
        Bundle bundle = new Bundle();
        bundle.putInt("ipl", this.zzb);
        bundle.putInt("ipds", this.zzc);
        bundle.putInt("ipde", this.zzd);
        bundle.putInt("iph", this.zze);
        bundle.putInt("ipm", this.zzf);
        return bundle;
    }
}
