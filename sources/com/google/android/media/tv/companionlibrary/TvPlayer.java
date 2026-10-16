package com.google.android.media.tv.companionlibrary;

import android.media.PlaybackParams;
import android.view.Surface;

/* JADX INFO: loaded from: classes2.dex */
public interface TvPlayer {

    public static abstract class Callback {
        public void onCompleted() {
        }

        public void onError(Exception exc) {
        }

        public void onPaused() {
        }

        public void onResumed() {
        }

        public void onStarted() {
        }
    }

    long getCurrentPosition();

    long getDuration();

    void pause();

    void play();

    void registerCallback(Callback callback);

    void seekTo(long j);

    void setPlaybackParams(PlaybackParams playbackParams);

    void setSurface(Surface surface);

    void setVolume(float f);

    void unregisterCallback(Callback callback);
}
