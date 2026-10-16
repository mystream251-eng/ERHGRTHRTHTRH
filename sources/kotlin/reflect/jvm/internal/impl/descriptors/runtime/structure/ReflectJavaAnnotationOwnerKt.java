package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: compiled from: ReflectJavaAnnotationOwner.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class ReflectJavaAnnotationOwnerKt {
    public static final List<ReflectJavaAnnotation> getAnnotations(Annotation[] getAnnotations) {
        Intrinsics.checkNotNullParameter(getAnnotations, "$this$getAnnotations");
        ArrayList arrayList = new ArrayList(getAnnotations.length);
        for (Annotation annotation : getAnnotations) {
            arrayList.add(new ReflectJavaAnnotation(annotation));
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002e  */
    /* JADX WARN: Code duplicated, block: B:15:? A[RETURN, SYNTHETIC] */
    public static final ReflectJavaAnnotation findAnnotation(Annotation[] findAnnotation, FqName fqName) {
        Intrinsics.checkNotNullParameter(findAnnotation, "$this$findAnnotation");
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        for (Annotation annotation : findAnnotation) {
            if (Intrinsics.areEqual(ReflectClassUtilKt.getClassId(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation))).asSingleFqName(), fqName)) {
                if (annotation != null) {
                    return new ReflectJavaAnnotation(annotation);
                }
                return null;
            }
        }
        annotation = null;
        if (annotation != null) {
            return new ReflectJavaAnnotation(annotation);
        }
        return null;
    }
}
