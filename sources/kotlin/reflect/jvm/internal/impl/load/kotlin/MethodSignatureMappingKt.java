package kotlin.reflect.jvm.internal.impl.load.kotlin;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: compiled from: methodSignatureMapping.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class MethodSignatureMappingKt {
    public static final String computeJvmDescriptor(FunctionDescriptor computeJvmDescriptor, boolean z, boolean z2) {
        String strAsString;
        Intrinsics.checkNotNullParameter(computeJvmDescriptor, "$this$computeJvmDescriptor");
        StringBuilder sb = new StringBuilder();
        if (z2) {
            if (computeJvmDescriptor instanceof ConstructorDescriptor) {
                strAsString = "<init>";
            } else {
                strAsString = computeJvmDescriptor.getName().asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
            }
            sb.append(strAsString);
        }
        sb.append("(");
        ReceiverParameterDescriptor it = computeJvmDescriptor.getExtensionReceiverParameter();
        if (it != null) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            KotlinType type = it.getType();
            Intrinsics.checkNotNullExpressionValue(type, "it.type");
            appendErasedType(sb, type);
        }
        for (ValueParameterDescriptor parameter : computeJvmDescriptor.getValueParameters()) {
            Intrinsics.checkNotNullExpressionValue(parameter, "parameter");
            KotlinType type2 = parameter.getType();
            Intrinsics.checkNotNullExpressionValue(type2, "parameter.type");
            appendErasedType(sb, type2);
        }
        sb.append(")");
        if (z) {
            if (TypeSignatureMappingKt.hasVoidReturnType(computeJvmDescriptor)) {
                sb.append(ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
            } else {
                KotlinType returnType = computeJvmDescriptor.getReturnType();
                Intrinsics.checkNotNull(returnType);
                Intrinsics.checkNotNullExpressionValue(returnType, "returnType!!");
                appendErasedType(sb, returnType);
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static /* synthetic */ String computeJvmDescriptor$default(FunctionDescriptor functionDescriptor, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return computeJvmDescriptor(functionDescriptor, z, z2);
    }

    public static final boolean forceSingleValueParameterBoxing(CallableDescriptor f) {
        FunctionDescriptor overriddenBuiltinFunctionWithErasedValueParametersInJava;
        Intrinsics.checkNotNullParameter(f, "f");
        if (!(f instanceof FunctionDescriptor)) {
            return false;
        }
        FunctionDescriptor functionDescriptor = (FunctionDescriptor) f;
        if ((!Intrinsics.areEqual(functionDescriptor.getName().asString(), "remove")) || functionDescriptor.getValueParameters().size() != 1 || SpecialBuiltinMembers.isFromJavaOrBuiltins((CallableMemberDescriptor) f)) {
            return false;
        }
        FunctionDescriptor original = functionDescriptor.getOriginal();
        Intrinsics.checkNotNullExpressionValue(original, "f.original");
        List<ValueParameterDescriptor> valueParameters = original.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "f.original.valueParameters");
        Object objSingle = CollectionsKt.single((List<? extends Object>) valueParameters);
        Intrinsics.checkNotNullExpressionValue(objSingle, "f.original.valueParameters.single()");
        KotlinType type = ((ValueParameterDescriptor) objSingle).getType();
        Intrinsics.checkNotNullExpressionValue(type, "f.original.valueParameters.single().type");
        JvmType jvmTypeMapToJvmType = mapToJvmType(type);
        if (!(jvmTypeMapToJvmType instanceof JvmType.Primitive)) {
            jvmTypeMapToJvmType = null;
        }
        JvmType.Primitive primitive = (JvmType.Primitive) jvmTypeMapToJvmType;
        if ((primitive != null ? primitive.getJvmPrimitiveType() : null) != JvmPrimitiveType.INT || (overriddenBuiltinFunctionWithErasedValueParametersInJava = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(functionDescriptor)) == null) {
            return false;
        }
        FunctionDescriptor original2 = overriddenBuiltinFunctionWithErasedValueParametersInJava.getOriginal();
        Intrinsics.checkNotNullExpressionValue(original2, "overridden.original");
        List<ValueParameterDescriptor> valueParameters2 = original2.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters2, "overridden.original.valueParameters");
        Object objSingle2 = CollectionsKt.single((List<? extends Object>) valueParameters2);
        Intrinsics.checkNotNullExpressionValue(objSingle2, "overridden.original.valueParameters.single()");
        KotlinType type2 = ((ValueParameterDescriptor) objSingle2).getType();
        Intrinsics.checkNotNullExpressionValue(type2, "overridden.original.valueParameters.single().type");
        JvmType jvmTypeMapToJvmType2 = mapToJvmType(type2);
        DeclarationDescriptor containingDeclaration = overriddenBuiltinFunctionWithErasedValueParametersInJava.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "overridden.containingDeclaration");
        return Intrinsics.areEqual(DescriptorUtilsKt.getFqNameUnsafe(containingDeclaration), StandardNames.FqNames.mutableCollection.toUnsafe()) && (jvmTypeMapToJvmType2 instanceof JvmType.Object) && Intrinsics.areEqual(((JvmType.Object) jvmTypeMapToJvmType2).getInternalName(), "java/lang/Object");
    }

    public static final String getInternalName(ClassDescriptor internalName) {
        Intrinsics.checkNotNullParameter(internalName, "$this$internalName");
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        FqNameUnsafe unsafe = DescriptorUtilsKt.getFqNameSafe(internalName).toUnsafe();
        Intrinsics.checkNotNullExpressionValue(unsafe, "fqNameSafe.toUnsafe()");
        ClassId classIdMapKotlinToJava = javaToKotlinClassMap.mapKotlinToJava(unsafe);
        if (classIdMapKotlinToJava != null) {
            JvmClassName jvmClassNameByClassId = JvmClassName.byClassId(classIdMapKotlinToJava);
            Intrinsics.checkNotNullExpressionValue(jvmClassNameByClassId, "JvmClassName.byClassId(it)");
            String internalName2 = jvmClassNameByClassId.getInternalName();
            Intrinsics.checkNotNullExpressionValue(internalName2, "JvmClassName.byClassId(it).internalName");
            return internalName2;
        }
        return TypeSignatureMappingKt.computeInternalName$default(internalName, null, 2, null);
    }

    private static final void appendErasedType(StringBuilder sb, KotlinType kotlinType) {
        sb.append(mapToJvmType(kotlinType));
    }

    public static final JvmType mapToJvmType(KotlinType mapToJvmType) {
        Intrinsics.checkNotNullParameter(mapToJvmType, "$this$mapToJvmType");
        return (JvmType) TypeSignatureMappingKt.mapType$default(mapToJvmType, JvmTypeFactoryImpl.INSTANCE, TypeMappingMode.DEFAULT, TypeMappingConfigurationImpl.INSTANCE, null, null, 32, null);
    }

    public static final String computeJvmSignature(CallableDescriptor computeJvmSignature) {
        Intrinsics.checkNotNullParameter(computeJvmSignature, "$this$computeJvmSignature");
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        if (DescriptorUtils.isLocal(computeJvmSignature)) {
            return null;
        }
        DeclarationDescriptor containingDeclaration = computeJvmSignature.getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            containingDeclaration = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        if (classDescriptor != null) {
            Name name = classDescriptor.getName();
            Intrinsics.checkNotNullExpressionValue(name, "classDescriptor.name");
            if (name.isSpecial()) {
                return null;
            }
            CallableDescriptor original = computeJvmSignature.getOriginal();
            if (!(original instanceof SimpleFunctionDescriptor)) {
                original = null;
            }
            SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) original;
            if (simpleFunctionDescriptor != null) {
                return MethodSignatureBuildingUtilsKt.signature(signatureBuildingComponents, classDescriptor, computeJvmDescriptor$default(simpleFunctionDescriptor, false, false, 3, null));
            }
        }
        return null;
    }
}
