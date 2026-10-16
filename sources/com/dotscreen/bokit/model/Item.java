package com.dotscreen.bokit.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Item.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0002\u0010\u000bJ\u001d\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u001eJ\u0013\u0010\u001f\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0010\u0010!\u001a\u0004\u0018\u00010\u00032\u0006\u0010\"\u001a\u00020\nJ\u0010\u0010#\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001c\u001a\u00020\u0003J\b\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\u0003H\u0016R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015¨\u0006'"}, d2 = {"Lcom/dotscreen/bokit/model/Item;", "", "id", "", "title", "summary", "ratable", "", "externalIds", "", "Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/Map;)V", "getExternalIds", "()Ljava/util/Map;", "extraKeys", "", "getExtraKeys", "()Ljava/util/Set;", "extras", "", "getId", "()Ljava/lang/String;", "getRatable", "()Z", "getSummary", "getTitle", "addExtra", "", "key", "value", "addExtra$bokit_android_release", "equals", "other", "externalId", "scheme", "extra", "hashCode", "", "toString", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class Item {
    private final Map<ItemExternalIdScheme, String> externalIds;
    private final Map<String, Object> extras;
    private final String id;
    private final boolean ratable;
    private final String summary;
    private final String title;

    public Item(String id, String title, String str, boolean z, Map<ItemExternalIdScheme, String> externalIds) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(externalIds, "externalIds");
        this.id = id;
        this.title = title;
        this.summary = str;
        this.ratable = z;
        this.externalIds = externalIds;
        this.extras = new LinkedHashMap();
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public /* synthetic */ Item(String str, String str2, String str3, boolean z, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? false : z, (i & 16) != 0 ? MapsKt.emptyMap() : map);
    }

    public final String getSummary() {
        return this.summary;
    }

    public final boolean getRatable() {
        return this.ratable;
    }

    public final Map<ItemExternalIdScheme, String> getExternalIds() {
        return this.externalIds;
    }

    public final Set<String> getExtraKeys() {
        return this.extras.keySet();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Item)) {
            return false;
        }
        Item item = (Item) other;
        return Intrinsics.areEqual(item.id, this.id) && Intrinsics.areEqual(item.title, this.title) && Intrinsics.areEqual(item.summary, this.summary);
    }

    public int hashCode() {
        return Objects.hash(this.id, this.title, this.summary);
    }

    public String toString() {
        return '[' + getClass().getSimpleName() + "]: #" + this.id + ", " + this.title;
    }

    public final Object extra(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.extras.get(key);
    }

    public final void addExtra$bokit_android_release(String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.extras.put(key, value);
    }

    public final String externalId(ItemExternalIdScheme scheme) {
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        return this.externalIds.get(scheme);
    }
}
