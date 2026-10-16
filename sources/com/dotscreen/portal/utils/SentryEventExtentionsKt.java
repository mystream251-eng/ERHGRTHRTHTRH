package com.dotscreen.portal.utils;

import androidx.core.app.NotificationCompat;
import io.sentry.SentryEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SentryEventExtentions.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004\u001a\u0016\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004\u001a\u0016\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004¨\u0006\u0007"}, d2 = {"getDegradedRootCauseAnalysis", "", "", "", "Lio/sentry/SentryEvent;", "getInternetRootCauseAnalysis", "getOTARootCauseAnalysis", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class SentryEventExtentionsKt {
    public static final Map<String, Object> getDegradedRootCauseAnalysis(SentryEvent getDegradedRootCauseAnalysis) {
        String tag;
        Intrinsics.checkParameterIsNotNull(getDegradedRootCauseAnalysis, "$this$getDegradedRootCauseAnalysis");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        String tag2 = getDegradedRootCauseAnalysis.getTag("availability.uiop.isAlive");
        if (tag2 != null) {
            if (Intrinsics.areEqual(tag2, "true")) {
                linkedHashMap3.put("UIOP", MapsKt.mapOf(TuplesKt.to("STATUS", true), TuplesKt.to("DESCRIPTION", getDegradedRootCauseAnalysis.getTag("availability.uiop.response"))));
            } else {
                linkedHashMap3.put("UIOP", MapsKt.mapOf(TuplesKt.to("STATUS", false), TuplesKt.to("DESCRIPTION", getDegradedRootCauseAnalysis.getTag("availability.uiop.error"))));
            }
        }
        String tag3 = getDegradedRootCauseAnalysis.getTag("availability.rightv.isAlive");
        if (tag3 != null) {
            if (Intrinsics.areEqual(tag3, "true")) {
                linkedHashMap3.put("RiGHTv", MapsKt.mapOf(TuplesKt.to("STATUS", true), TuplesKt.to("DESCRIPTION", getDegradedRootCauseAnalysis.getTag("availability.rightv.response"))));
            } else {
                linkedHashMap3.put("RiGHTv", MapsKt.mapOf(TuplesKt.to("STATUS", false), TuplesKt.to("DESCRIPTION", getDegradedRootCauseAnalysis.getTag("availability.rightv.error"))));
            }
        }
        String tag4 = getDegradedRootCauseAnalysis.getTag("availability.compass.isAlive");
        if (tag4 != null) {
            if (Intrinsics.areEqual(tag4, "true")) {
                linkedHashMap3.put("CMP", MapsKt.mapOf(TuplesKt.to("STATUS", true), TuplesKt.to("DESCRIPTION", getDegradedRootCauseAnalysis.getTag("availability.compass.response"))));
            } else {
                linkedHashMap3.put("CMP", MapsKt.mapOf(TuplesKt.to("STATUS", false), TuplesKt.to("DESCRIPTION", getDegradedRootCauseAnalysis.getTag("availability.compass.error"))));
            }
        }
        String it = getDegradedRootCauseAnalysis.getTag("check.reason.url");
        if (it != null) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            linkedHashMap2.put("URL_PATH", it);
        }
        String it2 = getDegradedRootCauseAnalysis.getTag("check.reason.statusCode");
        if (it2 != null) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            linkedHashMap2.put("STATUS_CODE", Integer.valueOf(Integer.parseInt(it2)));
        }
        if (getDegradedRootCauseAnalysis.getTag("check.reason.backendCode") != null) {
            tag = getDegradedRootCauseAnalysis.getTag("check.reason.backendCode");
        } else {
            tag = getDegradedRootCauseAnalysis.getTag("check.reason.class") + ": " + getDegradedRootCauseAnalysis.getTag("check.reason.message");
        }
        if (tag == null) {
            tag = "empty";
        }
        linkedHashMap2.put("DESCRIPTION", tag);
        linkedHashMap.put("ERROR_TRIGGER", linkedHashMap2);
        linkedHashMap.put("CHECKS", linkedHashMap3);
        return linkedHashMap;
    }

    public static final Map<String, Object> getInternetRootCauseAnalysis(SentryEvent getInternetRootCauseAnalysis) {
        Intrinsics.checkParameterIsNotNull(getInternetRootCauseAnalysis, "$this$getInternetRootCauseAnalysis");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        if (getInternetRootCauseAnalysis.getTag("server.time.url") != null) {
            linkedHashMap3.put("timeServer", MapsKt.mapOf(TuplesKt.to("ip", getInternetRootCauseAnalysis.getTag("server.time.ip"))));
        }
        if (getInternetRootCauseAnalysis.getTag("server.google.icmp.url") != null) {
            linkedHashMap3.put("googleIcmp", MapsKt.mapOf(TuplesKt.to("ip", getInternetRootCauseAnalysis.getTag("server.google.icmp.ip"))));
        }
        if (getInternetRootCauseAnalysis.getTag("server.xviewplus.url") != null) {
            linkedHashMap3.put("xviewplus", MapsKt.mapOf(TuplesKt.to("ip", getInternetRootCauseAnalysis.getTag("server.xviewplus.ip"))));
        }
        if (getInternetRootCauseAnalysis.getTag("server.connectivitycheck.url") != null) {
            linkedHashMap2.put("httpConnectivityCheck", MapsKt.mapOf(TuplesKt.to("statuscode", getInternetRootCauseAnalysis.getTag("server.http.connectivitycheck.statuscode")), TuplesKt.to("date", getInternetRootCauseAnalysis.getTag("server.http.connectivitycheck.date"))));
        }
        if (getInternetRootCauseAnalysis.getTag("server.https.url") != null) {
            linkedHashMap2.put("httpsConnectivityCheck", MapsKt.mapOf(TuplesKt.to("statuscode", getInternetRootCauseAnalysis.getTag("server.https.statuscode")), TuplesKt.to("error", getInternetRootCauseAnalysis.getTag("server.https.error"))));
        }
        linkedHashMap.put("httpStatus", linkedHashMap2);
        linkedHashMap.put("ping", linkedHashMap3);
        return linkedHashMap;
    }

    public static final Map<String, Object> getOTARootCauseAnalysis(SentryEvent getOTARootCauseAnalysis) {
        Intrinsics.checkParameterIsNotNull(getOTARootCauseAnalysis, "$this$getOTARootCauseAnalysis");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String apkName = getOTARootCauseAnalysis.getTag("updater.apk");
        if (apkName != null) {
            Intrinsics.checkExpressionValueIsNotNull(apkName, "apkName");
            linkedHashMap.put("apk", apkName);
        }
        String status = getOTARootCauseAnalysis.getTag("updater.status");
        if (status != null) {
            Intrinsics.checkExpressionValueIsNotNull(status, "status");
            linkedHashMap.put(NotificationCompat.CATEGORY_STATUS, status);
        }
        Object currentVersion = getOTARootCauseAnalysis.getExtra("currentInstalledVersion");
        if (currentVersion != null) {
            Intrinsics.checkExpressionValueIsNotNull(currentVersion, "currentVersion");
            linkedHashMap.put("currentInstalledVersion", currentVersion);
        }
        Object extra = getOTARootCauseAnalysis.getExtra("reason.class");
        if (extra != null) {
            Map mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("class", extra), TuplesKt.to("message", getOTARootCauseAnalysis.getExtra("reason.message")), TuplesKt.to("details", getOTARootCauseAnalysis.getExtra("reason.details")));
            Object extra2 = getOTARootCauseAnalysis.getExtra("reason.cause");
            if (extra2 != null) {
                mapMutableMapOf.put("cause", extra2);
            }
            linkedHashMap.put("exception", mapMutableMapOf);
        }
        Object extra3 = getOTARootCauseAnalysis.getExtra("reasonMessage");
        if (extra3 != null) {
            Map mapMutableMapOf2 = MapsKt.mutableMapOf(TuplesKt.to("message", extra3), TuplesKt.to("details", getOTARootCauseAnalysis.getExtra("reason")));
            Object extra4 = getOTARootCauseAnalysis.getExtra("cause");
            if (extra4 != null) {
                mapMutableMapOf2.put("cause", extra4);
            }
            linkedHashMap.put("exception", mapMutableMapOf2);
        }
        Object extra5 = getOTARootCauseAnalysis.getExtra("downloaded");
        if (extra5 != null) {
            linkedHashMap.put("downloadInformation", MapsKt.mapOf(TuplesKt.to("downloadedSize", extra5), TuplesKt.to("apkSize", getOTARootCauseAnalysis.getExtra("total"))));
        }
        return linkedHashMap;
    }
}
