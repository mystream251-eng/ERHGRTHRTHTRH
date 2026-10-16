package kotlinx.coroutines;

import kotlin.Metadata;

/* JADX INFO: compiled from: ThreadPoolDispatcher.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0007*\n\u0010\u0007\"\u00020\u00012\u00020\u0001¨\u0006\b"}, d2 = {"newFixedThreadPoolContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "nThreads", "", "name", "", "newSingleThreadContext", "SingleThreadDispatcher", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
public final class ThreadPoolDispatcherKt {
    public static final ExecutorCoroutineDispatcher newSingleThreadContext(String str) {
        return newFixedThreadPoolContext(1, str);
    }

    public static final ExecutorCoroutineDispatcher newFixedThreadPoolContext(int i, String str) {
        if (!(i >= 1)) {
            throw new IllegalArgumentException(("Expected at least one thread, but " + i + " specified").toString());
        }
        return new ThreadPoolDispatcher(i, str);
    }
}
