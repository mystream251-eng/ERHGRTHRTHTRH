package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReflectJavaClassFinder.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class ReflectJavaClassFinderKt {
    public static final Class<?> tryLoadClass(ClassLoader tryLoadClass, String fqName) {
        Intrinsics.checkNotNullParameter(tryLoadClass, "$this$tryLoadClass");
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        try {
            return Class.forName(fqName, false, tryLoadClass);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
