package com.dotscreen.portal.utils.reporting;

import android.net.NetworkCapabilities;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.dotscreen.farkit.network.NetworkStateManager;
import com.dotscreen.farkit.network.NetworkStatus;
import com.dotscreen.farkit.network.PingData;
import com.dotscreen.farkit.network.ServerStatus;
import com.dotscreen.portal.BuildConfig;
import com.dotscreen.portal.utils.SentryEventExtentionsKt;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.DeviceInfo;
import com.dotscreen.tv.common.utils.SentryReportHint;
import com.viaccessorca.analytics.fma.VoFmaController;
import com.viaccessorca.analytics.fma.VoFmaEvent;
import com.viaccessorca.voplayer.VOPlayer;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.protocol.Message;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: NoInternetReport.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/portal/utils/reporting/NoInternetReport;", "Lcom/dotscreen/portal/utils/reporting/AsynchronousReport;", "networkStateManager", "Lcom/dotscreen/farkit/network/NetworkStateManager;", "voPlayerUniqueId", "", "serialNumber", NotificationCompat.CATEGORY_STATUS, "Lcom/dotscreen/farkit/network/NetworkStatus;", "(Lcom/dotscreen/farkit/network/NetworkStateManager;Ljava/lang/String;Ljava/lang/String;Lcom/dotscreen/farkit/network/NetworkStatus;)V", "createSentryEvent", "Lio/sentry/SentryEvent;", "isStart", "", "endReport", "", "endDate", "Ljava/util/Date;", "reportToVO", NotificationCompat.CATEGORY_EVENT, "startReport", "startDate", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class NoInternetReport extends AsynchronousReport {
    private final NetworkStateManager networkStateManager;
    private final String serialNumber;
    private final NetworkStatus status;
    private final String voPlayerUniqueId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoInternetReport(NetworkStateManager networkStateManager, String voPlayerUniqueId, String serialNumber, NetworkStatus status) {
        super("NO_INTERNET_REPORT");
        Intrinsics.checkParameterIsNotNull(networkStateManager, "networkStateManager");
        Intrinsics.checkParameterIsNotNull(voPlayerUniqueId, "voPlayerUniqueId");
        Intrinsics.checkParameterIsNotNull(serialNumber, "serialNumber");
        Intrinsics.checkParameterIsNotNull(status, "status");
        this.networkStateManager = networkStateManager;
        this.voPlayerUniqueId = voPlayerUniqueId;
        this.serialNumber = serialNumber;
        this.status = status;
    }

    @Override // com.dotscreen.portal.utils.reporting.AsynchronousReport
    public void startReport(Date startDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        super.startReport(startDate);
        SentryEvent sentryEventCreateSentryEvent = createSentryEvent(true);
        Sentry.captureEvent(sentryEventCreateSentryEvent, SentryReportHint.Internet);
        reportToVO(sentryEventCreateSentryEvent);
    }

    @Override // com.dotscreen.portal.utils.reporting.AsynchronousReport
    public void endReport(Date endDate) {
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        super.endReport(endDate);
        SentryEvent sentryEventCreateSentryEvent = createSentryEvent(false);
        Sentry.captureEvent(sentryEventCreateSentryEvent, SentryReportHint.Internet);
        reportToVO(sentryEventCreateSentryEvent);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final SentryEvent createSentryEvent(boolean isStart) {
        SentryEvent sentryEvent;
        String resolvedIp;
        String resolvedIp2;
        String resolvedIp3;
        if (isStart) {
            sentryEvent = new SentryEvent();
            sentryEvent.setLevel(SentryLevel.WARNING);
            Message message = new Message();
            message.setMessage(CommonUtils.NO_INTERNET_MODE_REASON_SENTRY_TITLE);
            sentryEvent.setMessage(message);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("component", "Internet");
            NetworkCapabilities currentActiveNetworkCapabilities = this.networkStateManager.getCurrentActiveNetworkCapabilities();
            if (currentActiveNetworkCapabilities != null) {
                linkedHashMap.put("capability.validated", String.valueOf(currentActiveNetworkCapabilities.hasCapability(16)));
                linkedHashMap.put("capability.internet", String.valueOf(currentActiveNetworkCapabilities.hasCapability(12)));
            }
            NetworkStatus networkStatus = this.status;
            if (networkStatus instanceof NetworkStatus.ConnectedNoInternet) {
                for (ServerStatus serverStatus : networkStatus.getServerStatus()) {
                    String url = serverStatus.getUrl();
                    String str = "Unresolved";
                    switch (url.hashCode()) {
                        case -1632447086:
                            if (url.equals("https://www.google.com")) {
                                linkedHashMap.put("server.https.url", serverStatus.getUrl());
                                linkedHashMap.put("server.https.statuscode", String.valueOf(serverStatus.getStatusCode()));
                                if (serverStatus.getExtra().containsKey("errorMessage")) {
                                    linkedHashMap.put("server.https.error", String.valueOf(serverStatus.getExtra().get("errorMessage")));
                                }
                                sentryEvent.setExtra("server.connectivitycheck.data", serverStatus);
                            } else {
                                sentryEvent.setExtra("server.unknown.data", serverStatus);
                            }
                            break;
                        case -1161062878:
                            if (url.equals("http://connectivitycheck.gstatic.com/generate_204")) {
                                linkedHashMap.put("server.connectivitycheck.url", serverStatus.getUrl());
                                linkedHashMap.put("server.http.connectivitycheck.statuscode", String.valueOf(serverStatus.getStatusCode()));
                                String str2 = serverStatus.getExtra().get("date");
                                if (str2 == null) {
                                    str2 = "N/A";
                                }
                                linkedHashMap.put("server.http.connectivitycheck.date", str2);
                                sentryEvent.setExtra("server.connectivitycheck.data", serverStatus);
                            } else {
                                sentryEvent.setExtra("server.unknown.data", serverStatus);
                            }
                            break;
                        case -702889725:
                            if (url.equals("www.google.com")) {
                                linkedHashMap.put("server.google.icmp.url", serverStatus.getUrl());
                                PingData pingData = serverStatus.getPingData();
                                if (pingData != null && (resolvedIp2 = pingData.getResolvedIp()) != null) {
                                    str = resolvedIp2;
                                }
                                linkedHashMap.put("server.google.icmp.ip", str);
                                sentryEvent.setExtra("server.google.icmp.data", serverStatus);
                            } else {
                                sentryEvent.setExtra("server.unknown.data", serverStatus);
                            }
                            break;
                        case -269283359:
                            if (url.equals("time.android.com")) {
                                linkedHashMap.put("server.time.url", serverStatus.getUrl());
                                PingData pingData2 = serverStatus.getPingData();
                                if (pingData2 != null && (resolvedIp3 = pingData2.getResolvedIp()) != null) {
                                    str = resolvedIp3;
                                }
                                linkedHashMap.put("server.time.ip", str);
                                sentryEvent.setExtra("server.time.data", serverStatus);
                            } else {
                                sentryEvent.setExtra("server.unknown.data", serverStatus);
                            }
                            break;
                        case 1238683855:
                            if (url.equals("xviewplus.com.mx")) {
                                linkedHashMap.put("server.xviewplus.url", serverStatus.getUrl());
                                PingData pingData3 = serverStatus.getPingData();
                                if (pingData3 != null && (resolvedIp = pingData3.getResolvedIp()) != null) {
                                    str = resolvedIp;
                                }
                                linkedHashMap.put("server.xviewplus.ip", str);
                                sentryEvent.setExtra("server.xviewplus.icmp.data", serverStatus);
                            } else {
                                sentryEvent.setExtra("server.unknown.data", serverStatus);
                            }
                            break;
                        default:
                            sentryEvent.setExtra("server.unknown.data", serverStatus);
                            break;
                    }
                }
            }
            sentryEvent.setTags(linkedHashMap);
            Log.v(ConnectivityStatusReportManagerKt.LOG_TAG, "Sentry: NO_INTERNET_REPORT START");
        } else {
            sentryEvent = new SentryEvent();
            sentryEvent.setLevel(SentryLevel.WARNING);
            Message message2 = new Message();
            message2.setMessage(CommonUtils.BACK_FROM_NO_INTERNET_MODE_SENTRY_TITLE);
            sentryEvent.setMessage(message2);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put("component", "Internet");
            linkedHashMap2.put("timer", String.valueOf(getIssueDuration()));
            sentryEvent.setTags(linkedHashMap2);
            StringBuilder sb = new StringBuilder();
            sb.append("Sentry: NO_INTERNET_REPORT END ");
            Message message3 = sentryEvent.getMessage();
            sb.append(message3 != null ? message3.getMessage() : null);
            Log.v(ConnectivityStatusReportManagerKt.LOG_TAG, sb.toString());
        }
        return sentryEvent;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0144  */
    private final void reportToVO(SentryEvent event) {
        String string;
        VOPlayer.VOPlayerSeverity vOPlayerSeverity;
        VoFmaEvent.VOPlayerSeverity vOPlayerSeverity2;
        String str = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneOffset.UTC).format(Instant.now());
        Message message = event.getMessage();
        String message2 = message != null ? message.getMessage() : null;
        String str2 = CommonUtils.NO_INTERNET_MODE_REASON_SENTRY_TITLE;
        if (message2 != null) {
            int iHashCode = message2.hashCode();
            if (iHashCode == 897327752) {
                if (message2.equals(CommonUtils.NO_INTERNET_MODE_REASON_SENTRY_TITLE)) {
                    vOPlayerSeverity = VOPlayer.VOPlayerSeverity.SEVERITY_WARNING;
                    vOPlayerSeverity2 = VoFmaEvent.VOPlayerSeverity.SEVERITY_WARNING;
                    string = new JSONObject(MapsKt.mapOf(TuplesKt.to("ERROR_TYPE", "NO_INTERNET_MODE_ON"), TuplesKt.to("VO_PLAYER_ID", this.voPlayerUniqueId), TuplesKt.to("TIMESTAMP", str), TuplesKt.to("APP_VERSION", BuildConfig.VERSION_NAME), TuplesKt.to("NETWORK_TYPE", this.networkStateManager.getActiveNetworkType().getVoName()), TuplesKt.to("FIRMWARE_VERSION", DeviceInfo.INSTANCE.getFirmwareVersionName()), TuplesKt.to("DEVICE_MODEL", DeviceInfo.INSTANCE.getDeviceModel()), TuplesKt.to("SERIAL_NUMBER", this.serialNumber), TuplesKt.to("ROOT_CAUSE_ANALYSIS", SentryEventExtentionsKt.getInternetRootCauseAnalysis(event)))).toString();
                } else {
                    string = new JSONObject(MapsKt.mapOf(TuplesKt.to("error", "Unknown error"))).toString();
                    vOPlayerSeverity = null;
                    str2 = null;
                    vOPlayerSeverity2 = null;
                }
            } else if (iHashCode == 2045839440 && message2.equals(CommonUtils.BACK_FROM_NO_INTERNET_MODE_SENTRY_TITLE)) {
                vOPlayerSeverity = VOPlayer.VOPlayerSeverity.SEVERITY_ERROR;
                VoFmaEvent.VOPlayerSeverity vOPlayerSeverity3 = VoFmaEvent.VOPlayerSeverity.SEVERITY_ERROR;
                string = new JSONObject(MapsKt.mapOf(TuplesKt.to("ERROR_TYPE", "INTERNET_MODE_ON"), TuplesKt.to("VO_PLAYER_ID", this.voPlayerUniqueId), TuplesKt.to("TIMESTAMP", str), TuplesKt.to("APP_VERSION", BuildConfig.VERSION_NAME), TuplesKt.to("NETWORK_TYPE", this.networkStateManager.getActiveNetworkType().getVoName()), TuplesKt.to("FIRMWARE_VERSION", DeviceInfo.INSTANCE.getFirmwareVersionName()), TuplesKt.to("DEVICE_MODEL", DeviceInfo.INSTANCE.getDeviceModel()), TuplesKt.to("SERIAL_NUMBER", this.serialNumber))).toString();
                vOPlayerSeverity2 = vOPlayerSeverity3;
                str2 = "No_internet_is_back";
            } else {
                string = new JSONObject(MapsKt.mapOf(TuplesKt.to("error", "Unknown error"))).toString();
                vOPlayerSeverity = null;
                str2 = null;
                vOPlayerSeverity2 = null;
            }
        } else {
            string = new JSONObject(MapsKt.mapOf(TuplesKt.to("error", "Unknown error"))).toString();
            vOPlayerSeverity = null;
            str2 = null;
            vOPlayerSeverity2 = null;
        }
        Intrinsics.checkExpressionValueIsNotNull(string, "when (event.message?.mes…r\")).toString()\n        }");
        Log.v(ConnectivityStatusReportManagerKt.LOG_TAG, "Send to VO Frontend Monitoring Agent : \n " + new JSONObject(string).toString(4));
        if (vOPlayerSeverity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("severityVOP");
        }
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tagVOP");
        }
        VOPlayer.logEvent(vOPlayerSeverity, str2, string);
        VoFmaController fmaController = VoFmaController.getFmaController();
        if (vOPlayerSeverity2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("severityFMA");
        }
        Message message3 = event.getMessage();
        fmaController.logEvent(vOPlayerSeverity2, message3 != null ? message3.getMessage() : null, string);
    }
}
