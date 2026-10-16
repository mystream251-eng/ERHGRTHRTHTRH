package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Set;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;

/* JADX INFO: compiled from: AbstractTypeChecker.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class AbstractNullabilityChecker {
    public static final AbstractNullabilityChecker INSTANCE = new AbstractNullabilityChecker();

    private AbstractNullabilityChecker() {
    }

    public final boolean isPossibleSubtype(AbstractTypeCheckerContext context, SimpleTypeMarker subType, SimpleTypeMarker superType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return runIsPossibleSubtype(context, subType, superType);
    }

    private final boolean runIsPossibleSubtype(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        if (AbstractTypeChecker.RUN_SLOW_ASSERTIONS) {
            boolean z = abstractTypeCheckerContext.isSingleClassifierType(simpleTypeMarker) || abstractTypeCheckerContext.isIntersection(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker)) || abstractTypeCheckerContext.isAllowedTypeVariable(simpleTypeMarker);
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Not singleClassifierType and not intersection subType: " + simpleTypeMarker);
            }
            boolean z2 = abstractTypeCheckerContext.isSingleClassifierType(simpleTypeMarker2) || abstractTypeCheckerContext.isAllowedTypeVariable(simpleTypeMarker2);
            if (_Assertions.ENABLED && !z2) {
                throw new AssertionError("Not singleClassifierType superType: " + simpleTypeMarker2);
            }
        }
        if (abstractTypeCheckerContext.isMarkedNullable(simpleTypeMarker2) || abstractTypeCheckerContext.isDefinitelyNotNullType(simpleTypeMarker)) {
            return true;
        }
        if (((simpleTypeMarker instanceof CapturedTypeMarker) && abstractTypeCheckerContext.isProjectionNotNull((CapturedTypeMarker) simpleTypeMarker)) || hasNotNullSupertype(abstractTypeCheckerContext, simpleTypeMarker, AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE)) {
            return true;
        }
        if (abstractTypeCheckerContext.isDefinitelyNotNullType(simpleTypeMarker2) || hasNotNullSupertype(abstractTypeCheckerContext, simpleTypeMarker2, AbstractTypeCheckerContext.SupertypesPolicy.UpperIfFlexible.INSTANCE) || abstractTypeCheckerContext.isClassType(simpleTypeMarker)) {
            return false;
        }
        return hasPathByNotMarkedNullableNodes(abstractTypeCheckerContext, simpleTypeMarker, abstractTypeCheckerContext.typeConstructor(simpleTypeMarker2));
    }

    public final boolean hasNotNullSupertype(AbstractTypeCheckerContext hasNotNullSupertype, SimpleTypeMarker type, AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicy) {
        Intrinsics.checkNotNullParameter(hasNotNullSupertype, "$this$hasNotNullSupertype");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(supertypesPolicy, "supertypesPolicy");
        if (!((hasNotNullSupertype.isClassType(type) && !hasNotNullSupertype.isMarkedNullable(type)) || hasNotNullSupertype.isDefinitelyNotNullType(type))) {
            hasNotNullSupertype.initialize();
            ArrayDeque<SimpleTypeMarker> supertypesDeque = hasNotNullSupertype.getSupertypesDeque();
            Intrinsics.checkNotNull(supertypesDeque);
            Set<SimpleTypeMarker> supertypesSet = hasNotNullSupertype.getSupertypesSet();
            Intrinsics.checkNotNull(supertypesSet);
            supertypesDeque.push(type);
            while (!supertypesDeque.isEmpty()) {
                if (supertypesSet.size() > 1000) {
                    throw new IllegalStateException(("Too many supertypes for type: " + type + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
                }
                SimpleTypeMarker current = supertypesDeque.pop();
                Intrinsics.checkNotNullExpressionValue(current, "current");
                if (supertypesSet.add(current)) {
                    AbstractTypeCheckerContext.SupertypesPolicy.None none = hasNotNullSupertype.isMarkedNullable(current) ? AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE : supertypesPolicy;
                    if (!(!Intrinsics.areEqual(none, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                        none = null;
                    }
                    if (none != null) {
                        Iterator<KotlinTypeMarker> it = hasNotNullSupertype.supertypes(hasNotNullSupertype.typeConstructor(current)).iterator();
                        while (it.hasNext()) {
                            SimpleTypeMarker simpleTypeMarkerMo1272transformType = none.mo1272transformType(hasNotNullSupertype, it.next());
                            if ((hasNotNullSupertype.isClassType(simpleTypeMarkerMo1272transformType) && !hasNotNullSupertype.isMarkedNullable(simpleTypeMarkerMo1272transformType)) || hasNotNullSupertype.isDefinitelyNotNullType(simpleTypeMarkerMo1272transformType)) {
                                hasNotNullSupertype.clear();
                            } else {
                                supertypesDeque.add(simpleTypeMarkerMo1272transformType);
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
            hasNotNullSupertype.clear();
            return false;
        }
        return true;
    }

    public final boolean hasPathByNotMarkedNullableNodes(AbstractTypeCheckerContext hasPathByNotMarkedNullableNodes, SimpleTypeMarker start, TypeConstructorMarker end) {
        Intrinsics.checkNotNullParameter(hasPathByNotMarkedNullableNodes, "$this$hasPathByNotMarkedNullableNodes");
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        if (INSTANCE.isApplicableAsEndNode(hasPathByNotMarkedNullableNodes, start, end)) {
            return true;
        }
        hasPathByNotMarkedNullableNodes.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = hasPathByNotMarkedNullableNodes.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set<SimpleTypeMarker> supertypesSet = hasPathByNotMarkedNullableNodes.getSupertypesSet();
        Intrinsics.checkNotNull(supertypesSet);
        supertypesDeque.push(start);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + start + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleTypeMarker current = supertypesDeque.pop();
            Intrinsics.checkNotNullExpressionValue(current, "current");
            if (supertypesSet.add(current)) {
                AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicy = hasPathByNotMarkedNullableNodes.isMarkedNullable(current) ? AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE : AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                if (!(!Intrinsics.areEqual(supertypesPolicy, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                    supertypesPolicy = null;
                }
                if (supertypesPolicy != null) {
                    Iterator<KotlinTypeMarker> it = hasPathByNotMarkedNullableNodes.supertypes(hasPathByNotMarkedNullableNodes.typeConstructor(current)).iterator();
                    while (it.hasNext()) {
                        SimpleTypeMarker simpleTypeMarkerMo1272transformType = supertypesPolicy.mo1272transformType(hasPathByNotMarkedNullableNodes, it.next());
                        if (INSTANCE.isApplicableAsEndNode(hasPathByNotMarkedNullableNodes, simpleTypeMarkerMo1272transformType, end)) {
                            hasPathByNotMarkedNullableNodes.clear();
                            return true;
                        }
                        supertypesDeque.add(simpleTypeMarkerMo1272transformType);
                    }
                } else {
                    continue;
                }
            }
        }
        hasPathByNotMarkedNullableNodes.clear();
        return false;
    }

    private final boolean isApplicableAsEndNode(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        if (abstractTypeCheckerContext.isNothing(simpleTypeMarker)) {
            return true;
        }
        if (abstractTypeCheckerContext.isMarkedNullable(simpleTypeMarker)) {
            return false;
        }
        if (abstractTypeCheckerContext.isStubTypeEqualsToAnything() && abstractTypeCheckerContext.isStubType(simpleTypeMarker)) {
            return true;
        }
        return abstractTypeCheckerContext.areEqualTypeConstructors(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker), typeConstructorMarker);
    }
}
