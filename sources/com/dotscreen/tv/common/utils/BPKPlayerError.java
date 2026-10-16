package com.dotscreen.tv.common.utils;

import com.android.tv.common.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: MegacableError.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\b\u0086\u0001\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001cB\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001b¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/tv/common/utils/BPKPlayerError;", "", "code", "", "resId", "owner", "", "(Ljava/lang/String;IIILjava/lang/String;)V", "getCode", "()I", "getOwner", "()Ljava/lang/String;", "getResId", "RESULT_NO_STREAMING_URL_FOUND", "RESULT_CDN_RESPONSE_UNREADABLE", "RESULT_REQUESTED_URL_NOT_FOUND_ON_CDN", "RESULT_REQUESTED_URL_NOT_FOUND_ON_NANOCDN", "RESULT_REQUESTED_URL_CDN_ERROR", "RESULT_NANOCDN_SESSION_LIMIT_REACHED", "RESULT_NANOCDN_SESSION_LIMIT_REACHED_ON_REQUESTED_CHANNEL", "RESULT_NANOCDN_SERVICE_UNAVAILABLE", "RESULT_NANOCDN_CHANNEL_UNAVAILABLE", "RESULT_NANOCDN_REQUEST_LOCAL_IF_CONNECTED_TO_WIFI_NOT_ALLOWED", "RESULT_NANOCDN_RESPONSE_UNREADABLE", "RESULT_API_PARAMETER_FORMAT_ERROR", "RESULT_API_NOT_INITIALIZED", "RESULT_SESSION_HAS_BEEN_STOPPED_DURING_REQUEST", "RESULT_REQUEST_ALREADY_DONE", "Companion", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public enum BPKPlayerError {
    RESULT_NO_STREAMING_URL_FOUND(-100, R.string.error_result_no_streaming_url_found, "BPK"),
    RESULT_CDN_RESPONSE_UNREADABLE(-101, R.string.error_result_cdn_response_unreadable, "BPK"),
    RESULT_REQUESTED_URL_NOT_FOUND_ON_CDN(-102, R.string.error_result_requested_url_not_found_on_cdn, "BPK"),
    RESULT_REQUESTED_URL_NOT_FOUND_ON_NANOCDN(-103, R.string.error_result_requested_url_not_found_on_nanocdn, "BPK"),
    RESULT_REQUESTED_URL_CDN_ERROR(-104, R.string.error_result_requested_url_cdn_error, "BPK"),
    RESULT_NANOCDN_SESSION_LIMIT_REACHED(-201, R.string.error_result_nanocdn_session_limit_reached, "BPK"),
    RESULT_NANOCDN_SESSION_LIMIT_REACHED_ON_REQUESTED_CHANNEL(-202, R.string.error_result_nanocdn_session_limit_reached_on_requested_channel, "BPK"),
    RESULT_NANOCDN_SERVICE_UNAVAILABLE(-203, R.string.error_result_nanocdn_service_unavailable, "BPK"),
    RESULT_NANOCDN_CHANNEL_UNAVAILABLE(-204, R.string.error_result_nanocdn_channel_unavailable, "BPK"),
    RESULT_NANOCDN_REQUEST_LOCAL_IF_CONNECTED_TO_WIFI_NOT_ALLOWED(-205, R.string.error_result_nanocdn_request_local_if_connected_to_wifi_not_allowed, "BPK"),
    RESULT_NANOCDN_RESPONSE_UNREADABLE(-206, R.string.error_result_nanocdn_response_unreadable, "BPK"),
    RESULT_API_PARAMETER_FORMAT_ERROR(-400, R.string.error_result_api_parameter_format_error, "BPK"),
    RESULT_API_NOT_INITIALIZED(-401, R.string.error_result_api_not_initialized, "BPK"),
    RESULT_SESSION_HAS_BEEN_STOPPED_DURING_REQUEST(-500, R.string.error_result_session_has_been_stopped_during_request, "BPK"),
    RESULT_REQUEST_ALREADY_DONE(-501, R.string.error_result_request_already_done, "BPK");


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int code;
    private final String owner;
    private final int resId;

    BPKPlayerError(int i, int i2, String str) {
        this.code = i;
        this.resId = i2;
        this.owner = str;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getOwner() {
        return this.owner;
    }

    public final int getResId() {
        return this.resId;
    }

    /* JADX INFO: compiled from: MegacableError.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/tv/common/utils/BPKPlayerError$Companion;", "", "()V", "getBPKPlayerError", "Lcom/dotscreen/tv/common/utils/BPKPlayerError;", "errorCode", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BPKPlayerError getBPKPlayerError(int errorCode) {
            for (BPKPlayerError bPKPlayerError : BPKPlayerError.values()) {
                if (bPKPlayerError.getCode() == errorCode) {
                    return bPKPlayerError;
                }
            }
            return null;
        }
    }
}
