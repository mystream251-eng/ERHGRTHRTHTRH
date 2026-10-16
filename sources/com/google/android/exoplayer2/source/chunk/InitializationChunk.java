package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class InitializationChunk extends Chunk {
    private static final PositionHolder DUMMY_POSITION_HOLDER = new PositionHolder();
    private final ChunkExtractorWrapper extractorWrapper;
    private volatile boolean loadCanceled;
    private long nextLoadPosition;

    public InitializationChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, ChunkExtractorWrapper chunkExtractorWrapper) {
        super(dataSource, dataSpec, 2, format, i, obj, C.TIME_UNSET, C.TIME_UNSET);
        this.extractorWrapper = chunkExtractorWrapper;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void load() throws InterruptedException, IOException {
        DataSpec dataSpecSubrange = this.dataSpec.subrange(this.nextLoadPosition);
        try {
            DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(this.dataSource, dataSpecSubrange.absoluteStreamPosition, this.dataSource.open(dataSpecSubrange));
            if (this.nextLoadPosition == 0) {
                this.extractorWrapper.init(null, C.TIME_UNSET, C.TIME_UNSET);
            }
            try {
                Extractor extractor = this.extractorWrapper.extractor;
                int i = 0;
                while (i == 0 && !this.loadCanceled) {
                    i = extractor.read(defaultExtractorInput, DUMMY_POSITION_HOLDER);
                }
                Assertions.checkState(i != 1);
                this.nextLoadPosition = defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition;
                Util.closeQuietly(this.dataSource);
            } catch (Throwable th) {
                this.nextLoadPosition = defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition;
                throw th;
            }
        } catch (Throwable th2) {
            Util.closeQuietly(this.dataSource);
            throw th2;
        }
    }
}
