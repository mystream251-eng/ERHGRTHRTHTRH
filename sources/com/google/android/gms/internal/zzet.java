package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzet implements Runnable {
    private /* synthetic */ zzes zza;

    zzet(zzes zzesVar) {
        this.zza = zzesVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzb();
    }
}
