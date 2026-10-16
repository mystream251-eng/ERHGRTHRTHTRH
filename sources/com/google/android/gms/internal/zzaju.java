package com.google.android.gms.internal;

import java.io.ByteArrayInputStream;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes2.dex */
final class zzaju<T> implements zzale<zzp, T> {
    private /* synthetic */ zzajx zza;

    zzaju(zzajr zzajrVar, zzajx zzajxVar) {
        this.zza = zzajxVar;
    }

    @Override // com.google.android.gms.internal.zzale
    public final /* synthetic */ Object zza(zzp zzpVar) {
        return this.zza.zza(new ByteArrayInputStream(zzpVar.zzb));
    }
}
