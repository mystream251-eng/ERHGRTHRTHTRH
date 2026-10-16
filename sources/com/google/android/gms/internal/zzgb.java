package com.google.android.gms.internal;

import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public final class zzgb implements zzhf {
    private final WeakReference<View> zza;
    private final WeakReference<zzahd> zzb;

    public zzgb(View view, zzahd zzahdVar) {
        this.zza = new WeakReference<>(view);
        this.zzb = new WeakReference<>(zzahdVar);
    }

    @Override // com.google.android.gms.internal.zzhf
    public final View zza() {
        return this.zza.get();
    }

    @Override // com.google.android.gms.internal.zzhf
    public final boolean zzb() {
        return this.zza.get() == null || this.zzb.get() == null;
    }

    @Override // com.google.android.gms.internal.zzhf
    public final zzhf zzc() {
        return new zzga(this.zza.get(), this.zzb.get());
    }
}
