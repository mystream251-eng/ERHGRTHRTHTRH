package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModalityUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilterKt;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureBuildingUtilsKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.CacheWithNotNullValues;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

/* JADX INFO: compiled from: JvmBuiltInsSettings.kt */
/* JADX INFO: loaded from: classes2.dex */
public class JvmBuiltInsSettings implements AdditionalClassPartsProvider, PlatformDependentDeclarationFilter {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsSettings.class), "cloneableType", "getCloneableType()Lorg/jetbrains/kotlin/types/SimpleType;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsSettings.class), "notConsideredDeprecation", "getNotConsideredDeprecation()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;"))};
    private final NotNullLazyValue cloneableType$delegate;
    private final Lazy isAdditionalBuiltInsFeatureSupported$delegate;
    private final JavaToKotlinClassMapper j2kClassMapper;
    private final CacheWithNotNullValues<FqName, ClassDescriptor> javaAnalogueClassesWithCustomSupertypeCache;
    private final KotlinType mockSerializableType;
    private final ModuleDescriptor moduleDescriptor;
    private final NotNullLazyValue notConsideredDeprecation$delegate;
    private final Lazy ownerModuleDescriptor$delegate;

    /* JADX INFO: compiled from: JvmBuiltInsSettings.kt */
    private enum JDKMemberStatus {
        HIDDEN,
        VISIBLE,
        NOT_CONSIDERED,
        DROP
    }

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JDKMemberStatus.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[JDKMemberStatus.HIDDEN.ordinal()] = 1;
            $EnumSwitchMapping$0[JDKMemberStatus.NOT_CONSIDERED.ordinal()] = 2;
            $EnumSwitchMapping$0[JDKMemberStatus.DROP.ordinal()] = 3;
            $EnumSwitchMapping$0[JDKMemberStatus.VISIBLE.ordinal()] = 4;
        }
    }

    private final SimpleType getCloneableType() {
        return (SimpleType) StorageKt.getValue(this.cloneableType$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }

    private final Annotations getNotConsideredDeprecation() {
        return (Annotations) StorageKt.getValue(this.notConsideredDeprecation$delegate, this, (KProperty<?>) $$delegatedProperties[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ModuleDescriptor getOwnerModuleDescriptor() {
        return (ModuleDescriptor) this.ownerModuleDescriptor$delegate.getValue();
    }

    private final boolean isAdditionalBuiltInsFeatureSupported() {
        return ((Boolean) this.isAdditionalBuiltInsFeatureSupported$delegate.getValue()).booleanValue();
    }

    public JvmBuiltInsSettings(ModuleDescriptor moduleDescriptor, final StorageManager storageManager, Function0<? extends ModuleDescriptor> deferredOwnerModuleDescriptor, Function0<Boolean> isAdditionalBuiltInsFeatureSupported) {
        Intrinsics.checkNotNullParameter(moduleDescriptor, "moduleDescriptor");
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(deferredOwnerModuleDescriptor, "deferredOwnerModuleDescriptor");
        Intrinsics.checkNotNullParameter(isAdditionalBuiltInsFeatureSupported, "isAdditionalBuiltInsFeatureSupported");
        this.moduleDescriptor = moduleDescriptor;
        this.j2kClassMapper = JavaToKotlinClassMapper.INSTANCE;
        this.ownerModuleDescriptor$delegate = LazyKt.lazy(deferredOwnerModuleDescriptor);
        this.isAdditionalBuiltInsFeatureSupported$delegate = LazyKt.lazy(isAdditionalBuiltInsFeatureSupported);
        this.mockSerializableType = createMockJavaIoSerializableType(storageManager);
        this.cloneableType$delegate = storageManager.createLazyValue(new Function0<SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$cloneableType$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final SimpleType invoke() {
                return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.this$0.getOwnerModuleDescriptor(), JvmBuiltInClassDescriptorFactory.Companion.getCLONEABLE_CLASS_ID(), new NotFoundClasses(storageManager, this.this$0.getOwnerModuleDescriptor())).getDefaultType();
            }
        });
        this.javaAnalogueClassesWithCustomSupertypeCache = storageManager.createCacheWithNotNullValues();
        this.notConsideredDeprecation$delegate = storageManager.createLazyValue(new Function0<Annotations>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$notConsideredDeprecation$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Annotations invoke() {
                return Annotations.Companion.create(CollectionsKt.listOf(AnnotationUtilKt.createDeprecatedAnnotation$default(this.this$0.moduleDescriptor.getBuiltIns(), "This member is not fully supported by Kotlin compiler, so it may be absent or have different signature in next major version", null, null, 6, null)));
            }
        });
    }

    private final KotlinType createMockJavaIoSerializableType(StorageManager storageManager) {
        final ModuleDescriptor moduleDescriptor = this.moduleDescriptor;
        final FqName fqName = new FqName("java.io");
        ClassDescriptorImpl classDescriptorImpl = new ClassDescriptorImpl(new PackageFragmentDescriptorImpl(moduleDescriptor, fqName) { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$createMockJavaIoSerializableType$mockJavaIoPackageFragment$1
            @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
            public MemberScope.Empty getMemberScope() {
                return MemberScope.Empty.INSTANCE;
            }
        }, Name.identifier("Serializable"), Modality.ABSTRACT, ClassKind.INTERFACE, CollectionsKt.listOf(new LazyWrappedType(storageManager, new Function0<KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$createMockJavaIoSerializableType$superTypes$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final KotlinType invoke() {
                SimpleType anyType = this.this$0.moduleDescriptor.getBuiltIns().getAnyType();
                Intrinsics.checkNotNullExpressionValue(anyType, "moduleDescriptor.builtIns.anyType");
                return anyType;
            }
        })), SourceElement.NO_SOURCE, false, storageManager);
        classDescriptorImpl.initialize(MemberScope.Empty.INSTANCE, SetsKt.emptySet(), null);
        SimpleType defaultType = classDescriptorImpl.getDefaultType();
        Intrinsics.checkNotNullExpressionValue(defaultType, "mockSerializableClass.defaultType");
        return defaultType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    public Collection<KotlinType> getSupertypes(ClassDescriptor classDescriptor) {
        List listListOf;
        Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        FqNameUnsafe fqNameUnsafe = DescriptorUtilsKt.getFqNameUnsafe(classDescriptor);
        if (JvmBuiltInsSignatures.INSTANCE.isArrayOrPrimitiveArray(fqNameUnsafe)) {
            SimpleType cloneableType = getCloneableType();
            Intrinsics.checkNotNullExpressionValue(cloneableType, "cloneableType");
            listListOf = CollectionsKt.listOf((Object[]) new KotlinType[]{cloneableType, this.mockSerializableType});
        } else {
            listListOf = JvmBuiltInsSignatures.INSTANCE.isSerializableInJava(fqNameUnsafe) ? CollectionsKt.listOf(this.mockSerializableType) : CollectionsKt.emptyList();
        }
        return listListOf;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    public Collection<SimpleFunctionDescriptor> getFunctions(final Name name, ClassDescriptor classDescriptor) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        boolean z = true;
        if (Intrinsics.areEqual(name, CloneableClassScope.Companion.getCLONE_NAME()) && (classDescriptor instanceof DeserializedClassDescriptor) && KotlinBuiltIns.isArrayOrPrimitiveArray(classDescriptor)) {
            DeserializedClassDescriptor deserializedClassDescriptor = (DeserializedClassDescriptor) classDescriptor;
            List<ProtoBuf.Function> functionList = deserializedClassDescriptor.getClassProto().getFunctionList();
            Intrinsics.checkNotNullExpressionValue(functionList, "classDescriptor.classProto.functionList");
            List<ProtoBuf.Function> list = functionList;
            if ((list instanceof Collection) && list.isEmpty()) {
                z = false;
            } else {
                for (ProtoBuf.Function functionProto : list) {
                    NameResolver nameResolver = deserializedClassDescriptor.getC().getNameResolver();
                    Intrinsics.checkNotNullExpressionValue(functionProto, "functionProto");
                    if (Intrinsics.areEqual(NameResolverUtilKt.getName(nameResolver, functionProto.getName()), CloneableClassScope.Companion.getCLONE_NAME())) {
                    }
                }
                z = false;
            }
            if (z) {
                return CollectionsKt.emptyList();
            }
            return CollectionsKt.listOf(createCloneForArray(deserializedClassDescriptor, (SimpleFunctionDescriptor) CollectionsKt.single(getCloneableType().getMemberScope().getContributedFunctions(name, NoLookupLocation.FROM_BUILTINS))));
        }
        if (!isAdditionalBuiltInsFeatureSupported()) {
            return CollectionsKt.emptyList();
        }
        Collection<SimpleFunctionDescriptor> additionalFunctions = getAdditionalFunctions(classDescriptor, new Function1<MemberScope, Collection<? extends SimpleFunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings.getFunctions.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Collection<SimpleFunctionDescriptor> invoke(MemberScope it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getContributedFunctions(name, NoLookupLocation.FROM_BUILTINS);
            }
        });
        ArrayList arrayList = new ArrayList();
        for (SimpleFunctionDescriptor simpleFunctionDescriptor : additionalFunctions) {
            DeclarationDescriptor containingDeclaration = simpleFunctionDescriptor.getContainingDeclaration();
            if (containingDeclaration != null) {
                FunctionDescriptor functionDescriptorSubstitute = simpleFunctionDescriptor.substitute(MappingUtilKt.createMappedTypeParametersSubstitution((ClassDescriptor) containingDeclaration, classDescriptor).buildSubstitutor());
                if (functionDescriptorSubstitute == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor");
                }
                FunctionDescriptor.CopyBuilder<? extends SimpleFunctionDescriptor> copyBuilderNewCopyBuilder = ((SimpleFunctionDescriptor) functionDescriptorSubstitute).newCopyBuilder();
                copyBuilderNewCopyBuilder.setOwner(classDescriptor);
                copyBuilderNewCopyBuilder.setDispatchReceiverParameter(classDescriptor.getThisAsReceiverParameter());
                copyBuilderNewCopyBuilder.setPreserveSourceElement();
                int i = WhenMappings.$EnumSwitchMapping$0[getJdkMethodStatus(simpleFunctionDescriptor).ordinal()];
                SimpleFunctionDescriptor simpleFunctionDescriptor2 = null;
                if (i == 1) {
                    if (!ModalityUtilsKt.isFinalClass(classDescriptor)) {
                        Intrinsics.checkNotNullExpressionValue(copyBuilderNewCopyBuilder.setHiddenForResolutionEverywhereBesideSupercalls(), "setHiddenForResolutionEverywhereBesideSupercalls()");
                        FunctionDescriptor functionDescriptorBuild = copyBuilderNewCopyBuilder.build();
                        Intrinsics.checkNotNull(functionDescriptorBuild);
                        simpleFunctionDescriptor2 = (SimpleFunctionDescriptor) functionDescriptorBuild;
                    }
                } else {
                    if (i == 2) {
                        Intrinsics.checkNotNullExpressionValue(copyBuilderNewCopyBuilder.setAdditionalAnnotations(getNotConsideredDeprecation()), "setAdditionalAnnotations(notConsideredDeprecation)");
                    } else if (i != 3) {
                    }
                    FunctionDescriptor functionDescriptorBuild2 = copyBuilderNewCopyBuilder.build();
                    Intrinsics.checkNotNull(functionDescriptorBuild2);
                    simpleFunctionDescriptor2 = (SimpleFunctionDescriptor) functionDescriptorBuild2;
                }
                if (simpleFunctionDescriptor2 != null) {
                    arrayList.add(simpleFunctionDescriptor2);
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    public Set<Name> getFunctionsNames(ClassDescriptor classDescriptor) {
        LazyJavaClassMemberScope unsubstitutedMemberScope;
        Set<Name> functionNames;
        Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        if (!isAdditionalBuiltInsFeatureSupported()) {
            return SetsKt.emptySet();
        }
        LazyJavaClassDescriptor javaAnalogue = getJavaAnalogue(classDescriptor);
        return (javaAnalogue == null || (unsubstitutedMemberScope = javaAnalogue.getUnsubstitutedMemberScope()) == null || (functionNames = unsubstitutedMemberScope.getFunctionNames()) == null) ? SetsKt.emptySet() : functionNames;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x009e  */
    private final Collection<SimpleFunctionDescriptor> getAdditionalFunctions(ClassDescriptor classDescriptor, Function1<? super MemberScope, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        boolean z;
        boolean z2;
        final LazyJavaClassDescriptor javaAnalogue = getJavaAnalogue(classDescriptor);
        if (javaAnalogue == null) {
            return CollectionsKt.emptyList();
        }
        LazyJavaClassDescriptor lazyJavaClassDescriptor = javaAnalogue;
        Collection<ClassDescriptor> collectionMapPlatformClass = this.j2kClassMapper.mapPlatformClass(DescriptorUtilsKt.getFqNameSafe(lazyJavaClassDescriptor), FallbackBuiltIns.Companion.getInstance());
        final ClassDescriptor classDescriptor2 = (ClassDescriptor) CollectionsKt.lastOrNull(collectionMapPlatformClass);
        if (classDescriptor2 == null) {
            return CollectionsKt.emptyList();
        }
        SmartSet.Companion companion = SmartSet.Companion;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionMapPlatformClass, 10));
        Iterator<T> it = collectionMapPlatformClass.iterator();
        while (it.hasNext()) {
            arrayList.add(DescriptorUtilsKt.getFqNameSafe((ClassDescriptor) it.next()));
        }
        SmartSet smartSetCreate = companion.create(arrayList);
        boolean zIsMutable = this.j2kClassMapper.isMutable(classDescriptor);
        MemberScope unsubstitutedMemberScope = this.javaAnalogueClassesWithCustomSupertypeCache.computeIfAbsent(DescriptorUtilsKt.getFqNameSafe(lazyJavaClassDescriptor), new Function0<ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$getAdditionalFunctions$fakeJavaClassDescriptor$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ClassDescriptor invoke() {
                LazyJavaClassDescriptor lazyJavaClassDescriptor2 = javaAnalogue;
                JavaResolverCache javaResolverCache = JavaResolverCache.EMPTY;
                Intrinsics.checkNotNullExpressionValue(javaResolverCache, "JavaResolverCache.EMPTY");
                return lazyJavaClassDescriptor2.copy$descriptors_jvm(javaResolverCache, classDescriptor2);
            }
        }).getUnsubstitutedMemberScope();
        Intrinsics.checkNotNullExpressionValue(unsubstitutedMemberScope, "fakeJavaClassDescriptor.unsubstitutedMemberScope");
        Collection<? extends SimpleFunctionDescriptor> collectionInvoke = function1.invoke(unsubstitutedMemberScope);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : collectionInvoke) {
            SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) obj;
            if (simpleFunctionDescriptor.getKind() == CallableMemberDescriptor.Kind.DECLARATION && simpleFunctionDescriptor.getVisibility().isPublicAPI() && !KotlinBuiltIns.isDeprecated(simpleFunctionDescriptor)) {
                Collection<? extends FunctionDescriptor> overriddenDescriptors = simpleFunctionDescriptor.getOverriddenDescriptors();
                Intrinsics.checkNotNullExpressionValue(overriddenDescriptors, "analogueMember.overriddenDescriptors");
                Collection<? extends FunctionDescriptor> collection = overriddenDescriptors;
                if (!(collection instanceof Collection) || !collection.isEmpty()) {
                    Iterator<T> it2 = collection.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            z = false;
                            break;
                        }
                        FunctionDescriptor it3 = (FunctionDescriptor) it2.next();
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        DeclarationDescriptor containingDeclaration = it3.getContainingDeclaration();
                        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "it.containingDeclaration");
                        if (smartSetCreate.contains(DescriptorUtilsKt.getFqNameSafe(containingDeclaration))) {
                            z = true;
                            break;
                        }
                    }
                } else {
                    z = false;
                    break;
                }
                z2 = (z || isMutabilityViolation(simpleFunctionDescriptor, zIsMutable)) ? false : true;
            }
            if (z2) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    private final SimpleFunctionDescriptor createCloneForArray(DeserializedClassDescriptor deserializedClassDescriptor, SimpleFunctionDescriptor simpleFunctionDescriptor) {
        FunctionDescriptor.CopyBuilder<? extends SimpleFunctionDescriptor> copyBuilderNewCopyBuilder = simpleFunctionDescriptor.newCopyBuilder();
        copyBuilderNewCopyBuilder.setOwner(deserializedClassDescriptor);
        copyBuilderNewCopyBuilder.setVisibility(DescriptorVisibilities.PUBLIC);
        copyBuilderNewCopyBuilder.setReturnType(deserializedClassDescriptor.getDefaultType());
        copyBuilderNewCopyBuilder.setDispatchReceiverParameter(deserializedClassDescriptor.getThisAsReceiverParameter());
        FunctionDescriptor functionDescriptorBuild = copyBuilderNewCopyBuilder.build();
        Intrinsics.checkNotNull(functionDescriptorBuild);
        return (SimpleFunctionDescriptor) functionDescriptorBuild;
    }

    private final boolean isMutabilityViolation(SimpleFunctionDescriptor simpleFunctionDescriptor, boolean z) {
        DeclarationDescriptor containingDeclaration = simpleFunctionDescriptor.getContainingDeclaration();
        if (containingDeclaration == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        }
        String strComputeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(simpleFunctionDescriptor, false, false, 3, null);
        if (z ^ JvmBuiltInsSignatures.INSTANCE.getMUTABLE_METHOD_SIGNATURES().contains(MethodSignatureBuildingUtilsKt.signature(SignatureBuildingComponents.INSTANCE, (ClassDescriptor) containingDeclaration, strComputeJvmDescriptor$default))) {
            return true;
        }
        Boolean boolIfAny = DFS.ifAny(CollectionsKt.listOf(simpleFunctionDescriptor), new DFS.Neighbors<CallableMemberDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings.isMutabilityViolation.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public final Iterable<CallableMemberDescriptor> getNeighbors(CallableMemberDescriptor it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                CallableMemberDescriptor original = it.getOriginal();
                Intrinsics.checkNotNullExpressionValue(original, "it.original");
                return original.getOverriddenDescriptors();
            }
        }, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings.isMutabilityViolation.2
            {
                super(1);
            }

            /* JADX WARN: Code duplicated, block: B:11:0x002b  */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(CallableMemberDescriptor overridden) {
                boolean z2;
                Intrinsics.checkNotNullExpressionValue(overridden, "overridden");
                if (overridden.getKind() == CallableMemberDescriptor.Kind.DECLARATION) {
                    JavaToKotlinClassMapper javaToKotlinClassMapper = JvmBuiltInsSettings.this.j2kClassMapper;
                    DeclarationDescriptor containingDeclaration2 = overridden.getContainingDeclaration();
                    if (containingDeclaration2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    }
                    if (javaToKotlinClassMapper.isMutable((ClassDescriptor) containingDeclaration2)) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                } else {
                    z2 = false;
                }
                return Boolean.valueOf(z2);
            }
        });
        Intrinsics.checkNotNullExpressionValue(boolIfAny, "DFS.ifAny<CallableMember…lassDescriptor)\n        }");
        return boolIfAny.booleanValue();
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$JDKMemberStatus] */
    private final JDKMemberStatus getJdkMethodStatus(FunctionDescriptor functionDescriptor) {
        DeclarationDescriptor containingDeclaration = functionDescriptor.getContainingDeclaration();
        if (containingDeclaration == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        }
        final String strComputeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor, false, false, 3, null);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (JDKMemberStatus) 0;
        Object objDfs = DFS.dfs(CollectionsKt.listOf((ClassDescriptor) containingDeclaration), new DFS.Neighbors<ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings.getJdkMethodStatus.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public final Iterable<ClassDescriptor> getNeighbors(ClassDescriptor it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                TypeConstructor typeConstructor = it.getTypeConstructor();
                Intrinsics.checkNotNullExpressionValue(typeConstructor, "it.typeConstructor");
                Collection<KotlinType> collectionMo1269getSupertypes = typeConstructor.mo1269getSupertypes();
                Intrinsics.checkNotNullExpressionValue(collectionMo1269getSupertypes, "it.typeConstructor.supertypes");
                ArrayList arrayList = new ArrayList();
                Iterator<T> it2 = collectionMo1269getSupertypes.iterator();
                while (it2.hasNext()) {
                    ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = ((KotlinType) it2.next()).getConstructor().mo1268getDeclarationDescriptor();
                    ClassifierDescriptor original = classifierDescriptorMo1268getDeclarationDescriptor != null ? classifierDescriptorMo1268getDeclarationDescriptor.getOriginal() : null;
                    if (!(original instanceof ClassDescriptor)) {
                        original = null;
                    }
                    ClassDescriptor classDescriptor = (ClassDescriptor) original;
                    LazyJavaClassDescriptor javaAnalogue = classDescriptor != null ? JvmBuiltInsSettings.this.getJavaAnalogue(classDescriptor) : null;
                    if (javaAnalogue != null) {
                        arrayList.add(javaAnalogue);
                    }
                }
                return arrayList;
            }
        }, new DFS.AbstractNodeHandler<ClassDescriptor, JDKMemberStatus>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings.getJdkMethodStatus.2
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v10, types: [T, kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$JDKMemberStatus] */
            /* JADX WARN: Type inference failed for: r0v11, types: [T, kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$JDKMemberStatus] */
            /* JADX WARN: Type inference failed for: r0v12, types: [T, kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$JDKMemberStatus] */
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(ClassDescriptor javaClassDescriptor) {
                Intrinsics.checkNotNullParameter(javaClassDescriptor, "javaClassDescriptor");
                String strSignature = MethodSignatureBuildingUtilsKt.signature(SignatureBuildingComponents.INSTANCE, javaClassDescriptor, strComputeJvmDescriptor$default);
                if (JvmBuiltInsSignatures.INSTANCE.getHIDDEN_METHOD_SIGNATURES().contains(strSignature)) {
                    objectRef.element = JDKMemberStatus.HIDDEN;
                } else if (JvmBuiltInsSignatures.INSTANCE.getVISIBLE_METHOD_SIGNATURES().contains(strSignature)) {
                    objectRef.element = JDKMemberStatus.VISIBLE;
                } else if (JvmBuiltInsSignatures.INSTANCE.getDROP_LIST_METHOD_SIGNATURES().contains(strSignature)) {
                    objectRef.element = JDKMemberStatus.DROP;
                }
                return ((JDKMemberStatus) objectRef.element) == null;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public JDKMemberStatus result() {
                JDKMemberStatus jDKMemberStatus = (JDKMemberStatus) objectRef.element;
                return jDKMemberStatus != null ? jDKMemberStatus : JDKMemberStatus.NOT_CONSIDERED;
            }
        });
        Intrinsics.checkNotNullExpressionValue(objDfs, "DFS.dfs<ClassDescriptor,…CONSIDERED\n            })");
        return (JDKMemberStatus) objDfs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LazyJavaClassDescriptor getJavaAnalogue(ClassDescriptor classDescriptor) {
        ClassId classIdMapKotlinToJava;
        FqName fqNameAsSingleFqName;
        if (KotlinBuiltIns.isAny(classDescriptor)) {
            return null;
        }
        ClassDescriptor classDescriptor2 = classDescriptor;
        if (!KotlinBuiltIns.isUnderKotlinPackage(classDescriptor2)) {
            return null;
        }
        FqNameUnsafe fqNameUnsafe = DescriptorUtilsKt.getFqNameUnsafe(classDescriptor2);
        if (!fqNameUnsafe.isSafe() || (classIdMapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(fqNameUnsafe)) == null || (fqNameAsSingleFqName = classIdMapKotlinToJava.asSingleFqName()) == null) {
            return null;
        }
        Intrinsics.checkNotNullExpressionValue(fqNameAsSingleFqName, "JavaToKotlinClassMap.map…leFqName() ?: return null");
        ClassDescriptor classDescriptorResolveClassByFqName = DescriptorUtilKt.resolveClassByFqName(getOwnerModuleDescriptor(), fqNameAsSingleFqName, NoLookupLocation.FROM_BUILTINS);
        return (LazyJavaClassDescriptor) (classDescriptorResolveClassByFqName instanceof LazyJavaClassDescriptor ? classDescriptorResolveClassByFqName : null);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    public Collection<ClassConstructorDescriptor> getConstructors(ClassDescriptor classDescriptor) {
        boolean z;
        Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        if (classDescriptor.getKind() != ClassKind.CLASS || !isAdditionalBuiltInsFeatureSupported()) {
            return CollectionsKt.emptyList();
        }
        LazyJavaClassDescriptor javaAnalogue = getJavaAnalogue(classDescriptor);
        if (javaAnalogue == null) {
            return CollectionsKt.emptyList();
        }
        ClassDescriptor classDescriptorMapJavaToKotlin$default = JavaToKotlinClassMapper.mapJavaToKotlin$default(this.j2kClassMapper, DescriptorUtilsKt.getFqNameSafe(javaAnalogue), FallbackBuiltIns.Companion.getInstance(), null, 4, null);
        if (classDescriptorMapJavaToKotlin$default == null) {
            return CollectionsKt.emptyList();
        }
        LazyJavaClassDescriptor lazyJavaClassDescriptor = javaAnalogue;
        final TypeSubstitutor typeSubstitutorBuildSubstitutor = MappingUtilKt.createMappedTypeParametersSubstitution(classDescriptorMapJavaToKotlin$default, lazyJavaClassDescriptor).buildSubstitutor();
        Function2<ConstructorDescriptor, ConstructorDescriptor, Boolean> function2 = new Function2<ConstructorDescriptor, ConstructorDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings.getConstructors.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Boolean invoke(ConstructorDescriptor constructorDescriptor, ConstructorDescriptor constructorDescriptor2) {
                return Boolean.valueOf(invoke2(constructorDescriptor, constructorDescriptor2));
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(ConstructorDescriptor isEffectivelyTheSameAs, ConstructorDescriptor javaConstructor) {
                Intrinsics.checkNotNullParameter(isEffectivelyTheSameAs, "$this$isEffectivelyTheSameAs");
                Intrinsics.checkNotNullParameter(javaConstructor, "javaConstructor");
                return OverridingUtil.getBothWaysOverridability(isEffectivelyTheSameAs, javaConstructor.substitute(typeSubstitutorBuildSubstitutor)) == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE;
            }
        };
        List<ClassConstructorDescriptor> constructors = javaAnalogue.getConstructors();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = constructors.iterator();
        while (true) {
            boolean z2 = false;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            ClassConstructorDescriptor classConstructorDescriptor = (ClassConstructorDescriptor) next;
            if (classConstructorDescriptor.getVisibility().isPublicAPI()) {
                Collection<ClassConstructorDescriptor> constructors2 = classDescriptorMapJavaToKotlin$default.getConstructors();
                Intrinsics.checkNotNullExpressionValue(constructors2, "defaultKotlinVersion.constructors");
                Collection<ClassConstructorDescriptor> collection = constructors2;
                if (!(collection instanceof Collection) || !collection.isEmpty()) {
                    Iterator<T> it2 = collection.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            z = true;
                            break;
                        }
                        ClassConstructorDescriptor it3 = (ClassConstructorDescriptor) it2.next();
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        if (function2.invoke2((ConstructorDescriptor) it3, (ConstructorDescriptor) classConstructorDescriptor)) {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = true;
                    break;
                }
                if (z && !isTrivialCopyConstructorFor(classConstructorDescriptor, classDescriptor) && !KotlinBuiltIns.isDeprecated(classConstructorDescriptor) && !JvmBuiltInsSignatures.INSTANCE.getHIDDEN_CONSTRUCTOR_SIGNATURES().contains(MethodSignatureBuildingUtilsKt.signature(SignatureBuildingComponents.INSTANCE, lazyJavaClassDescriptor, MethodSignatureMappingKt.computeJvmDescriptor$default(classConstructorDescriptor, false, false, 3, null)))) {
                    z2 = true;
                }
            }
            if (z2) {
                arrayList.add(next);
            }
        }
        ArrayList<ClassConstructorDescriptor> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (ClassConstructorDescriptor classConstructorDescriptor2 : arrayList2) {
            FunctionDescriptor.CopyBuilder<? extends FunctionDescriptor> copyBuilderNewCopyBuilder = classConstructorDescriptor2.newCopyBuilder();
            copyBuilderNewCopyBuilder.setOwner(classDescriptor);
            copyBuilderNewCopyBuilder.setReturnType(classDescriptor.getDefaultType());
            copyBuilderNewCopyBuilder.setPreserveSourceElement();
            copyBuilderNewCopyBuilder.setSubstitution(typeSubstitutorBuildSubstitutor.getSubstitution());
            if (!JvmBuiltInsSignatures.INSTANCE.getVISIBLE_CONSTRUCTOR_SIGNATURES().contains(MethodSignatureBuildingUtilsKt.signature(SignatureBuildingComponents.INSTANCE, lazyJavaClassDescriptor, MethodSignatureMappingKt.computeJvmDescriptor$default(classConstructorDescriptor2, false, false, 3, null)))) {
                copyBuilderNewCopyBuilder.setAdditionalAnnotations(getNotConsideredDeprecation());
            }
            FunctionDescriptor functionDescriptorBuild = copyBuilderNewCopyBuilder.build();
            if (functionDescriptorBuild == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor");
            }
            arrayList3.add((ClassConstructorDescriptor) functionDescriptorBuild);
        }
        return arrayList3;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter
    public boolean isFunctionAvailable(ClassDescriptor classDescriptor, SimpleFunctionDescriptor functionDescriptor) {
        Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        Intrinsics.checkNotNullParameter(functionDescriptor, "functionDescriptor");
        LazyJavaClassDescriptor javaAnalogue = getJavaAnalogue(classDescriptor);
        if (javaAnalogue == null || !functionDescriptor.getAnnotations().hasAnnotation(PlatformDependentDeclarationFilterKt.getPLATFORM_DEPENDENT_ANNOTATION_FQ_NAME())) {
            return true;
        }
        if (!isAdditionalBuiltInsFeatureSupported()) {
            return false;
        }
        String strComputeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor, false, false, 3, null);
        LazyJavaClassMemberScope unsubstitutedMemberScope = javaAnalogue.getUnsubstitutedMemberScope();
        Name name = functionDescriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "functionDescriptor.name");
        Collection<SimpleFunctionDescriptor> contributedFunctions = unsubstitutedMemberScope.getContributedFunctions(name, NoLookupLocation.FROM_BUILTINS);
        if (!(contributedFunctions instanceof Collection) || !contributedFunctions.isEmpty()) {
            Iterator<T> it = contributedFunctions.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(MethodSignatureMappingKt.computeJvmDescriptor$default((SimpleFunctionDescriptor) it.next(), false, false, 3, null), strComputeJvmDescriptor$default)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean isTrivialCopyConstructorFor(ConstructorDescriptor constructorDescriptor, ClassDescriptor classDescriptor) {
        if (constructorDescriptor.getValueParameters().size() == 1) {
            List<ValueParameterDescriptor> valueParameters = constructorDescriptor.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters, "valueParameters");
            Object objSingle = CollectionsKt.single((List<? extends Object>) valueParameters);
            Intrinsics.checkNotNullExpressionValue(objSingle, "valueParameters.single()");
            ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = ((ValueParameterDescriptor) objSingle).getType().getConstructor().mo1268getDeclarationDescriptor();
            if (Intrinsics.areEqual(classifierDescriptorMo1268getDeclarationDescriptor != null ? DescriptorUtilsKt.getFqNameUnsafe(classifierDescriptorMo1268getDeclarationDescriptor) : null, DescriptorUtilsKt.getFqNameUnsafe(classDescriptor))) {
                return true;
            }
        }
        return false;
    }
}
