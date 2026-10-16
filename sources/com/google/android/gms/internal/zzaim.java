package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
final class zzaim implements zzakr {
    private /* synthetic */ Context zza;
    private /* synthetic */ String zzb;

    zzaim(zzaij zzaijVar, Context context, String str) {
        this.zza = context;
        this.zzb = str;
    }

    @Override // com.google.android.gms.internal.zzakr
    public final void zza(String str) {
        zzbt.zze();
        zzaij.zzb(this.zza, this.zzb, str);
    }
}
