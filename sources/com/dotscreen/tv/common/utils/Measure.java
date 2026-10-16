package com.dotscreen.tv.common.utils;

import android.util.Log;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: Physic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0007H\u0002J\u0012\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0003H\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/tv/common/utils/Measure;", "", "measureValue", "", "measureUnit", "Lcom/dotscreen/tv/common/utils/MeasureUnit;", "desiredDisplayFactor", "Lcom/dotscreen/tv/common/utils/PowerOfTen;", "(DLcom/dotscreen/tv/common/utils/MeasureUnit;Lcom/dotscreen/tv/common/utils/PowerOfTen;)V", "getMeasureValue", "()D", "applyFactor", "factor", "getFormattedValue", "", "hideUselessZero", "value", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public class Measure {
    private final PowerOfTen desiredDisplayFactor;
    private final MeasureUnit measureUnit;
    private final double measureValue;

    public Measure(double d, MeasureUnit measureUnit, PowerOfTen powerOfTen) {
        Intrinsics.checkParameterIsNotNull(measureUnit, "measureUnit");
        this.measureValue = d;
        this.measureUnit = measureUnit;
        this.desiredDisplayFactor = powerOfTen;
    }

    public /* synthetic */ Measure(double d, MeasureUnit measureUnit, PowerOfTen powerOfTen, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(d, measureUnit, (i & 4) != 0 ? (PowerOfTen) null : powerOfTen);
    }

    public final double getMeasureValue() {
        return this.measureValue;
    }

    private final double applyFactor(PowerOfTen factor) {
        return this.measureValue * Math.pow(10.0d, this.measureUnit.getFactor().getPower() - factor.getPower());
    }

    public static /* synthetic */ String getFormattedValue$default(Measure measure, PowerOfTen powerOfTen, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFormattedValue");
        }
        if ((i & 1) != 0) {
            powerOfTen = (PowerOfTen) null;
        }
        return measure.getFormattedValue(powerOfTen);
    }

    public final String getFormattedValue(PowerOfTen factor) {
        Log.d("Ludo", "getValue1");
        if (factor != null) {
            return hideUselessZero(applyFactor(factor)) + ' ' + factor.getShortName() + this.measureUnit;
        }
        if (this.desiredDisplayFactor == null) {
            return hideUselessZero(this.measureValue) + ' ' + this.measureUnit;
        }
        return hideUselessZero(applyFactor(this.desiredDisplayFactor)) + ' ' + this.desiredDisplayFactor.getShortName() + this.measureUnit;
    }

    private final String hideUselessZero(double value) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        sb.append(' ');
        long j = (long) value;
        double d = j;
        sb.append(d);
        Log.d("Ludo", sb.toString());
        if (value == d) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            str = String.format("%d", Arrays.copyOf(new Object[]{Long.valueOf(j)}, 1));
        } else {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            str = String.format("%s", Arrays.copyOf(new Object[]{Double.valueOf(value)}, 1));
        }
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        return str;
    }

    public String toString() {
        return getFormattedValue$default(this, null, 1, null);
    }
}
