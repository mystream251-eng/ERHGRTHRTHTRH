package com.apollographql.apollo.internal.util;

/* JADX INFO: loaded from: classes.dex */
public interface Cancelable {
    void cancel();

    boolean isCanceled();
}
