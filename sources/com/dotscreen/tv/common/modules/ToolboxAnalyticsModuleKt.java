package com.dotscreen.tv.common.modules;

import kotlin.Metadata;

/* JADX INFO: compiled from: ToolboxAnalyticsModule.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001c\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"EVENT_CLOSE", "", "EVENT_COMPLETE", "EVENT_ENDED", "EVENT_FIRSTQUARTILE", "EVENT_LOGIN", "EVENT_MIDPOINT", "EVENT_PAUSE", "EVENT_PLAYBACK_ERROR", "EVENT_PROFILE_ID", "EVENT_PROGRESSMARK", "EVENT_RESUME", "EVENT_RE_BUFFERING", "EVENT_START", "EVENT_START_BUFFERING", "EVENT_STREAMING_PROGRESS", "EVENT_THIRDQUARTILE", "KEY_AUTH_HBO", "KEY_BUFFER_STATS", "KEY_CONTENT", "KEY_CONTENT_ID", "KEY_DRM_TYPE", "KEY_EVENTS", "KEY_FORMAT_TYPE", "KEY_PLAYBACK", "KEY_PLAY_BACK_URL", "KEY_USER", "M_HEIGHT", "M_WIDTH", "common_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class ToolboxAnalyticsModuleKt {
    public static final String EVENT_CLOSE = "close";
    public static final String EVENT_COMPLETE = "complete";
    public static final String EVENT_ENDED = "ended";
    public static final String EVENT_FIRSTQUARTILE = "firstquartile";
    public static final String EVENT_LOGIN = "Login";
    public static final String EVENT_MIDPOINT = "midpoint";
    public static final String EVENT_PAUSE = "pause";
    public static final String EVENT_PLAYBACK_ERROR = "playbackError";
    public static final String EVENT_PROFILE_ID = "profileId";
    public static final String EVENT_PROGRESSMARK = "progressmark";
    public static final String EVENT_RESUME = "resume";
    public static final String EVENT_RE_BUFFERING = "re-buffering";
    public static final String EVENT_START = "start";
    public static final String EVENT_START_BUFFERING = "start-buffering";
    public static final String EVENT_STREAMING_PROGRESS = "StreamingProgress";
    public static final String EVENT_THIRDQUARTILE = "thirdquartile";
    public static final String KEY_AUTH_HBO = "hboAuthzToken";
    public static final String KEY_BUFFER_STATS = "bufferStats";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_CONTENT_ID = "id";
    public static final String KEY_DRM_TYPE = "drmType";
    public static final String KEY_EVENTS = "events";
    public static final String KEY_FORMAT_TYPE = "formatType";
    public static final String KEY_PLAYBACK = "playback";
    public static final String KEY_PLAY_BACK_URL = "playbackUrl";
    public static final String KEY_USER = "user";
    public static final String M_HEIGHT = "height";
    public static final String M_WIDTH = "width";
}
