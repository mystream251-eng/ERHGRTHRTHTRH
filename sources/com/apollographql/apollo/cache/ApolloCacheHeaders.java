package com.apollographql.apollo.cache;

import kotlin.Metadata;

/* JADX INFO: compiled from: ApolloCacheHeaders.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/cache/ApolloCacheHeaders;", "", "()V", "DO_NOT_STORE", "", "EVICT_AFTER_READ", "STORE_PARTIAL_RESPONSES", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public final class ApolloCacheHeaders {
    public static final String DO_NOT_STORE = "do-not-store";
    public static final String EVICT_AFTER_READ = "evict-after-read";
    public static final ApolloCacheHeaders INSTANCE = new ApolloCacheHeaders();
    public static final String STORE_PARTIAL_RESPONSES = "store-partial-responses";

    private ApolloCacheHeaders() {
    }
}
