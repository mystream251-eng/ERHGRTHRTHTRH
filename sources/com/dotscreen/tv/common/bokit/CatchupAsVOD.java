package com.dotscreen.tv.common.bokit;

import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.services.BOService;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/tv/common/bokit/CatchupAsVOD;", "Lcom/dotscreen/bokit/model/Content;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", TtmlNode.TAG_METADATA, "Lcom/dotscreen/bokit/model/MediaMetadata;", "channel", "Lcom/dotscreen/bokit/model/Channel;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/dotscreen/bokit/model/MediaMetadata;Lcom/dotscreen/bokit/model/Channel;)V", "getChannel", "()Lcom/dotscreen/bokit/model/Channel;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CatchupAsVOD extends Content {
    private final Channel channel;

    public final Channel getChannel() {
        return this.channel;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatchupAsVOD(BOService boService, String id, String title, List<? extends Artwork> artworks, MediaMetadata metadata, Channel channel) {
        super(boService, id, title, null, artworks, null, metadata, false, null, null, 896, null);
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(artworks, "artworks");
        Intrinsics.checkParameterIsNotNull(metadata, "metadata");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        this.channel = channel;
    }
}
