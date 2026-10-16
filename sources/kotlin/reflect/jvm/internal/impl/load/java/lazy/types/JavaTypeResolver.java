package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMapper;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifier;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPrimitiveType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: JavaTypeResolver.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class JavaTypeResolver {
    private final LazyJavaResolverContext c;
    private final TypeParameterResolver typeParameterResolver;

    /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver$argumentsMakeSenseOnlyForMutableContainer$1, reason: invalid class name */
    /* JADX INFO: compiled from: JavaTypeResolver.kt */
    static final class AnonymousClass1 extends Lambda implements Function1<JavaType, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(JavaType javaType) {
            return Boolean.valueOf(invoke2(javaType));
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(JavaType javaType) {
            if (!(javaType instanceof JavaWildcardType)) {
                javaType = null;
            }
            JavaWildcardType javaWildcardType = (JavaWildcardType) javaType;
            return (javaWildcardType == null || javaWildcardType.getBound() == null || javaWildcardType.isExtends()) ? false : true;
        }
    }

    public JavaTypeResolver(LazyJavaResolverContext c, TypeParameterResolver typeParameterResolver) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeParameterResolver, "typeParameterResolver");
        this.c = c;
        this.typeParameterResolver = typeParameterResolver;
    }

    public final KotlinType transformJavaType(JavaType javaType, JavaTypeAttributes attr) {
        KotlinType kotlinTypeTransformJavaType;
        SimpleType unitType;
        Intrinsics.checkNotNullParameter(attr, "attr");
        if (javaType instanceof JavaPrimitiveType) {
            PrimitiveType type = ((JavaPrimitiveType) javaType).getType();
            if (type != null) {
                unitType = this.c.getModule().getBuiltIns().getPrimitiveKotlinType(type);
            } else {
                unitType = this.c.getModule().getBuiltIns().getUnitType();
            }
            Intrinsics.checkNotNullExpressionValue(unitType, "if (primitiveType != nul….module.builtIns.unitType");
            return unitType;
        }
        if (javaType instanceof JavaClassifierType) {
            return transformJavaClassifierType((JavaClassifierType) javaType, attr);
        }
        if (javaType instanceof JavaArrayType) {
            return transformArrayType$default(this, (JavaArrayType) javaType, attr, false, 4, null);
        }
        if (javaType instanceof JavaWildcardType) {
            JavaType bound = ((JavaWildcardType) javaType).getBound();
            if (bound != null && (kotlinTypeTransformJavaType = transformJavaType(bound, attr)) != null) {
                return kotlinTypeTransformJavaType;
            }
            SimpleType defaultBound = this.c.getModule().getBuiltIns().getDefaultBound();
            Intrinsics.checkNotNullExpressionValue(defaultBound, "c.module.builtIns.defaultBound");
            return defaultBound;
        }
        if (javaType == null) {
            SimpleType defaultBound2 = this.c.getModule().getBuiltIns().getDefaultBound();
            Intrinsics.checkNotNullExpressionValue(defaultBound2, "c.module.builtIns.defaultBound");
            return defaultBound2;
        }
        throw new UnsupportedOperationException("Unsupported type: " + javaType);
    }

    public static /* synthetic */ KotlinType transformArrayType$default(JavaTypeResolver javaTypeResolver, JavaArrayType javaArrayType, JavaTypeAttributes javaTypeAttributes, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return javaTypeResolver.transformArrayType(javaArrayType, javaTypeAttributes, z);
    }

    public final KotlinType transformArrayType(JavaArrayType arrayType, JavaTypeAttributes attr, boolean z) {
        SimpleType simpleTypeFlexibleType;
        Intrinsics.checkNotNullParameter(arrayType, "arrayType");
        Intrinsics.checkNotNullParameter(attr, "attr");
        JavaType componentType = arrayType.getComponentType();
        JavaPrimitiveType javaPrimitiveType = (JavaPrimitiveType) (!(componentType instanceof JavaPrimitiveType) ? null : componentType);
        PrimitiveType type = javaPrimitiveType != null ? javaPrimitiveType.getType() : null;
        if (type != null) {
            SimpleType primitiveArrayKotlinType = this.c.getModule().getBuiltIns().getPrimitiveArrayKotlinType(type);
            Intrinsics.checkNotNullExpressionValue(primitiveArrayKotlinType, "c.module.builtIns.getPri…KotlinType(primitiveType)");
            if (attr.isForAnnotationParameter()) {
                simpleTypeFlexibleType = primitiveArrayKotlinType;
            } else {
                simpleTypeFlexibleType = KotlinTypeFactory.flexibleType(primitiveArrayKotlinType, primitiveArrayKotlinType.makeNullableAsSpecified(true));
            }
            return simpleTypeFlexibleType;
        }
        KotlinType kotlinTypeTransformJavaType = transformJavaType(componentType, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, attr.isForAnnotationParameter(), null, 2, null));
        if (attr.isForAnnotationParameter()) {
            SimpleType arrayType2 = this.c.getModule().getBuiltIns().getArrayType(z ? Variance.OUT_VARIANCE : Variance.INVARIANT, kotlinTypeTransformJavaType);
            Intrinsics.checkNotNullExpressionValue(arrayType2, "c.module.builtIns.getArr…ctionKind, componentType)");
            return arrayType2;
        }
        SimpleType arrayType3 = this.c.getModule().getBuiltIns().getArrayType(Variance.INVARIANT, kotlinTypeTransformJavaType);
        Intrinsics.checkNotNullExpressionValue(arrayType3, "c.module.builtIns.getArr…INVARIANT, componentType)");
        return KotlinTypeFactory.flexibleType(arrayType3, this.c.getModule().getBuiltIns().getArrayType(Variance.OUT_VARIANCE, kotlinTypeTransformJavaType).makeNullableAsSpecified(true));
    }

    private final KotlinType transformJavaClassifierType(final JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes) {
        RawTypeImpl rawTypeImplFlexibleType;
        Function0<SimpleType> function0 = new Function0<SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver.transformJavaClassifierType.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final SimpleType invoke() {
                SimpleType simpleTypeCreateErrorType = ErrorUtils.createErrorType("Unresolved java class " + javaClassifierType.getPresentableText());
                Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorType, "ErrorUtils.createErrorTy…vaType.presentableText}\")");
                return simpleTypeCreateErrorType;
            }
        };
        boolean z = (javaTypeAttributes.isForAnnotationParameter() || javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) ? false : true;
        boolean zIsRaw = javaClassifierType.isRaw();
        if (!zIsRaw && !z) {
            SimpleType simpleTypeComputeSimpleJavaClassifierType = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes, null);
            if (simpleTypeComputeSimpleJavaClassifierType == null) {
                simpleTypeComputeSimpleJavaClassifierType = function0.invoke();
            }
            return simpleTypeComputeSimpleJavaClassifierType;
        }
        SimpleType simpleTypeComputeSimpleJavaClassifierType2 = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes.withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND), null);
        if (simpleTypeComputeSimpleJavaClassifierType2 == null) {
            return function0.invoke();
        }
        SimpleType simpleTypeComputeSimpleJavaClassifierType3 = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes.withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND), simpleTypeComputeSimpleJavaClassifierType2);
        if (simpleTypeComputeSimpleJavaClassifierType3 == null) {
            return function0.invoke();
        }
        if (zIsRaw) {
            rawTypeImplFlexibleType = new RawTypeImpl(simpleTypeComputeSimpleJavaClassifierType2, simpleTypeComputeSimpleJavaClassifierType3);
        } else {
            rawTypeImplFlexibleType = KotlinTypeFactory.flexibleType(simpleTypeComputeSimpleJavaClassifierType2, simpleTypeComputeSimpleJavaClassifierType3);
        }
        return rawTypeImplFlexibleType;
    }

    private final SimpleType computeSimpleJavaClassifierType(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes, SimpleType simpleType) {
        LazyJavaAnnotations lazyJavaAnnotations;
        if (simpleType == null || (lazyJavaAnnotations = simpleType.getAnnotations()) == null) {
            lazyJavaAnnotations = new LazyJavaAnnotations(this.c, javaClassifierType);
        }
        Annotations annotations = lazyJavaAnnotations;
        TypeConstructor typeConstructorComputeTypeConstructor = computeTypeConstructor(javaClassifierType, javaTypeAttributes);
        if (typeConstructorComputeTypeConstructor == null) {
            return null;
        }
        boolean zIsNullable = isNullable(javaTypeAttributes);
        if (Intrinsics.areEqual(simpleType != null ? simpleType.getConstructor() : null, typeConstructorComputeTypeConstructor) && !javaClassifierType.isRaw() && zIsNullable) {
            return simpleType.makeNullableAsSpecified(true);
        }
        return KotlinTypeFactory.simpleType$default(annotations, typeConstructorComputeTypeConstructor, computeArguments(javaClassifierType, javaTypeAttributes, typeConstructorComputeTypeConstructor), zIsNullable, null, 16, null);
    }

    private final TypeConstructor computeTypeConstructor(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes) {
        TypeConstructor typeConstructor;
        JavaClassifier classifier = javaClassifierType.getClassifier();
        if (classifier == null) {
            return createNotFoundClass(javaClassifierType);
        }
        if (classifier instanceof JavaClass) {
            JavaClass javaClass = (JavaClass) classifier;
            FqName fqName = javaClass.getFqName();
            if (fqName == null) {
                throw new AssertionError("Class type should have a FQ name: " + classifier);
            }
            ClassDescriptor classDescriptorMapKotlinClass = mapKotlinClass(javaClassifierType, javaTypeAttributes, fqName);
            if (classDescriptorMapKotlinClass == null) {
                classDescriptorMapKotlinClass = this.c.getComponents().getModuleClassResolver().resolveClass(javaClass);
            }
            return (classDescriptorMapKotlinClass == null || (typeConstructor = classDescriptorMapKotlinClass.getTypeConstructor()) == null) ? createNotFoundClass(javaClassifierType) : typeConstructor;
        }
        if (classifier instanceof JavaTypeParameter) {
            TypeParameterDescriptor typeParameterDescriptorResolveTypeParameter = this.typeParameterResolver.resolveTypeParameter((JavaTypeParameter) classifier);
            if (typeParameterDescriptorResolveTypeParameter != null) {
                return typeParameterDescriptorResolveTypeParameter.getTypeConstructor();
            }
            return null;
        }
        throw new IllegalStateException("Unknown classifier kind: " + classifier);
    }

    private final TypeConstructor createNotFoundClass(JavaClassifierType javaClassifierType) {
        ClassId classId = ClassId.topLevel(new FqName(javaClassifierType.getClassifierQualifiedName()));
        Intrinsics.checkNotNullExpressionValue(classId, "ClassId.topLevel(FqName(…classifierQualifiedName))");
        TypeConstructor typeConstructor = this.c.getComponents().getDeserializedDescriptorResolver().getComponents().getNotFoundClasses().getClass(classId, CollectionsKt.listOf(0)).getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "c.components.deserialize…istOf(0)).typeConstructor");
        return typeConstructor;
    }

    private final ClassDescriptor mapKotlinClass(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes, FqName fqName) {
        if (javaTypeAttributes.isForAnnotationParameter() && Intrinsics.areEqual(fqName, JavaTypeResolverKt.JAVA_LANG_CLASS_FQ_NAME)) {
            return this.c.getComponents().getReflectionTypes().getKClass();
        }
        JavaToKotlinClassMapper javaToKotlinClassMapper = JavaToKotlinClassMapper.INSTANCE;
        ClassDescriptor classDescriptorMapJavaToKotlin$default = JavaToKotlinClassMapper.mapJavaToKotlin$default(javaToKotlinClassMapper, fqName, this.c.getModule().getBuiltIns(), null, 4, null);
        if (classDescriptorMapJavaToKotlin$default != null) {
            return (javaToKotlinClassMapper.isReadOnly(classDescriptorMapJavaToKotlin$default) && (javaTypeAttributes.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND || javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE || argumentsMakeSenseOnlyForMutableContainer(javaClassifierType, classDescriptorMapJavaToKotlin$default))) ? javaToKotlinClassMapper.convertReadOnlyToMutable(classDescriptorMapJavaToKotlin$default) : classDescriptorMapJavaToKotlin$default;
        }
        return null;
    }

    private final boolean argumentsMakeSenseOnlyForMutableContainer(JavaClassifierType javaClassifierType, ClassDescriptor classDescriptor) {
        Variance variance;
        if (!AnonymousClass1.INSTANCE.invoke2((JavaType) CollectionsKt.lastOrNull((List) javaClassifierType.getTypeArguments()))) {
            return false;
        }
        TypeConstructor typeConstructor = JavaToKotlinClassMapper.INSTANCE.convertReadOnlyToMutable(classDescriptor).getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "JavaToKotlinClassMapper.…         .typeConstructor");
        List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "JavaToKotlinClassMapper.…ypeConstructor.parameters");
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) CollectionsKt.lastOrNull((List) parameters);
        if (typeParameterDescriptor == null || (variance = typeParameterDescriptor.getVariance()) == null) {
            return false;
        }
        Intrinsics.checkNotNullExpressionValue(variance, "JavaToKotlinClassMapper.….variance ?: return false");
        return variance != Variance.OUT_VARIANCE;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0028  */
    private final List<TypeProjection> computeArguments(JavaClassifierType javaClassifierType, final JavaTypeAttributes javaTypeAttributes, final TypeConstructor typeConstructor) {
        boolean z;
        final boolean zIsRaw = javaClassifierType.isRaw();
        if (!zIsRaw) {
            if (javaClassifierType.getTypeArguments().isEmpty()) {
                List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters, "constructor.parameters");
                z = !parameters.isEmpty();
            }
        }
        List<TypeParameterDescriptor> parameters2 = typeConstructor.getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters2, "constructor.parameters");
        if (z) {
            List<TypeParameterDescriptor> list = parameters2;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (final TypeParameterDescriptor parameter : list) {
                LazyWrappedType lazyWrappedType = new LazyWrappedType(this.c.getStorageManager(), new Function0<KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver$computeArguments$$inlined$map$lambda$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final KotlinType invoke() {
                        TypeParameterDescriptor parameter2 = parameter;
                        Intrinsics.checkNotNullExpressionValue(parameter2, "parameter");
                        return JavaTypeResolverKt.getErasedUpperBound(parameter2, javaTypeAttributes.getUpperBoundOfTypeParameter(), new Function0<KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver$computeArguments$$inlined$map$lambda$1.1
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final KotlinType invoke() {
                                ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = typeConstructor.mo1268getDeclarationDescriptor();
                                Intrinsics.checkNotNull(classifierDescriptorMo1268getDeclarationDescriptor);
                                Intrinsics.checkNotNullExpressionValue(classifierDescriptorMo1268getDeclarationDescriptor, "constructor.declarationDescriptor!!");
                                SimpleType defaultType = classifierDescriptorMo1268getDeclarationDescriptor.getDefaultType();
                                Intrinsics.checkNotNullExpressionValue(defaultType, "constructor.declarationDescriptor!!.defaultType");
                                return TypeUtilsKt.replaceArgumentsWithStarProjections(defaultType);
                            }
                        });
                    }
                });
                RawSubstitution rawSubstitution = RawSubstitution.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(parameter, "parameter");
                arrayList.add(rawSubstitution.computeProjection(parameter, zIsRaw ? javaTypeAttributes : javaTypeAttributes.withFlexibility(JavaTypeFlexibility.INFLEXIBLE), lazyWrappedType));
            }
            return CollectionsKt.toList(arrayList);
        }
        if (parameters2.size() != javaClassifierType.getTypeArguments().size()) {
            List<TypeParameterDescriptor> list2 = parameters2;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (TypeParameterDescriptor p : list2) {
                Intrinsics.checkNotNullExpressionValue(p, "p");
                arrayList2.add(new TypeProjectionImpl(ErrorUtils.createErrorType(p.getName().asString())));
            }
            return CollectionsKt.toList(arrayList2);
        }
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(javaClassifierType.getTypeArguments());
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterableWithIndex, 10));
        for (IndexedValue indexedValue : iterableWithIndex) {
            int index = indexedValue.getIndex();
            JavaType javaType = (JavaType) indexedValue.component2();
            boolean z2 = index < parameters2.size();
            if (_Assertions.ENABLED && !z2) {
                throw new AssertionError("Argument index should be less then type parameters count, but " + index + " > " + parameters2.size());
            }
            TypeParameterDescriptor parameter2 = parameters2.get(index);
            JavaTypeAttributes attributes$default = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null);
            Intrinsics.checkNotNullExpressionValue(parameter2, "parameter");
            arrayList3.add(transformToTypeProjection(javaType, attributes$default, parameter2));
        }
        return CollectionsKt.toList(arrayList3);
    }

    private final TypeProjection transformToTypeProjection(JavaType javaType, JavaTypeAttributes javaTypeAttributes, TypeParameterDescriptor typeParameterDescriptor) {
        if (javaType instanceof JavaWildcardType) {
            JavaWildcardType javaWildcardType = (JavaWildcardType) javaType;
            JavaType bound = javaWildcardType.getBound();
            Variance variance = javaWildcardType.isExtends() ? Variance.OUT_VARIANCE : Variance.IN_VARIANCE;
            if (bound == null || isConflictingArgumentFor(variance, typeParameterDescriptor)) {
                return JavaTypeResolverKt.makeStarProjection(typeParameterDescriptor, javaTypeAttributes);
            }
            return TypeUtilsKt.createProjection(transformJavaType(bound, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null)), variance, typeParameterDescriptor);
        }
        return new TypeProjectionImpl(Variance.INVARIANT, transformJavaType(javaType, javaTypeAttributes));
    }

    private final boolean isConflictingArgumentFor(Variance variance, TypeParameterDescriptor typeParameterDescriptor) {
        return (typeParameterDescriptor.getVariance() == Variance.INVARIANT || variance == typeParameterDescriptor.getVariance()) ? false : true;
    }

    private final boolean isNullable(JavaTypeAttributes javaTypeAttributes) {
        return (javaTypeAttributes.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND || javaTypeAttributes.isForAnnotationParameter() || javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) ? false : true;
    }
}
