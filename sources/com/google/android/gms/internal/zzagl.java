package com.google.android.gms.internal;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzagl implements zzagr {
    private zzags zza;

    public zzagl(zzags zzagsVar) {
        this.zza = zzagsVar;
    }

    @Override // com.google.android.gms.internal.zzagr
    public final zzagq zza(Context context, zzala zzalaVar, zzacj zzacjVar) {
        if (zzacjVar.zzak == null) {
            return null;
        }
        return new zzagf(context, zzalaVar, zzacjVar.zzak, zzacjVar.zza, this.zza);
    }
}
