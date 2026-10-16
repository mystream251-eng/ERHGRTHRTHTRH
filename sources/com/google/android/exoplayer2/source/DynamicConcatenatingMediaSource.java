package com.google.android.exoplayer2.source;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class DynamicConcatenatingMediaSource extends ConcatenatingMediaSource {
    @Deprecated
    public DynamicConcatenatingMediaSource() {
        super(new MediaSource[0]);
    }

    @Deprecated
    public DynamicConcatenatingMediaSource(boolean z) {
        super(z, new MediaSource[0]);
    }

    @Deprecated
    public DynamicConcatenatingMediaSource(boolean z, ShuffleOrder shuffleOrder) {
        super(z, shuffleOrder, new MediaSource[0]);
    }
}
