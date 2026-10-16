package com.android.tv.common.feature;

import android.content.Context;
import com.android.tv.common.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public final class EngOnlyFeature implements Feature {
    public static Feature ENG_ONLY_FEATURE = new EngOnlyFeature();

    private EngOnlyFeature() {
    }

    @Override // com.android.tv.common.feature.Feature
    public boolean isEnabled(Context context) {
        return BuildConfig.ENG.booleanValue();
    }

    public String toString() {
        return "EngOnlyFeature(" + BuildConfig.ENG + ")";
    }
}
