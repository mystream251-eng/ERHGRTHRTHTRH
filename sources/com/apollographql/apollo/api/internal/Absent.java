package com.apollographql.apollo.api.internal;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class Absent<T> extends Optional<T> {
    static final Absent<Object> INSTANCE = new Absent<>();
    private static final long serialVersionUID = 0;

    @Override // com.apollographql.apollo.api.internal.Optional
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public int hashCode() {
        return 2040732332;
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public boolean isPresent() {
        return false;
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public T orNull() {
        return null;
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public String toString() {
        return "Optional.absent()";
    }

    static <T> Optional<T> withType() {
        return INSTANCE;
    }

    private Absent() {
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public T or(T t) {
        return (T) Utils.checkNotNull(t, "use Optional.orNull() instead of Optional.or(null)");
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public Optional<T> or(Optional<? extends T> optional) {
        return (Optional) Utils.checkNotNull(optional);
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public <V> Optional<V> transform(Function<? super T, V> function) {
        Utils.checkNotNull(function);
        return Optional.absent();
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public <V> Optional<V> map(Function<? super T, V> function) {
        Utils.checkNotNull(function);
        return Optional.absent();
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public <V> Optional<V> flatMap(Function<? super T, Optional<V>> function) {
        Utils.checkNotNull(function);
        return Optional.absent();
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public Optional<T> apply(Action<T> action) {
        Utils.checkNotNull(action);
        return Optional.absent();
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public Set<T> asSet() {
        return Collections.emptySet();
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
