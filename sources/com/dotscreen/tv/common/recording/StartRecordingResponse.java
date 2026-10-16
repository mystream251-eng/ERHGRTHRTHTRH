package com.dotscreen.tv.common.recording;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: GlobalRecordingManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/tv/common/recording/StartRecordingResponse;", "", "recordingId", "", "error", "Lcom/dotscreen/tv/common/recording/Error;", "(Ljava/lang/String;Lcom/dotscreen/tv/common/recording/Error;)V", "getError", "()Lcom/dotscreen/tv/common/recording/Error;", "setError", "(Lcom/dotscreen/tv/common/recording/Error;)V", "getRecordingId", "()Ljava/lang/String;", "setRecordingId", "(Ljava/lang/String;)V", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class StartRecordingResponse {
    private Error error;
    private String recordingId;

    /* JADX WARN: Multi-variable type inference failed */
    public StartRecordingResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public StartRecordingResponse(String str, Error error) {
        this.recordingId = str;
        this.error = error;
    }

    public /* synthetic */ StartRecordingResponse(String str, Error error, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Error) null : error);
    }

    public final Error getError() {
        return this.error;
    }

    public final String getRecordingId() {
        return this.recordingId;
    }

    public final void setError(Error error) {
        this.error = error;
    }

    public final void setRecordingId(String str) {
        this.recordingId = str;
    }
}
