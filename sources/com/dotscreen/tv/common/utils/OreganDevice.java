package com.dotscreen.tv.common.utils;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableDevices.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0010¢\u0006\u0002\b\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/tv/common/utils/OreganDevice;", "Lcom/dotscreen/tv/common/utils/DVBDevice;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getTunerMetrics", "Lcom/dotscreen/tv/common/utils/TunerMetrics;", "refreshMetrics", "", "setTunerMetrics", "defaultNotAvailableMeasure", "Lcom/dotscreen/tv/common/utils/NotAvailableMeasure;", "setTunerMetrics$common_prodRelease", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class OreganDevice extends DVBDevice {
    private final Context context;

    @Override // com.dotscreen.tv.common.utils.DVBDevice
    public void refreshMetrics() {
    }

    @Override // com.dotscreen.tv.common.utils.DVBDevice
    public void setTunerMetrics$common_prodRelease(NotAvailableMeasure defaultNotAvailableMeasure) {
        Intrinsics.checkParameterIsNotNull(defaultNotAvailableMeasure, "defaultNotAvailableMeasure");
    }

    public OreganDevice(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // com.dotscreen.tv.common.utils.DVBDevice
    public TunerMetrics getTunerMetrics() {
        return new TunerMetrics(null, null, null, null, null, null, 63, null);
    }
}
