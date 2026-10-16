package com.android.tv.common;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class AutoCloseableUtils {
    private static final String TAG = "AutoCloseableUtils";

    private AutoCloseableUtils() {
    }

    public static void closeQuietly(AutoCloseable autoCloseable) {
        try {
            autoCloseable.close();
        } catch (Exception e) {
            Log.e(TAG, "Error closing " + autoCloseable, e);
        }
    }
}
