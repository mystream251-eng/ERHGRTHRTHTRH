package com.dotscreen.portal.utils.reporting;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.dotscreen.bokit.BackendErrorException;
import com.dotscreen.farkit.network.BackendReachability;
import com.dotscreen.farkit.network.NetworkStateManager;
import com.dotscreen.farkit.network.NetworkStatus;
import com.dotscreen.farkit.network.UnreachabilityReason;
import com.dotscreen.portal.BuildConfig;
import com.dotscreen.portal.utils.SentryEventExtentionsKt;
import com.dotscreen.tv.common.bokit.RightVBackendErrorException;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.DeviceInfo;
import com.dotscreen.tv.common.utils.SentryReportHint;
import com.google.android.gms.common.internal.ImagesContract;
import com.viaccessorca.analytics.fma.VoFmaController;
import com.viaccessorca.analytics.fma.VoFmaEvent;
import com.viaccessorca.voplayer.VOPlayer;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.protocol.Message;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: NoBackendReport.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0015H\u0002J\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u001bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/dotscreen/portal/utils/reporting/NoBackendReport;", "Lcom/dotscreen/portal/utils/reporting/AsynchronousReport;", "networkStateManager", "Lcom/dotscreen/farkit/network/NetworkStateManager;", "voPlayerUniqueId", "", "serialNumber", NotificationCompat.CATEGORY_STATUS, "Lcom/dotscreen/farkit/network/NetworkStatus;", ImagesContract.URL, "Ljava/net/URL;", "reason", "", "reachability", "Lcom/dotscreen/farkit/network/BackendReachability;", "(Lcom/dotscreen/farkit/network/NetworkStateManager;Ljava/lang/String;Ljava/lang/String;Lcom/dotscreen/farkit/network/NetworkStatus;Ljava/net/URL;Ljava/lang/Throwable;Lcom/dotscreen/farkit/network/BackendReachability;)V", "getReason", "()Ljava/lang/Throwable;", "getUrl", "()Ljava/net/URL;", "createSentryReport", "Lio/sentry/SentryEvent;", "isStart", "", "endReport", "", "endDate", "Ljava/util/Date;", "reportToVO", NotificationCompat.CATEGORY_EVENT, "startReport", "startDate", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class NoBackendReport extends AsynchronousReport {
    private final NetworkStateManager networkStateManager;
    private final BackendReachability reachability;
    private final Throwable reason;
    private final String serialNumber;
    private final NetworkStatus status;
    private final URL url;
    private final String voPlayerUniqueId;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[UnreachabilityReason.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[UnreachabilityReason.SSLCannotBeValidated.ordinal()] = 1;
        }
    }

    public final URL getUrl() {
        return this.url;
    }

    public final Throwable getReason() {
        return this.reason;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoBackendReport(NetworkStateManager networkStateManager, String voPlayerUniqueId, String serialNumber, NetworkStatus status, URL url, Throwable reason, BackendReachability reachability) {
        super("NO_BACKEND_REPORT");
        Intrinsics.checkParameterIsNotNull(networkStateManager, "networkStateManager");
        Intrinsics.checkParameterIsNotNull(voPlayerUniqueId, "voPlayerUniqueId");
        Intrinsics.checkParameterIsNotNull(serialNumber, "serialNumber");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        Intrinsics.checkParameterIsNotNull(reachability, "reachability");
        this.networkStateManager = networkStateManager;
        this.voPlayerUniqueId = voPlayerUniqueId;
        this.serialNumber = serialNumber;
        this.status = status;
        this.url = url;
        this.reason = reason;
        this.reachability = reachability;
    }

    @Override // com.dotscreen.portal.utils.reporting.AsynchronousReport
    public void startReport(Date startDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        super.startReport(startDate);
        SentryEvent sentryEventCreateSentryReport = createSentryReport(true);
        Sentry.captureEvent(sentryEventCreateSentryReport, SentryReportHint.Degraded);
        reportToVO(sentryEventCreateSentryReport);
    }

    @Override // com.dotscreen.portal.utils.reporting.AsynchronousReport
    public void endReport(Date endDate) {
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        super.endReport(endDate);
        SentryEvent sentryEventCreateSentryReport = createSentryReport(false);
        Sentry.captureEvent(sentryEventCreateSentryReport, SentryReportHint.Degraded);
        reportToVO(sentryEventCreateSentryReport);
    }

    private final SentryEvent createSentryReport(boolean isStart) {
        SentryEvent sentryEvent;
        Throwable cause;
        String message;
        if (isStart) {
            sentryEvent = new SentryEvent();
            sentryEvent.setLevel(SentryLevel.WARNING);
            Message message2 = new Message();
            NetworkStatus networkStatus = this.status;
            if (networkStatus != null) {
                message2.setMessage(WhenMappings.$EnumSwitchMapping$0[((NetworkStatus.ConnectedNoBackendAccess) networkStatus).getReason().ordinal()] != 1 ? CommonUtils.LIMITED_MODE_REASON_SENTRY_TITLE : CommonUtils.SYNC_FAILED_MODE_REASON_SENTRY_TITLE);
                sentryEvent.setMessage(message2);
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put("component", "Degraded");
                sentryEvent.setExtra("reason.class", this.reason.getClass().getCanonicalName());
                String message3 = this.reason.getMessage();
                if (message3 != null) {
                    sentryEvent.setExtra("reason.message", message3);
                }
                sentryEvent.setExtra("reason.details", this.reason);
                Throwable cause2 = this.reason.getCause();
                if (cause2 != null) {
                    sentryEvent.setExtra("reason.cause", cause2);
                }
                if (this.reason.getCause() instanceof BackendErrorException) {
                    Throwable cause3 = this.reason.getCause();
                    if (cause3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.BackendErrorException");
                    }
                    BackendErrorException backendErrorException = (BackendErrorException) cause3;
                    linkedHashMap.put("check.reason.backendCode", backendErrorException.getBackendCode());
                    linkedHashMap.put("check.reason.service", backendErrorException.getRequestId());
                    if (backendErrorException instanceof RightVBackendErrorException) {
                        linkedHashMap.put("check.reason.statusCode", String.valueOf(((RightVBackendErrorException) backendErrorException).getHttpStatusCodes()));
                    }
                } else {
                    String canonicalName = this.reason.getClass().getCanonicalName();
                    if (canonicalName == null) {
                        canonicalName = "UnknownClass";
                    }
                    linkedHashMap.put("check.reason.class", canonicalName);
                    String message4 = this.reason.getMessage();
                    if (message4 == null) {
                        message4 = "EmptyMessage";
                    }
                    linkedHashMap.put("check.reason.message", message4);
                }
                for (BackendReachability.BackendStatus backendStatus : this.reachability.getBackends()) {
                    linkedHashMap.put("availability." + backendStatus.getName() + ".isAlive", String.valueOf(backendStatus.getReachable()));
                    if (backendStatus.getReachable()) {
                        linkedHashMap.put("availability." + backendStatus.getName() + ".response", String.valueOf(backendStatus.getResponse()));
                    } else {
                        linkedHashMap.put("availability." + backendStatus.getName() + ".error", String.valueOf(backendStatus.getError()));
                        Throwable error = backendStatus.getError();
                        if (error != null && (message = error.getMessage()) != null) {
                            sentryEvent.setExtra(backendStatus.getName() + ".error.message", message);
                        }
                        Throwable error2 = backendStatus.getError();
                        if (error2 != null && (cause = error2.getCause()) != null) {
                            sentryEvent.setExtra(backendStatus.getName() + ".error.cause", cause);
                        }
                    }
                }
                URL url = this.url;
                if (url != null) {
                    linkedHashMap.put("check.reason.url", String.valueOf(url));
                    sentryEvent.setExtra("reason.url", String.valueOf(url));
                }
                sentryEvent.setTags(linkedHashMap);
                Log.v(ConnectivityStatusReportManagerKt.LOG_TAG, "Sentry: NO_BACKEND_REPORT " + this.reason);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.farkit.network.NetworkStatus.ConnectedNoBackendAccess");
            }
        } else {
            NetworkStatus networkStatus2 = this.status;
            if (networkStatus2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.farkit.network.NetworkStatus.ConnectedNoBackendAccess");
            }
            boolean z = ((NetworkStatus.ConnectedNoBackendAccess) networkStatus2).getReason() == UnreachabilityReason.SSLCannotBeValidated;
            sentryEvent = new SentryEvent();
            sentryEvent.setLevel(SentryLevel.WARNING);
            Message message5 = new Message();
            message5.setMessage(z ? CommonUtils.BACK_FROM_SYNC_FAILED_MODE_SENTRY_TITLE : CommonUtils.BACK_FROM_LIMITED_MODE_SENTRY_TITLE);
            sentryEvent.setMessage(message5);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put("component", "Degraded");
            linkedHashMap2.put("timer", String.valueOf(getIssueDuration()));
            sentryEvent.setTags(linkedHashMap2);
            StringBuilder sb = new StringBuilder();
            sb.append("Sentry: NO_BACKEND_REPORT END ");
            Message message6 = sentryEvent.getMessage();
            sb.append(message6 != null ? message6.getMessage() : null);
            Log.v(ConnectivityStatusReportManagerKt.LOG_TAG, sb.toString());
        }
        return sentryEvent;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:22:0x0151  */
    /* JADX WARN: Code duplicated, block: B:24:0x015f  */
    /* JADX WARN: Code duplicated, block: B:25:0x016a  */
    /* JADX WARN: Code duplicated, block: B:28:0x0173  */
    /* JADX WARN: Code duplicated, block: B:29:0x0176  */
    /* JADX WARN: Code duplicated, block: B:31:0x020c  */
    private final void reportToVO(SentryEvent event) {
        String string;
        VoFmaEvent.VOPlayerSeverity vOPlayerSeverity;
        VOPlayer.VOPlayerSeverity vOPlayerSeverity2;
        String message;
        String str;
        String str2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneOffset.UTC).format(Instant.now());
        Message message2 = event.getMessage();
        String message3 = message2 != null ? message2.getMessage() : null;
        if (message3 != null) {
            switch (message3) {
                case "Sync_failed_mode_root_cause":
                case "Limited_mode_root_cause":
                    VoFmaEvent.VOPlayerSeverity vOPlayerSeverity3 = VoFmaEvent.VOPlayerSeverity.SEVERITY_WARNING;
                    vOPlayerSeverity2 = VOPlayer.VOPlayerSeverity.SEVERITY_WARNING;
                    Pair[] pairArr = new Pair[11];
                    Message message4 = event.getMessage();
                    if (message4 != null) {
                        message = message4.getMessage();
                    } else {
                        message = null;
                    }
                    if (Intrinsics.areEqual(message, CommonUtils.SYNC_FAILED_MODE_REASON_SENTRY_TITLE)) {
                        str = "SYNC_FAILED_MODE_ON";
                    } else {
                        str = "LIMITED_MODE_ON";
                    }
                    pairArr[0] = TuplesKt.to("ERROR_TYPE", str);
                    pairArr[1] = TuplesKt.to("VERSION", "SR_ON_0.1");
                    pairArr[2] = TuplesKt.to("VO_PLAYER_ID", this.voPlayerUniqueId);
                    pairArr[3] = TuplesKt.to("TIMESTAMP", str2);
                    pairArr[4] = TuplesKt.to("APP_VERSION", BuildConfig.VERSION_NAME);
                    pairArr[5] = TuplesKt.to("NETWORK_TYPE", this.networkStateManager.getActiveNetworkType().getVoName());
                    pairArr[6] = TuplesKt.to("FIRMWARE_VERSION", DeviceInfo.INSTANCE.getFirmwareVersionName());
                    pairArr[7] = TuplesKt.to("DEVICE_MODEL", DeviceInfo.INSTANCE.getDeviceModel());
                    pairArr[8] = TuplesKt.to("SERIAL_NUMBER", this.serialNumber);
                    pairArr[9] = TuplesKt.to("ROOT_CAUSE_ANALYSIS", SentryEventExtentionsKt.getDegradedRootCauseAnalysis(event));
                    pairArr[10] = TuplesKt.to("GOOGLE_DNS_STATUS", MapsKt.mapOf(TuplesKt.to("STATUS", Boolean.valueOf(this.networkStateManager.isValidated()))));
                    string = new JSONObject(MapsKt.mapOf(pairArr)).toString();
                    vOPlayerSeverity = vOPlayerSeverity3;
                    break;
                case "Sync_Failed_mode_duration":
                    vOPlayerSeverity = VoFmaEvent.VOPlayerSeverity.SEVERITY_ERROR;
                    vOPlayerSeverity2 = VOPlayer.VOPlayerSeverity.SEVERITY_ERROR;
                    string = new JSONObject(MapsKt.mapOf(TuplesKt.to("ERROR_TYPE", "SYNC_FAILED_MODE_OFF"), TuplesKt.to("VERSION", "SR_OFF_0.1"), TuplesKt.to("VO_PLAYER_ID", this.voPlayerUniqueId), TuplesKt.to("TIMESTAMP", str2), TuplesKt.to("APP_VERSION", BuildConfig.VERSION_NAME), TuplesKt.to("NETWORK_TYPE", this.networkStateManager.getActiveNetworkType().getVoName()), TuplesKt.to("FIRMWARE_VERSION", DeviceInfo.INSTANCE.getFirmwareVersionName()), TuplesKt.to("DEVICE_MODEL", DeviceInfo.INSTANCE.getDeviceModel()), TuplesKt.to("SERIAL_NUMBER", this.serialNumber))).toString();
                    break;
                case "Limited_mode_duration":
                    vOPlayerSeverity = VoFmaEvent.VOPlayerSeverity.SEVERITY_ERROR;
                    vOPlayerSeverity2 = VOPlayer.VOPlayerSeverity.SEVERITY_ERROR;
                    string = new JSONObject(MapsKt.mapOf(TuplesKt.to("ERROR_TYPE", "LIMITED_MODE_OFF"), TuplesKt.to("VERSION", "SR_OFF_0.1"), TuplesKt.to("VO_PLAYER_ID", this.voPlayerUniqueId), TuplesKt.to("TIMESTAMP", str2), TuplesKt.to("APP_VERSION", BuildConfig.VERSION_NAME), TuplesKt.to("NETWORK_TYPE", this.networkStateManager.getActiveNetworkType().getVoName()), TuplesKt.to("FIRMWARE_VERSION", DeviceInfo.INSTANCE.getFirmwareVersionName()), TuplesKt.to("DEVICE_MODEL", DeviceInfo.INSTANCE.getDeviceModel()), TuplesKt.to("SERIAL_NUMBER", this.serialNumber))).toString();
                    break;
                default:
                    string = new JSONObject(MapsKt.mapOf(TuplesKt.to("error", "Unknown error"))).toString();
                    vOPlayerSeverity = null;
                    vOPlayerSeverity2 = null;
                    break;
            }
        } else {
            string = new JSONObject(MapsKt.mapOf(TuplesKt.to("error", "Unknown error"))).toString();
            vOPlayerSeverity = null;
            vOPlayerSeverity2 = null;
        }
        Intrinsics.checkExpressionValueIsNotNull(string, "when (event.message?.mes…r\")).toString()\n        }");
        Log.v(ConnectivityStatusReportManagerKt.LOG_TAG, "Send to VO Frontend Monitoring Agent : \n " + new JSONObject(string).toString(4));
        if (vOPlayerSeverity2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("severityVOP");
        }
        Message message5 = event.getMessage();
        VOPlayer.logEvent(vOPlayerSeverity2, message5 != null ? message5.getMessage() : null, string);
        VoFmaController fmaController = VoFmaController.getFmaController();
        if (vOPlayerSeverity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("severityFMA");
        }
        Message message6 = event.getMessage();
        fmaController.logEvent(vOPlayerSeverity, message6 != null ? message6.getMessage() : null, string);
    }
}
