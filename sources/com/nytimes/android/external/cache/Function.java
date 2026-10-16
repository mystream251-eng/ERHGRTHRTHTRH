package com.nytimes.android.external.cache;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface Function<F, T> {
    @Nullable
    T apply(F f);

    boolean equals(Object obj);
}
