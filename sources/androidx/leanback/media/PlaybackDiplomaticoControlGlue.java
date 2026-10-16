package androidx.leanback.media;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import androidx.leanback.media.PlayerAdapter;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.PlaybackControlsRow;
import androidx.leanback.widget.PlaybackDiplomaticoControlsRow;
import androidx.leanback.widget.PlaybackDiplomaticoRowPresenter;
import androidx.leanback.widget.PlaybackRowPresenter;
import androidx.leanback.widget.PlaybackSeekDataProvider;
import androidx.leanback.widget.PlaybackSeekUi;
import androidx.leanback.widget.RowPresenter;
import com.dotscreen.farkit.analytics.AnalyticsManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackDiplomaticoControlGlue<T extends PlayerAdapter> extends PlaybackBaseControlGlue<T> {
    static final boolean DEBUG = false;
    static final int MSG_UPDATE_PLAYBACK_STATE = 100;
    static final String TAG = "PlaybackTransportGlue";
    static final int UPDATE_PLAYBACK_STATE_DELAY_MS = 2000;
    static final Handler sHandler = new UpdatePlaybackStateHandler();
    private long epg_duration;
    public boolean isLoading;
    private CharSequence mBody;
    final WeakReference<PlaybackBaseControlGlue> mGlueWeakReference;
    final SeekUiClient mPlaybackSeekUiClient;
    private boolean mPreviouslyPaused;
    boolean mSeekEnabled;
    PlaybackSeekDataProvider mSeekProvider;

    static class UpdatePlaybackStateHandler extends Handler {
        UpdatePlaybackStateHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            PlaybackDiplomaticoControlGlue playbackDiplomaticoControlGlue;
            if (message.what != 100 || (playbackDiplomaticoControlGlue = (PlaybackDiplomaticoControlGlue) ((WeakReference) message.obj).get()) == null) {
                return;
            }
            playbackDiplomaticoControlGlue.onUpdatePlaybackState();
        }
    }

    public PlaybackDiplomaticoControlGlue(Context context, T t) {
        super(context, t);
        this.mGlueWeakReference = new WeakReference<>(this);
        this.isLoading = false;
        this.mPlaybackSeekUiClient = new SeekUiClient(getContext());
        this.mPreviouslyPaused = false;
        this.epg_duration = 0L;
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    void onCreateDefaultControlsRow() {
        if (this.mControlsRow == null) {
            setControlsRow(new PlaybackDiplomaticoControlsRow(this));
        }
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
        return new PlaybackDiplomaticoRowPresenter() { // from class: androidx.leanback.media.PlaybackDiplomaticoControlGlue.1
            @Override // androidx.leanback.widget.PlaybackDiplomaticoRowPresenter, androidx.leanback.widget.RowPresenter
            protected void onBindRowViewHolder(RowPresenter.ViewHolder viewHolder, Object obj) {
                super.onBindRowViewHolder(viewHolder, obj);
                PlaybackDiplomaticoRowPresenter.ViewHolder viewHolder2 = (PlaybackDiplomaticoRowPresenter.ViewHolder) viewHolder;
                viewHolder2.getMTitleTextView().setText(PlaybackDiplomaticoControlGlue.this.getTitle());
                viewHolder2.getMSubTitleTextView().setText(PlaybackDiplomaticoControlGlue.this.getSubtitle());
                viewHolder2.getMBodyTextView().setText(PlaybackDiplomaticoControlGlue.this.getBody());
                if (PlaybackDiplomaticoControlGlue.this.getEPGDuration() > 0) {
                    viewHolder2.setForcedTotalTime(PlaybackDiplomaticoControlGlue.this.getEPGDuration());
                }
                viewHolder.setOnKeyListener(PlaybackDiplomaticoControlGlue.this);
            }

            @Override // androidx.leanback.widget.PlaybackDiplomaticoRowPresenter, androidx.leanback.widget.RowPresenter
            protected void onUnbindRowViewHolder(RowPresenter.ViewHolder viewHolder) {
                super.onUnbindRowViewHolder(viewHolder);
                viewHolder.setOnKeyListener(null);
            }
        };
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
        if (i != 4 && i != 111 && i != 89 && i != 90) {
            switch (i) {
                default:
                    try {
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
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
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
            boolean z2 = keyEvent == null || keyEvent.getKeyCode() == 85 || keyEvent.getKeyCode() == 127;
            if (this.mPlayerAdapter.isPrepared() && !this.isLoading) {
                if (z2 && this.mIsPlaying) {
                    this.mIsPlaying = false;
                    pause();
                } else if (z && !this.mIsPlaying) {
                    this.mIsPlaying = true;
                    play();
                }
                onUpdatePlaybackStatusAfterUserAction();
            } else {
                Toast.makeText(getContext(), R.string.media_content_is_loading, 0).show();
            }
        } else if (action instanceof PlaybackControlsRow.SkipNextAction) {
            next();
        } else if (action instanceof PlaybackControlsRow.SkipPreviousAction) {
            previous();
        } else {
            if (!(action instanceof PlaybackControlsRow.FastForwardAction) && !(action instanceof PlaybackControlsRow.RewindAction)) {
                return false;
            }
            onActionClicked(action);
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
        AnalyticsManager analyticsManager;
        boolean mIsSeek;
        long mLastUserPosition;
        boolean mPausedBeforeSeek;
        long mPositionBeforeSeek;

        public SeekUiClient(Context context) {
            this.analyticsManager = ((PortalApplication) context.getApplicationContext()).getAnalyticsManager();
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public PlaybackSeekDataProvider getPlaybackSeekDataProvider() {
            return PlaybackDiplomaticoControlGlue.this.mSeekProvider;
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public boolean isSeekEnabled() {
            return PlaybackDiplomaticoControlGlue.this.mSeekProvider != null || PlaybackDiplomaticoControlGlue.this.mSeekEnabled;
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public void onSeekStarted() {
            this.mIsSeek = true;
            this.mPausedBeforeSeek = !PlaybackDiplomaticoControlGlue.this.isPlaying();
            PlaybackDiplomaticoControlGlue.this.mPlayerAdapter.setProgressUpdatingEnabled(true);
            long currentPosition = PlaybackDiplomaticoControlGlue.this.mSeekProvider == null ? PlaybackDiplomaticoControlGlue.this.mPlayerAdapter.getCurrentPosition() : -1L;
            this.mPositionBeforeSeek = currentPosition;
            this.mLastUserPosition = -1L;
            this.analyticsManager.onSeekStarted((int) currentPosition);
            PlaybackDiplomaticoControlGlue.this.pause();
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public void onSeekPositionChanged(long j) {
            if (PlaybackDiplomaticoControlGlue.this.mSeekProvider == null) {
                PlaybackDiplomaticoControlGlue.this.mPlayerAdapter.seekTo(j);
            } else {
                this.mLastUserPosition = j;
            }
            if (PlaybackDiplomaticoControlGlue.this.mControlsRow != null) {
                PlaybackDiplomaticoControlGlue.this.mControlsRow.setCurrentPosition(j);
            }
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi.Client
        public void onSeekFinished(boolean z) {
            this.analyticsManager.onSeekEnded((int) PlaybackDiplomaticoControlGlue.this.mPlayerAdapter.getCurrentPosition());
            if (!z) {
                long j = this.mLastUserPosition;
                if (j >= 0) {
                    PlaybackDiplomaticoControlGlue.this.seekTo(j);
                }
            } else {
                long j2 = this.mPositionBeforeSeek;
                if (j2 >= 0) {
                    PlaybackDiplomaticoControlGlue.this.seekTo(j2);
                }
            }
            this.mIsSeek = false;
            if (!this.mPausedBeforeSeek) {
                PlaybackDiplomaticoControlGlue.this.play();
            } else {
                PlaybackDiplomaticoControlGlue.this.mPlayerAdapter.setProgressUpdatingEnabled(false);
                PlaybackDiplomaticoControlGlue.this.onUpdateProgress();
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

    @Override // androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.media.PlaybackGlue
    public void play() {
        super.play();
        ((PlaybackDiplomaticoControlsRow) this.mControlsRow).play();
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.media.PlaybackGlue
    public void pause() {
        super.pause();
        ((PlaybackDiplomaticoControlsRow) this.mControlsRow).pause();
    }

    public void setBody(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.mBody)) {
            return;
        }
        this.mBody = charSequence;
        if (getHost() != null) {
            getHost().notifyPlaybackRowChanged();
        }
    }

    public CharSequence getBody() {
        return this.mBody;
    }

    public void setEPGDuration(long j) {
        if (j == this.epg_duration || j <= 0) {
            return;
        }
        this.epg_duration = j;
        if (getHost() != null) {
            getHost().notifyPlaybackRowChanged();
        }
    }

    public long getEPGDuration() {
        return this.epg_duration;
    }
}
