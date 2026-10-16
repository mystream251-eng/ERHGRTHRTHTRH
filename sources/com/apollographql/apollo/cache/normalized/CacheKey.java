package com.apollographql.apollo.cache.normalized;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CacheKey.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0007J\b\u0010\f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/CacheKey;", "", "key", "", "(Ljava/lang/String;)V", "getKey", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "toString", "Companion", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public final class CacheKey {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final CacheKey NO_KEY = new CacheKey("");
    private final String key;

    @JvmStatic
    public static final CacheKey from(String str) {
        return INSTANCE.from(str);
    }

    public CacheKey(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        this.key = key;
    }

    public final String getKey() {
        return this.key;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "key", imports = {}))
    public final String key() {
        return this.key;
    }

    public boolean equals(Object other) {
        String str = this.key;
        if (!(other instanceof CacheKey)) {
            other = null;
        }
        CacheKey cacheKey = (CacheKey) other;
        return Intrinsics.areEqual(str, cacheKey != null ? cacheKey.key : null);
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public String toString() {
        return this.key;
    }

    /* JADX INFO: compiled from: CacheKey.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/CacheKey$Companion;", "", "()V", "NO_KEY", "Lcom/apollographql/apollo/cache/normalized/CacheKey;", "from", "key", "", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final CacheKey from(String key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return new CacheKey(key);
        }
    }
}
