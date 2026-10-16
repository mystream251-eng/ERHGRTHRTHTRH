package com.google.android.gms.internal;

import android.os.Process;
import com.google.android.gms.ads.internal.zzbt;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zzaig implements Runnable {
    private /* synthetic */ zzamd zza;
    private /* synthetic */ Callable zzb;

    zzaig(zzamd zzamdVar, Callable callable) {
        this.zza = zzamdVar;
        this.zzb = callable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Process.setThreadPriority(10);
            this.zza.zzb(this.zzb.call());
        } catch (Exception e) {
            zzbt.zzi().zza(e, "AdThreadPool.submit");
            this.zza.zza(e);
        }
    }
}
