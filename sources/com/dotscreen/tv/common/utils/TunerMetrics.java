package com.dotscreen.tv.common.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Device.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004BA\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006¢\u0006\u0002\u0010\fJ\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000e¨\u0006\""}, d2 = {"Lcom/dotscreen/tv/common/utils/TunerMetrics;", "", "defaultNotAvailableMeasure", "Lcom/dotscreen/tv/common/utils/NotAvailableMeasure;", "(Lcom/dotscreen/tv/common/utils/NotAvailableMeasure;)V", "frequency", "Lcom/dotscreen/tv/common/utils/Measure;", "signalStrength", "snr", "ber", "paquetError", "quality", "(Lcom/dotscreen/tv/common/utils/Measure;Lcom/dotscreen/tv/common/utils/Measure;Lcom/dotscreen/tv/common/utils/Measure;Lcom/dotscreen/tv/common/utils/Measure;Lcom/dotscreen/tv/common/utils/Measure;Lcom/dotscreen/tv/common/utils/Measure;)V", "getBer", "()Lcom/dotscreen/tv/common/utils/Measure;", "getFrequency", "getPaquetError", "getQuality", "getSignalStrength", "getSnr", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class TunerMetrics {
    private final Measure ber;
    private final Measure frequency;
    private final Measure paquetError;
    private final Measure quality;
    private final Measure signalStrength;
    private final Measure snr;

    public TunerMetrics() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ TunerMetrics copy$default(TunerMetrics tunerMetrics, Measure measure, Measure measure2, Measure measure3, Measure measure4, Measure measure5, Measure measure6, int i, Object obj) {
        if ((i & 1) != 0) {
            measure = tunerMetrics.frequency;
        }
        if ((i & 2) != 0) {
            measure2 = tunerMetrics.signalStrength;
        }
        Measure measure7 = measure2;
        if ((i & 4) != 0) {
            measure3 = tunerMetrics.snr;
        }
        Measure measure8 = measure3;
        if ((i & 8) != 0) {
            measure4 = tunerMetrics.ber;
        }
        Measure measure9 = measure4;
        if ((i & 16) != 0) {
            measure5 = tunerMetrics.paquetError;
        }
        Measure measure10 = measure5;
        if ((i & 32) != 0) {
            measure6 = tunerMetrics.quality;
        }
        return tunerMetrics.copy(measure, measure7, measure8, measure9, measure10, measure6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Measure getFrequency() {
        return this.frequency;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Measure getSignalStrength() {
        return this.signalStrength;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Measure getSnr() {
        return this.snr;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Measure getBer() {
        return this.ber;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Measure getPaquetError() {
        return this.paquetError;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Measure getQuality() {
        return this.quality;
    }

    public final TunerMetrics copy(Measure frequency, Measure signalStrength, Measure snr, Measure ber, Measure paquetError, Measure quality) {
        Intrinsics.checkParameterIsNotNull(frequency, "frequency");
        Intrinsics.checkParameterIsNotNull(signalStrength, "signalStrength");
        Intrinsics.checkParameterIsNotNull(snr, "snr");
        Intrinsics.checkParameterIsNotNull(ber, "ber");
        Intrinsics.checkParameterIsNotNull(paquetError, "paquetError");
        Intrinsics.checkParameterIsNotNull(quality, "quality");
        return new TunerMetrics(frequency, signalStrength, snr, ber, paquetError, quality);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TunerMetrics)) {
            return false;
        }
        TunerMetrics tunerMetrics = (TunerMetrics) other;
        return Intrinsics.areEqual(this.frequency, tunerMetrics.frequency) && Intrinsics.areEqual(this.signalStrength, tunerMetrics.signalStrength) && Intrinsics.areEqual(this.snr, tunerMetrics.snr) && Intrinsics.areEqual(this.ber, tunerMetrics.ber) && Intrinsics.areEqual(this.paquetError, tunerMetrics.paquetError) && Intrinsics.areEqual(this.quality, tunerMetrics.quality);
    }

    public int hashCode() {
        Measure measure = this.frequency;
        int iHashCode = (measure != null ? measure.hashCode() : 0) * 31;
        Measure measure2 = this.signalStrength;
        int iHashCode2 = (iHashCode + (measure2 != null ? measure2.hashCode() : 0)) * 31;
        Measure measure3 = this.snr;
        int iHashCode3 = (iHashCode2 + (measure3 != null ? measure3.hashCode() : 0)) * 31;
        Measure measure4 = this.ber;
        int iHashCode4 = (iHashCode3 + (measure4 != null ? measure4.hashCode() : 0)) * 31;
        Measure measure5 = this.paquetError;
        int iHashCode5 = (iHashCode4 + (measure5 != null ? measure5.hashCode() : 0)) * 31;
        Measure measure6 = this.quality;
        return iHashCode5 + (measure6 != null ? measure6.hashCode() : 0);
    }

    public String toString() {
        return "TunerMetrics(frequency=" + this.frequency + ", signalStrength=" + this.signalStrength + ", snr=" + this.snr + ", ber=" + this.ber + ", paquetError=" + this.paquetError + ", quality=" + this.quality + ")";
    }

    public TunerMetrics(Measure frequency, Measure signalStrength, Measure snr, Measure ber, Measure paquetError, Measure quality) {
        Intrinsics.checkParameterIsNotNull(frequency, "frequency");
        Intrinsics.checkParameterIsNotNull(signalStrength, "signalStrength");
        Intrinsics.checkParameterIsNotNull(snr, "snr");
        Intrinsics.checkParameterIsNotNull(ber, "ber");
        Intrinsics.checkParameterIsNotNull(paquetError, "paquetError");
        Intrinsics.checkParameterIsNotNull(quality, "quality");
        this.frequency = frequency;
        this.signalStrength = signalStrength;
        this.snr = snr;
        this.ber = ber;
        this.paquetError = paquetError;
        this.quality = quality;
    }

    public /* synthetic */ TunerMetrics(NotAvailableMeasure notAvailableMeasure, NotAvailableMeasure notAvailableMeasure2, NotAvailableMeasure notAvailableMeasure3, NotAvailableMeasure notAvailableMeasure4, NotAvailableMeasure notAvailableMeasure5, NotAvailableMeasure notAvailableMeasure6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new NotAvailableMeasure(null, 1, null) : notAvailableMeasure, (i & 2) != 0 ? new NotAvailableMeasure(null, 1, null) : notAvailableMeasure2, (i & 4) != 0 ? new NotAvailableMeasure(null, 1, null) : notAvailableMeasure3, (i & 8) != 0 ? new NotAvailableMeasure(null, 1, null) : notAvailableMeasure4, (i & 16) != 0 ? new NotAvailableMeasure(null, 1, null) : notAvailableMeasure5, (i & 32) != 0 ? new NotAvailableMeasure(null, 1, null) : notAvailableMeasure6);
    }

    public final Measure getFrequency() {
        return this.frequency;
    }

    public final Measure getSignalStrength() {
        return this.signalStrength;
    }

    public final Measure getSnr() {
        return this.snr;
    }

    public final Measure getBer() {
        return this.ber;
    }

    public final Measure getPaquetError() {
        return this.paquetError;
    }

    public final Measure getQuality() {
        return this.quality;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TunerMetrics(NotAvailableMeasure defaultNotAvailableMeasure) {
        this(null, null, null, null, null, null, 63, null);
        Intrinsics.checkParameterIsNotNull(defaultNotAvailableMeasure, "defaultNotAvailableMeasure");
        NotAvailableMeasure notAvailableMeasure = defaultNotAvailableMeasure;
        new TunerMetrics(notAvailableMeasure, notAvailableMeasure, notAvailableMeasure, notAvailableMeasure, notAvailableMeasure, notAvailableMeasure);
    }
}
