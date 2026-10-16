package com.nytimes.android.external.cache;

import java.util.NoSuchElementException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractSequentialIterator<T> extends UnmodifiableIterator<T> {

    @Nullable
    private T nextOrNull;

    @Nullable
    protected abstract T computeNext(T t);

    protected AbstractSequentialIterator(T t) {
        this.nextOrNull = t;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.nextOrNull != null;
    }

    @Override // java.util.Iterator
    @Nullable
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            return this.nextOrNull;
        } finally {
            this.nextOrNull = computeNext(this.nextOrNull);
        }
    }
}
