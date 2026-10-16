package io.sentry.android.core;

/* JADX INFO: loaded from: classes2.dex */
interface IHandler {
    Thread getThread();

    void post(Runnable runnable);
}
