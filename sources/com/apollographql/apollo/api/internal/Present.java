package com.apollographql.apollo.api.internal;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class Present<T> extends Optional<T> {
    private static final long serialVersionUID = 0;
    private final T reference;

    @Override // com.apollographql.apollo.api.internal.Optional
    public boolean isPresent() {
        return true;
    }

    Present(T t) {
        this.reference = t;
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public T get() {
        return this.reference;
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public T or(T t) {
        Utils.checkNotNull(t, "use Optional.orNull() instead of Optional.or(null)");
        return this.reference;
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public Optional<T> or(Optional<? extends T> optional) {
        Utils.checkNotNull(optional);
        return this;
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public <V> Optional<V> transform(Function<? super T, V> function) {
        return new Present(Utils.checkNotNull(function.apply(this.reference), "the Function passed to Optional.transform() must not return null."));
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public <V> Optional<V> map(Function<? super T, V> function) {
        return new Present(Utils.checkNotNull(function.apply(this.reference), "the Function passed to Optional.map() must not return null."));
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public <V> Optional<V> flatMap(Function<? super T, Optional<V>> function) {
        Utils.checkNotNull(function);
        return (Optional) Utils.checkNotNull(function.apply(this.reference), "the Function passed to Optional.flatMap() must not return null.");
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public Optional<T> apply(final Action<T> action) {
        Utils.checkNotNull(action);
        return (Optional<T>) map(new Function<T, T>() { // from class: com.apollographql.apollo.api.internal.Present.1
            @Override // com.apollographql.apollo.api.internal.Function
            public T apply(T t) {
                action.apply(t);
                return t;
            }
        });
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public T orNull() {
        return this.reference;
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public Set<T> asSet() {
        return Collections.singleton(this.reference);
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public boolean equals(Object obj) {
        if (obj instanceof Present) {
            return this.reference.equals(((Present) obj).reference);
        }
        return false;
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    @Override // com.apollographql.apollo.api.internal.Optional
    public String toString() {
        return "Optional.of(" + this.reference + ")";
    }
}
