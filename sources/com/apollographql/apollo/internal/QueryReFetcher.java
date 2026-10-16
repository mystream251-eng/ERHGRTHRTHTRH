package com.apollographql.apollo.internal;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloQueryWatcher;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.cache.http.HttpCachePolicy;
import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.fetcher.ApolloResponseFetchers;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.Call;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
final class QueryReFetcher {
    private ApolloCallTracker callTracker;
    private final List<RealApolloCall> calls;
    private final AtomicBoolean executed = new AtomicBoolean();
    final ApolloLogger logger;
    OnCompleteCallback onCompleteCallback;
    private List<OperationName> queryWatchers;

    interface OnCompleteCallback {
        void onFetchComplete();
    }

    static Builder builder() {
        return new Builder();
    }

    QueryReFetcher(Builder builder) {
        this.logger = builder.logger;
        this.calls = new ArrayList(builder.queries.size());
        Iterator<Query> it = builder.queries.iterator();
        while (it.hasNext()) {
            this.calls.add(RealApolloCall.builder().operation(it.next()).serverUrl(builder.serverUrl).httpCallFactory(builder.httpCallFactory).responseFieldMapperFactory(builder.responseFieldMapperFactory).scalarTypeAdapters(builder.scalarTypeAdapters).apolloStore(builder.apolloStore).httpCachePolicy(HttpCachePolicy.NETWORK_ONLY).responseFetcher(ApolloResponseFetchers.NETWORK_ONLY).cacheHeaders(CacheHeaders.NONE).logger(builder.logger).applicationInterceptors(builder.applicationInterceptors).applicationInterceptorFactories(builder.applicationInterceptorFactories).autoPersistedOperationsInterceptorFactory(builder.autoPersistedOperationsInterceptorFactory).tracker(builder.callTracker).dispatcher(builder.dispatcher).build());
        }
        this.queryWatchers = builder.queryWatchers;
        this.callTracker = builder.callTracker;
    }

    void refetch() {
        if (!this.executed.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        refetchQueryWatchers();
        refetchQueries();
    }

    void cancel() {
        Iterator<RealApolloCall> it = this.calls.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    private void refetchQueryWatchers() {
        try {
            Iterator<OperationName> it = this.queryWatchers.iterator();
            while (it.hasNext()) {
                Iterator<ApolloQueryWatcher> it2 = this.callTracker.activeQueryWatchers(it.next()).iterator();
                while (it2.hasNext()) {
                    it2.next().refetch();
                }
            }
        } catch (Exception e) {
            this.logger.e(e, "Failed to re-fetch query watcher", new Object[0]);
        }
    }

    private void refetchQueries() {
        final OnCompleteCallback onCompleteCallback = this.onCompleteCallback;
        final AtomicInteger atomicInteger = new AtomicInteger(this.calls.size());
        for (final RealApolloCall realApolloCall : this.calls) {
            realApolloCall.enqueue(new ApolloCall.Callback() { // from class: com.apollographql.apollo.internal.QueryReFetcher.1
                @Override // com.apollographql.apollo.ApolloCall.Callback
                public void onResponse(Response response) {
                    OnCompleteCallback onCompleteCallback2;
                    if (atomicInteger.decrementAndGet() != 0 || (onCompleteCallback2 = onCompleteCallback) == null) {
                        return;
                    }
                    onCompleteCallback2.onFetchComplete();
                }

                @Override // com.apollographql.apollo.ApolloCall.Callback
                public void onFailure(ApolloException apolloException) {
                    OnCompleteCallback onCompleteCallback2;
                    if (QueryReFetcher.this.logger != null) {
                        QueryReFetcher.this.logger.e(apolloException, "Failed to fetch query: %s", realApolloCall.operation);
                    }
                    if (atomicInteger.decrementAndGet() != 0 || (onCompleteCallback2 = onCompleteCallback) == null) {
                        return;
                    }
                    onCompleteCallback2.onFetchComplete();
                }
            });
        }
    }

    static final class Builder {
        ApolloStore apolloStore;
        List<ApolloInterceptorFactory> applicationInterceptorFactories;
        List<ApolloInterceptor> applicationInterceptors;
        ApolloInterceptorFactory autoPersistedOperationsInterceptorFactory;
        ApolloCallTracker callTracker;
        Executor dispatcher;
        Call.Factory httpCallFactory;
        ApolloLogger logger;
        List<Query> queries = Collections.emptyList();
        List<OperationName> queryWatchers = Collections.emptyList();
        ResponseFieldMapperFactory responseFieldMapperFactory;
        ScalarTypeAdapters scalarTypeAdapters;
        HttpUrl serverUrl;

        Builder queries(List<Query> list) {
            if (list == null) {
                list = Collections.emptyList();
            }
            this.queries = list;
            return this;
        }

        public Builder queryWatchers(List<OperationName> list) {
            if (list == null) {
                list = Collections.emptyList();
            }
            this.queryWatchers = list;
            return this;
        }

        Builder serverUrl(HttpUrl httpUrl) {
            this.serverUrl = httpUrl;
            return this;
        }

        Builder httpCallFactory(Call.Factory factory) {
            this.httpCallFactory = factory;
            return this;
        }

        Builder responseFieldMapperFactory(ResponseFieldMapperFactory responseFieldMapperFactory) {
            this.responseFieldMapperFactory = responseFieldMapperFactory;
            return this;
        }

        Builder scalarTypeAdapters(ScalarTypeAdapters scalarTypeAdapters) {
            this.scalarTypeAdapters = scalarTypeAdapters;
            return this;
        }

        Builder apolloStore(ApolloStore apolloStore) {
            this.apolloStore = apolloStore;
            return this;
        }

        Builder dispatcher(Executor executor) {
            this.dispatcher = executor;
            return this;
        }

        Builder logger(ApolloLogger apolloLogger) {
            this.logger = apolloLogger;
            return this;
        }

        Builder applicationInterceptors(List<ApolloInterceptor> list) {
            this.applicationInterceptors = list;
            return this;
        }

        Builder applicationInterceptorFactories(List<ApolloInterceptorFactory> list) {
            this.applicationInterceptorFactories = list;
            return this;
        }

        Builder autoPersistedOperationsInterceptorFactory(ApolloInterceptorFactory apolloInterceptorFactory) {
            this.autoPersistedOperationsInterceptorFactory = apolloInterceptorFactory;
            return this;
        }

        Builder callTracker(ApolloCallTracker apolloCallTracker) {
            this.callTracker = apolloCallTracker;
            return this;
        }

        QueryReFetcher build() {
            return new QueryReFetcher(this);
        }

        Builder() {
        }
    }
}
