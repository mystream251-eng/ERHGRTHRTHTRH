package com.dotscreen.tv.common.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.dotscreen.portal.utils.DVBTechnicolorManagerKt;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegacableDevices.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\fH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0015\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0010¢\u0006\u0002\b\u0018R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/dotscreen/tv/common/utils/TechnicolorDevice;", "Lcom/dotscreen/tv/common/utils/DVBDevice;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TUNER_INFO_PROVIDER_URL", "", "getTUNER_INFO_PROVIDER_URL", "()Ljava/lang/String;", "getContext", "()Landroid/content/Context;", "tunerMetrics", "Lcom/dotscreen/tv/common/utils/TunerMetrics;", "getTunerMetrics", "parseTunerInfoData", "Lcom/dotscreen/tv/common/utils/TechnicolorDevice$TechnicolorTunerInfo;", "parameters", "Lorg/json/JSONObject;", "stats", "refreshMetrics", "", "setTunerMetrics", "defaultNotAvailableMeasure", "Lcom/dotscreen/tv/common/utils/NotAvailableMeasure;", "setTunerMetrics$common_prodRelease", "TechnicolorTunerInfo", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TechnicolorDevice extends DVBDevice {
    private final String TUNER_INFO_PROVIDER_URL;
    private final Context context;
    private TunerMetrics tunerMetrics;

    public TechnicolorDevice(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TUNER_INFO_PROVIDER_URL = DVBTechnicolorManagerKt.PROVIDER_URL;
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getTUNER_INFO_PROVIDER_URL() {
        return this.TUNER_INFO_PROVIDER_URL;
    }

    @Override // com.dotscreen.tv.common.utils.DVBDevice
    public void setTunerMetrics$common_prodRelease(NotAvailableMeasure defaultNotAvailableMeasure) throws IOException {
        Cursor cursorQuery;
        TunerMetrics tunerMetrics;
        Intrinsics.checkParameterIsNotNull(defaultNotAvailableMeasure, "defaultNotAvailableMeasure");
        Uri uri = Uri.parse(this.TUNER_INFO_PROVIDER_URL);
        ContentResolver contentResolver = this.context.getContentResolver();
        if (contentResolver == null || (cursorQuery = contentResolver.query(uri, null, null, null, null)) == null) {
            return;
        }
        Cursor cursor = cursorQuery;
        Throwable th = (Throwable) null;
        try {
            Cursor cursor2 = cursor;
            if (cursor2.moveToFirst()) {
                TechnicolorTunerInfo tunerInfoData = parseTunerInfoData(new JSONObject(MegacableDevicesKt.getFormattedValue(cursor2, "parameters")), new JSONObject(MegacableDevicesKt.getFormattedValue(cursor2, "stats")));
                tunerMetrics = new TunerMetrics(new SignalInfoHelper.FrequencyMeasure(tunerInfoData.getFrequency(), null, 2, null), new SignalInfoHelper.RSSIMeasure(tunerInfoData.getSignal(), null, 2, null), new Measure(tunerInfoData.getSnr(), MeasureUnit.dB, null, 4, null), new Measure(tunerInfoData.getBer(), MeasureUnit.RATIO, null, 4, null), new Measure(tunerInfoData.getPaquetError(), MeasureUnit.COUNT, null, 4, null), new Measure(tunerInfoData.getQuality(), MeasureUnit.PERCENTAGE, null, 4, null));
            } else {
                tunerMetrics = new TunerMetrics(defaultNotAvailableMeasure);
            }
            this.tunerMetrics = tunerMetrics;
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursor, th);
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                CloseableKt.closeFinally(cursor, th2);
                throw th3;
            }
        }
    }

    @Override // com.dotscreen.tv.common.utils.DVBDevice
    public void refreshMetrics() {
        DVBDevice.setTunerMetrics$common_prodRelease$default(this, null, 1, null);
    }

    private final TechnicolorTunerInfo parseTunerInfoData(JSONObject parameters, JSONObject stats) {
        return new TechnicolorTunerInfo(parameters.optLong("frequency"), stats.optInt("rssi"), stats.optDouble("ber"), stats.optInt("snr"), stats.optInt("lost-packets"));
    }

    @Override // com.dotscreen.tv.common.utils.DVBDevice
    public TunerMetrics getTunerMetrics() {
        TunerMetrics tunerMetrics = this.tunerMetrics;
        if (tunerMetrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tunerMetrics");
        }
        return tunerMetrics;
    }

    /* JADX INFO: compiled from: MegacableDevices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010¨\u0006!"}, d2 = {"Lcom/dotscreen/tv/common/utils/TechnicolorDevice$TechnicolorTunerInfo;", "", "frequency", "", "signal", "", "ber", "", "snr", "paquetError", "(JIDII)V", "getBer", "()D", "getFrequency", "()J", "getPaquetError", "()I", "quality", "getQuality", "getSignal", "getSnr", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class TechnicolorTunerInfo {
        private final double ber;
        private final long frequency;
        private final int paquetError;
        private final int signal;
        private final int snr;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getFrequency() {
            return this.frequency;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getSignal() {
            return this.signal;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final double getBer() {
            return this.ber;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getSnr() {
            return this.snr;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getPaquetError() {
            return this.paquetError;
        }

        public final TechnicolorTunerInfo copy(long frequency, int signal, double ber, int snr, int paquetError) {
            return new TechnicolorTunerInfo(frequency, signal, ber, snr, paquetError);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TechnicolorTunerInfo)) {
                return false;
            }
            TechnicolorTunerInfo technicolorTunerInfo = (TechnicolorTunerInfo) other;
            return this.frequency == technicolorTunerInfo.frequency && this.signal == technicolorTunerInfo.signal && Double.compare(this.ber, technicolorTunerInfo.ber) == 0 && this.snr == technicolorTunerInfo.snr && this.paquetError == technicolorTunerInfo.paquetError;
        }

        public int hashCode() {
            long j = this.frequency;
            int i = ((((int) (j ^ (j >>> 32))) * 31) + this.signal) * 31;
            long jDoubleToLongBits = Double.doubleToLongBits(this.ber);
            return ((((i + ((int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits))) * 31) + this.snr) * 31) + this.paquetError;
        }

        public String toString() {
            return "TechnicolorTunerInfo(frequency=" + this.frequency + ", signal=" + this.signal + ", ber=" + this.ber + ", snr=" + this.snr + ", paquetError=" + this.paquetError + ")";
        }

        public TechnicolorTunerInfo(long j, int i, double d, int i2, int i3) {
            this.frequency = j;
            this.signal = i;
            this.ber = d;
            this.snr = i2;
            this.paquetError = i3;
        }

        public final long getFrequency() {
            return this.frequency;
        }

        public final int getSignal() {
            return this.signal;
        }

        public final double getBer() {
            return this.ber;
        }

        public final int getSnr() {
            return this.snr;
        }

        public final int getPaquetError() {
            return this.paquetError;
        }

        public final int getQuality() {
            return SignalInfoHelper.INSTANCE.getQualityFromSnr(this.snr, 280, 420);
        }
    }
}
