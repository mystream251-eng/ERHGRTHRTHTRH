package com.apollographql.apollo.subscription;

/* JADX INFO: loaded from: classes.dex */
public interface SubscriptionTransport {

    public interface Callback {
        void onClosed();

        void onConnected();

        void onFailure(Throwable th);

        void onMessage(OperationServerMessage operationServerMessage);
    }

    public interface Factory {
        SubscriptionTransport create(Callback callback);
    }

    void connect();

    void disconnect(OperationClientMessage operationClientMessage);

    void send(OperationClientMessage operationClientMessage);
}
