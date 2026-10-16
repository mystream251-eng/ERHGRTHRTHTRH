package org.tukaani.xz;

/* JADX INFO: loaded from: classes3.dex */
class BCJEncoder extends BCJCoder implements FilterEncoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final long filterID;
    private final BCJOptions options;
    private final byte[] props;

    BCJEncoder(BCJOptions bCJOptions, long j) {
        int startOffset = bCJOptions.getStartOffset();
        if (startOffset == 0) {
            this.props = new byte[0];
        } else {
            this.props = new byte[4];
            for (int i = 0; i < 4; i++) {
                this.props[i] = (byte) (startOffset >>> (i * 8));
            }
        }
        this.filterID = j;
        this.options = (BCJOptions) bCJOptions.clone();
    }

    @Override // org.tukaani.xz.FilterEncoder
    public long getFilterID() {
        return this.filterID;
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
        return false;
    }
}
