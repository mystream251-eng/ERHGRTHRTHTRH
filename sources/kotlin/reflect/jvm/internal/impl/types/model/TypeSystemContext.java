package kotlin.reflect.jvm.internal.impl.types.model;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: TypeSystemContext.kt */
/* JADX INFO: loaded from: classes3.dex */
public interface TypeSystemContext extends TypeSystemOptimizationContext {
    boolean areEqualTypeConstructors(TypeConstructorMarker typeConstructorMarker, TypeConstructorMarker typeConstructorMarker2);

    int argumentsCount(KotlinTypeMarker kotlinTypeMarker);

    TypeArgumentListMarker asArgumentList(SimpleTypeMarker simpleTypeMarker);

    CapturedTypeMarker asCapturedType(SimpleTypeMarker simpleTypeMarker);

    DefinitelyNotNullTypeMarker asDefinitelyNotNullType(SimpleTypeMarker simpleTypeMarker);

    DynamicTypeMarker asDynamicType(FlexibleTypeMarker flexibleTypeMarker);

    FlexibleTypeMarker asFlexibleType(KotlinTypeMarker kotlinTypeMarker);

    SimpleTypeMarker asSimpleType(KotlinTypeMarker kotlinTypeMarker);

    TypeArgumentMarker asTypeArgument(KotlinTypeMarker kotlinTypeMarker);

    SimpleTypeMarker captureFromArguments(SimpleTypeMarker simpleTypeMarker, CaptureStatus captureStatus);

    TypeArgumentMarker get(TypeArgumentListMarker typeArgumentListMarker, int i);

    TypeArgumentMarker getArgument(KotlinTypeMarker kotlinTypeMarker, int i);

    TypeParameterMarker getParameter(TypeConstructorMarker typeConstructorMarker, int i);

    KotlinTypeMarker getType(TypeArgumentMarker typeArgumentMarker);

    TypeVariance getVariance(TypeArgumentMarker typeArgumentMarker);

    TypeVariance getVariance(TypeParameterMarker typeParameterMarker);

    KotlinTypeMarker intersectTypes(List<? extends KotlinTypeMarker> list);

    boolean isAnyConstructor(TypeConstructorMarker typeConstructorMarker);

    boolean isClassTypeConstructor(TypeConstructorMarker typeConstructorMarker);

    boolean isCommonFinalClassConstructor(TypeConstructorMarker typeConstructorMarker);

    boolean isDenotable(TypeConstructorMarker typeConstructorMarker);

    boolean isError(KotlinTypeMarker kotlinTypeMarker);

    boolean isIntegerLiteralTypeConstructor(TypeConstructorMarker typeConstructorMarker);

    boolean isIntersection(TypeConstructorMarker typeConstructorMarker);

    boolean isMarkedNullable(KotlinTypeMarker kotlinTypeMarker);

    boolean isMarkedNullable(SimpleTypeMarker simpleTypeMarker);

    boolean isNothingConstructor(TypeConstructorMarker typeConstructorMarker);

    boolean isNullableType(KotlinTypeMarker kotlinTypeMarker);

    boolean isPrimitiveType(SimpleTypeMarker simpleTypeMarker);

    boolean isProjectionNotNull(CapturedTypeMarker capturedTypeMarker);

    boolean isSingleClassifierType(SimpleTypeMarker simpleTypeMarker);

    boolean isStarProjection(TypeArgumentMarker typeArgumentMarker);

    boolean isStubType(SimpleTypeMarker simpleTypeMarker);

    SimpleTypeMarker lowerBound(FlexibleTypeMarker flexibleTypeMarker);

