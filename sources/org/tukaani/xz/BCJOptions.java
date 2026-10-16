package org.tukaani.xz;

/* JADX INFO: loaded from: classes3.dex */
abstract class BCJOptions extends FilterOptions {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final int alignment;
    int startOffset = 0;

    BCJOptions(int i) {
        this.alignment = i;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new RuntimeException();
        }
    }

    @Override // org.tukaani.xz.FilterOptions
    public int getDecoderMemoryUsage() {
        return SimpleInputStream.getMemoryUsage();
    }

    @Override // org.tukaani.xz.FilterOptions
    public int getEncoderMemoryUsage() {
        return SimpleOutputStream.getMemoryUsage();
    }

    public int getStartOffset() {
        return this.startOffset;
    }

    public void setStartOffset(int i) throws UnsupportedOptionsException {
        if (((this.alignment - 1) & i) == 0) {
            this.startOffset = i;
            return;
        }
        throw new UnsupportedOptionsException("Start offset must be a multiple of " + this.alignment);
    }
}
