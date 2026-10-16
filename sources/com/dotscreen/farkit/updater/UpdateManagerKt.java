package com.dotscreen.farkit.updater;

import android.content.Context;
import android.content.Intent;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f\u001a\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f\u001a\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u000e\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"ACTION_APPLICATION_GOES_BACKGROUND", "", "ACTION_APPLICATION_GOES_FOREGROUND", "ACTION_NOTIFY_CHECK_FAILED", "ACTION_NOTIFY_CHECK_RESULT", "ACTION_NOTIFY_FIRST_CHECK", "ACTION_NOTIFY_NEXT_CHECK", "ACTION_NOTIFY_PERIODIC_CHECK_VALUE", "ACTION_NOTIFY_UPDATE_CHECK", "EXTRA_CHECK_DELAY", "EXTRA_CHECK_PERIOD", "EXTRA_CHECK_RESULT_DATA", "EXTRA_PACKAGE_NAME", "MAX_RETRY_COUNT", "", "STATUS_DOWNLOAD_CANCELED", "STATUS_DOWNLOAD_FAIL", "STATUS_DOWNLOAD_FAIL_INCOMPATIBLE_SDK", "STATUS_DOWNLOAD_FAIL_PACKAGE_NOT_FOUND_IN_BUNDLE", "STATUS_DOWNLOAD_SUCCESS", "STATUS_INSTALL_FAIL", "STATUS_INSTALL_FAIL_ABORTED", "STATUS_INSTALL_FAIL_BLOCKED", "STATUS_INSTALL_FAIL_CONFLICT", "STATUS_INSTALL_FAIL_INCOMPATIBLE", "STATUS_INSTALL_FAIL_INVALID", "STATUS_INSTALL_FAIL_STORAGE", "STATUS_INSTALL_SUCCESS", "notifyApplicationGoesBackground", "", "context", "Landroid/content/Context;", "notifyApplicationGoesForeground", "retryDelay", "", "retryCount", "farkit_release"}, k = 2, mv = {1, 1, 16})
public final class UpdateManagerKt {
    public static final String ACTION_APPLICATION_GOES_BACKGROUND = "com.dotscreen.farkit.ACTION_APPLICATION_GOES_BACKGROUND";
    public static final String ACTION_APPLICATION_GOES_FOREGROUND = "com.dotscreen.farkit.ACTION_APPLICATION_GOES_FOREGROUND";
    public static final String ACTION_NOTIFY_CHECK_FAILED = "com.dotscreen.farkit.NOTIFY_CHECK_FAILED";
    public static final String ACTION_NOTIFY_CHECK_RESULT = "com.dotscreen.farkit.NOTIFY_CHECK_RESULT";
    public static final String ACTION_NOTIFY_FIRST_CHECK = "com.dotscreen.farkit.NOTIFY_FIRST_CHECK";
    public static final String ACTION_NOTIFY_NEXT_CHECK = "com.dotscreen.farkit.NOTIFY_NEXT_CHECK";
    public static final String ACTION_NOTIFY_PERIODIC_CHECK_VALUE = "com.dotscreen.farkit.NOTIFY_PERIODIC_CHECK_VALUE";
    public static final String ACTION_NOTIFY_UPDATE_CHECK = "com.dotscreen.farkit.NOTIFY_UPDATE_CHECK";
    public static final String EXTRA_CHECK_DELAY = "check_delay";
    public static final String EXTRA_CHECK_PERIOD = "check_period";
    public static final String EXTRA_CHECK_RESULT_DATA = "check_result_data";
    public static final String EXTRA_PACKAGE_NAME = "com.dotscreen.farkit.package_name";
    public static final int MAX_RETRY_COUNT = 0;
    public static final int STATUS_DOWNLOAD_CANCELED = -2;
    public static final int STATUS_DOWNLOAD_FAIL = -1;
    public static final int STATUS_DOWNLOAD_FAIL_INCOMPATIBLE_SDK = -3;
    public static final int STATUS_DOWNLOAD_FAIL_PACKAGE_NOT_FOUND_IN_BUNDLE = -4;
    public static final int STATUS_DOWNLOAD_SUCCESS = 0;
    public static final int STATUS_INSTALL_FAIL = -1;
    public static final int STATUS_INSTALL_FAIL_ABORTED = -3;
    public static final int STATUS_INSTALL_FAIL_BLOCKED = -2;
    public static final int STATUS_INSTALL_FAIL_CONFLICT = -5;
    public static final int STATUS_INSTALL_FAIL_INCOMPATIBLE = -7;
    public static final int STATUS_INSTALL_FAIL_INVALID = -4;
    public static final int STATUS_INSTALL_FAIL_STORAGE = -6;
    public static final int STATUS_INSTALL_SUCCESS = 0;

    public static final long retryDelay(int i) {
        return TimeUnit.SECONDS.toMillis((1 << i) * 10);
    }

    public static final void notifyApplicationGoesBackground(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intent intent = new Intent(ACTION_APPLICATION_GOES_BACKGROUND);
        intent.putExtra(EXTRA_PACKAGE_NAME, context.getPackageName());
        context.sendBroadcast(intent);
    }

    public static final void notifyApplicationGoesForeground(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intent intent = new Intent(ACTION_APPLICATION_GOES_FOREGROUND);
        intent.putExtra(EXTRA_PACKAGE_NAME, context.getPackageName());
        context.sendBroadcast(intent);
    }
}
