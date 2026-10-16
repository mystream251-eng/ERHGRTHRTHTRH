package io.sentry;

import io.sentry.protocol.Message;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;

/* JADX INFO: loaded from: classes2.dex */
public interface ISentryClient {
    SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Object obj);

    SentryId captureEvent(SentryEvent sentryEvent, Scope scope, Object obj);

    void captureSession(Session session, Object obj);

    SentryId captureTransaction(SentryTransaction sentryTransaction, TraceState traceState, Scope scope, Object obj);

    void captureUserFeedback(UserFeedback userFeedback);

    void close();

    void flush(long j);

    boolean isEnabled();

    default SentryId captureEvent(SentryEvent sentryEvent) {
        return captureEvent(sentryEvent, null, null);
    }

    default SentryId captureEvent(SentryEvent sentryEvent, Scope scope) {
        return captureEvent(sentryEvent, scope, null);
    }

    default SentryId captureEvent(SentryEvent sentryEvent, Object obj) {
        return captureEvent(sentryEvent, null, obj);
    }

    default SentryId captureMessage(String str, SentryLevel sentryLevel, Scope scope) {
        SentryEvent sentryEvent = new SentryEvent();
        Message message = new Message();
        message.setFormatted(str);
        sentryEvent.setMessage(message);
        sentryEvent.setLevel(sentryLevel);
        return captureEvent(sentryEvent, scope);
    }

    default SentryId captureMessage(String str, SentryLevel sentryLevel) {
        return captureMessage(str, sentryLevel, null);
    }

    default SentryId captureException(Throwable th) {
        return captureException(th, null, null);
    }

    default SentryId captureException(Throwable th, Scope scope, Object obj) {
        return captureEvent(new SentryEvent(th), scope, obj);
    }

    default SentryId captureException(Throwable th, Object obj) {
        return captureException(th, null, obj);
    }

    default SentryId captureException(Throwable th, Scope scope) {
        return captureException(th, scope, null);
    }

    default void captureSession(Session session) {
        captureSession(session, null);
    }

    default SentryId captureEnvelope(SentryEnvelope sentryEnvelope) {
        return captureEnvelope(sentryEnvelope, null);
    }

    default SentryId captureTransaction(SentryTransaction sentryTransaction, Scope scope, Object obj) {
        return captureTransaction(sentryTransaction, null, scope, obj);
    }

    default SentryId captureTransaction(SentryTransaction sentryTransaction, TraceState traceState) {
        return captureTransaction(sentryTransaction, traceState, null, null);
    }

    default SentryId captureTransaction(SentryTransaction sentryTransaction) {
        return captureTransaction(sentryTransaction, null, null, null);
    }
}
