package com.dotscreen.bokit.internal.parsers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProgramParser.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/bokit/internal/parsers/Rerun;", "", "channelId", "", "airingTime", "", "(Ljava/lang/String;J)V", "getAiringTime", "()J", "getChannelId", "()Ljava/lang/String;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class Rerun {
    private final long airingTime;
    private final String channelId;

    public Rerun(String channelId, long j) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        this.channelId = channelId;
        this.airingTime = j;
    }

    public final long getAiringTime() {
        return this.airingTime;
    }

    public final String getChannelId() {
        return this.channelId;
    }
}
