package com.dotscreen.tv.common.bokit;

import com.android.volley.NetworkResponse;
import com.android.volley.ServerError;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RightVRequestManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVServerError;", "Lcom/android/volley/ServerError;", "response", "Lcom/android/volley/NetworkResponse;", "serviceName", "Lcom/dotscreen/tv/common/bokit/BackendName;", "(Lcom/android/volley/NetworkResponse;Lcom/dotscreen/tv/common/bokit/BackendName;)V", "getServiceName", "()Lcom/dotscreen/tv/common/bokit/BackendName;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class RightVServerError extends ServerError {
    private final BackendName serviceName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RightVServerError(NetworkResponse response, BackendName serviceName) {
        super(response);
        Intrinsics.checkParameterIsNotNull(response, "response");
        Intrinsics.checkParameterIsNotNull(serviceName, "serviceName");
        this.serviceName = serviceName;
    }

    public final BackendName getServiceName() {
        return this.serviceName;
    }
}
