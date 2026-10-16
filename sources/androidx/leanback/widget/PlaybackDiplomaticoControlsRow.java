package androidx.leanback.widget;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackDiplomaticoControlsRow extends PlaybackControlsRow {
    private OnPlaybackStateCallback mListener;

    public static class OnPlaybackStateCallback {
        public void onPause(PlaybackDiplomaticoControlsRow playbackDiplomaticoControlsRow) {
        }

        public void onPlay(PlaybackDiplomaticoControlsRow playbackDiplomaticoControlsRow) {
        }
    }

    public PlaybackDiplomaticoControlsRow(Object obj) {
        super(obj);
    }

    public void play() {
        OnPlaybackStateCallback onPlaybackStateCallback = this.mListener;
        if (onPlaybackStateCallback != null) {
            onPlaybackStateCallback.onPlay(this);
        }
    }

    public void pause() {
        OnPlaybackStateCallback onPlaybackStateCallback = this.mListener;
        if (onPlaybackStateCallback != null) {
            onPlaybackStateCallback.onPause(this);
        }
    }

    public void setOnPlaybackStateChangedListener(OnPlaybackStateCallback onPlaybackStateCallback) {
        this.mListener = onPlaybackStateCallback;
    }
}
