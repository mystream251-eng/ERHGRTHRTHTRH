package com.dotscreen.tv.common.utils;

import kotlin.Metadata;

/* JADX INFO: compiled from: Device.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/tv/common/utils/Device;", "", "()V", "deviceInfo", "Lcom/dotscreen/tv/common/utils/DeviceInfo;", "getDeviceInfo", "()Lcom/dotscreen/tv/common/utils/DeviceInfo;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class Device {
    private final DeviceInfo deviceInfo = DeviceInfo.INSTANCE;

    public final DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }
}
