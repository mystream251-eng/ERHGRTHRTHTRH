package io.sentry;

import io.sentry.protocol.SentryTransaction;

/* JADX INFO: loaded from: classes2.dex */
public interface EventProcessor {
    default SentryEvent process(SentryEvent sentryEvent, Object obj) {
        return sentryEvent;
    }

    default SentryTransaction process(SentryTransaction sentryTransaction, Object obj) {
        return sentryTransaction;
    }
}
