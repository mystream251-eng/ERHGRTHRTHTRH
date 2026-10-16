package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: typeParameterUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class TypeParameterUtilsKt {
    public static final List<TypeParameterDescriptor> computeConstructorTypeParameters(ClassifierDescriptorWithTypeParameters computeConstructorTypeParameters) {
        List<TypeParameterDescriptor> listEmptyList;
        DeclarationDescriptor next;
        TypeConstructor typeConstructor;
        Intrinsics.checkNotNullParameter(computeConstructorTypeParameters, "$this$computeConstructorTypeParameters");
        List<TypeParameterDescriptor> declaredTypeParameters = computeConstructorTypeParameters.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(declaredTypeParameters, "declaredTypeParameters");
        if (!computeConstructorTypeParameters.isInner() && !(computeConstructorTypeParameters.getContainingDeclaration() instanceof CallableDescriptor)) {
            return declaredTypeParameters;
        }
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters = computeConstructorTypeParameters;
        List list = SequencesKt.toList(SequencesKt.flatMap(SequencesKt.filter(SequencesKt.takeWhile(DescriptorUtilsKt.getParents(classifierDescriptorWithTypeParameters), new Function1<DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor) {
                return Boolean.valueOf(invoke2(declarationDescriptor));
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(DeclarationDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof CallableDescriptor;
            }
        }), new Function1<DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor) {
                return Boolean.valueOf(invoke2(declarationDescriptor));
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(DeclarationDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return !(it instanceof ConstructorDescriptor);
            }
        }), new Function1<DeclarationDescriptor, Sequence<? extends TypeParameterDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$3
            @Override // kotlin.jvm.functions.Function1
            public final Sequence<TypeParameterDescriptor> invoke(DeclarationDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                List<TypeParameterDescriptor> typeParameters = ((CallableDescriptor) it).getTypeParameters();
                Intrinsics.checkNotNullExpressionValue(typeParameters, "(it as CallableDescriptor).typeParameters");
                return CollectionsKt.asSequence(typeParameters);
            }
        }));
        Iterator<DeclarationDescriptor> it = DescriptorUtilsKt.getParents(classifierDescriptorWithTypeParameters).iterator();
        do {
            listEmptyList = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(next instanceof ClassDescriptor));
        ClassDescriptor classDescriptor = (ClassDescriptor) next;
        if (classDescriptor != null && (typeConstructor = classDescriptor.getTypeConstructor()) != null) {
            listEmptyList = typeConstructor.getParameters();
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        if (list.isEmpty() && listEmptyList.isEmpty()) {
            List<TypeParameterDescriptor> declaredTypeParameters2 = computeConstructorTypeParameters.getDeclaredTypeParameters();
            Intrinsics.checkNotNullExpressionValue(declaredTypeParameters2, "declaredTypeParameters");
            return declaredTypeParameters2;
        }
        List<TypeParameterDescriptor> listPlus = CollectionsKt.plus((Collection) list, (Iterable) listEmptyList);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
        for (TypeParameterDescriptor it2 : listPlus) {
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            arrayList.add(capturedCopyForInnerDeclaration(it2, classifierDescriptorWithTypeParameters, declaredTypeParameters.size()));
        }
        return CollectionsKt.plus((Collection) declaredTypeParameters, (Iterable) arrayList);
    }

    private static final CapturedTypeParameterDescriptor capturedCopyForInnerDeclaration(TypeParameterDescriptor typeParameterDescriptor, DeclarationDescriptor declarationDescriptor, int i) {
        return new CapturedTypeParameterDescriptor(typeParameterDescriptor, declarationDescriptor, i);
    }

    public static final PossiblyInnerType buildPossiblyInnerType(KotlinType buildPossiblyInnerType) {
        Intrinsics.checkNotNullParameter(buildPossiblyInnerType, "$this$buildPossiblyInnerType");
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = buildPossiblyInnerType.getConstructor().mo1268getDeclarationDescriptor();
        if (!(classifierDescriptorMo1268getDeclarationDescriptor instanceof ClassifierDescriptorWithTypeParameters)) {
            classifierDescriptorMo1268getDeclarationDescriptor = null;
        }
        return buildPossiblyInnerType(buildPossiblyInnerType, (ClassifierDescriptorWithTypeParameters) classifierDescriptorMo1268getDeclarationDescriptor, 0);
    }

    private static final PossiblyInnerType buildPossiblyInnerType(KotlinType kotlinType, ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters, int i) {
        if (classifierDescriptorWithTypeParameters != null) {
            ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters2 = classifierDescriptorWithTypeParameters;
            if (!ErrorUtils.isError(classifierDescriptorWithTypeParameters2)) {
                int size = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters().size() + i;
                if (!classifierDescriptorWithTypeParameters.isInner()) {
                    boolean z = size == kotlinType.getArguments().size() || DescriptorUtils.isLocal(classifierDescriptorWithTypeParameters2);
                    if (_Assertions.ENABLED && !z) {
                        throw new AssertionError((kotlinType.getArguments().size() - size) + " trailing arguments were found in " + kotlinType + " type");
                    }
                    return new PossiblyInnerType(classifierDescriptorWithTypeParameters, kotlinType.getArguments().subList(i, kotlinType.getArguments().size()), null);
                }
                List<TypeProjection> listSubList = kotlinType.getArguments().subList(i, size);
                DeclarationDescriptor containingDeclaration = classifierDescriptorWithTypeParameters.getContainingDeclaration();
                return new PossiblyInnerType(classifierDescriptorWithTypeParameters, listSubList, buildPossiblyInnerType(kotlinType, (ClassifierDescriptorWithTypeParameters) (containingDeclaration instanceof ClassifierDescriptorWithTypeParameters ? containingDeclaration : null), size));
            }
        }
        return null;
    }
}
