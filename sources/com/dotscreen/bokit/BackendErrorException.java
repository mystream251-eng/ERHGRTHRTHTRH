package com.dotscreen.bokit;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BOKit.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0016\u0018\u0000 \u000f2\u00060\u0001j\u0002`\u0002:\u0001\u000fB1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/bokit/BackendErrorException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "requestId", "backendCode", ImagesContract.URL, "cause", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V", "getBackendCode", "()Ljava/lang/String;", "getRequestId", "getUrl", "Companion", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class BackendErrorException extends Exception {
    private static final long serialVersionUID = -6085461351461611945L;
    private final String backendCode;
    private final String requestId;
    private final String url;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BackendErrorException(String message, String requestId, String backendCode, String url, Throwable th) {
        super(message, th);
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(backendCode, "backendCode");
        Intrinsics.checkNotNullParameter(url, "url");
        this.requestId = requestId;
        this.backendCode = backendCode;
        this.url = url;
    }

    public /* synthetic */ BackendErrorException(String str, String str2, String str3, String str4, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i & 16) != 0 ? (Throwable) null : th);
    }

    public final String getBackendCode() {
        return this.backendCode;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getUrl() {
        return this.url;
    }
}
