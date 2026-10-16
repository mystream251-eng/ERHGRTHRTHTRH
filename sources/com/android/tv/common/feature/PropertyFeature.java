package com.android.tv.common.feature;

import android.content.Context;
import com.android.tv.common.BooleanSystemProperty;

/* JADX INFO: loaded from: classes.dex */
public final class PropertyFeature implements Feature {
    private final BooleanSystemProperty mProperty;

    public PropertyFeature(String str, boolean z) {
        if (str.length() > 31) {
            throw new IllegalArgumentException("Property keys have a max length of 31 characters but '" + str + "' is " + str.length() + " characters.");
        }
        this.mProperty = new BooleanSystemProperty(str, z);
    }

    @Override // com.android.tv.common.feature.Feature
    public boolean isEnabled(Context context) {
        return this.mProperty.getValue();
    }

    public String toString() {
        return this.mProperty.toString();
    }
}
