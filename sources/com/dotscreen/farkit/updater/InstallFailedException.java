package com.dotscreen.farkit.updater;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/farkit/updater/InstallFailedException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "(Ljava/lang/String;)V", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class InstallFailedException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InstallFailedException(String message) {
        super(message);
        Intrinsics.checkParameterIsNotNull(message, "message");
    }
}
