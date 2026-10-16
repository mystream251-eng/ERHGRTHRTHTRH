package com.viaccessorca.voplayer;

import android.content.Context;
import android.os.Build;
import androidx.core.os.EnvironmentCompat;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public final class VOCrashReporter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Thread.UncaughtExceptionHandler f63a = Thread.getDefaultUncaughtExceptionHandler();
    private static Thread.UncaughtExceptionHandler b = null;
    private static b c = new b(null);
    private static VOPlayer.VOPlayerEnabledReports d = VOPlayer.VOPlayerEnabledReports.REPORT_NONE;
    private static ArrayList<String> e = new ArrayList<>();
    private static Context f = null;
    static boolean g = false;
    static Boolean h = false;

    public static class A2rl9t7p639784tzp {
        public String m329t7p6390828478448;
        public String m329t7p6390828478449;
        public String m329t7p6390828478450;
        public String m329t7p6390828478451;
        public String m329t7p6390828478452;
        public String m329t7p6390828478453;
        public String m329t7p6390828478454;
        public String m329t7p6390828478455;
        public String m329t7p6390828478456;
        public String m329t7p6390828478457;
        public String m329t7p6390828478458;
        public String m329t7p6390828478465;
        public String m329t7p6390828478475;
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f64a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[VOPlayer.VOPlayerSeverity.values().length];
            b = iArr;
            try {
                iArr[VOPlayer.VOPlayerSeverity.SEVERITY_INFO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[VOPlayer.VOPlayerSeverity.SEVERITY_WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[VOPlayer.VOPlayerSeverity.SEVERITY_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[VOPlayer.VOPlayerEnabledReports.values().length];
            f64a = iArr2;
            try {
                iArr2[VOPlayer.VOPlayerEnabledReports.REPORT_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f64a[VOPlayer.VOPlayerEnabledReports.REPORT_CRASHES.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f64a[VOPlayer.VOPlayerEnabledReports.REPORT_ERRORS.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f64a[VOPlayer.VOPlayerEnabledReports.REPORT_ALL.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private static final class b implements Thread.UncaughtExceptionHandler {
        private b() {
        }

        /* synthetic */ b(a aVar) {
            this();
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
            String string;
            try {
                StringBuilder sb = new StringBuilder();
                if (th != null) {
                    try {
                        try {
                            string = th.getCause().toString().substring(0, th.getCause().toString().indexOf(":"));
                        } catch (Exception unused) {
                            string = "crash report error";
                        }
                    } catch (Exception unused2) {
                        string = th.toString();
                    }
                    sb.append(th.getLocalizedMessage());
                    for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                        sb.append(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")\n");
                    }
                } else {
                    string = EnvironmentCompat.MEDIA_UNKNOWN;
                }
                if (VOCrashReporter.f != null) {
                    VOCrashReporter.NUU0NDU1M0I1RDA0NDlDMTg3RDkxODYzOThFMjA2RUY(string, sb.toString());
                }
            } catch (Exception unused3) {
            }
            if (VOCrashReporter.b != null) {
                uncaughtExceptionHandler = VOCrashReporter.b;
            } else if (VOCrashReporter.f63a == null) {
                return;
            } else {
                uncaughtExceptionHandler = VOCrashReporter.f63a;
            }
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }

    private static native void MTQ4RkM0RDhFQjMxNDQ4MjkzQUZFRDRFRTQyMjhDNzEK(String str);

    private static native void MTYyNTUyRDg3REU1NDEyMkFDMzdBNTdBQjJGQURCMTQ(String str);

    private static native boolean MzIwRTcyQzY2QTFENDRFQTgzMjM3NTQwMjYzOTlBNEQK();

    private static native boolean Mzc5QTIzNTUwNjU4NDY0NkFBM0JFOEI3QTQ0Nzg4NUYK();

    private static native int NEEzQzIyN0E5RkU5NEExNTg2NkYyNEFEQTFBMTExQ0IK();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void NUU0NDU1M0I1RDA0NDlDMTg3RDkxODYzOThFMjA2RUY(String str, String str2);

    private static native int QjMzNjNBMTg5QUVDNDg1RjhFQ0UzOTFFNzkzMkE1MTMK(String str, Object obj);

    private static native void QzQ1QkJDOEY2RDlGNEU2NEIxRUZCM0I2MzcyMEU3QTIK(String str);

    private static native void RDFCMzQ4ODc0ODJFNDFGRkFCNjVDQ0RERTAxRjMxOEM(boolean z);

    private static native void REYzM0YzM0VEMjQzNEE0REE0Q0FEN0FFNzBEMzQ0MkIK(String str, String str2, String str3, boolean z);

    private static void a(VOPlayer.VOPlayerEnabledReports vOPlayerEnabledReports) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (f == null) {
            return;
        }
        try {
            if ((d != VOPlayer.VOPlayerEnabledReports.REPORT_NONE && d != VOPlayer.VOPlayerEnabledReports.REPORT_ERRORS) || (vOPlayerEnabledReports != VOPlayer.VOPlayerEnabledReports.REPORT_CRASHES && vOPlayerEnabledReports != VOPlayer.VOPlayerEnabledReports.REPORT_ALL)) {
                if ((d == VOPlayer.VOPlayerEnabledReports.REPORT_CRASHES || d == VOPlayer.VOPlayerEnabledReports.REPORT_ALL) && (vOPlayerEnabledReports == VOPlayer.VOPlayerEnabledReports.REPORT_ERRORS || vOPlayerEnabledReports == VOPlayer.VOPlayerEnabledReports.REPORT_NONE)) {
                    uncaughtExceptionHandler = b != null ? b : f63a;
                }
                d = vOPlayerEnabledReports;
                int i = a.f64a[vOPlayerEnabledReports.ordinal()];
            }
            b = Thread.getDefaultUncaughtExceptionHandler();
            uncaughtExceptionHandler = c;
            Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
            d = vOPlayerEnabledReports;
            int i2 = a.f64a[vOPlayerEnabledReports.ordinal()];
        } catch (Exception unused) {
        }
    }

    private static void d() {
        int iNEEzQzIyN0E5RkU5NEExNTg2NkYyNEFEQTFBMTExQ0IK;
        VOPlayer.VOPlayerEnabledReports vOPlayerEnabledReports;
        if (f == null || g || (iNEEzQzIyN0E5RkU5NEExNTg2NkYyNEFEQTFBMTExQ0IK = NEEzQzIyN0E5RkU5NEExNTg2NkYyNEFEQTFBMTExQ0IK()) < 0 || iNEEzQzIyN0E5RkU5NEExNTg2NkYyNEFEQTFBMTExQ0IK > 3 || d.ordinal() == iNEEzQzIyN0E5RkU5NEExNTg2NkYyNEFEQTFBMTExQ0IK) {
            return;
        }
        if (iNEEzQzIyN0E5RkU5NEExNTg2NkYyNEFEQTFBMTExQ0IK == 0) {
            vOPlayerEnabledReports = VOPlayer.VOPlayerEnabledReports.REPORT_NONE;
        } else if (iNEEzQzIyN0E5RkU5NEExNTg2NkYyNEFEQTFBMTExQ0IK == 1) {
            vOPlayerEnabledReports = VOPlayer.VOPlayerEnabledReports.REPORT_CRASHES;
        } else if (iNEEzQzIyN0E5RkU5NEExNTg2NkYyNEFEQTFBMTExQ0IK == 2) {
            vOPlayerEnabledReports = VOPlayer.VOPlayerEnabledReports.REPORT_ERRORS;
        } else if (iNEEzQzIyN0E5RkU5NEExNTg2NkYyNEFEQTFBMTExQ0IK != 3) {
            return;
        } else {
            vOPlayerEnabledReports = VOPlayer.VOPlayerEnabledReports.REPORT_ALL;
        }
        a(vOPlayerEnabledReports);
    }

    private static boolean e() {
        return MzIwRTcyQzY2QTFENDRFQTgzMjM3NTQwMjYzOTlBNEQK();
    }

    public static String getEvents() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = e.iterator();
        while (it.hasNext()) {
            sb.append(it.next() + "\n");
        }
        return sb.toString();
    }

    public static Boolean init(Context context) {
        File filesDir;
        if (h.booleanValue()) {
            return true;
        }
        if (context != null && (filesDir = context.getFilesDir()) != null) {
            try {
                A2rl9t7p639784tzp a2rl9t7p639784tzp = new A2rl9t7p639784tzp();
                String packageName = context.getPackageName();
                a2rl9t7p639784tzp.m329t7p6390828478448 = filesDir.getAbsolutePath();
                a2rl9t7p639784tzp.m329t7p6390828478465 = filesDir.getAbsolutePath() + "/";
                a2rl9t7p639784tzp.m329t7p6390828478449 = packageName;
                a2rl9t7p639784tzp.m329t7p6390828478450 = "1.0";
                a2rl9t7p639784tzp.m329t7p6390828478451 = packageName.substring(packageName.lastIndexOf(".") + 1);
                a2rl9t7p639784tzp.m329t7p6390828478452 = "1.0";
                a2rl9t7p639784tzp.m329t7p6390828478453 = Build.MODEL;
                a2rl9t7p639784tzp.m329t7p6390828478454 = Build.BOARD;
                a2rl9t7p639784tzp.m329t7p6390828478455 = Build.MANUFACTURER;
                a2rl9t7p639784tzp.m329t7p6390828478456 = "and " + Build.VERSION.RELEASE;
                a2rl9t7p639784tzp.m329t7p6390828478457 = VOPlayer.getSdkVersion(context);
                a2rl9t7p639784tzp.m329t7p6390828478458 = VOPlayer.h(context);
                a2rl9t7p639784tzp.m329t7p6390828478475 = VOPlayer.e(context);
                if (QjMzNjNBMTg5QUVDNDg1RjhFQ0UzOTFFNzkzMkE1MTMK(context.getFilesDir().getPath(), a2rl9t7p639784tzp) != 0) {
                    return false;
                }
                f = context;
                RDFCMzQ4ODc0ODJFNDFGRkFCNjVDQ0RERTAxRjMxOEM(true);
                d();
                h = true;
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    public static void logEvent(VOPlayer.VOPlayerSeverity vOPlayerSeverity, String str, String str2) {
        String str3;
        StringBuilder sb;
        StringBuilder sb2;
        String string;
        String str4;
        int i = a.b[vOPlayerSeverity.ordinal()];
        if (i == 1) {
            str3 = "(I) ";
            if (str2 == null || str2 == "") {
                sb = new StringBuilder();
                sb.append(str3);
                sb.append(str);
                string = sb.toString();
            } else {
                sb2 = new StringBuilder();
                sb2.append(str3);
                sb2.append(str);
                sb2.append(" : ");
                sb2.append(str2);
                string = sb2.toString();
            }
        } else {
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                if (str2 == null || str2 == "") {
                    str4 = "(E) " + str;
                } else {
                    str4 = "(E) " + str + " : " + str2;
                }
                MTYyNTUyRDg3REU1NDEyMkFDMzdBNTdBQjJGQURCMTQ(str4);
                sendErrorReport(null, str, true);
                return;
            }
            str3 = "(W) ";
            if (str2 == null || str2 == "") {
                sb = new StringBuilder();
                sb.append(str3);
                sb.append(str);
                string = sb.toString();
            } else {
                sb2 = new StringBuilder();
                sb2.append(str3);
                sb2.append(str);
                sb2.append(" : ");
                sb2.append(str2);
                string = sb2.toString();
            }
        }
        MTYyNTUyRDg3REU1NDEyMkFDMzdBNTdBQjJGQURCMTQ(string);
    }

    public static void logEvent(String str) {
        if (d != VOPlayer.VOPlayerEnabledReports.REPORT_NONE) {
            MTYyNTUyRDg3REU1NDEyMkFDMzdBNTdBQjJGQURCMTQ(str);
        }
    }

    public static void logEvent(String str, String str2) {
        logEvent("[" + str + "] " + str2);
    }

    public static void logEvent(String str, String str2, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(" * " + entry.getKey() + " -> " + entry.getValue() + "\n");
        }
        logEvent("[" + str + "] " + str2 + "\n" + sb.toString());
    }

    public static void sendErrorReport(VOPlayer vOPlayer, String str, boolean z) {
        d();
        if (d == VOPlayer.VOPlayerEnabledReports.REPORT_NONE) {
            return;
        }
        if (z || d == VOPlayer.VOPlayerEnabledReports.REPORT_ERRORS || d == VOPlayer.VOPlayerEnabledReports.REPORT_ALL) {
            String str2 = "PSR [ ";
            if (vOPlayer != null) {
                try {
                    VOPlaybackSessionReport playbackSessionReport = vOPlayer.getPlaybackSessionReport();
                    if (playbackSessionReport == null) {
                        playbackSessionReport = vOPlayer.getNonFilteredPlaybackSessionReport();
                    }
                    if (playbackSessionReport != null) {
                        str2 = "PSR [ " + playbackSessionReport.toString();
                    }
                } catch (Exception unused) {
                    return;
                }
            }
            REYzM0YzM0VEMjQzNEE0REE0Q0FEN0FFNzBEMzQ0MkIK(UUID.randomUUID().toString(), str, str2 + " ]\n", z);
        }
    }

    public static void sendPendingCrashReport() {
        d();
        if ((d == VOPlayer.VOPlayerEnabledReports.REPORT_CRASHES || d == VOPlayer.VOPlayerEnabledReports.REPORT_ALL) && e()) {
            QzQ1QkJDOEY2RDlGNEU2NEIxRUZCM0I2MzcyMEU3QTIK(UUID.randomUUID().toString());
        }
    }

    public static void setEnabledReports(VOPlayer.VOPlayerEnabledReports vOPlayerEnabledReports) {
        g = true;
        a(vOPlayerEnabledReports);
    }

    public static void setReportHeader(String str) {
        if (str == null) {
            return;
        }
        MTQ4RkM0RDhFQjMxNDQ4MjkzQUZFRDRFRTQyMjhDNzEK(str);
    }

    public static void updateCrashReportEnablementStatus() {
        Mzc5QTIzNTUwNjU4NDY0NkFBM0JFOEI3QTQ0Nzg4NUYK();
    }
}
