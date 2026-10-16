package com.google.android.gms.ads.internal.js;

/* JADX INFO: loaded from: classes2.dex */
final class zzj implements Runnable {
    private /* synthetic */ String zza;
    private /* synthetic */ zze zzb;

    zzj(zze zzeVar, String str) {
        this.zzb = zzeVar;
        this.zza = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.loadUrl(this.zza);
    }
}
