package com.dotscreen.bokit.model;

import com.dotscreen.bokit.services.BOService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Artwork.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/dotscreen/bokit/model/AmazinChannelArtwork;", "Lcom/dotscreen/bokit/model/AmazinArtwork;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "channelId", "", "format", "Lcom/dotscreen/bokit/model/ArtworkFormat;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Lcom/dotscreen/bokit/model/ArtworkFormat;)V", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class AmazinChannelArtwork extends AmazinArtwork {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AmazinChannelArtwork(BOService boService, String channelId, ArtworkFormat format) {
        super(boService, "/api/mobile/v1/channels/" + channelId + "/{width}x{height}.png", format);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        Intrinsics.checkNotNullParameter(format, "format");
    }
}
