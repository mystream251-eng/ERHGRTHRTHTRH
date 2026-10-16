package com.apollographql.apollo.cache.normalized.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: SimpleStack.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\f\u001a\u00028\u0000¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\t\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/SimpleStack;", ExifInterface.LONGITUDE_EAST, "", "()V", "initialSize", "", "(I)V", "backing", "", "isEmpty", "", "()Z", "pop", "()Ljava/lang/Object;", "push", "", "element", "(Ljava/lang/Object;)V", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public final class SimpleStack<E> {
    private final List<E> backing;

    public SimpleStack() {
        this.backing = new ArrayList();
    }

    public SimpleStack(int i) {
        this.backing = new ArrayList(i);
    }

    public final void push(E element) {
        this.backing.add(element);
    }

    public final E pop() {
        if (!(!isEmpty())) {
            throw new IllegalStateException("Stack is empty.".toString());
        }
        List<E> list = this.backing;
        return list.remove(list.size() - 1);
    }

    public final boolean isEmpty() {
        return this.backing.isEmpty();
    }
}
