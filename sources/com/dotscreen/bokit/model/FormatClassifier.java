package com.dotscreen.bokit.model;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Classifiers.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/dotscreen/bokit/model/FormatClassifier;", "Lcom/dotscreen/bokit/model/Classifier;", "id", "", "title", "summary", "ratable", "", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/Map;)V", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class FormatClassifier extends Classifier {
    public /* synthetic */ FormatClassifier(String str, String str2, String str3, boolean z, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? false : z, (i & 16) != 0 ? MapsKt.emptyMap() : map);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormatClassifier(String id, String title, String str, boolean z, Map<ItemExternalIdScheme, String> externalIds) {
        super(id, title, str, z, externalIds);
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(externalIds, "externalIds");
    }
}
