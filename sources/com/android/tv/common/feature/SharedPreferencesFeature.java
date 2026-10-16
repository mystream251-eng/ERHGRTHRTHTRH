package com.android.tv.common.feature;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.tv.common.SharedPreferencesUtils;

/* JADX INFO: loaded from: classes.dex */
public final class SharedPreferencesFeature implements Feature {
    private static final boolean DEBUG = false;
    private static final String TAG = "SharedPrefFeature";
    private final Feature mBaseFeature;
    private boolean mDefaultValue;
    private boolean mEnabled;
    private String mKey;
    private SharedPreferences mSharedPreferences;

    public SharedPreferencesFeature(String str, boolean z, Feature feature) {
        this.mKey = str;
        this.mDefaultValue = z;
        this.mBaseFeature = feature;
    }

    @Override // com.android.tv.common.feature.Feature
    public boolean isEnabled(Context context) {
        if (!this.mBaseFeature.isEnabled(context)) {
            return false;
        }
        if (this.mSharedPreferences == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesUtils.SHARED_PREF_FEATURES, 0);
            this.mSharedPreferences = sharedPreferences;
            this.mEnabled = sharedPreferences.getBoolean(this.mKey, this.mDefaultValue);
        }
        return this.mEnabled;
    }

    public String toString() {
        return "SharedPreferencesFeature:key=" + this.mKey + ",value=" + this.mEnabled;
    }

    public void setEnabled(Context context, boolean z) {
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences == null) {
            SharedPreferences sharedPreferences2 = context.getSharedPreferences(SharedPreferencesUtils.SHARED_PREF_FEATURES, 0);
            this.mSharedPreferences = sharedPreferences2;
            this.mEnabled = z;
            sharedPreferences2.edit().putBoolean(this.mKey, z).apply();
            return;
        }
        if (this.mEnabled != z) {
            this.mEnabled = z;
            sharedPreferences.edit().putBoolean(this.mKey, z).apply();
        }
    }
}
