package io.sentry.android.core;

import android.app.Activity;
import android.util.SparseIntArray;
import androidx.core.app.FrameMetricsAggregator;
import io.sentry.protocol.MeasurementValue;
import io.sentry.protocol.SentryId;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
final class ActivityFramesTracker {
    private final Map<SentryId, Map<String, MeasurementValue>> activityMeasurements;
    private boolean androidXAvailable;
    private FrameMetricsAggregator frameMetricsAggregator;

    ActivityFramesTracker(LoadClass loadClass) {
        this.frameMetricsAggregator = null;
        this.androidXAvailable = true;
        this.activityMeasurements = new ConcurrentHashMap();
        boolean zCheckAndroidXAvailability = checkAndroidXAvailability(loadClass);
        this.androidXAvailable = zCheckAndroidXAvailability;
        if (zCheckAndroidXAvailability) {
            this.frameMetricsAggregator = new FrameMetricsAggregator();
        }
    }

    ActivityFramesTracker(FrameMetricsAggregator frameMetricsAggregator) {
        this.frameMetricsAggregator = null;
        this.androidXAvailable = true;
        this.activityMeasurements = new ConcurrentHashMap();
        this.frameMetricsAggregator = frameMetricsAggregator;
    }

    private static boolean checkAndroidXAvailability(LoadClass loadClass) {
        try {
            loadClass.loadClass("androidx.core.app.FrameMetricsAggregator");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private boolean isFrameMetricsAggregatorAvailable() {
        return this.androidXAvailable && this.frameMetricsAggregator != null;
    }

    synchronized void addActivity(Activity activity) {
        if (isFrameMetricsAggregatorAvailable()) {
            this.frameMetricsAggregator.add(activity);
        }
    }

    synchronized void setMetrics(Activity activity, SentryId sentryId) {
        int i;
        int i2;
        SparseIntArray sparseIntArray;
        if (isFrameMetricsAggregatorAvailable()) {
            SparseIntArray[] sparseIntArrayArrRemove = null;
            try {
                sparseIntArrayArrRemove = this.frameMetricsAggregator.remove(activity);
            } catch (Throwable unused) {
            }
            int i3 = 0;
            if (sparseIntArrayArrRemove == null || (sparseIntArray = sparseIntArrayArrRemove[0]) == null) {
                i = 0;
                i2 = 0;
            } else {
                int i4 = 0;
                i = 0;
                i2 = 0;
                while (i3 < sparseIntArray.size()) {
                    int iKeyAt = sparseIntArray.keyAt(i3);
                    int iValueAt = sparseIntArray.valueAt(i3);
                    i4 += iValueAt;
                    if (iKeyAt > 700) {
                        i2 += iValueAt;
                    } else if (iKeyAt > 16) {
                        i += iValueAt;
                    }
                    i3++;
                }
                i3 = i4;
            }
            if (i3 == 0 && i == 0 && i2 == 0) {
                return;
            }
            MeasurementValue measurementValue = new MeasurementValue(i3);
            MeasurementValue measurementValue2 = new MeasurementValue(i);
            MeasurementValue measurementValue3 = new MeasurementValue(i2);
            HashMap map = new HashMap();
            map.put("frames_total", measurementValue);
            map.put("frames_slow", measurementValue2);
            map.put("frames_frozen", measurementValue3);
            this.activityMeasurements.put(sentryId, map);
        }
    }

    synchronized Map<String, MeasurementValue> takeMetrics(SentryId sentryId) {
        if (!isFrameMetricsAggregatorAvailable()) {
            return null;
        }
        Map<String, MeasurementValue> map = this.activityMeasurements.get(sentryId);
        this.activityMeasurements.remove(sentryId);
        return map;
    }

    synchronized void stop() {
        if (isFrameMetricsAggregatorAvailable()) {
            this.frameMetricsAggregator.stop();
        }
        this.activityMeasurements.clear();
    }
}
