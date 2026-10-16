package com.nytimes.android.external.cache;

import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Equivalence<T> {
    protected abstract boolean doEquivalent(T t, T t2);

    protected abstract int doHash(T t);

    protected Equivalence() {
    }

    public final boolean equivalent(T t, T t2) {
        if (t == t2) {
            return true;
        }
        if (t == null || t2 == null) {
            return false;
        }
        return doEquivalent(t, t2);
    }

    public final int hash(@Nullable T t) {
        if (t == null) {
            return 0;
        }
        return doHash(t);
    }

    @Nonnull
    public static Equivalence<Object> equals() {
        return Equals.INSTANCE;
    }

    @Nonnull
    public static Equivalence<Object> identity() {
        return Identity.INSTANCE;
    }

    static final class Equals extends Equivalence<Object> implements Serializable {
        static final Equals INSTANCE = new Equals();
        private static final long serialVersionUID = 1;

        Equals() {
        }

        @Override // com.nytimes.android.external.cache.Equivalence
        protected boolean doEquivalent(@Nonnull Object obj, Object obj2) {
            return obj.equals(obj2);
        }

        @Override // com.nytimes.android.external.cache.Equivalence
        protected int doHash(@Nonnull Object obj) {
            return obj.hashCode();
        }

        @Nonnull
        private Object readResolve() {
            return INSTANCE;
        }
    }

    static final class Identity extends Equivalence<Object> implements Serializable {
        static final Identity INSTANCE = new Identity();
        private static final long serialVersionUID = 1;

        @Override // com.nytimes.android.external.cache.Equivalence
        protected boolean doEquivalent(Object obj, Object obj2) {
            return false;
        }

        Identity() {
        }

        @Override // com.nytimes.android.external.cache.Equivalence
        protected int doHash(Object obj) {
            return System.identityHashCode(obj);
        }

        @Nonnull
        private Object readResolve() {
            return INSTANCE;
        }
    }
}
