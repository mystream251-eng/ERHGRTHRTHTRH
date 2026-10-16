package com.apollographql.apollo.cache.normalized.lru;

import com.apollographql.apollo.api.internal.Optional;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EvictionPolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016BO\b\u0000\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\nJ\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014H\u0007J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014H\u0007J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014H\u0007J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014H\u0007J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014H\u0007J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014H\u0007R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0010\u0010\fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0012\u0010\fR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0013\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/lru/EvictionPolicy;", "", "maxSizeBytes", "", "maxEntries", "expireAfterAccess", "expireAfterAccessTimeUnit", "Ljava/util/concurrent/TimeUnit;", "expireAfterWrite", "expireAfterWriteTimeUnit", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;)V", "getExpireAfterAccess", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getExpireAfterAccessTimeUnit", "()Ljava/util/concurrent/TimeUnit;", "getExpireAfterWrite", "getExpireAfterWriteTimeUnit", "getMaxEntries", "getMaxSizeBytes", "Lcom/apollographql/apollo/api/internal/Optional;", "Builder", "Companion", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public final class EvictionPolicy {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final EvictionPolicy NO_EVICTION;
    private final Long expireAfterAccess;
    private final TimeUnit expireAfterAccessTimeUnit;
    private final Long expireAfterWrite;
    private final TimeUnit expireAfterWriteTimeUnit;
    private final Long maxEntries;
    private final Long maxSizeBytes;

    public EvictionPolicy() {
        this(null, null, null, null, null, null, 63, null);
    }

    @JvmStatic
    public static final Builder builder() {
        return INSTANCE.builder();
    }

    public EvictionPolicy(Long l, Long l2, Long l3, TimeUnit timeUnit, Long l4, TimeUnit timeUnit2) {
        this.maxSizeBytes = l;
        this.maxEntries = l2;
        this.expireAfterAccess = l3;
        this.expireAfterAccessTimeUnit = timeUnit;
        this.expireAfterWrite = l4;
        this.expireAfterWriteTimeUnit = timeUnit2;
    }

    public /* synthetic */ EvictionPolicy(Long l, Long l2, Long l3, TimeUnit timeUnit, Long l4, TimeUnit timeUnit2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Long) null : l, (i & 2) != 0 ? (Long) null : l2, (i & 4) != 0 ? (Long) null : l3, (i & 8) != 0 ? (TimeUnit) null : timeUnit, (i & 16) != 0 ? (Long) null : l4, (i & 32) != 0 ? (TimeUnit) null : timeUnit2);
    }

    public final Long getMaxSizeBytes() {
        return this.maxSizeBytes;
    }

    public final Long getMaxEntries() {
        return this.maxEntries;
    }

    public final Long getExpireAfterAccess() {
        return this.expireAfterAccess;
    }

    public final TimeUnit getExpireAfterAccessTimeUnit() {
        return this.expireAfterAccessTimeUnit;
    }

    public final Long getExpireAfterWrite() {
        return this.expireAfterWrite;
    }

    public final TimeUnit getExpireAfterWriteTimeUnit() {
        return this.expireAfterWriteTimeUnit;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "maxSizeBytes", imports = {}))
    public final Optional<Long> maxSizeBytes() {
        Optional<Long> optionalFromNullable = Optional.fromNullable(this.maxSizeBytes);
        Intrinsics.checkExpressionValueIsNotNull(optionalFromNullable, "Optional.fromNullable(maxSizeBytes)");
        return optionalFromNullable;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "maxEntries", imports = {}))
    public final Optional<Long> maxEntries() {
        Optional<Long> optionalFromNullable = Optional.fromNullable(this.maxEntries);
        Intrinsics.checkExpressionValueIsNotNull(optionalFromNullable, "Optional.fromNullable(maxEntries)");
        return optionalFromNullable;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "expireAfterAccess", imports = {}))
    public final Optional<Long> expireAfterAccess() {
        Optional<Long> optionalFromNullable = Optional.fromNullable(this.expireAfterAccess);
        Intrinsics.checkExpressionValueIsNotNull(optionalFromNullable, "Optional.fromNullable(expireAfterAccess)");
        return optionalFromNullable;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "expireAfterAccessTimeUnit", imports = {}))
    public final Optional<TimeUnit> expireAfterAccessTimeUnit() {
        Optional<TimeUnit> optionalFromNullable = Optional.fromNullable(this.expireAfterAccessTimeUnit);
        Intrinsics.checkExpressionValueIsNotNull(optionalFromNullable, "Optional.fromNullable(expireAfterAccessTimeUnit)");
        return optionalFromNullable;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "expireAfterWrite", imports = {}))
    public final Optional<Long> expireAfterWrite() {
        Optional<Long> optionalFromNullable = Optional.fromNullable(this.expireAfterWrite);
        Intrinsics.checkExpressionValueIsNotNull(optionalFromNullable, "Optional.fromNullable(expireAfterWrite)");
        return optionalFromNullable;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "expireAfterWriteTimeUnit", imports = {}))
    public final Optional<TimeUnit> expireAfterWriteTimeUnit() {
        Optional<TimeUnit> optionalFromNullable = Optional.fromNullable(this.expireAfterWriteTimeUnit);
        Intrinsics.checkExpressionValueIsNotNull(optionalFromNullable, "Optional.fromNullable(expireAfterWriteTimeUnit)");
        return optionalFromNullable;
    }

    /* JADX INFO: compiled from: EvictionPolicy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/lru/EvictionPolicy$Builder;", "", "()V", "expireAfterAccess", "", "Ljava/lang/Long;", "expireAfterAccessTimeUnit", "Ljava/util/concurrent/TimeUnit;", "expireAfterWrite", "expireAfterWriteTimeUnit", "maxEntries", "maxSizeBytes", "build", "Lcom/apollographql/apollo/cache/normalized/lru/EvictionPolicy;", "time", "timeUnit", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
    public static final class Builder {
        private Long expireAfterAccess;
        private TimeUnit expireAfterAccessTimeUnit;
        private Long expireAfterWrite;
        private TimeUnit expireAfterWriteTimeUnit;
        private Long maxEntries;
        private Long maxSizeBytes;

        public final Builder maxSizeBytes(long maxSizeBytes) {
            this.maxSizeBytes = Long.valueOf(maxSizeBytes);
            return this;
        }

        public final Builder maxEntries(long maxEntries) {
            this.maxEntries = Long.valueOf(maxEntries);
            return this;
        }

        public final Builder expireAfterAccess(long time, TimeUnit timeUnit) {
            Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
            this.expireAfterAccess = Long.valueOf(time);
            this.expireAfterAccessTimeUnit = timeUnit;
            return this;
        }

        public final Builder expireAfterWrite(long time, TimeUnit timeUnit) {
            Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
            this.expireAfterWrite = Long.valueOf(time);
            this.expireAfterWriteTimeUnit = timeUnit;
            return this;
        }

        public final EvictionPolicy build() {
            return new EvictionPolicy(this.maxSizeBytes, this.maxEntries, this.expireAfterAccess, this.expireAfterAccessTimeUnit, this.expireAfterWrite, this.expireAfterWriteTimeUnit);
        }
    }

    /* JADX INFO: compiled from: EvictionPolicy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/lru/EvictionPolicy$Companion;", "", "()V", "NO_EVICTION", "Lcom/apollographql/apollo/cache/normalized/lru/EvictionPolicy;", "builder", "Lcom/apollographql/apollo/cache/normalized/lru/EvictionPolicy$Builder;", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Builder builder() {
            return new Builder();
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        NO_EVICTION = companion.builder().build();
    }
}
