package io.sentry.transport;

import io.sentry.SentryEnvelope;
import io.sentry.cache.IEnvelopeCache;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public final class NoOpEnvelopeCache implements IEnvelopeCache {
    private static final NoOpEnvelopeCache instance = new NoOpEnvelopeCache();

    @Override // io.sentry.cache.IEnvelopeCache
    public void discard(SentryEnvelope sentryEnvelope) {
    }

    @Override // io.sentry.cache.IEnvelopeCache
    public void store(SentryEnvelope sentryEnvelope, Object obj) {
    }

    public static NoOpEnvelopeCache getInstance() {
        return instance;
    }

    @Override // java.lang.Iterable
    public Iterator<SentryEnvelope> iterator() {
        return new ArrayList(0).iterator();
    }
}
