package com.apollographql.apollo.subscription;

import com.apollographql.apollo.api.internal.Utils;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;

/* JADX INFO: loaded from: classes.dex */
public final class WebSocketSubscriptionTransport implements SubscriptionTransport {
    private final SubscriptionTransport.Callback callback;
    private final WebSocket.Factory webSocketConnectionFactory;
    private final Request webSocketRequest;
    final AtomicReference<WebSocket> webSocket = new AtomicReference<>();
    final AtomicReference<WebSocketListener> webSocketListener = new AtomicReference<>();

    public WebSocketSubscriptionTransport(Request request, WebSocket.Factory factory, SubscriptionTransport.Callback callback) {
        this.webSocketRequest = request;
        this.webSocketConnectionFactory = factory;
        this.callback = callback;
    }

    @Override // com.apollographql.apollo.subscription.SubscriptionTransport
    public void connect() {
        WebSocketListener webSocketListener = new WebSocketListener(this);
        if (!this.webSocketListener.compareAndSet(null, webSocketListener)) {
            throw new IllegalStateException("Already connected");
        }
        this.webSocket.set(this.webSocketConnectionFactory.newWebSocket(this.webSocketRequest, webSocketListener));
    }

    @Override // com.apollographql.apollo.subscription.SubscriptionTransport
    public void disconnect(OperationClientMessage operationClientMessage) {
        WebSocket andSet = this.webSocket.getAndSet(null);
        if (andSet != null) {
            andSet.close(1001, operationClientMessage.toJsonString());
        }
        release();
    }

    @Override // com.apollographql.apollo.subscription.SubscriptionTransport
    public void send(OperationClientMessage operationClientMessage) {
        WebSocket webSocket = this.webSocket.get();
        if (webSocket == null) {
            this.callback.onFailure(new IllegalStateException("Send attempted on closed connection"));
        } else {
            webSocket.send(operationClientMessage.toJsonString());
        }
    }

    void onOpen() {
        this.callback.onConnected();
    }

    void onMessage(OperationServerMessage operationServerMessage) {
        this.callback.onMessage(operationServerMessage);
    }

    void onFailure(Throwable th) {
        try {
            this.callback.onFailure(th);
        } finally {
            release();
        }
    }

    void onClosed() {
        try {
            this.callback.onClosed();
        } finally {
            release();
        }
    }

    void release() {
        WebSocketListener andSet = this.webSocketListener.getAndSet(null);
        if (andSet != null) {
            andSet.release();
        }
        this.webSocket.set(null);
    }

    static final class WebSocketListener extends okhttp3.WebSocketListener {
        final WeakReference<WebSocketSubscriptionTransport> delegateRef;

        WebSocketListener(WebSocketSubscriptionTransport webSocketSubscriptionTransport) {
            this.delegateRef = new WeakReference<>(webSocketSubscriptionTransport);
        }

        @Override // okhttp3.WebSocketListener
        public void onOpen(WebSocket webSocket, Response response) {
            WebSocketSubscriptionTransport webSocketSubscriptionTransport = this.delegateRef.get();
            if (webSocketSubscriptionTransport != null) {
                webSocketSubscriptionTransport.onOpen();
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, String str) {
            WebSocketSubscriptionTransport webSocketSubscriptionTransport = this.delegateRef.get();
            if (webSocketSubscriptionTransport != null) {
                webSocketSubscriptionTransport.onMessage(OperationServerMessage.fromJsonString(str));
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            WebSocketSubscriptionTransport webSocketSubscriptionTransport = this.delegateRef.get();
            if (webSocketSubscriptionTransport != null) {
                webSocketSubscriptionTransport.onFailure(th);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onClosing(WebSocket webSocket, int i, String str) {
            WebSocketSubscriptionTransport webSocketSubscriptionTransport = this.delegateRef.get();
            if (webSocketSubscriptionTransport != null) {
                webSocketSubscriptionTransport.onClosed();
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onClosed(WebSocket webSocket, int i, String str) {
            WebSocketSubscriptionTransport webSocketSubscriptionTransport = this.delegateRef.get();
            if (webSocketSubscriptionTransport != null) {
                webSocketSubscriptionTransport.onClosed();
            }
        }

        void release() {
            this.delegateRef.clear();
        }
    }

    public static final class Factory implements SubscriptionTransport.Factory {
        private final WebSocket.Factory webSocketConnectionFactory;
        private final Request webSocketRequest;

        public Factory(String str, WebSocket.Factory factory) {
            this.webSocketRequest = new Request.Builder().url((String) Utils.checkNotNull(str, "webSocketUrl == null")).addHeader("Sec-WebSocket-Protocol", "graphql-ws").addHeader("Cookie", "").build();
            this.webSocketConnectionFactory = (WebSocket.Factory) Utils.checkNotNull(factory, "webSocketConnectionFactory == null");
        }

        @Override // com.apollographql.apollo.subscription.SubscriptionTransport.Factory
        public SubscriptionTransport create(SubscriptionTransport.Callback callback) {
            Utils.checkNotNull(callback, "callback == null");
            return new WebSocketSubscriptionTransport(this.webSocketRequest, this.webSocketConnectionFactory, callback);
        }
    }
}
