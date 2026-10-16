package io.sentry.cache;

import io.sentry.SentryEnvelope;

/* JADX INFO: loaded from: classes2.dex */
public interface IEnvelopeCache extends Iterable<SentryEnvelope> {
    void discard(SentryEnvelope sentryEnvelope);

    void store(SentryEnvelope sentryEnvelope, Object obj);

    default void store(SentryEnvelope sentryEnvelope) {
        store(sentryEnvelope, null);
    }
}
