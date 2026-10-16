package com.apollographql.apollo.internal.subscription;

import com.apollographql.apollo.api.Error;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.Subscription;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.internal.ResponseFieldMapperFactory;
import com.apollographql.apollo.response.OperationResponseParser;
import com.apollographql.apollo.subscription.OnSubscriptionManagerStateChangeListener;
import com.apollographql.apollo.subscription.OperationClientMessage;
import com.apollographql.apollo.subscription.OperationServerMessage;
import com.apollographql.apollo.subscription.SubscriptionConnectionParamsProvider;
import com.apollographql.apollo.subscription.SubscriptionManagerState;
import com.apollographql.apollo.subscription.SubscriptionTransport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes.dex */
public final class RealSubscriptionManager implements SubscriptionManager {
    static final int CONNECTION_ACKNOWLEDGE_TIMEOUT_TIMER_TASK_ID = 1;
    static final int CONNECTION_KEEP_ALIVE_TIMEOUT_TIMER_TASK_ID = 3;
    static final int INACTIVITY_TIMEOUT_TIMER_TASK_ID = 2;
    static final String PROTOCOL_NEGOTIATION_ERROR_NOT_FOUND = "PersistedQueryNotFound";
    static final String PROTOCOL_NEGOTIATION_ERROR_NOT_SUPPORTED = "PersistedQueryNotSupported";
    private final boolean autoPersistSubscription;
    private final long connectionHeartbeatTimeoutMs;
    private final SubscriptionConnectionParamsProvider connectionParams;
    private final Executor dispatcher;
    private final Function0<ResponseNormalizer<Map<String, Object>>> responseNormalizer;
    private final ScalarTypeAdapters scalarTypeAdapters;
    private final SubscriptionTransport transport;
    static final long CONNECTION_ACKNOWLEDGE_TIMEOUT = TimeUnit.SECONDS.toMillis(5);
    static final long INACTIVITY_TIMEOUT = TimeUnit.SECONDS.toMillis(10);
    Map<UUID, SubscriptionRecord> subscriptions = new LinkedHashMap();
    volatile SubscriptionManagerState state = SubscriptionManagerState.DISCONNECTED;
    final AutoReleaseTimer timer = new AutoReleaseTimer();
    private final ResponseFieldMapperFactory responseFieldMapperFactory = new ResponseFieldMapperFactory();
    private final Runnable connectionAcknowledgeTimeoutTimerTask = new Runnable() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.1
        @Override // java.lang.Runnable
        public void run() {
            RealSubscriptionManager.this.onConnectionAcknowledgeTimeout();
        }
    };
    private final Runnable inactivityTimeoutTimerTask = new Runnable() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.2
        @Override // java.lang.Runnable
        public void run() {
            RealSubscriptionManager.this.onInactivityTimeout();
        }
    };
    private final Runnable connectionHeartbeatTimeoutTimerTask = new Runnable() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.3
        @Override // java.lang.Runnable
        public void run() {
            RealSubscriptionManager.this.onConnectionHeartbeatTimeout();
        }
    };
    private final List<OnSubscriptionManagerStateChangeListener> onStateChangeListeners = new CopyOnWriteArrayList();

    public RealSubscriptionManager(ScalarTypeAdapters scalarTypeAdapters, SubscriptionTransport.Factory factory, SubscriptionConnectionParamsProvider subscriptionConnectionParamsProvider, Executor executor, long j, Function0<ResponseNormalizer<Map<String, Object>>> function0, boolean z) {
        Utils.checkNotNull(scalarTypeAdapters, "scalarTypeAdapters == null");
        Utils.checkNotNull(factory, "transportFactory == null");
        Utils.checkNotNull(executor, "dispatcher == null");
        Utils.checkNotNull(function0, "responseNormalizer == null");
        this.scalarTypeAdapters = (ScalarTypeAdapters) Utils.checkNotNull(scalarTypeAdapters, "scalarTypeAdapters == null");
        this.connectionParams = (SubscriptionConnectionParamsProvider) Utils.checkNotNull(subscriptionConnectionParamsProvider, "connectionParams == null");
        this.transport = factory.create(new SubscriptionTransportCallback(this, executor));
        this.dispatcher = executor;
        this.connectionHeartbeatTimeoutMs = j;
        this.responseNormalizer = function0;
        this.autoPersistSubscription = z;
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public <T> void subscribe(final Subscription<?, T, ?> subscription, final SubscriptionManager.Callback<T> callback) {
        Utils.checkNotNull(subscription, "subscription == null");
        Utils.checkNotNull(callback, "callback == null");
        this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.4
            @Override // java.lang.Runnable
            public void run() {
                RealSubscriptionManager.this.doSubscribe(subscription, callback);
            }
        });
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public void unsubscribe(final Subscription subscription) {
        Utils.checkNotNull(subscription, "subscription == null");
        this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.5
            @Override // java.lang.Runnable
            public void run() {
                RealSubscriptionManager.this.doUnsubscribe(subscription);
            }
        });
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public void start() {
        SubscriptionManagerState subscriptionManagerState;
        synchronized (this) {
            subscriptionManagerState = this.state;
            if (this.state == SubscriptionManagerState.STOPPED) {
                this.state = SubscriptionManagerState.DISCONNECTED;
            }
        }
        notifyStateChanged(subscriptionManagerState, this.state);
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public void stop() {
        this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.6
            @Override // java.lang.Runnable
            public void run() {
                RealSubscriptionManager.this.doStop();
            }
        });
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public SubscriptionManagerState getState() {
        return this.state;
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public void addOnStateChangeListener(OnSubscriptionManagerStateChangeListener onSubscriptionManagerStateChangeListener) {
        this.onStateChangeListeners.add((OnSubscriptionManagerStateChangeListener) Utils.checkNotNull(onSubscriptionManagerStateChangeListener, "onStateChangeListener == null"));
    }

    @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager
    public void removeOnStateChangeListener(OnSubscriptionManagerStateChangeListener onSubscriptionManagerStateChangeListener) {
        this.onStateChangeListeners.remove(Utils.checkNotNull(onSubscriptionManagerStateChangeListener, "onStateChangeListener == null"));
    }

    void doSubscribe(Subscription subscription, SubscriptionManager.Callback callback) {
        SubscriptionManagerState subscriptionManagerState;
        synchronized (this) {
            subscriptionManagerState = this.state;
            if (this.state != SubscriptionManagerState.STOPPING && this.state != SubscriptionManagerState.STOPPED) {
                this.timer.cancelTask(2);
                UUID uuidRandomUUID = UUID.randomUUID();
                this.subscriptions.put(uuidRandomUUID, new SubscriptionRecord(uuidRandomUUID, subscription, callback));
                if (this.state == SubscriptionManagerState.DISCONNECTED) {
                    this.state = SubscriptionManagerState.CONNECTING;
                    this.transport.connect();
                } else if (this.state == SubscriptionManagerState.ACTIVE) {
                    this.transport.send(new OperationClientMessage.Start(uuidRandomUUID.toString(), subscription, this.scalarTypeAdapters, this.autoPersistSubscription, false));
                }
            }
        }
        if (subscriptionManagerState == SubscriptionManagerState.STOPPING || subscriptionManagerState == SubscriptionManagerState.STOPPED) {
            callback.onError(new ApolloSubscriptionException("Illegal state: " + this.state.name() + " for subscriptions to be created. SubscriptionManager.start() must be called to re-enable subscriptions."));
        } else if (subscriptionManagerState == SubscriptionManagerState.CONNECTED) {
            callback.onConnected();
        }
        notifyStateChanged(subscriptionManagerState, this.state);
    }

    void doUnsubscribe(Subscription subscription) {
        synchronized (this) {
            SubscriptionRecord subscriptionRecord = null;
            for (SubscriptionRecord subscriptionRecord2 : this.subscriptions.values()) {
                if (subscriptionRecord2.subscription == subscription) {
                    subscriptionRecord = subscriptionRecord2;
                }
            }
            if (subscriptionRecord != null) {
                this.subscriptions.remove(subscriptionRecord.id);
                if (this.state == SubscriptionManagerState.ACTIVE || this.state == SubscriptionManagerState.STOPPING) {
                    this.transport.send(new OperationClientMessage.Stop(subscriptionRecord.id.toString()));
                }
            }
            if (this.subscriptions.isEmpty() && this.state != SubscriptionManagerState.STOPPING) {
                startInactivityTimer();
            }
        }
    }

    void doStop() {
        SubscriptionManagerState subscriptionManagerState;
        Collection<SubscriptionRecord> collectionValues;
        synchronized (this) {
            subscriptionManagerState = this.state;
            this.state = SubscriptionManagerState.STOPPING;
            collectionValues = this.subscriptions.values();
            if (subscriptionManagerState == SubscriptionManagerState.ACTIVE) {
                Iterator<SubscriptionRecord> it = collectionValues.iterator();
                while (it.hasNext()) {
                    this.transport.send(new OperationClientMessage.Stop(it.next().id.toString()));
                }
            }
            this.state = SubscriptionManagerState.STOPPED;
            this.transport.disconnect(new OperationClientMessage.Terminate());
            this.subscriptions = new LinkedHashMap();
        }
        Iterator<SubscriptionRecord> it2 = collectionValues.iterator();
        while (it2.hasNext()) {
            it2.next().notifyOnCompleted();
        }
        notifyStateChanged(subscriptionManagerState, SubscriptionManagerState.STOPPING);
        notifyStateChanged(SubscriptionManagerState.STOPPING, this.state);
    }

    void onTransportConnected() {
        SubscriptionManagerState subscriptionManagerState;
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            subscriptionManagerState = this.state;
            if (this.state == SubscriptionManagerState.CONNECTING) {
                arrayList.addAll(this.subscriptions.values());
                this.state = SubscriptionManagerState.CONNECTED;
                this.transport.send(new OperationClientMessage.Init(this.connectionParams.provide()));
            }
            if (this.state == SubscriptionManagerState.CONNECTED) {
                this.timer.schedule(1, this.connectionAcknowledgeTimeoutTimerTask, CONNECTION_ACKNOWLEDGE_TIMEOUT);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((SubscriptionRecord) it.next()).callback.onConnected();
        }
        notifyStateChanged(subscriptionManagerState, this.state);
    }

    void onConnectionAcknowledgeTimeout() {
        this.timer.cancelTask(1);
        this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.7
            @Override // java.lang.Runnable
            public void run() {
                RealSubscriptionManager.this.onTransportFailure(new ApolloNetworkException("Subscription server is not responding"));
            }
        });
    }

    void onInactivityTimeout() {
        this.timer.cancelTask(2);
        this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.8
            @Override // java.lang.Runnable
            public void run() {
                RealSubscriptionManager.this.disconnect(false);
            }
        });
    }

    void onTransportFailure(Throwable th) {
        Iterator<SubscriptionRecord> it = disconnect(true).iterator();
        while (it.hasNext()) {
            it.next().notifyOnNetworkError(th);
        }
    }

    void onOperationServerMessage(OperationServerMessage operationServerMessage) {
        if (operationServerMessage instanceof OperationServerMessage.ConnectionAcknowledge) {
            onConnectionAcknowledgeServerMessage();
            return;
        }
        if (operationServerMessage instanceof OperationServerMessage.Data) {
            onOperationDataServerMessage((OperationServerMessage.Data) operationServerMessage);
            return;
        }
        if (operationServerMessage instanceof OperationServerMessage.Error) {
            onErrorServerMessage((OperationServerMessage.Error) operationServerMessage);
            return;
        }
        if (operationServerMessage instanceof OperationServerMessage.Complete) {
            onCompleteServerMessage((OperationServerMessage.Complete) operationServerMessage);
        } else if (operationServerMessage instanceof OperationServerMessage.ConnectionError) {
            disconnect(true);
        } else if (operationServerMessage instanceof OperationServerMessage.ConnectionKeepAlive) {
            resetConnectionKeepAliveTimerTask();
        }
    }

    Collection<SubscriptionRecord> disconnect(boolean z) {
        SubscriptionManagerState subscriptionManagerState;
        Collection<SubscriptionRecord> collectionValues;
        synchronized (this) {
            subscriptionManagerState = this.state;
            collectionValues = this.subscriptions.values();
            if (z || this.subscriptions.isEmpty()) {
                this.transport.disconnect(new OperationClientMessage.Terminate());
                this.state = this.state == SubscriptionManagerState.STOPPING ? SubscriptionManagerState.STOPPED : SubscriptionManagerState.DISCONNECTED;
                this.subscriptions = new LinkedHashMap();
            }
        }
        notifyStateChanged(subscriptionManagerState, this.state);
        return collectionValues;
    }

    void onConnectionHeartbeatTimeout() {
        SubscriptionManagerState subscriptionManagerState;
        synchronized (this) {
            subscriptionManagerState = this.state;
            this.state = SubscriptionManagerState.DISCONNECTED;
            this.transport.disconnect(new OperationClientMessage.Terminate());
            this.state = SubscriptionManagerState.CONNECTING;
            this.transport.connect();
        }
        notifyStateChanged(subscriptionManagerState, SubscriptionManagerState.DISCONNECTED);
        notifyStateChanged(SubscriptionManagerState.DISCONNECTED, SubscriptionManagerState.CONNECTING);
    }

    void onConnectionClosed() {
        SubscriptionManagerState subscriptionManagerState;
        Collection<SubscriptionRecord> collectionValues;
        synchronized (this) {
            subscriptionManagerState = this.state;
            collectionValues = this.subscriptions.values();
            this.state = SubscriptionManagerState.DISCONNECTED;
            this.subscriptions = new LinkedHashMap();
        }
        Iterator<SubscriptionRecord> it = collectionValues.iterator();
        while (it.hasNext()) {
            it.next().callback.onTerminated();
        }
        notifyStateChanged(subscriptionManagerState, this.state);
    }

    private void resetConnectionKeepAliveTimerTask() {
        if (this.connectionHeartbeatTimeoutMs <= 0) {
            return;
        }
        synchronized (this) {
            this.timer.schedule(3, this.connectionHeartbeatTimeoutTimerTask, this.connectionHeartbeatTimeoutMs);
        }
    }

    private void startInactivityTimer() {
        this.timer.schedule(2, this.inactivityTimeoutTimerTask, INACTIVITY_TIMEOUT);
    }

    private void onOperationDataServerMessage(OperationServerMessage.Data data) {
        SubscriptionRecord subscriptionRecord;
        String str = data.id != null ? data.id : "";
        synchronized (this) {
            try {
                subscriptionRecord = this.subscriptions.get(UUID.fromString(str));
            } catch (IllegalArgumentException unused) {
                subscriptionRecord = null;
            }
        }
        if (subscriptionRecord != null) {
            ResponseNormalizer<Map<String, Object>> responseNormalizerInvoke = this.responseNormalizer.invoke();
            try {
                subscriptionRecord.notifyOnResponse(new OperationResponseParser(subscriptionRecord.subscription, this.responseFieldMapperFactory.create(subscriptionRecord.subscription), this.scalarTypeAdapters, responseNormalizerInvoke).parse(data.payload), responseNormalizerInvoke.records());
            } catch (Exception e) {
                SubscriptionRecord subscriptionRecordRemoveSubscriptionById = removeSubscriptionById(str);
                if (subscriptionRecordRemoveSubscriptionById != null) {
                    subscriptionRecordRemoveSubscriptionById.notifyOnError(new ApolloSubscriptionException("Failed to parse server message", e));
                }
            }
        }
    }

    private void onConnectionAcknowledgeServerMessage() {
        SubscriptionManagerState subscriptionManagerState;
        synchronized (this) {
            subscriptionManagerState = this.state;
            this.timer.cancelTask(1);
            if (this.state == SubscriptionManagerState.CONNECTED) {
                this.state = SubscriptionManagerState.ACTIVE;
                for (SubscriptionRecord subscriptionRecord : this.subscriptions.values()) {
                    this.transport.send(new OperationClientMessage.Start(subscriptionRecord.id.toString(), subscriptionRecord.subscription, this.scalarTypeAdapters, this.autoPersistSubscription, false));
                }
            }
        }
        notifyStateChanged(subscriptionManagerState, this.state);
    }

    private void onErrorServerMessage(OperationServerMessage.Error error) {
        SubscriptionRecord subscriptionRecordRemoveSubscriptionById = removeSubscriptionById(error.id != null ? error.id : "");
        if (subscriptionRecordRemoveSubscriptionById == null) {
            return;
        }
        boolean z = false;
        if (this.autoPersistSubscription) {
            Error error2 = OperationResponseParser.parseError(error.payload);
            if (PROTOCOL_NEGOTIATION_ERROR_NOT_FOUND.equalsIgnoreCase(error2.getMessage()) || PROTOCOL_NEGOTIATION_ERROR_NOT_SUPPORTED.equalsIgnoreCase(error2.getMessage())) {
                z = true;
            }
        }
        if (z) {
            synchronized (this) {
                this.subscriptions.put(subscriptionRecordRemoveSubscriptionById.id, subscriptionRecordRemoveSubscriptionById);
                this.transport.send(new OperationClientMessage.Start(subscriptionRecordRemoveSubscriptionById.id.toString(), subscriptionRecordRemoveSubscriptionById.subscription, this.scalarTypeAdapters, true, true));
            }
            return;
        }
        subscriptionRecordRemoveSubscriptionById.notifyOnError(new ApolloSubscriptionServerException(error.payload));
    }

    private void onCompleteServerMessage(OperationServerMessage.Complete complete) {
        SubscriptionRecord subscriptionRecordRemoveSubscriptionById = removeSubscriptionById(complete.id != null ? complete.id : "");
        if (subscriptionRecordRemoveSubscriptionById != null) {
            subscriptionRecordRemoveSubscriptionById.notifyOnCompleted();
        }
    }

    private SubscriptionRecord removeSubscriptionById(String str) {
        SubscriptionRecord subscriptionRecordRemove;
        synchronized (this) {
            try {
                subscriptionRecordRemove = this.subscriptions.remove(UUID.fromString(str));
            } catch (IllegalArgumentException unused) {
                subscriptionRecordRemove = null;
            }
            if (this.subscriptions.isEmpty()) {
                startInactivityTimer();
            }
        }
        return subscriptionRecordRemove;
    }

    private void notifyStateChanged(SubscriptionManagerState subscriptionManagerState, SubscriptionManagerState subscriptionManagerState2) {
        if (subscriptionManagerState == subscriptionManagerState2) {
            return;
        }
        Iterator<OnSubscriptionManagerStateChangeListener> it = this.onStateChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onStateChange(subscriptionManagerState, subscriptionManagerState2);
        }
    }

    private static class SubscriptionRecord {
        final SubscriptionManager.Callback<?> callback;
        final UUID id;
        final Subscription<?, ?, ?> subscription;

        SubscriptionRecord(UUID uuid, Subscription<?, ?, ?> subscription, SubscriptionManager.Callback<?> callback) {
            this.id = uuid;
            this.subscription = subscription;
            this.callback = callback;
        }

        void notifyOnResponse(Response response, Collection<Record> collection) {
            this.callback.onResponse(new SubscriptionResponse<>(this.subscription, response, collection));
        }

        void notifyOnError(ApolloSubscriptionException apolloSubscriptionException) {
            this.callback.onError(apolloSubscriptionException);
        }

        void notifyOnNetworkError(Throwable th) {
            this.callback.onNetworkError(th);
        }

        void notifyOnCompleted() {
            this.callback.onCompleted();
        }
    }

    private static final class SubscriptionTransportCallback implements SubscriptionTransport.Callback {
        private final RealSubscriptionManager delegate;
        private final Executor dispatcher;

        SubscriptionTransportCallback(RealSubscriptionManager realSubscriptionManager, Executor executor) {
            this.delegate = realSubscriptionManager;
            this.dispatcher = executor;
        }

        @Override // com.apollographql.apollo.subscription.SubscriptionTransport.Callback
        public void onConnected() {
            this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.SubscriptionTransportCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    SubscriptionTransportCallback.this.delegate.onTransportConnected();
                }
            });
        }

        @Override // com.apollographql.apollo.subscription.SubscriptionTransport.Callback
        public void onFailure(final Throwable th) {
            this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.SubscriptionTransportCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    SubscriptionTransportCallback.this.delegate.onTransportFailure(th);
                }
            });
        }

        @Override // com.apollographql.apollo.subscription.SubscriptionTransport.Callback
        public void onMessage(final OperationServerMessage operationServerMessage) {
            this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.SubscriptionTransportCallback.3
                @Override // java.lang.Runnable
                public void run() {
                    SubscriptionTransportCallback.this.delegate.onOperationServerMessage(operationServerMessage);
                }
            });
        }

        @Override // com.apollographql.apollo.subscription.SubscriptionTransport.Callback
        public void onClosed() {
            this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.SubscriptionTransportCallback.4
                @Override // java.lang.Runnable
                public void run() {
                    SubscriptionTransportCallback.this.delegate.onConnectionClosed();
                }
            });
        }
    }

    static final class AutoReleaseTimer {
        final Map<Integer, TimerTask> tasks = new LinkedHashMap();
        Timer timer;

        AutoReleaseTimer() {
        }

        void schedule(final int i, final Runnable runnable, long j) {
            TimerTask timerTask = new TimerTask() { // from class: com.apollographql.apollo.internal.subscription.RealSubscriptionManager.AutoReleaseTimer.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        AutoReleaseTimer.this.cancelTask(i);
                    }
                }
            };
            synchronized (this) {
                TimerTask timerTaskPut = this.tasks.put(Integer.valueOf(i), timerTask);
                if (timerTaskPut != null) {
                    timerTaskPut.cancel();
                }
                if (this.timer == null) {
                    this.timer = new Timer("Subscription SmartTimer", true);
                }
                this.timer.schedule(timerTask, j);
            }
        }

        void cancelTask(int i) {
            synchronized (this) {
                TimerTask timerTaskRemove = this.tasks.remove(Integer.valueOf(i));
                if (timerTaskRemove != null) {
                    timerTaskRemove.cancel();
                }
                if (this.tasks.isEmpty() && this.timer != null) {
                    this.timer.cancel();
                    this.timer = null;
                }
            }
        }
    }
}
