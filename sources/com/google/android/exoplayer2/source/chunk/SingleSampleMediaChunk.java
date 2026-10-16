package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class SingleSampleMediaChunk extends BaseMediaChunk {
    private boolean loadCompleted;
    private long nextLoadPosition;
    private final Format sampleFormat;
    private final int trackType;

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void cancelLoad() {
    }

    public SingleSampleMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, long j, long j2, long j3, int i2, Format format2) {
        super(dataSource, dataSpec, format, i, obj, j, j2, C.TIME_UNSET, C.TIME_UNSET, j3);
        this.trackType = i2;
        this.sampleFormat = format2;
    }

    @Override // com.google.android.exoplayer2.source.chunk.MediaChunk
    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void load() throws InterruptedException, IOException {
        try {
            long jOpen = this.dataSource.open(this.dataSpec.subrange(this.nextLoadPosition));
            if (jOpen != -1) {
                jOpen += this.nextLoadPosition;
            }
            DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(this.dataSource, this.nextLoadPosition, jOpen);
            BaseMediaChunkOutput output = getOutput();
            output.setSampleOffsetUs(0L);
            TrackOutput trackOutputTrack = output.track(0, this.trackType);
            trackOutputTrack.format(this.sampleFormat);
            for (int iSampleData = 0; iSampleData != -1; iSampleData = trackOutputTrack.sampleData(defaultExtractorInput, Integer.MAX_VALUE, true)) {
                this.nextLoadPosition += (long) iSampleData;
            }
            trackOutputTrack.sampleMetadata(this.startTimeUs, 1, (int) this.nextLoadPosition, 0, null);
            Util.closeQuietly(this.dataSource);
            this.loadCompleted = true;
        } catch (Throwable th) {
            Util.closeQuietly(this.dataSource);
            throw th;
        }
    }
}
