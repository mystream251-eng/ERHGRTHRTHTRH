package com.android.tv.common.feature;

import android.content.Context;
import java.util.Arrays;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes.dex */
public class FeatureUtils {
    public static final Feature ON = new Feature() { // from class: com.android.tv.common.feature.FeatureUtils.3
        @Override // com.android.tv.common.feature.Feature
        public boolean isEnabled(Context context) {
            return true;
        }

        public String toString() {
            return DebugKt.DEBUG_PROPERTY_VALUE_ON;
        }
    };
    public static final Feature OFF = new Feature() { // from class: com.android.tv.common.feature.FeatureUtils.4
        @Override // com.android.tv.common.feature.Feature
        public boolean isEnabled(Context context) {
            return false;
        }

        public String toString() {
            return DebugKt.DEBUG_PROPERTY_VALUE_OFF;
        }
    };

    public static Feature OR(final Feature... featureArr) {
        return new Feature() { // from class: com.android.tv.common.feature.FeatureUtils.1
            @Override // com.android.tv.common.feature.Feature
            public boolean isEnabled(Context context) {
                for (Feature feature : featureArr) {
                    if (feature.isEnabled(context)) {
                        return true;
                    }
                }
                return false;
            }

            public String toString() {
                return "or(" + Arrays.asList(featureArr) + ")";
            }
        };
    }

    public static Feature AND(final Feature... featureArr) {
        return new Feature() { // from class: com.android.tv.common.feature.FeatureUtils.2
            @Override // com.android.tv.common.feature.Feature
            public boolean isEnabled(Context context) {
                for (Feature feature : featureArr) {
                    if (!feature.isEnabled(context)) {
                        return false;
                    }
                }
                return true;
            }

            public String toString() {
                return "and(" + Arrays.asList(featureArr) + ")";
            }
        };
    }

    private FeatureUtils() {
    }
}
