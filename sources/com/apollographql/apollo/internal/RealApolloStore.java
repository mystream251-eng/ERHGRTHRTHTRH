package com.apollographql.apollo.internal;

import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.ApolloStoreOperation;
import com.apollographql.apollo.cache.normalized.CacheKey;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.NormalizedCache;
import com.apollographql.apollo.cache.normalized.OptimisticNormalizedCache;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.cache.normalized.internal.CacheFieldValueResolver;
import com.apollographql.apollo.cache.normalized.internal.CacheKeyBuilder;
import com.apollographql.apollo.cache.normalized.internal.ReadableStore;
import com.apollographql.apollo.cache.normalized.internal.RealCacheKeyBuilder;
import com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer;
import com.apollographql.apollo.cache.normalized.internal.Transaction;
import com.apollographql.apollo.cache.normalized.internal.WriteableStore;
import com.apollographql.apollo.internal.response.RealResponseReader;
import com.apollographql.apollo.internal.response.RealResponseWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public final class RealApolloStore implements ApolloStore, ReadableStore, WriteableStore {
    private final CacheKeyBuilder cacheKeyBuilder;
    final CacheKeyResolver cacheKeyResolver;
    private final Executor dispatcher;
    private final ReadWriteLock lock;
    final ApolloLogger logger;
    final OptimisticNormalizedCache optimisticCache;
    final ScalarTypeAdapters scalarTypeAdapters;
    private final Set<ApolloStore.RecordChangeSubscriber> subscribers;

    public RealApolloStore(NormalizedCache normalizedCache, CacheKeyResolver cacheKeyResolver, ScalarTypeAdapters scalarTypeAdapters, Executor executor, ApolloLogger apolloLogger) {
        Utils.checkNotNull(normalizedCache, "cacheStore == null");
        this.optimisticCache = (OptimisticNormalizedCache) new OptimisticNormalizedCache().chain(normalizedCache);
        this.cacheKeyResolver = (CacheKeyResolver) Utils.checkNotNull(cacheKeyResolver, "cacheKeyResolver == null");
        this.scalarTypeAdapters = (ScalarTypeAdapters) Utils.checkNotNull(scalarTypeAdapters, "scalarTypeAdapters == null");
        this.dispatcher = (Executor) Utils.checkNotNull(executor, "dispatcher == null");
        this.logger = (ApolloLogger) Utils.checkNotNull(apolloLogger, "logger == null");
        this.lock = new ReentrantReadWriteLock();
        this.subscribers = Collections.newSetFromMap(new WeakHashMap());
        this.cacheKeyBuilder = new RealCacheKeyBuilder();
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ResponseNormalizer<Map<String, Object>> networkResponseNormalizer() {
        return new ResponseNormalizer<Map<String, Object>>() { // from class: com.apollographql.apollo.internal.RealApolloStore.1
            @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer
            public CacheKey resolveCacheKey(ResponseField responseField, Map<String, Object> map) {
                return RealApolloStore.this.cacheKeyResolver.fromFieldRecordSet(responseField, map);
            }

            @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer
            public CacheKeyBuilder cacheKeyBuilder() {
                return RealApolloStore.this.cacheKeyBuilder;
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ResponseNormalizer<Record> cacheResponseNormalizer() {
        return new ResponseNormalizer<Record>() { // from class: com.apollographql.apollo.internal.RealApolloStore.2
            @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer
            public CacheKey resolveCacheKey(ResponseField responseField, Record record) {
                return new CacheKey(record.key());
            }

            @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer
            public CacheKeyBuilder cacheKeyBuilder() {
                return RealApolloStore.this.cacheKeyBuilder;
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public synchronized void subscribe(ApolloStore.RecordChangeSubscriber recordChangeSubscriber) {
        this.subscribers.add(recordChangeSubscriber);
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public synchronized void unsubscribe(ApolloStore.RecordChangeSubscriber recordChangeSubscriber) {
        this.subscribers.remove(recordChangeSubscriber);
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public void publish(Set<String> set) {
        LinkedHashSet linkedHashSet;
        Utils.checkNotNull(set, "changedKeys == null");
        if (set.isEmpty()) {
            return;
        }
        synchronized (this) {
            linkedHashSet = new LinkedHashSet(this.subscribers);
        }
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            ((ApolloStore.RecordChangeSubscriber) it.next()).onCacheRecordsChanged(set);
        }
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Boolean> clearAll() {
        return new ApolloStoreOperation<Boolean>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public Boolean perform() {
                return (Boolean) RealApolloStore.this.writeTransaction(new Transaction<WriteableStore, Boolean>() { // from class: com.apollographql.apollo.internal.RealApolloStore.3.1
                    @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
                    public Boolean execute(WriteableStore writeableStore) {
                        RealApolloStore.this.optimisticCache.clearAll();
                        return Boolean.TRUE;
                    }
                });
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Boolean> remove(CacheKey cacheKey) {
        return remove(cacheKey, false);
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Boolean> remove(final CacheKey cacheKey, final boolean z) {
        Utils.checkNotNull(cacheKey, "cacheKey == null");
        return new ApolloStoreOperation<Boolean>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.4
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public Boolean perform() {
                return (Boolean) RealApolloStore.this.writeTransaction(new Transaction<WriteableStore, Boolean>() { // from class: com.apollographql.apollo.internal.RealApolloStore.4.1
                    @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
                    public Boolean execute(WriteableStore writeableStore) {
                        return Boolean.valueOf(RealApolloStore.this.optimisticCache.remove(cacheKey, z));
                    }
                });
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Integer> remove(final List<CacheKey> list) {
        Utils.checkNotNull(list, "cacheKey == null");
        return new ApolloStoreOperation<Integer>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.5
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public Integer perform() {
                return (Integer) RealApolloStore.this.writeTransaction(new Transaction<WriteableStore, Integer>() { // from class: com.apollographql.apollo.internal.RealApolloStore.5.1
                    @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
                    public Integer execute(WriteableStore writeableStore) {
                        Iterator it = list.iterator();
                        int i = 0;
                        while (it.hasNext()) {
                            if (RealApolloStore.this.optimisticCache.remove((CacheKey) it.next())) {
                                i++;
                            }
                        }
                        return Integer.valueOf(i);
                    }
                });
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <R> R readTransaction(Transaction<ReadableStore, R> transaction) {
        this.lock.readLock().lock();
        try {
            return transaction.execute(this);
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <R> R writeTransaction(Transaction<WriteableStore, R> transaction) {
        this.lock.writeLock().lock();
        try {
            return transaction.execute(this);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public NormalizedCache normalizedCache() {
        return this.optimisticCache;
    }

    @Override // com.apollographql.apollo.cache.normalized.internal.ReadableStore
    public Record read(String str, CacheHeaders cacheHeaders) {
        return this.optimisticCache.loadRecord((String) Utils.checkNotNull(str, "key == null"), cacheHeaders);
    }

    @Override // com.apollographql.apollo.cache.normalized.internal.ReadableStore
    public Collection<Record> read(Collection<String> collection, CacheHeaders cacheHeaders) {
        return this.optimisticCache.loadRecords((Collection) Utils.checkNotNull(collection, "keys == null"), cacheHeaders);
    }

    @Override // com.apollographql.apollo.cache.normalized.internal.WriteableStore
    public Set<String> merge(Collection<Record> collection, CacheHeaders cacheHeaders) {
        return this.optimisticCache.merge((Collection<Record>) Utils.checkNotNull(collection, "recordSet == null"), cacheHeaders);
    }

    @Override // com.apollographql.apollo.cache.normalized.internal.WriteableStore
    public Set<String> merge(Record record, CacheHeaders cacheHeaders) {
        return this.optimisticCache.merge((Record) Utils.checkNotNull(record, "record == null"), cacheHeaders);
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public CacheKeyResolver cacheKeyResolver() {
        return this.cacheKeyResolver;
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<T> read(final Operation<D, T, V> operation) {
        Utils.checkNotNull(operation, "operation == null");
        return new ApolloStoreOperation<T>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.6
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            protected T perform() {
                return (T) RealApolloStore.this.doRead(operation);
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Response<T>> read(final Operation<D, T, V> operation, final ResponseFieldMapper<D> responseFieldMapper, final ResponseNormalizer<Record> responseNormalizer, final CacheHeaders cacheHeaders) {
        Utils.checkNotNull(operation, "operation == null");
        Utils.checkNotNull(responseNormalizer, "responseNormalizer == null");
        return new ApolloStoreOperation<Response<T>>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public Response<T> perform() {
                return RealApolloStore.this.doRead(operation, responseFieldMapper, responseNormalizer, cacheHeaders);
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <F extends GraphqlFragment> ApolloStoreOperation<F> read(final ResponseFieldMapper<F> responseFieldMapper, final CacheKey cacheKey, final Operation.Variables variables) {
        Utils.checkNotNull(responseFieldMapper, "responseFieldMapper == null");
        Utils.checkNotNull(cacheKey, "cacheKey == null");
        Utils.checkNotNull(variables, "variables == null");
        return (ApolloStoreOperation<F>) new ApolloStoreOperation<F>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.8
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Incorrect return type in method signature: ()TF; */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public GraphqlFragment perform() {
                return RealApolloStore.this.doRead(responseFieldMapper, cacheKey, variables);
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Set<String>> write(final Operation<D, T, V> operation, final D d) {
        Utils.checkNotNull(operation, "operation == null");
        Utils.checkNotNull(d, "operationData == null");
        return new ApolloStoreOperation<Set<String>>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.9
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public Set<String> perform() {
                return RealApolloStore.this.doWrite(operation, d, false, null);
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Boolean> writeAndPublish(final Operation<D, T, V> operation, final D d) {
        return new ApolloStoreOperation<Boolean>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.10
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public Boolean perform() {
                RealApolloStore.this.publish(RealApolloStore.this.doWrite(operation, d, false, null));
                return Boolean.TRUE;
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Set<String>> write(final GraphqlFragment graphqlFragment, final CacheKey cacheKey, final Operation.Variables variables) {
        Utils.checkNotNull(graphqlFragment, "fragment == null");
        Utils.checkNotNull(cacheKey, "cacheKey == null");
        Utils.checkNotNull(variables, "operation == null");
        if (cacheKey.equals(CacheKey.NO_KEY)) {
            throw new IllegalArgumentException("undefined cache key");
        }
        return new ApolloStoreOperation<Set<String>>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.11
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public Set<String> perform() {
                return (Set) RealApolloStore.this.writeTransaction(new Transaction<WriteableStore, Set<String>>() { // from class: com.apollographql.apollo.internal.RealApolloStore.11.1
                    @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
                    public Set<String> execute(WriteableStore writeableStore) {
                        return RealApolloStore.this.doWrite(graphqlFragment, cacheKey, variables);
                    }
                });
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Boolean> writeAndPublish(final GraphqlFragment graphqlFragment, final CacheKey cacheKey, final Operation.Variables variables) {
        return new ApolloStoreOperation<Boolean>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.12
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public Boolean perform() {
                RealApolloStore.this.publish(RealApolloStore.this.doWrite(graphqlFragment, cacheKey, variables));
                return Boolean.TRUE;
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Set<String>> writeOptimisticUpdates(final Operation<D, T, V> operation, final D d, final UUID uuid) {
        return new ApolloStoreOperation<Set<String>>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.13
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public Set<String> perform() {
                return RealApolloStore.this.doWrite(operation, d, true, uuid);
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Boolean> writeOptimisticUpdatesAndPublish(final Operation<D, T, V> operation, final D d, final UUID uuid) {
        return new ApolloStoreOperation<Boolean>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.14
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public Boolean perform() {
                RealApolloStore.this.publish(RealApolloStore.this.doWrite(operation, d, true, uuid));
                return Boolean.TRUE;
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Set<String>> rollbackOptimisticUpdates(final UUID uuid) {
        return new ApolloStoreOperation<Set<String>>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.15
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public Set<String> perform() {
                return (Set) RealApolloStore.this.writeTransaction(new Transaction<WriteableStore, Set<String>>() { // from class: com.apollographql.apollo.internal.RealApolloStore.15.1
                    @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
                    public Set<String> execute(WriteableStore writeableStore) {
                        return RealApolloStore.this.optimisticCache.removeOptimisticUpdates(uuid);
                    }
                });
            }
        };
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Boolean> rollbackOptimisticUpdatesAndPublish(final UUID uuid) {
        return new ApolloStoreOperation<Boolean>(this.dispatcher) { // from class: com.apollographql.apollo.internal.RealApolloStore.16
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.cache.normalized.ApolloStoreOperation
            public Boolean perform() {
                RealApolloStore.this.publish((Set) RealApolloStore.this.writeTransaction(new Transaction<WriteableStore, Set<String>>() { // from class: com.apollographql.apollo.internal.RealApolloStore.16.1
                    @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
                    public Set<String> execute(WriteableStore writeableStore) {
                        return RealApolloStore.this.optimisticCache.removeOptimisticUpdates(uuid);
                    }
                }));
                return Boolean.TRUE;
            }
        };
    }

    <D extends Operation.Data, T, V extends Operation.Variables> T doRead(final Operation<D, T, V> operation) {
        return (T) readTransaction(new Transaction<ReadableStore, T>() { // from class: com.apollographql.apollo.internal.RealApolloStore.17
            @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
            public T execute(ReadableStore readableStore) {
                Record record = readableStore.read(CacheKeyResolver.rootKeyForOperation(operation).key(), CacheHeaders.NONE);
                if (record == null) {
                    return null;
                }
                return (T) operation.wrapData((Operation.Data) operation.responseFieldMapper().map(new RealResponseReader(operation.variables(), record, new CacheFieldValueResolver(readableStore, operation.variables(), RealApolloStore.this.cacheKeyResolver(), CacheHeaders.NONE, RealApolloStore.this.cacheKeyBuilder), RealApolloStore.this.scalarTypeAdapters, ResponseNormalizer.NO_OP_NORMALIZER)));
            }
        });
    }

    <D extends Operation.Data, T, V extends Operation.Variables> Response<T> doRead(final Operation<D, T, V> operation, final ResponseFieldMapper<D> responseFieldMapper, final ResponseNormalizer<Record> responseNormalizer, final CacheHeaders cacheHeaders) {
        return (Response) readTransaction(new Transaction<ReadableStore, Response<T>>() { // from class: com.apollographql.apollo.internal.RealApolloStore.18
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
            public Response<T> execute(ReadableStore readableStore) {
                Record record = readableStore.read(CacheKeyResolver.rootKeyForOperation(operation).key(), cacheHeaders);
                if (record == null) {
                    return Response.builder(operation).fromCache(true).build();
                }
                RealResponseReader realResponseReader = new RealResponseReader(operation.variables(), record, new CacheFieldValueResolver(readableStore, operation.variables(), RealApolloStore.this.cacheKeyResolver(), cacheHeaders, RealApolloStore.this.cacheKeyBuilder), RealApolloStore.this.scalarTypeAdapters, responseNormalizer);
                try {
                    responseNormalizer.willResolveRootQuery(operation);
                    return Response.builder(operation).data(operation.wrapData((Operation.Data) responseFieldMapper.map(realResponseReader))).fromCache(true).dependentKeys(responseNormalizer.dependentKeys()).build();
                } catch (Exception e) {
                    RealApolloStore.this.logger.e(e, "Failed to read cache response", new Object[0]);
                    return Response.builder(operation).fromCache(true).build();
                }
            }
        });
    }

    <F extends GraphqlFragment> F doRead(final ResponseFieldMapper<F> responseFieldMapper, final CacheKey cacheKey, final Operation.Variables variables) {
        return (F) readTransaction(new Transaction<ReadableStore, F>() { // from class: com.apollographql.apollo.internal.RealApolloStore.19
            /* JADX WARN: Incorrect return type in method signature: (Lcom/apollographql/apollo/cache/normalized/internal/ReadableStore;)TF; */
            @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
            public GraphqlFragment execute(ReadableStore readableStore) {
                Record record = readableStore.read(cacheKey.key(), CacheHeaders.NONE);
                if (record == null) {
                    return null;
                }
                return (GraphqlFragment) responseFieldMapper.map(new RealResponseReader(variables, record, new CacheFieldValueResolver(readableStore, variables, RealApolloStore.this.cacheKeyResolver(), CacheHeaders.NONE, RealApolloStore.this.cacheKeyBuilder), RealApolloStore.this.scalarTypeAdapters, ResponseNormalizer.NO_OP_NORMALIZER));
            }
        });
    }

    <D extends Operation.Data, T, V extends Operation.Variables> Set<String> doWrite(final Operation<D, T, V> operation, final D d, final boolean z, final UUID uuid) {
        return (Set) writeTransaction(new Transaction<WriteableStore, Set<String>>() { // from class: com.apollographql.apollo.internal.RealApolloStore.20
            @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
            public Set<String> execute(WriteableStore writeableStore) {
                RealResponseWriter realResponseWriter = new RealResponseWriter(operation.variables(), RealApolloStore.this.scalarTypeAdapters);
                d.marshaller().marshal(realResponseWriter);
                ResponseNormalizer<Map<String, Object>> responseNormalizerNetworkResponseNormalizer = RealApolloStore.this.networkResponseNormalizer();
                responseNormalizerNetworkResponseNormalizer.willResolveRootQuery(operation);
                realResponseWriter.resolveFields(responseNormalizerNetworkResponseNormalizer);
                if (z) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<Record> it = responseNormalizerNetworkResponseNormalizer.records().iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next().toBuilder().mutationId(uuid).build());
                    }
                    return RealApolloStore.this.optimisticCache.mergeOptimisticUpdates(arrayList);
                }
                return RealApolloStore.this.optimisticCache.merge(responseNormalizerNetworkResponseNormalizer.records(), CacheHeaders.NONE);
            }
        });
    }

    Set<String> doWrite(final GraphqlFragment graphqlFragment, final CacheKey cacheKey, final Operation.Variables variables) {
        return (Set) writeTransaction(new Transaction<WriteableStore, Set<String>>() { // from class: com.apollographql.apollo.internal.RealApolloStore.21
            @Override // com.apollographql.apollo.cache.normalized.internal.Transaction
            public Set<String> execute(WriteableStore writeableStore) {
                RealResponseWriter realResponseWriter = new RealResponseWriter(variables, RealApolloStore.this.scalarTypeAdapters);
                graphqlFragment.marshaller().marshal(realResponseWriter);
                ResponseNormalizer<Map<String, Object>> responseNormalizerNetworkResponseNormalizer = RealApolloStore.this.networkResponseNormalizer();
                responseNormalizerNetworkResponseNormalizer.willResolveRecord(cacheKey);
                realResponseWriter.resolveFields(responseNormalizerNetworkResponseNormalizer);
                return RealApolloStore.this.merge(responseNormalizerNetworkResponseNormalizer.records(), CacheHeaders.NONE);
            }
        });
    }
}
