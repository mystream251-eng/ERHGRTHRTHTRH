package com.apollographql.apollo.cache.normalized;

import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

/* JADX INFO: compiled from: CacheReference.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0007J\u0006\u0010\f\u001a\u00020\u0003J\b\u0010\r\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/CacheReference;", "", "key", "", "(Ljava/lang/String;)V", "getKey", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "serialize", "toString", "Companion", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public final class CacheReference {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Regex SERIALIZATION_REGEX_PATTERN = new Regex("ApolloCacheReference\\{(.*)\\}");
    private static final String SERIALIZATION_TEMPLATE = "ApolloCacheReference";
    private final String key;

    @JvmStatic
    public static final boolean canDeserialize(String str) {
        return INSTANCE.canDeserialize(str);
    }

    @JvmStatic
    public static final CacheReference deserialize(String str) {
        return INSTANCE.deserialize(str);
    }

    public CacheReference(String key) {
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
        if (!(other instanceof CacheReference)) {
            other = null;
        }
        CacheReference cacheReference = (CacheReference) other;
        return Intrinsics.areEqual(str, cacheReference != null ? cacheReference.key : null);
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public String toString() {
        return this.key;
    }

    public final String serialize() {
        return "ApolloCacheReference{" + this.key + '}';
    }

    /* JADX INFO: compiled from: CacheReference.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/CacheReference$Companion;", "", "()V", "SERIALIZATION_REGEX_PATTERN", "Lkotlin/text/Regex;", "SERIALIZATION_TEMPLATE", "", "canDeserialize", "", "value", "deserialize", "Lcom/apollographql/apollo/cache/normalized/CacheReference;", "serializedCacheReference", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final CacheReference deserialize(String serializedCacheReference) {
            Intrinsics.checkParameterIsNotNull(serializedCacheReference, "serializedCacheReference");
            MatchResult matchResultMatchEntire = CacheReference.SERIALIZATION_REGEX_PATTERN.matchEntire(serializedCacheReference);
            List<String> groupValues = matchResultMatchEntire != null ? matchResultMatchEntire.getGroupValues() : null;
            if (!(groupValues != null && groupValues.size() > 1)) {
                throw new IllegalArgumentException(("Not a cache reference: " + serializedCacheReference + " Must be of the form: ApolloCacheReference{%s}").toString());
            }
            return new CacheReference(groupValues.get(1));
        }

        @JvmStatic
        public final boolean canDeserialize(String value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            return CacheReference.SERIALIZATION_REGEX_PATTERN.matches(value);
        }
    }
}
