package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FilterOptions implements Cloneable {
    FilterOptions() {
    }

    public static int getDecoderMemoryUsage(FilterOptions[] filterOptionsArr) {
        int decoderMemoryUsage = 0;
        for (FilterOptions filterOptions : filterOptionsArr) {
            decoderMemoryUsage += filterOptions.getDecoderMemoryUsage();
        }
        return decoderMemoryUsage;
    }

    public static int getEncoderMemoryUsage(FilterOptions[] filterOptionsArr) {
        int encoderMemoryUsage = 0;
        for (FilterOptions filterOptions : filterOptionsArr) {
            encoderMemoryUsage += filterOptions.getEncoderMemoryUsage();
        }
        return encoderMemoryUsage;
    }

    public abstract int getDecoderMemoryUsage();

    public abstract int getEncoderMemoryUsage();

    abstract FilterEncoder getFilterEncoder();

    public InputStream getInputStream(InputStream inputStream) throws IOException {
        return getInputStream(inputStream, ArrayCache.getDefaultCache());
    }

    public abstract InputStream getInputStream(InputStream inputStream, ArrayCache arrayCache) throws IOException;

    public FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream) {
        return getOutputStream(finishableOutputStream, ArrayCache.getDefaultCache());
    }

    public abstract FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream, ArrayCache arrayCache);
}
