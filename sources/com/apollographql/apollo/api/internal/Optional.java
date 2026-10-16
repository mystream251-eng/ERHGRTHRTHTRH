package com.apollographql.apollo.api.internal;

import java.io.Serializable;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    public abstract Optional<T> apply(Action<T> action);

    public abstract Set<T> asSet();

    public abstract boolean equals(Object obj);

    public abstract <V> Optional<V> flatMap(Function<? super T, Optional<V>> function);

    public abstract T get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract <V> Optional<V> map(Function<? super T, V> function);

    public abstract Optional<T> or(Optional<? extends T> optional);

    public abstract T or(T t);

    public abstract T orNull();

    public abstract String toString();

    public abstract <V> Optional<V> transform(Function<? super T, V> function);

    public static <T> Optional<T> absent() {
        return Absent.withType();
    }

    public static <T> Optional<T> of(T t) {
        return new Present(Utils.checkNotNull(t));
    }

    public static <T> Optional<T> fromNullable(T t) {
        return t == null ? absent() : new Present(t);
    }

    Optional() {
    }
}
