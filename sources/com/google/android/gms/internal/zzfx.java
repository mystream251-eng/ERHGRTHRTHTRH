package com.google.android.gms.internal;

import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfx implements zzhf {
    private WeakReference<zzpv> zza;

    public zzfx(zzpv zzpvVar) {
        this.zza = new WeakReference<>(zzpvVar);
    }

    @Override // com.google.android.gms.internal.zzhf
    public final View zza() {
        zzpv zzpvVar = this.zza.get();
        if (zzpvVar != null) {
            return zzpvVar.zzh();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzhf
    public final boolean zzb() {
        return this.zza.get() == null;
    }

    @Override // com.google.android.gms.internal.zzhf
    public final zzhf zzc() {
        return new zzfz(this.zza.get());
    }
}
