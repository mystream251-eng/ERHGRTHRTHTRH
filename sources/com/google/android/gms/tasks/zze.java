package com.google.android.gms.tasks;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zze implements Runnable {
    private final /* synthetic */ Task zza;
    private final /* synthetic */ zzc zzb;

    zze(zzc zzcVar, Task task) {
        this.zzb = zzcVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.isCanceled()) {
            this.zzb.zzc.zza();
            return;
        }
        try {
            this.zzb.zzc.zza(this.zzb.zzb.then(this.zza));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzb.zzc.zza((Exception) e.getCause());
            } else {
                this.zzb.zzc.zza((Exception) e);
            }
        } catch (Exception e2) {
            this.zzb.zzc.zza(e2);
        }
    }
}
