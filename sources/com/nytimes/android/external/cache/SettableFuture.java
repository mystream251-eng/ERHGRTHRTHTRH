package com.nytimes.android.external.cache;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class SettableFuture<V> extends AbstractFuture.TrustedFuture<V> {
    @Nonnull
    public static <V> SettableFuture<V> create() {
        return new SettableFuture<>();
    }

    private SettableFuture() {
    }

    @Override // com.nytimes.android.external.cache.AbstractFuture
    public boolean set(V v) {
        return super.set(v);
    }

    @Override // com.nytimes.android.external.cache.AbstractFuture
    public boolean setException(Throwable th) {
        return super.setException(th);
    }

    @Override // com.nytimes.android.external.cache.AbstractFuture
    public boolean setFuture(@Nonnull ListenableFuture<? extends V> listenableFuture) {
        return super.setFuture(listenableFuture);
    }
}
