package io.sentry;

import io.sentry.transport.ITransport;
import io.sentry.transport.NoOpTransport;

/* JADX INFO: loaded from: classes2.dex */
public final class NoOpTransportFactory implements ITransportFactory {
    private static final NoOpTransportFactory instance = new NoOpTransportFactory();

    public static NoOpTransportFactory getInstance() {
        return instance;
    }

    private NoOpTransportFactory() {
    }

    @Override // io.sentry.ITransportFactory
    public ITransport create(SentryOptions sentryOptions, RequestDetails requestDetails) {
        return NoOpTransport.getInstance();
    }
}
