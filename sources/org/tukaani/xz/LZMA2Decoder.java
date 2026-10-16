package org.tukaani.xz;

import java.io.InputStream;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
class LZMA2Decoder extends LZMA2Coder implements FilterDecoder {
    private int dictSize;

    LZMA2Decoder(byte[] bArr) throws UnsupportedOptionsException {
        if (bArr.length != 1 || (bArr[0] & UByte.MAX_VALUE) > 37) {
            throw new UnsupportedOptionsException("Unsupported LZMA2 properties");
        }
        int i = (bArr[0] & 1) | 2;
        this.dictSize = i;
        this.dictSize = i << ((bArr[0] >>> 1) + 11);
    }

    @Override // org.tukaani.xz.FilterDecoder
    public InputStream getInputStream(InputStream inputStream, ArrayCache arrayCache) {
        return new LZMA2InputStream(inputStream, this.dictSize, null, arrayCache);
    }

    @Override // org.tukaani.xz.FilterDecoder
    public int getMemoryUsage() {
        return LZMA2InputStream.getMemoryUsage(this.dictSize);
    }
}
