package com.apollographql.apollo.cache.normalized;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.internal.NoOpApolloStore;
import com.apollographql.apollo.cache.normalized.internal.ReadableStore;
import com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer;
import com.apollographql.apollo.cache.normalized.internal.Transaction;
import com.apollographql.apollo.cache.normalized.internal.WriteableStore;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ApolloStore.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 D2\u00020\u0001:\u0002DEJ\b\u0010\u0002\u001a\u00020\u0003H&J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&J\u001a\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b0\u0005H&J\b\u0010\r\u001a\u00020\u000eH&J\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012H&JB\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00140\b\"\b\b\u0000\u0010\u0015*\u00020\u0016\"\u0004\b\u0001\u0010\u0014\"\b\b\u0002\u0010\u0017*\u00020\u00182\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00170\u001aH&Jl\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u001b0\b\"\b\b\u0000\u0010\u0015*\u00020\u0016\"\u0004\b\u0001\u0010\u0014\"\b\b\u0002\u0010\u0017*\u00020\u00182\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00170\u001a2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00150\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001f\u001a\u00020 H&J6\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H!0\b\"\b\b\u0000\u0010!*\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H!0\u001d2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0018H&J'\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u0002H(0*H&¢\u0006\u0002\u0010,J\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010$\u001a\u00020%H&J\u001e\u0010-\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010$\u001a\u00020%2\u0006\u0010.\u001a\u00020\tH&J\u001c\u0010-\u001a\b\u0012\u0004\u0012\u00020/0\b2\f\u00100\u001a\b\u0012\u0004\u0012\u00020%01H&J\u001c\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00120\b2\u0006\u00103\u001a\u000204H&J\u0016\u00105\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u00103\u001a\u000204H&J\u0010\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u000208H&J\u0010\u00109\u001a\u00020\u00102\u0006\u00107\u001a\u000208H&J,\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00120\b2\u0006\u0010;\u001a\u00020\"2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0018H&JU\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00120\b\"\b\b\u0000\u0010\u0015*\u00020\u0016\"\u0004\b\u0001\u0010\u0014\"\b\b\u0002\u0010\u0017*\u00020\u00182\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00170\u001a2\u0006\u0010<\u001a\u0002H\u0015H&¢\u0006\u0002\u0010=J&\u0010>\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010;\u001a\u00020\"2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0018H&JO\u0010>\u001a\b\u0012\u0004\u0012\u00020\t0\b\"\b\b\u0000\u0010\u0015*\u00020\u0016\"\u0004\b\u0001\u0010\u0014\"\b\b\u0002\u0010\u0017*\u00020\u00182\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00170\u001a2\u0006\u0010<\u001a\u0002H\u0015H&¢\u0006\u0002\u0010=J]\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00120\b\"\b\b\u0000\u0010\u0015*\u00020\u0016\"\u0004\b\u0001\u0010\u0014\"\b\b\u0002\u0010\u0017*\u00020\u00182\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00170\u001a2\u0006\u0010<\u001a\u0002H\u00152\u0006\u00103\u001a\u000204H&¢\u0006\u0002\u0010@JW\u0010A\u001a\b\u0012\u0004\u0012\u00020\t0\b\"\b\b\u0000\u0010\u0015*\u00020\u0016\"\u0004\b\u0001\u0010\u0014\"\b\b\u0002\u0010\u0017*\u00020\u00182\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00170\u001a2\u0006\u0010<\u001a\u0002H\u00152\u0006\u00103\u001a\u000204H&¢\u0006\u0002\u0010@J'\u0010B\u001a\u0002H(\"\u0004\b\u0000\u0010(2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u0002H(0*H&¢\u0006\u0002\u0010,\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001¨\u0006F"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/ApolloStore;", "", "cacheKeyResolver", "Lcom/apollographql/apollo/cache/normalized/CacheKeyResolver;", "cacheResponseNormalizer", "Lcom/apollographql/apollo/cache/normalized/internal/ResponseNormalizer;", "Lcom/apollographql/apollo/cache/normalized/Record;", "clearAll", "Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation;", "", "networkResponseNormalizer", "", "", "normalizedCache", "Lcom/apollographql/apollo/cache/normalized/NormalizedCache;", "publish", "", "keys", "", "read", ExifInterface.GPS_DIRECTION_TRUE, "D", "Lcom/apollographql/apollo/api/Operation$Data;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lcom/apollographql/apollo/api/Operation$Variables;", "operation", "Lcom/apollographql/apollo/api/Operation;", "Lcom/apollographql/apollo/api/Response;", "responseFieldMapper", "Lcom/apollographql/apollo/api/internal/ResponseFieldMapper;", "responseNormalizer", "cacheHeaders", "Lcom/apollographql/apollo/cache/CacheHeaders;", "F", "Lcom/apollographql/apollo/api/GraphqlFragment;", "fieldMapper", "cacheKey", "Lcom/apollographql/apollo/cache/normalized/CacheKey;", "variables", "readTransaction", "R", "transaction", "Lcom/apollographql/apollo/cache/normalized/internal/Transaction;", "Lcom/apollographql/apollo/cache/normalized/internal/ReadableStore;", "(Lcom/apollographql/apollo/cache/normalized/internal/Transaction;)Ljava/lang/Object;", "remove", "cascade", "", "cacheKeys", "", "rollbackOptimisticUpdates", "mutationId", "Ljava/util/UUID;", "rollbackOptimisticUpdatesAndPublish", "subscribe", "subscriber", "Lcom/apollographql/apollo/cache/normalized/ApolloStore$RecordChangeSubscriber;", "unsubscribe", "write", "fragment", "operationData", "(Lcom/apollographql/apollo/api/Operation;Lcom/apollographql/apollo/api/Operation$Data;)Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation;", "writeAndPublish", "writeOptimisticUpdates", "(Lcom/apollographql/apollo/api/Operation;Lcom/apollographql/apollo/api/Operation$Data;Ljava/util/UUID;)Lcom/apollographql/apollo/cache/normalized/ApolloStoreOperation;", "writeOptimisticUpdatesAndPublish", "writeTransaction", "Lcom/apollographql/apollo/cache/normalized/internal/WriteableStore;", "Companion", "RecordChangeSubscriber", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public interface ApolloStore {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final ApolloStore NO_APOLLO_STORE = new NoOpApolloStore();

    /* JADX INFO: compiled from: ApolloStore.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/ApolloStore$RecordChangeSubscriber;", "", "onCacheRecordsChanged", "", "changedRecordKeys", "", "", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
    public interface RecordChangeSubscriber {
        void onCacheRecordsChanged(Set<String> changedRecordKeys);
    }

    CacheKeyResolver cacheKeyResolver();

    ResponseNormalizer<Record> cacheResponseNormalizer();

    ApolloStoreOperation<Boolean> clearAll();

    ResponseNormalizer<Map<String, Object>> networkResponseNormalizer();

    NormalizedCache normalizedCache();

    void publish(Set<String> keys);

    <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<T> read(Operation<D, T, V> operation);

    <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Response<T>> read(Operation<D, T, V> operation, ResponseFieldMapper<D> responseFieldMapper, ResponseNormalizer<Record> responseNormalizer, CacheHeaders cacheHeaders);

    <F extends GraphqlFragment> ApolloStoreOperation<F> read(ResponseFieldMapper<F> fieldMapper, CacheKey cacheKey, Operation.Variables variables);

    <R> R readTransaction(Transaction<ReadableStore, R> transaction);

    ApolloStoreOperation<Boolean> remove(CacheKey cacheKey);

    ApolloStoreOperation<Boolean> remove(CacheKey cacheKey, boolean cascade);

    ApolloStoreOperation<Integer> remove(List<CacheKey> cacheKeys);

    ApolloStoreOperation<Set<String>> rollbackOptimisticUpdates(UUID mutationId);

    ApolloStoreOperation<Boolean> rollbackOptimisticUpdatesAndPublish(UUID mutationId);

    void subscribe(RecordChangeSubscriber subscriber);

    void unsubscribe(RecordChangeSubscriber subscriber);

    ApolloStoreOperation<Set<String>> write(GraphqlFragment fragment, CacheKey cacheKey, Operation.Variables variables);

    <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Set<String>> write(Operation<D, T, V> operation, D operationData);

    ApolloStoreOperation<Boolean> writeAndPublish(GraphqlFragment fragment, CacheKey cacheKey, Operation.Variables variables);

    <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Boolean> writeAndPublish(Operation<D, T, V> operation, D operationData);

    <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Set<String>> writeOptimisticUpdates(Operation<D, T, V> operation, D operationData, UUID mutationId);

    <D extends Operation.Data, T, V extends Operation.Variables> ApolloStoreOperation<Boolean> writeOptimisticUpdatesAndPublish(Operation<D, T, V> operation, D operationData, UUID mutationId);

    <R> R writeTransaction(Transaction<WriteableStore, R> transaction);

    /* JADX INFO: compiled from: ApolloStore.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000¨\u0006\u0001\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/ApolloStore$Companion;", "", "()V", "NO_APOLLO_STORE", "Lcom/apollographql/apollo/cache/normalized/ApolloStore;", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
