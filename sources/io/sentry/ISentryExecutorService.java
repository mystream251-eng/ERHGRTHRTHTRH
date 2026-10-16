package io.sentry;

import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
interface ISentryExecutorService {
    void close(long j);

    Future<?> submit(Runnable runnable);
}
