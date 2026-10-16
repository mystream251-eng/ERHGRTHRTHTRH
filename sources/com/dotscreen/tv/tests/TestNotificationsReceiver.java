package com.dotscreen.tv.tests;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestNotificationsReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = true;
    private static final String PATH = "notifications";
    private static final String TAG = "TestNotifsReceiver";
    private boolean mSetUp = false;
    private static final String[] LEVEL = {"default", "high", "low", "max"};
    private static final int[] IMPORTANCE_LEVEL = {3, 4, 2, 5};

    private void postNotification(Context context, int i) {
    }

    public TestNotificationsReceiver() {
        Log.d(TAG, "new TestNotificationsReceiver");
    }

    private void setUp(Context context) {
        NotificationChannel notificationChannel = new NotificationChannel(LEVEL[0], "default level notification", IMPORTANCE_LEVEL[0]);
        NotificationChannel notificationChannel2 = new NotificationChannel(LEVEL[1], "high level notification", IMPORTANCE_LEVEL[1]);
        NotificationChannel notificationChannel3 = new NotificationChannel(LEVEL[2], "low level notification", IMPORTANCE_LEVEL[2]);
        NotificationChannel notificationChannel4 = new NotificationChannel(LEVEL[3], "max level notification", IMPORTANCE_LEVEL[3]);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.createNotificationChannel(notificationChannel2);
        notificationManager.createNotificationChannel(notificationChannel3);
        notificationManager.createNotificationChannel(notificationChannel4);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x007b  */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        int iBinarySearch;
        Log.d(TAG, "onReceive: " + intent);
        boolean z = true;
        if (!this.mSetUp) {
            setUp(context);
            this.mSetUp = true;
        }
        Uri data = intent.getData();
        if (data != null) {
            List<String> pathSegments = data.getPathSegments();
            Log.d(TAG, "Got URI: " + data);
            if (pathSegments == null || pathSegments.size() != 2 || !PATH.equals(pathSegments.get(0)) || (iBinarySearch = Arrays.binarySearch(LEVEL, (str = pathSegments.get(1)))) == -1) {
                z = false;
            } else {
                postNotification(context, iBinarySearch);
                Log.d(TAG, "Sent notification type " + str);
            }
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        Log.d(TAG, "Failed to create notification for: " + data);
    }
}
