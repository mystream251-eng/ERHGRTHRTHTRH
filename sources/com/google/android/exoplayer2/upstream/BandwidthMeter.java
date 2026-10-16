package com.google.android.exoplayer2.upstream;

import android.os.Handler;

/* JADX INFO: loaded from: classes2.dex */
public interface BandwidthMeter {

    public interface EventListener {
        void onBandwidthSample(int i, long j, long j2);
    }

    void addEventListener(Handler handler, EventListener eventListener);

    long getBitrateEstimate();

    TransferListener getTransferListener();

    void removeEventListener(EventListener eventListener);
}
