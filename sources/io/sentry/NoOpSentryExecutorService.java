package io.sentry;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes2.dex */
final class NoOpSentryExecutorService implements ISentryExecutorService {
    private static final NoOpSentryExecutorService instance = new NoOpSentryExecutorService();

    static /* synthetic */ Object lambda$submit$0() throws Exception {
        return null;
    }

    @Override // io.sentry.ISentryExecutorService
    public void close(long j) {
    }

    private NoOpSentryExecutorService() {
    }

    public static ISentryExecutorService getInstance() {
        return instance;
    }

    @Override // io.sentry.ISentryExecutorService
    public Future<?> submit(Runnable runnable) {
        return new FutureTask(new Callable() { // from class: io.sentry.-$$Lambda$NoOpSentryExecutorService$FQj0tU3z4jMeG2H7DH3W-LX5nSg
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return NoOpSentryExecutorService.lambda$submit$0();
            }
        });
    }
}
