package com.dotscreen.farkit.video;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.Formatter;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class MediaController extends FrameLayout {
    private static final int FADE_OUT = 1;
    private static final int SHOW_PROGRESS = 2;
    private static final String TAG = MediaController.class.getSimpleName();
    private ViewGroup mAnchor;
    private int mDisplayTimeout;
    private boolean mDragging;
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;
    private Handler mHandler;
    private MediaPlayerControl mPlayer;
    private boolean mShowing;
    private View.OnTouchListener mTouchListener;

    public interface MediaPlayerControl {
        boolean canPause();

        boolean canSeekBackward();

        boolean canSeekForward();

        int getAudioSessionId();

        int getBufferPercentage();

        int getCurrentPosition();

        int getDuration();

        boolean isPlaying();

        boolean isPrepared();

        void pause();

        void seekTo(int i);

        void start();
    }

    protected abstract View makeControllerView();

    protected void onPlaybackStatusChanged() {
    }

    protected void onPostHide() {
    }

    protected void onPostShow() {
    }

    protected void onPreHide() {
    }

    protected void onPreShow() {
    }

    protected void onProgressChanged(int i, int i2) {
    }

    public MediaController(Context context) {
        super(context);
        this.mDisplayTimeout = 5000;
        this.mTouchListener = new View.OnTouchListener() { // from class: com.dotscreen.farkit.video.MediaController.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0 || !MediaController.this.mShowing) {
                    return false;
                }
                MediaController.this.hide();
                return false;
            }
        };
        this.mHandler = new Handler() { // from class: com.dotscreen.farkit.video.MediaController.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    MediaController.this.hide();
                    return;
                }
                if (i != 2) {
                    return;
                }
                int progress = MediaController.this.setProgress();
                if (MediaController.this.mDragging || !MediaController.this.mShowing || MediaController.this.mPlayer == null) {
                    return;
                }
                sendMessageDelayed(obtainMessage(2), 1000 - (progress % 1000));
            }
        };
        this.mFormatBuilder = new StringBuilder();
        this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
    }

    public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
        this.mPlayer = mediaPlayerControl;
        onPlaybackStatusChanged();
    }

    public void setAnchorView(ViewGroup viewGroup) {
        this.mAnchor = viewGroup;
        removeAllViews();
        addView(makeControllerView(), makeControllerViewLayoutParams());
    }

    protected FrameLayout.LayoutParams makeControllerViewLayoutParams() {
        return new FrameLayout.LayoutParams(-1, -2, 80);
    }

    public void show() {
        show(this.mDisplayTimeout);
    }

    public void show(int i) {
        if (!this.mShowing && this.mAnchor != null) {
            setProgress();
            onPreShow();
            this.mAnchor.addView(this, new FrameLayout.LayoutParams(-1, -1));
            this.mShowing = true;
            onPostShow();
        }
        this.mHandler.sendEmptyMessage(2);
        Message messageObtainMessage = this.mHandler.obtainMessage(1);
        this.mHandler.removeMessages(1);
        if (i != 0) {
            this.mHandler.sendMessageDelayed(messageObtainMessage, i);
        }
    }

    public boolean isShowing() {
        return this.mShowing;
    }

    public void hide() {
        if (this.mAnchor != null && this.mShowing) {
            onPreHide();
            try {
                this.mHandler.removeMessages(2);
                this.mAnchor.removeView(this);
                onPostHide();
            } catch (Exception unused) {
                Log.w(TAG, "already removed");
            }
            this.mShowing = false;
        }
    }

    void destroy() {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
    }

    protected int setProgress() {
        MediaPlayerControl mediaPlayerControl = this.mPlayer;
        if (mediaPlayerControl == null || this.mDragging) {
            return 0;
        }
        int currentPosition = mediaPlayerControl.getCurrentPosition();
        onProgressChanged(currentPosition, this.mPlayer.getDuration());
        return currentPosition;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mHandler.removeMessages(1);
            show(0);
        } else if (action != 1) {
            if (action == 3) {
                hide();
            }
        } else if (this.mShowing) {
            hide();
        } else {
            show(this.mDisplayTimeout);
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        show(this.mDisplayTimeout);
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        boolean z = keyEvent.getRepeatCount() == 0 && keyEvent.getAction() == 0;
        if (keyCode == 79 || keyCode == 85 || keyCode == 62) {
            if (z) {
                doPauseResume();
                show(this.mDisplayTimeout);
            }
            return true;
        }
        if (keyCode == 126) {
            if (z && !this.mPlayer.isPlaying()) {
                this.mPlayer.start();
                onPlaybackStatusChanged();
                show(this.mDisplayTimeout);
            }
            return true;
        }
        if (keyCode == 86 || keyCode == 127) {
            if (z && this.mPlayer.isPlaying()) {
                this.mPlayer.pause();
                onPlaybackStatusChanged();
                show(this.mDisplayTimeout);
            }
            return true;
        }
        if (keyCode == 25 || keyCode == 24 || keyCode == 164 || keyCode == 27) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (keyCode != 4 && keyCode != 82) {
            show(this.mDisplayTimeout);
            return super.dispatchKeyEvent(keyEvent);
        }
        if (z) {
            hide();
        }
        return true;
    }

    private void doPauseResume() {
        if (this.mPlayer.isPlaying()) {
            this.mPlayer.pause();
        } else {
            this.mPlayer.start();
        }
        onPlaybackStatusChanged();
    }

    protected String stringForTime(long j) {
        long j2 = j / 1000;
        long j3 = j2 % 60;
        long j4 = (j2 / 60) % 60;
        long j5 = j2 / 3600;
        this.mFormatBuilder.setLength(0);
        return j5 > 0 ? this.mFormatter.format("%d:%02d:%02d", Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)).toString() : this.mFormatter.format("%02d:%02d", Long.valueOf(j4), Long.valueOf(j3)).toString();
    }

    public void setDisplayTimeout(int i) {
        this.mDisplayTimeout = i;
    }

    protected void setDraging(boolean z) {
        this.mDragging = z;
    }

    public MediaPlayerControl getPlayer() {
        return this.mPlayer;
    }
}
