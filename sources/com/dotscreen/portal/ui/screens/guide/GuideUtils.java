package com.dotscreen.portal.ui.screens.guide;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;

/* JADX INFO: compiled from: GuideUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/GuideUtils;", "", "()V", "sWidthPerHour", "", "ceilTime", "", "timeMs", "timeUnit", "convertMillisToPixel", "millis", "startMillis", "endMillis", "convertPixelToMillis", "pixel", "setWidthPerHour", "", "widthPerHour", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class GuideUtils {
    public static final GuideUtils INSTANCE = new GuideUtils();
    private static int sWidthPerHour;

    private GuideUtils() {
    }

    public final void setWidthPerHour(int widthPerHour) {
        sWidthPerHour = widthPerHour;
    }

    public final int convertMillisToPixel(long millis) {
        return (int) ((millis * ((long) sWidthPerHour)) / TimeUnit.HOURS.toMillis(1L));
    }

    public final int convertMillisToPixel(long startMillis, long endMillis) {
        return convertMillisToPixel(endMillis) - convertMillisToPixel(startMillis);
    }

    public final long convertPixelToMillis(int pixel) {
        return (((long) pixel) * TimeUnit.HOURS.toMillis(1L)) / ((long) sWidthPerHour);
    }

    public final long ceilTime(long timeMs, long timeUnit) {
        return (timeMs + timeUnit) - (timeMs % timeUnit);
    }
}
