package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedure;

/* JADX INFO: compiled from: specialBuiltinMembers.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class SpecialBuiltinMembers {
    /* JADX INFO: Access modifiers changed from: private */
    public static final NameAndSignature method(String str, String str2, String str3, String str4) {
        Name nameIdentifier = Name.identifier(str2);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(name)");
        return new NameAndSignature(nameIdentifier, SignatureBuildingComponents.INSTANCE.signature(str, str2 + '(' + str3 + ')' + str4));
    }

    public static final <T extends CallableMemberDescriptor> T getOverriddenBuiltinWithDifferentJvmName(T getOverriddenBuiltinWithDifferentJvmName) {
        Intrinsics.checkNotNullParameter(getOverriddenBuiltinWithDifferentJvmName, "$this$getOverriddenBuiltinWithDifferentJvmName");
        if (!BuiltinMethodsWithDifferentJvmName.INSTANCE.getORIGINAL_SHORT_NAMES().contains(getOverriddenBuiltinWithDifferentJvmName.getName()) && !BuiltinSpecialProperties.INSTANCE.getSPECIAL_SHORT_NAMES().contains(DescriptorUtilsKt.getPropertyIfAccessor(getOverriddenBuiltinWithDifferentJvmName).getName())) {
            return null;
        }
        if ((getOverriddenBuiltinWithDifferentJvmName instanceof PropertyDescriptor) || (getOverriddenBuiltinWithDifferentJvmName instanceof PropertyAccessorDescriptor)) {
            return (T) DescriptorUtilsKt.firstOverridden$default(getOverriddenBuiltinWithDifferentJvmName, false, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                    return Boolean.valueOf(invoke2(callableMemberDescriptor));
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(CallableMemberDescriptor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ClassicBuiltinSpecialProperties.INSTANCE.hasBuiltinSpecialPropertyFqName(DescriptorUtilsKt.getPropertyIfAccessor(it));
                }
            }, 1, null);
        }
        if (getOverriddenBuiltinWithDifferentJvmName instanceof SimpleFunctionDescriptor) {
            return (T) DescriptorUtilsKt.firstOverridden$default(getOverriddenBuiltinWithDifferentJvmName, false, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                    return Boolean.valueOf(invoke2(callableMemberDescriptor));
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(CallableMemberDescriptor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return BuiltinMethodsWithDifferentJvmName.INSTANCE.isBuiltinFunctionWithDifferentNameInJvm((SimpleFunctionDescriptor) it);
                }
            }, 1, null);
        }
        return null;
    }

    public static final boolean doesOverrideBuiltinWithDifferentJvmName(CallableMemberDescriptor doesOverrideBuiltinWithDifferentJvmName) {
        Intrinsics.checkNotNullParameter(doesOverrideBuiltinWithDifferentJvmName, "$this$doesOverrideBuiltinWithDifferentJvmName");
        return getOverriddenBuiltinWithDifferentJvmName(doesOverrideBuiltinWithDifferentJvmName) != null;
    }

    public static final <T extends CallableMemberDescriptor> T getOverriddenSpecialBuiltin(T getOverriddenSpecialBuiltin) {
        Intrinsics.checkNotNullParameter(getOverriddenSpecialBuiltin, "$this$getOverriddenSpecialBuiltin");
        T t = (T) getOverriddenBuiltinWithDifferentJvmName(getOverriddenSpecialBuiltin);
        if (t != null) {
            return t;
        }
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
        Name name = getOverriddenSpecialBuiltin.getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        if (builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            return (T) DescriptorUtilsKt.firstOverridden$default(getOverriddenSpecialBuiltin, false, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenSpecialBuiltin.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                    return Boolean.valueOf(invoke2(callableMemberDescriptor));
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(CallableMemberDescriptor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return KotlinBuiltIns.isBuiltIn(it) && BuiltinMethodsWithSpecialGenericSignature.getSpecialSignatureInfo(it) != null;
                }
            }, 1, null);
        }
        return null;
    }

    public static final String getJvmMethodNameIfSpecial(CallableMemberDescriptor callableMemberDescriptor) {
        CallableMemberDescriptor propertyIfAccessor;
        Name jvmName;
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "callableMemberDescriptor");
        CallableMemberDescriptor overriddenBuiltinThatAffectsJvmName = getOverriddenBuiltinThatAffectsJvmName(callableMemberDescriptor);
        if (overriddenBuiltinThatAffectsJvmName == null || (propertyIfAccessor = DescriptorUtilsKt.getPropertyIfAccessor(overriddenBuiltinThatAffectsJvmName)) == null) {
            return null;
        }
        if (propertyIfAccessor instanceof PropertyDescriptor) {
            return ClassicBuiltinSpecialProperties.INSTANCE.getBuiltinSpecialPropertyGetterName(propertyIfAccessor);
        }
        if (!(propertyIfAccessor instanceof SimpleFunctionDescriptor) || (jvmName = BuiltinMethodsWithDifferentJvmName.INSTANCE.getJvmName((SimpleFunctionDescriptor) propertyIfAccessor)) == null) {
            return null;
        }
        return jvmName.asString();
    }

    private static final CallableMemberDescriptor getOverriddenBuiltinThatAffectsJvmName(CallableMemberDescriptor callableMemberDescriptor) {
        if (KotlinBuiltIns.isBuiltIn(callableMemberDescriptor)) {
            return getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor);
        }
        return null;
    }

    public static final boolean hasRealKotlinSuperClassWithOverrideOf(ClassDescriptor hasRealKotlinSuperClassWithOverrideOf, CallableDescriptor specialCallableDescriptor) {
        Intrinsics.checkNotNullParameter(hasRealKotlinSuperClassWithOverrideOf, "$this$hasRealKotlinSuperClassWithOverrideOf");
        Intrinsics.checkNotNullParameter(specialCallableDescriptor, "specialCallableDescriptor");
        DeclarationDescriptor containingDeclaration = specialCallableDescriptor.getContainingDeclaration();
        if (containingDeclaration == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        }
        SimpleType defaultType = ((ClassDescriptor) containingDeclaration).getDefaultType();
        Intrinsics.checkNotNullExpressionValue(defaultType, "(specialCallableDescript…ssDescriptor).defaultType");
        ClassDescriptor superClassDescriptor = DescriptorUtils.getSuperClassDescriptor(hasRealKotlinSuperClassWithOverrideOf);
        while (true) {
            if (superClassDescriptor == null) {
                return false;
            }
            if (!(superClassDescriptor instanceof JavaClassDescriptor)) {
                if (TypeCheckingProcedure.findCorrespondingSupertype(superClassDescriptor.getDefaultType(), defaultType) != null) {
                    return !KotlinBuiltIns.isBuiltIn(superClassDescriptor);
                }
            }
            superClassDescriptor = DescriptorUtils.getSuperClassDescriptor(superClassDescriptor);
        }
    }

    public static final boolean isFromJava(CallableMemberDescriptor isFromJava) {
        Intrinsics.checkNotNullParameter(isFromJava, "$this$isFromJava");
        return DescriptorUtilsKt.getPropertyIfAccessor(isFromJava).getContainingDeclaration() instanceof JavaClassDescriptor;
    }

    public static final boolean isFromJavaOrBuiltins(CallableMemberDescriptor isFromJavaOrBuiltins) {
        Intrinsics.checkNotNullParameter(isFromJavaOrBuiltins, "$this$isFromJavaOrBuiltins");
        return isFromJava(isFromJavaOrBuiltins) || KotlinBuiltIns.isBuiltIn(isFromJavaOrBuiltins);
    }
}
