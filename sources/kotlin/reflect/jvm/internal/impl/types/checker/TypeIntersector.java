package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* JADX INFO: compiled from: IntersectionType.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class TypeIntersector {
    public static final TypeIntersector INSTANCE = new TypeIntersector();

    private TypeIntersector() {
    }

    public final SimpleType intersectTypes$descriptors(List<? extends SimpleType> types) {
        Intrinsics.checkNotNullParameter(types, "types");
        boolean z = types.size() > 1;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Size should be at least 2, but it is " + types.size());
        }
        ArrayList arrayList = new ArrayList();
        for (SimpleType simpleType : types) {
            if (simpleType.getConstructor() instanceof IntersectionTypeConstructor) {
                Collection<KotlinType> collectionMo1269getSupertypes = simpleType.getConstructor().mo1269getSupertypes();
                Intrinsics.checkNotNullExpressionValue(collectionMo1269getSupertypes, "type.constructor.supertypes");
                Collection<KotlinType> collection = collectionMo1269getSupertypes;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
                for (KotlinType it : collection) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    SimpleType simpleTypeUpperIfFlexible = FlexibleTypesKt.upperIfFlexible(it);
                    if (simpleType.isMarkedNullable()) {
                        simpleTypeUpperIfFlexible = simpleTypeUpperIfFlexible.makeNullableAsSpecified(true);
                    }
                    arrayList2.add(simpleTypeUpperIfFlexible);
                }
                arrayList.addAll(arrayList2);
            } else {
                arrayList.add(simpleType);
            }
        }
        ArrayList<SimpleType> arrayList3 = arrayList;
        ResultNullability resultNullabilityCombine = ResultNullability.START;
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            resultNullabilityCombine = resultNullabilityCombine.combine((UnwrappedType) it2.next());
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (NewCapturedType newCapturedTypeMakeSimpleTypeDefinitelyNotNullOrNotNull$default : arrayList3) {
            if (resultNullabilityCombine == ResultNullability.NOT_NULL) {
                if (newCapturedTypeMakeSimpleTypeDefinitelyNotNullOrNotNull$default instanceof NewCapturedType) {
                    newCapturedTypeMakeSimpleTypeDefinitelyNotNullOrNotNull$default = SpecialTypesKt.withNotNullProjection((NewCapturedType) newCapturedTypeMakeSimpleTypeDefinitelyNotNullOrNotNull$default);
                }
                newCapturedTypeMakeSimpleTypeDefinitelyNotNullOrNotNull$default = SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull$default(newCapturedTypeMakeSimpleTypeDefinitelyNotNullOrNotNull$default, false, 1, null);
            }
            linkedHashSet.add(newCapturedTypeMakeSimpleTypeDefinitelyNotNullOrNotNull$default);
        }
        return intersectTypesWithoutIntersectionType(linkedHashSet);
    }

    private final SimpleType intersectTypesWithoutIntersectionType(final Set<? extends SimpleType> set) {
        if (set.size() == 1) {
            return (SimpleType) CollectionsKt.single(set);
        }
        Function0<String> function0 = new Function0<String>() { // from class: kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector$intersectTypesWithoutIntersectionType$errorMessage$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "This collections cannot be empty! input types: " + CollectionsKt.joinToString$default(set, null, null, null, 0, null, null, 63, null);
            }
        };
        Set<? extends SimpleType> set2 = set;
        Collection<SimpleType> collectionFilterTypes = filterTypes(set2, new TypeIntersector$intersectTypesWithoutIntersectionType$filteredEqualTypes$1(this));
        boolean z = !collectionFilterTypes.isEmpty();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(function0.invoke());
        }
        SimpleType simpleTypeFindIntersectionType = IntegerLiteralTypeConstructor.Companion.findIntersectionType(collectionFilterTypes);
        if (simpleTypeFindIntersectionType != null) {
            return simpleTypeFindIntersectionType;
        }
        Collection<SimpleType> collectionFilterTypes2 = filterTypes(collectionFilterTypes, new TypeIntersector$intersectTypesWithoutIntersectionType$filteredSuperAndEqualTypes$1(NewKotlinTypeChecker.Companion.getDefault()));
        boolean zIsEmpty = true ^ collectionFilterTypes2.isEmpty();
        if (!_Assertions.ENABLED || zIsEmpty) {
            return collectionFilterTypes2.size() < 2 ? (SimpleType) CollectionsKt.single(collectionFilterTypes2) : new IntersectionTypeConstructor(set2).createType();
        }
        throw new AssertionError(function0.invoke());
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0058  */
    private final Collection<SimpleType> filterTypes(Collection<? extends SimpleType> collection, Function2<? super SimpleType, ? super SimpleType, Boolean> function2) {
        boolean z;
        ArrayList arrayList = new ArrayList(collection);
        Iterator it = arrayList.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "filteredTypes.iterator()");
        while (it.hasNext()) {
            SimpleType upper = (SimpleType) it.next();
            ArrayList arrayList2 = arrayList;
            boolean z2 = true;
            if (!(arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
                Iterator it2 = arrayList2.iterator();
                do {
                    if (!it2.hasNext()) {
                        z2 = false;
                        break;
                    }
                    SimpleType lower = (SimpleType) it2.next();
                    if (lower != upper) {
                        Intrinsics.checkNotNullExpressionValue(lower, "lower");
                        Intrinsics.checkNotNullExpressionValue(upper, "upper");
                        if (function2.invoke(lower, upper).booleanValue()) {
                            z = true;
                        } else {
                            z = false;
                        }
                    } else {
                        z = false;
                    }
                } while (!z);
            } else {
                z2 = false;
                break;
            }
            if (z2) {
                it.remove();
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isStrictSupertype(KotlinType kotlinType, KotlinType kotlinType2) {
        NewKotlinTypeCheckerImpl newKotlinTypeCheckerImpl = NewKotlinTypeChecker.Companion.getDefault();
        return newKotlinTypeCheckerImpl.isSubtypeOf(kotlinType, kotlinType2) && !newKotlinTypeCheckerImpl.isSubtypeOf(kotlinType2, kotlinType);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: IntersectionType.kt */
    private static final class ResultNullability {
        private static final /* synthetic */ ResultNullability[] $VALUES;
        public static final ResultNullability ACCEPT_NULL;
        public static final ResultNullability NOT_NULL;
        public static final ResultNullability START;
        public static final ResultNullability UNKNOWN;

        static {
            START start = new START("START", 0);
            START = start;
            ACCEPT_NULL accept_null = new ACCEPT_NULL("ACCEPT_NULL", 1);
            ACCEPT_NULL = accept_null;
            UNKNOWN unknown = new UNKNOWN("UNKNOWN", 2);
            UNKNOWN = unknown;
            NOT_NULL not_null = new NOT_NULL("NOT_NULL", 3);
            NOT_NULL = not_null;
            $VALUES = new ResultNullability[]{start, accept_null, unknown, not_null};
        }

        public static ResultNullability valueOf(String str) {
            return (ResultNullability) Enum.valueOf(ResultNullability.class, str);
        }

        public static ResultNullability[] values() {
            return (ResultNullability[]) $VALUES.clone();
        }

        public abstract ResultNullability combine(UnwrappedType unwrappedType);

        /* JADX INFO: compiled from: IntersectionType.kt */
        static final class START extends ResultNullability {
            START(String str, int i) {
                super(str, i, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            public ResultNullability combine(UnwrappedType nextType) {
                Intrinsics.checkNotNullParameter(nextType, "nextType");
                return getResultNullability(nextType);
            }
        }

        private ResultNullability(String str, int i) {
            super(str, i);
        }

        public /* synthetic */ ResultNullability(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i);
        }

        /* JADX INFO: compiled from: IntersectionType.kt */
        static final class ACCEPT_NULL extends ResultNullability {
            ACCEPT_NULL(String str, int i) {
                super(str, i, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            public ResultNullability combine(UnwrappedType nextType) {
                Intrinsics.checkNotNullParameter(nextType, "nextType");
                return getResultNullability(nextType);
            }
        }

        /* JADX INFO: compiled from: IntersectionType.kt */
        static final class UNKNOWN extends ResultNullability {
            UNKNOWN(String str, int i) {
                super(str, i, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            public ResultNullability combine(UnwrappedType nextType) {
                Intrinsics.checkNotNullParameter(nextType, "nextType");
                ResultNullability resultNullability = getResultNullability(nextType);
                return resultNullability == ResultNullability.ACCEPT_NULL ? this : resultNullability;
            }
        }

        /* JADX INFO: compiled from: IntersectionType.kt */
        static final class NOT_NULL extends ResultNullability {
            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            public NOT_NULL combine(UnwrappedType nextType) {
                Intrinsics.checkNotNullParameter(nextType, "nextType");
                return this;
            }

            NOT_NULL(String str, int i) {
                super(str, i, null);
            }
        }

        protected final ResultNullability getResultNullability(UnwrappedType resultNullability) {
            Intrinsics.checkNotNullParameter(resultNullability, "$this$resultNullability");
            if (resultNullability.isMarkedNullable()) {
                return ACCEPT_NULL;
            }
            return NullabilityChecker.INSTANCE.isSubtypeOfAny(resultNullability) ? NOT_NULL : UNKNOWN;
        }
    }
}
