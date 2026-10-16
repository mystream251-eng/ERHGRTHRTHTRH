package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ResponseField;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CacheKeyResolver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J)\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\r0\u000bH&¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/CacheKeyResolver;", "", "()V", "fromFieldArguments", "Lcom/apollographql/apollo/cache/normalized/CacheKey;", "field", "Lcom/apollographql/apollo/api/ResponseField;", "variables", "Lcom/apollographql/apollo/api/Operation$Variables;", "fromFieldRecordSet", "recordSet", "", "", "Lkotlin/jvm/JvmSuppressWildcards;", "Companion", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public abstract class CacheKeyResolver {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final CacheKey ROOT_CACHE_KEY = new CacheKey("QUERY_ROOT");
    public static final CacheKeyResolver DEFAULT = new CacheKeyResolver() { // from class: com.apollographql.apollo.cache.normalized.CacheKeyResolver$Companion$DEFAULT$1
        @Override // com.apollographql.apollo.cache.normalized.CacheKeyResolver
        public CacheKey fromFieldRecordSet(ResponseField field, Map<String, ? extends Object> recordSet) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(recordSet, "recordSet");
            return CacheKey.NO_KEY;
        }

        @Override // com.apollographql.apollo.cache.normalized.CacheKeyResolver
        public CacheKey fromFieldArguments(ResponseField field, Operation.Variables variables) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(variables, "variables");
            return CacheKey.NO_KEY;
        }
    };

    @JvmStatic
    public static final CacheKey rootKeyForOperation(Operation<?, ?, ?> operation) {
        return INSTANCE.rootKeyForOperation(operation);
    }

    public abstract CacheKey fromFieldArguments(ResponseField field, Operation.Variables variables);

    public abstract CacheKey fromFieldRecordSet(ResponseField field, Map<String, Object> recordSet);

    /* JADX INFO: compiled from: CacheKeyResolver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0007\u001a\u00020\u00062\u0012\u0010\b\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\tH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/CacheKeyResolver$Companion;", "", "()V", "DEFAULT", "Lcom/apollographql/apollo/cache/normalized/CacheKeyResolver;", "ROOT_CACHE_KEY", "Lcom/apollographql/apollo/cache/normalized/CacheKey;", "rootKeyForOperation", "operation", "Lcom/apollographql/apollo/api/Operation;", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final CacheKey rootKeyForOperation(Operation<?, ?, ?> operation) {
            Intrinsics.checkParameterIsNotNull(operation, "operation");
            return CacheKeyResolver.ROOT_CACHE_KEY;
        }
    }
}
