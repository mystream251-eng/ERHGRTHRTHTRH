package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefinerKt;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: DescriptorUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class DescriptorUtilsKt {
    private static final Name RETENTION_PARAMETER_NAME;

    static {
        Name nameIdentifier = Name.identifier("value");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(\"value\")");
        RETENTION_PARAMETER_NAME = nameIdentifier;
    }

    public static final FqNameUnsafe getFqNameUnsafe(DeclarationDescriptor fqNameUnsafe) {
        Intrinsics.checkNotNullParameter(fqNameUnsafe, "$this$fqNameUnsafe");
        FqNameUnsafe fqName = DescriptorUtils.getFqName(fqNameUnsafe);
        Intrinsics.checkNotNullExpressionValue(fqName, "DescriptorUtils.getFqName(this)");
        return fqName;
    }

    public static final FqName getFqNameSafe(DeclarationDescriptor fqNameSafe) {
        Intrinsics.checkNotNullParameter(fqNameSafe, "$this$fqNameSafe");
        FqName fqNameSafe2 = DescriptorUtils.getFqNameSafe(fqNameSafe);
        Intrinsics.checkNotNullExpressionValue(fqNameSafe2, "DescriptorUtils.getFqNameSafe(this)");
        return fqNameSafe2;
    }

    public static final ModuleDescriptor getModule(DeclarationDescriptor module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        ModuleDescriptor containingModule = DescriptorUtils.getContainingModule(module);
        Intrinsics.checkNotNullExpressionValue(containingModule, "DescriptorUtils.getContainingModule(this)");
        return containingModule;
    }

    public static final ClassDescriptor resolveTopLevelClass(ModuleDescriptor resolveTopLevelClass, FqName topLevelClassFqName, LookupLocation location) {
        Intrinsics.checkNotNullParameter(resolveTopLevelClass, "$this$resolveTopLevelClass");
        Intrinsics.checkNotNullParameter(topLevelClassFqName, "topLevelClassFqName");
        Intrinsics.checkNotNullParameter(location, "location");
        boolean z = !topLevelClassFqName.isRoot();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Assertion failed");
        }
        FqName fqNameParent = topLevelClassFqName.parent();
        Intrinsics.checkNotNullExpressionValue(fqNameParent, "topLevelClassFqName.parent()");
        MemberScope memberScope = resolveTopLevelClass.getPackage(fqNameParent).getMemberScope();
        Name nameShortName = topLevelClassFqName.shortName();
        Intrinsics.checkNotNullExpressionValue(nameShortName, "topLevelClassFqName.shortName()");
        ClassifierDescriptor contributedClassifier = memberScope.mo1270getContributedClassifier(nameShortName, location);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        return (ClassDescriptor) contributedClassifier;
    }

    public static final ClassId getClassId(ClassifierDescriptor classifierDescriptor) {
        DeclarationDescriptor containingDeclaration;
        ClassId classId;
        if (classifierDescriptor == null || (containingDeclaration = classifierDescriptor.getContainingDeclaration()) == null) {
            return null;
        }
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            return new ClassId(((PackageFragmentDescriptor) containingDeclaration).getFqName(), classifierDescriptor.getName());
        }
        if (!(containingDeclaration instanceof ClassifierDescriptorWithTypeParameters) || (classId = getClassId((ClassifierDescriptor) containingDeclaration)) == null) {
            return null;
        }
        return classId.createNestedClassId(classifierDescriptor.getName());
    }

    public static final ClassDescriptor getSuperClassNotAny(ClassDescriptor getSuperClassNotAny) {
        Intrinsics.checkNotNullParameter(getSuperClassNotAny, "$this$getSuperClassNotAny");
        for (KotlinType kotlinType : getSuperClassNotAny.getDefaultType().getConstructor().mo1269getSupertypes()) {
            if (!KotlinBuiltIns.isAnyOrNullableAny(kotlinType)) {
                ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = kotlinType.getConstructor().mo1268getDeclarationDescriptor();
                if (DescriptorUtils.isClassOrEnumClass(classifierDescriptorMo1268getDeclarationDescriptor)) {
                    if (classifierDescriptorMo1268getDeclarationDescriptor != null) {
                        return (ClassDescriptor) classifierDescriptorMo1268getDeclarationDescriptor;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                }
            }
        }
        return null;
    }

    public static final KotlinBuiltIns getBuiltIns(DeclarationDescriptor builtIns) {
        Intrinsics.checkNotNullParameter(builtIns, "$this$builtIns");
        return getModule(builtIns).getBuiltIns();
    }

    public static final boolean declaresOrInheritsDefaultValue(ValueParameterDescriptor declaresOrInheritsDefaultValue) {
        Intrinsics.checkNotNullParameter(declaresOrInheritsDefaultValue, "$this$declaresOrInheritsDefaultValue");
        Boolean boolIfAny = DFS.ifAny(CollectionsKt.listOf(declaresOrInheritsDefaultValue), new DFS.Neighbors<ValueParameterDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public final Iterable<ValueParameterDescriptor> getNeighbors(ValueParameterDescriptor current) {
                Intrinsics.checkNotNullExpressionValue(current, "current");
                Collection<ValueParameterDescriptor> overriddenDescriptors = current.getOverriddenDescriptors();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(overriddenDescriptors, 10));
                Iterator<T> it = overriddenDescriptors.iterator();
                while (it.hasNext()) {
                    arrayList.add(((ValueParameterDescriptor) it.next()).getOriginal());
                }
                return arrayList;
            }
        }, AnonymousClass2.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(boolIfAny, "DFS.ifAny(\n        listO…eclaresDefaultValue\n    )");
        return boolIfAny.booleanValue();
    }

    /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$declaresOrInheritsDefaultValue$2, reason: invalid class name */
    /* JADX INFO: compiled from: DescriptorUtils.kt */
    static final /* synthetic */ class AnonymousClass2 extends FunctionReference implements Function1<ValueParameterDescriptor, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "declaresDefaultValue";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(ValueParameterDescriptor.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "declaresDefaultValue()Z";
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(ValueParameterDescriptor valueParameterDescriptor) {
            return Boolean.valueOf(invoke2(valueParameterDescriptor));
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(ValueParameterDescriptor p1) {
            Intrinsics.checkNotNullParameter(p1, "p1");
            return p1.declaresDefaultValue();
        }
    }

    public static final Sequence<DeclarationDescriptor> getParentsWithSelf(DeclarationDescriptor parentsWithSelf) {
        Intrinsics.checkNotNullParameter(parentsWithSelf, "$this$parentsWithSelf");
        return SequencesKt.generateSequence(parentsWithSelf, new Function1<DeclarationDescriptor, DeclarationDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$parentsWithSelf$1
            @Override // kotlin.jvm.functions.Function1
            public final DeclarationDescriptor invoke(DeclarationDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getContainingDeclaration();
            }
        });
    }

    public static final Sequence<DeclarationDescriptor> getParents(DeclarationDescriptor parents) {
        Intrinsics.checkNotNullParameter(parents, "$this$parents");
        return SequencesKt.drop(getParentsWithSelf(parents), 1);
    }

    public static final CallableMemberDescriptor getPropertyIfAccessor(CallableMemberDescriptor propertyIfAccessor) {
        Intrinsics.checkNotNullParameter(propertyIfAccessor, "$this$propertyIfAccessor");
        if (!(propertyIfAccessor instanceof PropertyAccessorDescriptor)) {
            return propertyIfAccessor;
        }
        PropertyDescriptor correspondingProperty = ((PropertyAccessorDescriptor) propertyIfAccessor).getCorrespondingProperty();
        Intrinsics.checkNotNullExpressionValue(correspondingProperty, "correspondingProperty");
        return correspondingProperty;
    }

    public static final FqName fqNameOrNull(DeclarationDescriptor fqNameOrNull) {
        Intrinsics.checkNotNullParameter(fqNameOrNull, "$this$fqNameOrNull");
        FqNameUnsafe fqNameUnsafe = getFqNameUnsafe(fqNameOrNull);
        if (!fqNameUnsafe.isSafe()) {
            fqNameUnsafe = null;
        }
        if (fqNameUnsafe != null) {
            return fqNameUnsafe.toSafe();
        }
        return null;
    }

    public static /* synthetic */ CallableMemberDescriptor firstOverridden$default(CallableMemberDescriptor callableMemberDescriptor, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return firstOverridden(callableMemberDescriptor, z, function1);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor] */
    public static final CallableMemberDescriptor firstOverridden(CallableMemberDescriptor firstOverridden, final boolean z, final Function1<? super CallableMemberDescriptor, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(firstOverridden, "$this$firstOverridden");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (CallableMemberDescriptor) 0;
        return (CallableMemberDescriptor) DFS.dfs(CollectionsKt.listOf(firstOverridden), new DFS.Neighbors<CallableMemberDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.firstOverridden.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public final Iterable<CallableMemberDescriptor> getNeighbors(CallableMemberDescriptor callableMemberDescriptor) {
                Collection<? extends CallableMemberDescriptor> collectionEmptyList;
                if (z) {
                    callableMemberDescriptor = callableMemberDescriptor != null ? callableMemberDescriptor.getOriginal() : null;
                }
                if (callableMemberDescriptor == null || (collectionEmptyList = callableMemberDescriptor.getOverriddenDescriptors()) == null) {
                    collectionEmptyList = CollectionsKt.emptyList();
                }
                return collectionEmptyList;
            }
        }, new DFS.AbstractNodeHandler<CallableMemberDescriptor, CallableMemberDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.firstOverridden.2
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(CallableMemberDescriptor current) {
                Intrinsics.checkNotNullParameter(current, "current");
                return ((CallableMemberDescriptor) objectRef.element) == null;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public void afterChildren(CallableMemberDescriptor current) {
                Intrinsics.checkNotNullParameter(current, "current");
                if (((CallableMemberDescriptor) objectRef.element) == null && ((Boolean) predicate.invoke(current)).booleanValue()) {
                    objectRef.element = current;
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public CallableMemberDescriptor result() {
                return (CallableMemberDescriptor) objectRef.element;
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$computeSealedSubclasses$1] */
    public static final Collection<ClassDescriptor> computeSealedSubclasses(final ClassDescriptor sealedClass) {
        Intrinsics.checkNotNullParameter(sealedClass, "sealedClass");
        if (sealedClass.getModality() != Modality.SEALED) {
            return CollectionsKt.emptyList();
        }
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        ?? r1 = new Function2<MemberScope, Boolean, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.computeSealedSubclasses.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(MemberScope memberScope, Boolean bool) {
                invoke(memberScope, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(MemberScope scope, boolean z) {
                Intrinsics.checkNotNullParameter(scope, "scope");
                for (DeclarationDescriptor declarationDescriptor : ResolutionScope.DefaultImpls.getContributedDescriptors$default(scope, DescriptorKindFilter.CLASSIFIERS, null, 2, null)) {
                    if (declarationDescriptor instanceof ClassDescriptor) {
                        ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
                        if (DescriptorUtils.isDirectSubclass(classDescriptor, sealedClass)) {
                            linkedHashSet.add(declarationDescriptor);
                        }
                        if (z) {
                            MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
                            Intrinsics.checkNotNullExpressionValue(unsubstitutedInnerClassesScope, "descriptor.unsubstitutedInnerClassesScope");
                            invoke(unsubstitutedInnerClassesScope, z);
                        }
                    }
                }
            }
        };
        DeclarationDescriptor containingDeclaration = sealedClass.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "sealedClass.containingDeclaration");
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            r1.invoke(((PackageFragmentDescriptor) containingDeclaration).getMemberScope(), false);
        }
        MemberScope unsubstitutedInnerClassesScope = sealedClass.getUnsubstitutedInnerClassesScope();
        Intrinsics.checkNotNullExpressionValue(unsubstitutedInnerClassesScope, "sealedClass.unsubstitutedInnerClassesScope");
        r1.invoke(unsubstitutedInnerClassesScope, true);
        return linkedHashSet;
    }

    public static final ClassDescriptor getAnnotationClass(AnnotationDescriptor annotationClass) {
        Intrinsics.checkNotNullParameter(annotationClass, "$this$annotationClass");
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = annotationClass.getType().getConstructor().mo1268getDeclarationDescriptor();
        if (!(classifierDescriptorMo1268getDeclarationDescriptor instanceof ClassDescriptor)) {
            classifierDescriptorMo1268getDeclarationDescriptor = null;
        }
        return (ClassDescriptor) classifierDescriptorMo1268getDeclarationDescriptor;
    }

    public static final ConstantValue<?> firstArgument(AnnotationDescriptor firstArgument) {
        Intrinsics.checkNotNullParameter(firstArgument, "$this$firstArgument");
        return (ConstantValue) CollectionsKt.firstOrNull(firstArgument.getAllValueArguments().values());
    }

    public static final KotlinTypeRefiner getKotlinTypeRefiner(ModuleDescriptor getKotlinTypeRefiner) {
        KotlinTypeRefiner kotlinTypeRefiner;
        Intrinsics.checkNotNullParameter(getKotlinTypeRefiner, "$this$getKotlinTypeRefiner");
        kotlin.reflect.jvm.internal.impl.types.checker.Ref ref = (kotlin.reflect.jvm.internal.impl.types.checker.Ref) getKotlinTypeRefiner.getCapability(KotlinTypeRefinerKt.getREFINER_CAPABILITY());
        return (ref == null || (kotlinTypeRefiner = (KotlinTypeRefiner) ref.getValue()) == null) ? KotlinTypeRefiner.Default.INSTANCE : kotlinTypeRefiner;
    }

    public static final boolean isTypeRefinementEnabled(ModuleDescriptor isTypeRefinementEnabled) {
        Intrinsics.checkNotNullParameter(isTypeRefinementEnabled, "$this$isTypeRefinementEnabled");
        kotlin.reflect.jvm.internal.impl.types.checker.Ref ref = (kotlin.reflect.jvm.internal.impl.types.checker.Ref) isTypeRefinementEnabled.getCapability(KotlinTypeRefinerKt.getREFINER_CAPABILITY());
        return (ref != null ? (KotlinTypeRefiner) ref.getValue() : null) != null;
    }
}
