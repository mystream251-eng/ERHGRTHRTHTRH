package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaqa {
    public final int zza;
    public final int zzb;
    private final int zzc;

    private zzaqa(int i, int i2, int i3) {
        this.zzc = i;
        this.zzb = i2;
        this.zza = i3;
    }

    public static zzaqa zza() {
        return new zzaqa(0, 0, 0);
    }

    public static zzaqa zza(int i, int i2) {
        return new zzaqa(1, i, i2);
    }

    public static zzaqa zza(zzko zzkoVar) {
        if (zzkoVar.zzd) {
            return new zzaqa(3, 0, 0);
        }
        if (zzkoVar.zzi) {
            return new zzaqa(2, 0, 0);
        }
        return zzkoVar.zzh ? zza() : zza(zzkoVar.zzf, zzkoVar.zzc);
    }

    public static zzaqa zzb() {
        return new zzaqa(4, 0, 0);
    }

    public final boolean zzc() {
        return this.zzc == 2;
    }

    public final boolean zzd() {
        return this.zzc == 3;
    }

    public final boolean zze() {
        return this.zzc == 0;
    }

    public final boolean zzf() {
        return this.zzc == 4;
    }
}
