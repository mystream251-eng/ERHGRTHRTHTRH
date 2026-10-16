package com.dotscreen.bokit.model;

import androidx.core.app.NotificationCompat;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.PVRStorageMode;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.reactivex.Maybe;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TVRecording.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0016\u0018\u00002\u00020\u0001B¯\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0010\b\u0002\u0010\u0017\u001a\n\u0018\u00010\u0018j\u0004\u0018\u0001`\u0019\u0012\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b\u0012\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001b\u0012\b\b\u0002\u0010\u001f\u001a\u00020 ¢\u0006\u0002\u0010!R\u0019\u0010\u0017\u001a\n\u0018\u00010\u0018j\u0004\u0018\u0001`\u0019¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)¨\u0006*"}, d2 = {"Lcom/dotscreen/bokit/model/TVRecording;", "Lcom/dotscreen/bokit/model/TVAiring;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "summary", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "deepLink", TtmlNode.TAG_METADATA, "Lcom/dotscreen/bokit/model/MediaMetadata;", "startDate", "Ljava/util/Date;", "endDate", "channel", "Lcom/dotscreen/bokit/model/Channel;", "storageMode", "Lcom/dotscreen/bokit/services/PVRStorageMode;", NotificationCompat.CATEGORY_STATUS, "Lcom/dotscreen/bokit/model/TVRecordingStatus;", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "tvProgram", "Lio/reactivex/Maybe;", "Lcom/dotscreen/bokit/model/TVProgram;", "media", "Lcom/dotscreen/bokit/model/Media;", "ratable", "", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/dotscreen/bokit/model/MediaMetadata;Ljava/util/Date;Ljava/util/Date;Lcom/dotscreen/bokit/model/Channel;Lcom/dotscreen/bokit/services/PVRStorageMode;Lcom/dotscreen/bokit/model/TVRecordingStatus;Ljava/lang/Exception;Lio/reactivex/Maybe;Lio/reactivex/Maybe;Z)V", "getError", "()Ljava/lang/Exception;", "getMedia", "()Lio/reactivex/Maybe;", "getStatus", "()Lcom/dotscreen/bokit/model/TVRecordingStatus;", "getStorageMode", "()Lcom/dotscreen/bokit/services/PVRStorageMode;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class TVRecording extends TVAiring {
    private final Exception error;
    private final Maybe<Media> media;
    private final TVRecordingStatus status;
    private final PVRStorageMode storageMode;

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TVRecording(BOService bOService, String str, String str2, String str3, List list, String str4, MediaMetadata mediaMetadata, Date date, Date date2, Channel channel, PVRStorageMode pVRStorageMode, TVRecordingStatus tVRecordingStatus, Exception exc, Maybe maybe, Maybe maybe2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Maybe maybe3;
        Maybe maybe4;
        List listEmptyList = (i & 16) != 0 ? CollectionsKt.emptyList() : list;
        String str5 = (i & 32) != 0 ? (String) null : str4;
        Exception exc2 = (i & 4096) != 0 ? (Exception) null : exc;
        if ((i & 8192) != 0) {
            Maybe maybeEmpty = Maybe.empty();
            Intrinsics.checkNotNullExpressionValue(maybeEmpty, "Maybe.empty()");
            maybe3 = maybeEmpty;
        } else {
            maybe3 = maybe;
        }
        if ((i & 16384) != 0) {
            Maybe maybeEmpty2 = Maybe.empty();
            Intrinsics.checkNotNullExpressionValue(maybeEmpty2, "Maybe.empty()");
            maybe4 = maybeEmpty2;
        } else {
            maybe4 = maybe2;
        }
        this(bOService, str, str2, str3, listEmptyList, str5, mediaMetadata, date, date2, channel, pVRStorageMode, tVRecordingStatus, exc2, maybe3, maybe4, (i & 32768) != 0 ? false : z);
    }

    public final PVRStorageMode getStorageMode() {
        return this.storageMode;
    }

    public final TVRecordingStatus getStatus() {
        return this.status;
    }

    public final Exception getError() {
        return this.error;
    }

    public final Maybe<Media> getMedia() {
        return this.media;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TVRecording(BOService boService, String id, String title, String str, List<? extends Artwork> artworks, String str2, MediaMetadata metadata, Date startDate, Date endDate, Channel channel, PVRStorageMode storageMode, TVRecordingStatus status, Exception exc, Maybe<TVProgram> tvProgram, Maybe<Media> media, boolean z) {
        super(boService, id, title, str, artworks, str2, metadata, startDate, endDate, channel, tvProgram, z, false, false, 12288, null);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworks, "artworks");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.checkNotNullParameter(storageMode, "storageMode");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(tvProgram, "tvProgram");
        Intrinsics.checkNotNullParameter(media, "media");
        this.storageMode = storageMode;
        this.status = status;
        this.error = exc;
        this.media = media;
    }
}
