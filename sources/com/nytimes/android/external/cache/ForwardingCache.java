package com.nytimes.android.external.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ForwardingCache<K, V> extends ForwardingObject implements Cache<K, V> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nytimes.android.external.cache.ForwardingObject
    public abstract Cache<K, V> delegate();

    protected ForwardingCache() {
    }

    @Override // com.nytimes.android.external.cache.Cache
    @Nullable
    public V getIfPresent(Object obj) {
        return delegate().getIfPresent(obj);
    }

    @Override // com.nytimes.android.external.cache.Cache
    @Nullable
    public V get(K k, Callable<? extends V> callable) throws ExecutionException {
        return delegate().get(k, callable);
    }

    @Override // com.nytimes.android.external.cache.Cache
    public Map<K, V> getAllPresent(Iterable<?> iterable) {
        return delegate().getAllPresent(iterable);
    }

    @Override // com.nytimes.android.external.cache.Cache
    public void put(K k, V v) {
        delegate().put(k, v);
    }

    @Override // com.nytimes.android.external.cache.Cache
    public void putAll(Map<? extends K, ? extends V> map) {
        delegate().putAll(map);
    }

    @Override // com.nytimes.android.external.cache.Cache
    public void invalidate(Object obj) {
        delegate().invalidate(obj);
    }

    @Override // com.nytimes.android.external.cache.Cache
    public void invalidateAll(Iterable<?> iterable) {
        delegate().invalidateAll(iterable);
    }

    @Override // com.nytimes.android.external.cache.Cache
    public void invalidateAll() {
        delegate().invalidateAll();
    }

    @Override // com.nytimes.android.external.cache.Cache
    public long size() {
        return delegate().size();
    }

    @Override // com.nytimes.android.external.cache.Cache
    public ConcurrentMap<K, V> asMap() {
        return delegate().asMap();
    }

    @Override // com.nytimes.android.external.cache.Cache
    public void cleanUp() {
        delegate().cleanUp();
    }
}
