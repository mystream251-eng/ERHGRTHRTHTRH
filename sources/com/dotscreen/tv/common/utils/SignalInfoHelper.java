package com.dotscreen.tv.common.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Physic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\b\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\n"}, d2 = {"Lcom/dotscreen/tv/common/utils/SignalInfoHelper;", "", "()V", "getQualityFromSnr", "", "snr", "minValue", "maxValue", "FrequencyMeasure", "RSSIMeasure", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SignalInfoHelper {
    public static final SignalInfoHelper INSTANCE = new SignalInfoHelper();

    private SignalInfoHelper() {
    }

    /* JADX INFO: compiled from: Physic.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/tv/common/utils/SignalInfoHelper$FrequencyMeasure;", "Lcom/dotscreen/tv/common/utils/Measure;", "frequency", "", "factor", "Lcom/dotscreen/tv/common/utils/PowerOfTen;", "(DLcom/dotscreen/tv/common/utils/PowerOfTen;)V", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class FrequencyMeasure extends Measure {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FrequencyMeasure(double d, PowerOfTen factor) {
            super(d, MeasureUnit.FREQUENCY, factor);
            Intrinsics.checkParameterIsNotNull(factor, "factor");
        }

        public /* synthetic */ FrequencyMeasure(double d, PowerOfTen powerOfTen, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(d, (i & 2) != 0 ? PowerOfTen.UNIT : powerOfTen);
        }
    }

    /* JADX INFO: compiled from: Physic.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/tv/common/utils/SignalInfoHelper$RSSIMeasure;", "Lcom/dotscreen/tv/common/utils/Measure;", "rssi", "", "factor", "Lcom/dotscreen/tv/common/utils/PowerOfTen;", "(DLcom/dotscreen/tv/common/utils/PowerOfTen;)V", "formatTodBmV", "", "precision", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class RSSIMeasure extends Measure {
        private final double rssi;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RSSIMeasure(double d, PowerOfTen factor) {
            super(d, MeasureUnit.dBm, factor);
            Intrinsics.checkParameterIsNotNull(factor, "factor");
            this.rssi = d;
        }

        public /* synthetic */ RSSIMeasure(double d, PowerOfTen powerOfTen, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(d, (i & 2) != 0 ? PowerOfTen.UNIT : powerOfTen);
        }

        public static /* synthetic */ String formatTodBmV$default(RSSIMeasure rSSIMeasure, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = 0;
            }
            return rSSIMeasure.formatTodBmV(i);
        }

        public final String formatTodBmV(int precision) {
            if (precision > 0) {
                return String.valueOf(PhysicKt.round(PhysicKt.convertdBmtodBmV$default(this.rssi, 0.0d, 2, null), precision));
            }
            return PhysicKt.convertdBmtodBmV$default(this.rssi, 0.0d, 2, null) + " dBmV";
        }
    }

    public final int getQualityFromSnr(int snr, int minValue, int maxValue) {
        int i = snr * 10;
        if (i < minValue) {
            return 0;
        }
        if (i >= maxValue) {
            return 100;
        }
        return (int) MathHelper.INSTANCE.getLinearInterpolation(i, minValue, maxValue, 0, 100);
    }
}
