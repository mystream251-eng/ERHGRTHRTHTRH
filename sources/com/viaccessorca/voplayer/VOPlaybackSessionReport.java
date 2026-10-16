package com.viaccessorca.voplayer;

import android.content.Context;
import android.os.Build;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class VOPlaybackSessionReport {
    public static final String NETWORK_TYPE_CELLULAR = "CELLULAR";
    public static final String NETWORK_TYPE_ETHERNET = "ETHERNET";
    public static final String NETWORK_TYPE_MIXED = "MIXED";
    public static final String NETWORK_TYPE_OFFLINE = "OFFLINE";
    public static final String NETWORK_TYPE_WIFI = "WIFI";
    public static final String SESSION_TYPE_EVENT = "EVENT";
    public static final String SESSION_TYPE_LIVE = "LIVE";
    public static final String SESSION_TYPE_LIVEMABR = "LIVE_MABR";
    public static final String SESSION_TYPE_VOD = "VOD";
    public static final String SES_CONTENT_DURATION = "SES_CONTENT_DURATION";
    public static final String SES_SESSION_DURATION = "SES_SESSION_DURATION";
    public static final String SES_SESSION_NETWORK_TYPE = "SES_SESSION_NETWORK-TYPE";
    public static final String SES_SESSION_TYPE = "SES_SESSION_TYPE";
    public static final String STR_ALTERNATE_SWITCH_DN = "STR_ALTERNATE_SWITCH-DN";
    public static final String STR_ALTERNATE_SWITCH_UP = "STR_ALTERNATE_SWITCH-UP";
    public static final String STR_BANDWIDTH_AVG = "STR_BANDWIDTH_AVG";
    public static final String STR_REBUFFERING_NB = "STR_REBUFFERING_NB";
    public static final String STR_REBUFFERING_TIME_AVG = "STR_REBUFFERING_TIME_AVG";
    public static final String SVA_MEDIA_BITRATE_AVG = "SVA_MEDIA-BITRATE-AVG";
    public static final String UNKNOWN_SVALUE = "Unknown";
    static Boolean e = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<String, Object> f72a;
    private Map<String, Object> b;
    protected String c;
    protected String d;

    public static class KeepClassClearAppInfos {
        public String m329t7p6390828478448;
        public String m329t7p6390828478449;
        public String m329t7p6390828478465;
        public String m329t7p6390828478467;
        public String m329t7p6390828478468;
        public String m329t7p6390828478469;
        public String m329t7p6390828478470;
        public String m329t7p6390828478472;
        public String m329t7p6390828478473;
        public String m329t7p6390828478474;
    }

    VOPlaybackSessionReport() {
        this.f72a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.f72a = null;
        this.b = null;
        this.c = null;
        this.d = null;
    }

    VOPlaybackSessionReport(Context context, boolean z, boolean z2, String str, String str2) {
        this.f72a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        a(context, z, z2, str, str2);
        this.f72a = null;
        this.b = null;
        this.c = null;
        this.d = null;
    }

    private static native boolean MzYwOTdDRkZENDY5NDAxQUJBMDVBNUQ1QUMyMTY4M0QK(int i);

    private static native int MzdFQTdFOUNFMEQ0NDI1NUI2N0ExRjg1QUY4NzlEOEIK(String str, Object obj, boolean z, boolean z2, String str2);

    private static native int NkE4ODRBQzdFQjMyNEE4MUE1NjlCMEU3MTU5Q0Y5N0UK();

    private static native String NzJDOTcxQTQ4QzI3NDlFNkE3RjkwREZDQ0M0RDYyN0QK(int i);

    private static Boolean a(Context context, boolean z, boolean z2, String str, String str2) {
        File filesDir;
        if (e.booleanValue()) {
            return true;
        }
        if (context != null && (filesDir = context.getFilesDir()) != null) {
            try {
                KeepClassClearAppInfos keepClassClearAppInfos = new KeepClassClearAppInfos();
                String packageName = context.getPackageName();
                VOSystemInfo systemInfo = VOSystemInfoRetriever.getSystemInfo(context);
                keepClassClearAppInfos.m329t7p6390828478448 = filesDir.getAbsolutePath();
                keepClassClearAppInfos.m329t7p6390828478465 = filesDir.getAbsolutePath() + "/";
                keepClassClearAppInfos.m329t7p6390828478449 = packageName;
                keepClassClearAppInfos.m329t7p6390828478467 = str2;
                keepClassClearAppInfos.m329t7p6390828478468 = Build.HARDWARE;
                keepClassClearAppInfos.m329t7p6390828478469 = Build.MODEL;
                keepClassClearAppInfos.m329t7p6390828478470 = Math.max(systemInfo.screenWidth, systemInfo.screenHeight) + "x" + Math.min(systemInfo.screenWidth, systemInfo.screenHeight);
                keepClassClearAppInfos.m329t7p6390828478472 = systemInfo.isTV ? "Android-TV" : "Android";
                keepClassClearAppInfos.m329t7p6390828478473 = Build.VERSION.RELEASE;
                keepClassClearAppInfos.m329t7p6390828478474 = str;
                String sdkVersion = null;
                try {
                    sdkVersion = VOPlayer.getSdkVersion(context);
                } catch (Exception unused) {
                }
                if (MzdFQTdFOUNFMEQ0NDI1NUI2N0ExRjg1QUY4NzlEOEIK(context.getFilesDir().getPath(), keepClassClearAppInfos, z, z2, sdkVersion) != 0) {
                    return false;
                }
                e = true;
                return true;
            } catch (Exception unused2) {
                return false;
            }
        }
        return false;
    }

    public static int getLastSentReportsTime() {
        return NkE4ODRBQzdFQjMyNEE4MUE1NjlCMEU3MTU5Q0Y5N0UK();
    }

    public static String getStoredReport(int i) {
        return NzJDOTcxQTQ4QzI3NDlFNkE3RjkwREZDQ0M0RDYyN0QK(i);
    }

    public static boolean setLastSentReportsTime(int i) {
        return MzYwOTdDRkZENDY5NDAxQUJBMDVBNUQ1QUMyMTY4M0QK(i);
    }

    final void a() {
        Map<String, Object> map;
        Object objValueOf;
        Map<String, Object> map2;
        Object objValueOf2;
        if (this.f72a == null) {
            this.f72a = new HashMap();
        }
        String str = this.c;
        if (str == null) {
            return;
        }
        try {
            for (String str2 : str.split(", ")) {
                String[] strArrSplit = str2.split(" = ");
                if (strArrSplit != null && strArrSplit.length > 1 && strArrSplit[0] != null && strArrSplit[1] != null) {
                    String str3 = strArrSplit[0];
                    String str4 = strArrSplit[1];
                    if (str3.equals(STR_BANDWIDTH_AVG) || str3.equals(SVA_MEDIA_BITRATE_AVG) || str3.equals(STR_REBUFFERING_NB) || str3.equals(STR_ALTERNATE_SWITCH_UP) || str3.equals(STR_ALTERNATE_SWITCH_DN)) {
                        map2 = this.f72a;
                        objValueOf2 = Integer.valueOf(Integer.parseInt(str4));
                    } else if (str3.equals(SES_SESSION_DURATION) || str3.equals(SES_CONTENT_DURATION) || str3.equals(STR_REBUFFERING_TIME_AVG)) {
                        map2 = this.f72a;
                        objValueOf2 = Float.valueOf(Float.parseFloat(str4));
                    } else {
                        map2 = this.f72a;
                        objValueOf2 = str4;
                    }
                    map2.put(str3, objValueOf2);
                }
            }
            if (this.d != null && this.b == null) {
                this.b = new HashMap();
                for (String str5 : this.d.split(", ")) {
                    String[] strArrSplit2 = str5.split(" = ");
                    if (strArrSplit2 != null && strArrSplit2.length > 1 && strArrSplit2[0] != null && strArrSplit2[1] != null) {
                        String str6 = strArrSplit2[0];
                        String str7 = strArrSplit2[1];
                        if (str6.equals("MDA_DEVICE_OS_ROOTED") || str6.equals("STR_BANDWIDTH_MAX") || str6.equals("STR_BANDWIDTH_MIN") || str6.equals("STR_REBUFFERING_NB_LAST-0.5MIN") || str6.equals("STR_REBUFFERING_NB_LAST-01MIN") || str6.equals("STR_REBUFFERING_NB_LAST-02MIN") || str6.equals("STR_REBUFFERING_NB_LAST-05MIN") || str6.equals("STR_REBUFFERING_NB_LAST-10MIN") || str6.equals("STR_CONTINUOUS-PLAYBACK_MIN") || str6.equals("STR_CONTINUOUS-PLAYBACK_MAX") || str6.equals("SVA_VIDEO-START-FAILURE") || str6.equals("APP_PAUSE_NB") || str6.equals("APP_SEEK_NB") || str6.equals("STR_ALTERNATE_LAST") || str6.equals("STR_ALTERNATE_MIN") || str6.equals("STR_ALTERNATE_MAX")) {
                            map = this.b;
                            objValueOf = Integer.valueOf(Integer.parseInt(str7));
                        } else if (str6.equals("STR_BANDWIDTH_STD") || str6.equals("SES_TIME_OPTIMAL-QUALITY") || str6.equals("STR_REBUFFERING_TIME_MIN") || str6.equals("STR_REBUFFERING_TIME_MAX") || str6.equals(STR_REBUFFERING_TIME_AVG) || str6.equals("STR_REBUFFERING_TIME_STD") || str6.equals("STR_CONTINUOUS-PLAYBACK_AVG") || str6.equals("STR_CONTINUOUS-PLAYBACK_STD") || str6.equals("STR_BUFFERING-TIME-INITIAL") || str6.equals("SVA_REBUFFERING-RATIO") || str6.equals("SES_TIME_SET-DATA-SOURCE") || str6.equals("SVA_VIDEO-START-TIME") || str6.equals("SES_TIME_ZAPPING") || str6.equals("SES_SESSION_COMPLETION-PERCENT")) {
                            try {
                                map = this.b;
                                objValueOf = Float.valueOf(Float.parseFloat(str7));
                            } catch (NumberFormatException unused) {
                            }
                        } else {
                            this.b.put(str6, str7);
                        }
                        map.put(str6, objValueOf);
                    }
                }
            }
        } catch (Exception unused2) {
        }
    }

    public final Object getValue(String str) {
        Map<String, Object> map;
        a();
        Object obj = this.f72a.get(str);
        return (obj != null || (map = this.b) == null) ? obj : map.get(str);
    }

    public final Map<String, Object> getValues() {
        a();
        return this.f72a;
    }

    public String toString() {
        return this.c;
    }

    protected String toStringWithInternalValues() {
        return this.d;
    }
}
