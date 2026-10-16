package com.dotscreen.tvinput.voPlayer;

import kotlin.Metadata;

/* JADX INFO: compiled from: VOTVPlayer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/dotscreen/tvinput/voPlayer/StreamingSessionResultMock;", "", "()V", "detailedErrorCode", "", "getDetailedErrorCode", "()I", "detailedErrorMessage", "", "getDetailedErrorMessage", "()Ljava/lang/String;", "errorCode", "getErrorCode", "errorMessage", "getErrorMessage", "isError", "", "()Z", "uRL", "getURL", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class StreamingSessionResultMock {
    public final int getDetailedErrorCode() {
        return 404;
    }

    public final String getDetailedErrorMessage() {
        return "Not found";
    }

    public final int getErrorCode() {
        return 3102;
    }

    public final String getErrorMessage() {
        return "MOCK MESSAGE";
    }

    public final String getURL() {
        return "";
    }

    public final boolean isError() {
        return true;
    }
}
