package com.nytimes.android.external.cache;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ForwardingObject {
    protected abstract Object delegate();

    protected ForwardingObject() {
    }

    public String toString() {
        return delegate().toString();
    }
}
