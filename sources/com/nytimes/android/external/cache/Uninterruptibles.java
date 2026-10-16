package com.nytimes.android.external.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class Uninterruptibles {
    public static <V> V getUninterruptibly(@Nonnull Future<V> future) throws ExecutionException {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    private Uninterruptibles() {
    }
}
