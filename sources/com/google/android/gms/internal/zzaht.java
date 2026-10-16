package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzaht implements Runnable {
    private /* synthetic */ zzahs zza;

    zzaht(zzahs zzahsVar) {
        this.zza = zzahsVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzb = Thread.currentThread();
        this.zza.zza();
    }
}
