package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMapper;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext;
import kotlin.reflect.jvm.internal.impl.types.checker.SimpleClassicTypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;

/* JADX INFO: compiled from: typeEnhancement.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class TypeEnhancementKt {
    private static final EnhancedTypeAnnotations ENHANCED_MUTABILITY_ANNOTATIONS;
    private static final EnhancedTypeAnnotations ENHANCED_NULLABILITY_ANNOTATIONS;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[MutabilityQualifier.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MutabilityQualifier.READ_ONLY.ordinal()] = 1;
            $EnumSwitchMapping$0[MutabilityQualifier.MUTABLE.ordinal()] = 2;
            int[] iArr2 = new int[NullabilityQualifier.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[NullabilityQualifier.NULLABLE.ordinal()] = 1;
            $EnumSwitchMapping$1[NullabilityQualifier.NOT_NULL.ordinal()] = 2;
        }
    }

    public static final boolean hasEnhancedNullability(KotlinType hasEnhancedNullability) {
        Intrinsics.checkNotNullParameter(hasEnhancedNullability, "$this$hasEnhancedNullability");
        return hasEnhancedNullability(SimpleClassicTypeSystemContext.INSTANCE, hasEnhancedNullability);
    }

    public static final boolean hasEnhancedNullability(TypeSystemCommonBackendContext hasEnhancedNullability, KotlinTypeMarker type) {
        Intrinsics.checkNotNullParameter(hasEnhancedNullability, "$this$hasEnhancedNullability");
        Intrinsics.checkNotNullParameter(type, "type");
        FqName fqName = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
        Intrinsics.checkNotNullExpressionValue(fqName, "JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION");
        return hasEnhancedNullability.hasAnnotation(type, fqName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Annotations compositeAnnotationsOrSingle(List<? extends Annotations> list) {
        int size = list.size();
        if (size == 0) {
            throw new IllegalStateException("At least one Annotations object expected".toString());
        }
        if (size == 1) {
            return (Annotations) CollectionsKt.single((List) list);
        }
        return new CompositeAnnotations((List<? extends Annotations>) CollectionsKt.toList(list));
    }

    public static final boolean shouldEnhance(TypeComponentPosition shouldEnhance) {
        Intrinsics.checkNotNullParameter(shouldEnhance, "$this$shouldEnhance");
        return shouldEnhance != TypeComponentPosition.INFLEXIBLE;
    }

    private static final <T> EnhancementResult<T> noChange(T t) {
        return new EnhancementResult<>(t, null);
    }

    private static final <T> EnhancementResult<T> enhancedNullability(T t) {
        return new EnhancementResult<>(t, ENHANCED_NULLABILITY_ANNOTATIONS);
    }

    private static final <T> EnhancementResult<T> enhancedMutability(T t) {
        return new EnhancementResult<>(t, ENHANCED_MUTABILITY_ANNOTATIONS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EnhancementResult<ClassifierDescriptor> enhanceMutability(ClassifierDescriptor classifierDescriptor, JavaTypeQualifiers javaTypeQualifiers, TypeComponentPosition typeComponentPosition) {
        if (shouldEnhance(typeComponentPosition) && (classifierDescriptor instanceof ClassDescriptor)) {
            JavaToKotlinClassMapper javaToKotlinClassMapper = JavaToKotlinClassMapper.INSTANCE;
            MutabilityQualifier mutability = javaTypeQualifiers.getMutability();
            if (mutability != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[mutability.ordinal()];
                if (i == 1) {
                    if (typeComponentPosition == TypeComponentPosition.FLEXIBLE_LOWER) {
                        ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptor;
                        if (javaToKotlinClassMapper.isMutable(classDescriptor)) {
                            return enhancedMutability(javaToKotlinClassMapper.convertMutableToReadOnly(classDescriptor));
                        }
                    }
                } else if (i == 2 && typeComponentPosition == TypeComponentPosition.FLEXIBLE_UPPER) {
                    ClassDescriptor classDescriptor2 = (ClassDescriptor) classifierDescriptor;
                    if (javaToKotlinClassMapper.isReadOnly(classDescriptor2)) {
                        return enhancedMutability(javaToKotlinClassMapper.convertReadOnlyToMutable(classDescriptor2));
                    }
                }
            }
            return noChange(classifierDescriptor);
        }
        return noChange(classifierDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EnhancementResult<Boolean> getEnhancedNullability(KotlinType kotlinType, JavaTypeQualifiers javaTypeQualifiers, TypeComponentPosition typeComponentPosition) {
        if (!shouldEnhance(typeComponentPosition)) {
            return noChange(Boolean.valueOf(kotlinType.isMarkedNullable()));
        }
        NullabilityQualifier nullability = javaTypeQualifiers.getNullability();
        if (nullability != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[nullability.ordinal()];
            if (i == 1) {
                return enhancedNullability(true);
            }
            if (i == 2) {
                return enhancedNullability(false);
            }
        }
        return noChange(Boolean.valueOf(kotlinType.isMarkedNullable()));
    }

    static {
        FqName fqName = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
        Intrinsics.checkNotNullExpressionValue(fqName, "JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION");
        ENHANCED_NULLABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(fqName);
        FqName fqName2 = JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION;
        Intrinsics.checkNotNullExpressionValue(fqName2, "JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION");
        ENHANCED_MUTABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(fqName2);
    }
}
