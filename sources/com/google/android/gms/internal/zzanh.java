package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzanh implements Runnable {
    private /* synthetic */ zzamy zza;

    zzanh(zzang zzangVar, zzamy zzamyVar) {
        this.zza = zzamyVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzg();
    }
}
