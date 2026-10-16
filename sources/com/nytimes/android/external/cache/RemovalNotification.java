package com.nytimes.android.external.cache;

import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class RemovalNotification<K, V> implements Map.Entry<K, V> {

    @Nullable
    private final RemovalCause cause;
    private final K key;
    private final V value;

    @Nonnull
    public static <K, V> RemovalNotification<K, V> create(K k, V v, @Nonnull RemovalCause removalCause) {
        return new RemovalNotification<>(k, v, removalCause);
    }

    private RemovalNotification(K k, V v, @Nonnull RemovalCause removalCause) {
        this.key = k;
        this.value = v;
        this.cause = (RemovalCause) Preconditions.checkNotNull(removalCause);
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    @Nonnull
    public final V setValue(V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return Objects.equal(getKey(), entry.getKey()) && Objects.equal(getValue(), entry.getValue());
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        K key = getKey();
        V value = getValue();
        return (key == null ? 0 : key.hashCode()) ^ (value != null ? value.hashCode() : 0);
    }

    @Nonnull
    public String toString() {
        return getKey() + "=" + getValue();
    }
}
