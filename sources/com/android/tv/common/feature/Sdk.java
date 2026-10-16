package com.android.tv.common.feature;

import android.content.Context;
import android.os.Build;
import androidx.core.os.BuildCompat;

/* JADX INFO: loaded from: classes.dex */
public class Sdk {
    public static Feature N_PRE_2_OR_HIGHER = new SdkPreviewVersionFeature(23, 2, true);
    public static Feature AT_LEAST_N = new Feature() { // from class: com.android.tv.common.feature.Sdk.1
        @Override // com.android.tv.common.feature.Feature
        public boolean isEnabled(Context context) {
            return BuildCompat.isAtLeastN();
        }
    };

    private static class SdkPreviewVersionFeature implements Feature {
        private final boolean mAllowHigherPreview;
        private final int mPreviewCode;
        private final int mVersionCode;

        private SdkPreviewVersionFeature(int i, int i2, boolean z) {
            this.mVersionCode = i;
            this.mPreviewCode = i2;
            this.mAllowHigherPreview = z;
        }

        @Override // com.android.tv.common.feature.Feature
        public boolean isEnabled(Context context) {
            try {
                if (this.mAllowHigherPreview) {
                    return Build.VERSION.SDK_INT == this.mVersionCode && Build.VERSION.PREVIEW_SDK_INT >= this.mPreviewCode;
                }
                return Build.VERSION.SDK_INT == this.mVersionCode && Build.VERSION.PREVIEW_SDK_INT == this.mPreviewCode;
            } catch (NoSuchFieldError unused) {
                return false;
            }
        }
    }

    private Sdk() {
    }
}
