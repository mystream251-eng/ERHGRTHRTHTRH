package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleCapability;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: compiled from: KotlinTypeRefiner.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class KotlinTypeRefinerKt {
    private static final ModuleCapability<Ref<KotlinTypeRefiner>> REFINER_CAPABILITY = new ModuleCapability<>("KotlinTypeRefiner");

    public static final ModuleCapability<Ref<KotlinTypeRefiner>> getREFINER_CAPABILITY() {
        return REFINER_CAPABILITY;
    }

    public static final List<KotlinType> refineTypes(KotlinTypeRefiner refineTypes, Iterable<? extends KotlinType> types) {
        Intrinsics.checkNotNullParameter(refineTypes, "$this$refineTypes");
        Intrinsics.checkNotNullParameter(types, "types");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(types, 10));
        Iterator<? extends KotlinType> it = types.iterator();
        while (it.hasNext()) {
            arrayList.add(refineTypes.refineType(it.next()));
        }
        return arrayList;
    }
}
