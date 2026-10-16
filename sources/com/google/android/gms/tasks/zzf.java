package com.google.android.gms.tasks;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzf implements Runnable {
    private final /* synthetic */ Task zza;
    private final /* synthetic */ zzd zzb;

    zzf(zzd zzdVar, Task task) {
        this.zzb = zzdVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Task task = (Task) this.zzb.zzb.then(this.zza);
            if (task == null) {
                this.zzb.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            task.addOnSuccessListener(TaskExecutors.zza, this.zzb);
            task.addOnFailureListener(TaskExecutors.zza, this.zzb);
            task.addOnCanceledListener(TaskExecutors.zza, this.zzb);
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
