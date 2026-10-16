package com.apollographql.apollo.cache.normalized.lru;

import com.apollographql.apollo.cache.normalized.NormalizedCacheFactory;
import com.apollographql.apollo.cache.normalized.RecordFieldJsonAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LruNormalizedCacheFactory.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/lru/LruNormalizedCacheFactory;", "Lcom/apollographql/apollo/cache/normalized/NormalizedCacheFactory;", "Lcom/apollographql/apollo/cache/normalized/lru/LruNormalizedCache;", "evictionPolicy", "Lcom/apollographql/apollo/cache/normalized/lru/EvictionPolicy;", "(Lcom/apollographql/apollo/cache/normalized/lru/EvictionPolicy;)V", "create", "recordFieldAdapter", "Lcom/apollographql/apollo/cache/normalized/RecordFieldJsonAdapter;", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public final class LruNormalizedCacheFactory extends NormalizedCacheFactory<LruNormalizedCache> {
    private final EvictionPolicy evictionPolicy;

    public LruNormalizedCacheFactory(EvictionPolicy evictionPolicy) {
        Intrinsics.checkParameterIsNotNull(evictionPolicy, "evictionPolicy");
        this.evictionPolicy = evictionPolicy;
    }

    @Override // com.apollographql.apollo.cache.normalized.NormalizedCacheFactory
    public LruNormalizedCache create(RecordFieldJsonAdapter recordFieldAdapter) {
        Intrinsics.checkParameterIsNotNull(recordFieldAdapter, "recordFieldAdapter");
        return new LruNormalizedCache(this.evictionPolicy);
    }
}
