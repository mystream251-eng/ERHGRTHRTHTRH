package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX INFO: compiled from: inlineClassesUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class InlineClassesUtilsKt {
    public static final ValueParameterDescriptor underlyingRepresentation(ClassDescriptor underlyingRepresentation) {
        ClassConstructorDescriptor classConstructorDescriptorMo1262getUnsubstitutedPrimaryConstructor;
        List<ValueParameterDescriptor> valueParameters;
        Intrinsics.checkNotNullParameter(underlyingRepresentation, "$this$underlyingRepresentation");
        if (!underlyingRepresentation.isInline() || (classConstructorDescriptorMo1262getUnsubstitutedPrimaryConstructor = underlyingRepresentation.mo1262getUnsubstitutedPrimaryConstructor()) == null || (valueParameters = classConstructorDescriptorMo1262getUnsubstitutedPrimaryConstructor.getValueParameters()) == null) {
            return null;
        }
        return (ValueParameterDescriptor) CollectionsKt.singleOrNull((List) valueParameters);
    }

    public static final boolean isInlineClass(DeclarationDescriptor isInlineClass) {
        Intrinsics.checkNotNullParameter(isInlineClass, "$this$isInlineClass");
        return (isInlineClass instanceof ClassDescriptor) && ((ClassDescriptor) isInlineClass).isInline();
    }

    public static final ValueParameterDescriptor unsubstitutedUnderlyingParameter(KotlinType unsubstitutedUnderlyingParameter) {
        Intrinsics.checkNotNullParameter(unsubstitutedUnderlyingParameter, "$this$unsubstitutedUnderlyingParameter");
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = unsubstitutedUnderlyingParameter.getConstructor().mo1268getDeclarationDescriptor();
        if (!(classifierDescriptorMo1268getDeclarationDescriptor instanceof ClassDescriptor)) {
            classifierDescriptorMo1268getDeclarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptorMo1268getDeclarationDescriptor;
        if (classDescriptor != null) {
            return underlyingRepresentation(classDescriptor);
        }
        return null;
    }

    public static final boolean isInlineClassType(KotlinType isInlineClassType) {
        Intrinsics.checkNotNullParameter(isInlineClassType, "$this$isInlineClassType");
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = isInlineClassType.getConstructor().mo1268getDeclarationDescriptor();
        if (classifierDescriptorMo1268getDeclarationDescriptor != null) {
            return isInlineClass(classifierDescriptorMo1268getDeclarationDescriptor);
        }
        return false;
    }

    public static final KotlinType substitutedUnderlyingType(KotlinType substitutedUnderlyingType) {
        Intrinsics.checkNotNullParameter(substitutedUnderlyingType, "$this$substitutedUnderlyingType");
        ValueParameterDescriptor valueParameterDescriptorUnsubstitutedUnderlyingParameter = unsubstitutedUnderlyingParameter(substitutedUnderlyingType);
        if (valueParameterDescriptorUnsubstitutedUnderlyingParameter != null) {
            return TypeSubstitutor.create(substitutedUnderlyingType).substitute(valueParameterDescriptorUnsubstitutedUnderlyingParameter.getType(), Variance.INVARIANT);
        }
        return null;
    }

    public static final boolean isGetterOfUnderlyingPropertyOfInlineClass(CallableDescriptor isGetterOfUnderlyingPropertyOfInlineClass) {
        Intrinsics.checkNotNullParameter(isGetterOfUnderlyingPropertyOfInlineClass, "$this$isGetterOfUnderlyingPropertyOfInlineClass");
        if (isGetterOfUnderlyingPropertyOfInlineClass instanceof PropertyGetterDescriptor) {
            PropertyDescriptor correspondingProperty = ((PropertyGetterDescriptor) isGetterOfUnderlyingPropertyOfInlineClass).getCorrespondingProperty();
            Intrinsics.checkNotNullExpressionValue(correspondingProperty, "correspondingProperty");
            if (isUnderlyingPropertyOfInlineClass(correspondingProperty)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isUnderlyingPropertyOfInlineClass(VariableDescriptor isUnderlyingPropertyOfInlineClass) {
        Intrinsics.checkNotNullParameter(isUnderlyingPropertyOfInlineClass, "$this$isUnderlyingPropertyOfInlineClass");
        DeclarationDescriptor containingDeclaration = isUnderlyingPropertyOfInlineClass.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "this.containingDeclaration");
        if (!isInlineClass(containingDeclaration)) {
            return false;
        }
        if (containingDeclaration == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        }
        ValueParameterDescriptor valueParameterDescriptorUnderlyingRepresentation = underlyingRepresentation((ClassDescriptor) containingDeclaration);
        return Intrinsics.areEqual(valueParameterDescriptorUnderlyingRepresentation != null ? valueParameterDescriptorUnderlyingRepresentation.getName() : null, isUnderlyingPropertyOfInlineClass.getName());
    }
}
