package io.sentry;

import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class ShutdownHookIntegration implements Integration, Closeable {
    private final Runtime runtime;
    private Thread thread;

    public ShutdownHookIntegration(Runtime runtime) {
        this.runtime = (Runtime) Objects.requireNonNull(runtime, "Runtime is required");
    }

    public ShutdownHookIntegration() {
        this(Runtime.getRuntime());
    }

    @Override // io.sentry.Integration
    public void register(final IHub iHub, final SentryOptions sentryOptions) {
        Objects.requireNonNull(iHub, "Hub is required");
        Objects.requireNonNull(sentryOptions, "SentryOptions is required");
        if (sentryOptions.isEnableShutdownHook()) {
            Thread thread = new Thread(new Runnable() { // from class: io.sentry.-$$Lambda$ShutdownHookIntegration$rAAwevLH0nb6Wau1Wt9dg8tjAmM
                @Override // java.lang.Runnable
                public final void run() {
                    iHub.flush(sentryOptions.getFlushTimeoutMillis());
                }
            });
            this.thread = thread;
            this.runtime.addShutdownHook(thread);
            sentryOptions.getLogger().log(SentryLevel.DEBUG, "ShutdownHookIntegration installed.", new Object[0]);
            return;
        }
        sentryOptions.getLogger().log(SentryLevel.INFO, "enableShutdownHook is disabled.", new Object[0]);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Thread thread = this.thread;
        if (thread != null) {
            this.runtime.removeShutdownHook(thread);
        }
    }

    Thread getHook() {
        return this.thread;
    }
}
