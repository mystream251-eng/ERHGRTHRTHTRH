package com.dotscreen.bokit;

import kotlin.Metadata;

/* JADX INFO: compiled from: BOKit.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\b\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\"\u001a\u0010\u0006\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0004\b\b\u0010\u0005\"\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\"\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000e¨\u0006\u0012"}, d2 = {"defaultAiringsDaysCache", "", "getDefaultAiringsDaysCache", "()I", "setDefaultAiringsDaysCache", "(I)V", "defaultBitmapLRUCacheFactor", "getDefaultBitmapLRUCacheFactor", "setDefaultBitmapLRUCacheFactor", "defaultConnectionTimeout", "", "getDefaultConnectionTimeout", "()J", "setDefaultConnectionTimeout", "(J)V", "defaultGraphQLConnectionTimeout", "getDefaultGraphQLConnectionTimeout", "setDefaultGraphQLConnectionTimeout", "bokit-android_release"}, k = 2, mv = {1, 4, 1})
public final class BOKitKt {
    private static int defaultAiringsDaysCache = 5;
    private static int defaultBitmapLRUCacheFactor = 3;
    private static long defaultConnectionTimeout = 10000;
    private static long defaultGraphQLConnectionTimeout = 20000;

    public static final long getDefaultConnectionTimeout() {
        return defaultConnectionTimeout;
    }

    public static final void setDefaultConnectionTimeout(long j) {
        defaultConnectionTimeout = j;
    }

    public static final long getDefaultGraphQLConnectionTimeout() {
        return defaultGraphQLConnectionTimeout;
    }

    public static final void setDefaultGraphQLConnectionTimeout(long j) {
        defaultGraphQLConnectionTimeout = j;
    }

    public static final int getDefaultAiringsDaysCache() {
        return defaultAiringsDaysCache;
    }

    public static final void setDefaultAiringsDaysCache(int i) {
        defaultAiringsDaysCache = i;
    }

    public static final int getDefaultBitmapLRUCacheFactor() {
        return defaultBitmapLRUCacheFactor;
    }

    public static final void setDefaultBitmapLRUCacheFactor(int i) {
        defaultBitmapLRUCacheFactor = i;
    }
}
