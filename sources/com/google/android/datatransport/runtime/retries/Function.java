package com.google.android.datatransport.runtime.retries;

import java.lang.Throwable;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
public interface Function<TInput, TResult, TException extends Throwable> {
    TResult apply(TInput tinput) throws Throwable;
}
