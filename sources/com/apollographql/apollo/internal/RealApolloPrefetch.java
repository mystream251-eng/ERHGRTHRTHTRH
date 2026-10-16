package com.apollographql.apollo.internal;

import com.apollographql.apollo.ApolloPrefetch;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.cache.http.HttpCachePolicy;
import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.exception.ApolloCanceledException;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.exception.ApolloHttpException;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import com.apollographql.apollo.internal.interceptor.ApolloServerInterceptor;
import com.apollographql.apollo.internal.interceptor.RealApolloInterceptorChain;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public final class RealApolloPrefetch implements ApolloPrefetch {
    final Executor dispatcher;
    final Call.Factory httpCallFactory;
    final ApolloInterceptorChain interceptorChain;
    final ApolloLogger logger;
    final Operation operation;
    final ScalarTypeAdapters scalarTypeAdapters;
    final HttpUrl serverUrl;
    final ApolloCallTracker tracker;
    final AtomicReference<CallState> state = new AtomicReference<>(CallState.IDLE);
    final AtomicReference<ApolloPrefetch.Callback> originalCallback = new AtomicReference<>();

    public RealApolloPrefetch(Operation operation, HttpUrl httpUrl, Call.Factory factory, ScalarTypeAdapters scalarTypeAdapters, Executor executor, ApolloLogger apolloLogger, ApolloCallTracker apolloCallTracker) {
        this.operation = operation;
        this.serverUrl = httpUrl;
        this.httpCallFactory = factory;
        this.scalarTypeAdapters = scalarTypeAdapters;
        this.dispatcher = executor;
        this.logger = apolloLogger;
        this.tracker = apolloCallTracker;
        this.interceptorChain = new RealApolloInterceptorChain(Collections.singletonList(new ApolloServerInterceptor(httpUrl, factory, HttpCachePolicy.NETWORK_ONLY, true, scalarTypeAdapters, apolloLogger)));
    }

    @Override // com.apollographql.apollo.ApolloPrefetch
    public void enqueue(ApolloPrefetch.Callback callback) {
        try {
            activate(Optional.fromNullable(callback));
            this.interceptorChain.proceedAsync(ApolloInterceptor.InterceptorRequest.builder(this.operation).build(), this.dispatcher, interceptorCallbackProxy());
        } catch (ApolloCanceledException e) {
            if (callback != null) {
                callback.onFailure(e);
            } else {
                this.logger.e(e, "Operation: %s was canceled", operation().name().name());
            }
        }
    }

    @Override // com.apollographql.apollo.ApolloPrefetch
    public Operation operation() {
        return this.operation;
    }

    private ApolloInterceptor.CallBack interceptorCallbackProxy() {
        return new ApolloInterceptor.CallBack() { // from class: com.apollographql.apollo.internal.RealApolloPrefetch.1
            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onCompleted() {
            }

            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onFetch(ApolloInterceptor.FetchSourceType fetchSourceType) {
            }

            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onResponse(ApolloInterceptor.InterceptorResponse interceptorResponse) {
                Response response = interceptorResponse.httpResponse.get();
                try {
                    Optional<ApolloPrefetch.Callback> optionalTerminate = RealApolloPrefetch.this.terminate();
                    if (!optionalTerminate.isPresent()) {
                        RealApolloPrefetch.this.logger.d("onResponse for prefetch operation: %s. No callback present.", RealApolloPrefetch.this.operation().name().name());
                        return;
                    }
                    if (response.isSuccessful()) {
                        optionalTerminate.get().onSuccess();
                    } else {
                        optionalTerminate.get().onHttpError(new ApolloHttpException(response));
                    }
                } finally {
                    response.close();
                }
            }

            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onFailure(ApolloException apolloException) {
                Optional<ApolloPrefetch.Callback> optionalTerminate = RealApolloPrefetch.this.terminate();
                if (!optionalTerminate.isPresent()) {
                    RealApolloPrefetch.this.logger.e(apolloException, "onFailure for prefetch operation: %s. No callback present.", RealApolloPrefetch.this.operation().name().name());
                    return;
                }
                if (apolloException instanceof ApolloHttpException) {
                    optionalTerminate.get().onHttpError((ApolloHttpException) apolloException);
                } else if (apolloException instanceof ApolloNetworkException) {
                    optionalTerminate.get().onNetworkError((ApolloNetworkException) apolloException);
                } else {
                    optionalTerminate.get().onFailure(apolloException);
                }
            }
        };
    }

    @Override // com.apollographql.apollo.ApolloPrefetch
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ApolloPrefetch m7clone() {
        return new RealApolloPrefetch(this.operation, this.serverUrl, this.httpCallFactory, this.scalarTypeAdapters, this.dispatcher, this.logger, this.tracker);
    }

    /* JADX INFO: renamed from: com.apollographql.apollo.internal.RealApolloPrefetch$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$apollographql$apollo$internal$CallState;

        static {
            int[] iArr = new int[CallState.values().length];
            $SwitchMap$com$apollographql$apollo$internal$CallState = iArr;
            try {
                iArr[CallState.ACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$internal$CallState[CallState.IDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$internal$CallState[CallState.CANCELED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$internal$CallState[CallState.TERMINATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.apollographql.apollo.ApolloPrefetch, com.apollographql.apollo.internal.util.Cancelable
    public synchronized void cancel() {
        int i = AnonymousClass2.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
        if (i == 1) {
            try {
                this.interceptorChain.dispose();
                this.tracker.unregisterPrefetchCall(this);
                this.originalCallback.set(null);
                this.state.set(CallState.CANCELED);
            } catch (Throwable th) {
                this.tracker.unregisterPrefetchCall(this);
                this.originalCallback.set(null);
                this.state.set(CallState.CANCELED);
                throw th;
            }
        } else if (i == 2) {
            this.state.set(CallState.CANCELED);
        } else if (i != 3 && i != 4) {
            throw new IllegalStateException("Unknown state");
        }
    }

    @Override // com.apollographql.apollo.internal.util.Cancelable
    public boolean isCanceled() {
        return this.state.get() == CallState.CANCELED;
    }

    private synchronized void activate(Optional<ApolloPrefetch.Callback> optional) throws ApolloCanceledException {
        int i = AnonymousClass2.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
        if (i != 1) {
            if (i == 2) {
                this.originalCallback.set(optional.orNull());
                this.tracker.registerPrefetchCall(this);
                this.state.set(CallState.ACTIVE);
            } else {
                if (i == 3) {
                    throw new ApolloCanceledException();
                }
                if (i != 4) {
                    throw new IllegalStateException("Unknown state");
                }
            }
        }
        throw new IllegalStateException("Already Executed");
    }

    synchronized Optional<ApolloPrefetch.Callback> terminate() {
        int i = AnonymousClass2.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
        if (i == 1) {
            this.tracker.unregisterPrefetchCall(this);
            this.state.set(CallState.TERMINATED);
            return Optional.fromNullable(this.originalCallback.getAndSet(null));
        }
        if (i != 2) {
            if (i == 3) {
                return Optional.fromNullable(this.originalCallback.getAndSet(null));
            }
            if (i != 4) {
                throw new IllegalStateException("Unknown state");
            }
        }
        throw new IllegalStateException(CallState.IllegalStateMessage.forCurrentState(this.state.get()).expected(CallState.ACTIVE, CallState.CANCELED));
    }
}
