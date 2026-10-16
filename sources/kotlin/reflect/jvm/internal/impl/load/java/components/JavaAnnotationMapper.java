package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: compiled from: JavaAnnotationMapper.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class JavaAnnotationMapper {
    private static final Name DEPRECATED_ANNOTATION_MESSAGE;
    private static final Name RETENTION_ANNOTATION_VALUE;
    private static final Name TARGET_ANNOTATION_ALLOWED_TARGETS;
    private static final Map<FqName, FqName> javaToKotlinNameMap;
    private static final Map<FqName, FqName> kotlinToJavaNameMap;
    public static final JavaAnnotationMapper INSTANCE = new JavaAnnotationMapper();
    private static final FqName JAVA_TARGET_FQ_NAME = new FqName(Target.class.getCanonicalName());
    private static final FqName JAVA_RETENTION_FQ_NAME = new FqName(Retention.class.getCanonicalName());
    private static final FqName JAVA_DEPRECATED_FQ_NAME = new FqName(Deprecated.class.getCanonicalName());
    private static final FqName JAVA_DOCUMENTED_FQ_NAME = new FqName(Documented.class.getCanonicalName());
    private static final FqName JAVA_REPEATABLE_FQ_NAME = new FqName("java.lang.annotation.Repeatable");

    static {
        Name nameIdentifier = Name.identifier("message");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(\"message\")");
        DEPRECATED_ANNOTATION_MESSAGE = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("allowedTargets");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier2, "Name.identifier(\"allowedTargets\")");
        TARGET_ANNOTATION_ALLOWED_TARGETS = nameIdentifier2;
        Name nameIdentifier3 = Name.identifier("value");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier3, "Name.identifier(\"value\")");
        RETENTION_ANNOTATION_VALUE = nameIdentifier3;
        kotlinToJavaNameMap = MapsKt.mapOf(TuplesKt.to(StandardNames.FqNames.target, JAVA_TARGET_FQ_NAME), TuplesKt.to(StandardNames.FqNames.retention, JAVA_RETENTION_FQ_NAME), TuplesKt.to(StandardNames.FqNames.repeatable, JAVA_REPEATABLE_FQ_NAME), TuplesKt.to(StandardNames.FqNames.mustBeDocumented, JAVA_DOCUMENTED_FQ_NAME));
        javaToKotlinNameMap = MapsKt.mapOf(TuplesKt.to(JAVA_TARGET_FQ_NAME, StandardNames.FqNames.target), TuplesKt.to(JAVA_RETENTION_FQ_NAME, StandardNames.FqNames.retention), TuplesKt.to(JAVA_DEPRECATED_FQ_NAME, StandardNames.FqNames.deprecated), TuplesKt.to(JAVA_REPEATABLE_FQ_NAME, StandardNames.FqNames.repeatable), TuplesKt.to(JAVA_DOCUMENTED_FQ_NAME, StandardNames.FqNames.mustBeDocumented));
    }

    private JavaAnnotationMapper() {
    }

    public final Name getDEPRECATED_ANNOTATION_MESSAGE$descriptors_jvm() {
        return DEPRECATED_ANNOTATION_MESSAGE;
    }

    public final Name getTARGET_ANNOTATION_ALLOWED_TARGETS$descriptors_jvm() {
        return TARGET_ANNOTATION_ALLOWED_TARGETS;
    }

    public final Name getRETENTION_ANNOTATION_VALUE$descriptors_jvm() {
        return RETENTION_ANNOTATION_VALUE;
    }

    public final AnnotationDescriptor mapOrResolveJavaAnnotation(JavaAnnotation annotation, LazyJavaResolverContext c) {
        LazyJavaAnnotationDescriptor lazyJavaAnnotationDescriptor;
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        Intrinsics.checkNotNullParameter(c, "c");
        ClassId classId = annotation.getClassId();
        if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_TARGET_FQ_NAME))) {
            lazyJavaAnnotationDescriptor = new JavaTargetAnnotationDescriptor(annotation, c);
        } else if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_RETENTION_FQ_NAME))) {
            lazyJavaAnnotationDescriptor = new JavaRetentionAnnotationDescriptor(annotation, c);
        } else if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_REPEATABLE_FQ_NAME))) {
            lazyJavaAnnotationDescriptor = new JavaAnnotationDescriptor(c, annotation, StandardNames.FqNames.repeatable);
        } else if (Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_DOCUMENTED_FQ_NAME))) {
            lazyJavaAnnotationDescriptor = new JavaAnnotationDescriptor(c, annotation, StandardNames.FqNames.mustBeDocumented);
        } else {
            lazyJavaAnnotationDescriptor = Intrinsics.areEqual(classId, ClassId.topLevel(JAVA_DEPRECATED_FQ_NAME)) ? null : new LazyJavaAnnotationDescriptor(c, annotation);
        }
        return lazyJavaAnnotationDescriptor;
    }

    public final AnnotationDescriptor findMappedJavaAnnotation(FqName kotlinName, JavaAnnotationOwner annotationOwner, LazyJavaResolverContext c) {
        JavaAnnotation javaAnnotationFindAnnotation;
        JavaAnnotation javaAnnotationFindAnnotation2;
        Intrinsics.checkNotNullParameter(kotlinName, "kotlinName");
        Intrinsics.checkNotNullParameter(annotationOwner, "annotationOwner");
        Intrinsics.checkNotNullParameter(c, "c");
        if (Intrinsics.areEqual(kotlinName, StandardNames.FqNames.deprecated) && ((javaAnnotationFindAnnotation2 = annotationOwner.findAnnotation(JAVA_DEPRECATED_FQ_NAME)) != null || annotationOwner.isDeprecatedInJavaDoc())) {
            return new JavaDeprecatedAnnotationDescriptor(javaAnnotationFindAnnotation2, c);
        }
        FqName fqName = kotlinToJavaNameMap.get(kotlinName);
        if (fqName == null || (javaAnnotationFindAnnotation = annotationOwner.findAnnotation(fqName)) == null) {
            return null;
        }
        return INSTANCE.mapOrResolveJavaAnnotation(javaAnnotationFindAnnotation, c);
    }
}
