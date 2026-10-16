package com.dotscreen.portal.ads;

import com.dotscreen.farkit.tools.DSLog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AdLoggingHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00032\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/portal/ads/AdLoggingHelper;", "", "()V", "LogHelper", "OPERATION", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class AdLoggingHelper {

    /* JADX INFO: renamed from: LogHelper, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: AdLoggingHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0010\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/portal/ads/AdLoggingHelper$OPERATION;", "", "(Ljava/lang/String;I)V", "INIT_CONFIG", "GET_AD_UNIT_IDs_FOR_PAGE", "GET_ADS_FOR_PAGE", "REQUEST_ADS", "GET_ADS_FOR_PAGE_onSuccess", "GET_ADS_FOR_PAGE_onFailure", "HANDLE_AD", "DISPLAY_FLOATING_AD", "DISPLAY_DISMISSIBLE_FLOATING_AD", "HIDE_ALL_FLOATING_ADS", "HIDE_ALL_DISMISSIBLE_FLOATING_ADS", "HIDE_BANNER", "AD_TRACKING", "LOAD_AD_IMAGE", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public enum OPERATION {
        INIT_CONFIG,
        GET_AD_UNIT_IDs_FOR_PAGE,
        GET_ADS_FOR_PAGE,
        REQUEST_ADS,
        GET_ADS_FOR_PAGE_onSuccess,
        GET_ADS_FOR_PAGE_onFailure,
        HANDLE_AD,
        DISPLAY_FLOATING_AD,
        DISPLAY_DISMISSIBLE_FLOATING_AD,
        HIDE_ALL_FLOATING_ADS,
        HIDE_ALL_DISMISSIBLE_FLOATING_ADS,
        HIDE_BANNER,
        AD_TRACKING,
        LOAD_AD_IMAGE
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ads.AdLoggingHelper$LogHelper, reason: from kotlin metadata */
    /* JADX INFO: compiled from: AdLoggingHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JH\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\nJu\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/portal/ads/AdLoggingHelper$LogHelper;", "", "()V", "generalLog", "", "logLevel", "", "operation", "Lcom/dotscreen/portal/ads/AdLoggingHelper$OPERATION;", "activityName", "", "dsAdUnitId", "voAdUnitId", "result", "message", "specificLog", "activityOrFragment", "adType", "runtimeTag", "imageResourceIndex", "adEvent", "(ILjava/lang/String;Lcom/dotscreen/portal/ads/AdLoggingHelper$OPERATION;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void generalLog(int logLevel, OPERATION operation, String activityName, String dsAdUnitId, String voAdUnitId, String result, String message) {
            String str;
            String str2;
            String str3;
            Intrinsics.checkParameterIsNotNull(operation, "operation");
            String str4 = activityName;
            String str5 = "";
            if (str4 == null || str4.length() == 0) {
                str = "";
            } else {
                str = '[' + activityName + ']';
            }
            String str6 = dsAdUnitId;
            if (str6 == null || str6.length() == 0) {
                str2 = "";
            } else {
                str2 = "[DS:" + dsAdUnitId + ']';
            }
            String str7 = voAdUnitId;
            if (str7 == null || str7.length() == 0) {
                str3 = "";
            } else {
                str3 = "[VO:" + voAdUnitId + ']';
            }
            String str8 = result;
            if (str8 == null || str8.length() == 0) {
                result = "";
            }
            String str9 = message;
            if (!(str9 == null || str9.length() == 0)) {
                str5 = " : " + message;
            }
            if (logLevel == 6) {
                DSLog.INSTANCE.adLogE('[' + operation + ']' + str + str2 + str3 + " --> " + result + str5);
                return;
            }
            DSLog.INSTANCE.adLogE('[' + operation + ']' + str + str2 + str3 + " --> " + result + str5);
        }

        public final void specificLog(int logLevel, String activityOrFragment, OPERATION operation, String adType, String dsAdUnitId, String voAdUnitId, String runtimeTag, Integer imageResourceIndex, String adEvent, String result, String message) {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            Intrinsics.checkParameterIsNotNull(operation, "operation");
            String str8 = activityOrFragment;
            String str9 = "";
            if (str8 == null || str8.length() == 0) {
                str = "";
            } else {
                str = "--- " + activityOrFragment + '-';
            }
            String str10 = adType;
            if (str10 == null || str10.length() == 0) {
                str2 = "";
            } else {
                str2 = '[' + adType + ']';
            }
            String str11 = dsAdUnitId;
            if (str11 == null || str11.length() == 0) {
                str3 = "";
            } else {
                str3 = "[DS:" + dsAdUnitId + ']';
            }
            String str12 = voAdUnitId;
            if (str12 == null || str12.length() == 0) {
                str4 = "";
            } else {
                str4 = "[VO:" + voAdUnitId + ']';
            }
            String str13 = runtimeTag;
            if (str13 == null || str13.length() == 0) {
                str5 = "";
            } else {
                str5 = "[RT-tag:" + runtimeTag + ']';
            }
            if (imageResourceIndex == null) {
                str6 = "";
            } else {
                str6 = "[imageResource n°" + imageResourceIndex + ']';
            }
            if (adEvent == null) {
                str7 = "";
            } else {
                str7 = '[' + adEvent + ']';
            }
            String str14 = result;
            String str15 = str14 == null || str14.length() == 0 ? "" : result;
            String str16 = message;
            if (!(str16 == null || str16.length() == 0)) {
                str9 = " : " + message;
            }
            if (logLevel == 6) {
                DSLog.INSTANCE.adLogE(str + '[' + operation + ']' + str2 + str3 + str4 + str5 + str6 + str7 + " --> " + str15 + str9);
                return;
            }
            DSLog.INSTANCE.adLogE(str + '[' + operation + ']' + str2 + str3 + str4 + str5 + str6 + str7 + " --> " + str15 + str9);
        }
    }
}
