package com.dotscreen.portal.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoginError.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/portal/utils/LoginError;", "", "error", "", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "GEOBLOKING", "NOT_PROVISIONED", "SUSPEND_ACCOUNT", "UNKNOWN", "Lcom/dotscreen/portal/utils/LoginError$GEOBLOKING;", "Lcom/dotscreen/portal/utils/LoginError$SUSPEND_ACCOUNT;", "Lcom/dotscreen/portal/utils/LoginError$NOT_PROVISIONED;", "Lcom/dotscreen/portal/utils/LoginError$UNKNOWN;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class LoginError {
    private final Throwable error;

    /* JADX INFO: compiled from: LoginError.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/portal/utils/LoginError$GEOBLOKING;", "Lcom/dotscreen/portal/utils/LoginError;", "error", "", "(Ljava/lang/Throwable;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class GEOBLOKING extends LoginError {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GEOBLOKING(Throwable error) {
            super(error, null);
            Intrinsics.checkParameterIsNotNull(error, "error");
        }
    }

    private LoginError(Throwable th) {
        this.error = th;
    }

    public /* synthetic */ LoginError(Throwable th, DefaultConstructorMarker defaultConstructorMarker) {
        this(th);
    }

    public final Throwable getError() {
        return this.error;
    }

    /* JADX INFO: compiled from: LoginError.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/portal/utils/LoginError$SUSPEND_ACCOUNT;", "Lcom/dotscreen/portal/utils/LoginError;", "error", "", "(Ljava/lang/Throwable;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class SUSPEND_ACCOUNT extends LoginError {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SUSPEND_ACCOUNT(Throwable error) {
            super(error, null);
            Intrinsics.checkParameterIsNotNull(error, "error");
        }
    }

    /* JADX INFO: compiled from: LoginError.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/portal/utils/LoginError$NOT_PROVISIONED;", "Lcom/dotscreen/portal/utils/LoginError;", "error", "", "(Ljava/lang/Throwable;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class NOT_PROVISIONED extends LoginError {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NOT_PROVISIONED(Throwable error) {
            super(error, null);
            Intrinsics.checkParameterIsNotNull(error, "error");
        }
    }

    /* JADX INFO: compiled from: LoginError.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/portal/utils/LoginError$UNKNOWN;", "Lcom/dotscreen/portal/utils/LoginError;", "error", "", "(Ljava/lang/Throwable;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class UNKNOWN extends LoginError {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UNKNOWN(Throwable error) {
            super(error, null);
            Intrinsics.checkParameterIsNotNull(error, "error");
        }
    }
}
