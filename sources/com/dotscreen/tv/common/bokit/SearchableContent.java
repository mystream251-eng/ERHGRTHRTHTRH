package com.dotscreen.tv.common.bokit;

import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/tv/common/bokit/SearchableContent;", "", "getEntityType", "", "getSearchId", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public interface SearchableContent {

    /* JADX INFO: compiled from: MegacableUtils.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static String getEntityType(SearchableContent searchableContent) {
            return ToolboxAnalyticsModuleKt.KEY_CONTENT;
        }
    }

    String getEntityType();

    String getSearchId();
}
