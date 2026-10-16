package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzanv extends zzahs {
    final zzann zza;
    final zzany zzb;
    private final String zzc;

    zzanv(zzann zzannVar, zzany zzanyVar, String str) {
        this.zza = zzannVar;
        this.zzb = zzanyVar;
        this.zzc = str;
        zzbt.zzy().zza(this);
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void b_() {
        this.zzb.zza();
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void zza() {
        try {
            this.zzb.zza(this.zzc);
        } finally {
            zzaij.zza.post(new zzanw(this));
        }
    }
}
