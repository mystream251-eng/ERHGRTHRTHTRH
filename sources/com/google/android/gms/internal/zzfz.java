package com.google.android.gms.internal;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfz implements zzhf {
    private zzpv zza;

    public zzfz(zzpv zzpvVar) {
        this.zza = zzpvVar;
    }

    @Override // com.google.android.gms.internal.zzhf
    public final View zza() {
        zzpv zzpvVar = this.zza;
        if (zzpvVar != null) {
            return zzpvVar.zzh();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzhf
    public final boolean zzb() {
        return this.zza == null;
    }

    @Override // com.google.android.gms.internal.zzhf
    public final zzhf zzc() {
        return this;
    }
}
