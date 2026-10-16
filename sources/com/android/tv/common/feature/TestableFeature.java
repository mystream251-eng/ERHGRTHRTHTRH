package com.android.tv.common.feature;

import android.content.Context;
import android.util.Log;
import com.android.tv.common.TvCommonUtils;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes.dex */
public class TestableFeature implements Feature {
    private static final String DETAIL_MESSAGE = "TestableFeatures should only be changed in tests.";
    private static final String TAG = "TestableFeature";
    private final Feature mDelegate;
    private Boolean mTestValue = null;

    public static TestableFeature createTestableFeature(Feature feature) {
        return new TestableFeature(feature);
    }

    private TestableFeature(Feature feature) {
        this.mDelegate = feature;
    }

    public void enableForTest() {
        if (!TvCommonUtils.isRunningInTest()) {
            Log.e(TAG, "Not enabling for test:" + this, new IllegalStateException(DETAIL_MESSAGE));
            return;
        }
        this.mTestValue = true;
    }

    public void disableForTests() {
        if (!TvCommonUtils.isRunningInTest()) {
            Log.e(TAG, "Not disabling for test: " + this, new IllegalStateException(DETAIL_MESSAGE));
            return;
        }
        this.mTestValue = false;
    }

    public void resetForTests() {
        if (!TvCommonUtils.isRunningInTest()) {
            Log.e(TAG, "Not resetting feature: " + this, new IllegalStateException(DETAIL_MESSAGE));
            return;
        }
        this.mTestValue = null;
    }

    @Override // com.android.tv.common.feature.Feature
    public boolean isEnabled(Context context) {
        Boolean bool;
        if (TvCommonUtils.isRunningInTest() && (bool = this.mTestValue) != null) {
            return bool.booleanValue();
        }
        return this.mDelegate.isEnabled(context);
    }

    public String toString() {
        String string = this.mDelegate.toString();
        if (!TvCommonUtils.isRunningInTest()) {
            return string;
        }
        if (this.mTestValue == null) {
            return "Testable Feature is unchanged: " + string;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Testable Feature is ");
        sb.append(this.mTestValue.booleanValue() ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        sb.append(" was ");
        sb.append(string);
        return sb.toString();
    }
}
