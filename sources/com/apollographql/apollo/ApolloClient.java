package com.apollographql.apollo;

import com.apollographql.apollo.api.CustomTypeAdapter;
import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.Subscription;
import com.apollographql.apollo.api.cache.http.HttpCache;
import com.apollographql.apollo.api.cache.http.HttpCachePolicy;
import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.ApolloStoreOperation;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.NormalizedCacheFactory;
import com.apollographql.apollo.cache.normalized.RecordFieldJsonAdapter;
import com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer;
import com.apollographql.apollo.fetcher.ApolloResponseFetchers;
import com.apollographql.apollo.fetcher.ResponseFetcher;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorFactory;
import com.apollographql.apollo.internal.ApolloCallTracker;
import com.apollographql.apollo.internal.RealApolloCall;
import com.apollographql.apollo.internal.RealApolloPrefetch;
import com.apollographql.apollo.internal.RealApolloStore;
import com.apollographql.apollo.internal.RealApolloSubscriptionCall;
import com.apollographql.apollo.internal.ResponseFieldMapperFactory;
import com.apollographql.apollo.internal.subscription.NoOpSubscriptionManager;
import com.apollographql.apollo.internal.subscription.RealSubscriptionManager;
import com.apollographql.apollo.internal.subscription.SubscriptionManager;
import com.apollographql.apollo.subscription.OnSubscriptionManagerStateChangeListener;
import com.apollographql.apollo.subscription.SubscriptionConnectionParams;
import com.apollographql.apollo.subscription.SubscriptionConnectionParamsProvider;
import com.apollographql.apollo.subscription.SubscriptionManagerState;
import com.apollographql.apollo.subscription.SubscriptionTransport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.functions.Function0;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public final class ApolloClient implements ApolloQueryCall.Factory, ApolloMutationCall.Factory, ApolloPrefetch.Factory, ApolloSubscriptionCall.Factory {
    private final ApolloStore apolloStore;
    private final List<ApolloInterceptorFactory> applicationInterceptorFactories;
    private final List<ApolloInterceptor> applicationInterceptors;
    private final ApolloInterceptorFactory autoPersistedOperationsInterceptorFactory;
    private final CacheHeaders defaultCacheHeaders;
    private final HttpCachePolicy.Policy defaultHttpCachePolicy;
    private final ResponseFetcher defaultResponseFetcher;
    private final Executor dispatcher;
    private final boolean enableAutoPersistedQueries;
    private final HttpCache httpCache;
    private final Call.Factory httpCallFactory;
    private final ApolloLogger logger;
    private final ScalarTypeAdapters scalarTypeAdapters;
    private final HttpUrl serverUrl;
    private final SubscriptionManager subscriptionManager;
    private final boolean useHttpGetMethodForPersistedQueries;
    private final boolean useHttpGetMethodForQueries;
    private final boolean writeToNormalizedCacheAsynchronously;
    private final ResponseFieldMapperFactory responseFieldMapperFactory = new ResponseFieldMapperFactory();
    private final ApolloCallTracker tracker = new ApolloCallTracker();

    public static Builder builder() {
        return new Builder();
    }

    ApolloClient(HttpUrl httpUrl, Call.Factory factory, HttpCache httpCache, ApolloStore apolloStore, ScalarTypeAdapters scalarTypeAdapters, Executor executor, HttpCachePolicy.Policy policy, ResponseFetcher responseFetcher, CacheHeaders cacheHeaders, ApolloLogger apolloLogger, List<ApolloInterceptor> list, List<ApolloInterceptorFactory> list2, ApolloInterceptorFactory apolloInterceptorFactory, boolean z, SubscriptionManager subscriptionManager, boolean z2, boolean z3, boolean z4) {
        this.serverUrl = httpUrl;
        this.httpCallFactory = factory;
        this.httpCache = httpCache;
        this.apolloStore = apolloStore;
        this.scalarTypeAdapters = scalarTypeAdapters;
        this.dispatcher = executor;
        this.defaultHttpCachePolicy = policy;
        this.defaultResponseFetcher = responseFetcher;
        this.defaultCacheHeaders = cacheHeaders;
        this.logger = apolloLogger;
        if (!list2.isEmpty() && !list.isEmpty()) {
            throw new IllegalArgumentException("You can either use applicationInterceptors or applicationInterceptorFactories but not both at the same time.");
        }
        this.applicationInterceptors = list;
        this.applicationInterceptorFactories = list2;
        this.autoPersistedOperationsInterceptorFactory = apolloInterceptorFactory;
        this.enableAutoPersistedQueries = z;
        this.subscriptionManager = subscriptionManager;
        this.useHttpGetMethodForQueries = z2;
        this.useHttpGetMethodForPersistedQueries = z3;
        this.writeToNormalizedCacheAsynchronously = z4;
    }

    @Override // com.apollographql.apollo.ApolloMutationCall.Factory
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloMutationCall<T> mutate(Mutation<D, T, V> mutation) {
        return newCall(mutation).responseFetcher(ApolloResponseFetchers.NETWORK_ONLY);
    }

    @Override // com.apollographql.apollo.ApolloMutationCall.Factory
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloMutationCall<T> mutate(Mutation<D, T, V> mutation, D d) {
        Utils.checkNotNull(d, "withOptimisticUpdate == null");
        return newCall(mutation).toBuilder().responseFetcher(ApolloResponseFetchers.NETWORK_ONLY).optimisticUpdates(Optional.fromNullable(d)).build();
    }

    @Override // com.apollographql.apollo.ApolloQueryCall.Factory
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloQueryCall<T> query(Query<D, T, V> query) {
        return newCall(query);
    }

    @Override // com.apollographql.apollo.ApolloPrefetch.Factory
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloPrefetch prefetch(Operation<D, T, V> operation) {
        return new RealApolloPrefetch(operation, this.serverUrl, this.httpCallFactory, this.scalarTypeAdapters, this.dispatcher, this.logger, this.tracker);
    }

    @Override // com.apollographql.apollo.ApolloSubscriptionCall.Factory
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloSubscriptionCall<T> subscribe(Subscription<D, T, V> subscription) {
        return new RealApolloSubscriptionCall(subscription, this.subscriptionManager, this.apolloStore, ApolloSubscriptionCall.CachePolicy.NO_CACHE, this.dispatcher, this.responseFieldMapperFactory, this.logger);
    }

    public void addOnSubscriptionManagerStateChangeListener(OnSubscriptionManagerStateChangeListener onSubscriptionManagerStateChangeListener) {
        this.subscriptionManager.addOnStateChangeListener((OnSubscriptionManagerStateChangeListener) Utils.checkNotNull(onSubscriptionManagerStateChangeListener, "onStateChangeListener is null"));
    }

    public void removeOnSubscriptionManagerStateChangeListener(OnSubscriptionManagerStateChangeListener onSubscriptionManagerStateChangeListener) {
        this.subscriptionManager.removeOnStateChangeListener((OnSubscriptionManagerStateChangeListener) Utils.checkNotNull(onSubscriptionManagerStateChangeListener, "onStateChangeListener is null"));
    }

    public SubscriptionManagerState getSubscriptionManagerState() {
        return this.subscriptionManager.getState();
    }

    public SubscriptionManager getSubscriptionManager() {
        return this.subscriptionManager;
    }

    public void enableSubscriptions() {
        this.subscriptionManager.start();
    }

    public void disableSubscriptions() {
        this.subscriptionManager.stop();
    }

    @Deprecated
    public CacheHeaders defaultCacheHeaders() {
        return this.defaultCacheHeaders;
    }

    public CacheHeaders getDefaultCacheHeaders() {
        return this.defaultCacheHeaders;
    }

    public void clearHttpCache() {
        HttpCache httpCache = this.httpCache;
        if (httpCache != null) {
            httpCache.clear();
        }
    }

    public void clearNormalizedCache(ApolloStoreOperation.Callback<Boolean> callback) {
        Utils.checkNotNull(callback, "callback == null");
        this.apolloStore.clearAll().enqueue(callback);
    }

    public boolean clearNormalizedCache() {
        return this.apolloStore.clearAll().execute().booleanValue();
    }

    @Deprecated
    public ApolloStore apolloStore() {
        return this.apolloStore;
    }

    public ApolloStore getApolloStore() {
        return this.apolloStore;
    }

    public HttpUrl getServerUrl() {
        return this.serverUrl;
    }

    public HttpCache getHttpCache() {
        return this.httpCache;
    }

    public ScalarTypeAdapters getScalarTypeAdapters() {
        return this.scalarTypeAdapters;
    }

    public List<ApolloInterceptor> getApplicationInterceptors() {
        return Collections.unmodifiableList(this.applicationInterceptors);
    }

    public List<ApolloInterceptorFactory> getApplicationInterceptorFactories() {
        return Collections.unmodifiableList(this.applicationInterceptorFactories);
    }

    public ApolloInterceptorFactory getAutoPersistedOperationsInterceptorFactory() {
        return this.autoPersistedOperationsInterceptorFactory;
    }

    public void idleCallback(IdleResourceCallback idleResourceCallback) {
        this.tracker.setIdleResourceCallback(idleResourceCallback);
    }

    public int activeCallsCount() {
        return this.tracker.activeCallsCount();
    }

    public Builder newBuilder() {
        return new Builder();
    }

    Response cachedHttpResponse(String str) throws IOException {
        HttpCache httpCache = this.httpCache;
        if (httpCache != null) {
            return httpCache.read(str);
        }
        return null;
    }

    private <D extends Operation.Data, T, V extends Operation.Variables> RealApolloCall<T> newCall(Operation<D, T, V> operation) {
        return RealApolloCall.builder().operation(operation).serverUrl(this.serverUrl).httpCallFactory(this.httpCallFactory).httpCache(this.httpCache).httpCachePolicy(this.defaultHttpCachePolicy).responseFieldMapperFactory(this.responseFieldMapperFactory).scalarTypeAdapters(this.scalarTypeAdapters).apolloStore(this.apolloStore).responseFetcher(this.defaultResponseFetcher).cacheHeaders(this.defaultCacheHeaders).dispatcher(this.dispatcher).logger(this.logger).applicationInterceptors(this.applicationInterceptors).applicationInterceptorFactories(this.applicationInterceptorFactories).autoPersistedOperationsInterceptorFactory(this.autoPersistedOperationsInterceptorFactory).tracker(this.tracker).refetchQueries(Collections.emptyList()).refetchQueryNames(Collections.emptyList()).enableAutoPersistedQueries(this.enableAutoPersistedQueries).useHttpGetMethodForQueries(this.useHttpGetMethodForQueries).useHttpGetMethodForPersistedQueries(this.useHttpGetMethodForPersistedQueries).writeToNormalizedCacheAsynchronously(this.writeToNormalizedCacheAsynchronously).build();
    }

    public static class Builder {
        ApolloStore apolloStore;
        final List<ApolloInterceptorFactory> applicationInterceptorFactories;
        final List<ApolloInterceptor> applicationInterceptors;
        ApolloInterceptorFactory autoPersistedOperationsInterceptorFactory;
        Optional<NormalizedCacheFactory> cacheFactory;
        Optional<CacheKeyResolver> cacheKeyResolver;
        Call.Factory callFactory;
        final Map<ScalarType, CustomTypeAdapter<?>> customTypeAdapters;
        CacheHeaders defaultCacheHeaders;
        HttpCachePolicy.Policy defaultHttpCachePolicy;
        ResponseFetcher defaultResponseFetcher;
        Executor dispatcher;
        boolean enableAutoPersistedQueries;
        boolean enableAutoPersistedSubscriptions;
        HttpCache httpCache;
        Logger logger;
        HttpUrl serverUrl;
        SubscriptionConnectionParamsProvider subscriptionConnectionParams;
        long subscriptionHeartbeatTimeout;
        SubscriptionManager subscriptionManager;
        Optional<SubscriptionTransport.Factory> subscriptionTransportFactory;
        boolean useHttpGetMethodForPersistedQueries;
        boolean useHttpGetMethodForQueries;
        boolean writeToNormalizedCacheAsynchronously;

        Builder() {
            this.apolloStore = ApolloStore.NO_APOLLO_STORE;
            this.cacheFactory = Optional.absent();
            this.cacheKeyResolver = Optional.absent();
            this.defaultHttpCachePolicy = HttpCachePolicy.NETWORK_ONLY;
            this.defaultResponseFetcher = ApolloResponseFetchers.CACHE_FIRST;
            this.defaultCacheHeaders = CacheHeaders.NONE;
            this.customTypeAdapters = new LinkedHashMap();
            this.logger = null;
            this.applicationInterceptors = new ArrayList();
            this.applicationInterceptorFactories = new ArrayList();
            this.autoPersistedOperationsInterceptorFactory = null;
            this.subscriptionManager = new NoOpSubscriptionManager();
            this.subscriptionTransportFactory = Optional.absent();
            this.subscriptionConnectionParams = new SubscriptionConnectionParamsProvider.Const(new SubscriptionConnectionParams());
            this.subscriptionHeartbeatTimeout = -1L;
        }

        private Builder(ApolloClient apolloClient) {
            this.apolloStore = ApolloStore.NO_APOLLO_STORE;
            this.cacheFactory = Optional.absent();
            this.cacheKeyResolver = Optional.absent();
            this.defaultHttpCachePolicy = HttpCachePolicy.NETWORK_ONLY;
            this.defaultResponseFetcher = ApolloResponseFetchers.CACHE_FIRST;
            this.defaultCacheHeaders = CacheHeaders.NONE;
            this.customTypeAdapters = new LinkedHashMap();
            this.logger = null;
            this.applicationInterceptors = new ArrayList();
            this.applicationInterceptorFactories = new ArrayList();
            this.autoPersistedOperationsInterceptorFactory = null;
            this.subscriptionManager = new NoOpSubscriptionManager();
            this.subscriptionTransportFactory = Optional.absent();
            this.subscriptionConnectionParams = new SubscriptionConnectionParamsProvider.Const(new SubscriptionConnectionParams());
            this.subscriptionHeartbeatTimeout = -1L;
            this.callFactory = apolloClient.httpCallFactory;
            this.serverUrl = apolloClient.serverUrl;
            this.httpCache = apolloClient.httpCache;
            this.apolloStore = apolloClient.apolloStore;
            this.defaultHttpCachePolicy = apolloClient.defaultHttpCachePolicy;
            this.defaultResponseFetcher = apolloClient.defaultResponseFetcher;
            this.defaultCacheHeaders = apolloClient.defaultCacheHeaders;
            this.customTypeAdapters.putAll(apolloClient.scalarTypeAdapters.getCustomAdapters());
            this.dispatcher = apolloClient.dispatcher;
            this.logger = apolloClient.logger.getLogger();
            this.applicationInterceptors.addAll(apolloClient.applicationInterceptors);
            this.applicationInterceptorFactories.addAll(apolloClient.applicationInterceptorFactories);
            this.autoPersistedOperationsInterceptorFactory = apolloClient.getAutoPersistedOperationsInterceptorFactory();
            this.enableAutoPersistedQueries = apolloClient.enableAutoPersistedQueries;
            this.subscriptionManager = apolloClient.subscriptionManager;
            this.useHttpGetMethodForQueries = apolloClient.useHttpGetMethodForQueries;
            this.useHttpGetMethodForPersistedQueries = apolloClient.useHttpGetMethodForPersistedQueries;
            this.writeToNormalizedCacheAsynchronously = apolloClient.writeToNormalizedCacheAsynchronously;
        }

        public Builder okHttpClient(OkHttpClient okHttpClient) {
            return callFactory((Call.Factory) Utils.checkNotNull(okHttpClient, "okHttpClient is null"));
        }

        public Builder callFactory(Call.Factory factory) {
            this.callFactory = (Call.Factory) Utils.checkNotNull(factory, "factory == null");
            return this;
        }

        public Builder serverUrl(HttpUrl httpUrl) {
            this.serverUrl = (HttpUrl) Utils.checkNotNull(httpUrl, "serverUrl is null");
            return this;
        }

        public Builder serverUrl(String str) {
            this.serverUrl = HttpUrl.parse((String) Utils.checkNotNull(str, "serverUrl == null"));
            return this;
        }

        public Builder httpCache(HttpCache httpCache) {
            this.httpCache = (HttpCache) Utils.checkNotNull(httpCache, "httpCache == null");
            return this;
        }

        public Builder normalizedCache(NormalizedCacheFactory normalizedCacheFactory) {
            return normalizedCache(normalizedCacheFactory, CacheKeyResolver.DEFAULT);
        }

        public Builder normalizedCache(NormalizedCacheFactory normalizedCacheFactory, CacheKeyResolver cacheKeyResolver) {
            return normalizedCache(normalizedCacheFactory, cacheKeyResolver, false);
        }

        public Builder normalizedCache(NormalizedCacheFactory normalizedCacheFactory, CacheKeyResolver cacheKeyResolver, boolean z) {
            this.cacheFactory = Optional.fromNullable(Utils.checkNotNull(normalizedCacheFactory, "normalizedCacheFactory == null"));
            this.cacheKeyResolver = Optional.fromNullable(Utils.checkNotNull(cacheKeyResolver, "cacheKeyResolver == null"));
            this.writeToNormalizedCacheAsynchronously = z;
            return this;
        }

        public <T> Builder addCustomTypeAdapter(ScalarType scalarType, CustomTypeAdapter<T> customTypeAdapter) {
            this.customTypeAdapters.put(scalarType, customTypeAdapter);
            return this;
        }

        public Builder dispatcher(Executor executor) {
            this.dispatcher = (Executor) Utils.checkNotNull(executor, "dispatcher == null");
            return this;
        }

        public Builder defaultHttpCachePolicy(HttpCachePolicy.Policy policy) {
            this.defaultHttpCachePolicy = (HttpCachePolicy.Policy) Utils.checkNotNull(policy, "cachePolicy == null");
            return this;
        }

        public Builder defaultCacheHeaders(CacheHeaders cacheHeaders) {
            this.defaultCacheHeaders = (CacheHeaders) Utils.checkNotNull(cacheHeaders, "cacheHeaders == null");
            return this;
        }

        public Builder defaultResponseFetcher(ResponseFetcher responseFetcher) {
            this.defaultResponseFetcher = (ResponseFetcher) Utils.checkNotNull(responseFetcher, "defaultResponseFetcher == null");
            return this;
        }

        public Builder logger(Logger logger) {
            this.logger = logger;
            return this;
        }

        public Builder addApplicationInterceptor(ApolloInterceptor apolloInterceptor) {
            this.applicationInterceptors.add(apolloInterceptor);
            return this;
        }

        public Builder addApplicationInterceptorFactory(ApolloInterceptorFactory apolloInterceptorFactory) {
            this.applicationInterceptorFactories.add(apolloInterceptorFactory);
            return this;
        }

        public Builder setAutoPersistedOperationsInterceptorFactory(ApolloInterceptorFactory apolloInterceptorFactory) {
            this.autoPersistedOperationsInterceptorFactory = apolloInterceptorFactory;
            return this;
        }

        public Builder enableAutoPersistedQueries(boolean z) {
            this.enableAutoPersistedQueries = z;
            return this;
        }

        public Builder subscriptionTransportFactory(SubscriptionTransport.Factory factory) {
            this.subscriptionTransportFactory = Optional.of(Utils.checkNotNull(factory, "subscriptionTransportFactory is null"));
            return this;
        }

        public Builder subscriptionConnectionParams(SubscriptionConnectionParams subscriptionConnectionParams) {
            this.subscriptionConnectionParams = new SubscriptionConnectionParamsProvider.Const((SubscriptionConnectionParams) Utils.checkNotNull(subscriptionConnectionParams, "connectionParams is null"));
            return this;
        }

        public Builder subscriptionConnectionParams(SubscriptionConnectionParamsProvider subscriptionConnectionParamsProvider) {
            this.subscriptionConnectionParams = (SubscriptionConnectionParamsProvider) Utils.checkNotNull(subscriptionConnectionParamsProvider, "provider is null");
            return this;
        }

        public Builder subscriptionHeartbeatTimeout(long j, TimeUnit timeUnit) {
            Utils.checkNotNull(timeUnit, "timeUnit is null");
            this.subscriptionHeartbeatTimeout = Math.max(timeUnit.toMillis(j), TimeUnit.SECONDS.toMillis(10L));
            return this;
        }

        public Builder enableAutoPersistedSubscriptions(boolean z) {
            this.enableAutoPersistedSubscriptions = z;
            return this;
        }

        public Builder useHttpGetMethodForQueries(boolean z) {
            this.useHttpGetMethodForQueries = z;
            return this;
        }

        public Builder useHttpGetMethodForPersistedQueries(boolean z) {
            this.useHttpGetMethodForPersistedQueries = z;
            return this;
        }

        public ApolloClient build() {
            Utils.checkNotNull(this.serverUrl, "serverUrl is null");
            ApolloLogger apolloLogger = new ApolloLogger(this.logger);
            Call.Factory factoryAddHttpCacheInterceptorIfNeeded = this.callFactory;
            if (factoryAddHttpCacheInterceptorIfNeeded == null) {
                factoryAddHttpCacheInterceptorIfNeeded = new OkHttpClient();
            }
            HttpCache httpCache = this.httpCache;
            if (httpCache != null) {
                factoryAddHttpCacheInterceptorIfNeeded = addHttpCacheInterceptorIfNeeded(factoryAddHttpCacheInterceptorIfNeeded, httpCache.interceptor());
            }
            Executor executorDefaultDispatcher = this.dispatcher;
            if (executorDefaultDispatcher == null) {
                executorDefaultDispatcher = defaultDispatcher();
            }
            Executor executor = executorDefaultDispatcher;
            ScalarTypeAdapters scalarTypeAdapters = new ScalarTypeAdapters(Collections.unmodifiableMap(this.customTypeAdapters));
            ApolloStore apolloStore = this.apolloStore;
            Optional<NormalizedCacheFactory> optional = this.cacheFactory;
            Optional<CacheKeyResolver> optional2 = this.cacheKeyResolver;
            final ApolloStore realApolloStore = (optional.isPresent() && optional2.isPresent()) ? new RealApolloStore(optional.get().createChain(RecordFieldJsonAdapter.create()), optional2.get(), scalarTypeAdapters, executor, apolloLogger) : apolloStore;
            SubscriptionManager realSubscriptionManager = this.subscriptionManager;
            Optional<SubscriptionTransport.Factory> optional3 = this.subscriptionTransportFactory;
            if (optional3.isPresent()) {
                realSubscriptionManager = new RealSubscriptionManager(scalarTypeAdapters, optional3.get(), this.subscriptionConnectionParams, executor, this.subscriptionHeartbeatTimeout, new Function0<ResponseNormalizer<Map<String, Object>>>() { // from class: com.apollographql.apollo.ApolloClient.Builder.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public ResponseNormalizer<Map<String, Object>> invoke() {
                        return realApolloStore.networkResponseNormalizer();
                    }
                }, this.enableAutoPersistedSubscriptions);
            }
            return new ApolloClient(this.serverUrl, factoryAddHttpCacheInterceptorIfNeeded, httpCache, realApolloStore, scalarTypeAdapters, executor, this.defaultHttpCachePolicy, this.defaultResponseFetcher, this.defaultCacheHeaders, apolloLogger, Collections.unmodifiableList(this.applicationInterceptors), Collections.unmodifiableList(this.applicationInterceptorFactories), this.autoPersistedOperationsInterceptorFactory, this.enableAutoPersistedQueries, realSubscriptionManager, this.useHttpGetMethodForQueries, this.useHttpGetMethodForPersistedQueries, this.writeToNormalizedCacheAsynchronously);
        }

        private Executor defaultDispatcher() {
            return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactory() { // from class: com.apollographql.apollo.ApolloClient.Builder.2
                @Override // java.util.concurrent.ThreadFactory
                public Thread newThread(Runnable runnable) {
                    return new Thread(runnable, "Apollo Dispatcher");
                }
            });
        }

        private static Call.Factory addHttpCacheInterceptorIfNeeded(Call.Factory factory, Interceptor interceptor) {
            if (!(factory instanceof OkHttpClient)) {
                return factory;
            }
            OkHttpClient okHttpClient = (OkHttpClient) factory;
            Iterator<Interceptor> it = okHttpClient.interceptors().iterator();
            while (it.hasNext()) {
                if (it.next().getClass().equals(interceptor.getClass())) {
                    return factory;
                }
            }
            return okHttpClient.newBuilder().addInterceptor(interceptor).build();
        }
    }
}
