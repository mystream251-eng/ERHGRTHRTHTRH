package kotlin.reflect.jvm.internal.impl.types.model;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TypeSystemContext.kt */
/* JADX INFO: loaded from: classes3.dex */
public interface TypeSystemInferenceExtensionContext extends TypeSystemCommonSuperTypesContext, TypeSystemContext {

    /* JADX INFO: compiled from: TypeSystemContext.kt */
    public static final class DefaultImpls {
        public static List<SimpleTypeMarker> fastCorrespondingSupertypes(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, SimpleTypeMarker fastCorrespondingSupertypes, TypeConstructorMarker constructor) {
            Intrinsics.checkNotNullParameter(fastCorrespondingSupertypes, "$this$fastCorrespondingSupertypes");
            Intrinsics.checkNotNullParameter(constructor, "constructor");
            return TypeSystemContext.DefaultImpls.fastCorrespondingSupertypes(typeSystemInferenceExtensionContext, fastCorrespondingSupertypes, constructor);
        }

        public static TypeArgumentMarker get(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, TypeArgumentListMarker get, int i) {
            Intrinsics.checkNotNullParameter(get, "$this$get");
            return TypeSystemContext.DefaultImpls.get(typeSystemInferenceExtensionContext, get, i);
        }

        public static TypeArgumentMarker getArgumentOrNull(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, SimpleTypeMarker getArgumentOrNull, int i) {
            Intrinsics.checkNotNullParameter(getArgumentOrNull, "$this$getArgumentOrNull");
            return TypeSystemContext.DefaultImpls.getArgumentOrNull(typeSystemInferenceExtensionContext, getArgumentOrNull, i);
        }

        public static boolean hasFlexibleNullability(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, KotlinTypeMarker hasFlexibleNullability) {
            Intrinsics.checkNotNullParameter(hasFlexibleNullability, "$this$hasFlexibleNullability");
            return TypeSystemContext.DefaultImpls.hasFlexibleNullability(typeSystemInferenceExtensionContext, hasFlexibleNullability);
        }

        public static boolean isClassType(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, SimpleTypeMarker isClassType) {
            Intrinsics.checkNotNullParameter(isClassType, "$this$isClassType");
            return TypeSystemContext.DefaultImpls.isClassType(typeSystemInferenceExtensionContext, isClassType);
        }

        public static boolean isDefinitelyNotNullType(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, KotlinTypeMarker isDefinitelyNotNullType) {
            Intrinsics.checkNotNullParameter(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
            return TypeSystemContext.DefaultImpls.isDefinitelyNotNullType(typeSystemInferenceExtensionContext, isDefinitelyNotNullType);
        }

        public static boolean isDynamic(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, KotlinTypeMarker isDynamic) {
            Intrinsics.checkNotNullParameter(isDynamic, "$this$isDynamic");
            return TypeSystemContext.DefaultImpls.isDynamic(typeSystemInferenceExtensionContext, isDynamic);
        }

        public static boolean isIntegerLiteralType(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, SimpleTypeMarker isIntegerLiteralType) {
            Intrinsics.checkNotNullParameter(isIntegerLiteralType, "$this$isIntegerLiteralType");
            return TypeSystemContext.DefaultImpls.isIntegerLiteralType(typeSystemInferenceExtensionContext, isIntegerLiteralType);
        }

        public static boolean isMarkedNullable(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, KotlinTypeMarker isMarkedNullable) {
            Intrinsics.checkNotNullParameter(isMarkedNullable, "$this$isMarkedNullable");
            return TypeSystemContext.DefaultImpls.isMarkedNullable(typeSystemInferenceExtensionContext, isMarkedNullable);
        }

        public static boolean isNothing(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, KotlinTypeMarker isNothing) {
            Intrinsics.checkNotNullParameter(isNothing, "$this$isNothing");
            return TypeSystemContext.DefaultImpls.isNothing(typeSystemInferenceExtensionContext, isNothing);
        }

        public static SimpleTypeMarker lowerBoundIfFlexible(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, KotlinTypeMarker lowerBoundIfFlexible) {
            Intrinsics.checkNotNullParameter(lowerBoundIfFlexible, "$this$lowerBoundIfFlexible");
            return TypeSystemContext.DefaultImpls.lowerBoundIfFlexible(typeSystemInferenceExtensionContext, lowerBoundIfFlexible);
        }

        public static int size(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, TypeArgumentListMarker size) {
            Intrinsics.checkNotNullParameter(size, "$this$size");
            return TypeSystemContext.DefaultImpls.size(typeSystemInferenceExtensionContext, size);
        }

        public static TypeConstructorMarker typeConstructor(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, KotlinTypeMarker typeConstructor) {
            Intrinsics.checkNotNullParameter(typeConstructor, "$this$typeConstructor");
            return TypeSystemContext.DefaultImpls.typeConstructor(typeSystemInferenceExtensionContext, typeConstructor);
        }

        public static SimpleTypeMarker upperBoundIfFlexible(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, KotlinTypeMarker upperBoundIfFlexible) {
            Intrinsics.checkNotNullParameter(upperBoundIfFlexible, "$this$upperBoundIfFlexible");
            return TypeSystemContext.DefaultImpls.upperBoundIfFlexible(typeSystemInferenceExtensionContext, upperBoundIfFlexible);
        }
    }
}
