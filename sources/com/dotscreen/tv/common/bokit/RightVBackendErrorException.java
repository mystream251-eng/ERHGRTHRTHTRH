package com.dotscreen.tv.common.bokit;

import com.dotscreen.bokit.BackendErrorException;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RightVRequestManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0012\u001a\u00020\u0003H\u0016R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVBackendErrorException;", "Lcom/dotscreen/bokit/BackendErrorException;", "rightvMessage", "", "requestId", "backendCode", ImagesContract.URL, "httpStatusCodes", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "errorType", "Lcom/dotscreen/tv/common/bokit/RightVErrorType;", "getErrorType", "()Lcom/dotscreen/tv/common/bokit/RightVErrorType;", "getHttpStatusCodes", "()I", "getRightvMessage", "()Ljava/lang/String;", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class RightVBackendErrorException extends BackendErrorException {
    private final int httpStatusCodes;
    private final String rightvMessage;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RightVBackendErrorException(String rightvMessage, String requestId, String backendCode, String url, int i) {
        super(rightvMessage, requestId, backendCode, url, null, 16, null);
        Intrinsics.checkParameterIsNotNull(rightvMessage, "rightvMessage");
        Intrinsics.checkParameterIsNotNull(requestId, "requestId");
        Intrinsics.checkParameterIsNotNull(backendCode, "backendCode");
        Intrinsics.checkParameterIsNotNull(url, "url");
        this.rightvMessage = rightvMessage;
        this.httpStatusCodes = i;
    }

    public final int getHttpStatusCodes() {
        return this.httpStatusCodes;
    }

    public final String getRightvMessage() {
        return this.rightvMessage;
    }

    public final RightVErrorType getErrorType() {
        String backendCode = getBackendCode();
        if (backendCode.hashCode() == 1904614137 && backendCode.equals("INVALID_PROFILE_ID")) {
            if (StringsKt.contains$default((CharSequence) this.rightvMessage, (CharSequence) "There is an existing profile with external id", false, 2, (Object) null)) {
                return new ProfileAlreadyExistError(getBackendCode(), this.rightvMessage);
            }
            return new UnknownError(getBackendCode(), this.rightvMessage);
        }
        return new UnknownError(getBackendCode(), this.rightvMessage);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return '[' + getBackendCode() + "] message";
    }
}
