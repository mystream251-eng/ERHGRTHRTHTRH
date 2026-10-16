package com.android.tv.common.feature;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class GServiceFeature implements Feature {
    private static final String LIVECHANNELS_PREFIX = "livechannels:";
    private final boolean mDefaultValue;
    private final String mKey;

    public GServiceFeature(String str, boolean z) {
        this.mKey = LIVECHANNELS_PREFIX + str;
        this.mDefaultValue = z;
    }

    @Override // com.android.tv.common.feature.Feature
    public boolean isEnabled(Context context) {
        return this.mDefaultValue;
    }

    public String toString() {
        return "GService[hash=" + this.mKey.hashCode() + "]";
    }
}
