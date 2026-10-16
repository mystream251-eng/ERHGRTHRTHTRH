package com.dotscreen.portal.utils;

import android.content.Context;
import android.util.Log;
import com.dotscreen.bokit.BackendErrorException;
import com.dotscreen.farkit.tools.ErrorManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.tv.common.bokit.RightVBackendErrorException;
import com.dotscreen.tv.common.bokit.RightVServerError;
import com.dotscreen.tv.common.utils.MegacableError;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MegacableErrorManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/utils/MegacableErrorManager;", "Lcom/dotscreen/farkit/tools/ErrorManager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getErrorMessage", "", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handleLoginError", "", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MegacableErrorManager extends ErrorManager {
    @Override // com.dotscreen.farkit.tools.ErrorManager
    public void handleLoginError(Throwable error) {
        Intrinsics.checkParameterIsNotNull(error, "error");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MegacableErrorManager(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    @Override // com.dotscreen.farkit.tools.ErrorManager
    public String getErrorMessage(Exception error) {
        String string;
        String string2;
        Intrinsics.checkParameterIsNotNull(error, "error");
        Exception exc = error;
        if (ErrorManager.INSTANCE.getErrorStatusCode(exc) == 411) {
            String string3 = getContext().getString(R.string.geoblocking_message);
            Intrinsics.checkExpressionValueIsNotNull(string3, "context.getString(R.string.geoblocking_message)");
            return string3;
        }
        if (!(error instanceof BackendErrorException) && !(error instanceof RightVServerError)) {
            exc = error.getCause();
        }
        if (exc instanceof RightVBackendErrorException) {
            RightVBackendErrorException rightVBackendErrorException = (RightVBackendErrorException) exc;
            String backendCode = rightVBackendErrorException.getBackendCode();
            if (backendCode.equals("INVALID_CHANNEL_ID")) {
                string2 = getContext().getString(R.string.no_adult_channels_in_favorite);
            } else if (backendCode.equals("INVALID_PIN_CODE")) {
                string2 = getContext().getString(R.string.error_pin_code_wrong);
            } else if (backendCode.equals("MAX_QUOTA_EXCEEDED")) {
                string2 = getContext().getString(R.string.recording_error_max_reached);
            } else if (backendCode.equals("MAX_PROFILES_EXCEEDED")) {
                string2 = getContext().getString(R.string.create_profile_error_max_exceeded);
            } else if (backendCode.equals("INVALID_CREDENTIALS")) {
                string2 = getContext().getString(R.string.login_error_stb_not_provisioned);
            } else if (backendCode.equals("INACTIVE_HOUSEHOLD") || backendCode.equals("SUSPENDED_HOUSEHOLD")) {
                string2 = getContext().getString(R.string.error_suspended_account);
            } else if (backendCode.equals("LOGIN_NOT_ALLOWED_OUT_OF_MEGACABLE_NETWORK")) {
                string2 = getContext().getString(R.string.error_geoblocking);
            } else if (backendCode.equals("INVALID_RECORDING_ID") || backendCode.equals("INVALID_PROGRAM_ID")) {
                string2 = getContext().getString(R.string.error_content_not_available);
            } else if (backendCode.equals("SERVICE_UNSUBSCRIBED")) {
                string2 = getContext().getString(R.string.not_subscribed_to_the_service);
            } else if (backendCode.equals("USER_NOT_SUBSCRIBED_TO_CHANNEL")) {
                string2 = getContext().getString(R.string.not_subscribed_to_the_channel);
            } else if (backendCode.equals("PLAYBACK_NOT_ALLOWED_OUT_OF_MEGACABLE_NETWORK")) {
                string2 = getContext().getString(R.string.error_content_not_playable_out_of_mega_network);
            } else {
                try {
                    MegacableError errorObject = MegacableError.INSTANCE.getErrorObject(((RightVBackendErrorException) exc).getUrl(), ((RightVBackendErrorException) exc).getBackendCode());
                    Log.e(ErrorManager.INSTANCE.getTAG(), "Error: " + errorObject.getOwner());
                    string2 = getContext().getString(errorObject.getResId());
                } catch (IllegalArgumentException unused) {
                    Log.e(ErrorManager.INSTANCE.getTAG(), "Service " + rightVBackendErrorException.getRequestId() + " => " + rightVBackendErrorException.getBackendCode());
                    if (rightVBackendErrorException.getHttpStatusCodes() == 200 && Intrinsics.areEqual(rightVBackendErrorException.getRequestId(), "ID")) {
                        string2 = getContext().getString(R.string.generic_technical_error_2);
                    } else {
                        String message = exc.getMessage();
                        if (message != null && StringsKt.contains$default((CharSequence) message, (CharSequence) "TB_2004", false, 2, (Object) null)) {
                            string2 = getContext().getString(R.string.generic_technical_error, getContext().getString(R.string.error_r200_toolbox_content_not_available));
                        } else if (Intrinsics.areEqual(rightVBackendErrorException.getBackendCode(), "GENERAL_SERVER_ERROR") && StringsKt.contains$default((CharSequence) rightVBackendErrorException.getUrl(), (CharSequence) "GetLivePlayingInfo", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) rightVBackendErrorException.getUrl(), (CharSequence) "otf_recording_id", false, 2, (Object) null)) {
                            string2 = getContext().getString(R.string.generic_technical_error, getContext().getString(R.string.error_r200_wrong_bpk_url_fqdn));
                        } else {
                            string2 = getContext().getString(R.string.generic_technical_error, rightVBackendErrorException.getRequestId() + ':' + rightVBackendErrorException.getHttpStatusCodes());
                        }
                    }
                }
            }
            Intrinsics.checkExpressionValueIsNotNull(string2, "with(cause.backendCode) …      }\n                }");
            return string2;
        }
        if (exc instanceof RightVServerError) {
            String string4 = getContext().getString(R.string.generic_technical_error, ((RightVServerError) exc).getServiceName().getShortName() + ':' + ErrorManager.INSTANCE.getErrorStatusCode(exc));
            Intrinsics.checkExpressionValueIsNotNull(string4, "context.getString(\n     …ause)}\"\n                )");
            return string4;
        }
        if (StringsKt.equals$default(error.getMessage(), "SM_1001", false, 2, null)) {
            string = getContext().getString(R.string.error_session_limit);
        } else {
            if (ErrorManager.INSTANCE.getErrorStatusCode(exc != null ? exc : new Exception()) != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(getContext().getString(R.string.generic_technical_error));
                sb.append(" : HTTP ");
                ErrorManager.Companion companion = ErrorManager.INSTANCE;
                if (exc == null) {
                    exc = new Exception();
                }
                sb.append(companion.getErrorStatusCode(exc));
                string = sb.toString();
            } else {
                string = getContext().getString(R.string.generic_technical_error, getContext().getString(R.string.connectivity_issue));
            }
        }
        Intrinsics.checkExpressionValueIsNotNull(string, "when {\n                 …      )\n                }");
        return string;
    }
}
