package com.dotscreen.farkit.tools;

import java.util.NoSuchElementException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class OptionalCompat<T> {
    private static final OptionalCompat<?> EMPTY = new OptionalCompat<>();
    private final T value;

    private OptionalCompat() {
        this.value = null;
    }

    public static <T> OptionalCompat<T> empty() {
        return (OptionalCompat<T>) EMPTY;
    }

    private OptionalCompat(T t) {
        this.value = (T) Objects.requireNonNull(t);
    }

    public static <T> OptionalCompat<T> of(T t) {
        return new OptionalCompat<>(t);
    }

    public static <T> OptionalCompat<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public T get() {
        T t = this.value;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("No value present");
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public T orElse(T t) {
        T t2 = this.value;
        return t2 != null ? t2 : t;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OptionalCompat) {
            return Objects.equals(this.value, ((OptionalCompat) obj).value);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    public String toString() {
        T t = this.value;
        return t != null ? String.format("Optional[%s]", t) : "Optional.empty";
    }
}
