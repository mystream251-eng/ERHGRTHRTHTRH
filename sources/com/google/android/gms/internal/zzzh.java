package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzzh implements Runnable {
    private /* synthetic */ zzzg zza;

    zzzh(zzzg zzzgVar) {
        this.zza = zzzgVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zzh.get()) {
            zzahw.zzc("Timed out waiting for WebView to finish loading.");
            this.zza.zzb();
        }
    }
}
