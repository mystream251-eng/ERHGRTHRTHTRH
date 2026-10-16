package com.dotscreen.tv.common.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Physic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\r\u001a\u00020\u0003J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fj\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/tv/common/utils/MeasureUnit;", "", "shortName", "", "factor", "Lcom/dotscreen/tv/common/utils/PowerOfTen;", "(Ljava/lang/String;ILjava/lang/String;Lcom/dotscreen/tv/common/utils/PowerOfTen;)V", "getFactor", "()Lcom/dotscreen/tv/common/utils/PowerOfTen;", "setFactor", "(Lcom/dotscreen/tv/common/utils/PowerOfTen;)V", "getShortName", "()Ljava/lang/String;", "getFactorPrefix", "toString", "PERCENTAGE", "COUNT", "RATIO", "NO_MEASURE_UNIT", "FREQUENCY", "dB", "dBm", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public enum MeasureUnit {
    PERCENTAGE("%", PowerOfTen.UNIT),
    COUNT("", PowerOfTen.UNIT),
    RATIO("", PowerOfTen.UNIT),
    NO_MEASURE_UNIT("", PowerOfTen.UNIT),
    FREQUENCY { // from class: com.dotscreen.tv.common.utils.MeasureUnit.FREQUENCY
        @Override // com.dotscreen.tv.common.utils.MeasureUnit, java.lang.Enum
        public String toString() {
            return getFactorPrefix() + getShortName();
        }
    },
    dB("dB", PowerOfTen.UNIT),
    dBm("dBm", PowerOfTen.UNIT);

    private PowerOfTen factor;
    private final String shortName;

    MeasureUnit(String str, PowerOfTen powerOfTen) {
        this.shortName = str;
        this.factor = powerOfTen;
    }

    /* synthetic */ MeasureUnit(String str, PowerOfTen powerOfTen, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, powerOfTen);
    }

    public final PowerOfTen getFactor() {
        return this.factor;
    }

    public final String getShortName() {
        return this.shortName;
    }

    public final void setFactor(PowerOfTen powerOfTen) {
        Intrinsics.checkParameterIsNotNull(powerOfTen, "<set-?>");
        this.factor = powerOfTen;
    }

    public final String getFactorPrefix() {
        return this.factor.getShortName();
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.shortName;
    }
}
