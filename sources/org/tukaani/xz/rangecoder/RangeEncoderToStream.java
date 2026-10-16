package org.tukaani.xz.rangecoder;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes3.dex */
public final class RangeEncoderToStream extends RangeEncoder {
    private final OutputStream out;

    public RangeEncoderToStream(OutputStream outputStream) {
        this.out = outputStream;
        reset();
    }

    @Override // org.tukaani.xz.rangecoder.RangeEncoder
    void writeByte(int i) throws IOException {
        this.out.write(i);
    }
}
