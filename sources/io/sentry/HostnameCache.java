package io.sentry;

import io.sentry.util.Objects;
import java.net.InetAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
final class HostnameCache {
    private final long cacheDuration;
    private final ExecutorService executorService;
    private volatile long expirationTimestamp;
    private final Callable<InetAddress> getLocalhost;
    private volatile String hostname;
    private final AtomicBoolean updateRunning;
    private static final long HOSTNAME_CACHE_DURATION = TimeUnit.HOURS.toMillis(5);
    private static final long GET_HOSTNAME_TIMEOUT = TimeUnit.SECONDS.toMillis(1);

    public HostnameCache() {
        this(HOSTNAME_CACHE_DURATION);
    }

    HostnameCache(long j) {
        this(j, new Callable() { // from class: io.sentry.-$$Lambda$HostnameCache$0Rf2wMKlMTm1paSpRhBPXF4RBZk
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return InetAddress.getLocalHost();
            }
        });
    }

    HostnameCache(long j, Callable<InetAddress> callable) {
        this.updateRunning = new AtomicBoolean(false);
        this.executorService = Executors.newSingleThreadExecutor(new HostnameCacheThreadFactory());
        this.cacheDuration = j;
        this.getLocalhost = (Callable) Objects.requireNonNull(callable, "getLocalhost is required");
        updateCache();
    }

    void close() {
        this.executorService.shutdown();
    }

    boolean isClosed() {
        return this.executorService.isShutdown();
    }

    String getHostname() {
        if (this.expirationTimestamp < System.currentTimeMillis() && this.updateRunning.compareAndSet(false, true)) {
            updateCache();
        }
        return this.hostname;
    }

    private void updateCache() {
        try {
            this.executorService.submit(new Callable() { // from class: io.sentry.-$$Lambda$HostnameCache$6_ZMJnpaNsMFsRhx_jb3BDmP53E
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$updateCache$1$HostnameCache();
                }
            }).get(GET_HOSTNAME_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            handleCacheUpdateFailure();
        } catch (RuntimeException | ExecutionException | TimeoutException unused2) {
            handleCacheUpdateFailure();
        }
    }

    public /* synthetic */ Void lambda$updateCache$1$HostnameCache() throws Exception {
        try {
            this.hostname = this.getLocalhost.call().getCanonicalHostName();
            this.expirationTimestamp = System.currentTimeMillis() + this.cacheDuration;
            return null;
        } finally {
            this.updateRunning.set(false);
        }
    }

    private void handleCacheUpdateFailure() {
        this.expirationTimestamp = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(1L);
    }

    private static final class HostnameCacheThreadFactory implements ThreadFactory {
        private int cnt;

        private HostnameCacheThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder();
            sb.append("SentryHostnameCache-");
            int i = this.cnt;
            this.cnt = i + 1;
            sb.append(i);
            Thread thread = new Thread(runnable, sb.toString());
            thread.setDaemon(true);
            return thread;
        }
    }
}
