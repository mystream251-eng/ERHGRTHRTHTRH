package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionForAbsentTypeParameter;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: TypeDeserializer.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class TypeDeserializer {
    private final DeserializationContext c;
    private final Function1<Integer, ClassifierDescriptor> classifierDescriptors;
    private final String containerPresentableName;
    private final String debugName;
    private boolean experimentalSuspendFunctionTypeEncountered;
    private final TypeDeserializer parent;
    private final Function1<Integer, ClassifierDescriptor> typeAliasDescriptors;
    private final Map<Integer, TypeParameterDescriptor> typeParameterDescriptors;

    /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeConstructor$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TypeDeserializer.kt */
    static final class C06861 extends Lambda implements Function1<Integer, ClassDescriptor> {
        final /* synthetic */ ProtoBuf.Type $proto;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06861(ProtoBuf.Type type) {
            super(1);
            this.$proto = type;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ ClassDescriptor invoke(Integer num) {
            return invoke(num.intValue());
        }

        public final ClassDescriptor invoke(int i) {
            ClassId classId = NameResolverUtilKt.getClassId(TypeDeserializer.this.c.getNameResolver(), i);
            List<Integer> mutableList = SequencesKt.toMutableList(SequencesKt.map(SequencesKt.generateSequence(this.$proto, new Function1<ProtoBuf.Type, ProtoBuf.Type>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeConstructor$1$typeParametersCount$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final ProtoBuf.Type invoke(ProtoBuf.Type it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ProtoTypeTableUtilKt.outerType(it, TypeDeserializer.this.c.getTypeTable());
                }
            }), new Function1<ProtoBuf.Type, Integer>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeConstructor$1$typeParametersCount$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(ProtoBuf.Type type) {
                    return Integer.valueOf(invoke2(type));
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final int invoke2(ProtoBuf.Type it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.getArgumentCount();
                }
            }));
            int iCount = SequencesKt.count(SequencesKt.generateSequence(classId, TypeDeserializer$typeConstructor$1$classNestingLevel$1.INSTANCE));
            while (mutableList.size() < iCount) {
                mutableList.add(0);
            }
            return TypeDeserializer.this.c.getComponents().getNotFoundClasses().getClass(classId, mutableList);
        }
    }

    public TypeDeserializer(DeserializationContext c, TypeDeserializer typeDeserializer, List<ProtoBuf.TypeParameter> typeParameterProtos, String debugName, String containerPresentableName, boolean z) {
        LinkedHashMap linkedHashMapEmptyMap;
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeParameterProtos, "typeParameterProtos");
        Intrinsics.checkNotNullParameter(debugName, "debugName");
        Intrinsics.checkNotNullParameter(containerPresentableName, "containerPresentableName");
        this.c = c;
        this.parent = typeDeserializer;
        this.debugName = debugName;
        this.containerPresentableName = containerPresentableName;
        this.experimentalSuspendFunctionTypeEncountered = z;
        this.classifierDescriptors = c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Integer, ClassifierDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$classifierDescriptors$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ ClassifierDescriptor invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final ClassifierDescriptor invoke(int i) {
                return this.this$0.computeClassifierDescriptor(i);
            }
        });
        this.typeAliasDescriptors = this.c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Integer, ClassifierDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeAliasDescriptors$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ ClassifierDescriptor invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final ClassifierDescriptor invoke(int i) {
                return this.this$0.computeTypeAliasDescriptor(i);
            }
        });
        if (typeParameterProtos.isEmpty()) {
            linkedHashMapEmptyMap = MapsKt.emptyMap();
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int i = 0;
            for (ProtoBuf.TypeParameter typeParameter : typeParameterProtos) {
                linkedHashMap.put(Integer.valueOf(typeParameter.getId()), new DeserializedTypeParameterDescriptor(this.c, typeParameter, i));
                i++;
            }
            linkedHashMapEmptyMap = linkedHashMap;
        }
        this.typeParameterDescriptors = linkedHashMapEmptyMap;
    }

    public /* synthetic */ TypeDeserializer(DeserializationContext deserializationContext, TypeDeserializer typeDeserializer, List list, String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(deserializationContext, typeDeserializer, list, str, str2, (i & 32) != 0 ? false : z);
    }

    public final boolean getExperimentalSuspendFunctionTypeEncountered() {
        return this.experimentalSuspendFunctionTypeEncountered;
    }

    public final List<TypeParameterDescriptor> getOwnTypeParameters() {
        return CollectionsKt.toList(this.typeParameterDescriptors.values());
    }

    public final KotlinType type(ProtoBuf.Type proto) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        if (proto.hasFlexibleTypeCapabilitiesId()) {
            String string = this.c.getNameResolver().getString(proto.getFlexibleTypeCapabilitiesId());
            SimpleType simpleTypeSimpleType$default = simpleType$default(this, proto, false, 2, null);
            ProtoBuf.Type typeFlexibleUpperBound = ProtoTypeTableUtilKt.flexibleUpperBound(proto, this.c.getTypeTable());
            Intrinsics.checkNotNull(typeFlexibleUpperBound);
            return this.c.getComponents().getFlexibleTypeDeserializer().create(proto, string, simpleTypeSimpleType$default, simpleType$default(this, typeFlexibleUpperBound, false, 2, null));
        }
        return simpleType(proto, true);
    }

    public static /* synthetic */ SimpleType simpleType$default(TypeDeserializer typeDeserializer, ProtoBuf.Type type, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return typeDeserializer.simpleType(type, z);
    }

    public final SimpleType simpleType(final ProtoBuf.Type proto, boolean z) {
        SimpleType simpleTypeComputeLocalClassifierReplacementType;
        SimpleType simpleTypeSimpleType$default;
        SimpleType simpleTypeWithAbbreviation;
        Intrinsics.checkNotNullParameter(proto, "proto");
        if (proto.hasClassName()) {
            simpleTypeComputeLocalClassifierReplacementType = computeLocalClassifierReplacementType(proto.getClassName());
        } else {
            simpleTypeComputeLocalClassifierReplacementType = proto.hasTypeAliasName() ? computeLocalClassifierReplacementType(proto.getTypeAliasName()) : null;
        }
        if (simpleTypeComputeLocalClassifierReplacementType != null) {
            return simpleTypeComputeLocalClassifierReplacementType;
        }
        TypeConstructor typeConstructor = typeConstructor(proto);
        if (ErrorUtils.isError(typeConstructor.mo1268getDeclarationDescriptor())) {
            SimpleType simpleTypeCreateErrorTypeWithCustomConstructor = ErrorUtils.createErrorTypeWithCustomConstructor(typeConstructor.toString(), typeConstructor);
            Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorTypeWithCustomConstructor, "ErrorUtils.createErrorTy….toString(), constructor)");
            return simpleTypeCreateErrorTypeWithCustomConstructor;
        }
        DeserializedAnnotations deserializedAnnotations = new DeserializedAnnotations(this.c.getStorageManager(), new Function0<List<? extends AnnotationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$simpleType$annotations$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends AnnotationDescriptor> invoke() {
                return this.this$0.c.getComponents().getAnnotationAndConstantLoader().loadTypeAnnotations(proto, this.this$0.c.getNameResolver());
            }
        });
        List<ProtoBuf.Type.Argument> listInvoke = new Function1<ProtoBuf.Type, List<? extends ProtoBuf.Type.Argument>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.simpleType.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<ProtoBuf.Type.Argument> invoke(ProtoBuf.Type collectAllArguments) {
                Intrinsics.checkNotNullParameter(collectAllArguments, "$this$collectAllArguments");
                List<ProtoBuf.Type.Argument> argumentList = collectAllArguments.getArgumentList();
                Intrinsics.checkNotNullExpressionValue(argumentList, "argumentList");
                List<ProtoBuf.Type.Argument> list = argumentList;
                ProtoBuf.Type typeOuterType = ProtoTypeTableUtilKt.outerType(collectAllArguments, TypeDeserializer.this.c.getTypeTable());
                List<ProtoBuf.Type.Argument> listInvoke2 = typeOuterType != null ? invoke(typeOuterType) : null;
                if (listInvoke2 == null) {
                    listInvoke2 = CollectionsKt.emptyList();
                }
                return CollectionsKt.plus((Collection) list, (Iterable) listInvoke2);
            }
        }.invoke(proto);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listInvoke, 10));
        int i = 0;
        for (Object obj : listInvoke) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "constructor.parameters");
            arrayList.add(typeArgument((TypeParameterDescriptor) CollectionsKt.getOrNull(parameters, i), (ProtoBuf.Type.Argument) obj));
            i = i2;
        }
        List<? extends TypeProjection> list = CollectionsKt.toList(arrayList);
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = typeConstructor.mo1268getDeclarationDescriptor();
        if (z && (classifierDescriptorMo1268getDeclarationDescriptor instanceof TypeAliasDescriptor)) {
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            SimpleType simpleTypeComputeExpandedType = KotlinTypeFactory.computeExpandedType((TypeAliasDescriptor) classifierDescriptorMo1268getDeclarationDescriptor, list);
            simpleTypeSimpleType$default = simpleTypeComputeExpandedType.makeNullableAsSpecified(KotlinTypeKt.isNullable(simpleTypeComputeExpandedType) || proto.getNullable()).replaceAnnotations(Annotations.Companion.create(CollectionsKt.plus((Iterable) deserializedAnnotations, (Iterable) simpleTypeComputeExpandedType.getAnnotations())));
        } else {
            Boolean bool = Flags.SUSPEND_TYPE.get(proto.getFlags());
            Intrinsics.checkNotNullExpressionValue(bool, "Flags.SUSPEND_TYPE.get(proto.flags)");
            if (bool.booleanValue()) {
                simpleTypeSimpleType$default = createSuspendFunctionType(deserializedAnnotations, typeConstructor, list, proto.getNullable());
            } else {
                simpleTypeSimpleType$default = KotlinTypeFactory.simpleType$default(deserializedAnnotations, typeConstructor, list, proto.getNullable(), null, 16, null);
            }
        }
        ProtoBuf.Type typeAbbreviatedType = ProtoTypeTableUtilKt.abbreviatedType(proto, this.c.getTypeTable());
        if (typeAbbreviatedType != null && (simpleTypeWithAbbreviation = SpecialTypesKt.withAbbreviation(simpleTypeSimpleType$default, simpleType(typeAbbreviatedType, false))) != null) {
            simpleTypeSimpleType$default = simpleTypeWithAbbreviation;
        }
        return proto.hasClassName() ? this.c.getComponents().getPlatformDependentTypeTransformer().transformPlatformType(NameResolverUtilKt.getClassId(this.c.getNameResolver(), proto.getClassName()), simpleTypeSimpleType$default) : simpleTypeSimpleType$default;
    }

    private final TypeConstructor typeConstructor(ProtoBuf.Type type) {
        Object next;
        TypeConstructor typeConstructorCreateErrorTypeConstructor;
        TypeConstructor typeConstructor;
        C06861 c06861 = new C06861(type);
        if (type.hasClassName()) {
            ClassDescriptor classDescriptorInvoke = this.classifierDescriptors.invoke(Integer.valueOf(type.getClassName()));
            if (classDescriptorInvoke == null) {
                classDescriptorInvoke = c06861.invoke(type.getClassName());
            }
            TypeConstructor typeConstructor2 = classDescriptorInvoke.getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor2, "(classifierDescriptors(p…assName)).typeConstructor");
            return typeConstructor2;
        }
        if (type.hasTypeParameter()) {
            TypeConstructor typeConstructorTypeParameterTypeConstructor = typeParameterTypeConstructor(type.getTypeParameter());
            if (typeConstructorTypeParameterTypeConstructor != null) {
                return typeConstructorTypeParameterTypeConstructor;
            }
            TypeConstructor typeConstructorCreateErrorTypeConstructor2 = ErrorUtils.createErrorTypeConstructor("Unknown type parameter " + type.getTypeParameter() + ". Please try recompiling module containing \"" + this.containerPresentableName + Typography.quote);
            Intrinsics.checkNotNullExpressionValue(typeConstructorCreateErrorTypeConstructor2, "ErrorUtils.createErrorTy…\\\"\"\n                    )");
            return typeConstructorCreateErrorTypeConstructor2;
        }
        if (type.hasTypeParameterName()) {
            DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
            String string = this.c.getNameResolver().getString(type.getTypeParameterName());
            Iterator<T> it = getOwnTypeParameters().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((TypeParameterDescriptor) next).getName().asString(), string));
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) next;
            if (typeParameterDescriptor == null || (typeConstructor = typeParameterDescriptor.getTypeConstructor()) == null) {
                typeConstructorCreateErrorTypeConstructor = ErrorUtils.createErrorTypeConstructor("Deserialized type parameter " + string + " in " + containingDeclaration);
            } else {
                typeConstructorCreateErrorTypeConstructor = typeConstructor;
            }
            Intrinsics.checkNotNullExpressionValue(typeConstructorCreateErrorTypeConstructor, "parameter?.typeConstruct…ter $name in $container\")");
            return typeConstructorCreateErrorTypeConstructor;
        }
        if (!type.hasTypeAliasName()) {
            TypeConstructor typeConstructorCreateErrorTypeConstructor3 = ErrorUtils.createErrorTypeConstructor("Unknown type");
            Intrinsics.checkNotNullExpressionValue(typeConstructorCreateErrorTypeConstructor3, "ErrorUtils.createErrorTy…nstructor(\"Unknown type\")");
            return typeConstructorCreateErrorTypeConstructor3;
        }
        ClassDescriptor classDescriptorInvoke2 = this.typeAliasDescriptors.invoke(Integer.valueOf(type.getTypeAliasName()));
        if (classDescriptorInvoke2 == null) {
            classDescriptorInvoke2 = c06861.invoke(type.getTypeAliasName());
        }
        TypeConstructor typeConstructor3 = classDescriptorInvoke2.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor3, "(typeAliasDescriptors(pr…iasName)).typeConstructor");
        return typeConstructor3;
    }

    private final SimpleType createSuspendFunctionType(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        int size;
        int size2 = typeConstructor.getParameters().size() - list.size();
        SimpleType simpleTypeCreateSuspendFunctionTypeForBasicCase = null;
        if (size2 == 0) {
            simpleTypeCreateSuspendFunctionTypeForBasicCase = createSuspendFunctionTypeForBasicCase(annotations, typeConstructor, list, z);
        } else if (size2 == 1 && (size = list.size() - 1) >= 0) {
            ClassDescriptor suspendFunction = typeConstructor.getBuiltIns().getSuspendFunction(size);
            Intrinsics.checkNotNullExpressionValue(suspendFunction, "functionTypeConstructor.…getSuspendFunction(arity)");
            TypeConstructor typeConstructor2 = suspendFunction.getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor2, "functionTypeConstructor.…on(arity).typeConstructor");
            simpleTypeCreateSuspendFunctionTypeForBasicCase = KotlinTypeFactory.simpleType$default(annotations, typeConstructor2, list, z, null, 16, null);
        }
        if (simpleTypeCreateSuspendFunctionTypeForBasicCase != null) {
            return simpleTypeCreateSuspendFunctionTypeForBasicCase;
        }
        SimpleType simpleTypeCreateErrorTypeWithArguments = ErrorUtils.createErrorTypeWithArguments("Bad suspend function in metadata with constructor: " + typeConstructor, list);
        Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorTypeWithArguments, "ErrorUtils.createErrorTy…      arguments\n        )");
        return simpleTypeCreateErrorTypeWithArguments;
    }

    private final SimpleType createSuspendFunctionTypeForBasicCase(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        SimpleType simpleTypeSimpleType$default = KotlinTypeFactory.simpleType$default(annotations, typeConstructor, list, z, null, 16, null);
        if (FunctionTypesKt.isFunctionType(simpleTypeSimpleType$default)) {
            return transformRuntimeFunctionTypeToSuspendFunction(simpleTypeSimpleType$default);
        }
        return null;
    }

    private final SimpleType transformRuntimeFunctionTypeToSuspendFunction(KotlinType kotlinType) {
        KotlinType type;
        boolean releaseCoroutines = this.c.getComponents().getConfiguration().getReleaseCoroutines();
        TypeProjection typeProjection = (TypeProjection) CollectionsKt.lastOrNull((List) FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType));
        if (typeProjection == null || (type = typeProjection.getType()) == null) {
            return null;
        }
        Intrinsics.checkNotNullExpressionValue(type, "funType.getValueParamete…ll()?.type ?: return null");
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = type.getConstructor().mo1268getDeclarationDescriptor();
        FqName fqNameSafe = classifierDescriptorMo1268getDeclarationDescriptor != null ? DescriptorUtilsKt.getFqNameSafe(classifierDescriptorMo1268getDeclarationDescriptor) : null;
        boolean z = true;
        if (type.getArguments().size() != 1 || (!SuspendFunctionTypesKt.isContinuation(fqNameSafe, true) && !SuspendFunctionTypesKt.isContinuation(fqNameSafe, false))) {
            return (SimpleType) kotlinType;
        }
        KotlinType type2 = ((TypeProjection) CollectionsKt.single((List) type.getArguments())).getType();
        Intrinsics.checkNotNullExpressionValue(type2, "continuationArgumentType.arguments.single().type");
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        if (!(containingDeclaration instanceof CallableDescriptor)) {
            containingDeclaration = null;
        }
        CallableDescriptor callableDescriptor = (CallableDescriptor) containingDeclaration;
        if (Intrinsics.areEqual(callableDescriptor != null ? DescriptorUtilsKt.fqNameOrNull(callableDescriptor) : null, SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
            return createSimpleSuspendFunctionType(kotlinType, type2);
        }
        if (!this.experimentalSuspendFunctionTypeEncountered && (!releaseCoroutines || !SuspendFunctionTypesKt.isContinuation(fqNameSafe, !releaseCoroutines))) {
            z = false;
        }
        this.experimentalSuspendFunctionTypeEncountered = z;
        return createSimpleSuspendFunctionType(kotlinType, type2);
    }

    private final SimpleType createSimpleSuspendFunctionType(KotlinType kotlinType, KotlinType kotlinType2) {
        KotlinBuiltIns builtIns = TypeUtilsKt.getBuiltIns(kotlinType);
        Annotations annotations = kotlinType.getAnnotations();
        KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(kotlinType);
        List listDropLast = CollectionsKt.dropLast(FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType), 1);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDropLast, 10));
        Iterator it = listDropLast.iterator();
        while (it.hasNext()) {
            arrayList.add(((TypeProjection) it.next()).getType());
        }
        return FunctionTypesKt.createFunctionType(builtIns, annotations, receiverTypeFromFunctionType, arrayList, null, kotlinType2, true).makeNullableAsSpecified(kotlinType.isMarkedNullable());
    }

    private final TypeConstructor typeParameterTypeConstructor(int i) {
        TypeConstructor typeConstructor;
        TypeParameterDescriptor typeParameterDescriptor = this.typeParameterDescriptors.get(Integer.valueOf(i));
        if (typeParameterDescriptor != null && (typeConstructor = typeParameterDescriptor.getTypeConstructor()) != null) {
            return typeConstructor;
        }
        TypeDeserializer typeDeserializer = this.parent;
        if (typeDeserializer != null) {
            return typeDeserializer.typeParameterTypeConstructor(i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassifierDescriptor computeClassifierDescriptor(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.c.getNameResolver(), i);
        if (classId.isLocal()) {
            return this.c.getComponents().deserializeClass(classId);
        }
        return FindClassInModuleKt.findClassifierAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), classId);
    }

    private final SimpleType computeLocalClassifierReplacementType(int i) {
        if (NameResolverUtilKt.getClassId(this.c.getNameResolver(), i).isLocal()) {
            return this.c.getComponents().getLocalClassifierTypeSettings().getReplacementTypeForLocalClassifiers();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassifierDescriptor computeTypeAliasDescriptor(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.c.getNameResolver(), i);
        if (classId.isLocal()) {
            return null;
        }
        return FindClassInModuleKt.findTypeAliasAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), classId);
    }

    private final TypeProjection typeArgument(TypeParameterDescriptor typeParameterDescriptor, ProtoBuf.Type.Argument argument) {
        StarProjectionImpl starProjectionImpl;
        if (argument.getProjection() == ProtoBuf.Type.Argument.Projection.STAR) {
            if (typeParameterDescriptor == null) {
                starProjectionImpl = new StarProjectionForAbsentTypeParameter(this.c.getComponents().getModuleDescriptor().getBuiltIns());
            } else {
                starProjectionImpl = new StarProjectionImpl(typeParameterDescriptor);
            }
            return starProjectionImpl;
        }
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        ProtoBuf.Type.Argument.Projection projection = argument.getProjection();
        Intrinsics.checkNotNullExpressionValue(projection, "typeArgumentProto.projection");
        Variance variance = protoEnumFlags.variance(projection);
        ProtoBuf.Type type = ProtoTypeTableUtilKt.type(argument, this.c.getTypeTable());
        if (type == null) {
            return new TypeProjectionImpl(ErrorUtils.createErrorType("No type recorded"));
        }
        return new TypeProjectionImpl(variance, type(type));
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.debugName);
        if (this.parent == null) {
            str = "";
        } else {
            str = ". Child of " + this.parent.debugName;
        }
        sb.append(str);
        return sb.toString();
    }
}
