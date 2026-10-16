package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

/* JADX INFO: compiled from: KotlinClassFinder.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class KotlinClassFinderKt {
    public static final KotlinJvmBinaryClass findKotlinClass(KotlinClassFinder findKotlinClass, ClassId classId) {
        Intrinsics.checkNotNullParameter(findKotlinClass, "$this$findKotlinClass");
        Intrinsics.checkNotNullParameter(classId, "classId");
        KotlinClassFinder.Result resultFindKotlinClassOrContent = findKotlinClass.findKotlinClassOrContent(classId);
        if (resultFindKotlinClassOrContent != null) {
            return resultFindKotlinClassOrContent.toKotlinJvmBinaryClass();
        }
        return null;
    }

    public static final KotlinJvmBinaryClass findKotlinClass(KotlinClassFinder findKotlinClass, JavaClass javaClass) {
        Intrinsics.checkNotNullParameter(findKotlinClass, "$this$findKotlinClass");
        Intrinsics.checkNotNullParameter(javaClass, "javaClass");
        KotlinClassFinder.Result resultFindKotlinClassOrContent = findKotlinClass.findKotlinClassOrContent(javaClass);
        if (resultFindKotlinClassOrContent != null) {
            return resultFindKotlinClassOrContent.toKotlinJvmBinaryClass();
        }
        return null;
    }
}
