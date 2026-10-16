package com.viaccessorca.voplayer;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class VOSafeTVManager {
    public static final int CACHED_ENABLED_FOR_BUFFER = 1;
    public static final int CACHED_ENABLED_FOR_FORWARD = 2;
    public static final int CACHED_ENABLED_FOR_FORWARD_AND_BACKWARD = 3;
    public static final int CACHED_ENABLED_FOR_NONE = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference<VOPlayer> f89a = null;

    public static class PlaybackParameters {
        public boolean disableNetworkError = false;
        public boolean insertSafeTVContentOnRebuffering = false;
        public int qualityHeight = -1;
        public int bufferLengthInSeconds = -1;
        public int cachingSizeInKB = 0;
        public int cacheEnabledStatus = 0;
    }

    public static class SecondaryContent {
        private String m329t7p6390828478461;
        private int m329t7p6390828478464;
        private boolean m329t7p6390828478466;
        private int m329t7p6390828478467;

        public SecondaryContent(String str, int i, int i2, boolean z) {
            this.m329t7p6390828478461 = str;
            this.m329t7p6390828478467 = i;
            this.m329t7p6390828478464 = i2;
            this.m329t7p6390828478466 = z;
            if (i < 0) {
                this.m329t7p6390828478467 = str.hashCode();
            }
        }
    }

    protected VOSafeTVManager() {
    }

    protected void a(VOPlayer vOPlayer) {
        WeakReference<VOPlayer> weakReference = this.f89a;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f89a = new WeakReference<>(vOPlayer);
    }

    public void addSecondaryContentWhenNetworkLost(SecondaryContent secondaryContent) {
        try {
            this.f89a.get().a(secondaryContent);
        } catch (Exception unused) {
        }
    }

    public VOCachedRange[] getCachedRanges() {
        try {
            return this.f89a.get().a();
        } catch (Exception unused) {
            return null;
        }
    }

    public void prepareSafeTVSwitch() {
        try {
            this.f89a.get().d();
        } catch (Exception unused) {
        }
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        try {
            this.f89a.get().a(playbackParameters);
        } catch (Exception unused) {
        }
    }
}
