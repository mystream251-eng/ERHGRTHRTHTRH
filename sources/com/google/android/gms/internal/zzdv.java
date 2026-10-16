package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzdv implements Runnable {
    private /* synthetic */ zzdu zza;

    zzdv(zzdu zzduVar) {
        this.zza = zzduVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzc();
    }
}
