package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* JADX INFO: compiled from: DescriptorEquivalenceForOverrides.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class DescriptorEquivalenceForOverrides {
    public static final DescriptorEquivalenceForOverrides INSTANCE = new DescriptorEquivalenceForOverrides();

    private DescriptorEquivalenceForOverrides() {
    }

    public static /* synthetic */ boolean areEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2, boolean z, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = true;
        }
        return descriptorEquivalenceForOverrides.areEquivalent(declarationDescriptor, declarationDescriptor2, z, z2);
    }

    public final boolean areEquivalent(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2, boolean z, boolean z2) {
        if ((declarationDescriptor instanceof ClassDescriptor) && (declarationDescriptor2 instanceof ClassDescriptor)) {
            return areClassesEquivalent((ClassDescriptor) declarationDescriptor, (ClassDescriptor) declarationDescriptor2);
        }
        if ((declarationDescriptor instanceof TypeParameterDescriptor) && (declarationDescriptor2 instanceof TypeParameterDescriptor)) {
            return areTypeParametersEquivalent$default(this, (TypeParameterDescriptor) declarationDescriptor, (TypeParameterDescriptor) declarationDescriptor2, z, null, 8, null);
        }
        if ((declarationDescriptor instanceof CallableDescriptor) && (declarationDescriptor2 instanceof CallableDescriptor)) {
            return areCallableDescriptorsEquivalent$default(this, (CallableDescriptor) declarationDescriptor, (CallableDescriptor) declarationDescriptor2, z, z2, false, KotlinTypeRefiner.Default.INSTANCE, 16, null);
        }
        return ((declarationDescriptor instanceof PackageFragmentDescriptor) && (declarationDescriptor2 instanceof PackageFragmentDescriptor)) ? Intrinsics.areEqual(((PackageFragmentDescriptor) declarationDescriptor).getFqName(), ((PackageFragmentDescriptor) declarationDescriptor2).getFqName()) : Intrinsics.areEqual(declarationDescriptor, declarationDescriptor2);
    }

    private final boolean areClassesEquivalent(ClassDescriptor classDescriptor, ClassDescriptor classDescriptor2) {
        return Intrinsics.areEqual(classDescriptor.getTypeConstructor(), classDescriptor2.getTypeConstructor());
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ boolean areTypeParametersEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 8) != 0) {
            function2 = new Function2<DeclarationDescriptor, DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areTypeParametersEquivalent.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
                    return false;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
                    return Boolean.valueOf(invoke2(declarationDescriptor, declarationDescriptor2));
                }
            };
        }
        return descriptorEquivalenceForOverrides.areTypeParametersEquivalent(typeParameterDescriptor, typeParameterDescriptor2, z, function2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean areTypeParametersEquivalent(TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, boolean z, Function2<? super DeclarationDescriptor, ? super DeclarationDescriptor, Boolean> function2) {
        if (Intrinsics.areEqual(typeParameterDescriptor, typeParameterDescriptor2)) {
            return true;
        }
        return !Intrinsics.areEqual(typeParameterDescriptor.getContainingDeclaration(), typeParameterDescriptor2.getContainingDeclaration()) && ownersEquivalent(typeParameterDescriptor, typeParameterDescriptor2, function2, z) && typeParameterDescriptor.getIndex() == typeParameterDescriptor2.getIndex();
    }

    private final SourceElement singleSource(CallableDescriptor callableDescriptor) {
        while (callableDescriptor instanceof CallableMemberDescriptor) {
            CallableMemberDescriptor callableMemberDescriptor = (CallableMemberDescriptor) callableDescriptor;
            if (callableMemberDescriptor.getKind() != CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
                break;
            }
            Collection<? extends CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
            Intrinsics.checkNotNullExpressionValue(overriddenDescriptors, "overriddenDescriptors");
            CallableMemberDescriptor callableMemberDescriptor2 = (CallableMemberDescriptor) CollectionsKt.singleOrNull(overriddenDescriptors);
            if (callableMemberDescriptor2 == null) {
                return null;
            }
            callableDescriptor = callableMemberDescriptor2;
        }
        return callableDescriptor.getSource();
    }

    public static /* synthetic */ boolean areCallableDescriptorsEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, boolean z, boolean z2, boolean z3, KotlinTypeRefiner kotlinTypeRefiner, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = true;
        }
        boolean z4 = z2;
        if ((i & 16) != 0) {
            z3 = false;
        }
        return descriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent(callableDescriptor, callableDescriptor2, z, z4, z3, kotlinTypeRefiner);
    }

    public final boolean areCallableDescriptorsEquivalent(final CallableDescriptor a2, final CallableDescriptor b, final boolean z, boolean z2, boolean z3, KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        if (Intrinsics.areEqual(a2, b)) {
            return true;
        }
        if (!Intrinsics.areEqual(a2.getName(), b.getName())) {
            return false;
        }
        if (z2 && (a2 instanceof MemberDescriptor) && (b instanceof MemberDescriptor) && ((MemberDescriptor) a2).isExpect() != ((MemberDescriptor) b).isExpect()) {
            return false;
        }
        if (Intrinsics.areEqual(a2.getContainingDeclaration(), b.getContainingDeclaration()) && (!z || (!Intrinsics.areEqual(singleSource(a2), singleSource(b))))) {
            return false;
        }
        CallableDescriptor callableDescriptor = a2;
        if (!DescriptorUtils.isLocal(callableDescriptor)) {
            CallableDescriptor callableDescriptor2 = b;
            if (DescriptorUtils.isLocal(callableDescriptor2) || !ownersEquivalent(callableDescriptor, callableDescriptor2, new Function2<DeclarationDescriptor, DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
                    return false;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
                    return Boolean.valueOf(invoke2(declarationDescriptor, declarationDescriptor2));
                }
            }, z)) {
                return false;
            }
            OverridingUtil overridingUtilCreate = OverridingUtil.create(kotlinTypeRefiner, new KotlinTypeChecker.TypeConstructorEquality() { // from class: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$overridingUtil$1
                @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.TypeConstructorEquality
                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                public final boolean equals(TypeConstructor c1, TypeConstructor c2) {
                    Intrinsics.checkNotNullParameter(c1, "c1");
                    Intrinsics.checkNotNullParameter(c2, "c2");
                    if (Intrinsics.areEqual(c1, c2)) {
                        return true;
                    }
                    ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = c1.mo1268getDeclarationDescriptor();
                    ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor2 = c2.mo1268getDeclarationDescriptor();
                    if ((classifierDescriptorMo1268getDeclarationDescriptor instanceof TypeParameterDescriptor) && (classifierDescriptorMo1268getDeclarationDescriptor2 instanceof TypeParameterDescriptor)) {
                        return DescriptorEquivalenceForOverrides.INSTANCE.areTypeParametersEquivalent((TypeParameterDescriptor) classifierDescriptorMo1268getDeclarationDescriptor, (TypeParameterDescriptor) classifierDescriptorMo1268getDeclarationDescriptor2, z, new Function2<DeclarationDescriptor, DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$overridingUtil$1.1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
                                return Boolean.valueOf(invoke2(declarationDescriptor, declarationDescriptor2));
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final boolean invoke2(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
                                return Intrinsics.areEqual(declarationDescriptor, a2) && Intrinsics.areEqual(declarationDescriptor2, b);
                            }
                        });
                    }
                    return false;
                }
            });
            Intrinsics.checkNotNullExpressionValue(overridingUtilCreate, "OverridingUtil.create(ko…= a && y == b }\n        }");
            OverridingUtil.OverrideCompatibilityInfo overrideCompatibilityInfoIsOverridableBy = overridingUtilCreate.isOverridableBy(a2, b, null, !z3);
            Intrinsics.checkNotNullExpressionValue(overrideCompatibilityInfoIsOverridableBy, "overridingUtil.isOverrid… null, !ignoreReturnType)");
            if (overrideCompatibilityInfoIsOverridableBy.getResult() == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE) {
                OverridingUtil.OverrideCompatibilityInfo overrideCompatibilityInfoIsOverridableBy2 = overridingUtilCreate.isOverridableBy(b, a2, null, !z3);
                Intrinsics.checkNotNullExpressionValue(overrideCompatibilityInfoIsOverridableBy2, "overridingUtil.isOverrid… null, !ignoreReturnType)");
                if (overrideCompatibilityInfoIsOverridableBy2.getResult() == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private final boolean ownersEquivalent(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2, Function2<? super DeclarationDescriptor, ? super DeclarationDescriptor, Boolean> function2, boolean z) {
        DeclarationDescriptor containingDeclaration = declarationDescriptor.getContainingDeclaration();
        DeclarationDescriptor containingDeclaration2 = declarationDescriptor2.getContainingDeclaration();
        if ((containingDeclaration instanceof CallableMemberDescriptor) || (containingDeclaration2 instanceof CallableMemberDescriptor)) {
            return function2.invoke(containingDeclaration, containingDeclaration2).booleanValue();
        }
        return areEquivalent$default(this, containingDeclaration, containingDeclaration2, z, false, 8, null);
    }
}
