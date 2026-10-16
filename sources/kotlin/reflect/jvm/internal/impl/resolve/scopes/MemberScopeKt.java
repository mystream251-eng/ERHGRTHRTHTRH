package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: compiled from: MemberScope.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class MemberScopeKt {
    public static final Set<Name> flatMapClassifierNamesOrNull(Iterable<? extends MemberScope> flatMapClassifierNamesOrNull) {
        Intrinsics.checkNotNullParameter(flatMapClassifierNamesOrNull, "$this$flatMapClassifierNamesOrNull");
        HashSet hashSet = new HashSet();
        Iterator<? extends MemberScope> it = flatMapClassifierNamesOrNull.iterator();
        while (it.hasNext()) {
            Set<Name> classifierNames = it.next().getClassifierNames();
            if (classifierNames == null) {
                hashSet = null;
                break;
            }
            CollectionsKt.addAll(hashSet, classifierNames);
        }
        return hashSet;
    }
}
