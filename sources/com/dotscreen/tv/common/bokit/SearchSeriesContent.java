package com.dotscreen.tv.common.bokit;

import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.services.BOService;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B=\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0006H\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/tv/common/bokit/SearchSeriesContent;", "Lcom/dotscreen/bokit/model/Content;", "Lcom/dotscreen/tv/common/bokit/SearchableContent;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", TtmlNode.TAG_METADATA, "Lcom/dotscreen/bokit/model/MediaMetadata;", "contentSearchId", "artworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;Lcom/dotscreen/bokit/model/MediaMetadata;Ljava/lang/String;Ljava/util/List;)V", "getEntityType", "getSearchId", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public class SearchSeriesContent extends Content implements SearchableContent {
    private final String contentSearchId;

    @Override // com.dotscreen.tv.common.bokit.SearchableContent
    public String getEntityType() {
        return "content_series";
    }

    public /* synthetic */ SearchSeriesContent(BOService bOService, String str, String str2, MediaMetadata mediaMetadata, String str3, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bOService, str, str2, mediaMetadata, str3, (i & 32) != 0 ? CollectionsKt.emptyList() : list);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchSeriesContent(BOService boService, String id, String title, MediaMetadata metadata, String contentSearchId, List<? extends Artwork> artworks) {
        super(boService, id, title, null, artworks, null, metadata, false, null, null, 928, null);
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(metadata, "metadata");
        Intrinsics.checkParameterIsNotNull(contentSearchId, "contentSearchId");
        Intrinsics.checkParameterIsNotNull(artworks, "artworks");
        this.contentSearchId = contentSearchId;
    }

    @Override // com.dotscreen.tv.common.bokit.SearchableContent
    /* JADX INFO: renamed from: getSearchId, reason: from getter */
    public String getContentSearchId() {
        return this.contentSearchId;
    }
}
