package com.dotscreen.farkit.video;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import com.dotscreen.farkit.video.impl.NativeMediaPlayer;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class VideoView extends FrameLayout implements MediaController.MediaPlayerControl {
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    private String TAG;
    private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener;
    private boolean mCanPause;
    private boolean mCanSeekBack;
    private boolean mCanSeekForward;
    private MediaPlayer.OnCompletionListener mCompletionListener;
    private int mCurrentBufferPercentage;
    private int mCurrentState;
    private String mCustomData;
    private int mDuration;
    private MediaPlayer.OnErrorListener mErrorListener;
    private Map<String, String> mHeaders;
    private Uri mLicenceUri;
    private MediaController mMediaController;
    private MediaPlayer mMediaPlayer;
    private MediaPlayerFactory mMediaPlayerFactory;
    private MediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    private MediaPlayer.OnErrorListener mOnErrorListener;
    private MediaPlayer.OnPreparedListener mOnPreparedListener;
    MediaPlayer.OnPreparedListener mPreparedListener;
    SurfaceHolder.Callback mSHCallback;
    private Integer mSeekWhenPrepared;
    MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener;
    private int mSurfaceHeight;
    private SurfaceHolder mSurfaceHolder;
    private ISurfaceView mSurfaceView;
    private ISurfaceViewFactory mSurfaceViewFactory;
    private int mSurfaceWidth;
    private int mTargetState;
    private Uri mUri;
    private int mVideoHeight;
    private int mVideoWidth;

    public interface ISurfaceViewFactory {
        ISurfaceView createISurfaceView(Context context);
    }

    public interface MediaPlayerFactory {
        MediaPlayer createPlayer();
    }

    @Override // com.dotscreen.farkit.video.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    public void setMediaPlayerFactory(MediaPlayerFactory mediaPlayerFactory) {
        this.mMediaPlayerFactory = mediaPlayerFactory;
    }

    public void setSurfaceViewFactory(ISurfaceViewFactory iSurfaceViewFactory) {
        this.mSurfaceViewFactory = iSurfaceViewFactory;
    }

    public VideoView(Context context) {
        this(context, null);
    }

    public VideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = VideoView.class.getSimpleName();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceView = null;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mSeekWhenPrepared = null;
        this.mMediaPlayerFactory = new MediaPlayerFactory() { // from class: com.dotscreen.farkit.video.VideoView.1
            @Override // com.dotscreen.farkit.video.VideoView.MediaPlayerFactory
            public MediaPlayer createPlayer() {
                return new NativeMediaPlayer(VideoView.this.getContext());
            }
        };
        this.mSurfaceViewFactory = new ISurfaceViewFactory() { // from class: com.dotscreen.farkit.video.VideoView.2
            @Override // com.dotscreen.farkit.video.VideoView.ISurfaceViewFactory
            public ISurfaceView createISurfaceView(final Context context2) {
                return new ISurfaceView() { // from class: com.dotscreen.farkit.video.VideoView.2.1
                    VideoSurfaceView view;

                    {
                        this.view = VideoView.this.new VideoSurfaceView(context2);
                    }

                    @Override // com.dotscreen.farkit.video.ISurfaceView
                    public View getView() {
                        return this.view;
                    }

                    @Override // com.dotscreen.farkit.video.ISurfaceView
                    public void addSurfaceHolderCallback(SurfaceHolder.Callback callback) {
                        this.view.getHolder().addCallback(callback);
                    }

                    @Override // com.dotscreen.farkit.video.ISurfaceView
                    public void setSurfaceHolderFixedSize(int i2, int i3) {
                        this.view.getHolder().setFixedSize(i2, i3);
                    }

                    @Override // com.dotscreen.farkit.video.ISurfaceView
                    public void setSurfaceHolderKeepScreenOn(boolean z) {
                        this.view.getHolder().setKeepScreenOn(z);
                    }
                };
            }
        };
        this.mSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.dotscreen.farkit.video.VideoView.3
            @Override // com.dotscreen.farkit.video.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                VideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                VideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (VideoView.this.mVideoWidth == 0 || VideoView.this.mVideoHeight == 0) {
                    return;
                }
                VideoView.this.mSurfaceView.setSurfaceHolderFixedSize(VideoView.this.mVideoWidth, VideoView.this.mVideoHeight);
            }
        };
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.dotscreen.farkit.video.VideoView.4
            @Override // com.dotscreen.farkit.video.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                VideoView.this.mCurrentState = 2;
                VideoView videoView = VideoView.this;
                videoView.mCanPause = videoView.mCanSeekBack = videoView.mCanSeekForward = true;
                if (VideoView.this.mOnPreparedListener != null) {
                    VideoView.this.mOnPreparedListener.onPrepared(VideoView.this.mMediaPlayer);
                }
                if (VideoView.this.mMediaController != null) {
                    VideoView.this.mMediaController.setEnabled(true);
                }
                VideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                VideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (VideoView.this.mSeekWhenPrepared != null) {
                    VideoView videoView2 = VideoView.this;
                    videoView2.seekTo(videoView2.mSeekWhenPrepared.intValue());
                }
                if (VideoView.this.mVideoWidth == 0 || VideoView.this.mVideoHeight == 0) {
                    if (VideoView.this.mTargetState != 3) {
                        if (VideoView.this.mTargetState == 4) {
                            VideoView.this.start();
                            VideoView.this.pause();
                            return;
                        }
                        return;
                    }
                    VideoView.this.start();
                    return;
                }
                VideoView.this.mSurfaceView.setSurfaceHolderFixedSize(VideoView.this.mVideoWidth, VideoView.this.mVideoHeight);
                if (VideoView.this.mSurfaceWidth == VideoView.this.mVideoWidth && VideoView.this.mSurfaceHeight == VideoView.this.mVideoHeight) {
                    if (VideoView.this.mTargetState != 3) {
                        if (VideoView.this.mTargetState == 4) {
                            VideoView.this.start();
                            VideoView.this.pause();
                            if (VideoView.this.mMediaController != null) {
                                VideoView.this.mMediaController.show(0);
                                return;
                            }
                            return;
                        }
                        if (VideoView.this.isPlaying()) {
                            return;
                        }
                        if ((VideoView.this.mSeekWhenPrepared.intValue() != 0 || VideoView.this.getCurrentPosition() > 0) && VideoView.this.mMediaController != null) {
                            VideoView.this.mMediaController.show(0);
                            return;
                        }
                        return;
                    }
                    VideoView.this.start();
                    if (VideoView.this.mMediaController != null) {
                        VideoView.this.mMediaController.show();
                    }
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.dotscreen.farkit.video.VideoView.5
            @Override // com.dotscreen.farkit.video.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                VideoView.this.mCurrentState = 5;
                VideoView.this.mTargetState = 5;
                if (VideoView.this.mMediaController != null) {
                    VideoView.this.mMediaController.hide();
                }
                if (VideoView.this.mOnCompletionListener != null) {
                    VideoView.this.mOnCompletionListener.onCompletion(VideoView.this.mMediaPlayer);
                }
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.dotscreen.farkit.video.VideoView.6
            @Override // com.dotscreen.farkit.video.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                Log.d(VideoView.this.TAG, "Error: " + i2 + "," + i3);
                VideoView.this.mCurrentState = -1;
                VideoView.this.mTargetState = -1;
                if (VideoView.this.mMediaController != null) {
                    VideoView.this.mMediaController.hide();
                }
                if ((VideoView.this.mOnErrorListener == null || !VideoView.this.mOnErrorListener.onError(VideoView.this.mMediaPlayer, i2, i3)) && VideoView.this.getWindowToken() != null) {
                    new AlertDialog.Builder(VideoView.this.getContext()).setMessage("Can't play this video.").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.dotscreen.farkit.video.VideoView.6.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i4) {
                            if (VideoView.this.mOnCompletionListener != null) {
                                VideoView.this.mOnCompletionListener.onCompletion(VideoView.this.mMediaPlayer);
                            }
                        }
                    }).setCancelable(true).show();
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.dotscreen.farkit.video.VideoView.7
            @Override // com.dotscreen.farkit.video.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
                VideoView.this.mCurrentBufferPercentage = i2;
                if (VideoView.this.mOnBufferingUpdateListener != null) {
                    VideoView.this.mOnBufferingUpdateListener.onBufferingUpdate(mediaPlayer, i2);
                }
            }
        };
        this.mSHCallback = new SurfaceHolder.Callback() { // from class: com.dotscreen.farkit.video.VideoView.8
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
                VideoView.this.mSurfaceWidth = i3;
                VideoView.this.mSurfaceHeight = i4;
                boolean z = VideoView.this.mVideoWidth == i3 && VideoView.this.mVideoHeight == i4;
                if (VideoView.this.mMediaPlayer == null || !z) {
                    return;
                }
                if (VideoView.this.mSeekWhenPrepared != null) {
                    VideoView videoView = VideoView.this;
                    videoView.seekTo(videoView.mSeekWhenPrepared.intValue());
                }
                if (VideoView.this.mTargetState != 3) {
                    if (VideoView.this.mTargetState == 4) {
                        VideoView.this.start();
                        VideoView.this.pause();
                        return;
                    }
                    return;
                }
                VideoView.this.start();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                VideoView.this.mSurfaceHolder = surfaceHolder;
                VideoView.this.openVideo();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                VideoView.this.mSurfaceHolder = null;
                if (VideoView.this.mMediaController != null) {
                    VideoView.this.mMediaController.destroy();
                }
                VideoView.this.release(true);
            }
        };
        initVideoView();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(VideoView.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(VideoView.class.getName());
    }

    public int resolveAdjustedSize(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE) {
            return mode != 1073741824 ? i : size;
        }
        return Math.min(i, size);
    }

    private void initVideoView() {
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mCurrentState = 0;
        this.mTargetState = 0;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoPath(String str, String str2, String str3) {
        setVideoURI(Uri.parse(str), Uri.parse(str2), str3);
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null, null, null);
    }

    public void setVideoURI(Uri uri, Uri uri2, String str) {
        setVideoURI(uri, uri2, str, null);
    }

    public void setVideoURI(Uri uri, Uri uri2, String str, Map<String, String> map) {
        this.mUri = uri;
        this.mLicenceUri = uri2;
        this.mCustomData = str;
        this.mHeaders = map;
        this.mSeekWhenPrepared = null;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void stopPlayback() {
        release(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openVideo() {
        if (this.mSurfaceView == null) {
            this.mSurfaceView = this.mSurfaceViewFactory.createISurfaceView(getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            addView(this.mSurfaceView.getView(), 0, layoutParams);
            this.mSurfaceView.addSurfaceHolderCallback(this.mSHCallback);
        }
        if (this.mUri == null || this.mSurfaceHolder == null) {
            return;
        }
        Intent intent = new Intent("com.android.music.musicservicecommand");
        intent.putExtra("command", ToolboxAnalyticsModuleKt.EVENT_PAUSE);
        getContext().sendBroadcast(intent);
        Uri uri = this.mUri;
        release(false);
        this.mUri = uri;
        try {
            MediaPlayer mediaPlayerCreatePlayer = this.mMediaPlayerFactory.createPlayer();
            this.mMediaPlayer = mediaPlayerCreatePlayer;
            mediaPlayerCreatePlayer.setOnPreparedListener(this.mPreparedListener);
            this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
            this.mDuration = -1;
            this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
            this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
            this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
            this.mCurrentBufferPercentage = 0;
            this.mMediaPlayer.setDataSource(getContext(), this.mUri, this.mLicenceUri, this.mCustomData, this.mHeaders);
            this.mMediaPlayer.setDisplay(this.mSurfaceView);
            this.mMediaPlayer.setAudioStreamType(3);
            this.mMediaPlayer.setScreenOnWhilePlaying(true);
            this.mMediaPlayer.prepareAsync();
            this.mCurrentState = 1;
            attachMediaController();
        } catch (IOException e) {
            Log.w(this.TAG, "Unable to open content: " + this.mUri, e);
            this.mCurrentState = -1;
            this.mTargetState = -1;
            this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
        } catch (IllegalArgumentException e2) {
            Log.w(this.TAG, "Unable to open content: " + this.mUri, e2);
            this.mCurrentState = -1;
            this.mTargetState = -1;
            this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
        }
    }

    public void setMediaController(MediaController mediaController) {
        MediaController mediaController2 = this.mMediaController;
        if (mediaController2 != null) {
            mediaController2.hide();
        }
        this.mMediaController = mediaController;
        attachMediaController();
    }

    private void attachMediaController() {
        MediaController mediaController = this.mMediaController;
        if (mediaController != null) {
            mediaController.setAnchorView(this);
            if (this.mMediaPlayer != null) {
                this.mMediaController.setMediaPlayer(this);
                this.mMediaController.setEnabled(isInPlaybackState());
            }
        }
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        this.mOnBufferingUpdateListener = onBufferingUpdateListener;
    }

    protected void release(boolean z) {
        this.mUri = null;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            if (z) {
                this.mTargetState = 0;
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mMediaController == null) {
            return false;
        }
        toggleMediaControlsVisiblity();
        return false;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (this.mMediaController == null) {
            return false;
        }
        toggleMediaControlsVisiblity();
        return false;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = (i == 4 || i == 24 || i == 25 || i == 164 || i == 82 || i == 5 || i == 6) ? false : true;
        if (isInPlaybackState() && z && this.mMediaController != null) {
            if (i == 79 || i == 85) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                } else {
                    start();
                    this.mMediaController.hide();
                }
                return true;
            }
            if (i == 126) {
                if (!this.mMediaPlayer.isPlaying()) {
                    start();
                    this.mMediaController.hide();
                }
                return true;
            }
            if (i == 86 || i == 127) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                }
                return true;
            }
            toggleMediaControlsVisiblity();
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void toggleMediaControlsVisiblity() {
        MediaController mediaController = this.mMediaController;
        if (mediaController != null) {
            mediaController.show();
        }
    }

    @Override // com.dotscreen.farkit.video.MediaController.MediaPlayerControl
    public void start() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
        }
        this.mTargetState = 3;
        MediaController mediaController = this.mMediaController;
        if (mediaController != null) {
            mediaController.show();
        }
    }

    @Override // com.dotscreen.farkit.video.MediaController.MediaPlayerControl
    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
        }
        this.mTargetState = 4;
        MediaController mediaController = this.mMediaController;
        if (mediaController != null) {
            mediaController.show(0);
        }
    }

    public void suspend() {
        release(false);
    }

    public void resume() {
        openVideo();
    }

    @Override // com.dotscreen.farkit.video.MediaController.MediaPlayerControl
    public int getDuration() {
        if (isInPlaybackState()) {
            int duration = this.mMediaPlayer.getDuration();
            this.mDuration = duration;
            return duration;
        }
        this.mDuration = -1;
        return -1;
    }

    @Override // com.dotscreen.farkit.video.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // com.dotscreen.farkit.video.MediaController.MediaPlayerControl
    public void seekTo(int i) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(i);
            this.mSeekWhenPrepared = null;
        } else {
            this.mSeekWhenPrepared = Integer.valueOf(i);
        }
    }

    public MediaPlayer getMediaPlayer() {
        return this.mMediaPlayer;
    }

    @Override // com.dotscreen.farkit.video.MediaController.MediaPlayerControl
    public boolean isPrepared() {
        return isInPlaybackState();
    }

    @Override // com.dotscreen.farkit.video.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    @Override // com.dotscreen.farkit.video.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    private boolean isInPlaybackState() {
        int i;
        return (this.mMediaPlayer == null || (i = this.mCurrentState) == -1 || i == 0 || i == 1) ? false : true;
    }

    @Override // com.dotscreen.farkit.video.MediaController.MediaPlayerControl
    public boolean canPause() {
        return this.mCanPause;
    }

    @Override // com.dotscreen.farkit.video.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return this.mCanSeekBack;
    }

    @Override // com.dotscreen.farkit.video.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return this.mCanSeekForward;
    }

    private class VideoSurfaceView extends SurfaceView {
        public VideoSurfaceView(Context context) {
            super(context);
        }

        @Override // android.view.SurfaceView, android.view.View
        protected void onMeasure(int i, int i2) {
            int defaultSize = getDefaultSize(VideoView.this.mVideoWidth, i);
            int defaultSize2 = getDefaultSize(VideoView.this.mVideoHeight, i2);
            if (VideoView.this.mVideoWidth > 0 && VideoView.this.mVideoHeight > 0) {
                if (VideoView.this.mVideoWidth * defaultSize2 > VideoView.this.mVideoHeight * defaultSize) {
                    defaultSize2 = (VideoView.this.mVideoHeight * defaultSize) / VideoView.this.mVideoWidth;
                } else if (VideoView.this.mVideoWidth * defaultSize2 < VideoView.this.mVideoHeight * defaultSize) {
                    defaultSize = (VideoView.this.mVideoWidth * defaultSize2) / VideoView.this.mVideoHeight;
                }
            }
            setMeasuredDimension(defaultSize, defaultSize2);
        }
    }
}
