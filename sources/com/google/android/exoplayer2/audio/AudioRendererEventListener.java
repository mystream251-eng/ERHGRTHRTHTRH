package com.google.android.exoplayer2.audio;

import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.util.Assertions;

/* JADX INFO: loaded from: classes.dex */
public interface AudioRendererEventListener {
    default void onAudioDecoderInitialized(String str, long j, long j2) {
    }

    default void onAudioDisabled(DecoderCounters decoderCounters) {
    }

    default void onAudioEnabled(DecoderCounters decoderCounters) {
    }

    default void onAudioInputFormatChanged(Format format) {
    }

    default void onAudioSessionId(int i) {
    }

    default void onAudioSinkUnderrun(int i, long j, long j2) {
    }

    public static final class EventDispatcher {
        private final Handler handler;
        private final AudioRendererEventListener listener;

        public EventDispatcher(Handler handler, AudioRendererEventListener audioRendererEventListener) {
            this.handler = audioRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler) : null;
            this.listener = audioRendererEventListener;
        }

        public void enabled(final DecoderCounters decoderCounters) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.-$$Lambda$AudioRendererEventListener$EventDispatcher$MUMUaHcEfIpwDLi9gxmScOQxifc
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$enabled$0$AudioRendererEventListener$EventDispatcher(decoderCounters);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$enabled$0$AudioRendererEventListener$EventDispatcher(DecoderCounters decoderCounters) {
            this.listener.onAudioEnabled(decoderCounters);
        }

        public void decoderInitialized(final String str, final long j, final long j2) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.-$$Lambda$AudioRendererEventListener$EventDispatcher$F29t8_xYSK7h_6CpLRlp2y2yb1E
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$decoderInitialized$1$AudioRendererEventListener$EventDispatcher(str, j, j2);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$decoderInitialized$1$AudioRendererEventListener$EventDispatcher(String str, long j, long j2) {
            this.listener.onAudioDecoderInitialized(str, j, j2);
        }

        public void inputFormatChanged(final Format format) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.-$$Lambda$AudioRendererEventListener$EventDispatcher$D7KvJbrpXrnWw4qzd_LI9ZtQytw
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$inputFormatChanged$2$AudioRendererEventListener$EventDispatcher(format);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$inputFormatChanged$2$AudioRendererEventListener$EventDispatcher(Format format) {
            this.listener.onAudioInputFormatChanged(format);
        }

        public void audioTrackUnderrun(final int i, final long j, final long j2) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.-$$Lambda$AudioRendererEventListener$EventDispatcher$oPQKly422CpX1mqIU2N6d76OGxk
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$audioTrackUnderrun$3$AudioRendererEventListener$EventDispatcher(i, j, j2);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$audioTrackUnderrun$3$AudioRendererEventListener$EventDispatcher(int i, long j, long j2) {
            this.listener.onAudioSinkUnderrun(i, j, j2);
        }

        public void disabled(final DecoderCounters decoderCounters) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.-$$Lambda$AudioRendererEventListener$EventDispatcher$jb22FSnmUl2pGG0LguQS_Wd-LWk
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$disabled$4$AudioRendererEventListener$EventDispatcher(decoderCounters);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$disabled$4$AudioRendererEventListener$EventDispatcher(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            this.listener.onAudioDisabled(decoderCounters);
        }

        public void audioSessionId(final int i) {
            if (this.listener != null) {
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.-$$Lambda$AudioRendererEventListener$EventDispatcher$a1B1YBHhPRCtc1MQAc2fSVEo22I
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$audioSessionId$5$AudioRendererEventListener$EventDispatcher(i);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$audioSessionId$5$AudioRendererEventListener$EventDispatcher(int i) {
            this.listener.onAudioSessionId(i);
        }
    }
}
