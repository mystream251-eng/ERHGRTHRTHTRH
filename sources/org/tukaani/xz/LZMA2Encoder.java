package org.tukaani.xz;

import org.tukaani.xz.lzma.LZMAEncoder;

/* JADX INFO: loaded from: classes3.dex */
class LZMA2Encoder extends LZMA2Coder implements FilterEncoder {
    private final LZMA2Options options;
    private final byte[] props = new byte[1];

    LZMA2Encoder(LZMA2Options lZMA2Options) {
        if (lZMA2Options.getPresetDict() != null) {
            throw new IllegalArgumentException("XZ doesn't support a preset dictionary for now");
        }
        if (lZMA2Options.getMode() == 0) {
            this.props[0] = 0;
        } else {
            this.props[0] = (byte) (LZMAEncoder.getDistSlot(Math.max(lZMA2Options.getDictSize(), 4096) - 1) - 23);
        }
        this.options = (LZMA2Options) lZMA2Options.clone();
    }

    @Override // org.tukaani.xz.FilterEncoder
    public long getFilterID() {
        return 33L;
    }

    @Override // org.tukaani.xz.FilterEncoder
    public byte[] getFilterProps() {
        return this.props;
    }

    @Override // org.tukaani.xz.FilterEncoder
    public FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream, ArrayCache arrayCache) {
        return this.options.getOutputStream(finishableOutputStream, arrayCache);
    }

    @Override // org.tukaani.xz.FilterEncoder
    public boolean supportsFlushing() {
        return true;
    }
}
