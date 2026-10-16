package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.HashSet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;

/* JADX INFO: compiled from: inlineClassMapping.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class InlineClassMappingKt {
    public static final KotlinTypeMarker computeExpandedTypeForInlineClass(TypeSystemCommonBackendContext computeExpandedTypeForInlineClass, KotlinTypeMarker inlineClassType) {
        Intrinsics.checkNotNullParameter(computeExpandedTypeForInlineClass, "$this$computeExpandedTypeForInlineClass");
        Intrinsics.checkNotNullParameter(inlineClassType, "inlineClassType");
        return computeExpandedTypeInner(computeExpandedTypeForInlineClass, inlineClassType, new HashSet());
    }

    private static final KotlinTypeMarker computeExpandedTypeInner(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker, HashSet<TypeConstructorMarker> hashSet) {
        KotlinTypeMarker kotlinTypeMarkerComputeExpandedTypeInner;
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemCommonBackendContext.typeConstructor(kotlinTypeMarker);
        if (!hashSet.add(typeConstructorMarkerTypeConstructor)) {
            return null;
        }
        TypeParameterMarker typeParameterClassifier = typeSystemCommonBackendContext.getTypeParameterClassifier(typeConstructorMarkerTypeConstructor);
        if (typeParameterClassifier != null) {
            kotlinTypeMarkerComputeExpandedTypeInner = computeExpandedTypeInner(typeSystemCommonBackendContext, typeSystemCommonBackendContext.getRepresentativeUpperBound(typeParameterClassifier), hashSet);
            if (kotlinTypeMarkerComputeExpandedTypeInner == null) {
                return null;
            }
            if (!typeSystemCommonBackendContext.isNullableType(kotlinTypeMarkerComputeExpandedTypeInner) && typeSystemCommonBackendContext.isMarkedNullable(kotlinTypeMarker)) {
                return typeSystemCommonBackendContext.makeNullable(kotlinTypeMarkerComputeExpandedTypeInner);
            }
        } else {
            if (!typeSystemCommonBackendContext.isInlineClass(typeConstructorMarkerTypeConstructor)) {
                return kotlinTypeMarker;
            }
            KotlinTypeMarker substitutedUnderlyingType = typeSystemCommonBackendContext.getSubstitutedUnderlyingType(kotlinTypeMarker);
            if (substitutedUnderlyingType == null || (kotlinTypeMarkerComputeExpandedTypeInner = computeExpandedTypeInner(typeSystemCommonBackendContext, substitutedUnderlyingType, hashSet)) == null) {
                return null;
            }
            if (typeSystemCommonBackendContext.isNullableType(kotlinTypeMarker)) {
                if (typeSystemCommonBackendContext.isNullableType(kotlinTypeMarkerComputeExpandedTypeInner)) {
                    return kotlinTypeMarker;
                }
                return ((kotlinTypeMarkerComputeExpandedTypeInner instanceof SimpleTypeMarker) && typeSystemCommonBackendContext.isPrimitiveType((SimpleTypeMarker) kotlinTypeMarkerComputeExpandedTypeInner)) ? kotlinTypeMarker : typeSystemCommonBackendContext.makeNullable(kotlinTypeMarkerComputeExpandedTypeInner);
            }
        }
        return kotlinTypeMarkerComputeExpandedTypeInner;
    }
}
