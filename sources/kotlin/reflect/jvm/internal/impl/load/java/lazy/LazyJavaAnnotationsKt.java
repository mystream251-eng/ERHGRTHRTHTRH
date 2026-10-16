package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;

/* JADX INFO: compiled from: LazyJavaAnnotations.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class LazyJavaAnnotationsKt {
    public static final Annotations resolveAnnotations(LazyJavaResolverContext resolveAnnotations, JavaAnnotationOwner annotationsOwner) {
        Intrinsics.checkNotNullParameter(resolveAnnotations, "$this$resolveAnnotations");
        Intrinsics.checkNotNullParameter(annotationsOwner, "annotationsOwner");
        return new LazyJavaAnnotations(resolveAnnotations, annotationsOwner);
    }
}
