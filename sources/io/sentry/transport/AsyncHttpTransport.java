package io.sentry.transport;

import io.sentry.ILogger;
import io.sentry.RequestDetails;
import io.sentry.SentryEnvelope;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.cache.IEnvelopeCache;
import io.sentry.hints.Cached;
import io.sentry.hints.DiskFlushNotification;
import io.sentry.hints.Retryable;
import io.sentry.hints.SubmissionResult;
import io.sentry.util.LogUtils;
import io.sentry.util.Objects;
import java.io.IOException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class AsyncHttpTransport implements ITransport {
    private final HttpConnection connection;
    private final IEnvelopeCache envelopeCache;
    private final QueuedThreadPoolExecutor executor;
    private final SentryOptions options;
    private final RateLimiter rateLimiter;
    private final ITransportGate transportGate;

    public AsyncHttpTransport(SentryOptions sentryOptions, RateLimiter rateLimiter, ITransportGate iTransportGate, RequestDetails requestDetails) {
        this(initExecutor(sentryOptions.getMaxQueueSize(), sentryOptions.getEnvelopeDiskCache(), sentryOptions.getLogger()), sentryOptions, rateLimiter, iTransportGate, new HttpConnection(sentryOptions, requestDetails, rateLimiter));
    }

    public AsyncHttpTransport(QueuedThreadPoolExecutor queuedThreadPoolExecutor, SentryOptions sentryOptions, RateLimiter rateLimiter, ITransportGate iTransportGate, HttpConnection httpConnection) {
        this.executor = (QueuedThreadPoolExecutor) Objects.requireNonNull(queuedThreadPoolExecutor, "executor is required");
        this.envelopeCache = (IEnvelopeCache) Objects.requireNonNull(sentryOptions.getEnvelopeDiskCache(), "envelopeCache is required");
        this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "options is required");
        this.rateLimiter = (RateLimiter) Objects.requireNonNull(rateLimiter, "rateLimiter is required");
        this.transportGate = (ITransportGate) Objects.requireNonNull(iTransportGate, "transportGate is required");
        this.connection = (HttpConnection) Objects.requireNonNull(httpConnection, "httpConnection is required");
    }

    @Override // io.sentry.transport.ITransport
    public void send(SentryEnvelope sentryEnvelope, Object obj) throws IOException {
        IEnvelopeCache noOpEnvelopeCache = this.envelopeCache;
        boolean z = false;
        if (obj instanceof Cached) {
            noOpEnvelopeCache = NoOpEnvelopeCache.getInstance();
            this.options.getLogger().log(SentryLevel.DEBUG, "Captured Envelope is already cached", new Object[0]);
            z = true;
        }
        SentryEnvelope sentryEnvelopeFilter = this.rateLimiter.filter(sentryEnvelope, obj);
        if (sentryEnvelopeFilter != null) {
            this.executor.submit(new EnvelopeSender(sentryEnvelopeFilter, obj, noOpEnvelopeCache));
        } else if (z) {
            this.envelopeCache.discard(sentryEnvelope);
        }
    }

    @Override // io.sentry.transport.ITransport
    public void flush(long j) {
        this.executor.waitTillIdle(j);
    }

    private static QueuedThreadPoolExecutor initExecutor(int i, final IEnvelopeCache iEnvelopeCache, final ILogger iLogger) {
        return new QueuedThreadPoolExecutor(1, i, new AsyncConnectionThreadFactory(), new RejectedExecutionHandler() { // from class: io.sentry.transport.-$$Lambda$AsyncHttpTransport$6Q77f17yLf-qOKI5FV6AeklhwZQ
            @Override // java.util.concurrent.RejectedExecutionHandler
            public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                AsyncHttpTransport.lambda$initExecutor$0(iEnvelopeCache, iLogger, runnable, threadPoolExecutor);
            }
        }, iLogger);
    }

    static /* synthetic */ void lambda$initExecutor$0(IEnvelopeCache iEnvelopeCache, ILogger iLogger, Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        if (runnable instanceof EnvelopeSender) {
            EnvelopeSender envelopeSender = (EnvelopeSender) runnable;
            if (!(envelopeSender.hint instanceof Cached)) {
                iEnvelopeCache.store(envelopeSender.envelope, envelopeSender.hint);
            }
            markHintWhenSendingFailed(envelopeSender.hint, true);
            iLogger.log(SentryLevel.WARNING, "Envelope rejected", new Object[0]);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.executor.shutdown();
        this.options.getLogger().log(SentryLevel.DEBUG, "Shutting down", new Object[0]);
        try {
            if (this.executor.awaitTermination(1L, TimeUnit.MINUTES)) {
                return;
            }
            this.options.getLogger().log(SentryLevel.WARNING, "Failed to shutdown the async connection async sender within 1 minute. Trying to force it now.", new Object[0]);
            this.executor.shutdownNow();
        } catch (InterruptedException unused) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Thread interrupted while closing the connection.", new Object[0]);
            Thread.currentThread().interrupt();
        }
    }

    private static void markHintWhenSendingFailed(Object obj, boolean z) {
        if (obj instanceof SubmissionResult) {
            ((SubmissionResult) obj).setResult(false);
        }
        if (obj instanceof Retryable) {
            ((Retryable) obj).setRetry(z);
        }
    }

    private static final class AsyncConnectionThreadFactory implements ThreadFactory {
        private int cnt;

        private AsyncConnectionThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder();
            sb.append("SentryAsyncConnection-");
            int i = this.cnt;
            this.cnt = i + 1;
            sb.append(i);
            Thread thread = new Thread(runnable, sb.toString());
            thread.setDaemon(true);
            return thread;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class EnvelopeSender implements Runnable {
        private final SentryEnvelope envelope;
        private final IEnvelopeCache envelopeCache;
        private final TransportResult failedResult = TransportResult.error();
        private final Object hint;

        EnvelopeSender(SentryEnvelope sentryEnvelope, Object obj, IEnvelopeCache iEnvelopeCache) {
            this.envelope = (SentryEnvelope) Objects.requireNonNull(sentryEnvelope, "Envelope is required.");
            this.hint = obj;
            this.envelopeCache = (IEnvelopeCache) Objects.requireNonNull(iEnvelopeCache, "EnvelopeCache is required.");
        }

        @Override // java.lang.Runnable
        public void run() {
            TransportResult transportResultFlush = this.failedResult;
            try {
                transportResultFlush = flush();
                AsyncHttpTransport.this.options.getLogger().log(SentryLevel.DEBUG, "Envelope flushed", new Object[0]);
                if (this.hint instanceof SubmissionResult) {
                    AsyncHttpTransport.this.options.getLogger().log(SentryLevel.DEBUG, "Marking envelope submission result: %s", Boolean.valueOf(transportResultFlush.isSuccess()));
                    ((SubmissionResult) this.hint).setResult(transportResultFlush.isSuccess());
                }
            } catch (Throwable th) {
                try {
                    AsyncHttpTransport.this.options.getLogger().log(SentryLevel.ERROR, th, "Envelope submission failed", new Object[0]);
                    throw th;
                } catch (Throwable th2) {
                    if (this.hint instanceof SubmissionResult) {
                        AsyncHttpTransport.this.options.getLogger().log(SentryLevel.DEBUG, "Marking envelope submission result: %s", Boolean.valueOf(transportResultFlush.isSuccess()));
                        ((SubmissionResult) this.hint).setResult(transportResultFlush.isSuccess());
                    }
                    throw th2;
                }
            }
        }

        private TransportResult flush() {
            TransportResult transportResultSend = this.failedResult;
            this.envelopeCache.store(this.envelope, this.hint);
            Object obj = this.hint;
            if (obj instanceof DiskFlushNotification) {
                ((DiskFlushNotification) obj).markFlushed();
                AsyncHttpTransport.this.options.getLogger().log(SentryLevel.DEBUG, "Disk flush envelope fired", new Object[0]);
            }
            if (AsyncHttpTransport.this.transportGate.isConnected()) {
                try {
                    transportResultSend = AsyncHttpTransport.this.connection.send(this.envelope);
                    if (transportResultSend.isSuccess()) {
                        this.envelopeCache.discard(this.envelope);
                    } else {
                        String str = "The transport failed to send the envelope with response code " + transportResultSend.getResponseCode();
                        AsyncHttpTransport.this.options.getLogger().log(SentryLevel.ERROR, str, new Object[0]);
                        throw new IllegalStateException(str);
                    }
                } catch (IOException e) {
                    Object obj2 = this.hint;
                    if (!(obj2 instanceof Retryable)) {
                        LogUtils.logIfNotRetryable(AsyncHttpTransport.this.options.getLogger(), this.hint);
                    } else {
                        ((Retryable) obj2).setRetry(true);
                    }
                    throw new IllegalStateException("Sending the event failed.", e);
                }
            } else {
                Object obj3 = this.hint;
                if (!(obj3 instanceof Retryable)) {
                    LogUtils.logIfNotRetryable(AsyncHttpTransport.this.options.getLogger(), this.hint);
                } else {
                    ((Retryable) obj3).setRetry(true);
                }
            }
            return transportResultSend;
        }
    }
}
