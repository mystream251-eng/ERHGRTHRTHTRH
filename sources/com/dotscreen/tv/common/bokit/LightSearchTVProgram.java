package com.dotscreen.tv.common.bokit;

import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.services.BOService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0006H\u0016¨\u0006\n"}, d2 = {"Lcom/dotscreen/tv/common/bokit/LightSearchTVProgram;", "Lcom/dotscreen/bokit/model/Content;", "Lcom/dotscreen/tv/common/bokit/SearchableContent;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;)V", "getSearchId", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public class LightSearchTVProgram extends Content implements SearchableContent {
    @Override // com.dotscreen.tv.common.bokit.SearchableContent
    /* JADX INFO: renamed from: getSearchId */
    public String getContentSearchId() {
        return "";
    }

    @Override // com.dotscreen.tv.common.bokit.SearchableContent
    public String getEntityType() {
        return SearchableContent.DefaultImpls.getEntityType(this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LightSearchTVProgram(BOService boService, String id, String title) {
        super(boService, id, title, null, null, null, new MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 65535, null), false, null, null, 944, null);
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(title, "title");
    }
}
