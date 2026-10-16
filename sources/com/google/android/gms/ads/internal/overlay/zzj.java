package com.google.android.gms.ads.internal.overlay;

import android.graphics.Bitmap;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahs;
import com.google.android.gms.internal.zzaij;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzj extends zzahs {
    final /* synthetic */ zzd zza;

    private zzj(zzd zzdVar) {
        this.zza = zzdVar;
    }

    /* synthetic */ zzj(zzd zzdVar, zzf zzfVar) {
        this(zzdVar);
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void b_() {
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void zza() {
        Bitmap bitmapZza = zzbt.zzx().zza(Integer.valueOf(this.zza.zzb.zzo.zze));
        if (bitmapZza != null) {
            zzaij.zza.post(new zzk(this, zzbt.zzg().zza(this.zza.zza, bitmapZza, this.zza.zzb.zzo.zzc, this.zza.zzb.zzo.zzd)));
        }
    }
}
