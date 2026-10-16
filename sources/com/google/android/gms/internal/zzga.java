package com.google.android.gms.internal;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public final class zzga implements zzhf {
    private final View zza;
    private final zzahd zzb;

    public zzga(View view, zzahd zzahdVar) {
        this.zza = view;
        this.zzb = zzahdVar;
    }

    @Override // com.google.android.gms.internal.zzhf
    public final View zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.zzhf
    public final boolean zzb() {
        return this.zzb == null || this.zza == null;
    }

    @Override // com.google.android.gms.internal.zzhf
    public final zzhf zzc() {
        return this;
    }
}
