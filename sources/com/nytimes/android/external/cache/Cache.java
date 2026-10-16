package com.nytimes.android.external.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface Cache<K, V> {
    ConcurrentMap<K, V> asMap();

    void cleanUp();

    @Nullable
    V get(K k, Callable<? extends V> callable) throws ExecutionException;

    Map<K, V> getAllPresent(Iterable<?> iterable);

    @Nullable
    V getIfPresent(Object obj);

    void invalidate(Object obj);

    void invalidateAll();

    void invalidateAll(Iterable<?> iterable);

    void put(K k, V v);

    void putAll(Map<? extends K, ? extends V> map);

    long size();
}
