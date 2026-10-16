package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzn;

/* JADX INFO: loaded from: classes2.dex */
final class zzaok implements zzn {
    private zzaof zza;
    private zzn zzb;

    public zzaok(zzaof zzaofVar, zzn zznVar) {
        this.zza = zzaofVar;
        this.zzb = zznVar;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzaa() {
        this.zzb.zzaa();
        this.zza.zzo();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzab() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzac() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzz() {
        this.zzb.zzz();
        this.zza.zzn();
    }
}
