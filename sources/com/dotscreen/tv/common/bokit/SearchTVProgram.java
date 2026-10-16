package com.dotscreen.tv.common.bokit;

import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.services.BOService;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00152\u00020\u00012\u00020\u0002:\u0001\u0015BU\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0006H\u0016R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/tv/common/bokit/SearchTVProgram;", "Lcom/dotscreen/bokit/model/TVAiring;", "Lcom/dotscreen/tv/common/bokit/SearchableContent;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", TtmlNode.TAG_METADATA, "Lcom/dotscreen/bokit/model/MediaMetadata;", "channel", "Lcom/dotscreen/bokit/model/Channel;", "startDate", "Ljava/util/Date;", "endDate", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "contentSearchId", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Lcom/dotscreen/bokit/model/MediaMetadata;Lcom/dotscreen/bokit/model/Channel;Ljava/util/Date;Ljava/util/Date;Ljava/util/List;Ljava/lang/String;)V", "getSearchId", "Companion", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public class SearchTVProgram extends TVAiring implements SearchableContent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String contentSearchId;

    @Override // com.dotscreen.tv.common.bokit.SearchableContent
    public String getEntityType() {
        return SearchableContent.DefaultImpls.getEntityType(this);
    }

    public /* synthetic */ SearchTVProgram(BOService bOService, String str, String str2, MediaMetadata mediaMetadata, Channel channel, Date date, Date date2, List list, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bOService, str, str2, mediaMetadata, channel, date, date2, (i & 128) != 0 ? CollectionsKt.emptyList() : list, str3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchTVProgram(BOService boService, String id, String title, MediaMetadata metadata, Channel channel, Date startDate, Date endDate, List<? extends Artwork> artworks, String contentSearchId) {
        super(boService, id, title, null, artworks, null, metadata, startDate, endDate, channel, null, false, false, false, 15392, null);
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(metadata, "metadata");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        Intrinsics.checkParameterIsNotNull(artworks, "artworks");
        Intrinsics.checkParameterIsNotNull(contentSearchId, "contentSearchId");
        this.contentSearchId = contentSearchId;
    }

    @Override // com.dotscreen.tv.common.bokit.SearchableContent
    /* JADX INFO: renamed from: getSearchId, reason: from getter */
    public String getContentSearchId() {
        return this.contentSearchId;
    }

    /* JADX INFO: compiled from: MegacableUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¨\u0006\t"}, d2 = {"Lcom/dotscreen/tv/common/bokit/SearchTVProgram$Companion;", "", "()V", "addArtworks", "Lcom/dotscreen/tv/common/bokit/SearchTVProgram;", "o", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SearchTVProgram addArtworks(SearchTVProgram o, List<? extends Artwork> artworks) {
            Intrinsics.checkParameterIsNotNull(o, "o");
            Intrinsics.checkParameterIsNotNull(artworks, "artworks");
            return new SearchTVProgram(o.getBoService(), o.getId(), o.getTitle(), o.getMetadata(), o.getChannel(), o.getStartDate(), o.getEndDate(), artworks, o.contentSearchId);
        }
    }
}
