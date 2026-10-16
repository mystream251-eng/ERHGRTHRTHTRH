package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.List;
import kotlin.TuplesKt;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.BuiltInAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: functionTypes.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class FunctionTypesKt {
    public static final boolean isFunctionType(KotlinType isFunctionType) {
        Intrinsics.checkNotNullParameter(isFunctionType, "$this$isFunctionType");
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = isFunctionType.getConstructor().mo1268getDeclarationDescriptor();
        return (classifierDescriptorMo1268getDeclarationDescriptor != null ? getFunctionalClassKind(classifierDescriptorMo1268getDeclarationDescriptor) : null) == FunctionClassKind.Function;
    }

    public static final boolean isSuspendFunctionType(KotlinType isSuspendFunctionType) {
        Intrinsics.checkNotNullParameter(isSuspendFunctionType, "$this$isSuspendFunctionType");
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = isSuspendFunctionType.getConstructor().mo1268getDeclarationDescriptor();
        return (classifierDescriptorMo1268getDeclarationDescriptor != null ? getFunctionalClassKind(classifierDescriptorMo1268getDeclarationDescriptor) : null) == FunctionClassKind.SuspendFunction;
    }

    public static final boolean isBuiltinFunctionalType(KotlinType isBuiltinFunctionalType) {
        Intrinsics.checkNotNullParameter(isBuiltinFunctionalType, "$this$isBuiltinFunctionalType");
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = isBuiltinFunctionalType.getConstructor().mo1268getDeclarationDescriptor();
        return classifierDescriptorMo1268getDeclarationDescriptor != null && isBuiltinFunctionalClassDescriptor(classifierDescriptorMo1268getDeclarationDescriptor);
    }

    public static final boolean isBuiltinFunctionalClassDescriptor(DeclarationDescriptor isBuiltinFunctionalClassDescriptor) {
        Intrinsics.checkNotNullParameter(isBuiltinFunctionalClassDescriptor, "$this$isBuiltinFunctionalClassDescriptor");
        FunctionClassKind functionalClassKind = getFunctionalClassKind(isBuiltinFunctionalClassDescriptor);
        return functionalClassKind == FunctionClassKind.Function || functionalClassKind == FunctionClassKind.SuspendFunction;
    }

    public static final boolean isBuiltinExtensionFunctionalType(KotlinType isBuiltinExtensionFunctionalType) {
        Intrinsics.checkNotNullParameter(isBuiltinExtensionFunctionalType, "$this$isBuiltinExtensionFunctionalType");
        return isBuiltinFunctionalType(isBuiltinExtensionFunctionalType) && isTypeAnnotatedWithExtensionFunctionType(isBuiltinExtensionFunctionalType);
    }

    private static final boolean isTypeAnnotatedWithExtensionFunctionType(KotlinType kotlinType) {
        return kotlinType.getAnnotations().mo1263findAnnotation(StandardNames.FqNames.extensionFunctionType) != null;
    }

    public static final FunctionClassKind getFunctionalClassKind(DeclarationDescriptor getFunctionalClassKind) {
        Intrinsics.checkNotNullParameter(getFunctionalClassKind, "$this$getFunctionalClassKind");
        if ((getFunctionalClassKind instanceof ClassDescriptor) && KotlinBuiltIns.isUnderKotlinPackage(getFunctionalClassKind)) {
            return getFunctionalClassKind(DescriptorUtilsKt.getFqNameUnsafe(getFunctionalClassKind));
        }
        return null;
    }

    private static final FunctionClassKind getFunctionalClassKind(FqNameUnsafe fqNameUnsafe) {
        if (!fqNameUnsafe.isSafe() || fqNameUnsafe.isRoot()) {
            return null;
        }
        FunctionClassKind.Companion companion = FunctionClassKind.Companion;
        String strAsString = fqNameUnsafe.shortName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "shortName().asString()");
        FqName fqNameParent = fqNameUnsafe.toSafe().parent();
        Intrinsics.checkNotNullExpressionValue(fqNameParent, "toSafe().parent()");
        return companion.getFunctionalClassKind(strAsString, fqNameParent);
    }

    public static final KotlinType getReceiverTypeFromFunctionType(KotlinType getReceiverTypeFromFunctionType) {
        Intrinsics.checkNotNullParameter(getReceiverTypeFromFunctionType, "$this$getReceiverTypeFromFunctionType");
        boolean zIsBuiltinFunctionalType = isBuiltinFunctionalType(getReceiverTypeFromFunctionType);
        if (!_Assertions.ENABLED || zIsBuiltinFunctionalType) {
            if (isTypeAnnotatedWithExtensionFunctionType(getReceiverTypeFromFunctionType)) {
                return ((TypeProjection) CollectionsKt.first((List) getReceiverTypeFromFunctionType.getArguments())).getType();
            }
            return null;
        }
        throw new AssertionError("Not a function type: " + getReceiverTypeFromFunctionType);
    }

    public static final KotlinType getReturnTypeFromFunctionType(KotlinType getReturnTypeFromFunctionType) {
        Intrinsics.checkNotNullParameter(getReturnTypeFromFunctionType, "$this$getReturnTypeFromFunctionType");
        boolean zIsBuiltinFunctionalType = isBuiltinFunctionalType(getReturnTypeFromFunctionType);
        if (!_Assertions.ENABLED || zIsBuiltinFunctionalType) {
            KotlinType type = ((TypeProjection) CollectionsKt.last((List) getReturnTypeFromFunctionType.getArguments())).getType();
            Intrinsics.checkNotNullExpressionValue(type, "arguments.last().type");
            return type;
        }
        throw new AssertionError("Not a function type: " + getReturnTypeFromFunctionType);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [boolean] */
    public static final List<TypeProjection> getValueParameterTypesFromFunctionType(KotlinType getValueParameterTypesFromFunctionType) {
        Intrinsics.checkNotNullParameter(getValueParameterTypesFromFunctionType, "$this$getValueParameterTypesFromFunctionType");
        boolean zIsBuiltinFunctionalType = isBuiltinFunctionalType(getValueParameterTypesFromFunctionType);
        if (_Assertions.ENABLED && !zIsBuiltinFunctionalType) {
            throw new AssertionError("Not a function type: " + getValueParameterTypesFromFunctionType);
        }
        List<TypeProjection> arguments = getValueParameterTypesFromFunctionType.getArguments();
        ?? IsBuiltinExtensionFunctionalType = isBuiltinExtensionFunctionalType(getValueParameterTypesFromFunctionType);
        int size = arguments.size() - 1;
        boolean z = IsBuiltinExtensionFunctionalType <= size;
        if (!_Assertions.ENABLED || z) {
            return arguments.subList(IsBuiltinExtensionFunctionalType == true ? 1 : 0, size);
        }
        throw new AssertionError("Not an exact function type: " + getValueParameterTypesFromFunctionType);
    }

    public static final Name extractParameterNameFromFunctionTypeArgument(KotlinType extractParameterNameFromFunctionTypeArgument) {
        String value;
        Intrinsics.checkNotNullParameter(extractParameterNameFromFunctionTypeArgument, "$this$extractParameterNameFromFunctionTypeArgument");
        AnnotationDescriptor annotationDescriptorMo1263findAnnotation = extractParameterNameFromFunctionTypeArgument.getAnnotations().mo1263findAnnotation(StandardNames.FqNames.parameterName);
        if (annotationDescriptorMo1263findAnnotation != null) {
            Object objSingleOrNull = CollectionsKt.singleOrNull(annotationDescriptorMo1263findAnnotation.getAllValueArguments().values());
            if (!(objSingleOrNull instanceof StringValue)) {
                objSingleOrNull = null;
            }
            StringValue stringValue = (StringValue) objSingleOrNull;
            if (stringValue != null && (value = stringValue.getValue()) != null) {
                if (!Name.isValidIdentifier(value)) {
                    value = null;
                }
                if (value != null) {
                    return Name.identifier(value);
                }
            }
        }
        return null;
    }

    public static final List<TypeProjection> getFunctionTypeArgumentProjections(KotlinType kotlinType, List<? extends KotlinType> parameterTypes, List<Name> list, KotlinType returnType, KotlinBuiltIns builtIns) {
        Name name;
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        int i = 0;
        ArrayList arrayList = new ArrayList(parameterTypes.size() + (kotlinType != null ? 1 : 0) + 1);
        ArrayList arrayList2 = arrayList;
        kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList2, kotlinType != null ? TypeUtilsKt.asTypeProjection(kotlinType) : null);
        for (Object obj : parameterTypes) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KotlinType kotlinTypeReplaceAnnotations = (KotlinType) obj;
            if (list == null || (name = list.get(i)) == null || name.isSpecial()) {
                name = null;
            }
            if (name != null) {
                FqName fqName = StandardNames.FqNames.parameterName;
                Name nameIdentifier = Name.identifier("name");
                String strAsString = name.asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
                kotlinTypeReplaceAnnotations = TypeUtilsKt.replaceAnnotations(kotlinTypeReplaceAnnotations, Annotations.Companion.create(CollectionsKt.plus(kotlinTypeReplaceAnnotations.getAnnotations(), new BuiltInAnnotationDescriptor(builtIns, fqName, MapsKt.mapOf(TuplesKt.to(nameIdentifier, new StringValue(strAsString)))))));
            }
            arrayList2.add(TypeUtilsKt.asTypeProjection(kotlinTypeReplaceAnnotations));
            i = i2;
        }
        arrayList.add(TypeUtilsKt.asTypeProjection(returnType));
        return arrayList;
    }

    public static /* synthetic */ SimpleType createFunctionType$default(KotlinBuiltIns kotlinBuiltIns, Annotations annotations, KotlinType kotlinType, List list, List list2, KotlinType kotlinType2, boolean z, int i, Object obj) {
        if ((i & 64) != 0) {
            z = false;
        }
        return createFunctionType(kotlinBuiltIns, annotations, kotlinType, list, list2, kotlinType2, z);
    }

    public static final SimpleType createFunctionType(KotlinBuiltIns builtIns, Annotations annotations, KotlinType kotlinType, List<? extends KotlinType> parameterTypes, List<Name> list, KotlinType returnType, boolean z) {
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        List<TypeProjection> functionTypeArgumentProjections = getFunctionTypeArgumentProjections(kotlinType, parameterTypes, list, returnType, builtIns);
        int size = parameterTypes.size();
        if (kotlinType != null) {
            size++;
        }
        ClassDescriptor functionDescriptor = getFunctionDescriptor(builtIns, size, z);
        if (kotlinType != null) {
            annotations = withExtensionFunctionAnnotation(annotations, builtIns);
        }
        return KotlinTypeFactory.simpleNotNullType(annotations, functionDescriptor, functionTypeArgumentProjections);
    }

    public static final Annotations withExtensionFunctionAnnotation(Annotations withExtensionFunctionAnnotation, KotlinBuiltIns builtIns) {
        Intrinsics.checkNotNullParameter(withExtensionFunctionAnnotation, "$this$withExtensionFunctionAnnotation");
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        return withExtensionFunctionAnnotation.hasAnnotation(StandardNames.FqNames.extensionFunctionType) ? withExtensionFunctionAnnotation : Annotations.Companion.create(CollectionsKt.plus(withExtensionFunctionAnnotation, new BuiltInAnnotationDescriptor(builtIns, StandardNames.FqNames.extensionFunctionType, MapsKt.emptyMap())));
    }

    public static final ClassDescriptor getFunctionDescriptor(KotlinBuiltIns builtIns, int i, boolean z) {
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        ClassDescriptor suspendFunction = z ? builtIns.getSuspendFunction(i) : builtIns.getFunction(i);
        Intrinsics.checkNotNullExpressionValue(suspendFunction, "if (isSuspendFunction) b…tFunction(parameterCount)");
        return suspendFunction;
    }
}
