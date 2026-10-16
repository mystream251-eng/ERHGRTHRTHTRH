package com.dotscreen.tvinput;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.media.PlaybackParams;
import android.media.tv.TvContentRating;
import android.media.tv.TvContract;
import android.media.tv.TvInputManager;
import android.media.tv.TvInputService;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.Surface;
import com.google.android.media.tv.companionlibrary.TvPlayer;
import com.google.android.media.tv.companionlibrary.model.Advertisement;
import com.google.android.media.tv.companionlibrary.model.Channel;
import com.google.android.media.tv.companionlibrary.model.ModelUtils;
import com.google.android.media.tv.companionlibrary.model.Program;
import com.google.android.media.tv.companionlibrary.model.RecordedProgram;
import com.google.android.media.tv.companionlibrary.utils.Constants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseMegaTvInputService extends TvInputService {
    private static final boolean DEBUG = false;
    private static LongSparseArray<Channel> mChannelMap;
    private static ContentObserver mChannelObserver;
    private static ContentResolver mContentResolver;
    private HandlerThread mDbHandlerThread;
    private final BroadcastReceiver mParentalControlsBroadcastReceiver = new BroadcastReceiver() { // from class: com.dotscreen.tvinput.BaseMegaTvInputService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            for (Session session : BaseMegaTvInputService.mSessions) {
                if (!((TvInputManager) context.getSystemService("tv_input")).isParentalControlsEnabled()) {
                    session.onUnblockContent(null);
                } else {
                    session.checkCurrentProgramContent();
                }
            }
        }
    };
    private static final String TAG = BaseMegaTvInputService.class.getSimpleName();
    private static final List<Session> mSessions = new ArrayList();

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread(getClass().getSimpleName());
        this.mDbHandlerThread = handlerThread;
        handlerThread.start();
        mContentResolver = getContentResolver();
        updateChannelMap();
        mChannelObserver = new ContentObserver(new Handler(this.mDbHandlerThread.getLooper())) { // from class: com.dotscreen.tvinput.BaseMegaTvInputService.2
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                BaseMegaTvInputService.this.updateChannelMap();
            }
        };
        mContentResolver.registerContentObserver(TvContract.Channels.CONTENT_URI, true, mChannelObserver);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.tv.action.BLOCKED_RATINGS_CHANGED");
        intentFilter.addAction("android.media.tv.action.PARENTAL_CONTROLS_ENABLED_CHANGED");
        registerReceiver(this.mParentalControlsBroadcastReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateChannelMap() {
        mChannelMap = ModelUtils.buildChannelMap(mContentResolver, TvContract.buildInputId(new ComponentName(getPackageName(), getClass().getName())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateChannelMap(String str) {
        mChannelMap = ModelUtils.buildChannelMap(mContentResolver, str);
    }

    public TvInputService.Session sessionCreated(TvInputService.Session session) {
        mSessions.add((Session) session);
        return session;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mParentalControlsBroadcastReceiver);
        mContentResolver.unregisterContentObserver(mChannelObserver);
        HandlerThread handlerThread = this.mDbHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
        } else {
            Log.e("BaseMegaTvInputService", "handler have been nulled");
        }
        this.mDbHandlerThread = null;
    }

    public abstract class Session extends TvInputService.Session implements Handler.Callback {
        private static final int MSG_PLAY_AD = 1001;
        private static final int MSG_PLAY_CONTENT = 1000;
        private static final int MSG_PLAY_RECORDED_CONTENT = 1002;
        private static final long PAST_AD_BUFFER_MILLIS = 2000;
        private static final long TIME_SHIFTED_MINIMUM_DIFFERENCE_MILLIS = 3000;
        private Uri mChannelUri;
        private final Context mContext;
        private Channel mCurrentChannel;
        private TvContentRating[] mCurrentContentRatingSet;
        private Program mCurrentProgram;
        private final Handler mDbHandler;
        private long mElapsedAdsTime;
        private long mElapsedProgramTime;
        private GetCurrentProgramRunnable mGetCurrentProgramRunnable;
        private final Handler mHandler;
        private String mInputId;
        private TvContentRating mLastBlockedRating;
        private long mMinimumOnTuneAdInterval;
        private boolean mNeedToCheckChannelAd;
        private boolean mPlayingRecordedProgram;
        private long mRecordedPlaybackStartTime;
        private RecordedProgram mRecordedProgram;
        private Surface mSurface;
        private boolean mTimeShiftIsPaused;
        private long mTimeShiftedPlaybackPosition;
        private final TvInputManager mTvInputManager;
        private final Set<TvContentRating> mUnblockedRatingSet;
        private float mVolume;

        private boolean insertAd(Advertisement advertisement) {
            return false;
        }

        private void releaseAdController() {
        }

        public abstract TvPlayer getTvPlayer();

        public void onBlockContent(TvContentRating tvContentRating) {
        }

        public void onPlayChannel(Channel channel) {
        }

        public abstract boolean onPlayProgram(Program program, long j);

        public abstract boolean onPlayRecordedProgram(RecordedProgram recordedProgram);

        public Session(Context context, String str) {
            super(context);
            this.mTimeShiftedPlaybackPosition = Long.MIN_VALUE;
            this.mRecordedPlaybackStartTime = Long.MIN_VALUE;
            this.mUnblockedRatingSet = new HashSet();
            this.mMinimumOnTuneAdInterval = TimeUnit.MINUTES.toMillis(5L);
            this.mVolume = 1.0f;
            this.mContext = context;
            this.mTvInputManager = (TvInputManager) context.getSystemService("tv_input");
            this.mLastBlockedRating = null;
            this.mDbHandler = new Handler(BaseMegaTvInputService.this.mDbHandlerThread.getLooper());
            this.mHandler = new Handler(this);
            this.mInputId = str;
        }

        @Override // android.media.tv.TvInputService.Session
        public void onRelease() {
            this.mDbHandler.removeCallbacksAndMessages(null);
            this.mHandler.removeCallbacksAndMessages(null);
            releaseAdController();
            BaseMegaTvInputService.mSessions.remove(this);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1000:
                    this.mCurrentProgram = (Program) message.obj;
                    playCurrentContent();
                    return true;
                case 1001:
                    return insertAd((Advertisement) message.obj);
                case 1002:
                    if (Build.VERSION.SDK_INT >= 24) {
                        this.mPlayingRecordedProgram = true;
                        this.mRecordedProgram = (RecordedProgram) message.obj;
                        playRecordedContent();
                    }
                    return true;
                default:
                    return false;
            }
        }

        @Override // android.media.tv.TvInputService.Session
        public boolean onSetSurface(Surface surface) {
            Log.d(BaseMegaTvInputService.TAG, "onSetSurface: " + surface);
            setTvPlayerSurface(surface);
            this.mSurface = surface;
            return true;
        }

        private void setTvPlayerSurface(Surface surface) {
            if (getTvPlayer() != null) {
                getTvPlayer().setSurface(surface);
            }
        }

        @Override // android.media.tv.TvInputService.Session
        public void onSetStreamVolume(float f) {
            setTvPlayerVolume(f);
            this.mVolume = f;
        }

        private void setTvPlayerVolume(float f) {
            if (getTvPlayer() != null) {
                getTvPlayer().setVolume(f);
            }
        }

        @Override // android.media.tv.TvInputService.Session
        public boolean onTune(Uri uri) {
            this.mNeedToCheckChannelAd = true;
            notifyVideoUnavailable(1);
            this.mChannelUri = uri;
            long id = ContentUris.parseId(uri);
            if (BaseMegaTvInputService.mChannelMap == null) {
                BaseMegaTvInputService.updateChannelMap(this.mInputId);
            }
            this.mCurrentChannel = (Channel) BaseMegaTvInputService.mChannelMap.get(id);
            this.mTimeShiftedPlaybackPosition = Long.MIN_VALUE;
            releaseAdController();
            this.mHandler.removeMessages(1001);
            if (this.mDbHandler != null) {
                this.mUnblockedRatingSet.clear();
                this.mDbHandler.removeCallbacks(this.mGetCurrentProgramRunnable);
                GetCurrentProgramRunnable getCurrentProgramRunnable = new GetCurrentProgramRunnable(this.mChannelUri);
                this.mGetCurrentProgramRunnable = getCurrentProgramRunnable;
                this.mDbHandler.post(getCurrentProgramRunnable);
            }
            return true;
        }

        @Override // android.media.tv.TvInputService.Session
        public void onTimeShiftPause() {
            this.mHandler.removeMessages(1001);
            this.mDbHandler.removeCallbacks(this.mGetCurrentProgramRunnable);
            this.mTimeShiftIsPaused = true;
            if (getTvPlayer() != null) {
                getTvPlayer().pause();
            }
        }

        @Override // android.media.tv.TvInputService.Session
        public void onTimeShiftResume() {
            this.mTimeShiftIsPaused = false;
            if (this.mCurrentProgram == null) {
                return;
            }
            if (!this.mPlayingRecordedProgram) {
                long jLongValue = 0;
                this.mElapsedAdsTime = 0L;
                long currentPosition = getTvPlayer().getCurrentPosition();
                this.mElapsedProgramTime = currentPosition;
                long startTimeUtcMillis = currentPosition + this.mCurrentProgram.getStartTimeUtcMillis();
                if (this.mCurrentProgram.getInternalProviderData() != null) {
                    List<Advertisement> ads = this.mCurrentProgram.getInternalProviderData().getAds();
                    TreeMap treeMap = new TreeMap();
                    for (Advertisement advertisement : ads) {
                        treeMap.put(Long.valueOf(advertisement.getStartTimeUtcMillis()), Long.valueOf(advertisement.getStopTimeUtcMillis()));
                    }
                    long j = 0;
                    for (Long l : treeMap.keySet()) {
                        jLongValue += l.longValue() - j;
                        if (jLongValue >= startTimeUtcMillis) {
                            break;
                        }
                        long jLongValue2 = ((Long) treeMap.get(l)).longValue() - l.longValue();
                        this.mElapsedAdsTime += jLongValue2;
                        j = jLongValue2 + jLongValue;
                    }
                } else {
                    Log.w(BaseMegaTvInputService.TAG, "Failed to get program provider data for " + this.mCurrentProgram.getTitle() + ". Try to do an EPG sync.");
                }
                this.mTimeShiftedPlaybackPosition = startTimeUtcMillis + this.mElapsedAdsTime;
                scheduleNextAd();
                scheduleNextProgram();
            }
            if (getTvPlayer() != null) {
                getTvPlayer().play();
            }
            if (Build.VERSION.SDK_INT >= 23) {
                PlaybackParams playbackParams = new PlaybackParams();
                playbackParams.setSpeed(1.0f);
                onTimeShiftSetPlaybackParams(playbackParams);
            }
        }

        @Override // android.media.tv.TvInputService.Session
        public void onTimeShiftSeekTo(long j) {
            if (this.mCurrentProgram == null) {
                return;
            }
            this.mHandler.removeMessages(1001);
            this.mDbHandler.removeCallbacks(this.mGetCurrentProgramRunnable);
            if (getTvPlayer() != null) {
                if (this.mPlayingRecordedProgram) {
                    getTvPlayer().seekTo((j - this.mRecordedPlaybackStartTime) + (this.mCurrentProgram.getInternalProviderData().getRecordedProgramStartTime() - this.mCurrentProgram.getStartTimeUtcMillis()));
                    return;
                }
                this.mTimeShiftedPlaybackPosition = j;
                calculateElapsedTimesFromCurrentTime();
                scheduleNextAd();
                scheduleNextProgram();
                getTvPlayer().seekTo(this.mElapsedProgramTime);
                onTimeShiftGetCurrentPosition();
                if (this.mTimeShiftIsPaused) {
                    onTimeShiftPause();
                }
            }
        }

        @Override // android.media.tv.TvInputService.Session
        public long onTimeShiftGetStartPosition() {
            Program program = this.mCurrentProgram;
            if (program == null) {
                return Long.MIN_VALUE;
            }
            if (this.mPlayingRecordedProgram) {
                return this.mRecordedPlaybackStartTime;
            }
            return program.getStartTimeUtcMillis();
        }

        @Override // android.media.tv.TvInputService.Session
        public long onTimeShiftGetCurrentPosition() {
            Program program;
            if (getTvPlayer() == null || (program = this.mCurrentProgram) == null) {
                return Long.MIN_VALUE;
            }
            if (this.mPlayingRecordedProgram) {
                long recordedProgramStartTime = program.getInternalProviderData().getRecordedProgramStartTime();
                if (getTvPlayer().getCurrentPosition() < recordedProgramStartTime - this.mCurrentProgram.getStartTimeUtcMillis()) {
                    getTvPlayer().seekTo(recordedProgramStartTime - this.mCurrentProgram.getStartTimeUtcMillis());
                    getTvPlayer().pause();
                }
                return (getTvPlayer().getCurrentPosition() - (recordedProgramStartTime - this.mCurrentProgram.getStartTimeUtcMillis())) + this.mRecordedPlaybackStartTime;
            }
            long currentPosition = getTvPlayer().getCurrentPosition();
            this.mElapsedProgramTime = currentPosition;
            this.mTimeShiftedPlaybackPosition = currentPosition + this.mElapsedAdsTime + this.mCurrentProgram.getStartTimeUtcMillis();
            return getCurrentTime();
        }

        @Override // android.media.tv.TvInputService.Session
        public void onTimeShiftSetPlaybackParams(PlaybackParams playbackParams) {
            if (playbackParams.getSpeed() != 1.0f) {
                this.mHandler.removeMessages(1001);
                this.mDbHandler.removeCallbacks(this.mGetCurrentProgramRunnable);
            }
            if (getTvPlayer() != null) {
                getTvPlayer().setPlaybackParams(playbackParams);
            }
        }

        @Override // android.media.tv.TvInputService.Session
        public void onTimeShiftPlay(Uri uri) {
            this.mDbHandler.post(new GetRecordedProgramRunnable(uri));
        }

        @Override // android.media.tv.TvInputService.Session
        public void onUnblockContent(TvContentRating tvContentRating) {
            if (tvContentRating == null) {
                this.mUnblockedRatingSet.clear();
            }
            unblockContent(tvContentRating);
            if (this.mPlayingRecordedProgram) {
                playRecordedContent();
            } else {
                playCurrentContent();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean checkCurrentProgramContent() {
            Program program = this.mCurrentProgram;
            this.mCurrentContentRatingSet = (program == null || program.getContentRatings() == null || this.mCurrentProgram.getContentRatings().length == 0) ? null : this.mCurrentProgram.getContentRatings();
            return blockContentIfNeeded();
        }

        private void playRecordedContent() {
            this.mCurrentProgram = this.mRecordedProgram.toProgram();
            if (!this.mTvInputManager.isParentalControlsEnabled() || checkCurrentProgramContent()) {
                this.mRecordedPlaybackStartTime = System.currentTimeMillis();
                if (onPlayRecordedProgram(this.mRecordedProgram)) {
                    setTvPlayerSurface(this.mSurface);
                    setTvPlayerVolume(this.mVolume);
                }
            }
        }

        private long getCurrentTime() {
            if (Build.VERSION.SDK_INT >= 23) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j = this.mTimeShiftedPlaybackPosition;
                long j2 = jCurrentTimeMillis - j;
                if (j != Long.MIN_VALUE && j2 > TIME_SHIFTED_MINIMUM_DIFFERENCE_MILLIS) {
                    return j;
                }
            }
            this.mTimeShiftedPlaybackPosition = Long.MIN_VALUE;
            return System.currentTimeMillis();
        }

        private void scheduleNextProgram() {
            this.mDbHandler.removeCallbacks(this.mGetCurrentProgramRunnable);
            this.mDbHandler.postDelayed(this.mGetCurrentProgramRunnable, this.mCurrentProgram.getEndTimeUtcMillis() - getCurrentTime());
        }

        private void playCurrentContent() {
            if (this.mTvInputManager.isParentalControlsEnabled() && !checkCurrentProgramContent()) {
                scheduleNextProgram();
                return;
            }
            if (this.mNeedToCheckChannelAd) {
                playCurrentChannel();
                return;
            }
            if (playCurrentProgram()) {
                setTvPlayerSurface(this.mSurface);
                setTvPlayerVolume(this.mVolume);
                if (this.mCurrentProgram != null) {
                    scheduleNextProgram();
                }
            }
        }

        private void calculateElapsedTimesFromCurrentTime() {
            long currentTime = getCurrentTime();
            this.mElapsedAdsTime = 0L;
            this.mElapsedProgramTime = currentTime - this.mCurrentProgram.getStartTimeUtcMillis();
            if (this.mCurrentProgram.getInternalProviderData() == null) {
                Log.w(BaseMegaTvInputService.TAG, "Failed to get program provider data for " + this.mCurrentProgram.getTitle() + ". Try to do an EPG sync.");
                return;
            }
            for (Advertisement advertisement : this.mCurrentProgram.getInternalProviderData().getAds()) {
                if (advertisement.getStopTimeUtcMillis() < 2000 + currentTime) {
                    long stopTimeUtcMillis = advertisement.getStopTimeUtcMillis() - advertisement.getStartTimeUtcMillis();
                    this.mElapsedAdsTime += stopTimeUtcMillis;
                    this.mElapsedProgramTime -= stopTimeUtcMillis;
                }
            }
        }

        private boolean playCurrentProgram() {
            if (this.mCurrentProgram == null) {
                Log.w(BaseMegaTvInputService.TAG, "Failed to get program info for " + this.mChannelUri + ". Try to do an EPG sync.");
                return onPlayProgram(null, 0L);
            }
            calculateElapsedTimesFromCurrentTime();
            if (scheduleNextAd()) {
                return onPlayProgram(this.mCurrentProgram, this.mElapsedProgramTime);
            }
            return false;
        }

        private boolean scheduleNextAd() {
            this.mHandler.removeMessages(1001);
            if (this.mPlayingRecordedProgram) {
                return false;
            }
            long currentTime = getCurrentTime();
            if (this.mCurrentProgram.getInternalProviderData() == null) {
                Log.w(BaseMegaTvInputService.TAG, "Failed to get program provider data for " + this.mCurrentProgram.getTitle() + ". Try to do an EPG sync.");
                return true;
            }
            Advertisement advertisement = null;
            long j = 0;
            for (Advertisement advertisement2 : this.mCurrentProgram.getInternalProviderData().getAds()) {
                if (advertisement2.getStopTimeUtcMillis() > 2000 + currentTime) {
                    long startTimeUtcMillis = advertisement2.getStartTimeUtcMillis() - currentTime;
                    if (startTimeUtcMillis < 0) {
                        Handler handler = this.mHandler;
                        handler.sendMessage(handler.obtainMessage(1001, advertisement2));
                        return false;
                    }
                    if (advertisement == null || startTimeUtcMillis < j) {
                        advertisement = advertisement2;
                        j = startTimeUtcMillis;
                    }
                }
            }
            if (advertisement == null) {
                return true;
            }
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1001, advertisement), j);
            return true;
        }

        /* JADX WARN: Code duplicated, block: B:9:0x0056  */
        private void playCurrentChannel() {
            Message messageObtainMessage;
            if (this.mCurrentChannel.getInternalProviderData() != null) {
                long j = this.mContext.getSharedPreferences("com.google.android.media.tv.companionlibrary", 0).getLong(Constants.SHARED_PREFERENCES_KEY_LAST_CHANNEL_AD_PLAY + this.mCurrentChannel.getId(), 0L);
                List<Advertisement> ads = this.mCurrentChannel.getInternalProviderData().getAds();
                if (ads.isEmpty() || System.currentTimeMillis() - j <= this.mMinimumOnTuneAdInterval) {
                    messageObtainMessage = null;
                } else {
                    messageObtainMessage = this.mHandler.obtainMessage(1001, ads.get(0));
                }
            } else {
                messageObtainMessage = null;
            }
            onPlayChannel(this.mCurrentChannel);
            if (messageObtainMessage != null) {
                messageObtainMessage.sendToTarget();
            } else {
                this.mNeedToCheckChannelAd = false;
                playCurrentContent();
            }
        }

        public Channel getCurrentChannel() {
            return this.mCurrentChannel;
        }

        public void onPlayAdvertisement(Advertisement advertisement) {
            throw new UnsupportedOperationException("Override BaseMegaTvInputService.Session.onPlayAdvertisement(int, Uri) to enable ads insertion.");
        }

        public void setMinimumOnTuneAdInterval(long j) {
            this.mMinimumOnTuneAdInterval = j;
        }

        public Uri getCurrentChannelUri() {
            return this.mChannelUri;
        }

        private boolean blockContentIfNeeded() {
            if (this.mCurrentContentRatingSet == null || !this.mTvInputManager.isParentalControlsEnabled()) {
                unblockContent(null);
                return true;
            }
            TvContentRating tvContentRating = null;
            for (TvContentRating tvContentRating2 : this.mCurrentContentRatingSet) {
                if (this.mTvInputManager.isRatingBlocked(tvContentRating2) && !this.mUnblockedRatingSet.contains(tvContentRating2)) {
                    tvContentRating = tvContentRating2;
                }
            }
            if (tvContentRating == null) {
                unblockContent(null);
                return true;
            }
            this.mLastBlockedRating = tvContentRating;
            onBlockContent(tvContentRating);
            notifyContentBlocked(tvContentRating);
            if (this.mTimeShiftedPlaybackPosition != Long.MIN_VALUE) {
                onTimeShiftPause();
            }
            return false;
        }

        private void unblockContent(TvContentRating tvContentRating) {
            TvContentRating tvContentRating2;
            if (tvContentRating == null || (tvContentRating2 = this.mLastBlockedRating) == null || tvContentRating.equals(tvContentRating2)) {
                this.mLastBlockedRating = null;
                if (tvContentRating != null) {
                    this.mUnblockedRatingSet.add(tvContentRating);
                }
                notifyContentAllowed();
            }
        }

        private class GetCurrentProgramRunnable implements Runnable {
            private final Uri mChannelUri;

            GetCurrentProgramRunnable(Uri uri) {
                this.mChannelUri = uri;
            }

            @Override // java.lang.Runnable
            public void run() {
                Program currentProgram;
                ContentResolver contentResolver = Session.this.mContext.getContentResolver();
                long jCurrentTimeMillis = System.currentTimeMillis() - Session.this.mTimeShiftedPlaybackPosition;
                if (Session.this.mTimeShiftedPlaybackPosition == Long.MIN_VALUE || jCurrentTimeMillis <= Session.TIME_SHIFTED_MINIMUM_DIFFERENCE_MILLIS) {
                    Session.this.mTimeShiftedPlaybackPosition = Long.MIN_VALUE;
                    currentProgram = ModelUtils.getCurrentProgram(contentResolver, this.mChannelUri);
                } else {
                    currentProgram = ModelUtils.getNextProgram(contentResolver, this.mChannelUri, Session.this.mCurrentProgram);
                }
                Session.this.mHandler.removeMessages(1000);
                Session.this.mHandler.obtainMessage(1000, currentProgram).sendToTarget();
            }
        }

        private class GetRecordedProgramRunnable implements Runnable {
            private final Uri mRecordedProgramUri;

            GetRecordedProgramRunnable(Uri uri) {
                this.mRecordedProgramUri = uri;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.mRecordedProgramUri == null) {
                    Session.this.notifyVideoUnavailable(0);
                    return;
                }
                Cursor cursorQuery = Session.this.mContext.getContentResolver().query(this.mRecordedProgramUri, RecordedProgram.PROJECTION, null, null, null);
                if (cursorQuery == null) {
                    Session.this.notifyVideoUnavailable(0);
                    return;
                }
                if (cursorQuery.moveToNext()) {
                    RecordedProgram recordedProgramFromCursor = RecordedProgram.fromCursor(cursorQuery);
                    if (recordedProgramFromCursor == null) {
                        Log.e(BaseMegaTvInputService.TAG, "RecordedProgram at " + this.mRecordedProgramUri + " does not exist");
                        Session.this.notifyVideoUnavailable(0);
                    }
                    Session.this.mHandler.removeMessages(1002);
                    Session.this.mHandler.obtainMessage(1002, recordedProgramFromCursor).sendToTarget();
                }
            }
        }
    }
}
