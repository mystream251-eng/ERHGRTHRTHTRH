package io.sentry.android.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import io.sentry.ILogger;
import io.sentry.SentryLevel;

/* JADX INFO: loaded from: classes2.dex */
final class ContextUtils {
    private ContextUtils() {
    }

    static PackageInfo getPackageInfo(Context context, ILogger iLogger) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Throwable th) {
            iLogger.log(SentryLevel.ERROR, "Error getting package info.", th);
            return null;
        }
    }

    static String getVersionCode(PackageInfo packageInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Long.toString(packageInfo.getLongVersionCode());
        }
        return getVersionCodeDep(packageInfo);
    }

    private static String getVersionCodeDep(PackageInfo packageInfo) {
        return Integer.toString(packageInfo.versionCode);
    }
}
