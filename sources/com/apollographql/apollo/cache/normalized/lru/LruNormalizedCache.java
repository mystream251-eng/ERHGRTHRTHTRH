package com.apollographql.apollo.cache.normalized.lru;

import com.apollographql.apollo.cache.ApolloCacheHeaders;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.CacheKey;
import com.apollographql.apollo.cache.normalized.CacheReference;
import com.apollographql.apollo.cache.normalized.NormalizedCache;
import com.apollographql.apollo.cache.normalized.Record;
import com.nytimes.android.external.cache.Cache;
import com.nytimes.android.external.cache.CacheBuilder;
import com.nytimes.android.external.cache.Weigher;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: LruNormalizedCache.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\r\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0002\b\fJ$\u0010\r\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u000e0\u000eH\u0016J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J(\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u00152\u0006\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0019H\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/lru/LruNormalizedCache;", "Lcom/apollographql/apollo/cache/normalized/NormalizedCache;", "evictionPolicy", "Lcom/apollographql/apollo/cache/normalized/lru/EvictionPolicy;", "(Lcom/apollographql/apollo/cache/normalized/lru/EvictionPolicy;)V", "lruCache", "Lcom/nytimes/android/external/cache/Cache;", "", "Lcom/apollographql/apollo/cache/normalized/Record;", "clearAll", "", "clearCurrentCache", "clearCurrentCache$apollo_normalized_cache", "dump", "", "Lkotlin/reflect/KClass;", "loadRecord", "key", "cacheHeaders", "Lcom/apollographql/apollo/cache/CacheHeaders;", "performMerge", "", "apolloRecord", "oldRecord", "remove", "", "cacheKey", "Lcom/apollographql/apollo/cache/normalized/CacheKey;", "cascade", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public final class LruNormalizedCache extends NormalizedCache {
    private final Cache<String, Record> lruCache;

    public LruNormalizedCache(EvictionPolicy evictionPolicy) {
        Intrinsics.checkParameterIsNotNull(evictionPolicy, "evictionPolicy");
        CacheBuilder<Object, Object> cacheBuilderNewBuilder = CacheBuilder.newBuilder();
        if (evictionPolicy.getMaxSizeBytes() != null) {
            cacheBuilderNewBuilder.maximumWeight(evictionPolicy.getMaxSizeBytes().longValue()).weigher(new Weigher<String, Record>() { // from class: com.apollographql.apollo.cache.normalized.lru.LruNormalizedCache$lruCache$1$1
                @Override // com.nytimes.android.external.cache.Weigher
                public final int weigh(String key, Record value) {
                    Intrinsics.checkParameterIsNotNull(key, "key");
                    Intrinsics.checkParameterIsNotNull(value, "value");
                    Charset charsetDefaultCharset = Charset.defaultCharset();
                    Intrinsics.checkExpressionValueIsNotNull(charsetDefaultCharset, "Charset.defaultCharset()");
                    byte[] bytes = key.getBytes(charsetDefaultCharset);
                    Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                    return bytes.length + value.sizeEstimateBytes();
                }
            });
        }
        if (evictionPolicy.getMaxEntries() != null) {
            cacheBuilderNewBuilder.maximumSize(evictionPolicy.getMaxEntries().longValue());
        }
        if (evictionPolicy.getExpireAfterAccess() != null) {
            long jLongValue = evictionPolicy.getExpireAfterAccess().longValue();
            TimeUnit expireAfterAccessTimeUnit = evictionPolicy.getExpireAfterAccessTimeUnit();
            if (expireAfterAccessTimeUnit == null) {
                Intrinsics.throwNpe();
            }
            cacheBuilderNewBuilder.expireAfterAccess(jLongValue, expireAfterAccessTimeUnit);
        }
        if (evictionPolicy.getExpireAfterWrite() != null) {
            long jLongValue2 = evictionPolicy.getExpireAfterWrite().longValue();
            TimeUnit expireAfterWriteTimeUnit = evictionPolicy.getExpireAfterWriteTimeUnit();
            if (expireAfterWriteTimeUnit == null) {
                Intrinsics.throwNpe();
            }
            cacheBuilderNewBuilder.expireAfterWrite(jLongValue2, expireAfterWriteTimeUnit);
        }
        Cache cacheBuild = cacheBuilderNewBuilder.build();
        Intrinsics.checkExpressionValueIsNotNull(cacheBuild, "CacheBuilder.newBuilder(…        }\n      }.build()");
        this.lruCache = cacheBuild;
    }

    @Override // com.apollographql.apollo.cache.normalized.NormalizedCache
    public Record loadRecord(final String key, final CacheHeaders cacheHeaders) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        try {
            Record record = this.lruCache.get(key, new Callable<Record>() { // from class: com.apollographql.apollo.cache.normalized.lru.LruNormalizedCache.loadRecord.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public final Record call() {
                    NormalizedCache nextCache = LruNormalizedCache.this.getNextCache();
                    if (nextCache != null) {
                        return nextCache.loadRecord(key, cacheHeaders);
                    }
                    return null;
                }
            });
            if (cacheHeaders.hasHeader(ApolloCacheHeaders.EVICT_AFTER_READ)) {
                this.lruCache.invalidate(key);
            }
            return record;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.apollographql.apollo.cache.normalized.NormalizedCache
    public void clearAll() {
        NormalizedCache nextCache = getNextCache();
        if (nextCache != null) {
            nextCache.clearAll();
        }
        clearCurrentCache$apollo_normalized_cache();
    }

    @Override // com.apollographql.apollo.cache.normalized.NormalizedCache
    public boolean remove(CacheKey cacheKey, boolean cascade) {
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        NormalizedCache nextCache = getNextCache();
        boolean zRemove = nextCache != null ? nextCache.remove(cacheKey, cascade) : false;
        Record ifPresent = this.lruCache.getIfPresent(cacheKey.getKey());
        if (ifPresent == null) {
            return zRemove;
        }
        this.lruCache.invalidate(cacheKey.getKey());
        if (!cascade) {
            return true;
        }
        while (true) {
            boolean z = true;
            for (CacheReference cacheReference : ifPresent.referencedFields()) {
                if (!z || !remove(new CacheKey(cacheReference.getKey()), true)) {
                    z = false;
                }
            }
            return z;
        }
    }

    public final void clearCurrentCache$apollo_normalized_cache() {
        this.lruCache.invalidateAll();
    }

    @Override // com.apollographql.apollo.cache.normalized.NormalizedCache
    protected Set<String> performMerge(Record apolloRecord, Record oldRecord, CacheHeaders cacheHeaders) {
        Intrinsics.checkParameterIsNotNull(apolloRecord, "apolloRecord");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        if (oldRecord == null) {
            this.lruCache.put(apolloRecord.getKey(), apolloRecord);
            return apolloRecord.keys();
        }
        Set<String> setMergeWith = oldRecord.mergeWith(apolloRecord);
        this.lruCache.put(apolloRecord.getKey(), oldRecord);
        return setMergeWith;
    }

    @Override // com.apollographql.apollo.cache.normalized.NormalizedCache
    public Map<KClass<?>, Map<String, Record>> dump() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(getClass());
        ConcurrentMap<String, Record> concurrentMapAsMap = this.lruCache.asMap();
        Intrinsics.checkExpressionValueIsNotNull(concurrentMapAsMap, "lruCache.asMap()");
        linkedHashMap.put(orCreateKotlinClass, concurrentMapAsMap);
        NormalizedCache nextCache = getNextCache();
        Map<KClass<?>, Map<String, Record>> mapDump = nextCache != null ? nextCache.dump() : null;
        if (mapDump == null) {
            mapDump = MapsKt.emptyMap();
        }
        linkedHashMap.putAll(mapDump);
        return linkedHashMap;
    }
}
