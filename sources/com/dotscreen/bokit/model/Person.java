package com.dotscreen.bokit.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Person.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/dotscreen/bokit/model/Person;", "Lcom/dotscreen/bokit/model/Item;", "id", "", "title", "summary", "portrait", "Lcom/dotscreen/bokit/model/Artwork;", "ratable", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/dotscreen/bokit/model/Artwork;Z)V", "getPortrait", "()Lcom/dotscreen/bokit/model/Artwork;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class Person extends Item {
    private final Artwork portrait;

    public /* synthetic */ Person(String str, String str2, String str3, Artwork artwork, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (Artwork) null : artwork, (i & 16) != 0 ? false : z);
    }

    public final Artwork getPortrait() {
        return this.portrait;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Person(String id, String title, String str, Artwork artwork, boolean z) {
        super(id, title, str, z, null, 16, null);
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        this.portrait = artwork;
    }
}
