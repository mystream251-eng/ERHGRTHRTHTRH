package com.google.android.gms.tasks;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzn implements Runnable {
    private final /* synthetic */ Task zza;
    private final /* synthetic */ zzm zzb;

    zzn(zzm zzmVar, Task task) {
        this.zzb = zzmVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzb) {
            if (this.zzb.zzc != null) {
                this.zzb.zzc.onSuccess(this.zza.getResult());
            }
        }
    }
}
