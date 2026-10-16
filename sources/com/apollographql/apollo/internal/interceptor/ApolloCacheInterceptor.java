package com.apollographql.apollo.internal.interceptor;

import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.api.internal.Function;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.ApolloCacheHeaders;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer;
import com.apollographql.apollo.cache.normalized.internal.Transaction;
import com.apollographql.apollo.cache.normalized.internal.WriteableStore;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class ApolloCacheInterceptor implements ApolloInterceptor {
    final ApolloStore apolloStore;
    private final Executor dispatcher;
    volatile boolean disposed;
    final ApolloLogger logger;
    private final ResponseFieldMapper responseFieldMapper;
    private final boolean writeToCacheAsynchronously;

    public ApolloCacheInterceptor(ApolloStore apolloStore, ResponseFieldMapper responseFieldMapper, Executor executor, ApolloLogger apolloLogger, boolean z) {
        this.apolloStore = (ApolloStore) Utils.checkNotNull(apolloStore, "cache == null");
        this.responseFieldMapper = (ResponseFieldMapper) Utils.checkNotNull(responseFieldMapper, "responseFieldMapper == null");
        this.dispatcher = (Executor) Utils.checkNotNull(executor, "dispatcher == null");
        this.logger = (ApolloLogger) Utils.checkNotNull(apolloLogger, "logger == null");
        this.writeToCacheAsynchronously = z;
    }

    @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
    public void interceptAsync(final ApolloInterceptor.InterceptorRequest interceptorRequest, final ApolloInterceptorChain apolloInterceptorChain, final Executor executor, final ApolloInterceptor.CallBack callBack) {
        executor.execute(new Runnable() { // from class: com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor.1
            @Override // java.lang.Runnable
            public void run() {
                if (ApolloCacheInterceptor.this.disposed) {
                    return;
                }
                if (interceptorRequest.fetchFromCache) {
                    callBack.onFetch(ApolloInterceptor.FetchSourceType.CACHE);
                    try {
                        callBack.onResponse(ApolloCacheInterceptor.this.resolveFromCache(interceptorRequest));
                        callBack.onCompleted();
                        return;
                    } catch (ApolloException e) {
                        callBack.onFailure(e);
                        return;
                    }
                }
                ApolloCacheInterceptor.this.writeOptimisticUpdatesAndPublish(interceptorRequest);
                apolloInterceptorChain.proceedAsync(interceptorRequest, executor, new ApolloInterceptor.CallBack() { // from class: com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor.1.1
                    @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                    public void onCompleted() {
                    }

                    @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                    public void onResponse(ApolloInterceptor.InterceptorResponse interceptorResponse) throws Exception {
                        if (ApolloCacheInterceptor.this.disposed) {
                            return;
                        }
                        ApolloCacheInterceptor.this.cacheResponseAndPublish(interceptorRequest, interceptorResponse, ApolloCacheInterceptor.this.writeToCacheAsynchronously);
                        callBack.onResponse(interceptorResponse);
                        callBack.onCompleted();
                    }

                    @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                    public void onFailure(ApolloException apolloException) {
                        ApolloCacheInterceptor.this.rollbackOptimisticUpdatesAndPublish(interceptorRequest);
                        callBack.onFailure(apolloException);
                    }

                    @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
                    public void onFetch(ApolloInterceptor.FetchSourceType fetchSourceType) {
                        callBack.onFetch(fetchSourceType);
                    }
                });
            }
        });
    }

    @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
    public void dispose() {
        this.disposed = true;
    }

    ApolloInterceptor.InterceptorResponse resolveFromCache(ApolloInterceptor.InterceptorRequest interceptorRequest) throws ApolloException {
        ResponseNormalizer<Record> responseNormalizerCacheResponseNormalizer = this.apolloStore.cacheResponseNormalizer();
        Response response = (Response) this.apolloStore.read(interceptorRequest.operation, this.responseFieldMapper, responseNormalizerCacheResponseNormalizer, interceptorRequest.cacheHeaders).execute();
        if (response.getData() != null) {
            this.logger.d("Cache HIT for operation %s", interceptorRequest.operation);
            return new ApolloInterceptor.InterceptorResponse(null, response, responseNormalizerCacheResponseNormalizer.records());
        }
        this.logger.d("Cache MISS for operation %s", interceptorRequest.operation);
        throw new ApolloException(String.format("Cache miss for operation %s", interceptorRequest.operation));
    }

    Set<String> cacheResponse(ApolloInterceptor.InterceptorResponse interceptorResponse, final ApolloInterceptor.InterceptorRequest interceptorRequest) {
        if (interceptorResponse.parsedResponse.isPresent() && interceptorResponse.parsedResponse.get().hasErrors() && !interceptorRequest.cacheHeaders.hasHeader(ApolloCacheHeaders.STORE_PARTIAL_RESPONSES)) {
            return Collections.emptySet();
        }
        final Optional<V> map = interceptorResponse.cacheRecords.map(new Function<Collection<Record>, List<Record>>() { // from class: com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor.2
            @Override // com.apollographql.apollo.api.internal.Function
            public List<Record> apply(Collection<Record> collection) {
                ArrayList arrayList = new ArrayList(collection.size());
                Iterator<Record> it = collection.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().toBuilder().mutationId(interceptorRequest.uniqueId).build());
                }
                return arrayList;
            }
        });
        if (!map.isPresent()) {
            return Collections.emptySet();
        }
        try {
            return (Set) this.apolloStore.writeTransaction(new Transaction<WriteableStore, Set<String>>() { // from class: com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor.3
                @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
                public Set<String> execute(WriteableStore writeableStore) {
                    return writeableStore.merge((Collection<Record>) map.get(), interceptorRequest.cacheHeaders);
                }
            });
        } catch (Exception e) {
            this.logger.e("Failed to cache operation response", e);
            return Collections.emptySet();
        }
    }

    void cacheResponseAndPublish(final ApolloInterceptor.InterceptorRequest interceptorRequest, final ApolloInterceptor.InterceptorResponse interceptorResponse, boolean z) throws Exception {
        if (z) {
            this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor.4
                @Override // java.lang.Runnable
                public void run() throws Exception {
                    ApolloCacheInterceptor.this.cacheResponseAndPublishSynchronously(interceptorRequest, interceptorResponse);
                }
            });
        } else {
            cacheResponseAndPublishSynchronously(interceptorRequest, interceptorResponse);
        }
    }

    void cacheResponseAndPublishSynchronously(ApolloInterceptor.InterceptorRequest interceptorRequest, ApolloInterceptor.InterceptorResponse interceptorResponse) throws Exception {
        try {
            Set<String> setCacheResponse = cacheResponse(interceptorResponse, interceptorRequest);
            Set<String> setRollbackOptimisticUpdates = rollbackOptimisticUpdates(interceptorRequest);
            HashSet hashSet = new HashSet();
            hashSet.addAll(setRollbackOptimisticUpdates);
            hashSet.addAll(setCacheResponse);
            publishCacheKeys(hashSet);
        } catch (Exception e) {
            rollbackOptimisticUpdatesAndPublish(interceptorRequest);
            throw e;
        }
    }

    void writeOptimisticUpdatesAndPublish(final ApolloInterceptor.InterceptorRequest interceptorRequest) {
        this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (interceptorRequest.optimisticUpdates.isPresent()) {
                        ApolloCacheInterceptor.this.apolloStore.writeOptimisticUpdatesAndPublish(interceptorRequest.operation, interceptorRequest.optimisticUpdates.get(), interceptorRequest.uniqueId).execute();
                    }
                } catch (Exception e) {
                    ApolloCacheInterceptor.this.logger.e(e, "failed to write operation optimistic updates, for: %s", interceptorRequest.operation);
                }
            }
        });
    }

    void rollbackOptimisticUpdatesAndPublish(final ApolloInterceptor.InterceptorRequest interceptorRequest) {
        this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ApolloCacheInterceptor.this.apolloStore.rollbackOptimisticUpdatesAndPublish(interceptorRequest.uniqueId).execute();
                } catch (Exception e) {
                    ApolloCacheInterceptor.this.logger.e(e, "failed to rollback operation optimistic updates, for: %s", interceptorRequest.operation);
                }
            }
        });
    }

    Set<String> rollbackOptimisticUpdates(ApolloInterceptor.InterceptorRequest interceptorRequest) {
        try {
            return this.apolloStore.rollbackOptimisticUpdates(interceptorRequest.uniqueId).execute();
        } catch (Exception e) {
            this.logger.e(e, "failed to rollback operation optimistic updates, for: %s", interceptorRequest.operation);
            return Collections.emptySet();
        }
    }

    void publishCacheKeys(final Set<String> set) {
        this.dispatcher.execute(new Runnable() { // from class: com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ApolloCacheInterceptor.this.apolloStore.publish(set);
                } catch (Exception e) {
                    ApolloCacheInterceptor.this.logger.e(e, "Failed to publish cache changes", new Object[0]);
                }
            }
        });
    }
}
