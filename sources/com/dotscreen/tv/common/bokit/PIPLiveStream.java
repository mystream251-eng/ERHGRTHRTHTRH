package com.dotscreen.tv.common.bokit;

import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.services.BOService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/dotscreen/tv/common/bokit/PIPLiveStream;", "Lcom/dotscreen/bokit/model/Media;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "channel", "Lcom/dotscreen/tv/common/bokit/RightVChannel;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Lcom/dotscreen/tv/common/bokit/RightVChannel;)V", "getChannel", "()Lcom/dotscreen/tv/common/bokit/RightVChannel;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PIPLiveStream extends Media {
    private final RightVChannel channel;

    public final RightVChannel getChannel() {
        return this.channel;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PIPLiveStream(BOService boService, String id, String title, RightVChannel channel) {
        super(boService, id, title, null, null, null, false, null, null, 504, null);
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        this.channel = channel;
    }
}
