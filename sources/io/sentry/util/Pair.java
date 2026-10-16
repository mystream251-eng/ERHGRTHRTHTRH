package io.sentry.util;

/* JADX INFO: loaded from: classes2.dex */
public final class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A a2, B b) {
        this.first = a2;
        this.second = b;
    }

    public A getFirst() {
        return this.first;
    }

    public B getSecond() {
        return this.second;
    }
}
