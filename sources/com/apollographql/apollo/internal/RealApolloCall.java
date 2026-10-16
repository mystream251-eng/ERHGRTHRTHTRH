package com.apollographql.apollo.internal;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloMutationCall;
import com.apollographql.apollo.ApolloQueryCall;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.cache.http.HttpCache;
import com.apollographql.apollo.api.cache.http.HttpCachePolicy;
import com.apollographql.apollo.api.internal.Action;
import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.exception.ApolloCanceledException;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.exception.ApolloHttpException;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.exception.ApolloParseException;
import com.apollographql.apollo.fetcher.ResponseFetcher;
import com.apollographql.apollo.interceptor.ApolloAutoPersistedOperationInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import com.apollographql.apollo.interceptor.ApolloInterceptorFactory;
import com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor;
import com.apollographql.apollo.internal.interceptor.ApolloParseInterceptor;
import com.apollographql.apollo.internal.interceptor.ApolloServerInterceptor;
import com.apollographql.apollo.internal.interceptor.RealApolloInterceptorChain;
import com.apollographql.apollo.request.RequestHeaders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.Call;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public final class RealApolloCall<T> implements ApolloQueryCall<T>, ApolloMutationCall<T> {
    final ApolloStore apolloStore;
    final List<ApolloInterceptorFactory> applicationInterceptorFactories;
    final List<ApolloInterceptor> applicationInterceptors;
    final ApolloInterceptorFactory autoPersistedOperationsInterceptorFactory;
    final CacheHeaders cacheHeaders;
    final Executor dispatcher;
    final boolean enableAutoPersistedQueries;
    final HttpCache httpCache;
    final HttpCachePolicy.Policy httpCachePolicy;
    final Call.Factory httpCallFactory;
    final ApolloInterceptorChain interceptorChain;
    final ApolloLogger logger;
    final Operation operation;
    final Optional<Operation.Data> optimisticUpdates;
    final Optional<QueryReFetcher> queryReFetcher;
    final List<Query> refetchQueries;
    final List<OperationName> refetchQueryNames;
    final RequestHeaders requestHeaders;
    final ResponseFetcher responseFetcher;
    final ResponseFieldMapperFactory responseFieldMapperFactory;
    final ScalarTypeAdapters scalarTypeAdapters;
    final HttpUrl serverUrl;
    final ApolloCallTracker tracker;
    final boolean useHttpGetMethodForPersistedQueries;
    final boolean useHttpGetMethodForQueries;
    final boolean writeToNormalizedCacheAsynchronously;
    final AtomicReference<CallState> state = new AtomicReference<>(CallState.IDLE);
    final AtomicReference<ApolloCall.Callback<T>> originalCallback = new AtomicReference<>();

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    RealApolloCall(Builder<T> builder) {
        this.operation = builder.operation;
        this.serverUrl = builder.serverUrl;
        this.httpCallFactory = builder.httpCallFactory;
        this.httpCache = builder.httpCache;
        this.httpCachePolicy = builder.httpCachePolicy;
        this.responseFieldMapperFactory = builder.responseFieldMapperFactory;
        this.scalarTypeAdapters = builder.scalarTypeAdapters;
        this.apolloStore = builder.apolloStore;
        this.responseFetcher = builder.responseFetcher;
        this.cacheHeaders = builder.cacheHeaders;
        this.requestHeaders = builder.requestHeaders;
        this.dispatcher = builder.dispatcher;
        this.logger = builder.logger;
        this.applicationInterceptors = builder.applicationInterceptors;
        this.applicationInterceptorFactories = builder.applicationInterceptorFactories;
        this.autoPersistedOperationsInterceptorFactory = builder.autoPersistedOperationsInterceptorFactory;
        this.refetchQueryNames = builder.refetchQueryNames;
        this.refetchQueries = builder.refetchQueries;
        this.tracker = builder.tracker;
        if ((this.refetchQueries.isEmpty() && this.refetchQueryNames.isEmpty()) || builder.apolloStore == null) {
            this.queryReFetcher = Optional.absent();
        } else {
            this.queryReFetcher = Optional.of(QueryReFetcher.builder().queries(builder.refetchQueries).queryWatchers(this.refetchQueryNames).serverUrl(builder.serverUrl).httpCallFactory(builder.httpCallFactory).responseFieldMapperFactory(builder.responseFieldMapperFactory).scalarTypeAdapters(builder.scalarTypeAdapters).apolloStore(builder.apolloStore).dispatcher(builder.dispatcher).logger(builder.logger).applicationInterceptors(builder.applicationInterceptors).applicationInterceptorFactories(builder.applicationInterceptorFactories).autoPersistedOperationsInterceptorFactory(builder.autoPersistedOperationsInterceptorFactory).callTracker(builder.tracker).build());
        }
        this.useHttpGetMethodForQueries = builder.useHttpGetMethodForQueries;
        this.enableAutoPersistedQueries = builder.enableAutoPersistedQueries;
        this.useHttpGetMethodForPersistedQueries = builder.useHttpGetMethodForPersistedQueries;
        this.interceptorChain = prepareInterceptorChain(this.operation);
        this.optimisticUpdates = builder.optimisticUpdates;
        this.writeToNormalizedCacheAsynchronously = builder.writeToNormalizedCacheAsynchronously;
    }

    @Override // com.apollographql.apollo.ApolloCall
    public void enqueue(ApolloCall.Callback<T> callback) {
        try {
            activate(Optional.fromNullable(callback));
            this.interceptorChain.proceedAsync(ApolloInterceptor.InterceptorRequest.builder(this.operation).cacheHeaders(this.cacheHeaders).requestHeaders(this.requestHeaders).fetchFromCache(false).optimisticUpdates(this.optimisticUpdates).useHttpGetMethodForQueries(this.useHttpGetMethodForQueries).build(), this.dispatcher, interceptorCallbackProxy());
        } catch (ApolloCanceledException e) {
            if (callback != null) {
                callback.onCanceledError(e);
            } else {
                this.logger.e(e, "Operation: %s was canceled", operation().name().name());
            }
        }
    }

    @Override // com.apollographql.apollo.ApolloQueryCall
    public RealApolloQueryWatcher<T> watcher() {
        return new RealApolloQueryWatcher<>(m6clone(), this.apolloStore, this.logger, this.tracker);
    }

    @Override // com.apollographql.apollo.ApolloQueryCall
    public RealApolloCall<T> httpCachePolicy(HttpCachePolicy.Policy policy) {
        if (this.state.get() != CallState.IDLE) {
            throw new IllegalStateException("Already Executed");
        }
        return toBuilder().httpCachePolicy((HttpCachePolicy.Policy) Utils.checkNotNull(policy, "httpCachePolicy == null")).build();
    }

    @Override // com.apollographql.apollo.ApolloQueryCall
    public RealApolloCall<T> responseFetcher(ResponseFetcher responseFetcher) {
        if (this.state.get() != CallState.IDLE) {
            throw new IllegalStateException("Already Executed");
        }
        return toBuilder().responseFetcher((ResponseFetcher) Utils.checkNotNull(responseFetcher, "responseFetcher == null")).build();
    }

    @Override // com.apollographql.apollo.ApolloQueryCall, com.apollographql.apollo.ApolloCall
    public RealApolloCall<T> cacheHeaders(CacheHeaders cacheHeaders) {
        if (this.state.get() != CallState.IDLE) {
            throw new IllegalStateException("Already Executed");
        }
        return toBuilder().cacheHeaders((CacheHeaders) Utils.checkNotNull(cacheHeaders, "cacheHeaders == null")).build();
    }

    @Override // com.apollographql.apollo.ApolloQueryCall
    public RealApolloCall<T> requestHeaders(RequestHeaders requestHeaders) {
        if (this.state.get() != CallState.IDLE) {
            throw new IllegalStateException("Already Executed");
        }
        return toBuilder().requestHeaders((RequestHeaders) Utils.checkNotNull(requestHeaders, "requestHeaders == null")).build();
    }

    @Override // com.apollographql.apollo.ApolloCall, com.apollographql.apollo.internal.util.Cancelable
    public synchronized void cancel() {
        int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
        if (i == 1) {
            this.state.set(CallState.CANCELED);
            try {
                this.interceptorChain.dispose();
                if (this.queryReFetcher.isPresent()) {
                    this.queryReFetcher.get().cancel();
                }
                this.tracker.unregisterCall(this);
                this.originalCallback.set(null);
            } catch (Throwable th) {
                this.tracker.unregisterCall(this);
                this.originalCallback.set(null);
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

    @Override // com.apollographql.apollo.ApolloQueryCall, com.apollographql.apollo.ApolloCall
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public RealApolloCall<T> m6clone() {
        return toBuilder().build();
    }

    @Override // com.apollographql.apollo.ApolloMutationCall
    public ApolloMutationCall<T> refetchQueries(OperationName... operationNameArr) {
        if (this.state.get() != CallState.IDLE) {
            throw new IllegalStateException("Already Executed");
        }
        return toBuilder().refetchQueryNames(Arrays.asList((Object[]) Utils.checkNotNull(operationNameArr, "operationNames == null"))).build();
    }

    @Override // com.apollographql.apollo.ApolloMutationCall
    public ApolloMutationCall<T> refetchQueries(Query... queryArr) {
        if (this.state.get() != CallState.IDLE) {
            throw new IllegalStateException("Already Executed");
        }
        return toBuilder().refetchQueries(Arrays.asList((Object[]) Utils.checkNotNull(queryArr, "queries == null"))).build();
    }

    @Override // com.apollographql.apollo.ApolloCall
    public Operation operation() {
        return this.operation;
    }

    private ApolloInterceptor.CallBack interceptorCallbackProxy() {
        return new ApolloInterceptor.CallBack() { // from class: com.apollographql.apollo.internal.RealApolloCall.1
            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onResponse(ApolloInterceptor.InterceptorResponse interceptorResponse) {
                Optional<ApolloCall.Callback<T>> optionalResponseCallback = RealApolloCall.this.responseCallback();
                if (!optionalResponseCallback.isPresent()) {
                    RealApolloCall.this.logger.d("onResponse for operation: %s. No callback present.", RealApolloCall.this.operation().name().name());
                } else {
                    optionalResponseCallback.get().onResponse(interceptorResponse.parsedResponse.get());
                }
            }

            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onFailure(ApolloException apolloException) {
                Optional<ApolloCall.Callback<T>> optionalTerminate = RealApolloCall.this.terminate();
                if (!optionalTerminate.isPresent()) {
                    RealApolloCall.this.logger.d(apolloException, "onFailure for operation: %s. No callback present.", RealApolloCall.this.operation().name().name());
                    return;
                }
                if (apolloException instanceof ApolloHttpException) {
                    optionalTerminate.get().onHttpError((ApolloHttpException) apolloException);
                    return;
                }
                if (apolloException instanceof ApolloParseException) {
                    optionalTerminate.get().onParseError((ApolloParseException) apolloException);
                } else if (apolloException instanceof ApolloNetworkException) {
                    optionalTerminate.get().onNetworkError((ApolloNetworkException) apolloException);
                } else {
                    optionalTerminate.get().onFailure(apolloException);
                }
            }

            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onCompleted() {
                Optional<ApolloCall.Callback<T>> optionalTerminate = RealApolloCall.this.terminate();
                if (RealApolloCall.this.queryReFetcher.isPresent()) {
                    RealApolloCall.this.queryReFetcher.get().refetch();
                }
                if (!optionalTerminate.isPresent()) {
                    RealApolloCall.this.logger.d("onCompleted for operation: %s. No callback present.", RealApolloCall.this.operation().name().name());
                } else {
                    optionalTerminate.get().onStatusEvent(ApolloCall.StatusEvent.COMPLETED);
                }
            }

            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onFetch(final ApolloInterceptor.FetchSourceType fetchSourceType) {
                RealApolloCall.this.responseCallback().apply(new Action<ApolloCall.Callback<T>>() { // from class: com.apollographql.apollo.internal.RealApolloCall.1.1
                    @Override // com.apollographql.apollo.api.internal.Action
                    public void apply(ApolloCall.Callback<T> callback) {
                        int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$interceptor$ApolloInterceptor$FetchSourceType[fetchSourceType.ordinal()];
                        if (i == 1) {
                            callback.onStatusEvent(ApolloCall.StatusEvent.FETCH_CACHE);
                        } else {
                            if (i != 2) {
                                return;
                            }
                            callback.onStatusEvent(ApolloCall.StatusEvent.FETCH_NETWORK);
                        }
                    }
                });
            }
        };
    }

    /* JADX INFO: renamed from: com.apollographql.apollo.internal.RealApolloCall$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$apollographql$apollo$interceptor$ApolloInterceptor$FetchSourceType;
        static final /* synthetic */ int[] $SwitchMap$com$apollographql$apollo$internal$CallState;

        static {
            int[] iArr = new int[ApolloInterceptor.FetchSourceType.values().length];
            $SwitchMap$com$apollographql$apollo$interceptor$ApolloInterceptor$FetchSourceType = iArr;
            try {
                iArr[ApolloInterceptor.FetchSourceType.CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$interceptor$ApolloInterceptor$FetchSourceType[ApolloInterceptor.FetchSourceType.NETWORK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[CallState.values().length];
            $SwitchMap$com$apollographql$apollo$internal$CallState = iArr2;
            try {
                iArr2[CallState.ACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$internal$CallState[CallState.IDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$internal$CallState[CallState.CANCELED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$apollographql$apollo$internal$CallState[CallState.TERMINATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // com.apollographql.apollo.ApolloQueryCall, com.apollographql.apollo.ApolloCall
    public Builder<T> toBuilder() {
        return builder().operation(this.operation).serverUrl(this.serverUrl).httpCallFactory(this.httpCallFactory).httpCache(this.httpCache).httpCachePolicy(this.httpCachePolicy).responseFieldMapperFactory(this.responseFieldMapperFactory).scalarTypeAdapters(this.scalarTypeAdapters).apolloStore(this.apolloStore).cacheHeaders(this.cacheHeaders).requestHeaders(this.requestHeaders).responseFetcher(this.responseFetcher).dispatcher(this.dispatcher).logger(this.logger).applicationInterceptors(this.applicationInterceptors).applicationInterceptorFactories(this.applicationInterceptorFactories).autoPersistedOperationsInterceptorFactory(this.autoPersistedOperationsInterceptorFactory).tracker(this.tracker).refetchQueryNames(this.refetchQueryNames).refetchQueries(this.refetchQueries).enableAutoPersistedQueries(this.enableAutoPersistedQueries).useHttpGetMethodForPersistedQueries(this.useHttpGetMethodForPersistedQueries).optimisticUpdates(this.optimisticUpdates).writeToNormalizedCacheAsynchronously(this.writeToNormalizedCacheAsynchronously);
    }

    private synchronized void activate(Optional<ApolloCall.Callback<T>> optional) {
        int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
        if (i != 1) {
            if (i == 2) {
                this.originalCallback.set(optional.orNull());
                this.tracker.registerCall(this);
                optional.apply(new Action<ApolloCall.Callback<T>>() { // from class: com.apollographql.apollo.internal.RealApolloCall.2
                    @Override // com.apollographql.apollo.api.internal.Action
                    public void apply(ApolloCall.Callback<T> callback) {
                        callback.onStatusEvent(ApolloCall.StatusEvent.SCHEDULED);
                    }
                });
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

    synchronized Optional<ApolloCall.Callback<T>> responseCallback() {
        int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        throw new IllegalStateException("Unknown state");
                    }
                }
            }
            throw new IllegalStateException(CallState.IllegalStateMessage.forCurrentState(this.state.get()).expected(CallState.ACTIVE, CallState.CANCELED));
        }
        return Optional.fromNullable(this.originalCallback.get());
    }

    synchronized Optional<ApolloCall.Callback<T>> terminate() {
        int i = AnonymousClass3.$SwitchMap$com$apollographql$apollo$internal$CallState[this.state.get().ordinal()];
        if (i == 1) {
            this.tracker.unregisterCall(this);
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

    private ApolloInterceptorChain prepareInterceptorChain(Operation operation) {
        boolean z = operation instanceof Query;
        HttpCachePolicy.Policy policy = z ? this.httpCachePolicy : null;
        ResponseFieldMapper responseFieldMapperCreate = this.responseFieldMapperFactory.create(operation);
        ArrayList arrayList = new ArrayList();
        Iterator<ApolloInterceptorFactory> it = this.applicationInterceptorFactories.iterator();
        while (it.hasNext()) {
            ApolloInterceptor apolloInterceptorNewInterceptor = it.next().newInterceptor(this.logger, operation);
            if (apolloInterceptorNewInterceptor != null) {
                arrayList.add(apolloInterceptorNewInterceptor);
            }
        }
        arrayList.addAll(this.applicationInterceptors);
        arrayList.add(this.responseFetcher.provideInterceptor(this.logger));
        arrayList.add(new ApolloCacheInterceptor(this.apolloStore, responseFieldMapperCreate, this.dispatcher, this.logger, this.writeToNormalizedCacheAsynchronously));
        ApolloInterceptorFactory apolloInterceptorFactory = this.autoPersistedOperationsInterceptorFactory;
        if (apolloInterceptorFactory != null) {
            ApolloInterceptor apolloInterceptorNewInterceptor2 = apolloInterceptorFactory.newInterceptor(this.logger, operation);
            if (apolloInterceptorNewInterceptor2 != null) {
                arrayList.add(apolloInterceptorNewInterceptor2);
            }
        } else if (z && this.enableAutoPersistedQueries) {
            arrayList.add(new ApolloAutoPersistedOperationInterceptor(this.logger, this.useHttpGetMethodForPersistedQueries));
        }
        arrayList.add(new ApolloParseInterceptor(this.httpCache, this.apolloStore.networkResponseNormalizer(), responseFieldMapperCreate, this.scalarTypeAdapters, this.logger));
        arrayList.add(new ApolloServerInterceptor(this.serverUrl, this.httpCallFactory, policy, false, this.scalarTypeAdapters, this.logger));
        return new RealApolloInterceptorChain(arrayList);
    }

    public static final class Builder<T> implements ApolloQueryCall.Builder<T>, ApolloMutationCall.Builder<T> {
        ApolloStore apolloStore;
        List<ApolloInterceptorFactory> applicationInterceptorFactories;
        List<ApolloInterceptor> applicationInterceptors;
        ApolloInterceptorFactory autoPersistedOperationsInterceptorFactory;
        CacheHeaders cacheHeaders;
        Executor dispatcher;
        boolean enableAutoPersistedQueries;
        HttpCache httpCache;
        HttpCachePolicy.Policy httpCachePolicy;
        Call.Factory httpCallFactory;
        ApolloLogger logger;
        Operation operation;
        ResponseFetcher responseFetcher;
        ResponseFieldMapperFactory responseFieldMapperFactory;
        ScalarTypeAdapters scalarTypeAdapters;
        HttpUrl serverUrl;
        ApolloCallTracker tracker;
        boolean useHttpGetMethodForPersistedQueries;
        boolean useHttpGetMethodForQueries;
        boolean writeToNormalizedCacheAsynchronously;
        RequestHeaders requestHeaders = RequestHeaders.NONE;
        List<OperationName> refetchQueryNames = Collections.emptyList();
        List<Query> refetchQueries = Collections.emptyList();
        Optional<Operation.Data> optimisticUpdates = Optional.absent();

        @Override // com.apollographql.apollo.ApolloMutationCall.Builder
        public /* bridge */ /* synthetic */ ApolloMutationCall.Builder refetchQueries(List list) {
            return refetchQueries((List<Query>) list);
        }

        @Override // com.apollographql.apollo.ApolloMutationCall.Builder
        public /* bridge */ /* synthetic */ ApolloMutationCall.Builder refetchQueryNames(List list) {
            return refetchQueryNames((List<OperationName>) list);
        }

        public Builder<T> operation(Operation operation) {
            this.operation = operation;
            return this;
        }

        public Builder<T> serverUrl(HttpUrl httpUrl) {
            this.serverUrl = httpUrl;
            return this;
        }

        public Builder<T> httpCallFactory(Call.Factory factory) {
            this.httpCallFactory = factory;
            return this;
        }

        public Builder<T> httpCache(HttpCache httpCache) {
            this.httpCache = httpCache;
            return this;
        }

        public Builder<T> responseFieldMapperFactory(ResponseFieldMapperFactory responseFieldMapperFactory) {
            this.responseFieldMapperFactory = responseFieldMapperFactory;
            return this;
        }

        public Builder<T> scalarTypeAdapters(ScalarTypeAdapters scalarTypeAdapters) {
            this.scalarTypeAdapters = scalarTypeAdapters;
            return this;
        }

        public Builder<T> apolloStore(ApolloStore apolloStore) {
            this.apolloStore = apolloStore;
            return this;
        }

        @Override // com.apollographql.apollo.ApolloQueryCall.Builder, com.apollographql.apollo.ApolloCall.Builder
        public Builder<T> cacheHeaders(CacheHeaders cacheHeaders) {
            this.cacheHeaders = cacheHeaders;
            return this;
        }

        @Override // com.apollographql.apollo.ApolloQueryCall.Builder
        public Builder<T> httpCachePolicy(HttpCachePolicy.Policy policy) {
            this.httpCachePolicy = policy;
            return this;
        }

        @Override // com.apollographql.apollo.ApolloQueryCall.Builder
        public Builder<T> responseFetcher(ResponseFetcher responseFetcher) {
            this.responseFetcher = responseFetcher;
            return this;
        }

        @Override // com.apollographql.apollo.ApolloQueryCall.Builder
        public Builder<T> requestHeaders(RequestHeaders requestHeaders) {
            this.requestHeaders = requestHeaders;
            return this;
        }

        @Override // com.apollographql.apollo.ApolloMutationCall.Builder
        public Builder<T> refetchQueryNames(List<OperationName> list) {
            this.refetchQueryNames = new ArrayList(list);
            return this;
        }

        @Override // com.apollographql.apollo.ApolloMutationCall.Builder
        public Builder<T> refetchQueries(List<Query> list) {
            this.refetchQueries = new ArrayList(list);
            return this;
        }

        public Builder<T> dispatcher(Executor executor) {
            this.dispatcher = executor;
            return this;
        }

        public Builder<T> logger(ApolloLogger apolloLogger) {
            this.logger = apolloLogger;
            return this;
        }

        public Builder<T> tracker(ApolloCallTracker apolloCallTracker) {
            this.tracker = apolloCallTracker;
            return this;
        }

        public Builder<T> applicationInterceptors(List<ApolloInterceptor> list) {
            this.applicationInterceptors = list;
            return this;
        }

        public Builder<T> applicationInterceptorFactories(List<ApolloInterceptorFactory> list) {
            this.applicationInterceptorFactories = list;
            return this;
        }

        public Builder<T> autoPersistedOperationsInterceptorFactory(ApolloInterceptorFactory apolloInterceptorFactory) {
            this.autoPersistedOperationsInterceptorFactory = apolloInterceptorFactory;
            return this;
        }

        public Builder<T> enableAutoPersistedQueries(boolean z) {
            this.enableAutoPersistedQueries = z;
            return this;
        }

        public Builder<T> optimisticUpdates(Optional<Operation.Data> optional) {
            this.optimisticUpdates = optional;
            return this;
        }

        public Builder<T> useHttpGetMethodForQueries(boolean z) {
            this.useHttpGetMethodForQueries = z;
            return this;
        }

        public Builder<T> useHttpGetMethodForPersistedQueries(boolean z) {
            this.useHttpGetMethodForPersistedQueries = z;
            return this;
        }

        public Builder<T> writeToNormalizedCacheAsynchronously(boolean z) {
            this.writeToNormalizedCacheAsynchronously = z;
            return this;
        }

        Builder() {
        }

        @Override // com.apollographql.apollo.ApolloQueryCall.Builder, com.apollographql.apollo.ApolloCall.Builder
        public RealApolloCall<T> build() {
            return new RealApolloCall<>(this);
        }
    }
}
