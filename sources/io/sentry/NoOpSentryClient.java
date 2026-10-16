package io.sentry;

import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;

/* JADX INFO: loaded from: classes2.dex */
final class NoOpSentryClient implements ISentryClient {
    private static final NoOpSentryClient instance = new NoOpSentryClient();

    @Override // io.sentry.ISentryClient
    public void captureSession(Session session, Object obj) {
    }

    @Override // io.sentry.ISentryClient
    public void captureUserFeedback(UserFeedback userFeedback) {
    }

    @Override // io.sentry.ISentryClient
    public void close() {
    }

    @Override // io.sentry.ISentryClient
    public void flush(long j) {
    }

    @Override // io.sentry.ISentryClient
    public boolean isEnabled() {
        return false;
    }

    private NoOpSentryClient() {
    }

    public static NoOpSentryClient getInstance() {
        return instance;
    }

    @Override // io.sentry.ISentryClient
    public SentryId captureEvent(SentryEvent sentryEvent, Scope scope, Object obj) {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.ISentryClient
    public SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Object obj) {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.ISentryClient
    public SentryId captureTransaction(SentryTransaction sentryTransaction, TraceState traceState, Scope scope, Object obj) {
        return SentryId.EMPTY_ID;
    }
}
