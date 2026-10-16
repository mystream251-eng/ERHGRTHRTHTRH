package androidx.leanback.media;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import androidx.leanback.media.PlayerAdapter;
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.PlaybackControlsRow;
import androidx.leanback.widget.PlaybackRowPresenter;
import androidx.leanback.widget.PlaybackSeekDataProvider;
import androidx.leanback.widget.PlaybackSeekUi;
import androidx.leanback.widget.PlaybackTransportRowPresenter;
import androidx.leanback.widget.RowPresenter;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackTransportControlGlue<T extends PlayerAdapter> extends PlaybackBaseControlGlue<T> {
    static final boolean DEBUG = false;
    static final int MSG_UPDATE_PLAYBACK_STATE = 100;
    static final String TAG = "PlaybackTransportGlue";
    static final int UPDATE_PLAYBACK_STATE_DELAY_MS = 2000;
    static final Handler sHandler = new UpdatePlaybackStateHandler();
    final WeakReference<PlaybackBaseControlGlue> mGlueWeakReference;
    final PlaybackTransportControlGlue<T>.SeekUiClient mPlaybackSeekUiClient;
    boolean mSeekEnabled;
    PlaybackSeekDataProvider mSeekProvider;

    static class UpdatePlaybackStateHandler extends Handler {
        UpdatePlaybackStateHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            PlaybackTransportControlGlue playbackTransportControlGlue;
            if (message.what != 100 || (playbackTransportControlGlue = (PlaybackTransportControlGlue) ((WeakReference) message.obj).get()) == null) {
                return;
            }
            playbackTransportControlGlue.onUpdatePlaybackState();
        }
    }

    public PlaybackTransportControlGlue(Context context, T t) {
        super(context, t);
        this.mGlueWeakReference = new WeakReference<>(this);
        this.mPlaybackSeekUiClient = new SeekUiClient();
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    public void setControlsRow(PlaybackControlsRow playbackControlsRow) {
        super.setControlsRow(playbackControlsRow);
        sHandler.removeMessages(100, this.mGlueWeakReference);
        onUpdatePlaybackState();
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected void onCreatePrimaryActions(ArrayObjectAdapter arrayObjectAdapter) {
        PlaybackControlsRow.PlayPauseAction playPauseAction = new PlaybackControlsRow.PlayPauseAction(getContext());
        this.mPlayPauseAction = playPauseAction;
        arrayObjectAdapter.add(playPauseAction);
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected PlaybackRowPresenter onCreateRowPresenter() {
        AbstractDetailsDescriptionPresenter abstractDetailsDescriptionPresenter = new AbstractDetailsDescriptionPresenter() { // from class: androidx.leanback.media.PlaybackTransportControlGlue.1
            @Override // androidx.leanback.widget.AbstractDetailsDescriptionPresenter
            protected void onBindDescription(AbstractDetailsDescriptionPresenter.ViewHolder viewHolder, Object obj) {
                PlaybackBaseControlGlue playbackBaseControlGlue = (PlaybackBaseControlGlue) obj;
                viewHolder.getTitle().setText(playbackBaseControlGlue.getTitle());
                viewHolder.getSubtitle().setText(playbackBaseControlGlue.getSubtitle());
            }
        };
        PlaybackTransportRowPresenter playbackTransportRowPresenter = new PlaybackTransportRowPresenter() { // from class: androidx.leanback.media.PlaybackTransportControlGlue.2
            @Override // androidx.leanback.widget.PlaybackTransportRowPresenter, androidx.leanback.widget.RowPresenter
            protected void onBindRowViewHolder(RowPresenter.ViewHolder viewHolder, Object obj) {
                super.onBindRowViewHolder(viewHolder, obj);
                viewHolder.setOnKeyListener(PlaybackTransportControlGlue.this);
            }

            @Override // androidx.leanback.widget.PlaybackTransportRowPresenter, androidx.leanback.widget.RowPresenter
            protected void onUnbindRowViewHolder(RowPresenter.ViewHolder viewHolder) {
                super.onUnbindRowViewHolder(viewHolder);
                viewHolder.setOnKeyListener(null);
            }
        };
        playbackTransportRowPresenter.setDescriptionPresenter(abstractDetailsDescriptionPresenter);
        return playbackTransportRowPresenter;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.media.PlaybackGlue
    protected void onAttachedToHost(PlaybackGlueHost playbackGlueHost) {
        super.onAttachedToHost(playbackGlueHost);
        if (playbackGlueHost instanceof PlaybackSeekUi) {
            ((PlaybackSeekUi) playbackGlueHost).setPlaybackSeekUiClient(this.mPlaybackSeekUiClient);
        }
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.media.PlaybackGlue
    protected void onDetachedFromHost() {
        super.onDetachedFromHost();
        if (getHost() instanceof PlaybackSeekUi) {
            ((PlaybackSeekUi) getHost()).setPlaybackSeekUiClient(null);
        }
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected void onUpdateProgress() {
        if (this.mPlaybackSeekUiClient.mIsSeek) {
            return;
        }
        super.onUpdateProgress();
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.widget.OnActionClickedListener
    public void onActionClicked(Action action) {
        dispatchAction(action, null);
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue, android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i != 4 && i != 111) {
            switch (i) {
                default:
                    Action actionForKeyCode = this.mControlsRow.getActionForKeyCode(this.mControlsRow.getPrimaryActionsAdapter(), i);
                    if (actionForKeyCode == null) {
                        actionForKeyCode = this.mControlsRow.getActionForKeyCode(this.mControlsRow.getSecondaryActionsAdapter(), i);
                    }
                    if (actionForKeyCode != null) {
                        if (keyEvent.getAction() != 0) {
                            return true;
                        }
                        dispatchAction(actionForKeyCode, keyEvent);
                        return true;
                    }
                case 19:
                case 20:
                case 21:
                case 22:
                    return false;
            }
        }
        return false;
    }

    void onUpdatePlaybackStatusAfterUserAction() {
        updatePlaybackState(this.mIsPlaying);
        sHandler.removeMessages(100, this.mGlueWeakReference);
        Handler handler = sHandler;
        handler.sendMessageDelayed(handler.obtainMessage(100, this.mGlueWeakReference), AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }

    boolean dispatchAction(Action action, KeyEvent keyEvent) {
        if (action instanceof PlaybackControlsRow.PlayPauseAction) {
            boolean z = keyEvent == null || keyEvent.getKeyCode() == 85 || keyEvent.getKeyCode() == 126;
            if ((keyEvent == null || keyEvent.getKeyCode() == 85 || keyEvent.getKeyCode() == 127) && this.mIsPlaying) {
                this.mIsPlaying = false;
                pause();
            } else if (z && !this.mIsPlaying) {
                this.mIsPlaying = true;
                play();
            }
            onUpdatePlaybackStatusAfterUserAction();
        } else if (action instanceof PlaybackControlsRow.SkipNextAction) {
            next();
        } else {
            if (!(action instanceof PlaybackControlsRow.SkipPreviousAction)) {
                return false;
            }
            previous();
        }
        return true;
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected void onPlayStateChanged() {
        if (sHandler.hasMessages(100, this.mGlueWeakReference)) {
            sHandler.removeMessages(100, this.mGlueWeakReference);
            if (this.mPlayerAdapter.isPlaying() != this.mIsPlaying) {
                Handler handler = sHandler;
                handler.sendMessageDelayed(handler.obtainMessage(100, this.mGlueWeakReference), AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
            } else {
                onUpdatePlaybackState();
            }
        } else {
            onUpdatePlaybackState();
        }
        super.onPlayStateChanged();
    }

    void onUpdatePlaybackState() {
        this.mIsPlaying = this.mPlayerAdapter.isPlaying();
        updatePlaybackState(this.mIsPlaying);
    }

    private void updatePlaybackState(boolean z) {
        if (this.mControlsRow == null) {
            return;
        }
        if (!z) {
            onUpdateProgress();
            this.mPlayerAdapter.setProgressUpdatingEnabled(this.mPlaybackSeekUiClient.mIsSeek);
        } else {
            this.mPlayerAdapter.setProgressUpdatingEnabled(true);
        }
        if (this.mFadeWhenPlaying && getHost() != null) {
            getHost().setControlsOverlayAutoHideEnabled(z);
        }
        if (this.mPlayPauseAction == null || this.mPlayPauseAction.getIndex() == z) {
            return;
        }
        this.mPlayPauseAction.setIndex(z ? 1 : 0);
        notifyItemChanged((ArrayObjectAdapter) getControlsRow().getPrimaryActionsAdapter(), this.mPlayPauseAction);
    }

    class SeekUiClient extends PlaybackSeekUi.Client {
        boolean mIsSeek;
        long mLastUserPosition;
        boolean mPausedBeforeSeek;
        long mPositionBeforeSeek;

        SeekUiClient() {
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public PlaybackSeekDataProvider getPlaybackSeekDataProvider() {
            return PlaybackTransportControlGlue.this.mSeekProvider;
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public boolean isSeekEnabled() {
            return PlaybackTransportControlGlue.this.mSeekProvider != null || PlaybackTransportControlGlue.this.mSeekEnabled;
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public void onSeekStarted() {
            this.mIsSeek = true;
            this.mPausedBeforeSeek = !PlaybackTransportControlGlue.this.isPlaying();
            PlaybackTransportControlGlue.this.mPlayerAdapter.setProgressUpdatingEnabled(true);
            this.mPositionBeforeSeek = PlaybackTransportControlGlue.this.mSeekProvider == null ? PlaybackTransportControlGlue.this.mPlayerAdapter.getCurrentPosition() : -1L;
            this.mLastUserPosition = -1L;
            PlaybackTransportControlGlue.this.pause();
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public void onSeekPositionChanged(long j) {
            if (PlaybackTransportControlGlue.this.mSeekProvider == null) {
                PlaybackTransportControlGlue.this.mPlayerAdapter.seekTo(j);
            } else {
                this.mLastUserPosition = j;
            }
            if (PlaybackTransportControlGlue.this.mControlsRow != null) {
                PlaybackTransportControlGlue.this.mControlsRow.setCurrentPosition(j);
            }
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public void onSeekFinished(boolean z) {
            if (!z) {
                long j = this.mLastUserPosition;
                if (j >= 0) {
                    PlaybackTransportControlGlue.this.seekTo(j);
                }
            } else {
                long j2 = this.mPositionBeforeSeek;
                if (j2 >= 0) {
                    PlaybackTransportControlGlue.this.seekTo(j2);
                }
            }
            this.mIsSeek = false;
            if (!this.mPausedBeforeSeek) {
                PlaybackTransportControlGlue.this.play();
            } else {
                PlaybackTransportControlGlue.this.mPlayerAdapter.setProgressUpdatingEnabled(false);
                PlaybackTransportControlGlue.this.onUpdateProgress();
            }
        }
    }

    public final void setSeekProvider(PlaybackSeekDataProvider playbackSeekDataProvider) {
        this.mSeekProvider = playbackSeekDataProvider;
    }

    public final PlaybackSeekDataProvider getSeekProvider() {
        return this.mSeekProvider;
    }

    public final void setSeekEnabled(boolean z) {
        this.mSeekEnabled = z;
    }

    public final boolean isSeekEnabled() {
        return this.mSeekEnabled;
    }
}
