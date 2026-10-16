package com.dotscreen.tv.common.bokit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002J\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/dotscreen/tv/common/bokit/ProfileList;", "Ljava/util/ArrayList;", "Lcom/dotscreen/tv/common/bokit/Profile;", "profiles", "", "(Ljava/util/List;)V", "getMainProfile", "hasMainProfile", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ProfileList extends ArrayList<Profile> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileList(List<Profile> profiles) {
        super(profiles);
        Intrinsics.checkParameterIsNotNull(profiles, "profiles");
    }

    public /* bridge */ boolean contains(Profile profile) {
        return super.contains((Object) profile);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Profile) {
            return contains((Profile) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(Profile profile) {
        return super.indexOf((Object) profile);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Profile) {
            return indexOf((Profile) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(Profile profile) {
        return super.lastIndexOf((Object) profile);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Profile) {
            return lastIndexOf((Profile) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ Profile remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(Profile profile) {
        return super.remove((Object) profile);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof Profile) {
            return remove((Profile) obj);
        }
        return false;
    }

    public /* bridge */ Profile removeAt(int i) {
        return (Profile) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0020 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:15:? A[RETURN, SYNTHETIC] */
    public final boolean hasMainProfile() {
        Iterator<Profile> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().isMainProfile()) {
                if (i != -1) {
                    return true;
                }
                return false;
            }
            i++;
        }
        i = -1;
        if (i != -1) {
            return true;
        }
        return false;
    }

    public final Profile getMainProfile() {
        Profile next;
        Iterator<Profile> it = iterator();
        while (it.hasNext()) {
            next = it.next();
            if (next.isMainProfile()) {
                return next;
            }
        }
        next = null;
        return next;
    }
}
