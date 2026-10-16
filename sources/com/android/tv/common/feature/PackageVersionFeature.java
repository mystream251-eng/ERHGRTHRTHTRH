package com.android.tv.common.feature;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* JADX INFO: loaded from: classes.dex */
public class PackageVersionFeature implements Feature {
    private static final boolean DEBUG = false;
    private static final String TAG = "PackageVersionFeature";
    private final String mPackageName;
    private final int mRequiredVersionCode;

    public PackageVersionFeature(String str, int i) {
        this.mPackageName = str;
        this.mRequiredVersionCode = i;
    }

    @Override // com.android.tv.common.feature.Feature
    public boolean isEnabled(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.mPackageName, 0);
            return packageInfo != null && packageInfo.versionCode >= this.mRequiredVersionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public String toString() {
        return "PackageVersionFeature[packageName=" + this.mPackageName + ",requiredVersion=" + this.mRequiredVersionCode + "]";
    }
}
