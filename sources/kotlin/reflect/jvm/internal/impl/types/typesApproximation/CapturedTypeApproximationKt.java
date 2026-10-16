package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: CapturedTypeApproximation.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class CapturedTypeApproximationKt {

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Variance.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Variance.INVARIANT.ordinal()] = 1;
            $EnumSwitchMapping$0[Variance.IN_VARIANCE.ordinal()] = 2;
            $EnumSwitchMapping$0[Variance.OUT_VARIANCE.ordinal()] = 3;
            int[] iArr2 = new int[Variance.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[Variance.IN_VARIANCE.ordinal()] = 1;
            $EnumSwitchMapping$1[Variance.OUT_VARIANCE.ordinal()] = 2;
        }
    }

    private static final TypeProjection toTypeProjection(final TypeArgument typeArgument) {
        TypeProjectionImpl typeProjectionImpl;
        boolean zIsConsistent = typeArgument.isConsistent();
        if (_Assertions.ENABLED && !zIsConsistent) {
            DescriptorRenderer descriptorRendererWithOptions = DescriptorRenderer.Companion.withOptions(new Function1<DescriptorRendererOptions, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt$toTypeProjection$1$descriptorRenderer$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DescriptorRendererOptions descriptorRendererOptions) {
                    invoke2(descriptorRendererOptions);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DescriptorRendererOptions receiver) {
                    Intrinsics.checkNotNullParameter(receiver, "$receiver");
                    receiver.setClassifierNamePolicy(ClassifierNamePolicy.FULLY_QUALIFIED.INSTANCE);
                }
            });
            throw new AssertionError("Only consistent enhanced type projection can be converted to type projection, but [" + descriptorRendererWithOptions.render(typeArgument.getTypeParameter()) + ": <" + descriptorRendererWithOptions.renderType(typeArgument.getInProjection()) + ", " + descriptorRendererWithOptions.renderType(typeArgument.getOutProjection()) + ">] was found");
        }
        Function1<Variance, Variance> function1 = new Function1<Variance, Variance>() { // from class: kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.toTypeProjection.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Variance invoke(Variance variance) {
                Intrinsics.checkNotNullParameter(variance, "variance");
                return variance == typeArgument.getTypeParameter().getVariance() ? Variance.INVARIANT : variance;
            }
        };
        if (Intrinsics.areEqual(typeArgument.getInProjection(), typeArgument.getOutProjection()) || typeArgument.getTypeParameter().getVariance() == Variance.IN_VARIANCE) {
            typeProjectionImpl = new TypeProjectionImpl(typeArgument.getInProjection());
        } else {
            typeProjectionImpl = ((!KotlinBuiltIns.isNothing(typeArgument.getInProjection()) || typeArgument.getTypeParameter().getVariance() == Variance.IN_VARIANCE) && KotlinBuiltIns.isNullableAny(typeArgument.getOutProjection())) ? new TypeProjectionImpl(function1.invoke(Variance.IN_VARIANCE), typeArgument.getInProjection()) : new TypeProjectionImpl(function1.invoke(Variance.OUT_VARIANCE), typeArgument.getOutProjection());
        }
        return typeProjectionImpl;
    }

    private static final TypeArgument toTypeArgument(TypeProjection typeProjection, TypeParameterDescriptor typeParameterDescriptor) {
        int i = WhenMappings.$EnumSwitchMapping$0[TypeSubstitutor.combine(typeParameterDescriptor.getVariance(), typeProjection).ordinal()];
        if (i == 1) {
            KotlinType type = typeProjection.getType();
            Intrinsics.checkNotNullExpressionValue(type, "type");
            KotlinType type2 = typeProjection.getType();
            Intrinsics.checkNotNullExpressionValue(type2, "type");
            return new TypeArgument(typeParameterDescriptor, type, type2);
        }
        if (i == 2) {
            KotlinType type3 = typeProjection.getType();
            Intrinsics.checkNotNullExpressionValue(type3, "type");
            SimpleType nullableAnyType = DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNullableAnyType();
            Intrinsics.checkNotNullExpressionValue(nullableAnyType, "typeParameter.builtIns.nullableAnyType");
            return new TypeArgument(typeParameterDescriptor, type3, nullableAnyType);
        }
        if (i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        SimpleType nothingType = DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNothingType();
        Intrinsics.checkNotNullExpressionValue(nothingType, "typeParameter.builtIns.nothingType");
        KotlinType type4 = typeProjection.getType();
        Intrinsics.checkNotNullExpressionValue(type4, "type");
        return new TypeArgument(typeParameterDescriptor, nothingType, type4);
    }

    public static final TypeProjection approximateCapturedTypesIfNecessary(TypeProjection typeProjection, boolean z) {
        if (typeProjection == null) {
            return null;
        }
        if (typeProjection.isStarProjection()) {
            return typeProjection;
        }
        KotlinType type = typeProjection.getType();
        Intrinsics.checkNotNullExpressionValue(type, "typeProjection.type");
        if (!TypeUtils.contains(type, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(UnwrappedType it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                return Boolean.valueOf(CapturedTypeConstructorKt.isCaptured(it));
            }
        })) {
            return typeProjection;
        }
        Variance projectionKind = typeProjection.getProjectionKind();
        Intrinsics.checkNotNullExpressionValue(projectionKind, "typeProjection.projectionKind");
        if (projectionKind == Variance.OUT_VARIANCE) {
            return new TypeProjectionImpl(projectionKind, approximateCapturedTypes(type).getUpper());
        }
        if (z) {
            return new TypeProjectionImpl(projectionKind, approximateCapturedTypes(type).getLower());
        }
        return substituteCapturedTypesWithProjections(typeProjection);
    }

    private static final TypeProjection substituteCapturedTypesWithProjections(TypeProjection typeProjection) {
        TypeSubstitutor typeSubstitutorCreate = TypeSubstitutor.create(new TypeConstructorSubstitution() { // from class: kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt$substituteCapturedTypesWithProjections$typeSubstitutor$1
            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
            public TypeProjection get(TypeConstructor key) {
                Intrinsics.checkNotNullParameter(key, "key");
                if (!(key instanceof CapturedTypeConstructor)) {
                    key = null;
                }
                CapturedTypeConstructor capturedTypeConstructor = (CapturedTypeConstructor) key;
                if (capturedTypeConstructor == null) {
                    return null;
                }
                if (capturedTypeConstructor.getProjection().isStarProjection()) {
                    return new TypeProjectionImpl(Variance.OUT_VARIANCE, capturedTypeConstructor.getProjection().getType());
                }
                return capturedTypeConstructor.getProjection();
            }
        });
        Intrinsics.checkNotNullExpressionValue(typeSubstitutorCreate, "TypeSubstitutor.create(o…ojection\n        }\n    })");
        return typeSubstitutorCreate.substituteWithoutApproximation(typeProjection);
    }

    public static final ApproximationBounds<KotlinType> approximateCapturedTypes(final KotlinType type) {
        SimpleType simpleTypeReplaceTypeArguments;
        Intrinsics.checkNotNullParameter(type, "type");
        if (FlexibleTypesKt.isFlexible(type)) {
            ApproximationBounds<KotlinType> approximationBoundsApproximateCapturedTypes = approximateCapturedTypes(FlexibleTypesKt.lowerIfFlexible(type));
            ApproximationBounds<KotlinType> approximationBoundsApproximateCapturedTypes2 = approximateCapturedTypes(FlexibleTypesKt.upperIfFlexible(type));
            return new ApproximationBounds<>(TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible(approximationBoundsApproximateCapturedTypes.getLower()), FlexibleTypesKt.upperIfFlexible(approximationBoundsApproximateCapturedTypes2.getLower())), type), TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible(approximationBoundsApproximateCapturedTypes.getUpper()), FlexibleTypesKt.upperIfFlexible(approximationBoundsApproximateCapturedTypes2.getUpper())), type));
        }
        TypeConstructor constructor = type.getConstructor();
        boolean z = true;
        if (CapturedTypeConstructorKt.isCaptured(type)) {
            if (constructor != null) {
                TypeProjection projection = ((CapturedTypeConstructor) constructor).getProjection();
                Function1<KotlinType, KotlinType> function1 = new Function1<KotlinType, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.approximateCapturedTypes.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final KotlinType invoke(KotlinType makeNullableIfNeeded) {
                        Intrinsics.checkNotNullParameter(makeNullableIfNeeded, "$this$makeNullableIfNeeded");
                        KotlinType kotlinTypeMakeNullableIfNeeded = TypeUtils.makeNullableIfNeeded(makeNullableIfNeeded, type.isMarkedNullable());
                        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNullableIfNeeded, "TypeUtils.makeNullableIf…s, type.isMarkedNullable)");
                        return kotlinTypeMakeNullableIfNeeded;
                    }
                };
                KotlinType type2 = projection.getType();
                Intrinsics.checkNotNullExpressionValue(type2, "typeProjection.type");
                KotlinType kotlinTypeInvoke = function1.invoke(type2);
                int i = WhenMappings.$EnumSwitchMapping$1[projection.getProjectionKind().ordinal()];
                if (i == 1) {
                    SimpleType nullableAnyType = TypeUtilsKt.getBuiltIns(type).getNullableAnyType();
                    Intrinsics.checkNotNullExpressionValue(nullableAnyType, "type.builtIns.nullableAnyType");
                    return new ApproximationBounds<>(kotlinTypeInvoke, nullableAnyType);
                }
                if (i == 2) {
                    SimpleType nothingType = TypeUtilsKt.getBuiltIns(type).getNothingType();
                    Intrinsics.checkNotNullExpressionValue(nothingType, "type.builtIns.nothingType");
                    return new ApproximationBounds<>(function1.invoke((KotlinType) nothingType), kotlinTypeInvoke);
                }
                throw new AssertionError("Only nontrivial projections should have been captured, not: " + projection);
            }
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.resolve.calls.inference.CapturedTypeConstructor");
        }
        if (type.getArguments().isEmpty() || type.getArguments().size() != constructor.getParameters().size()) {
            return new ApproximationBounds<>(type, type);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List<TypeProjection> arguments = type.getArguments();
        List<TypeParameterDescriptor> parameters = constructor.getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "typeConstructor.parameters");
        for (Pair pair : CollectionsKt.zip(arguments, parameters)) {
            TypeProjection typeProjection = (TypeProjection) pair.component1();
            TypeParameterDescriptor typeParameter = (TypeParameterDescriptor) pair.component2();
            Intrinsics.checkNotNullExpressionValue(typeParameter, "typeParameter");
            TypeArgument typeArgument = toTypeArgument(typeProjection, typeParameter);
            if (typeProjection.isStarProjection()) {
                arrayList.add(typeArgument);
                arrayList2.add(typeArgument);
            } else {
                ApproximationBounds<TypeArgument> approximationBoundsApproximateProjection = approximateProjection(typeArgument);
                TypeArgument typeArgumentComponent1 = approximationBoundsApproximateProjection.component1();
                TypeArgument typeArgumentComponent2 = approximationBoundsApproximateProjection.component2();
                arrayList.add(typeArgumentComponent1);
                arrayList2.add(typeArgumentComponent2);
            }
        }
        ArrayList arrayList3 = arrayList;
        if (!(arrayList3 instanceof Collection) || !arrayList3.isEmpty()) {
            Iterator it = arrayList3.iterator();
            do {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
            } while (!(!((TypeArgument) it.next()).isConsistent()));
        } else {
            z = false;
            break;
        }
        if (z) {
            SimpleType nothingType2 = TypeUtilsKt.getBuiltIns(type).getNothingType();
            Intrinsics.checkNotNullExpressionValue(nothingType2, "type.builtIns.nothingType");
            simpleTypeReplaceTypeArguments = nothingType2;
        } else {
            simpleTypeReplaceTypeArguments = replaceTypeArguments(type, arrayList);
        }
        return new ApproximationBounds<>(simpleTypeReplaceTypeArguments, replaceTypeArguments(type, arrayList2));
    }

    private static final KotlinType replaceTypeArguments(KotlinType kotlinType, List<TypeArgument> list) {
        boolean z = kotlinType.getArguments().size() == list.size();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Incorrect type arguments " + list);
        }
        List<TypeArgument> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(toTypeProjection((TypeArgument) it.next()));
        }
        return TypeSubstitutionKt.replace$default(kotlinType, arrayList, (Annotations) null, 2, (Object) null);
    }

    private static final ApproximationBounds<TypeArgument> approximateProjection(TypeArgument typeArgument) {
        ApproximationBounds<KotlinType> approximationBoundsApproximateCapturedTypes = approximateCapturedTypes(typeArgument.getInProjection());
        KotlinType kotlinTypeComponent1 = approximationBoundsApproximateCapturedTypes.component1();
        KotlinType kotlinTypeComponent2 = approximationBoundsApproximateCapturedTypes.component2();
        ApproximationBounds<KotlinType> approximationBoundsApproximateCapturedTypes2 = approximateCapturedTypes(typeArgument.getOutProjection());
        return new ApproximationBounds<>(new TypeArgument(typeArgument.getTypeParameter(), kotlinTypeComponent2, approximationBoundsApproximateCapturedTypes2.component1()), new TypeArgument(typeArgument.getTypeParameter(), kotlinTypeComponent1, approximationBoundsApproximateCapturedTypes2.component2()));
    }
}
