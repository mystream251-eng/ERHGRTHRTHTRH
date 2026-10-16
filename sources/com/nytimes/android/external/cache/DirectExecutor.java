package com.nytimes.android.external.cache;

import java.util.concurrent.Executor;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
enum DirectExecutor implements Executor {
    INSTANCE;

    @Override // java.lang.Enum
    public String toString() {
        return "MoreExecutors.directExecutor()";
    }

    @Override // java.util.concurrent.Executor
    public void execute(@Nonnull Runnable runnable) {
        runnable.run();
    }
}
