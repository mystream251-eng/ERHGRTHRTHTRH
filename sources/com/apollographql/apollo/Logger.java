package com.apollographql.apollo;

import kotlin.Metadata;

/* JADX INFO: compiled from: Logger.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\bf\u0018\u0000 \r2\u00020\u0001:\u0001\rJ;\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b\"\u00020\u0001H&¢\u0006\u0002\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo/Logger;", "", "log", "", "priority", "", "message", "", "t", "", "args", "", "(ILjava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V", "Companion", "apollo-api"}, k = 1, mv = {1, 1, 16})
public interface Logger {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int WARN = 5;

    void log(int priority, String message, Throwable t, Object... args);

    /* JADX INFO: compiled from: Logger.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/Logger$Companion;", "", "()V", "DEBUG", "", "ERROR", "WARN", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int DEBUG = 3;
        public static final int ERROR = 6;
        public static final int WARN = 5;

        private Companion() {
        }
    }
}