    SimpleTypeMarker lowerBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker);

    KotlinTypeMarker lowerType(CapturedTypeMarker capturedTypeMarker);

    int parametersCount(TypeConstructorMarker typeConstructorMarker);

    Collection<KotlinTypeMarker> possibleIntegerTypes(SimpleTypeMarker simpleTypeMarker);

    int size(TypeArgumentListMarker typeArgumentListMarker);

    Collection<KotlinTypeMarker> supertypes(TypeConstructorMarker typeConstructorMarker);

    TypeConstructorMarker typeConstructor(KotlinTypeMarker kotlinTypeMarker);

    TypeConstructorMarker typeConstructor(SimpleTypeMarker simpleTypeMarker);

    SimpleTypeMarker upperBound(FlexibleTypeMarker flexibleTypeMarker);

    SimpleTypeMarker upperBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker);

    SimpleTypeMarker withNullability(SimpleTypeMarker simpleTypeMarker, boolean z);

    /* JADX INFO: compiled from: TypeSystemContext.kt */
    public static final class DefaultImpls {
        public static List<SimpleTypeMarker> fastCorrespondingSupertypes(TypeSystemContext typeSystemContext, SimpleTypeMarker fastCorrespondingSupertypes, TypeConstructorMarker constructor) {
            Intrinsics.checkNotNullParameter(fastCorrespondingSupertypes, "$this$fastCorrespondingSupertypes");
            Intrinsics.checkNotNullParameter(constructor, "constructor");
            return null;
        }

        public static boolean identicalArguments(TypeSystemContext typeSystemContext, SimpleTypeMarker a2, SimpleTypeMarker b) {
            Intrinsics.checkNotNullParameter(a2, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            return TypeSystemOptimizationContext.DefaultImpls.identicalArguments(typeSystemContext, a2, b);
        }

        public static boolean isMarkedNullable(TypeSystemContext typeSystemContext, KotlinTypeMarker isMarkedNullable) {
            Intrinsics.checkNotNullParameter(isMarkedNullable, "$this$isMarkedNullable");
            return (isMarkedNullable instanceof SimpleTypeMarker) && typeSystemContext.isMarkedNullable((SimpleTypeMarker) isMarkedNullable);
        }

        public static TypeArgumentMarker getArgumentOrNull(TypeSystemContext typeSystemContext, SimpleTypeMarker getArgumentOrNull, int i) {
            Intrinsics.checkNotNullParameter(getArgumentOrNull, "$this$getArgumentOrNull");
            SimpleTypeMarker simpleTypeMarker = getArgumentOrNull;
            int iArgumentsCount = typeSystemContext.argumentsCount(simpleTypeMarker);
            if (i >= 0 && iArgumentsCount > i) {
                return typeSystemContext.getArgument(simpleTypeMarker, i);
            }
            return null;
        }

        public static SimpleTypeMarker lowerBoundIfFlexible(TypeSystemContext typeSystemContext, KotlinTypeMarker lowerBoundIfFlexible) {
            SimpleTypeMarker simpleTypeMarkerLowerBound;
            Intrinsics.checkNotNullParameter(lowerBoundIfFlexible, "$this$lowerBoundIfFlexible");
            FlexibleTypeMarker flexibleTypeMarkerAsFlexibleType = typeSystemContext.asFlexibleType(lowerBoundIfFlexible);
            if (flexibleTypeMarkerAsFlexibleType != null && (simpleTypeMarkerLowerBound = typeSystemContext.lowerBound(flexibleTypeMarkerAsFlexibleType)) != null) {
                return simpleTypeMarkerLowerBound;
            }
            SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType(lowerBoundIfFlexible);
            Intrinsics.checkNotNull(simpleTypeMarkerAsSimpleType);
            return simpleTypeMarkerAsSimpleType;
        }

        public static SimpleTypeMarker upperBoundIfFlexible(TypeSystemContext typeSystemContext, KotlinTypeMarker upperBoundIfFlexible) {
            SimpleTypeMarker simpleTypeMarkerUpperBound;
            Intrinsics.checkNotNullParameter(upperBoundIfFlexible, "$this$upperBoundIfFlexible");
            FlexibleTypeMarker flexibleTypeMarkerAsFlexibleType = typeSystemContext.asFlexibleType(upperBoundIfFlexible);
            if (flexibleTypeMarkerAsFlexibleType != null && (simpleTypeMarkerUpperBound = typeSystemContext.upperBound(flexibleTypeMarkerAsFlexibleType)) != null) {
                return simpleTypeMarkerUpperBound;
            }
            SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType(upperBoundIfFlexible);
            Intrinsics.checkNotNull(simpleTypeMarkerAsSimpleType);
            return simpleTypeMarkerAsSimpleType;
        }

        public static boolean isDynamic(TypeSystemContext typeSystemContext, KotlinTypeMarker isDynamic) {
            Intrinsics.checkNotNullParameter(isDynamic, "$this$isDynamic");
            FlexibleTypeMarker flexibleTypeMarkerAsFlexibleType = typeSystemContext.asFlexibleType(isDynamic);
            return (flexibleTypeMarkerAsFlexibleType != null ? typeSystemContext.asDynamicType(flexibleTypeMarkerAsFlexibleType) : null) != null;
        }

        public static boolean isDefinitelyNotNullType(TypeSystemContext typeSystemContext, KotlinTypeMarker isDefinitelyNotNullType) {
            Intrinsics.checkNotNullParameter(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
            SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType(isDefinitelyNotNullType);
            return (simpleTypeMarkerAsSimpleType != null ? typeSystemContext.asDefinitelyNotNullType(simpleTypeMarkerAsSimpleType) : null) != null;
        }

        public static boolean hasFlexibleNullability(TypeSystemContext typeSystemContext, KotlinTypeMarker hasFlexibleNullability) {
            Intrinsics.checkNotNullParameter(hasFlexibleNullability, "$this$hasFlexibleNullability");
            return typeSystemContext.isMarkedNullable(typeSystemContext.lowerBoundIfFlexible(hasFlexibleNullability)) != typeSystemContext.isMarkedNullable(typeSystemContext.upperBoundIfFlexible(hasFlexibleNullability));
        }

        public static TypeConstructorMarker typeConstructor(TypeSystemContext typeSystemContext, KotlinTypeMarker typeConstructor) {
            Intrinsics.checkNotNullParameter(typeConstructor, "$this$typeConstructor");
            SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType(typeConstructor);
            if (simpleTypeMarkerAsSimpleType == null) {
                simpleTypeMarkerAsSimpleType = typeSystemContext.lowerBoundIfFlexible(typeConstructor);
            }
            return typeSystemContext.typeConstructor(simpleTypeMarkerAsSimpleType);
        }

        public static boolean isNothing(TypeSystemContext typeSystemContext, KotlinTypeMarker isNothing) {
            Intrinsics.checkNotNullParameter(isNothing, "$this$isNothing");
            return typeSystemContext.isNothingConstructor(typeSystemContext.typeConstructor(isNothing)) && !typeSystemContext.isNullableType(isNothing);
        }

        public static boolean isClassType(TypeSystemContext typeSystemContext, SimpleTypeMarker isClassType) {
            Intrinsics.checkNotNullParameter(isClassType, "$this$isClassType");
            return typeSystemContext.isClassTypeConstructor(typeSystemContext.typeConstructor(isClassType));
        }

        public static boolean isIntegerLiteralType(TypeSystemContext typeSystemContext, SimpleTypeMarker isIntegerLiteralType) {
            Intrinsics.checkNotNullParameter(isIntegerLiteralType, "$this$isIntegerLiteralType");
            return typeSystemContext.isIntegerLiteralTypeConstructor(typeSystemContext.typeConstructor(isIntegerLiteralType));
        }

        public static TypeArgumentMarker get(TypeSystemContext typeSystemContext, TypeArgumentListMarker get, int i) {
            Intrinsics.checkNotNullParameter(get, "$this$get");
            if (get instanceof SimpleTypeMarker) {
                return typeSystemContext.getArgument((KotlinTypeMarker) get, i);
            }
            if (get instanceof ArgumentList) {
                TypeArgumentMarker typeArgumentMarker = ((ArgumentList) get).get(i);
                Intrinsics.checkNotNullExpressionValue(typeArgumentMarker, "get(index)");
                return typeArgumentMarker;
            }
            throw new IllegalStateException(("unknown type argument list type: " + get + ", " + Reflection.getOrCreateKotlinClass(get.getClass())).toString());
        }

        public static int size(TypeSystemContext typeSystemContext, TypeArgumentListMarker size) {
            Intrinsics.checkNotNullParameter(size, "$this$size");
            if (size instanceof SimpleTypeMarker) {
                return typeSystemContext.argumentsCount((KotlinTypeMarker) size);
            }
            if (size instanceof ArgumentList) {
                return ((ArgumentList) size).size();
            }
            throw new IllegalStateException(("unknown type argument list type: " + size + ", " + Reflection.getOrCreateKotlinClass(size.getClass())).toString());
        }
    }
}
