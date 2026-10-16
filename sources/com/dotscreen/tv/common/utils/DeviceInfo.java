package com.dotscreen.tv.common.utils;

import android.os.SystemProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Device.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/utils/DeviceInfo;", "", "()V", "getDeviceModel", "", "getFirmwareVersionName", "getSystemPropertySummary", "property", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class DeviceInfo {
    public static final DeviceInfo INSTANCE = new DeviceInfo();

    private DeviceInfo() {
    }

    private final String getSystemPropertySummary(String property) {
        String str = SystemProperties.get(property, "N/A");
        Intrinsics.checkExpressionValueIsNotNull(str, "SystemProperties.get(property, \"N/A\")");
        return str;
    }

    public final String getFirmwareVersionName() {
        String systemPropertySummary = Intrinsics.areEqual("N/A", "N/A") ? getSystemPropertySummary("ro.build.version.incremental") : "N/A";
        if (Intrinsics.areEqual(systemPropertySummary, "N/A") || Intrinsics.areEqual(systemPropertySummary, "")) {
            systemPropertySummary = getSystemPropertySummary("ro.build.fingerprint");
        }
        return (Intrinsics.areEqual(systemPropertySummary, "N/A") || Intrinsics.areEqual(systemPropertySummary, "")) ? getSystemPropertySummary("ro.product.version.software") : systemPropertySummary;
    }

    public final String getDeviceModel() {
        return getSystemPropertySummary("ro.product.vendor.device");
    }
}
