package com.apollographql.apollo.internal;

import com.apollographql.apollo.ApolloSubscriptionCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.Subscription;
import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.internal.Transaction;
import com.apollographql.apollo.cache.normalized.internal.WriteableStore;
import com.apollographql.apollo.exception.ApolloCanceledException;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.internal.subscription.ApolloSubscriptionException;
import com.apollographql.apollo.internal.subscription.SubscriptionManager;
import com.apollographql.apollo.internal.subscription.SubscriptionResponse;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class RealApolloSubscriptionCall<T> implements ApolloSubscriptionCall<T> {
    private final ApolloStore apolloStore;
    private final ApolloSubscriptionCall.CachePolicy cachePolicy;
    private final Executor dispatcher;
    private final ApolloLogger logger;
    private final ResponseFieldMapperFactory responseFieldMapperFactory;
    private final AtomicReference<CallState> state = new AtomicReference<>(CallState.IDLE);
    private final Subscription<?, T, ?> subscription;
    private SubscriptionManagerCallback<T> subscriptionCallback;
    private final SubscriptionManager subscriptionManager;

    public RealApolloSubscriptionCall(Subscription<?, T, ?> subscription, SubscriptionManager subscriptionManager, ApolloStore apolloStore, ApolloSubscriptionCall.CachePolicy cachePolicy, Executor executor, ResponseFieldMapperFactory responseFieldMapperFactory, ApolloLogger apolloLogger) {
        this.subscription = subscription;
        this.subscriptionManager = subscriptionManager;
        this.apolloStore = apolloStore;
        this.cachePolicy = cachePolicy;
        this.dispatcher = executor;
        this.responseFieldMapperFactory = responseFieldMapperFactory;
        this.logger = apolloLogger;
    }

    @Override // com.apollographql.apollo.ApolloSubscriptionCall
    public void execute(final ApolloSubscriptionCall.Callback<T> callback) throws ApolloCanceledException {
        Utils.checkNotNull(callback, "callback == null");
        synchronized (this) {
            int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    throw new ApolloCanceledException();
                }
                if (i == 3 || i == 4) {
                    throw new IllegalStateException("Already Executed");
                }
                throw new IllegalStateException("Unknown state");
            }
            this.state.set(CallState.ACTIVE);
            if (this.cachePolicy == ApolloSubscriptionCall.CachePolicy.CACHE_AND_NETWORK) {
                this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.RealApolloSubscriptionCall.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Response<T> responseResolveFromCache = RealApolloSubscriptionCall.this.resolveFromCache();
                        if (responseResolveFromCache != null) {
                            callback.onResponse(responseResolveFromCache);
                        }
                    }
                });
            }
            SubscriptionManagerCallback<T> subscriptionManagerCallback = new SubscriptionManagerCallback<>(callback, this);
            this.subscriptionCallback = subscriptionManagerCallback;
            this.subscriptionManager.subscribe(this.subscription, subscriptionManagerCallback);
        }
    }

    /* JADX INFO: renamed from: com.apollographql.apollo.internal.RealApolloSubscriptionCall$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$apollographql$apollo$internal$CallState;

        static {
            int[] iArr = new int[CallState.values().length];
            $SwitchMap$com$apollographql$apollo$internal$CallState = iArr;
            try {
                iArr[CallState.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$internal$CallState[CallState.CANCELED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$internal$CallState[CallState.TERMINATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$internal$CallState[CallState.ACTIVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.apollographql.apollo.internal.util.Cancelable
    public void cancel() {
        synchronized (this) {
            int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
            if (i == 1) {
                this.state.set(CallState.CANCELED);
            } else if (i != 2 && i != 3) {
                if (i == 4) {
                    try {
                        this.subscriptionManager.unsubscribe(this.subscription);
                        this.state.set(CallState.CANCELED);
                        this.subscriptionCallback.release();
                    } catch (Throwable th) {
                        this.state.set(CallState.CANCELED);
                        this.subscriptionCallback.release();
                        throw th;
                    }
                } else {
                    throw new IllegalStateException("Unknown state");
                }
            }
        }
    }

    @Override // com.apollographql.apollo.ApolloSubscriptionCall
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ApolloSubscriptionCall<T> m9clone() {
        return new RealApolloSubscriptionCall(this.subscription, this.subscriptionManager, this.apolloStore, this.cachePolicy, this.dispatcher, this.responseFieldMapperFactory, this.logger);
    }

    @Override // com.apollographql.apollo.internal.util.Cancelable
    public boolean isCanceled() {
        return this.state.get() == CallState.CANCELED;
    }

    @Override // com.apollographql.apollo.ApolloSubscriptionCall
    public ApolloSubscriptionCall<T> cachePolicy(ApolloSubscriptionCall.CachePolicy cachePolicy) {
        Utils.checkNotNull(cachePolicy, "cachePolicy is null");
        return new RealApolloSubscriptionCall(this.subscription, this.subscriptionManager, this.apolloStore, cachePolicy, this.dispatcher, this.responseFieldMapperFactory, this.logger);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void terminate() {
        synchronized (this) {
            int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            this.state.set(CallState.TERMINATED);
                            this.subscriptionCallback.release();
                        } else {
                            throw new IllegalStateException("Unknown state");
                        }
                    }
                }
            }
            throw new IllegalStateException(CallState.IllegalStateMessage.forCurrentState(this.state.get()).expected(CallState.ACTIVE, CallState.CANCELED));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Response<T> resolveFromCache() {
        Response<T> responseExecute;
        try {
            responseExecute = this.apolloStore.read(this.subscription, this.responseFieldMapperFactory.create(this.subscription), this.apolloStore.cacheResponseNormalizer(), CacheHeaders.NONE).execute();
        } catch (Exception e) {
            this.logger.e(e, "Failed to fetch subscription `%s` from the store", this.subscription);
            responseExecute = null;
        }
        if (responseExecute != null && responseExecute.getData() != null) {
            this.logger.d("Cache HIT for subscription `%s`", this.subscription);
            return responseExecute;
        }
        this.logger.d("Cache MISS for subscription `%s`", this.subscription);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheResponse(final SubscriptionResponse<T> subscriptionResponse) {
        if (subscriptionResponse.cacheRecords.isEmpty() || this.cachePolicy == ApolloSubscriptionCall.CachePolicy.NO_CACHE) {
            return;
        }
        this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.RealApolloSubscriptionCall.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    try {
                        RealApolloSubscriptionCall.this.apolloStore.publish((Set) RealApolloSubscriptionCall.this.apolloStore.writeTransaction(new Transaction<WriteableStore, Set<String>>() { // from class: com.apollographql.apollo.internal.RealApolloSubscriptionCall.2.1
                            @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
                            public Set<String> execute(WriteableStore writeableStore) {
                                return writeableStore.merge(subscriptionResponse.cacheRecords, CacheHeaders.NONE);
                            }
                        }));
                    } catch (Exception e) {
                        RealApolloSubscriptionCall.this.logger.e(e, "Failed to publish cache changes for subscription `%s`", RealApolloSubscriptionCall.this.subscription);
                    }
                } catch (Exception e2) {
                    RealApolloSubscriptionCall.this.logger.e(e2, "Failed to cache response for subscription `%s`", RealApolloSubscriptionCall.this.subscription);
                }
            }
        });
    }

    private static final class SubscriptionManagerCallback<T> implements SubscriptionManager.Callback<T> {
        private RealApolloSubscriptionCall<T> delegate;
        private ApolloSubscriptionCall.Callback<T> originalCallback;

        SubscriptionManagerCallback(ApolloSubscriptionCall.Callback<T> callback, RealApolloSubscriptionCall<T> realApolloSubscriptionCall) {
            this.originalCallback = callback;
            this.delegate = realApolloSubscriptionCall;
        }

        @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager.Callback
        public void onResponse(SubscriptionResponse<T> subscriptionResponse) {
            ApolloSubscriptionCall.Callback<T> callback = this.originalCallback;
            if (callback != null) {
                this.delegate.cacheResponse(subscriptionResponse);
                callback.onResponse(subscriptionResponse.response);
            }
        }

        @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager.Callback
        public void onError(ApolloSubscriptionException apolloSubscriptionException) {
            ApolloSubscriptionCall.Callback<T> callback = this.originalCallback;
            if (callback != null) {
                callback.onFailure(apolloSubscriptionException);
            }
            terminate();
        }

        @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager.Callback
        public void onNetworkError(Throwable th) {
            ApolloSubscriptionCall.Callback<T> callback = this.originalCallback;
            if (callback != null) {
                callback.onFailure(new ApolloNetworkException("Subscription failed", th));
            }
            terminate();
        }

        @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager.Callback
        public void onCompleted() {
            ApolloSubscriptionCall.Callback<T> callback = this.originalCallback;
            if (callback != null) {
                callback.onCompleted();
            }
            terminate();
        }

        @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager.Callback
        public void onTerminated() {
            ApolloSubscriptionCall.Callback<T> callback = this.originalCallback;
            if (callback != null) {
                callback.onTerminated();
            }
            terminate();
        }

        @Override // com.apollographql.apollo.internal.subscription.SubscriptionManager.Callback
        public void onConnected() {
            ApolloSubscriptionCall.Callback<T> callback = this.originalCallback;
            if (callback != null) {
                callback.onConnected();
            }
        }

        void terminate() {
            RealApolloSubscriptionCall<T> realApolloSubscriptionCall = this.delegate;
            if (realApolloSubscriptionCall != null) {
                realApolloSubscriptionCall.terminate();
            }
        }

        void release() {
            this.originalCallback = null;
            this.delegate = null;
        }
    }
}
