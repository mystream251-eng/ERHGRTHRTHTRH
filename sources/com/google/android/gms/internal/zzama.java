package com.google.android.gms.internal;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
final class zzama implements Executor {
    zzama() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
