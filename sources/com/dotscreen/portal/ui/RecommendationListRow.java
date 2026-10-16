package com.dotscreen.portal.ui;

import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ObjectAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecommendationListRow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/ui/RecommendationListRow;", "Landroidx/leanback/widget/ListRow;", "id", "", "header", "Landroidx/leanback/widget/HeaderItem;", "adapter", "Landroidx/leanback/widget/ObjectAdapter;", "(JLandroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V", "getBlend", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class RecommendationListRow extends ListRow {
    public abstract String getBlend();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecommendationListRow(long j, HeaderItem headerItem, ObjectAdapter adapter) {
        super(j, headerItem, adapter);
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
    }
}
