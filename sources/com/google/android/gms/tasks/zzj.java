package com.google.android.gms.tasks;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzj implements Runnable {
    private final /* synthetic */ Task zza;
    private final /* synthetic */ zzi zzb;

    zzj(zzi zziVar, Task task) {
        this.zzb = zziVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzb) {
            if (this.zzb.zzc != null) {
                this.zzb.zzc.onComplete(this.zza);
            }
        }
    }
}
