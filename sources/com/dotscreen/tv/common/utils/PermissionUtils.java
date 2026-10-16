package com.dotscreen.tv.common.utils;

import android.content.Context;
import android.os.Build;
import androidx.tvprovider.media.tv.TvContractCompat;

/* JADX INFO: loaded from: classes.dex */
public class PermissionUtils {
    private static Boolean sHasAccessAllEpgPermission;
    private static Boolean sHasAccessWatchedHistoryPermission;
    private static Boolean sHasModifyParentalControlsPermission;

    public static boolean hasAccessAllEpg(Context context) {
        if (sHasAccessAllEpgPermission == null) {
            if (Build.VERSION.SDK_INT >= 23) {
                sHasAccessAllEpgPermission = Boolean.valueOf(context.checkSelfPermission("com.android.providers.tv.permission.ACCESS_ALL_EPG_DATA") == 0);
            } else {
                sHasAccessAllEpgPermission = Boolean.valueOf(context.getPackageManager().checkPermission("com.android.providers.tv.permission.ACCESS_ALL_EPG_DATA", context.getPackageName()) == 0);
            }
        }
        return sHasAccessAllEpgPermission.booleanValue();
    }

    public static boolean hasAccessWatchedHistory(Context context) {
        if (sHasAccessWatchedHistoryPermission == null) {
            if (Build.VERSION.SDK_INT >= 23) {
                sHasAccessWatchedHistoryPermission = Boolean.valueOf(context.checkSelfPermission("com.android.providers.tv.permission.ACCESS_WATCHED_PROGRAMS") == 0);
            } else {
                sHasAccessWatchedHistoryPermission = Boolean.valueOf(context.getPackageManager().checkPermission("com.android.providers.tv.permission.ACCESS_WATCHED_PROGRAMS", context.getPackageName()) == 0);
            }
        }
        return sHasAccessWatchedHistoryPermission.booleanValue();
    }

    public static boolean hasModifyParentalControls(Context context) {
        if (sHasModifyParentalControlsPermission == null) {
            if (Build.VERSION.SDK_INT >= 23) {
                sHasModifyParentalControlsPermission = Boolean.valueOf(context.checkSelfPermission("android.permission.MODIFY_PARENTAL_CONTROLS") == 0);
            } else {
                sHasModifyParentalControlsPermission = Boolean.valueOf(context.getPackageManager().checkPermission("android.permission.MODIFY_PARENTAL_CONTROLS", context.getPackageName()) == 0);
            }
        }
        return sHasModifyParentalControlsPermission.booleanValue();
    }

    public static boolean hasReadTvListings(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.checkSelfPermission(TvContractCompat.PERMISSION_READ_TV_LISTINGS) == 0;
        }
        return context.getPackageManager().checkPermission("android.permission.MODIFY_PARENTAL_CONTROLS", context.getPackageName()) == 0;
    }
}
