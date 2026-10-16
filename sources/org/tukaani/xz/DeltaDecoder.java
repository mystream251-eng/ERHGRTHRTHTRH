package org.tukaani.xz;

import java.io.InputStream;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
class DeltaDecoder extends DeltaCoder implements FilterDecoder {
    private final int distance;

    DeltaDecoder(byte[] bArr) throws UnsupportedOptionsException {
        if (bArr.length != 1) {
            throw new UnsupportedOptionsException("Unsupported Delta filter properties");
        }
        this.distance = (bArr[0] & UByte.MAX_VALUE) + 1;
    }

    @Override // org.tukaani.xz.FilterDecoder
    public InputStream getInputStream(InputStream inputStream, ArrayCache arrayCache) {
        return new DeltaInputStream(inputStream, this.distance);
    }

    @Override // org.tukaani.xz.FilterDecoder
    public int getMemoryUsage() {
        return 1;
    }
}
