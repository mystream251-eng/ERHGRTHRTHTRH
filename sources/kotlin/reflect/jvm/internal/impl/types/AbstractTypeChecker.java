package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.ArgumentList;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.IntersectionTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

/* JADX INFO: compiled from: AbstractTypeChecker.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class AbstractTypeChecker {
    public static final AbstractTypeChecker INSTANCE = new AbstractTypeChecker();
    public static boolean RUN_SLOW_ASSERTIONS;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TypeVariance.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[TypeVariance.INV.ordinal()] = 1;
            $EnumSwitchMapping$0[TypeVariance.OUT.ordinal()] = 2;
            $EnumSwitchMapping$0[TypeVariance.IN.ordinal()] = 3;
            int[] iArr2 = new int[AbstractTypeCheckerContext.LowerCapturedTypePolicy.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[AbstractTypeCheckerContext.LowerCapturedTypePolicy.CHECK_ONLY_LOWER.ordinal()] = 1;
            $EnumSwitchMapping$1[AbstractTypeCheckerContext.LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER.ordinal()] = 2;
            $EnumSwitchMapping$1[AbstractTypeCheckerContext.LowerCapturedTypePolicy.SKIP_LOWER.ordinal()] = 3;
        }
    }

    private AbstractTypeChecker() {
    }

    public static /* synthetic */ boolean isSubtypeOf$default(AbstractTypeChecker abstractTypeChecker, AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        return abstractTypeChecker.isSubtypeOf(abstractTypeCheckerContext, kotlinTypeMarker, kotlinTypeMarker2, z);
    }

    public final boolean isSubtypeOf(AbstractTypeCheckerContext context, KotlinTypeMarker subType, KotlinTypeMarker superType, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        if (subType == superType) {
            return true;
        }
        if (context.customIsSubtypeOf(subType, superType)) {
            return INSTANCE.completeIsSubTypeOf(context, context.prepareType(context.refineType(subType)), context.prepareType(context.refineType(superType)), z);
        }
        return false;
    }

    public final boolean equalTypes(AbstractTypeCheckerContext context, KotlinTypeMarker a2, KotlinTypeMarker b) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        if (a2 == b) {
            return true;
        }
        if (INSTANCE.isCommonDenotableType(context, a2) && INSTANCE.isCommonDenotableType(context, b)) {
            KotlinTypeMarker kotlinTypeMarkerRefineType = context.refineType(a2);
            KotlinTypeMarker kotlinTypeMarkerRefineType2 = context.refineType(b);
            SimpleTypeMarker simpleTypeMarkerLowerBoundIfFlexible = context.lowerBoundIfFlexible(kotlinTypeMarkerRefineType);
            if (!context.areEqualTypeConstructors(context.typeConstructor(kotlinTypeMarkerRefineType), context.typeConstructor(kotlinTypeMarkerRefineType2))) {
                return false;
            }
            if (context.argumentsCount(simpleTypeMarkerLowerBoundIfFlexible) == 0) {
                return context.hasFlexibleNullability(kotlinTypeMarkerRefineType) || context.hasFlexibleNullability(kotlinTypeMarkerRefineType2) || context.isMarkedNullable(simpleTypeMarkerLowerBoundIfFlexible) == context.isMarkedNullable(context.lowerBoundIfFlexible(kotlinTypeMarkerRefineType2));
            }
        }
        return isSubtypeOf$default(INSTANCE, context, a2, b, false, 8, null) && isSubtypeOf$default(INSTANCE, context, b, a2, false, 8, null);
    }

    private final boolean completeIsSubTypeOf(AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z) {
        Boolean boolCheckSubtypeForSpecialCases = checkSubtypeForSpecialCases(abstractTypeCheckerContext, abstractTypeCheckerContext.lowerBoundIfFlexible(kotlinTypeMarker), abstractTypeCheckerContext.upperBoundIfFlexible(kotlinTypeMarker2));
        if (boolCheckSubtypeForSpecialCases != null) {
            boolean zBooleanValue = boolCheckSubtypeForSpecialCases.booleanValue();
            abstractTypeCheckerContext.addSubtypeConstraint(kotlinTypeMarker, kotlinTypeMarker2, z);
            return zBooleanValue;
        }
        Boolean boolAddSubtypeConstraint = abstractTypeCheckerContext.addSubtypeConstraint(kotlinTypeMarker, kotlinTypeMarker2, z);
        return boolAddSubtypeConstraint != null ? boolAddSubtypeConstraint.booleanValue() : isSubtypeOfForSingleClassifierType(abstractTypeCheckerContext, abstractTypeCheckerContext.lowerBoundIfFlexible(kotlinTypeMarker), abstractTypeCheckerContext.upperBoundIfFlexible(kotlinTypeMarker2));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker$checkSubtypeForIntegerLiteralType$1] */
    private final Boolean checkSubtypeForIntegerLiteralType(final AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        if (!abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarker) && !abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarker2)) {
            return null;
        }
        ?? r0 = new Function3<SimpleTypeMarker, SimpleTypeMarker, Boolean, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker.checkSubtypeForIntegerLiteralType.1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Boolean invoke(SimpleTypeMarker simpleTypeMarker3, SimpleTypeMarker simpleTypeMarker4, Boolean bool) {
                return Boolean.valueOf(invoke(simpleTypeMarker3, simpleTypeMarker4, bool.booleanValue()));
            }

            public final boolean invoke(SimpleTypeMarker integerLiteralType, SimpleTypeMarker type, boolean z) {
                Intrinsics.checkNotNullParameter(integerLiteralType, "integerLiteralType");
                Intrinsics.checkNotNullParameter(type, "type");
                Collection<KotlinTypeMarker> collectionPossibleIntegerTypes = abstractTypeCheckerContext.possibleIntegerTypes(integerLiteralType);
                if (!(collectionPossibleIntegerTypes instanceof Collection) || !collectionPossibleIntegerTypes.isEmpty()) {
                    for (KotlinTypeMarker kotlinTypeMarker : collectionPossibleIntegerTypes) {
                        if (Intrinsics.areEqual(abstractTypeCheckerContext.typeConstructor(kotlinTypeMarker), abstractTypeCheckerContext.typeConstructor(type)) || (z && AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, abstractTypeCheckerContext, type, kotlinTypeMarker, false, 8, null))) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
        Function1<SimpleTypeMarker, Boolean> function1 = new Function1<SimpleTypeMarker, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker.checkSubtypeForIntegerLiteralType.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(SimpleTypeMarker simpleTypeMarker3) {
                return Boolean.valueOf(invoke2(simpleTypeMarker3));
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(SimpleTypeMarker type) {
                boolean z;
                Intrinsics.checkNotNullParameter(type, "type");
                TypeConstructorMarker typeConstructorMarkerTypeConstructor = abstractTypeCheckerContext.typeConstructor(type);
                if (typeConstructorMarkerTypeConstructor instanceof IntersectionTypeConstructorMarker) {
                    Collection<KotlinTypeMarker> collectionSupertypes = abstractTypeCheckerContext.supertypes(typeConstructorMarkerTypeConstructor);
                    if (!(collectionSupertypes instanceof Collection) || !collectionSupertypes.isEmpty()) {
                        Iterator<T> it = collectionSupertypes.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z = false;
                                break;
                            }
                            SimpleTypeMarker simpleTypeMarkerAsSimpleType = abstractTypeCheckerContext.asSimpleType((KotlinTypeMarker) it.next());
                            if (simpleTypeMarkerAsSimpleType != null && abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarkerAsSimpleType)) {
                                z = true;
                                break;
                            }
                        }
                    } else {
                        z = false;
                        break;
                    }
                    if (z) {
                        return true;
                    }
                }
                return false;
            }
        };
        if (abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarker) && abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarker2)) {
            return true;
        }
        if (abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarker)) {
            if (r0.invoke(simpleTypeMarker, simpleTypeMarker2, false)) {
                return true;
            }
        } else if (abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarker2) && (function1.invoke2(simpleTypeMarker) || r0.invoke(simpleTypeMarker2, simpleTypeMarker, true))) {
            return true;
        }
        return null;
    }

    private final boolean hasNothingSupertype(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker) {
        AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible lowerIfFlexible;
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = abstractTypeCheckerContext.typeConstructor(simpleTypeMarker);
        if (abstractTypeCheckerContext.isClassTypeConstructor(typeConstructorMarkerTypeConstructor)) {
            return abstractTypeCheckerContext.isNothingConstructor(typeConstructorMarkerTypeConstructor);
        }
        if (abstractTypeCheckerContext.isNothingConstructor(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker))) {
            return true;
        }
        abstractTypeCheckerContext.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = abstractTypeCheckerContext.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set<SimpleTypeMarker> supertypesSet = abstractTypeCheckerContext.getSupertypesSet();
        Intrinsics.checkNotNull(supertypesSet);
        supertypesDeque.push(simpleTypeMarker);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleTypeMarker current = supertypesDeque.pop();
            Intrinsics.checkNotNullExpressionValue(current, "current");
            if (supertypesSet.add(current)) {
                if (abstractTypeCheckerContext.isClassType(current)) {
                    lowerIfFlexible = AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                } else {
                    lowerIfFlexible = AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                }
                if (!(!Intrinsics.areEqual(lowerIfFlexible, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                    lowerIfFlexible = null;
                }
                if (lowerIfFlexible != null) {
                    Iterator<KotlinTypeMarker> it = abstractTypeCheckerContext.supertypes(abstractTypeCheckerContext.typeConstructor(current)).iterator();
                    while (it.hasNext()) {
                        SimpleTypeMarker simpleTypeMarkerMo1272transformType = lowerIfFlexible.mo1272transformType(abstractTypeCheckerContext, it.next());
                        if (abstractTypeCheckerContext.isNothingConstructor(abstractTypeCheckerContext.typeConstructor(simpleTypeMarkerMo1272transformType))) {
                            abstractTypeCheckerContext.clear();
                            return true;
                        }
                        supertypesDeque.add(simpleTypeMarkerMo1272transformType);
                    }
                } else {
                    continue;
                }
            }
        }
        abstractTypeCheckerContext.clear();
        return false;
    }

    private final boolean isSubtypeOfForSingleClassifierType(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        KotlinTypeMarker type;
        boolean z = false;
        boolean z2 = true;
        if (RUN_SLOW_ASSERTIONS) {
            boolean z3 = abstractTypeCheckerContext.isSingleClassifierType(simpleTypeMarker) || abstractTypeCheckerContext.isIntersection(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker)) || abstractTypeCheckerContext.isAllowedTypeVariable(simpleTypeMarker);
            if (_Assertions.ENABLED && !z3) {
                throw new AssertionError("Not singleClassifierType and not intersection subType: " + simpleTypeMarker);
            }
            boolean z4 = abstractTypeCheckerContext.isSingleClassifierType(simpleTypeMarker2) || abstractTypeCheckerContext.isAllowedTypeVariable(simpleTypeMarker2);
            if (_Assertions.ENABLED && !z4) {
                throw new AssertionError("Not singleClassifierType superType: " + simpleTypeMarker2);
            }
        }
        if (!AbstractNullabilityChecker.INSTANCE.isPossibleSubtype(abstractTypeCheckerContext, simpleTypeMarker, simpleTypeMarker2)) {
            return false;
        }
        SimpleTypeMarker simpleTypeMarker3 = simpleTypeMarker;
        SimpleTypeMarker simpleTypeMarker4 = simpleTypeMarker2;
        Boolean boolCheckSubtypeForIntegerLiteralType = checkSubtypeForIntegerLiteralType(abstractTypeCheckerContext, abstractTypeCheckerContext.lowerBoundIfFlexible(simpleTypeMarker3), abstractTypeCheckerContext.upperBoundIfFlexible(simpleTypeMarker4));
        if (boolCheckSubtypeForIntegerLiteralType != null) {
            boolean zBooleanValue = boolCheckSubtypeForIntegerLiteralType.booleanValue();
            AbstractTypeCheckerContext.addSubtypeConstraint$default(abstractTypeCheckerContext, simpleTypeMarker3, simpleTypeMarker4, false, 4, null);
            return zBooleanValue;
        }
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = abstractTypeCheckerContext.typeConstructor(simpleTypeMarker2);
        if ((abstractTypeCheckerContext.areEqualTypeConstructors(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker), typeConstructorMarkerTypeConstructor) && abstractTypeCheckerContext.parametersCount(typeConstructorMarkerTypeConstructor) == 0) || abstractTypeCheckerContext.isAnyConstructor(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker2))) {
            return true;
        }
        List<SimpleTypeMarker> listFindCorrespondingSupertypes = findCorrespondingSupertypes(abstractTypeCheckerContext, simpleTypeMarker, typeConstructorMarkerTypeConstructor);
        int size = listFindCorrespondingSupertypes.size();
        if (size == 0) {
            return hasNothingSupertype(abstractTypeCheckerContext, simpleTypeMarker);
        }
        if (size == 1) {
            return isSubtypeForSameConstructor(abstractTypeCheckerContext, abstractTypeCheckerContext.asArgumentList((SimpleTypeMarker) CollectionsKt.first((List) listFindCorrespondingSupertypes)), simpleTypeMarker2);
        }
        ArgumentList argumentList = new ArgumentList(abstractTypeCheckerContext.parametersCount(typeConstructorMarkerTypeConstructor));
        int iParametersCount = abstractTypeCheckerContext.parametersCount(typeConstructorMarkerTypeConstructor);
        int i = 0;
        boolean z5 = false;
        while (i < iParametersCount) {
            z5 = (z5 || abstractTypeCheckerContext.getVariance(abstractTypeCheckerContext.getParameter(typeConstructorMarkerTypeConstructor, i)) != TypeVariance.OUT) ? z2 : z;
            if (!z5) {
                List<SimpleTypeMarker> list = listFindCorrespondingSupertypes;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (SimpleTypeMarker simpleTypeMarker5 : list) {
                    TypeArgumentMarker argumentOrNull = abstractTypeCheckerContext.getArgumentOrNull(simpleTypeMarker5, i);
                    if (argumentOrNull != null) {
                        if (!(abstractTypeCheckerContext.getVariance(argumentOrNull) == TypeVariance.INV)) {
                            argumentOrNull = null;
                        }
                        if (argumentOrNull != null && (type = abstractTypeCheckerContext.getType(argumentOrNull)) != null) {
                            arrayList.add(type);
                        }
                    }
                    throw new IllegalStateException(("Incorrect type: " + simpleTypeMarker5 + ", subType: " + simpleTypeMarker + ", superType: " + simpleTypeMarker2).toString());
                }
                argumentList.add(abstractTypeCheckerContext.asTypeArgument(abstractTypeCheckerContext.intersectTypes(arrayList)));
            }
            i++;
            z = false;
            z2 = true;
        }
        if (!z5 && isSubtypeForSameConstructor(abstractTypeCheckerContext, argumentList, simpleTypeMarker2)) {
            return true;
        }
        List<SimpleTypeMarker> list2 = listFindCorrespondingSupertypes;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                if (INSTANCE.isSubtypeForSameConstructor(abstractTypeCheckerContext, abstractTypeCheckerContext.asArgumentList((SimpleTypeMarker) it.next()), simpleTypeMarker2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean isSubtypeForSameConstructor(AbstractTypeCheckerContext isSubtypeForSameConstructor, TypeArgumentListMarker capturedSubArguments, SimpleTypeMarker superType) {
        boolean zEqualTypes;
        Intrinsics.checkNotNullParameter(isSubtypeForSameConstructor, "$this$isSubtypeForSameConstructor");
        Intrinsics.checkNotNullParameter(capturedSubArguments, "capturedSubArguments");
        Intrinsics.checkNotNullParameter(superType, "superType");
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = isSubtypeForSameConstructor.typeConstructor(superType);
        int iParametersCount = isSubtypeForSameConstructor.parametersCount(typeConstructorMarkerTypeConstructor);
        for (int i = 0; i < iParametersCount; i++) {
            TypeArgumentMarker argument = isSubtypeForSameConstructor.getArgument(superType, i);
            if (!isSubtypeForSameConstructor.isStarProjection(argument)) {
                KotlinTypeMarker type = isSubtypeForSameConstructor.getType(argument);
                TypeArgumentMarker typeArgumentMarker = isSubtypeForSameConstructor.get(capturedSubArguments, i);
                boolean z = isSubtypeForSameConstructor.getVariance(typeArgumentMarker) == TypeVariance.INV;
                if (_Assertions.ENABLED && !z) {
                    throw new AssertionError("Incorrect sub argument: " + typeArgumentMarker);
                }
                KotlinTypeMarker type2 = isSubtypeForSameConstructor.getType(typeArgumentMarker);
                TypeVariance typeVarianceEffectiveVariance = effectiveVariance(isSubtypeForSameConstructor.getVariance(isSubtypeForSameConstructor.getParameter(typeConstructorMarkerTypeConstructor, i)), isSubtypeForSameConstructor.getVariance(argument));
                if (typeVarianceEffectiveVariance != null) {
                    if (isSubtypeForSameConstructor.argumentsDepth > 100) {
                        throw new IllegalStateException(("Arguments depth is too high. Some related argument: " + type2).toString());
                    }
                    isSubtypeForSameConstructor.argumentsDepth++;
                    int i2 = WhenMappings.$EnumSwitchMapping$0[typeVarianceEffectiveVariance.ordinal()];
                    if (i2 == 1) {
                        zEqualTypes = INSTANCE.equalTypes(isSubtypeForSameConstructor, type2, type);
                    } else if (i2 == 2) {
                        zEqualTypes = isSubtypeOf$default(INSTANCE, isSubtypeForSameConstructor, type2, type, false, 8, null);
                    } else {
                        if (i2 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        zEqualTypes = isSubtypeOf$default(INSTANCE, isSubtypeForSameConstructor, type, type2, false, 8, null);
                    }
                    isSubtypeForSameConstructor.argumentsDepth--;
                    if (!zEqualTypes) {
                        return false;
                    }
                } else {
                    return isSubtypeForSameConstructor.isErrorTypeEqualsToAnything();
                }
            }
        }
        return true;
    }

    private final boolean isCommonDenotableType(AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker) {
        return abstractTypeCheckerContext.isDenotable(abstractTypeCheckerContext.typeConstructor(kotlinTypeMarker)) && !abstractTypeCheckerContext.isDynamic(kotlinTypeMarker) && !abstractTypeCheckerContext.isDefinitelyNotNullType(kotlinTypeMarker) && Intrinsics.areEqual(abstractTypeCheckerContext.typeConstructor(abstractTypeCheckerContext.lowerBoundIfFlexible(kotlinTypeMarker)), abstractTypeCheckerContext.typeConstructor(abstractTypeCheckerContext.upperBoundIfFlexible(kotlinTypeMarker)));
    }

    public final TypeVariance effectiveVariance(TypeVariance declared, TypeVariance useSite) {
        Intrinsics.checkNotNullParameter(declared, "declared");
        Intrinsics.checkNotNullParameter(useSite, "useSite");
        if (declared == TypeVariance.INV) {
            return useSite;
        }
        if (useSite == TypeVariance.INV || declared == useSite) {
            return declared;
        }
        return null;
    }

    private final Boolean checkSubtypeForSpecialCases(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        SimpleTypeMarker simpleTypeMarker3 = simpleTypeMarker;
        boolean z = false;
        if (abstractTypeCheckerContext.isError(simpleTypeMarker3) || abstractTypeCheckerContext.isError(simpleTypeMarker2)) {
            if (abstractTypeCheckerContext.isErrorTypeEqualsToAnything()) {
                return true;
            }
            if (!abstractTypeCheckerContext.isMarkedNullable(simpleTypeMarker) || abstractTypeCheckerContext.isMarkedNullable(simpleTypeMarker2)) {
                return Boolean.valueOf(AbstractStrictEqualityTypeChecker.INSTANCE.strictEqualTypes(abstractTypeCheckerContext, abstractTypeCheckerContext.withNullability(simpleTypeMarker, false), abstractTypeCheckerContext.withNullability(simpleTypeMarker2, false)));
            }
            return false;
        }
        if (abstractTypeCheckerContext.isStubType(simpleTypeMarker) || abstractTypeCheckerContext.isStubType(simpleTypeMarker2)) {
            return Boolean.valueOf(abstractTypeCheckerContext.isStubTypeEqualsToAnything());
        }
        CapturedTypeMarker capturedTypeMarkerAsCapturedType = abstractTypeCheckerContext.asCapturedType(simpleTypeMarker2);
        KotlinTypeMarker kotlinTypeMarkerLowerType = capturedTypeMarkerAsCapturedType != null ? abstractTypeCheckerContext.lowerType(capturedTypeMarkerAsCapturedType) : null;
        if (capturedTypeMarkerAsCapturedType != null && kotlinTypeMarkerLowerType != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[abstractTypeCheckerContext.getLowerCapturedTypePolicy(simpleTypeMarker, capturedTypeMarkerAsCapturedType).ordinal()];
            if (i == 1) {
                return Boolean.valueOf(isSubtypeOf$default(this, abstractTypeCheckerContext, simpleTypeMarker3, kotlinTypeMarkerLowerType, false, 8, null));
            }
            if (i == 2 && isSubtypeOf$default(this, abstractTypeCheckerContext, simpleTypeMarker3, kotlinTypeMarkerLowerType, false, 8, null)) {
                return true;
            }
        }
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = abstractTypeCheckerContext.typeConstructor(simpleTypeMarker2);
        if (!abstractTypeCheckerContext.isIntersection(typeConstructorMarkerTypeConstructor)) {
            return null;
        }
        boolean z2 = !abstractTypeCheckerContext.isMarkedNullable(simpleTypeMarker2);
        if (_Assertions.ENABLED && !z2) {
            throw new AssertionError("Intersection type should not be marked nullable!: " + simpleTypeMarker2);
        }
        Collection<KotlinTypeMarker> collectionSupertypes = abstractTypeCheckerContext.supertypes(typeConstructorMarkerTypeConstructor);
        if ((collectionSupertypes instanceof Collection) && collectionSupertypes.isEmpty()) {
            z = true;
        } else {
            Iterator<T> it = collectionSupertypes.iterator();
            while (it.hasNext()) {
                if (!isSubtypeOf$default(INSTANCE, abstractTypeCheckerContext, simpleTypeMarker3, (KotlinTypeMarker) it.next(), false, 8, null)) {
                }
            }
            z = true;
        }
        return Boolean.valueOf(z);
    }

    private final List<SimpleTypeMarker> collectAllSupertypesWithGivenTypeConstructor(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible lowerIfFlexibleSubstitutionSupertypePolicy;
        List<SimpleTypeMarker> listFastCorrespondingSupertypes = abstractTypeCheckerContext.fastCorrespondingSupertypes(simpleTypeMarker, typeConstructorMarker);
        if (listFastCorrespondingSupertypes != null) {
            return listFastCorrespondingSupertypes;
        }
        if (!abstractTypeCheckerContext.isClassTypeConstructor(typeConstructorMarker) && abstractTypeCheckerContext.isClassType(simpleTypeMarker)) {
            return CollectionsKt.emptyList();
        }
        if (abstractTypeCheckerContext.isCommonFinalClassConstructor(typeConstructorMarker)) {
            if (abstractTypeCheckerContext.areEqualTypeConstructors(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker), typeConstructorMarker)) {
                SimpleTypeMarker simpleTypeMarkerCaptureFromArguments = abstractTypeCheckerContext.captureFromArguments(simpleTypeMarker, CaptureStatus.FOR_SUBTYPING);
                if (simpleTypeMarkerCaptureFromArguments != null) {
                    simpleTypeMarker = simpleTypeMarkerCaptureFromArguments;
                }
                return CollectionsKt.listOf(simpleTypeMarker);
            }
            return CollectionsKt.emptyList();
        }
        SmartList smartList = new SmartList();
        abstractTypeCheckerContext.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = abstractTypeCheckerContext.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set<SimpleTypeMarker> supertypesSet = abstractTypeCheckerContext.getSupertypesSet();
        Intrinsics.checkNotNull(supertypesSet);
        supertypesDeque.push(simpleTypeMarker);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleTypeMarker current = supertypesDeque.pop();
            Intrinsics.checkNotNullExpressionValue(current, "current");
            if (supertypesSet.add(current)) {
                SimpleTypeMarker simpleTypeMarkerCaptureFromArguments2 = abstractTypeCheckerContext.captureFromArguments(current, CaptureStatus.FOR_SUBTYPING);
                if (simpleTypeMarkerCaptureFromArguments2 == null) {
                    simpleTypeMarkerCaptureFromArguments2 = current;
                }
                if (abstractTypeCheckerContext.areEqualTypeConstructors(abstractTypeCheckerContext.typeConstructor(simpleTypeMarkerCaptureFromArguments2), typeConstructorMarker)) {
                    smartList.add(simpleTypeMarkerCaptureFromArguments2);
                    lowerIfFlexibleSubstitutionSupertypePolicy = AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                } else if (abstractTypeCheckerContext.argumentsCount(simpleTypeMarkerCaptureFromArguments2) == 0) {
                    lowerIfFlexibleSubstitutionSupertypePolicy = AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                } else {
                    lowerIfFlexibleSubstitutionSupertypePolicy = abstractTypeCheckerContext.substitutionSupertypePolicy(simpleTypeMarkerCaptureFromArguments2);
                }
                if (!(!Intrinsics.areEqual(lowerIfFlexibleSubstitutionSupertypePolicy, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                    lowerIfFlexibleSubstitutionSupertypePolicy = null;
                }
                if (lowerIfFlexibleSubstitutionSupertypePolicy != null) {
                    Iterator<KotlinTypeMarker> it = abstractTypeCheckerContext.supertypes(abstractTypeCheckerContext.typeConstructor(current)).iterator();
                    while (it.hasNext()) {
                        supertypesDeque.add(lowerIfFlexibleSubstitutionSupertypePolicy.mo1272transformType(abstractTypeCheckerContext, it.next()));
                    }
                }
            }
        }
        abstractTypeCheckerContext.clear();
        return smartList;
    }

    private final List<SimpleTypeMarker> collectAndFilter(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        return selectOnlyPureKotlinSupertypes(abstractTypeCheckerContext, collectAllSupertypesWithGivenTypeConstructor(abstractTypeCheckerContext, simpleTypeMarker, typeConstructorMarker));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<SimpleTypeMarker> selectOnlyPureKotlinSupertypes(AbstractTypeCheckerContext abstractTypeCheckerContext, List<? extends SimpleTypeMarker> list) {
        if (list.size() < 2) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            TypeArgumentListMarker typeArgumentListMarkerAsArgumentList = abstractTypeCheckerContext.asArgumentList((SimpleTypeMarker) next);
            AbstractTypeCheckerContext abstractTypeCheckerContext2 = abstractTypeCheckerContext;
            int size = abstractTypeCheckerContext2.size(typeArgumentListMarkerAsArgumentList);
            for (int i = 0; i < size; i++) {
                if (!(abstractTypeCheckerContext.asFlexibleType(abstractTypeCheckerContext.getType(abstractTypeCheckerContext2.get(typeArgumentListMarkerAsArgumentList, i))) == null)) {
                    z = false;
                    break;
                }
            }
            if (z) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = arrayList;
        return arrayList2.isEmpty() ^ true ? arrayList2 : list;
    }

    public final List<SimpleTypeMarker> findCorrespondingSupertypes(AbstractTypeCheckerContext findCorrespondingSupertypes, SimpleTypeMarker subType, TypeConstructorMarker superConstructor) {
        AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible lowerIfFlexible;
        Intrinsics.checkNotNullParameter(findCorrespondingSupertypes, "$this$findCorrespondingSupertypes");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superConstructor, "superConstructor");
        if (findCorrespondingSupertypes.isClassType(subType)) {
            return collectAndFilter(findCorrespondingSupertypes, subType, superConstructor);
        }
        if (!findCorrespondingSupertypes.isClassTypeConstructor(superConstructor) && !findCorrespondingSupertypes.isIntegerLiteralTypeConstructor(superConstructor)) {
            return collectAllSupertypesWithGivenTypeConstructor(findCorrespondingSupertypes, subType, superConstructor);
        }
        SmartList<SimpleTypeMarker> smartList = new SmartList();
        findCorrespondingSupertypes.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = findCorrespondingSupertypes.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set<SimpleTypeMarker> supertypesSet = findCorrespondingSupertypes.getSupertypesSet();
        Intrinsics.checkNotNull(supertypesSet);
        supertypesDeque.push(subType);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + subType + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleTypeMarker current = supertypesDeque.pop();
            Intrinsics.checkNotNullExpressionValue(current, "current");
            if (supertypesSet.add(current)) {
                if (findCorrespondingSupertypes.isClassType(current)) {
                    smartList.add(current);
                    lowerIfFlexible = AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                } else {
                    lowerIfFlexible = AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                }
                if (!(!Intrinsics.areEqual(lowerIfFlexible, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                    lowerIfFlexible = null;
                }
                if (lowerIfFlexible != null) {
                    Iterator<KotlinTypeMarker> it = findCorrespondingSupertypes.supertypes(findCorrespondingSupertypes.typeConstructor(current)).iterator();
                    while (it.hasNext()) {
                        supertypesDeque.add(lowerIfFlexible.mo1272transformType(findCorrespondingSupertypes, it.next()));
                    }
                }
            }
        }
        findCorrespondingSupertypes.clear();
        ArrayList arrayList = new ArrayList();
        for (SimpleTypeMarker it2 : smartList) {
            AbstractTypeChecker abstractTypeChecker = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            CollectionsKt.addAll(arrayList, abstractTypeChecker.collectAndFilter(findCorrespondingSupertypes, it2, superConstructor));
        }
        return arrayList;
    }
}
