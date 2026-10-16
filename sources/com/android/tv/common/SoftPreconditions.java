package com.android.tv.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.tv.common.feature.Feature;

/* JADX INFO: loaded from: classes.dex */
public final class SoftPreconditions {
    private static final String TAG = "SoftPreconditions";

    public static void checkArgument(boolean z, String str, String str2) {
        if (z) {
            return;
        }
        warn(str, "Illegal argument", str2, new IllegalArgumentException(str2));
    }

    public static void checkArgument(boolean z) {
        checkArgument(z, null, null);
    }

    public static <T> T checkNotNull(T t, String str, String str2) {
        if (t == null) {
            warn(str, "Null Pointer", str2, new NullPointerException(str2));
        }
        return t;
    }

    public static <T> T checkNotNull(T t) {
        return (T) checkNotNull(t, null, null);
    }

    public static void checkState(boolean z, String str, String str2) {
        if (z) {
            return;
        }
        warn(str, "Illegal State", str2, new IllegalStateException(str2));
    }

    public static void checkState(boolean z) {
        checkState(z, null, null);
    }

    public static void checkFeatureEnabled(Context context, Feature feature, String str) {
        checkState(feature.isEnabled(context), str, feature.toString());
    }

    public static void warn(String str, String str2, String str3, Exception exc) throws RuntimeException {
        if (TextUtils.isEmpty(str)) {
            str = TAG;
        }
        if (!TextUtils.isEmpty(str3)) {
            if (TextUtils.isEmpty(str2)) {
                str2 = str3;
            } else {
                str2 = str2 + ": " + str3;
            }
        }
        if (BuildConfig.ENG.booleanValue()) {
            throw new RuntimeException(str3, exc);
        }
        Log.w(str, str2, exc);
    }

    private SoftPreconditions() {
    }
}
