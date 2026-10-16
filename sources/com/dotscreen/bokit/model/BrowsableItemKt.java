package com.dotscreen.bokit.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrowsableItem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"isYoutubeContent", "", "Lcom/dotscreen/bokit/model/BrowsableItem;", "bokit-android_release"}, k = 2, mv = {1, 4, 1})
public final class BrowsableItemKt {
    public static final boolean isYoutubeContent(BrowsableItem isYoutubeContent) {
        Intrinsics.checkNotNullParameter(isYoutubeContent, "$this$isYoutubeContent");
        return isYoutubeContent.getExternalIds().get(ItemExternalIdScheme.Youtube) != null;
    }
}
