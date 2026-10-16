package com.apollographql.apollo.cache.normalized.internal;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.ApolloStoreOperation;
import com.apollographql.apollo.cache.normalized.CacheKey;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.NormalizedCache;
import com.apollographql.apollo.cache.normalized.Record;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoOpApolloStore.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00170\u00160\bH\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0016\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016JB\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u000b\"\b\b\u0000\u0010\u001f*\u00020 \"\u0004\b\u0001\u0010\u001e\"\b\b\u0002\u0010!*\u00020\"2\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!0$H\u0016Jl\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001e0%0\u000b\"\b\b\u0000\u0010\u001f*\u00020 \"\u0004\b\u0001\u0010\u001e\"\b\b\u0002\u0010!*\u00020\"2\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!0$2\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u001f0'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J6\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H)0\u000b\"\b\b\u0000\u0010)*\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H)0'2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\"H\u0016J\u001a\u0010\u001d\u001a\u0004\u0018\u00010\t2\u0006\u0010/\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J$\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u00142\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J'\u00100\u001a\u0002H1\"\u0004\b\u0000\u001012\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H103H\u0016¢\u0006\u0002\u00104J\u0016\u00105\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010,\u001a\u00020-H\u0016J\u001e\u00105\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010,\u001a\u00020-2\u0006\u00106\u001a\u00020\fH\u0016J\u001c\u00105\u001a\b\u0012\u0004\u0012\u0002070\u000b2\f\u00108\u001a\b\u0012\u0004\u0012\u00020-09H\u0016J\u001c\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u000b2\u0006\u0010;\u001a\u00020<H\u0016J\u0016\u0010=\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010;\u001a\u00020<H\u0016J\u0010\u0010>\u001a\u00020\u001b2\u0006\u0010?\u001a\u00020@H\u0016J\u0010\u0010A\u001a\u00020\u001b2\u0006\u0010?\u001a\u00020@H\u0016J,\u0010B\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u000b2\u0006\u0010C\u001a\u00020*2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\"H\u0016JU\u0010B\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u000b\"\b\b\u0000\u0010\u001f*\u00020 \"\u0004\b\u0001\u0010\u001e\"\b\b\u0002\u0010!*\u00020\"2\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!0$2\u0006\u0010D\u001a\u0002H\u001fH\u0016¢\u0006\u0002\u0010EJ&\u0010F\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010C\u001a\u00020*2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\"H\u0016JO\u0010F\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\"\b\b\u0000\u0010\u001f*\u00020 \"\u0004\b\u0001\u0010\u001e\"\b\b\u0002\u0010!*\u00020\"2\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!0$2\u0006\u0010D\u001a\u0002H\u001fH\u0016¢\u0006\u0002\u0010EJ]\u0010G\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u000b\"\b\b\u0000\u0010\u001f*\u00020 \"\u0004\b\u0001\u0010\u001e\"\b\b\u0002\u0010!*\u00020\"2\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!0$2\u0006\u0010D\u001a\u0002H\u001f2\u0006\u0010;\u001a\u00020<H\u0016¢\u0006\u0002\u0010HJW\u0010I\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\"\b\b\u0000\u0010\u001f*\u00020 \"\u0004\b\u0001\u0010\u001e\"\b\b\u0002\u0010!*\u00020\"2\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!0$2\u0006\u0010D\u001a\u0002H\u001f2\u0006\u0010;\u001a\u00020<H\u0016¢\u0006\u0002\u0010HJ'\u0010J\u001a\u0002H1\"\u0004\b\u0000\u001012\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H103H\u0016¢\u0006\u0002\u00104¨\u0006K"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/NoOpApolloStore;", "Lcom/apollographql/apollo/cache/normalized/ApolloStore;", "Lcom/apollographql/apollo/cache/normalized/internal/ReadableStore;", "Lcom/apollographql/apollo/cache/normalized/internal/WriteableStore;", "()V", "cacheKeyResolver", "Lcom/apollographql/apollo/cache/normalized/CacheKeyResolver;", "cacheResponseNormalizer", "Lcom/apollographql/apollo/cache/normalized/internal/ResponseNormalizer;", "Lcom/apollographql/apollo/cache/normalized/Record;", "clearAll", "Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation;", "", "merge", "", "", "record", "cacheHeaders", "Lcom/apollographql/apollo/cache/CacheHeaders;", "recordCollection", "", "networkResponseNormalizer", "", "", "normalizedCache", "Lcom/apollographql/apollo/cache/normalized/NormalizedCache;", "publish", "", "keys", "read", ExifInterface.GPS_DIRECTION_TRUE, "D", "Lcom/apollographql/apollo/api/Operation$Data;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lcom/apollographql/apollo/api/Operation$Variables;", "operation", "Lcom/apollographql/apollo/api/Operation;", "Lcom/apollographql/apollo/api/Response;", "responseFieldMapper", "Lcom/apollographql/apollo/api/internal/ResponseFieldMapper;", "responseNormalizer", "F", "Lcom/apollographql/apollo/api/GraphqlFragment;", "fieldMapper", "cacheKey", "Lcom/apollographql/apollo/cache/normalized/CacheKey;", "variables", "key", "readTransaction", "R", "transaction", "Lcom/apollographql/apollo/cache/normalized/internal/Transaction;", "(Lcom/apollographql/apollo/cache/normalized/internal/Transaction;)Ljava/lang/Object;", "remove", "cascade", "", "cacheKeys", "", "rollbackOptimisticUpdates", "mutationId", "Ljava/util/UUID;", "rollbackOptimisticUpdatesAndPublish", "subscribe", "subscriber", "Lcom/apollographql/apollo/cache/normalized/ApolloStore$RecordChangeSubscriber;", "unsubscribe", "write", "fragment", "operationData", "(Lcom/apollographql/apollo/api/Operation;Lcom/apollographql/apollo/api/Operation$Data;)Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation;", "writeAndPublish", "writeOptimisticUpdates", "(Lcom/apollographql/apollo/api/Operation;Lcom/apollographql/apollo/api/Operation$Data;Ljava/util/UUID;)Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation;", "writeOptimisticUpdatesAndPublish", "writeTransaction", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public final class NoOpApolloStore implements ApolloStore, ReadableStore, WriteableStore {
    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public void publish(Set<String> keys) {
        Intrinsics.checkParameterIsNotNull(keys, "keys");
    }

    @Override // com.apollographql.apollo.cache.normalized.internal.ReadableStore
    public Record read(String key, CacheHeaders cacheHeaders) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        return null;
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public void subscribe(ApolloStore.RecordChangeSubscriber subscriber) {
        Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public void unsubscribe(ApolloStore.RecordChangeSubscriber subscriber) {
        Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
    }

    @Override // com.apollographql.apollo.cache.normalized.internal.WriteableStore
    public Set<String> merge(Collection<Record> recordCollection, CacheHeaders cacheHeaders) {
        Intrinsics.checkParameterIsNotNull(recordCollection, "recordCollection");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        return SetsKt.emptySet();
    }

    @Override // com.apollographql.apollo.cache.normalized.internal.WriteableStore
    public Set<String> merge(Record record, CacheHeaders cacheHeaders) {
        Intrinsics.checkParameterIsNotNull(record, "record");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        return SetsKt.emptySet();
    }

    @Override // com.apollographql.apollo.cache.normalized.internal.ReadableStore
    public Collection<Record> read(Collection<String> keys, CacheHeaders cacheHeaders) {
        Intrinsics.checkParameterIsNotNull(keys, "keys");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        return SetsKt.emptySet();
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Boolean> clearAll() {
        ApolloStoreOperation.Companion companion = ApolloStoreOperation.INSTANCE;
        Boolean bool = Boolean.FALSE;
        Intrinsics.checkExpressionValueIsNotNull(bool, "java.lang.Boolean.FALSE");
        return companion.emptyOperation(bool);
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Boolean> remove(CacheKey cacheKey, boolean cascade) {
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        ApolloStoreOperation.Companion companion = ApolloStoreOperation.INSTANCE;
        Boolean bool = Boolean.FALSE;
        Intrinsics.checkExpressionValueIsNotNull(bool, "java.lang.Boolean.FALSE");
        return companion.emptyOperation(bool);
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Boolean> remove(CacheKey cacheKey) {
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        ApolloStoreOperation.Companion companion = ApolloStoreOperation.INSTANCE;
        Boolean bool = Boolean.FALSE;
        Intrinsics.checkExpressionValueIsNotNull(bool, "java.lang.Boolean.FALSE");
        return companion.emptyOperation(bool);
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Integer> remove(List<CacheKey> cacheKeys) {
        Intrinsics.checkParameterIsNotNull(cacheKeys, "cacheKeys");
        return ApolloStoreOperation.INSTANCE.emptyOperation(0);
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ResponseNormalizer<Map<String, Object>> networkResponseNormalizer() {
        ResponseNormalizer responseNormalizer = ResponseNormalizer.NO_OP_NORMALIZER;
        if (responseNormalizer != null) {
            return responseNormalizer;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer<kotlin.collections.Map<kotlin.String, kotlin.Any>>");
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ResponseNormalizer<Record> cacheResponseNormalizer() {
        ResponseNormalizer responseNormalizer = ResponseNormalizer.NO_OP_NORMALIZER;
        if (responseNormalizer != null) {
            return responseNormalizer;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer<com.apollographql.apollo.cache.normalized.Record>");
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <R> R readTransaction(Transaction<ReadableStore, R> transaction) {
        Intrinsics.checkParameterIsNotNull(transaction, "transaction");
        R rExecute = transaction.execute(this);
        if (rExecute == null) {
            Intrinsics.throwNpe();
        }
        return rExecute;
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <R> R writeTransaction(Transaction<WriteableStore, R> transaction) {
        Intrinsics.checkParameterIsNotNull(transaction, "transaction");
        R rExecute = transaction.execute(this);
        if (rExecute == null) {
            Intrinsics.throwNpe();
        }
        return rExecute;
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public NormalizedCache normalizedCache() {
        throw new IllegalStateException("Cannot get normalizedCache: no cache configured".toString());
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public CacheKeyResolver cacheKeyResolver() {
        throw new IllegalStateException("Cannot get cacheKeyResolver: no cache configured".toString());
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<T> read(Operation<D, T, V> operation) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        throw new IllegalStateException("Cannot read operation: no cache configured".toString());
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Response<T>> read(Operation<D, T, V> operation, ResponseFieldMapper<D> responseFieldMapper, ResponseNormalizer<Record> responseNormalizer, CacheHeaders cacheHeaders) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Intrinsics.checkParameterIsNotNull(responseFieldMapper, "responseFieldMapper");
        Intrinsics.checkParameterIsNotNull(responseNormalizer, "responseNormalizer");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        return ApolloStoreOperation.INSTANCE.emptyOperation(Response.INSTANCE.builder(operation).build());
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <F extends GraphqlFragment> ApolloStoreOperation<F> read(ResponseFieldMapper<F> fieldMapper, CacheKey cacheKey, Operation.Variables variables) {
        Intrinsics.checkParameterIsNotNull(fieldMapper, "fieldMapper");
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        Intrinsics.checkParameterIsNotNull(variables, "variables");
        throw new IllegalStateException("Cannot read fragment: no cache configured".toString());
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Set<String>> write(Operation<D, T, V> operation, D operationData) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Intrinsics.checkParameterIsNotNull(operationData, "operationData");
        return ApolloStoreOperation.INSTANCE.emptyOperation(SetsKt.emptySet());
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Boolean> writeAndPublish(Operation<D, T, V> operation, D operationData) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Intrinsics.checkParameterIsNotNull(operationData, "operationData");
        return ApolloStoreOperation.INSTANCE.emptyOperation(false);
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Set<String>> write(GraphqlFragment fragment, CacheKey cacheKey, Operation.Variables variables) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        Intrinsics.checkParameterIsNotNull(variables, "variables");
        return ApolloStoreOperation.INSTANCE.emptyOperation(SetsKt.emptySet());
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Boolean> writeAndPublish(GraphqlFragment fragment, CacheKey cacheKey, Operation.Variables variables) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        Intrinsics.checkParameterIsNotNull(variables, "variables");
        return ApolloStoreOperation.INSTANCE.emptyOperation(false);
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Set<String>> writeOptimisticUpdates(Operation<D, T, V> operation, D operationData, UUID mutationId) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Intrinsics.checkParameterIsNotNull(operationData, "operationData");
        Intrinsics.checkParameterIsNotNull(mutationId, "mutationId");
        return ApolloStoreOperation.INSTANCE.emptyOperation(SetsKt.emptySet());
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Boolean> writeOptimisticUpdatesAndPublish(Operation<D, T, V> operation, D operationData, UUID mutationId) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Intrinsics.checkParameterIsNotNull(operationData, "operationData");
        Intrinsics.checkParameterIsNotNull(mutationId, "mutationId");
        ApolloStoreOperation.Companion companion = ApolloStoreOperation.INSTANCE;
        Boolean bool = Boolean.FALSE;
        Intrinsics.checkExpressionValueIsNotNull(bool, "java.lang.Boolean.FALSE");
        return companion.emptyOperation(bool);
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Boolean> rollbackOptimisticUpdatesAndPublish(UUID mutationId) {
        Intrinsics.checkParameterIsNotNull(mutationId, "mutationId");
        ApolloStoreOperation.Companion companion = ApolloStoreOperation.INSTANCE;
        Boolean bool = Boolean.FALSE;
        Intrinsics.checkExpressionValueIsNotNull(bool, "java.lang.Boolean.FALSE");
        return companion.emptyOperation(bool);
    }

    @Override // com.apollographql.apollo.cache.normalized.ApolloStore
    public ApolloStoreOperation<Set<String>> rollbackOptimisticUpdates(UUID mutationId) {
        Intrinsics.checkParameterIsNotNull(mutationId, "mutationId");
        return ApolloStoreOperation.INSTANCE.emptyOperation(SetsKt.emptySet());
    }
}
