package com.dotscreen.tv.common.bokit;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RightVRequestManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVErrorType;", "", "code", "", "message", "(Ljava/lang/String;Ljava/lang/String;)V", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class RightVErrorType {
    public RightVErrorType(String code, String message) {
        Intrinsics.checkParameterIsNotNull(code, "code");
        Intrinsics.checkParameterIsNotNull(message, "message");
    }
}
