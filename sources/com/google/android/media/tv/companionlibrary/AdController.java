package com.google.android.media.tv.companionlibrary;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AdController implements AdErrorEvent.AdErrorListener, AdsLoader.AdsLoadedListener, AdEvent.AdEventListener {
    private static final boolean DEBUG = false;
    private static final String TAG = "AdController";
    private AdControllerCallback mAdControllerCallback;
    private AdDisplayContainer mAdDisplayContainer;
    private AdsLoader mAdsLoader;
    private AdsManager mAdsManager;
    private ImaSdkFactory mSdkFactory;
    private ViewGroup mStubViewGroup;

    public interface AdControllerCallback {
        void onAdCompleted();

        void onAdError();

        TvPlayer onAdReadyToPlay(String str);
    }

    public AdController(Context context) {
        ImaSdkFactory imaSdkFactory = ImaSdkFactory.getInstance();
        this.mSdkFactory = imaSdkFactory;
        AdsLoader adsLoaderCreateAdsLoader = imaSdkFactory.createAdsLoader(context);
        this.mAdsLoader = adsLoaderCreateAdsLoader;
        adsLoaderCreateAdsLoader.addAdErrorListener(this);
        this.mAdsLoader.addAdsLoadedListener(this);
        this.mStubViewGroup = new FrameLayout(context);
    }

    public void requestAds(String str, AdControllerCallback adControllerCallback) {
        this.mAdControllerCallback = adControllerCallback;
        AdDisplayContainer adDisplayContainerCreateAdDisplayContainer = this.mSdkFactory.createAdDisplayContainer();
        this.mAdDisplayContainer = adDisplayContainerCreateAdDisplayContainer;
        adDisplayContainerCreateAdDisplayContainer.setPlayer(new VideoAdPlayerImpl());
        this.mAdDisplayContainer.setAdContainer(this.mStubViewGroup);
        AdsRequest adsRequestCreateAdsRequest = this.mSdkFactory.createAdsRequest();
        adsRequestCreateAdsRequest.setAdTagUrl(str);
        adsRequestCreateAdsRequest.setAdDisplayContainer(this.mAdDisplayContainer);
        this.mAdsLoader.requestAds(adsRequestCreateAdsRequest);
    }

    public void release() {
        AdsManager adsManager = this.mAdsManager;
        if (adsManager != null) {
            adsManager.destroy();
            this.mAdsManager = null;
        }
        AdsLoader adsLoader = this.mAdsLoader;
        if (adsLoader != null) {
            adsLoader.removeAdsLoadedListener(this);
            this.mAdsLoader.removeAdErrorListener(this);
        }
    }

    public void onAdError(AdErrorEvent adErrorEvent) {
        Log.e(TAG, adErrorEvent.getError().getMessage());
        this.mAdControllerCallback.onAdError();
        release();
    }

    public void onAdsManagerLoaded(AdsManagerLoadedEvent adsManagerLoadedEvent) {
        AdsManager adsManager = adsManagerLoadedEvent.getAdsManager();
        this.mAdsManager = adsManager;
        adsManager.addAdErrorListener(this);
        this.mAdsManager.addAdEventListener(this);
        this.mAdsManager.init();
    }

    /* JADX INFO: renamed from: com.google.android.media.tv.companionlibrary.AdController$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType;

        static {
            int[] iArr = new int[AdEvent.AdEventType.values().length];
            $SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType = iArr;
            try {
                iArr[AdEvent.AdEventType.LOADED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType[AdEvent.AdEventType.COMPLETED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public void onAdEvent(AdEvent adEvent) {
        int i = AnonymousClass1.$SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType[adEvent.getType().ordinal()];
        if (i == 1) {
            this.mAdsManager.start();
        } else {
            if (i != 2) {
                return;
            }
            this.mAdControllerCallback.onAdCompleted();
            release();
        }
    }

    private class VideoAdPlayerImpl extends TvPlayer.Callback implements VideoAdPlayer {
        List<VideoAdPlayer.VideoAdPlayerCallback> mAdCallbacks = new ArrayList(1);
        String mAdVideoUrl;
        TvPlayer mTvPlayer;

        public void pauseAd() {
        }

        public void resumeAd() {
        }

        public void stopAd() {
        }

        VideoAdPlayerImpl() {
        }

        public void loadAd(String str) {
            this.mAdVideoUrl = str;
        }

        public void playAd() {
            TvPlayer tvPlayerOnAdReadyToPlay = AdController.this.mAdControllerCallback.onAdReadyToPlay(this.mAdVideoUrl);
            this.mTvPlayer = tvPlayerOnAdReadyToPlay;
            tvPlayerOnAdReadyToPlay.registerCallback(this);
            this.mTvPlayer.play();
        }

        public void addCallback(VideoAdPlayer.VideoAdPlayerCallback videoAdPlayerCallback) {
            this.mAdCallbacks.add(videoAdPlayerCallback);
        }

        public void removeCallback(VideoAdPlayer.VideoAdPlayerCallback videoAdPlayerCallback) {
            this.mAdCallbacks.remove(videoAdPlayerCallback);
        }

        public VideoProgressUpdate getAdProgress() {
            if (this.mTvPlayer == null) {
                return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
            }
            return new VideoProgressUpdate(this.mTvPlayer.getCurrentPosition(), this.mTvPlayer.getDuration());
        }

        @Override // com.google.android.media.tv.companionlibrary.TvPlayer.Callback
        public void onStarted() {
            Iterator<VideoAdPlayer.VideoAdPlayerCallback> it = this.mAdCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onPlay();
            }
        }

        @Override // com.google.android.media.tv.companionlibrary.TvPlayer.Callback
        public void onCompleted() {
            Iterator<VideoAdPlayer.VideoAdPlayerCallback> it = this.mAdCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onEnded();
            }
        }

        @Override // com.google.android.media.tv.companionlibrary.TvPlayer.Callback
        public void onError(Exception exc) {
            Iterator<VideoAdPlayer.VideoAdPlayerCallback> it = this.mAdCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onError();
            }
        }

        @Override // com.google.android.media.tv.companionlibrary.TvPlayer.Callback
        public void onPaused() {
            Iterator<VideoAdPlayer.VideoAdPlayerCallback> it = this.mAdCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onPause();
            }
        }

        @Override // com.google.android.media.tv.companionlibrary.TvPlayer.Callback
        public void onResumed() {
            Iterator<VideoAdPlayer.VideoAdPlayerCallback> it = this.mAdCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onResume();
            }
        }
    }
}
