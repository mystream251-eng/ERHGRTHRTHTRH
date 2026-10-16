package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzabe implements Runnable {
    private /* synthetic */ zzakz zza;
    private /* synthetic */ String zzb;

    zzabe(zzabb zzabbVar, zzakz zzakzVar, String str) {
        this.zza = zzakzVar;
        this.zzb = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(this.zzb);
    }
}
