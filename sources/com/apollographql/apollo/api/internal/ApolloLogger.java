package com.apollographql.apollo.api.internal;

import com.apollographql.apollo.Logger;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApolloLogger.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001¢\u0006\u0002\u0010\rJ1\u0010\u0007\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001¢\u0006\u0002\u0010\u0010J'\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001¢\u0006\u0002\u0010\rJ1\u0010\u0011\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001¢\u0006\u0002\u0010\u0010J;\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0015J'\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001¢\u0006\u0002\u0010\rJ1\u0010\u0016\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001¢\u0006\u0002\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcom/apollographql/apollo/api/internal/ApolloLogger;", "", "logger", "Lcom/apollographql/apollo/Logger;", "(Lcom/apollographql/apollo/Logger;)V", "getLogger", "()Lcom/apollographql/apollo/Logger;", "d", "", "message", "", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", "t", "", "(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V", "e", "log", "priority", "", "(ILjava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V", "w", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class ApolloLogger {
    private final Logger logger;

    public ApolloLogger(Logger logger) {
        this.logger = logger;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final void d(String message, Object... args) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(args, "args");
        log(3, message, null, Arrays.copyOf(args, args.length));
    }

    public final void d(Throwable t, String message, Object... args) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(args, "args");
        log(3, message, t, Arrays.copyOf(args, args.length));
    }

    public final void w(String message, Object... args) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(args, "args");
        log(5, message, null, Arrays.copyOf(args, args.length));
    }

    public final void w(Throwable t, String message, Object... args) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(args, "args");
        log(5, message, t, Arrays.copyOf(args, args.length));
    }

    public final void e(String message, Object... args) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(args, "args");
        log(6, message, null, Arrays.copyOf(args, args.length));
    }

    public final void e(Throwable t, String message, Object... args) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(args, "args");
        log(6, message, t, Arrays.copyOf(args, args.length));
    }

    private final void log(int priority, String message, Throwable t, Object... args) {
        Logger logger = this.logger;
        if (logger != null) {
            logger.log(priority, message, t, Arrays.copyOf(args, args.length));
        }
    }
}
