package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzagj implements zzalf<Void> {
    private /* synthetic */ zzalt zza;

    zzagj(zzagf zzagfVar, zzalt zzaltVar) {
        this.zza = zzaltVar;
    }

    @Override // com.google.android.gms.internal.zzalf
    public final /* synthetic */ void zza(Void r2) {
        zzagf.zza.remove(this.zza);
    }

    @Override // com.google.android.gms.internal.zzalf
    public final void zza(Throwable th) {
        zzagf.zza.remove(this.zza);
    }
}
