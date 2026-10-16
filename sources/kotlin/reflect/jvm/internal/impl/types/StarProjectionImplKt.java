package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

/* JADX INFO: compiled from: StarProjectionImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class StarProjectionImplKt {
    public static final KotlinType starProjectionType(TypeParameterDescriptor starProjectionType) {
        Intrinsics.checkNotNullParameter(starProjectionType, "$this$starProjectionType");
        DeclarationDescriptor containingDeclaration = starProjectionType.getContainingDeclaration();
        if (containingDeclaration == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters");
        }
        TypeConstructor typeConstructor = ((ClassifierDescriptorWithTypeParameters) containingDeclaration).getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "classDescriptor.typeConstructor");
        List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "classDescriptor.typeConstructor.parameters");
        List<TypeParameterDescriptor> list = parameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (TypeParameterDescriptor it : list) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            arrayList.add(it.getTypeConstructor());
        }
        final ArrayList arrayList2 = arrayList;
        TypeSubstitutor typeSubstitutorCreate = TypeSubstitutor.create(new TypeConstructorSubstitution() { // from class: kotlin.reflect.jvm.internal.impl.types.StarProjectionImplKt.starProjectionType.1
            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
            public TypeProjection get(TypeConstructor key) {
                Intrinsics.checkNotNullParameter(key, "key");
                if (!arrayList2.contains(key)) {
                    return null;
                }
                ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = key.mo1268getDeclarationDescriptor();
                if (classifierDescriptorMo1268getDeclarationDescriptor != null) {
                    return TypeUtils.makeStarProjection((TypeParameterDescriptor) classifierDescriptorMo1268getDeclarationDescriptor);
                }
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
            }
        });
        List<KotlinType> upperBounds = starProjectionType.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "this.upperBounds");
        KotlinType kotlinTypeSubstitute = typeSubstitutorCreate.substitute((KotlinType) CollectionsKt.first((List) upperBounds), Variance.OUT_VARIANCE);
        if (kotlinTypeSubstitute != null) {
            return kotlinTypeSubstitute;
        }
        SimpleType defaultBound = DescriptorUtilsKt.getBuiltIns(starProjectionType).getDefaultBound();
        Intrinsics.checkNotNullExpressionValue(defaultBound, "builtIns.defaultBound");
        return defaultBound;
    }
}
