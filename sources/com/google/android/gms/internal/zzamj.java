package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzamj<T> implements zzamf<T> {
    private final Object zza = new Object();
    private int zzb = 0;
    private BlockingQueue<zzamk> zzc = new LinkedBlockingQueue();
    private T zzd;

    @Override // com.google.android.gms.internal.zzamf
    public final void zza(zzami<T> zzamiVar, zzamg zzamgVar) {
        synchronized (this.zza) {
            if (this.zzb == 1) {
                zzamiVar.zza(this.zzd);
            } else if (this.zzb == -1) {
                zzamgVar.zza();
            } else if (this.zzb == 0) {
                this.zzc.add(new zzamk(this, zzamiVar, zzamgVar));
            }
        }
    }

    @Override // com.google.android.gms.internal.zzamf
    public final void zza(T t) {
        synchronized (this.zza) {
            if (this.zzb != 0) {
                throw new UnsupportedOperationException();
            }
            this.zzd = t;
            this.zzb = 1;
            Iterator it = this.zzc.iterator();
            while (it.hasNext()) {
                ((zzamk) it.next()).zza.zza(t);
            }
            this.zzc.clear();
        }
    }

    public final void zzd() {
        synchronized (this.zza) {
            if (this.zzb != 0) {
                throw new UnsupportedOperationException();
            }
            this.zzb = -1;
            Iterator it = this.zzc.iterator();
            while (it.hasNext()) {
                ((zzamk) it.next()).zzb.zza();
            }
            this.zzc.clear();
        }
    }

    public final int zze() {
        return this.zzb;
    }
}
