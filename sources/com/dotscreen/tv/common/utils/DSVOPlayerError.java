package com.dotscreen.tv.common.utils;

import com.android.tv.common.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: MegacableError.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\bA\b\u0086\u0001\u0018\u0000 G2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001GB\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bF¨\u0006H"}, d2 = {"Lcom/dotscreen/tv/common/utils/DSVOPlayerError;", "", "code", "", "resId", "", "owner", "", "(Ljava/lang/String;IJILjava/lang/String;)V", "getCode", "()J", "getOwner", "()Ljava/lang/String;", "getResId", "()I", "MEDIA_INFO_GENERIC_HACKING_DETECTED", "MEDIA_ERROR_NO_ATERNATE_SUPPORTED", "DRM_SCHEME_IS_NOT_SUPPORTED", "HTTP_ERROR_FROM_LICENSER", "LICENSER_NOT_REACHED", "DRM_LICENSE_HAS_EXPIRED", "DRM_LICENSE_NOT_FOUND", "MEDIA_INFO_NETWORK_DOWN", "MEDIA_INFO_NETWORK_NO_FILE", "MEDIA_ERROR_NETWORK_BAD_URL", "MEDIA_ERROR_NETWORK_HTTP_UNAUTHORIZED", "MEDIA_ERROR_NETWORK_HTTP_FORBIDDEN", "MEDIA_ERROR_NETWORK_HTTP_REQUEST_TIMEOUT", "MEDIA_ERROR_NETWORK_HTTP_CONFLICT", "MEDIA_ERROR_NETWORK_HTTP_GONE", "MEDIA_ERROR_NETWORK_HTTP_INTERNAL_SERVER_ERROR", "MEDIA_ERROR_NETWORK_HTTP_NOT_IMPLEMENTED", "MEDIA_ERROR_NETWORK_HTTP_BAD_GATEWAY", "MEDIA_ERROR_NETWORK_HTTP_SERVICE_UNAVAILABLE", "MEDIA_ERROR_NETWORK_HTTP_GATEWAY_TIMEOUT", "MEDIA_ERROR_NETWORK_HTTP_VERSION_NOT_SUPPORTED", "MEDIA_ERROR_SECURITY_RECORDING", "AIRPLAY_NOT_AVAILABLE", "INVALID_MISSING_PARAMETER", "REQUESTED_ACTION_NOT_ALLOWED_CURRENT_STATE", "STREAM_IS_NOT_COMPATIBLE", "REQUESTED_MEDIA_IS_NOT_SUPPORTED", "GENERIC_CORE_ERROR", "REQUESTED_MEDIA_TYPE_IS_NOT_SUPPORTED", "INVALID_URL_SERVER_DOWN", "NETWORK_DOWN", "PROVIDED_URL_IS_MALFORMED", "FAILED_TO_CONNECT_SERVER", "COMMUNICATION_TIME_OUT", "PROVIDED_URL_IS_MALFORMED_2", "HTTP_404_VO_FILE_NOT_FOUND", "HTTP_403_VO_forbidden", "HTTP_408_VO_REQUEST_TIME_OUT", "HTTP_409_VO_CONFLICT", "HTTP_410_VO_GONE", "HTTP_503_VO_SERVICE_UNAVAILABLE", "INVALID_EXPIRED_CREDENTIALS", "PROVIDED_PLAYLIST_IS_MALFORMED", "FAILED_TO_CONNECT_SERVER_2", "PROVIDED_URL_IS_MALFORMED_3", "ROOTED_DEVICE_DETECTED", "DEBUG_APP_IS_NOT_ALLOWED", "DRM_AGENT_NOT_SET_CORRECTLY", "DEVICE_SERCURITY_LEVEL_MISMATCH", "BAD_FORMAT_CHECKSUM", "CANNOT_CONNET_TO_LICENSE_SERVER", "DRM_GENERIC_ERROR", "LICENSE_NO_MORE_VALID", "KID_MISMATCH_CDM_UPGRADE", "MISSING_LICENSE_ACQUISITION_URL", "NOT_AUTHORIZED_TO_PLAY_CONTENT", "Companion", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public enum DSVOPlayerError {
    MEDIA_INFO_GENERIC_HACKING_DETECTED(8001, R.string.error_media_info_generic_hacking_detected, "VOP"),
    MEDIA_ERROR_NO_ATERNATE_SUPPORTED(1009, R.string.error_media_error_no_aternate_supported, "VOP"),
    DRM_SCHEME_IS_NOT_SUPPORTED(3101, R.string.error_drm_scheme_is_not_supported, "VOP"),
    HTTP_ERROR_FROM_LICENSER(3105, R.string.error_http_error_from_licenser, "VOP"),
    LICENSER_NOT_REACHED(3109, R.string.error_licenser_not_reached, "VOP"),
    DRM_LICENSE_HAS_EXPIRED(3111, R.string.error_drm_license_has_expired, "VOP"),
    DRM_LICENSE_NOT_FOUND(3112, R.string.error_drm_license_not_found, "VOP"),
    MEDIA_INFO_NETWORK_DOWN(4001, R.string.error_media_info_network_down, "VOP"),
    MEDIA_INFO_NETWORK_NO_FILE(4002, R.string.error_media_info_network_no_file, "VOP"),
    MEDIA_ERROR_NETWORK_BAD_URL(4004, R.string.error_media_error_network_bad_url, "VOP"),
    MEDIA_ERROR_NETWORK_HTTP_UNAUTHORIZED(4005, R.string.error_media_error_network_http_unauthorized, "VOP"),
    MEDIA_ERROR_NETWORK_HTTP_FORBIDDEN(4006, R.string.error_media_error_network_http_forbidden, "VOP"),
    MEDIA_ERROR_NETWORK_HTTP_REQUEST_TIMEOUT(4007, R.string.error_media_error_network_http_request_timeout, "VOP"),
    MEDIA_ERROR_NETWORK_HTTP_CONFLICT(4008, R.string.error_media_error_network_http_conflict, "VOP"),
    MEDIA_ERROR_NETWORK_HTTP_GONE(4009, R.string.error_media_error_network_http_gone, "VOP"),
    MEDIA_ERROR_NETWORK_HTTP_INTERNAL_SERVER_ERROR(4010, R.string.error_media_error_network_http_internal_server_error, "VOP"),
    MEDIA_ERROR_NETWORK_HTTP_NOT_IMPLEMENTED(4011, R.string.error_media_error_network_http_not_implemented, "VOP"),
    MEDIA_ERROR_NETWORK_HTTP_BAD_GATEWAY(4012, R.string.error_media_error_network_http_bad_gateway, "VOP"),
    MEDIA_ERROR_NETWORK_HTTP_SERVICE_UNAVAILABLE(4013, R.string.error_media_error_network_http_service_unavailable, "VOP"),
    MEDIA_ERROR_NETWORK_HTTP_GATEWAY_TIMEOUT(4014, R.string.error_media_error_network_http_gateway_timeout, "VOP"),
    MEDIA_ERROR_NETWORK_HTTP_VERSION_NOT_SUPPORTED(4015, R.string.error_media_error_network_http_version_not_supported, "VOP"),
    MEDIA_ERROR_SECURITY_RECORDING(13001, R.string.error_media_error_security_recording, "VOP"),
    AIRPLAY_NOT_AVAILABLE(18001, R.string.error_airplay_not_available, "VOP"),
    INVALID_MISSING_PARAMETER(2147483649L, R.string.error_invalid_missing_parameter, "VOP"),
    REQUESTED_ACTION_NOT_ALLOWED_CURRENT_STATE(2147483650L, R.string.error_requested_action_not_allowed_current_state, "VOP"),
    STREAM_IS_NOT_COMPATIBLE(2147483654L, R.string.error_stream_is_not_compatible, "VOP"),
    REQUESTED_MEDIA_IS_NOT_SUPPORTED(2147483659L, R.string.error_requested_media_is_not_supported, "VOP"),
    GENERIC_CORE_ERROR(2172977152L, R.string.error_generic_core_error, "VOP"),
    REQUESTED_MEDIA_TYPE_IS_NOT_SUPPORTED(2172977160L, R.string.error_requested_media_type_is_not_supported, "VOP"),
    INVALID_URL_SERVER_DOWN(2172977162L, R.string.error_invalid_url_server_down, "VOP"),
    NETWORK_DOWN(2172977408L, R.string.error_network_down, "VOP"),
    PROVIDED_URL_IS_MALFORMED(2181038081L, R.string.error_provided_url_is_malformed, "VOP"),
    FAILED_TO_CONNECT_SERVER(2214592513L, R.string.error_failed_to_connect_server, "VOP"),
    COMMUNICATION_TIME_OUT(2214592514L, R.string.error_communication_time_out, "VOP"),
    PROVIDED_URL_IS_MALFORMED_2(2214592516L, R.string.error_provided_url_is_malformed_2, "VOP"),
    HTTP_404_VO_FILE_NOT_FOUND(2214592517L, R.string.error_http_404_vo_file_not_found, "VOP"),
    HTTP_403_VO_forbidden(2214592519L, R.string.error_http_403_vo_forbidden, "VOP"),
    HTTP_408_VO_REQUEST_TIME_OUT(2214592520L, R.string.error_http_408_vo_request_time_out, "VOP"),
    HTTP_409_VO_CONFLICT(2214592521L, R.string.error_http_409_vo_conflict, "VOP"),
    HTTP_410_VO_GONE(2214592528L, R.string.error_http_410_vo_gone, "VOP"),
    HTTP_503_VO_SERVICE_UNAVAILABLE(2214592529L, R.string.error_http_503_vo_service_unavailable, "VOP"),
    INVALID_EXPIRED_CREDENTIALS(2214592532L, R.string.error_invalid_expired_credentials, "VOP"),
    PROVIDED_PLAYLIST_IS_MALFORMED(2214658049L, R.string.error_provided_playlist_is_malformed, "VOP"),
    FAILED_TO_CONNECT_SERVER_2(2231369729L, R.string.error_failed_to_connect_server_2, "VOP"),
    PROVIDED_URL_IS_MALFORMED_3(2231369730L, R.string.error_provided_url_is_malformed_3, "VOP"),
    ROOTED_DEVICE_DETECTED(2380922881L, R.string.error_rooted_device_detected, "VOP"),
    DEBUG_APP_IS_NOT_ALLOWED(2380922882L, R.string.error_debug_app_is_not_allowed, "VOP"),
    DRM_AGENT_NOT_SET_CORRECTLY(2172977165L, R.string.error_drm_agent_not_set_correctly, "VOP"),
    DEVICE_SERCURITY_LEVEL_MISMATCH(2248146945L, R.string.error_device_sercurity_level_mismatch, "VOP"),
    BAD_FORMAT_CHECKSUM(2248146946L, R.string.error_bad_format_checksum, "VOP"),
    CANNOT_CONNET_TO_LICENSE_SERVER(2248146948L, R.string.error_cannot_connet_to_license_server, "VOP"),
    DRM_GENERIC_ERROR(2248146949L, R.string.error_drm_generic_error, "VOP"),
    LICENSE_NO_MORE_VALID(2248146953L, R.string.error_license_no_more_valid, "VOP"),
    KID_MISMATCH_CDM_UPGRADE(2248146954L, R.string.error_kid_mismatch_cdm_upgrade, "VOP"),
    MISSING_LICENSE_ACQUISITION_URL(2248146957L, R.string.error_missing_license_acquisition_url, "VOP"),
    NOT_AUTHORIZED_TO_PLAY_CONTENT(2248409089L, R.string.error_not_authorized_to_play_content, "VOP");


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long code;
    private final String owner;
    private final int resId;

    DSVOPlayerError(long j, int i, String str) {
        this.code = j;
        this.resId = i;
        this.owner = str;
    }

    public final long getCode() {
        return this.code;
    }

    public final String getOwner() {
        return this.owner;
    }

    public final int getResId() {
        return this.resId;
    }

    /* JADX INFO: compiled from: MegacableError.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/tv/common/utils/DSVOPlayerError$Companion;", "", "()V", "getPlayerError", "Lcom/dotscreen/tv/common/utils/DSVOPlayerError;", "errorCode", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DSVOPlayerError getPlayerError(int errorCode) {
            for (DSVOPlayerError dSVOPlayerError : DSVOPlayerError.values()) {
                if (((int) dSVOPlayerError.getCode()) == errorCode) {
                    return dSVOPlayerError;
                }
            }
            return null;
        }
    }
}
