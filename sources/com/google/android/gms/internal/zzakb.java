package com.google.android.gms.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.zzbq;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzakb {
    private HandlerThread zza = null;
    private Handler zzb = null;
    private int zzc = 0;
    private final Object zzd = new Object();

    public final Looper zza() {
        Looper looper;
        synchronized (this.zzd) {
            if (this.zzc != 0) {
                zzbq.zza(this.zza, "Invalid state: mHandlerThread should already been initialized.");
            } else if (this.zza == null) {
                zzahw.zza("Starting the looper thread.");
                HandlerThread handlerThread = new HandlerThread("LooperProvider");
                this.zza = handlerThread;
                handlerThread.start();
                this.zzb = new Handler(this.zza.getLooper());
                zzahw.zza("Looper thread started.");
            } else {
                zzahw.zza("Resuming the looper thread");
                this.zzd.notifyAll();
            }
            this.zzc++;
            looper = this.zza.getLooper();
        }
        return looper;
    }

    public final Handler zzb() {
        return this.zzb;
    }
}
