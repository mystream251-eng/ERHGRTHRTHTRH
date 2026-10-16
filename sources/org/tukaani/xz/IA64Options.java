package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.IA64;

/* JADX INFO: loaded from: classes3.dex */
public class IA64Options extends BCJOptions {
    private static final int ALIGNMENT = 16;

    public IA64Options() {
        super(16);
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
        return new BCJEncoder(this, 6L);
    }

    @Override // org.tukaani.xz.FilterOptions
    public InputStream getInputStream(InputStream inputStream, ArrayCache arrayCache) {
        return new SimpleInputStream(inputStream, new IA64(false, this.startOffset));
    }

    @Override // org.tukaani.xz.FilterOptions
    public FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream, ArrayCache arrayCache) {
        return new SimpleOutputStream(finishableOutputStream, new IA64(true, this.startOffset));
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
