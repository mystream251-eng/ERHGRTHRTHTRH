package com.apollographql.apollo.api.cache.http;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpCachePolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\t\n\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo/api/cache/http/HttpCachePolicy;", "", "()V", "CACHE_FIRST", "Lcom/apollographql/apollo/api/cache/http/HttpCachePolicy$ExpirePolicy;", "CACHE_ONLY", "NETWORK_FIRST", "NETWORK_ONLY", "Lcom/apollographql/apollo/api/cache/http/HttpCachePolicy$Policy;", "ExpirePolicy", "FetchStrategy", "Policy", "apollo-http-cache-api"}, k = 1, mv = {1, 1, 16})
public final class HttpCachePolicy {
    public static final HttpCachePolicy INSTANCE = new HttpCachePolicy();
    public static final ExpirePolicy CACHE_ONLY = new ExpirePolicy(FetchStrategy.CACHE_ONLY);
    public static final Policy NETWORK_ONLY = new Policy(FetchStrategy.NETWORK_ONLY, 0, null, false);
    public static final ExpirePolicy CACHE_FIRST = new ExpirePolicy(FetchStrategy.CACHE_FIRST);
    public static final ExpirePolicy NETWORK_FIRST = new ExpirePolicy(FetchStrategy.NETWORK_FIRST);

    /* JADX INFO: compiled from: HttpCachePolicy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/api/cache/http/HttpCachePolicy$FetchStrategy;", "", "(Ljava/lang/String;I)V", "CACHE_ONLY", "NETWORK_ONLY", "CACHE_FIRST", "NETWORK_FIRST", "apollo-http-cache-api"}, k = 1, mv = {1, 1, 16})
    public enum FetchStrategy {
        CACHE_ONLY,
        NETWORK_ONLY,
        CACHE_FIRST,
        NETWORK_FIRST
    }

    private HttpCachePolicy() {
    }

    /* JADX INFO: compiled from: HttpCachePolicy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\u0005R\u0010\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo/api/cache/http/HttpCachePolicy$Policy;", "", "fetchStrategy", "Lcom/apollographql/apollo/api/cache/http/HttpCachePolicy$FetchStrategy;", "expireTimeout", "", "expireTimeUnit", "Ljava/util/concurrent/TimeUnit;", "expireAfterRead", "", "(Lcom/apollographql/apollo/api/cache/http/HttpCachePolicy$FetchStrategy;JLjava/util/concurrent/TimeUnit;Z)V", "expireTimeoutMs", "apollo-http-cache-api"}, k = 1, mv = {1, 1, 16})
    public static class Policy {
        public final boolean expireAfterRead;
        public final TimeUnit expireTimeUnit;
        public final long expireTimeout;
        public final FetchStrategy fetchStrategy;

        public Policy(FetchStrategy fetchStrategy, long j, TimeUnit timeUnit, boolean z) {
            Intrinsics.checkParameterIsNotNull(fetchStrategy, "fetchStrategy");
            this.fetchStrategy = fetchStrategy;
            this.expireTimeout = j;
            this.expireTimeUnit = timeUnit;
            this.expireAfterRead = z;
        }

        public final long expireTimeoutMs() {
            TimeUnit timeUnit = this.expireTimeUnit;
            if (timeUnit != null) {
                return timeUnit.toMillis(this.expireTimeout);
            }
            return 0L;
        }
    }

    /* JADX INFO: compiled from: HttpCachePolicy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B)\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\u0000¨\u0006\r"}, d2 = {"Lcom/apollographql/apollo/api/cache/http/HttpCachePolicy$ExpirePolicy;", "Lcom/apollographql/apollo/api/cache/http/HttpCachePolicy$Policy;", "fetchStrategy", "Lcom/apollographql/apollo/api/cache/http/HttpCachePolicy$FetchStrategy;", "(Lcom/apollographql/apollo/api/cache/http/HttpCachePolicy$FetchStrategy;)V", "expireTimeout", "", "expireTimeUnit", "Ljava/util/concurrent/TimeUnit;", "expireAfterRead", "", "(Lcom/apollographql/apollo/api/cache/http/HttpCachePolicy$FetchStrategy;JLjava/util/concurrent/TimeUnit;Z)V", "expireAfter", "apollo-http-cache-api"}, k = 1, mv = {1, 1, 16})
    public static final class ExpirePolicy extends Policy {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ExpirePolicy(FetchStrategy fetchStrategy) {
            super(fetchStrategy, 0L, null, false);
            Intrinsics.checkParameterIsNotNull(fetchStrategy, "fetchStrategy");
        }

        private ExpirePolicy(FetchStrategy fetchStrategy, long j, TimeUnit timeUnit, boolean z) {
            super(fetchStrategy, j, timeUnit, z);
        }

        public final ExpirePolicy expireAfter(long expireTimeout, TimeUnit expireTimeUnit) {
            Intrinsics.checkParameterIsNotNull(expireTimeUnit, "expireTimeUnit");
            return new ExpirePolicy(this.fetchStrategy, expireTimeout, expireTimeUnit, this.expireAfterRead);
        }

        public final ExpirePolicy expireAfterRead() {
            return new ExpirePolicy(this.fetchStrategy, this.expireTimeout, this.expireTimeUnit, true);
        }
    }
}
