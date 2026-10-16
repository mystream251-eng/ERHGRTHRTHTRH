package com.dotscreen.bokit.model;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PlaybackInfo.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\u0002\u0010\bJ\b\u0010\u000e\u001a\u00020\u0007H\u0016R\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/bokit/model/PlaybackInfo;", "", "watchedTime", "", "urls", "", "Lcom/dotscreen/bokit/model/PlaybackInfoMimeType;", "", "(Ljava/lang/Long;Ljava/util/Map;)V", "getUrls", "()Ljava/util/Map;", "getWatchedTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "toString", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class PlaybackInfo {
    private final Map<PlaybackInfoMimeType, String> urls;
    private final Long watchedTime;

    public PlaybackInfo(Long l, Map<PlaybackInfoMimeType, String> map) {
        this.watchedTime = l;
        this.urls = map;
    }

    public /* synthetic */ PlaybackInfo(Long l, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(l, (i & 2) != 0 ? (Map) null : map);
    }

    public final Map<PlaybackInfoMimeType, String> getUrls() {
        return this.urls;
    }

    public final Long getWatchedTime() {
        return this.watchedTime;
    }

    public String toString() {
        return '[' + getClass().getSimpleName() + "] watchedTime: " + this.watchedTime + ", urls: " + this.urls;
    }
}
