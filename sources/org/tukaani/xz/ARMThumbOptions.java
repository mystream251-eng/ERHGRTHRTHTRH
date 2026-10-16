package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.ARMThumb;

/* JADX INFO: loaded from: classes3.dex */
public class ARMThumbOptions extends BCJOptions {
    private static final int ALIGNMENT = 2;

    public ARMThumbOptions() {
        super(2);
    }

    @Override // org.tukaani.xz.BCJOptions
    public /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    @Override // org.tukaani.xz.BCJOptions, org.tukaani.xz.FilterOptions
    public /* bridge */ /* synthetic */ int getDecoderMemoryUsage() {
        return super.getDecoderMemoryUsage();
    }

    @Override // org.tukaani.xz.BCJOptions, org.tukaani.xz.FilterOptions
    public /* bridge */ /* synthetic */ int getEncoderMemoryUsage() {
        return super.getEncoderMemoryUsage();
    }

    @Override // org.tukaani.xz.FilterOptions
    FilterEncoder getFilterEncoder() {
        return new BCJEncoder(this, 8L);
    }

    @Override // org.tukaani.xz.FilterOptions
    public InputStream getInputStream(InputStream inputStream, ArrayCache arrayCache) {
        return new SimpleInputStream(inputStream, new ARMThumb(false, this.startOffset));
    }

    @Override // org.tukaani.xz.FilterOptions
    public FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream, ArrayCache arrayCache) {
        return new SimpleOutputStream(finishableOutputStream, new ARMThumb(true, this.startOffset));
    }

    @Override // org.tukaani.xz.BCJOptions
    public /* bridge */ /* synthetic */ int getStartOffset() {
        return super.getStartOffset();
    }

    @Override // org.tukaani.xz.BCJOptions
    public /* bridge */ /* synthetic */ void setStartOffset(int i) throws UnsupportedOptionsException {
        super.setStartOffset(i);
    }
}
