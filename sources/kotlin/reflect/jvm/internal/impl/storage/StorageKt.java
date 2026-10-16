package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: storage.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class StorageKt {
    public static final <T> T getValue(NotNullLazyValue<? extends T> getValue, Object obj, KProperty<?> p) {
        Intrinsics.checkNotNullParameter(getValue, "$this$getValue");
        Intrinsics.checkNotNullParameter(p, "p");
        return getValue.invoke();
    }

    public static final <T> T getValue(NullableLazyValue<? extends T> getValue, Object obj, KProperty<?> p) {
        Intrinsics.checkNotNullParameter(getValue, "$this$getValue");
        Intrinsics.checkNotNullParameter(p, "p");
        return getValue.invoke();
    }
}
