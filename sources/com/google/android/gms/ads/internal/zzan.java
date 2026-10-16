package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahs;
import com.google.android.gms.internal.zzaij;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzan extends zzahs {
    final /* synthetic */ zzak zza;
    private final int zzb;

    public zzan(zzak zzakVar, int i) {
        this.zza = zzakVar;
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void b_() {
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void zza() {
        zzap zzapVar = new zzap(this.zza.zze.zzah, this.zza.zzau(), this.zza.zzl, this.zza.zzm, this.zza.zze.zzah ? this.zzb : -1, this.zza.zzn, this.zza.zze.zzj.zzai);
        int iZzz = this.zza.zze.zzj.zzb.zzz();
        if (iZzz == -1) {
            iZzz = this.zza.zze.zzj.zzg;
        }
        zzak zzakVar = this.zza;
        zzaij.zza.post(new zzao(this, new AdOverlayInfoParcel(zzakVar, zzakVar, zzakVar, zzakVar.zze.zzj.zzb, iZzz, this.zza.zze.zze, this.zza.zze.zzj.zzz, zzapVar)));
    }
}
