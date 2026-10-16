package com.google.android.gms.ads.internal.js;

/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzv implements Runnable {
    private final zzc zza;

    private zzv(zzc zzcVar) {
        this.zza = zzcVar;
    }

    static Runnable zza(zzc zzcVar) {
        return new zzv(zzcVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza();
    }
}
