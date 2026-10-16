package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzalu {
    private final Object zza = new Object();
    private final List<Runnable> zzb = new ArrayList();
    private boolean zzc = false;

    public final void zza() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.zza) {
            if (this.zzc) {
                return;
            }
            arrayList.addAll(this.zzb);
            this.zzb.clear();
            this.zzc = true;
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                ((Runnable) obj).run();
            }
        }
    }

    public final void zza(final Runnable runnable, final Executor executor) {
        synchronized (this.zza) {
            if (this.zzc) {
                executor.execute(runnable);
            } else {
                this.zzb.add(new Runnable(executor, runnable) { // from class: com.google.android.gms.internal.zzalv
                    private final Executor zza;
                    private final Runnable zzb;

                    {
                        this.zza = executor;
                        this.zzb = runnable;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zza.execute(this.zzb);
                    }
                });
            }
        }
    }
}
