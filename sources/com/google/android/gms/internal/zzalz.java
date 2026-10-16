package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
final class zzalz implements Executor {
    private final Handler zza = new Handler(Looper.getMainLooper());

    zzalz() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            this.zza.post(runnable);
        }
    }
}
