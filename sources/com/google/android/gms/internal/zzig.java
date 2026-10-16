package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzig implements Runnable {
    private /* synthetic */ zzif zza;

    zzig(zzif zzifVar) {
        this.zza = zzifVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzc();
    }
}
