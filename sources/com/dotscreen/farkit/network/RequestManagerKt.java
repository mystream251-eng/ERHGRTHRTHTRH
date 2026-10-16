package com.dotscreen.farkit.network;

import kotlin.Metadata;

/* JADX INFO: compiled from: RequestManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"defaultConnectionTimeout", "", "getDefaultConnectionTimeout", "()J", "setDefaultConnectionTimeout", "(J)V", "farkit_release"}, k = 2, mv = {1, 1, 16})
public final class RequestManagerKt {
    private static long defaultConnectionTimeout = 10000;

    public static final long getDefaultConnectionTimeout() {
        return defaultConnectionTimeout;
    }

    public static final void setDefaultConnectionTimeout(long j) {
        defaultConnectionTimeout = j;
    }
}
