package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: dynamicTypes.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class DynamicTypesKt {
    public static final boolean isDynamic(KotlinType isDynamic) {
        Intrinsics.checkNotNullParameter(isDynamic, "$this$isDynamic");
        return isDynamic.unwrap() instanceof DynamicType;
    }
}
