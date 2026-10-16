package com.nytimes.android.external.cache;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Ticker {
    private static final Ticker SYSTEM_TICKER = new Ticker() { // from class: com.nytimes.android.external.cache.Ticker.1
        @Override // com.nytimes.android.external.cache.Ticker
        public long read() {
            return Platform.systemNanoTime();
        }
    };

    public abstract long read();

    protected Ticker() {
    }

    @Nonnull
    public static Ticker systemTicker() {
        return SYSTEM_TICKER;
    }
}
