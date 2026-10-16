package com.dotscreen.farkit.video;

import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PowerManager;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class MediaPlayer {
    public static final String MEDIA_MIMETYPE_TEXT_CEA_608 = "text/cea-608";
    public static final String MEDIA_MIMETYPE_TEXT_SUBRIP = "application/x-subrip";
    public static final String MEDIA_MIMETYPE_TEXT_VTT = "text/vtt";
    private static final String TAG = MediaPlayer.class.getSimpleName();
    protected OnBufferingUpdateListener mOnBufferingUpdateListener;
    protected OnCompletionListener mOnCompletionListener;
    protected OnErrorListener mOnErrorListener;
    protected OnInfoListener mOnInfoListener;
    protected OnPreparedListener mOnPreparedListener;
    protected OnSeekCompleteListener mOnSeekCompleteListener;
    protected OnTimedTextListener mOnTimedTextListener;
    protected OnVideoSizeChangedListener mOnVideoSizeChangedListener;
    protected boolean mScreenOnWhilePlaying;
    protected boolean mStayAwake;
    protected ISurfaceView mSurfaceView;
    protected PowerManager.WakeLock mWakeLock = null;

    public interface OnBufferingUpdateListener {
        void onBufferingUpdate(MediaPlayer mediaPlayer, int i);
    }

    public interface OnCompletionListener {
        void onCompletion(MediaPlayer mediaPlayer);
    }

    public interface OnErrorListener {
        boolean onError(MediaPlayer mediaPlayer, int i, int i2);
    }

    public interface OnInfoListener {
        boolean onInfo(MediaPlayer mediaPlayer, int i, int i2);
    }

    public interface OnPreparedListener {
        void onPrepared(MediaPlayer mediaPlayer);
    }

    public interface OnSeekCompleteListener {
        void onSeekComplete(MediaPlayer mediaPlayer);
    }

    public interface OnTimedTextListener {
        void onTimedText(MediaPlayer mediaPlayer, TimedText timedText);
    }

    public interface OnVideoSizeChangedListener {
        void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2);
    }

    public abstract void addTimedTextSource(Context context, Uri uri, String str) throws IllegalStateException, IOException, IllegalArgumentException;

    public abstract void addTimedTextSource(FileDescriptor fileDescriptor, long j, long j2, String str) throws IllegalStateException, IllegalArgumentException;

    public abstract void addTimedTextSource(FileDescriptor fileDescriptor, String str) throws IllegalStateException, IllegalArgumentException;

    public abstract void addTimedTextSource(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException;

    public abstract void deselectTrack(int i, int i2) throws IllegalStateException;

    public abstract int getCurrentPosition();

    public abstract int getDuration();

    public abstract int getSelectedTrack(int i) throws IllegalStateException;

    public abstract TrackInfo[] getTrackInfo() throws IllegalStateException;

    public abstract int getVideoHeight();

    public abstract int getVideoWidth();

    public abstract boolean isCrypted();

    public abstract boolean isPlaying();

    public abstract void pause() throws IllegalStateException;

    public abstract void prepare() throws IllegalStateException, IOException;

    public abstract void prepareAsync() throws IllegalStateException;

    public abstract void release();

    public abstract void reset();

    public abstract void seekTo(int i) throws IllegalStateException;

    public abstract void selectTrack(int i, int i2) throws IllegalStateException;

    public abstract void setAudioStreamType(int i);

    public abstract void setDataSource(Context context, Uri uri, Uri uri2, String str, Map<String, String> map) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException;

    public abstract void start() throws IllegalStateException;

    public abstract void stop() throws IllegalStateException;

    public void setDisplay(ISurfaceView iSurfaceView) {
        this.mSurfaceView = iSurfaceView;
    }

    public void setDataSource(Context context, Uri uri) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        setDataSource(context, uri, null, null, null);
    }

    public void setDataSource(Context context, Uri uri, Uri uri2, String str) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        setDataSource(context, uri, uri2, str, null);
    }

    public void setWakeMode(Context context, int i) {
        boolean z;
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            if (wakeLock.isHeld()) {
                z = true;
                this.mWakeLock.release();
            } else {
                z = false;
            }
            this.mWakeLock = null;
        } else {
            z = false;
        }
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(i | 536870912, MediaPlayer.class.getName());
        this.mWakeLock = wakeLockNewWakeLock;
        wakeLockNewWakeLock.setReferenceCounted(false);
        if (z) {
            this.mWakeLock.acquire();
        }
    }

    public void setScreenOnWhilePlaying(boolean z) {
        if (this.mScreenOnWhilePlaying != z) {
            if (z && this.mSurfaceView == null) {
                Log.w(TAG, "setScreenOnWhilePlaying(true) is ineffective without a SurfaceHolder");
            }
            this.mScreenOnWhilePlaying = z;
            updateSurfaceScreenOn();
        }
    }

    protected void stayAwake(boolean z) {
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            if (z && !wakeLock.isHeld()) {
                this.mWakeLock.acquire();
            } else if (!z && this.mWakeLock.isHeld()) {
                this.mWakeLock.release();
            }
        }
        this.mStayAwake = z;
        updateSurfaceScreenOn();
    }

    private void updateSurfaceScreenOn() {
        ISurfaceView iSurfaceView = this.mSurfaceView;
        if (iSurfaceView != null) {
            iSurfaceView.setSurfaceHolderKeepScreenOn(this.mScreenOnWhilePlaying && this.mStayAwake);
        }
    }

    public static class TrackInfo implements Parcelable {
        public static final Parcelable.Creator<TrackInfo> CREATOR = new Parcelable.Creator<TrackInfo>() { // from class: com.dotscreen.farkit.video.MediaPlayer.TrackInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public TrackInfo createFromParcel(Parcel parcel) {
                return new TrackInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public TrackInfo[] newArray(int i) {
                return new TrackInfo[i];
            }
        };
        public static final int MEDIA_TRACK_TYPE_AUDIO = 2;
        public static final int MEDIA_TRACK_TYPE_TIMEDTEXT = 3;
        public static final int MEDIA_TRACK_TYPE_UNKNOWN = 0;
        public static final int MEDIA_TRACK_TYPE_VIDEO = 1;
        final String mLanguage;
        final int mTrackType;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public int getTrackType() {
            return this.mTrackType;
        }

        public String getLanguage() {
            return this.mLanguage;
        }

        public TrackInfo(Parcel parcel) {
            this.mTrackType = parcel.readInt();
            this.mLanguage = parcel.readString();
        }

        public TrackInfo(int i, String str) {
            this.mTrackType = i;
            this.mLanguage = str;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mTrackType);
            parcel.writeString(this.mLanguage);
        }
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener onBufferingUpdateListener) {
        this.mOnBufferingUpdateListener = onBufferingUpdateListener;
    }

    public void setOnSeekCompleteListener(OnSeekCompleteListener onSeekCompleteListener) {
        this.mOnSeekCompleteListener = onSeekCompleteListener;
    }

    public void setOnVideoSizeChangedListener(OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.mOnVideoSizeChangedListener = onVideoSizeChangedListener;
    }

    public void setOnTimedTextListener(OnTimedTextListener onTimedTextListener) {
        this.mOnTimedTextListener = onTimedTextListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnInfoListener(OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }
}
