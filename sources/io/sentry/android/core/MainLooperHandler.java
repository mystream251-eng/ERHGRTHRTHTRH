package io.sentry.android.core;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes2.dex */
final class MainLooperHandler implements IHandler {
    private final Handler handler;

    MainLooperHandler() {
        this(Looper.getMainLooper());
    }

    MainLooperHandler(Looper looper) {
        this.handler = new Handler(looper);
    }

    @Override // io.sentry.android.core.IHandler
    public void post(Runnable runnable) {
        this.handler.post(runnable);
    }

    @Override // io.sentry.android.core.IHandler
    public Thread getThread() {
        return this.handler.getLooper().getThread();
    }
}
