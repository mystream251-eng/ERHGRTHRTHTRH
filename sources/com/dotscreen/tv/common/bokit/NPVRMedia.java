package com.dotscreen.tv.common.bokit;

import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.services.BOService;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/tv/common/bokit/NPVRMedia;", "Lcom/dotscreen/bokit/model/Media;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "otfRecordingId", "", "channelExternalId", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getChannelExternalId", "()Ljava/lang/String;", "getOtfRecordingId", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class NPVRMedia extends Media {
    private final String channelExternalId;
    private final String otfRecordingId;

    public final String getOtfRecordingId() {
        return this.otfRecordingId;
    }

    public final String getChannelExternalId() {
        return this.channelExternalId;
    }

    public /* synthetic */ NPVRMedia(BOService bOService, String str, String str2, String str3, String str4, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bOService, str, str2, str3, str4, (i & 32) != 0 ? CollectionsKt.emptyList() : list);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NPVRMedia(BOService boService, String otfRecordingId, String channelExternalId, String title, String str, List<? extends Artwork> artworks) {
        super(boService, otfRecordingId, title, str, artworks, null, false, null, null, 480, null);
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(otfRecordingId, "otfRecordingId");
        Intrinsics.checkParameterIsNotNull(channelExternalId, "channelExternalId");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(artworks, "artworks");
        this.otfRecordingId = otfRecordingId;
        this.channelExternalId = channelExternalId;
    }
}
