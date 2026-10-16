package kotlin.reflect.jvm.internal.impl.types.typeUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.StubType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;

/* JADX INFO: compiled from: TypeUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class TypeUtilsKt {
    public static final KotlinBuiltIns getBuiltIns(KotlinType builtIns) {
        Intrinsics.checkNotNullParameter(builtIns, "$this$builtIns");
        KotlinBuiltIns builtIns2 = builtIns.getConstructor().getBuiltIns();
        Intrinsics.checkNotNullExpressionValue(builtIns2, "constructor.builtIns");
        return builtIns2;
    }

    public static final KotlinType makeNullable(KotlinType makeNullable) {
        Intrinsics.checkNotNullParameter(makeNullable, "$this$makeNullable");
        KotlinType kotlinTypeMakeNullable = TypeUtils.makeNullable(makeNullable);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNullable, "TypeUtils.makeNullable(this)");
        return kotlinTypeMakeNullable;
    }

    public static final KotlinType makeNotNullable(KotlinType makeNotNullable) {
        Intrinsics.checkNotNullParameter(makeNotNullable, "$this$makeNotNullable");
        KotlinType kotlinTypeMakeNotNullable = TypeUtils.makeNotNullable(makeNotNullable);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNotNullable, "TypeUtils.makeNotNullable(this)");
        return kotlinTypeMakeNotNullable;
    }

    public static final boolean isTypeParameter(KotlinType isTypeParameter) {
        Intrinsics.checkNotNullParameter(isTypeParameter, "$this$isTypeParameter");
        return TypeUtils.isTypeParameter(isTypeParameter);
    }

    public static final boolean isSubtypeOf(KotlinType isSubtypeOf, KotlinType superType) {
        Intrinsics.checkNotNullParameter(isSubtypeOf, "$this$isSubtypeOf");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return KotlinTypeChecker.DEFAULT.isSubtypeOf(isSubtypeOf, superType);
    }

    public static final KotlinType replaceAnnotations(KotlinType replaceAnnotations, Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter(replaceAnnotations, "$this$replaceAnnotations");
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        return (replaceAnnotations.getAnnotations().isEmpty() && newAnnotations.isEmpty()) ? replaceAnnotations : replaceAnnotations.unwrap().replaceAnnotations(newAnnotations);
    }

    public static final TypeProjection createProjection(KotlinType type, Variance projectionKind, TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(projectionKind, "projectionKind");
        if ((typeParameterDescriptor != null ? typeParameterDescriptor.getVariance() : null) == projectionKind) {
            projectionKind = Variance.INVARIANT;
        }
        return new TypeProjectionImpl(projectionKind, type);
    }

    public static final TypeProjection asTypeProjection(KotlinType asTypeProjection) {
        Intrinsics.checkNotNullParameter(asTypeProjection, "$this$asTypeProjection");
        return new TypeProjectionImpl(asTypeProjection);
    }

    public static final boolean contains(KotlinType contains, Function1<? super UnwrappedType, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(contains, "$this$contains");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return TypeUtils.contains(contains, predicate);
    }

    public static final boolean containsTypeAliasParameters(KotlinType containsTypeAliasParameters) {
        Intrinsics.checkNotNullParameter(containsTypeAliasParameters, "$this$containsTypeAliasParameters");
        return contains(containsTypeAliasParameters, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeAliasParameters.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(UnwrappedType unwrappedType) {
                return Boolean.valueOf(invoke2(unwrappedType));
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(UnwrappedType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = it.getConstructor().mo1268getDeclarationDescriptor();
                if (classifierDescriptorMo1268getDeclarationDescriptor != null) {
                    return TypeUtilsKt.isTypeAliasParameter(classifierDescriptorMo1268getDeclarationDescriptor);
                }
                return false;
            }
        });
    }

    public static final boolean isTypeAliasParameter(ClassifierDescriptor isTypeAliasParameter) {
        Intrinsics.checkNotNullParameter(isTypeAliasParameter, "$this$isTypeAliasParameter");
        return (isTypeAliasParameter instanceof TypeParameterDescriptor) && (((TypeParameterDescriptor) isTypeAliasParameter).getContainingDeclaration() instanceof TypeAliasDescriptor);
    }

    public static final boolean requiresTypeAliasExpansion(KotlinType requiresTypeAliasExpansion) {
        Intrinsics.checkNotNullParameter(requiresTypeAliasExpansion, "$this$requiresTypeAliasExpansion");
        return contains(requiresTypeAliasExpansion, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.requiresTypeAliasExpansion.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(UnwrappedType unwrappedType) {
                return Boolean.valueOf(invoke2(unwrappedType));
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(UnwrappedType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = it.getConstructor().mo1268getDeclarationDescriptor();
                if (classifierDescriptorMo1268getDeclarationDescriptor != null) {
                    return (classifierDescriptorMo1268getDeclarationDescriptor instanceof TypeAliasDescriptor) || (classifierDescriptorMo1268getDeclarationDescriptor instanceof TypeParameterDescriptor);
                }
                return false;
            }
        });
    }

    public static final KotlinType getRepresentativeUpperBound(TypeParameterDescriptor representativeUpperBound) {
        Object obj;
        Intrinsics.checkNotNullParameter(representativeUpperBound, "$this$representativeUpperBound");
        List<KotlinType> upperBounds = representativeUpperBound.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "upperBounds");
        boolean z = !upperBounds.isEmpty();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Upper bounds should not be empty: " + representativeUpperBound);
        }
        List<KotlinType> upperBounds2 = representativeUpperBound.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds2, "upperBounds");
        Iterator<T> it = upperBounds2.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = ((KotlinType) next).getConstructor().mo1268getDeclarationDescriptor();
            ClassDescriptor classDescriptor = (ClassDescriptor) (classifierDescriptorMo1268getDeclarationDescriptor instanceof ClassDescriptor ? classifierDescriptorMo1268getDeclarationDescriptor : null);
            boolean z2 = false;
            if (classDescriptor != null && classDescriptor.getKind() != ClassKind.INTERFACE && classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
                z2 = true;
            }
            if (z2) {
                obj = next;
                break;
            }
        }
        KotlinType kotlinType = (KotlinType) obj;
        if (kotlinType != null) {
            return kotlinType;
        }
        List<KotlinType> upperBounds3 = representativeUpperBound.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds3, "upperBounds");
        Object objFirst = CollectionsKt.first((List<? extends Object>) upperBounds3);
        Intrinsics.checkNotNullExpressionValue(objFirst, "upperBounds.first()");
        return (KotlinType) objFirst;
    }

    public static final boolean shouldBeSubstituted(KotlinType shouldBeSubstituted) {
        Intrinsics.checkNotNullParameter(shouldBeSubstituted, "$this$shouldBeSubstituted");
        return contains(shouldBeSubstituted, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.shouldBeSubstituted.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(UnwrappedType unwrappedType) {
                return Boolean.valueOf(invoke2(unwrappedType));
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(UnwrappedType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return (it instanceof StubType) || (it.getConstructor() instanceof TypeVariableTypeConstructorMarker);
            }
        });
    }

    public static final KotlinType replaceArgumentsWithStarProjections(KotlinType replaceArgumentsWithStarProjections) {
        SimpleType simpleTypeFlexibleType;
        Intrinsics.checkNotNullParameter(replaceArgumentsWithStarProjections, "$this$replaceArgumentsWithStarProjections");
        UnwrappedType unwrappedTypeUnwrap = replaceArgumentsWithStarProjections.unwrap();
        if (unwrappedTypeUnwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedTypeUnwrap;
            SimpleType lowerBound = flexibleType.getLowerBound();
            if (!lowerBound.getConstructor().getParameters().isEmpty() && lowerBound.getConstructor().mo1268getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters = lowerBound.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters, "constructor.parameters");
                List<TypeParameterDescriptor> list = parameters;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(new StarProjectionImpl((TypeParameterDescriptor) it.next()));
                }
                lowerBound = TypeSubstitutionKt.replace$default(lowerBound, (List) arrayList, (Annotations) null, 2, (Object) null);
            }
            SimpleType upperBound = flexibleType.getUpperBound();
            if (!upperBound.getConstructor().getParameters().isEmpty() && upperBound.getConstructor().mo1268getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters2 = upperBound.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters2, "constructor.parameters");
                List<TypeParameterDescriptor> list2 = parameters2;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it2 = list2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new StarProjectionImpl((TypeParameterDescriptor) it2.next()));
                }
                upperBound = TypeSubstitutionKt.replace$default(upperBound, (List) arrayList2, (Annotations) null, 2, (Object) null);
            }
            simpleTypeFlexibleType = KotlinTypeFactory.flexibleType(lowerBound, upperBound);
        } else if (unwrappedTypeUnwrap instanceof SimpleType) {
            SimpleType simpleTypeReplace$default = (SimpleType) unwrappedTypeUnwrap;
            if (!simpleTypeReplace$default.getConstructor().getParameters().isEmpty() && simpleTypeReplace$default.getConstructor().mo1268getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters3 = simpleTypeReplace$default.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters3, "constructor.parameters");
                List<TypeParameterDescriptor> list3 = parameters3;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                Iterator<T> it3 = list3.iterator();
                while (it3.hasNext()) {
                    arrayList3.add(new StarProjectionImpl((TypeParameterDescriptor) it3.next()));
                }
                simpleTypeReplace$default = TypeSubstitutionKt.replace$default(simpleTypeReplace$default, (List) arrayList3, (Annotations) null, 2, (Object) null);
            }
            simpleTypeFlexibleType = simpleTypeReplace$default;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(simpleTypeFlexibleType, unwrappedTypeUnwrap);
    }
}
