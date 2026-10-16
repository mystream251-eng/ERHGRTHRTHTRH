package io.sentry.transport;

import io.sentry.SentryEnvelope;
import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface ITransport extends Closeable {
    void flush(long j);

    void send(SentryEnvelope sentryEnvelope, Object obj) throws IOException;

    default void send(SentryEnvelope sentryEnvelope) throws IOException {
        send(sentryEnvelope, null);
    }
}
