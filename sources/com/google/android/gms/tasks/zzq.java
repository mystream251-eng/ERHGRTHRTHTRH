package com.google.android.gms.tasks;

import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzq<TResult> {
    private final Object zza = new Object();
    private Queue<zzr<TResult>> zzb;
    private boolean zzc;

    zzq() {
    }

    public final void zza(zzr<TResult> zzrVar) {
        synchronized (this.zza) {
            if (this.zzb == null) {
                this.zzb = new ArrayDeque();
            }
            this.zzb.add(zzrVar);
        }
    }

    public final void zza(Task<TResult> task) {
        zzr<TResult> zzrVarPoll;
        synchronized (this.zza) {
            if (this.zzb != null && !this.zzc) {
                this.zzc = true;
                while (true) {
                    synchronized (this.zza) {
                        zzrVarPoll = this.zzb.poll();
                        if (zzrVarPoll == null) {
                            this.zzc = false;
                            return;
                        }
                    }
                    zzrVarPoll.zza(task);
                }
            }
        }
    }
}
