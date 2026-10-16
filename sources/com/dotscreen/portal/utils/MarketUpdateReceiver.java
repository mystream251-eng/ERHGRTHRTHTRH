package com.dotscreen.portal.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MarketUpdateReceiver extends BroadcastReceiver {
    private static final String TAG = MarketUpdateReceiver.class.getSimpleName();

    public static String getBroadcastPermission() {
        return "android.permission.INSTALL_PACKAGES";
    }

    public static IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.android.launcher.action.ACTION_PACKAGE_ENQUEUED");
        intentFilter.addAction("com.android.launcher.action.ACTION_PACKAGE_DOWNLOADING");
        intentFilter.addAction("com.android.launcher.action.ACTION_PACKAGE_INSTALLING");
        intentFilter.addAction("com.android.launcher.action.ACTION_PACKAGE_DEQUEUED");
        return intentFilter;
    }

    private boolean isNonLeanbackApp(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(str, 1);
            return packageManager.getLeanbackLaunchIntentForPackage(str) == null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String schemeSpecificPart;
        Log.d(TAG, "onReceive(" + context + ", " + intent + ")");
        Uri data = intent.getData();
        if (data == null || (schemeSpecificPart = data.getSchemeSpecificPart()) == null || isNonLeanbackApp(context, schemeSpecificPart)) {
            return;
        }
        String action = intent.getAction();
        if (intent.getBooleanExtra("user_initiated", false)) {
            if ("com.android.launcher.action.ACTION_PACKAGE_ENQUEUED".equals(action)) {
                Log.d("MarketUpdateReceiver", "market has promised to " + (intent.hasExtra("reason") ? intent.getStringExtra("reason") : "install") + ": " + schemeSpecificPart + " user initiated: " + intent.getBooleanExtra("user_initiated", false));
                return;
            }
            if ("com.android.launcher.action.ACTION_PACKAGE_DOWNLOADING".equals(action)) {
                Log.d("MarketUpdateReceiver", "market is downloading (" + intent.getIntExtra("progress", 0) + "%): " + schemeSpecificPart);
                return;
            }
            if ("com.android.launcher.action.ACTION_PACKAGE_INSTALLING".equals(action)) {
                Log.d("MarketUpdateReceiver", "market is installing: " + schemeSpecificPart);
                return;
            }
            if ("com.android.launcher.action.ACTION_PACKAGE_DEQUEUED".equals(action)) {
                if (intent.getBooleanExtra("com.android.launcher.action.INSTALL_COMPLETED", false)) {
                    Log.d("MarketUpdateReceiver", "market has installed: " + schemeSpecificPart);
                    return;
                }
                Log.d("MarketUpdateReceiver", "market has decided not to install: " + schemeSpecificPart);
                return;
            }
            Log.d("MarketUpdateReceiver", "unknown message " + action);
        }
    }
}
