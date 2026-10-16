package com.nytimes.android.external.cache;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CacheLoader<K, V> {
    @Nullable
    public abstract V load(K k) throws Exception;

    protected CacheLoader() {
    }

    @Nullable
    public ListenableFuture<V> reload(@Nonnull K k, @Nonnull V v) throws Exception {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        return Futures.immediateFuture(load(k));
    }

    @Nonnull
    public Map<K, V> loadAll(Iterable<? extends K> iterable) throws Exception {
        throw new UnsupportedLoadingOperationException();
    }

    @Nonnull
    public static <K, V> CacheLoader<K, V> from(@Nonnull Function<K, V> function) {
        return new FunctionToCacheLoader(function);
    }

    private static final class FunctionToCacheLoader<K, V> extends CacheLoader<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Function<K, V> computingFunction;

        public FunctionToCacheLoader(@Nonnull Function<K, V> function) {
            this.computingFunction = (Function) Preconditions.checkNotNull(function);
        }

        @Override // com.nytimes.android.external.cache.CacheLoader
        @Nullable
        public V load(@Nonnull K k) {
            return this.computingFunction.apply((K) Preconditions.checkNotNull(k));
        }
    }

    @Nonnull
    public static <V> CacheLoader<Object, V> from(@Nonnull Supplier<V> supplier) {
        return new SupplierToCacheLoader(supplier);
    }

    private static final class SupplierToCacheLoader<V> extends CacheLoader<Object, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Supplier<V> computingSupplier;

        public SupplierToCacheLoader(@Nonnull Supplier<V> supplier) {
            this.computingSupplier = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.nytimes.android.external.cache.CacheLoader
        @Nonnull
        public V load(@Nonnull Object obj) {
            Preconditions.checkNotNull(obj);
            return this.computingSupplier.get();
        }
    }

    public static final class UnsupportedLoadingOperationException extends UnsupportedOperationException {
        UnsupportedLoadingOperationException() {
        }
    }

    public static final class InvalidCacheLoadException extends RuntimeException {
        public InvalidCacheLoadException(String str) {
            super(str);
        }
    }
}
