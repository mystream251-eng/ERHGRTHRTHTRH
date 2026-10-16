package com.dotscreen.tv.common.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Physic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/tv/common/utils/NotAvailableMeasure;", "Lcom/dotscreen/tv/common/utils/Measure;", "defaultText", "", "(Ljava/lang/String;)V", "getValue", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class NotAvailableMeasure extends Measure {
    private final String defaultText;

    /* JADX WARN: Multi-variable type inference failed */
    public NotAvailableMeasure() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotAvailableMeasure(String defaultText) {
        super(0.0d, MeasureUnit.NO_MEASURE_UNIT, null, 4, null);
        Intrinsics.checkParameterIsNotNull(defaultText, "defaultText");
        this.defaultText = defaultText;
    }

    public /* synthetic */ NotAvailableMeasure(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "N/A" : str);
    }

    /* JADX INFO: renamed from: getValue, reason: from getter */
    public final String getDefaultText() {
        return this.defaultText;
    }
}
