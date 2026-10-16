package com.google.android.gms.ads.internal.js;

/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzr implements Runnable {
    private final zzc zza;

    private zzr(zzc zzcVar) {
        this.zza = zzcVar;
    }

    static Runnable zza(zzc zzcVar) {
        return new zzr(zzcVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza();
    }
}
