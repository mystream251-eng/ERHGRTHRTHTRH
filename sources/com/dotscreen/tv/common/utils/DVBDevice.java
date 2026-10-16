package com.dotscreen.tv.common.utils;

import kotlin.Metadata;

/* JADX INFO: compiled from: Device.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\u0017\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\tH ¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/tv/common/utils/DVBDevice;", "Lcom/dotscreen/tv/common/utils/Device;", "()V", "getTunerMetrics", "Lcom/dotscreen/tv/common/utils/TunerMetrics;", "refreshMetrics", "", "setTunerMetrics", "defaultNotAvailableMeasure", "Lcom/dotscreen/tv/common/utils/NotAvailableMeasure;", "setTunerMetrics$common_prodRelease", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class DVBDevice extends Device {
    public abstract TunerMetrics getTunerMetrics();

    public abstract void refreshMetrics();

    public abstract void setTunerMetrics$common_prodRelease(NotAvailableMeasure defaultNotAvailableMeasure);

    public static /* synthetic */ void setTunerMetrics$common_prodRelease$default(DVBDevice dVBDevice, NotAvailableMeasure notAvailableMeasure, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setTunerMetrics");
        }
        if ((i & 1) != 0) {
            notAvailableMeasure = new NotAvailableMeasure(null, 1, null);
        }
        dVBDevice.setTunerMetrics$common_prodRelease(notAvailableMeasure);
    }
}
