package com.apollographql.apollo.api.cache.http;

import java.io.IOException;
import kotlin.Metadata;
import okhttp3.Interceptor;
import okhttp3.Response;

/* JADX INFO: compiled from: HttpCache.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eJ\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&J\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo/api/cache/http/HttpCache;", "", "clear", "", "interceptor", "Lokhttp3/Interceptor;", "read", "Lokhttp3/Response;", "cacheKey", "", "expireAfterRead", "", "remove", "removeQuietly", "Companion", "apollo-http-cache-api"}, k = 1, mv = {1, 1, 16})
public interface HttpCache {
    public static final String CACHE_DO_NOT_STORE = "X-APOLLO-CACHE-DO-NOT-STORE";
    public static final String CACHE_EXPIRE_AFTER_READ_HEADER = "X-APOLLO-EXPIRE-AFTER-READ";
    public static final String CACHE_EXPIRE_TIMEOUT_HEADER = "X-APOLLO-EXPIRE-TIMEOUT";
    public static final String CACHE_FETCH_STRATEGY_HEADER = "X-APOLLO-CACHE-FETCH-STRATEGY";
    public static final String CACHE_KEY_HEADER = "X-APOLLO-CACHE-KEY";
    public static final String CACHE_PREFETCH_HEADER = "X-APOLLO-PREFETCH";
    public static final String CACHE_SERVED_DATE_HEADER = "X-APOLLO-SERVED-DATE";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final String FROM_CACHE = "X-APOLLO-FROM-CACHE";

    void clear();

    Interceptor interceptor();

    Response read(String cacheKey);

    Response read(String cacheKey, boolean expireAfterRead);

    void remove(String cacheKey) throws IOException;

    void removeQuietly(String cacheKey);

    /* JADX INFO: compiled from: HttpCache.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo/api/cache/http/HttpCache$Companion;", "", "()V", "CACHE_DO_NOT_STORE", "", "CACHE_EXPIRE_AFTER_READ_HEADER", "CACHE_EXPIRE_TIMEOUT_HEADER", "CACHE_FETCH_STRATEGY_HEADER", "CACHE_KEY_HEADER", "CACHE_PREFETCH_HEADER", "CACHE_SERVED_DATE_HEADER", "FROM_CACHE", "apollo-http-cache-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String CACHE_DO_NOT_STORE = "X-APOLLO-CACHE-DO-NOT-STORE";
        public static final String CACHE_EXPIRE_AFTER_READ_HEADER = "X-APOLLO-EXPIRE-AFTER-READ";
        public static final String CACHE_EXPIRE_TIMEOUT_HEADER = "X-APOLLO-EXPIRE-TIMEOUT";
        public static final String CACHE_FETCH_STRATEGY_HEADER = "X-APOLLO-CACHE-FETCH-STRATEGY";
        public static final String CACHE_KEY_HEADER = "X-APOLLO-CACHE-KEY";
        public static final String CACHE_PREFETCH_HEADER = "X-APOLLO-PREFETCH";
        public static final String CACHE_SERVED_DATE_HEADER = "X-APOLLO-SERVED-DATE";
        public static final String FROM_CACHE = "X-APOLLO-FROM-CACHE";

        private Companion() {
        }
    }
}
