package com.google.android.exoplayer2.video;

import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.util.Assertions;

/* JADX INFO: loaded from: classes2.dex */
public interface VideoRendererEventListener {
    default void onDroppedFrames(int i, long j) {
    }

    default void onRenderedFirstFrame(Surface surface) {
    }

    default void onVideoDecoderInitialized(String str, long j, long j2) {
    }

    default void onVideoDisabled(DecoderCounters decoderCounters) {
    }

    default void onVideoEnabled(DecoderCounters decoderCounters) {
    }

    default void onVideoInputFormatChanged(Format format) {
    }

    default void onVideoSizeChanged(int i, int i2, int i3, float f) {
    }

    public static final class EventDispatcher {
        private final Handler handler;
        private final VideoRendererEventListener listener;

        public EventDispatcher(Handler handler, VideoRendererEventListener videoRendererEventListener) {
            this.handler = videoRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler) : null;
            this.listener = videoRendererEventListener;
        }

        public void enabled(final DecoderCounters decoderCounters) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.-$$Lambda$VideoRendererEventListener$EventDispatcher$Zf6ofdxzBBJ5SL288lE0HglRj8g
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$enabled$0$VideoRendererEventListener$EventDispatcher(decoderCounters);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$enabled$0$VideoRendererEventListener$EventDispatcher(DecoderCounters decoderCounters) {
            this.listener.onVideoEnabled(decoderCounters);
        }

        public void decoderInitialized(final String str, final long j, final long j2) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.-$$Lambda$VideoRendererEventListener$EventDispatcher$Y232CA7hogfrRJjYu2VeUSxg0VQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$decoderInitialized$1$VideoRendererEventListener$EventDispatcher(str, j, j2);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$decoderInitialized$1$VideoRendererEventListener$EventDispatcher(String str, long j, long j2) {
            this.listener.onVideoDecoderInitialized(str, j, j2);
        }

        public void inputFormatChanged(final Format format) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.-$$Lambda$VideoRendererEventListener$EventDispatcher$26y6c6BFFT4OL6bJiMmdsfxDEMQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$inputFormatChanged$2$VideoRendererEventListener$EventDispatcher(format);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$inputFormatChanged$2$VideoRendererEventListener$EventDispatcher(Format format) {
            this.listener.onVideoInputFormatChanged(format);
        }

        public void droppedFrames(final int i, final long j) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.-$$Lambda$VideoRendererEventListener$EventDispatcher$wpJzum9Nim-WREQi3I6t6RZgGzs
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$droppedFrames$3$VideoRendererEventListener$EventDispatcher(i, j);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$droppedFrames$3$VideoRendererEventListener$EventDispatcher(int i, long j) {
            this.listener.onDroppedFrames(i, j);
        }

        public void videoSizeChanged(final int i, final int i2, final int i3, final float f) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.-$$Lambda$VideoRendererEventListener$EventDispatcher$TaBV3X3b5lKElsQ7tczViKAyQ3w
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$videoSizeChanged$4$VideoRendererEventListener$EventDispatcher(i, i2, i3, f);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$videoSizeChanged$4$VideoRendererEventListener$EventDispatcher(int i, int i2, int i3, float f) {
            this.listener.onVideoSizeChanged(i, i2, i3, f);
        }

        public void renderedFirstFrame(final Surface surface) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.-$$Lambda$VideoRendererEventListener$EventDispatcher$SFK5uUI0PHTm3Dg6Wdc1eRaQ9xk
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$renderedFirstFrame$5$VideoRendererEventListener$EventDispatcher(surface);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$renderedFirstFrame$5$VideoRendererEventListener$EventDispatcher(Surface surface) {
            this.listener.onRenderedFirstFrame(surface);
        }

        public void disabled(final DecoderCounters decoderCounters) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.-$$Lambda$VideoRendererEventListener$EventDispatcher$qTQ-0WnG_WelRJ9iR8L0OaiS0Go
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$disabled$6$VideoRendererEventListener$EventDispatcher(decoderCounters);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$disabled$6$VideoRendererEventListener$EventDispatcher(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            this.listener.onVideoDisabled(decoderCounters);
        }
    }
}
