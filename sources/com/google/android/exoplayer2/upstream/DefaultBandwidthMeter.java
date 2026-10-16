package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.os.Handler;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.EventDispatcher;
import com.google.android.exoplayer2.util.SlidingPercentile;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class DefaultBandwidthMeter implements BandwidthMeter, TransferListener {
    private static final int BYTES_TRANSFERRED_FOR_ESTIMATE = 524288;
    public static final long DEFAULT_INITIAL_BITRATE_ESTIMATE = 1000000;
    public static final int DEFAULT_SLIDING_WINDOW_MAX_WEIGHT = 2000;
    private static final int ELAPSED_MILLIS_FOR_ESTIMATE = 2000;
    private long bitrateEstimate;
    private final Clock clock;
    private final EventDispatcher<BandwidthMeter.EventListener> eventDispatcher;
    private long sampleBytesTransferred;
    private long sampleStartTimeMs;
    private final SlidingPercentile slidingPercentile;
    private int streamCount;
    private long totalBytesTransferred;
    private long totalElapsedTimeMs;
    public static final Map<String, int[]> DEFAULT_INITIAL_BITRATE_COUNTRY_GROUPS = createInitialBitrateCountryGroupAssignment();
    public static final long[] DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI = {5700000, 3400000, 1900000, 1000000, 400000};
    public static final long[] DEFAULT_INITIAL_BITRATE_ESTIMATES_2G = {169000, 129000, 114000, 102000, 87000};
    public static final long[] DEFAULT_INITIAL_BITRATE_ESTIMATES_3G = {2100000, 1300000, 950000, 700000, 400000};
    public static final long[] DEFAULT_INITIAL_BITRATE_ESTIMATES_4G = {6900000, 4300000, 2700000, 1600000, 450000};

    @Override // com.google.android.exoplayer2.upstream.BandwidthMeter
    public TransferListener getTransferListener() {
        return this;
    }

    @Override // com.google.android.exoplayer2.upstream.TransferListener
    public void onTransferInitializing(DataSource dataSource, DataSpec dataSpec, boolean z) {
    }

    public static final class Builder {
        private Clock clock;
        private final Context context;
        private Handler eventHandler;
        private BandwidthMeter.EventListener eventListener;
        private SparseArray<Long> initialBitrateEstimates;
        private int slidingWindowMaxWeight;

        @Deprecated
        public Builder() {
            this(null);
        }

        public Builder(Context context) {
            this.context = context == null ? null : context.getApplicationContext();
            this.initialBitrateEstimates = getInitialBitrateEstimatesForCountry(Util.getCountryCode(context));
            this.slidingWindowMaxWeight = 2000;
            this.clock = Clock.DEFAULT;
        }

        public Builder setEventListener(Handler handler, BandwidthMeter.EventListener eventListener) {
            Assertions.checkArgument((handler == null || eventListener == null) ? false : true);
            this.eventHandler = handler;
            this.eventListener = eventListener;
            return this;
        }

        public Builder setSlidingWindowMaxWeight(int i) {
            this.slidingWindowMaxWeight = i;
            return this;
        }

        public Builder setInitialBitrateEstimate(long j) {
            for (int i = 0; i < this.initialBitrateEstimates.size(); i++) {
                this.initialBitrateEstimates.setValueAt(i, Long.valueOf(j));
            }
            return this;
        }

        public Builder setInitialBitrateEstimate(int i, long j) {
            this.initialBitrateEstimates.put(i, Long.valueOf(j));
            return this;
        }

        public Builder setInitialBitrateEstimate(String str) {
            this.initialBitrateEstimates = getInitialBitrateEstimatesForCountry(Util.toUpperInvariant(str));
            return this;
        }

        public Builder setClock(Clock clock) {
            this.clock = clock;
            return this;
        }

        public DefaultBandwidthMeter build() {
            BandwidthMeter.EventListener eventListener;
            Long l = this.initialBitrateEstimates.get(Util.getNetworkType(this.context));
            if (l == null) {
                l = this.initialBitrateEstimates.get(0);
            }
            DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter(l.longValue(), this.slidingWindowMaxWeight, this.clock);
            Handler handler = this.eventHandler;
            if (handler != null && (eventListener = this.eventListener) != null) {
                defaultBandwidthMeter.addEventListener(handler, eventListener);
            }
            return defaultBandwidthMeter;
        }

        private static SparseArray<Long> getInitialBitrateEstimatesForCountry(String str) {
            int[] countryGroupIndices = getCountryGroupIndices(str);
            SparseArray<Long> sparseArray = new SparseArray<>(6);
            sparseArray.append(0, 1000000L);
            sparseArray.append(2, Long.valueOf(DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI[countryGroupIndices[0]]));
            sparseArray.append(3, Long.valueOf(DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_2G[countryGroupIndices[1]]));
            sparseArray.append(4, Long.valueOf(DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_3G[countryGroupIndices[2]]));
            sparseArray.append(5, Long.valueOf(DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_4G[countryGroupIndices[3]]));
            sparseArray.append(7, Long.valueOf(DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI[countryGroupIndices[0]]));
            return sparseArray;
        }

        private static int[] getCountryGroupIndices(String str) {
            int[] iArr = DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_COUNTRY_GROUPS.get(str);
            return iArr == null ? new int[]{2, 2, 2, 2} : iArr;
        }
    }

    public DefaultBandwidthMeter() {
        this(1000000L, 2000, Clock.DEFAULT);
    }

    @Deprecated
    public DefaultBandwidthMeter(Handler handler, BandwidthMeter.EventListener eventListener) {
        this(1000000L, 2000, Clock.DEFAULT);
        if (handler == null || eventListener == null) {
            return;
        }
        addEventListener(handler, eventListener);
    }

    @Deprecated
    public DefaultBandwidthMeter(Handler handler, BandwidthMeter.EventListener eventListener, int i) {
        this(1000000L, i, Clock.DEFAULT);
        if (handler == null || eventListener == null) {
            return;
        }
        addEventListener(handler, eventListener);
    }

    private DefaultBandwidthMeter(long j, int i, Clock clock) {
        this.eventDispatcher = new EventDispatcher<>();
        this.slidingPercentile = new SlidingPercentile(i);
        this.clock = clock;
        this.bitrateEstimate = j;
    }

    @Override // com.google.android.exoplayer2.upstream.BandwidthMeter
    public synchronized long getBitrateEstimate() {
        return this.bitrateEstimate;
    }

    @Override // com.google.android.exoplayer2.upstream.BandwidthMeter
    public void addEventListener(Handler handler, BandwidthMeter.EventListener eventListener) {
        this.eventDispatcher.addListener(handler, eventListener);
    }

    @Override // com.google.android.exoplayer2.upstream.BandwidthMeter
    public void removeEventListener(BandwidthMeter.EventListener eventListener) {
        this.eventDispatcher.removeListener(eventListener);
    }

    @Override // com.google.android.exoplayer2.upstream.TransferListener
    public synchronized void onTransferStart(DataSource dataSource, DataSpec dataSpec, boolean z) {
        if (z) {
            if (this.streamCount == 0) {
                this.sampleStartTimeMs = this.clock.elapsedRealtime();
            }
            this.streamCount++;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.TransferListener
    public synchronized void onBytesTransferred(DataSource dataSource, DataSpec dataSpec, boolean z, int i) {
        if (z) {
            this.sampleBytesTransferred += (long) i;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.TransferListener
    public synchronized void onTransferEnd(DataSource dataSource, DataSpec dataSpec, boolean z) {
        if (z) {
            Assertions.checkState(this.streamCount > 0);
            long jElapsedRealtime = this.clock.elapsedRealtime();
            int i = (int) (jElapsedRealtime - this.sampleStartTimeMs);
            long j = i;
            this.totalElapsedTimeMs += j;
            this.totalBytesTransferred += this.sampleBytesTransferred;
            if (i > 0) {
                this.slidingPercentile.addSample((int) Math.sqrt(this.sampleBytesTransferred), (this.sampleBytesTransferred * 8000) / j);
                if (this.totalElapsedTimeMs >= AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS || this.totalBytesTransferred >= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                    this.bitrateEstimate = (long) this.slidingPercentile.getPercentile(0.5f);
                }
            }
            notifyBandwidthSample(i, this.sampleBytesTransferred, this.bitrateEstimate);
            int i2 = this.streamCount - 1;
            this.streamCount = i2;
            if (i2 > 0) {
                this.sampleStartTimeMs = jElapsedRealtime;
            }
            this.sampleBytesTransferred = 0L;
        }
    }

    private void notifyBandwidthSample(final int i, final long j, final long j2) {
        this.eventDispatcher.dispatch(new EventDispatcher.Event() { // from class: com.google.android.exoplayer2.upstream.-$$Lambda$DefaultBandwidthMeter$0dWpVoCfeEm8PONlag-OKGMu96M
            @Override // com.google.android.exoplayer2.util.EventDispatcher.Event
            public final void sendTo(Object obj) {
                ((BandwidthMeter.EventListener) obj).onBandwidthSample(i, j, j2);
            }
        });
    }

    private static Map<String, int[]> createInitialBitrateCountryGroupAssignment() {
        HashMap map = new HashMap();
        map.put("AD", new int[]{1, 0, 0, 0});
        map.put("AE", new int[]{1, 3, 4, 4});
        map.put("AF", new int[]{4, 4, 3, 2});
        map.put("AG", new int[]{3, 2, 1, 2});
        map.put("AI", new int[]{1, 0, 0, 2});
        map.put("AL", new int[]{1, 1, 1, 1});
        map.put("AM", new int[]{2, 2, 4, 3});
        map.put("AO", new int[]{2, 4, 2, 0});
        map.put("AR", new int[]{2, 3, 2, 3});
        map.put("AS", new int[]{3, 4, 4, 1});
        map.put("AT", new int[]{0, 1, 0, 0});
        map.put("AU", new int[]{0, 3, 0, 0});
        map.put("AW", new int[]{1, 1, 0, 4});
        map.put("AX", new int[]{0, 1, 0, 0});
        map.put("AZ", new int[]{3, 3, 2, 2});
        map.put("BA", new int[]{1, 1, 1, 2});
        map.put("BB", new int[]{0, 1, 0, 0});
        map.put("BD", new int[]{2, 1, 3, 2});
        map.put("BE", new int[]{0, 0, 0, 0});
        map.put("BF", new int[]{4, 4, 4, 1});
        map.put("BG", new int[]{0, 0, 0, 1});
        map.put("BH", new int[]{2, 1, 3, 4});
        map.put("BI", new int[]{4, 3, 4, 4});
        map.put("BJ", new int[]{4, 3, 4, 3});
        map.put("BL", new int[]{1, 0, 1, 2});
        map.put("BM", new int[]{1, 0, 0, 0});
        map.put("BN", new int[]{4, 3, 3, 3});
        map.put("BO", new int[]{2, 2, 1, 2});
        map.put("BQ", new int[]{1, 1, 2, 4});
        map.put("BR", new int[]{2, 3, 2, 2});
        map.put("BS", new int[]{1, 1, 0, 2});
        map.put("BT", new int[]{3, 0, 2, 1});
        map.put("BW", new int[]{4, 4, 2, 3});
        map.put("BY", new int[]{1, 1, 1, 1});
        map.put("BZ", new int[]{2, 3, 3, 1});
        map.put("CA", new int[]{0, 2, 2, 3});
        map.put("CD", new int[]{4, 4, 2, 1});
        map.put("CF", new int[]{4, 4, 3, 3});
        map.put("CG", new int[]{4, 4, 4, 4});
        map.put("CH", new int[]{0, 0, 0, 0});
        map.put("CI", new int[]{4, 4, 4, 4});
        map.put("CK", new int[]{2, 4, 2, 0});
        map.put("CL", new int[]{2, 2, 2, 3});
        map.put("CM", new int[]{3, 4, 3, 1});
        map.put("CN", new int[]{2, 0, 1, 2});
        map.put("CO", new int[]{2, 3, 2, 1});
        map.put("CR", new int[]{2, 2, 4, 4});
        map.put("CU", new int[]{4, 4, 4, 1});
        map.put("CV", new int[]{2, 2, 2, 4});
        map.put("CW", new int[]{1, 1, 0, 0});
        map.put("CX", new int[]{1, 2, 2, 2});
        map.put("CY", new int[]{1, 1, 0, 0});
        map.put("CZ", new int[]{0, 1, 0, 0});
        map.put("DE", new int[]{0, 2, 2, 2});
        map.put("DJ", new int[]{3, 4, 4, 0});
        map.put("DK", new int[]{0, 0, 0, 0});
        map.put("DM", new int[]{2, 0, 3, 4});
        map.put("DO", new int[]{3, 3, 4, 4});
        map.put("DZ", new int[]{3, 3, 4, 4});
        map.put("EC", new int[]{2, 3, 3, 1});
        map.put("EE", new int[]{0, 0, 0, 0});
        map.put("EG", new int[]{3, 3, 1, 1});
        map.put("EH", new int[]{2, 0, 2, 3});
        map.put("ER", new int[]{4, 2, 2, 2});
        map.put("ES", new int[]{0, 0, 1, 1});
        map.put("ET", new int[]{4, 4, 4, 0});
        map.put("FI", new int[]{0, 0, 1, 0});
        map.put("FJ", new int[]{3, 2, 3, 3});
        map.put("FK", new int[]{3, 4, 2, 1});
        map.put("FM", new int[]{4, 2, 4, 0});
        map.put("FO", new int[]{0, 0, 0, 1});
        map.put("FR", new int[]{1, 0, 2, 1});
        map.put("GA", new int[]{3, 3, 2, 1});
        map.put("GB", new int[]{0, 1, 3, 2});
        map.put("GD", new int[]{2, 0, 3, 0});
        map.put("GE", new int[]{1, 1, 0, 3});
        map.put("GF", new int[]{1, 2, 4, 4});
        map.put("GG", new int[]{0, 1, 0, 0});
        map.put("GH", new int[]{3, 2, 2, 2});
        map.put("GI", new int[]{0, 0, 0, 1});
        map.put("GL", new int[]{2, 4, 1, 4});
        map.put("GM", new int[]{4, 3, 3, 0});
        map.put("GN", new int[]{4, 4, 3, 4});
        map.put("GP", new int[]{2, 2, 1, 3});
        map.put("GQ", new int[]{4, 4, 3, 1});
        map.put("GR", new int[]{1, 1, 0, 1});
        map.put("GT", new int[]{3, 2, 3, 4});
        map.put("GU", new int[]{1, 0, 4, 4});
        map.put("GW", new int[]{4, 4, 4, 0});
        map.put("GY", new int[]{3, 4, 1, 0});
        map.put("HK", new int[]{0, 2, 3, 4});
        map.put("HN", new int[]{3, 3, 2, 2});
        map.put("HR", new int[]{1, 0, 0, 2});
        map.put("HT", new int[]{3, 3, 3, 3});
        map.put("HU", new int[]{0, 0, 1, 0});
        map.put("ID", new int[]{2, 3, 3, 4});
        map.put("IE", new int[]{0, 0, 1, 1});
        map.put("IL", new int[]{0, 1, 1, 3});
        map.put("IM", new int[]{0, 1, 0, 1});
        map.put("IN", new int[]{2, 3, 3, 4});
        map.put("IO", new int[]{4, 2, 2, 2});
        map.put("IQ", new int[]{3, 3, 4, 3});
        map.put("IR", new int[]{3, 2, 4, 4});
        map.put("IS", new int[]{0, 0, 0, 0});
        map.put("IT", new int[]{1, 0, 1, 3});
        map.put("JE", new int[]{0, 0, 0, 1});
        map.put("JM", new int[]{3, 3, 3, 2});
        map.put("JO", new int[]{1, 1, 1, 2});
        map.put("JP", new int[]{0, 1, 1, 2});
        map.put("KE", new int[]{3, 3, 3, 3});
        map.put("KG", new int[]{2, 2, 3, 3});
        map.put("KH", new int[]{1, 0, 4, 4});
        map.put("KI", new int[]{4, 4, 4, 4});
        map.put("KM", new int[]{4, 4, 2, 2});
        map.put("KN", new int[]{1, 0, 1, 3});
        map.put("KP", new int[]{1, 2, 2, 2});
        map.put("KR", new int[]{0, 4, 0, 2});
        map.put("KW", new int[]{1, 2, 1, 2});
        map.put("KY", new int[]{1, 1, 0, 2});
        map.put("KZ", new int[]{1, 2, 2, 3});
        map.put("LA", new int[]{3, 2, 2, 2});
        map.put("LB", new int[]{3, 2, 0, 0});
        map.put("LC", new int[]{2, 2, 1, 0});
        map.put("LI", new int[]{0, 0, 1, 2});
        map.put("LK", new int[]{1, 1, 2, 2});
        map.put("LR", new int[]{3, 4, 3, 1});
        map.put("LS", new int[]{3, 3, 2, 0});
        map.put("LT", new int[]{0, 0, 0, 1});
        map.put("LU", new int[]{0, 0, 1, 0});
        map.put("LV", new int[]{0, 0, 0, 0});
        map.put("LY", new int[]{4, 4, 4, 4});
        map.put("MA", new int[]{2, 1, 2, 2});
        map.put("MC", new int[]{1, 0, 1, 0});
        map.put("MD", new int[]{1, 1, 0, 0});
        map.put("ME", new int[]{1, 2, 2, 3});
        map.put("MF", new int[]{1, 4, 3, 3});
        map.put("MG", new int[]{3, 4, 1, 2});
        map.put("MH", new int[]{4, 0, 2, 3});
        map.put("MK", new int[]{1, 0, 0, 1});
        map.put("ML", new int[]{4, 4, 4, 4});
        map.put("MM", new int[]{2, 3, 1, 2});
        map.put("MN", new int[]{2, 2, 2, 4});
        map.put("MO", new int[]{0, 1, 4, 4});
        map.put("MP", new int[]{0, 0, 4, 4});
        map.put("MQ", new int[]{1, 1, 1, 3});
        map.put("MR", new int[]{4, 2, 4, 2});
        map.put("MS", new int[]{1, 2, 1, 2});
        map.put("MT", new int[]{0, 0, 0, 0});
        map.put("MU", new int[]{2, 2, 4, 4});
        map.put("MV", new int[]{4, 2, 0, 1});
        map.put("MW", new int[]{3, 2, 1, 1});
        map.put("MX", new int[]{2, 4, 3, 1});
        map.put("MY", new int[]{2, 3, 3, 3});
        map.put("MZ", new int[]{3, 3, 2, 4});
        map.put("NA", new int[]{4, 2, 1, 1});
        map.put("NC", new int[]{2, 1, 3, 3});
        map.put("NE", new int[]{4, 4, 4, 4});
        map.put("NF", new int[]{0, 2, 2, 2});
        map.put("NG", new int[]{3, 4, 2, 2});
        map.put("NI", new int[]{3, 4, 3, 3});
        map.put("NL", new int[]{0, 1, 3, 2});
        map.put("NO", new int[]{0, 0, 1, 0});
        map.put("NP", new int[]{2, 3, 2, 2});
        map.put("NR", new int[]{4, 3, 4, 1});
        map.put("NU", new int[]{4, 2, 2, 2});
        map.put("NZ", new int[]{0, 0, 0, 1});
        map.put("OM", new int[]{2, 2, 1, 3});
        map.put("PA", new int[]{1, 3, 2, 3});
        map.put("PE", new int[]{2, 2, 4, 4});
        map.put("PF", new int[]{2, 2, 0, 1});
        map.put("PG", new int[]{4, 4, 4, 4});
        map.put("PH", new int[]{3, 0, 4, 4});
        map.put("PK", new int[]{3, 3, 3, 3});
        map.put("PL", new int[]{1, 0, 1, 3});
        map.put("PM", new int[]{0, 2, 2, 3});
        map.put("PR", new int[]{2, 3, 4, 3});
        map.put("PS", new int[]{2, 3, 0, 4});
        map.put("PT", new int[]{1, 1, 1, 1});
        map.put("PW", new int[]{3, 2, 3, 0});
        map.put("PY", new int[]{2, 1, 3, 3});
        map.put("QA", new int[]{2, 3, 1, 2});
        map.put("RE", new int[]{1, 1, 2, 2});
        map.put("RO", new int[]{0, 1, 1, 3});
        map.put("RS", new int[]{1, 1, 0, 0});
        map.put("RU", new int[]{0, 1, 1, 1});
        map.put("RW", new int[]{3, 4, 3, 1});
        map.put("SA", new int[]{3, 2, 2, 3});
        map.put("SB", new int[]{4, 4, 3, 0});
        map.put("SC", new int[]{4, 2, 0, 1});
        map.put("SD", new int[]{3, 4, 4, 4});
        map.put("SE", new int[]{0, 0, 0, 0});
        map.put("SG", new int[]{1, 2, 3, 3});
        map.put("SH", new int[]{4, 2, 2, 2});
        map.put("SI", new int[]{0, 1, 0, 0});
        map.put("SJ", new int[]{3, 2, 0, 2});
        map.put("SK", new int[]{0, 1, 0, 1});
        map.put("SL", new int[]{4, 3, 2, 4});
        map.put("SM", new int[]{1, 0, 1, 1});
        map.put("SN", new int[]{4, 4, 4, 2});
        map.put("SO", new int[]{4, 4, 4, 3});
        map.put("SR", new int[]{3, 2, 2, 3});
        map.put("SS", new int[]{4, 3, 4, 2});
        map.put("ST", new int[]{3, 2, 2, 2});
        map.put("SV", new int[]{2, 3, 2, 3});
        map.put("SX", new int[]{2, 4, 2, 0});
        map.put("SY", new int[]{4, 4, 2, 0});
        map.put("SZ", new int[]{3, 4, 1, 1});
        map.put("TC", new int[]{2, 1, 2, 1});
        map.put("TD", new int[]{4, 4, 4, 3});
        map.put("TG", new int[]{3, 2, 2, 0});
        map.put("TH", new int[]{1, 3, 4, 4});
        map.put("TJ", new int[]{4, 4, 4, 4});
        map.put("TL", new int[]{4, 2, 4, 4});
        map.put("TM", new int[]{4, 1, 3, 3});
        map.put("TN", new int[]{2, 2, 1, 2});
        map.put("TO", new int[]{2, 3, 3, 1});
        map.put("TR", new int[]{1, 2, 0, 2});
        map.put("TT", new int[]{2, 1, 1, 0});
        map.put("TV", new int[]{4, 2, 2, 4});
        map.put("TW", new int[]{0, 0, 0, 1});
        map.put("TZ", new int[]{3, 3, 3, 2});
        map.put("UA", new int[]{0, 2, 1, 3});
        map.put("UG", new int[]{4, 3, 2, 2});
        map.put("US", new int[]{0, 1, 3, 3});
        map.put("UY", new int[]{2, 1, 2, 2});
        map.put("UZ", new int[]{4, 3, 2, 4});
        map.put("VA", new int[]{1, 2, 2, 2});
        map.put("VC", new int[]{2, 0, 3, 2});
        map.put("VE", new int[]{3, 4, 4, 3});
        map.put("VG", new int[]{3, 1, 3, 4});
        map.put("VI", new int[]{1, 0, 2, 4});
        map.put("VN", new int[]{0, 2, 4, 4});
        map.put("VU", new int[]{4, 1, 3, 2});
        map.put("WS", new int[]{3, 2, 3, 0});
        map.put("XK", new int[]{1, 2, 1, 0});
        map.put("YE", new int[]{4, 4, 4, 2});
        map.put("YT", new int[]{3, 1, 1, 2});
        map.put("ZA", new int[]{2, 3, 1, 2});
        map.put("ZM", new int[]{3, 3, 3, 1});
        map.put("ZW", new int[]{3, 3, 2, 1});
        return Collections.unmodifiableMap(map);
    }
}
