package com.dotscreen.tv.common.utils;

import com.android.tv.common.R;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MegacableError.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\"\b\u0086\u0001\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002%&B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$¨\u0006'"}, d2 = {"Lcom/dotscreen/tv/common/utils/MegacableError;", "", "errorCode", "", "resId", "", "owner", "(Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;)V", "getErrorCode", "()Ljava/lang/String;", "getOwner", "getResId", "()I", "GVPI_INVALID_DEFINITION", "GVPI_INVALID_ENCODING", "GVPI_INVALID_TERMINAL", "GVPI_INVALID_TICKET", "GVPI_INVALID_VIDEO_ID", "GVPI_MAX_SIMULTANEUOS_VIEWS_REACHED", "GVPI_NO_MATCHING_MEDIA_FILE_FOR_DEFINITION", "GVPI_NO_SUITABLE_MEDIA_FILE", "GVPI_TERMINAL_MODEL_MISMATCH", "GLPI_CHANNEL_NOT_VIEWABLE", "GLPI_FAILED_TO_GET_RECORDING_PLAYBACK_INFO", "GLPI_INVALID_CHANNEL", "GLPI_INVALID_PROGRAM_ID", "GLPI_INVALID_TERMINAL", "GLPI_NO_MATCHING_SOURCE_TYPE", "GLPI_SERVICE_NOT_AVAILABLE_ON_DEVICE", "GLPI_SERVICE_UNSUBSCRIBED", "GLPI_TERMINAL_IDENTIFIER_MISMATCH", "GLPI_USER_NOT_SUBSCRIBED_TO_CHANNEL", "OV_GENERAL_ERROR", "OV_INACTIVE_HOUSEHOLD", "OV_INSUFFICIENT_CREDIT", "OV_INVALID_VIDEO_ID", "OV_NO_MATCHING_MEDIA_FILE", "Companion", "RightVAction", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public enum MegacableError {
    GVPI_INVALID_DEFINITION("INVALID_DEFINITION", R.string.error_invalid_definition, "Megacable Ingestion team"),
    GVPI_INVALID_ENCODING("INVALID_ENCODING", R.string.error_invalid_encoding, "Megacable Ingestion team"),
    GVPI_INVALID_TERMINAL("INVALID_TERMINAL", R.string.error_invalid_terminal, "RiGHTv config"),
    GVPI_INVALID_TICKET("INVALID_TICKET", R.string.error_invalid_ticket, ""),
    GVPI_INVALID_VIDEO_ID("INVALID_VIDEO_ID", R.string.error_invalid_video_id, "DS/RiGHTv"),
    GVPI_MAX_SIMULTANEUOS_VIEWS_REACHED("MAX_SIMULTANEUOS_VIEWS_REACHED", R.string.error_max_simultaneuos_views_reached, ""),
    GVPI_NO_MATCHING_MEDIA_FILE_FOR_DEFINITION("NO_MATCHING_MEDIA_FILE_FOR_DEFINITION", R.string.error_no_matching_media_file_for_definition, "Megacable Ingestion team"),
    GVPI_NO_SUITABLE_MEDIA_FILE("NO_SUITABLE_MEDIA_FILE", R.string.error_no_suitable_media_file, "Megacable Ingestion team"),
    GVPI_TERMINAL_MODEL_MISMATCH("TERMINAL_MODEL_MISMATCH", R.string.error_terminal_model_mismatch, "DS/RiGHTv"),
    GLPI_CHANNEL_NOT_VIEWABLE("CHANNEL_NOT_VIEWABLE", R.string.error_channel_not_viewable, "RiGHTv config"),
    GLPI_FAILED_TO_GET_RECORDING_PLAYBACK_INFO("FAILED_TO_GET_RECORDING_PLAYBACK_INFO", R.string.error_failed_to_get_recording_playback_info, "RiGHTv config"),
    GLPI_INVALID_CHANNEL("INVALID_CHANNEL", R.string.error_invalid_channel, "Megacable Ingestion team"),
    GLPI_INVALID_PROGRAM_ID("INVALID_PROGRAM_ID", R.string.error_invalid_program_id, "RiGHTv config"),
    GLPI_INVALID_TERMINAL("INVALID_TERMINAL", R.string.error_glpi_invalid_terminal, "DS/RiGHTv"),
    GLPI_NO_MATCHING_SOURCE_TYPE("NO_MATCHING_SOURCE_TYPE", R.string.error_no_matching_source_type, "RiGHTv config"),
    GLPI_SERVICE_NOT_AVAILABLE_ON_DEVICE("SERVICE_NOT_AVAILABLE_ON_DEVICE", R.string.error_service_not_available_on_device, "RiGHTv config"),
    GLPI_SERVICE_UNSUBSCRIBED("SERVICE_UNSUBSCRIBED", R.string.error_service_unsubscribed, "RiGHTv config"),
    GLPI_TERMINAL_IDENTIFIER_MISMATCH("TERMINAL_IDENTIFIER_MISMATCH", R.string.error_terminal_identifier_mismatch, "DS/RiGHTv"),
    GLPI_USER_NOT_SUBSCRIBED_TO_CHANNEL("USER_NOT_SUBSCRIBED_TO_CHANNEL", R.string.error_user_not_subscribed_to_channel, "RiGHTv config"),
    OV_GENERAL_ERROR("GENERAL_ERROR", R.string.error_general_error, "DS/RiGHTv"),
    OV_INACTIVE_HOUSEHOLD("INACTIVE_HOUSEHOLD", R.string.error_inactive_household, "RiGHTv config"),
    OV_INSUFFICIENT_CREDIT("INSUFFICIENT_CREDIT", R.string.error_insufficient_credit, "RiGHTv config"),
    OV_INVALID_VIDEO_ID("INVALID_VIDEO_ID", R.string.error_ov_invalid_video_id, "DS/RiGHTv"),
    OV_NO_MATCHING_MEDIA_FILE("NO_MATCHING_MEDIA_FILE", R.string.error_no_matching_media_file, "RiGHTv config");


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String errorCode;
    private final String owner;
    private final int resId;

    MegacableError(String str, int i, String str2) {
        this.errorCode = str;
        this.resId = i;
        this.owner = str2;
    }

    public final String getErrorCode() {
        return this.errorCode;
    }

    public final String getOwner() {
        return this.owner;
    }

    public final int getResId() {
        return this.resId;
    }

    /* JADX INFO: compiled from: MegacableError.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/utils/MegacableError$Companion;", "", "()V", "getErrorObject", "Lcom/dotscreen/tv/common/utils/MegacableError;", "requestUrl", "", "errorCode", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MegacableError getErrorObject(final String requestUrl, String errorCode) {
            Intrinsics.checkParameterIsNotNull(requestUrl, "requestUrl");
            Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
            Lazy lazy = LazyKt.lazy(new Function0<String>() { // from class: com.dotscreen.tv.common.utils.MegacableError$Companion$getErrorObject$prefix$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    for (MegacableError.RightVAction rightVAction : MegacableError.RightVAction.values()) {
                        if (StringsKt.contains$default((CharSequence) requestUrl, (CharSequence) rightVAction.getActionName(), false, 2, (Object) null)) {
                            return rightVAction.getShorten();
                        }
                    }
                    return null;
                }
            });
            if (lazy.getValue() != null) {
                errorCode = ((String) lazy.getValue()) + '_' + errorCode;
            }
            return MegacableError.valueOf(errorCode);
        }
    }

    /* JADX INFO: compiled from: MegacableError.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/dotscreen/tv/common/utils/MegacableError$RightVAction;", "", "actionName", "", "shorten", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getActionName", "()Ljava/lang/String;", "getShorten", "GET_LIVE_PLAYING_INFO", "GET_VIDEO_PLAYING_INFO", "ORDER_VIDEO", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public enum RightVAction {
        GET_LIVE_PLAYING_INFO("GetLivePlayingInfo", "GLPI"),
        GET_VIDEO_PLAYING_INFO("GetVideoPlayingInfo", "GVPI"),
        ORDER_VIDEO("OrderVideo", "OV");

        private final String actionName;
        private final String shorten;

        RightVAction(String str, String str2) {
            this.actionName = str;
            this.shorten = str2;
        }

        public final String getActionName() {
            return this.actionName;
        }

        public final String getShorten() {
            return this.shorten;
        }
    }
}
