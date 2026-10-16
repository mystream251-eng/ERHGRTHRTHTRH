package com.dotscreen.bokit.model;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Classifiers.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/bokit/model/ContentRatingClassifier;", "Lcom/dotscreen/bokit/model/Classifier;", "id", "", "title", "summary", "level", "", "ratable", "", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/util/Map;)V", "getLevel", "()I", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class ContentRatingClassifier extends Classifier {
    private final int level;

    public /* synthetic */ ContentRatingClassifier(String str, String str2, String str3, int i, boolean z, Map map, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? (String) null : str3, i, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? MapsKt.emptyMap() : map);
    }

    public final int getLevel() {
        return this.level;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentRatingClassifier(String id, String title, String str, int i, boolean z, Map<ItemExternalIdScheme, String> externalIds) {
        super(id, title, str, z, externalIds);
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(externalIds, "externalIds");
        this.level = i;
    }
}
