package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: inlineClassManglingRules.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class InlineClassManglingRulesKt {
    public static final boolean shouldHideConstructorDueToInlineClassTypeValueParameters(CallableMemberDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (!(descriptor instanceof ClassConstructorDescriptor)) {
            descriptor = null;
        }
        ClassConstructorDescriptor classConstructorDescriptor = (ClassConstructorDescriptor) descriptor;
        if (classConstructorDescriptor == null || DescriptorVisibilities.isPrivate(classConstructorDescriptor.getVisibility())) {
            return false;
        }
        ClassDescriptor constructedClass = classConstructorDescriptor.getConstructedClass();
        Intrinsics.checkNotNullExpressionValue(constructedClass, "constructorDescriptor.constructedClass");
        if (constructedClass.isInline() || DescriptorUtils.isSealedClass(classConstructorDescriptor.getConstructedClass())) {
            return false;
        }
        List<ValueParameterDescriptor> valueParameters = classConstructorDescriptor.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "constructorDescriptor.valueParameters");
        List<ValueParameterDescriptor> list = valueParameters;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (ValueParameterDescriptor it : list) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            KotlinType type = it.getType();
            Intrinsics.checkNotNullExpressionValue(type, "it.type");
            if (requiresFunctionNameManglingInParameterTypes(type)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isInlineClassThatRequiresMangling(DeclarationDescriptor isInlineClassThatRequiresMangling) {
        Intrinsics.checkNotNullParameter(isInlineClassThatRequiresMangling, "$this$isInlineClassThatRequiresMangling");
        return InlineClassesUtilsKt.isInlineClass(isInlineClassThatRequiresMangling) && !isDontMangleClass((ClassDescriptor) isInlineClassThatRequiresMangling);
    }

    public static final boolean isInlineClassThatRequiresMangling(KotlinType isInlineClassThatRequiresMangling) {
        Intrinsics.checkNotNullParameter(isInlineClassThatRequiresMangling, "$this$isInlineClassThatRequiresMangling");
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = isInlineClassThatRequiresMangling.getConstructor().mo1268getDeclarationDescriptor();
        return classifierDescriptorMo1268getDeclarationDescriptor != null && isInlineClassThatRequiresMangling(classifierDescriptorMo1268getDeclarationDescriptor);
    }

    private static final boolean requiresFunctionNameManglingInParameterTypes(KotlinType kotlinType) {
        return isInlineClassThatRequiresMangling(kotlinType) || isTypeParameterWithUpperBoundThatRequiresMangling(kotlinType);
    }

    private static final boolean isDontMangleClass(ClassDescriptor classDescriptor) {
        return Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(classDescriptor), StandardNames.RESULT_FQ_NAME);
    }

    private static final boolean isTypeParameterWithUpperBoundThatRequiresMangling(KotlinType kotlinType) {
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = kotlinType.getConstructor().mo1268getDeclarationDescriptor();
        if (!(classifierDescriptorMo1268getDeclarationDescriptor instanceof TypeParameterDescriptor)) {
            classifierDescriptorMo1268getDeclarationDescriptor = null;
        }
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) classifierDescriptorMo1268getDeclarationDescriptor;
        if (typeParameterDescriptor != null) {
            return requiresFunctionNameManglingInParameterTypes(TypeUtilsKt.getRepresentativeUpperBound(typeParameterDescriptor));
        }
        return false;
    }
}
