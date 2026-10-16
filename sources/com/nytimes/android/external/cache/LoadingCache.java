package com.nytimes.android.external.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface LoadingCache<K, V> extends Cache<K, V>, Function<K, V> {
    @Override // com.nytimes.android.external.cache.Function
    @Nullable
    @Deprecated
    V apply(K k);

    @Override // com.nytimes.android.external.cache.Cache
    ConcurrentMap<K, V> asMap();

    @Nullable
    V get(K k) throws ExecutionException;

    Map<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException;

    @Nullable
    V getUnchecked(K k);

    void refresh(K k);
}
