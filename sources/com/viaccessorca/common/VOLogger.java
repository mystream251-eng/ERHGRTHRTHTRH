package com.viaccessorca.common;

/* JADX INFO: loaded from: classes2.dex */
public final class VOLogger {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f7a = false;
    private static boolean b = false;

    public static final void d(String str, String str2) {
        boolean z = b;
    }

    public static final void e(String str, String str2) {
    }

    public static final void i(String str, String str2) {
        boolean z = b;
    }

    public static final void logCallStack(String str, String str2) {
        if (b) {
            return;
        }
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            boolean z = f7a;
            stackTraceElement.getClassName();
            stackTraceElement.getMethodName();
        }
    }

    public static final void logMethod(String str, String str2, Object... objArr) {
        StringBuilder sb;
        if (b) {
            return;
        }
        String str3 = new String(str2 + "(");
        for (int i = 0; i < objArr.length; i++) {
            if (i == objArr.length - 1) {
                sb = new StringBuilder();
                sb.append(str3);
                sb.append(objArr[i]);
            } else {
                sb = new StringBuilder();
                sb.append(str3);
                sb.append(objArr[i]);
                sb.append(", ");
            }
            str3 = sb.toString();
        }
    }

    public static void setForceMinPriority(boolean z) {
        f7a = z;
    }

    public static void setNoLogs(boolean z) {
        b = z;
    }

    public static final void v(String str, String str2) {
        boolean z = b;
    }

    public static final void w(String str, String str2) {
    }
}
