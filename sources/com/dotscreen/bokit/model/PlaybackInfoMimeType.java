package com.dotscreen.bokit.model;

import com.google.android.exoplayer2.util.MimeTypes;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaybackInfo.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/dotscreen/bokit/model/PlaybackInfoMimeType;", "", "mime", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getMime", "()Ljava/lang/String;", "DASH", "HLS", "SMOOTHSTREAMING", "MULTICAST", "DEFAULT", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public enum PlaybackInfoMimeType {
    DASH(MimeTypes.APPLICATION_MPD),
    HLS("application/vnd.apple.mpegurl"),
    SMOOTHSTREAMING(MimeTypes.APPLICATION_SS),
    MULTICAST("multicast"),
    DEFAULT("default");

    private final String mime;

    PlaybackInfoMimeType(String str) {
        this.mime = str;
    }

    public final String getMime() {
        return this.mime;
    }
}
