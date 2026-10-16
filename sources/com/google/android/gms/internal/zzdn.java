package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzdn implements Runnable {
    private /* synthetic */ zzdm zza;

    zzdn(zzdm zzdmVar) {
        this.zza = zzdmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzo();
    }
}
