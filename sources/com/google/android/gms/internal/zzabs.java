package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzabs implements Runnable {
    private /* synthetic */ zzabk zza;

    zzabs(zzabk zzabkVar) {
        this.zza = zzabkVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza.zzd) {
            if (this.zza.zza == null) {
                return;
            }
            this.zza.b_();
            this.zza.zza(2, "Timed out waiting for ad response.");
        }
    }
}
