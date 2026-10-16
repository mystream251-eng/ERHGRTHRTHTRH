package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
interface EbmlReader {
    void init(EbmlReaderOutput ebmlReaderOutput);

    boolean read(ExtractorInput extractorInput) throws InterruptedException, IOException;

    void reset();
}
