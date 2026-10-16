package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

/* JADX INFO: compiled from: specialBuiltinMembers.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class BuiltinMethodsWithDifferentJvmName {
    public static final BuiltinMethodsWithDifferentJvmName INSTANCE = new BuiltinMethodsWithDifferentJvmName();
    private static final Map<Name, List<Name>> JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP;
    private static final Map<NameAndSignature, Name> NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP;
    private static final List<Name> ORIGINAL_SHORT_NAMES;
    private static final NameAndSignature REMOVE_AT_NAME_AND_SIGNATURE;
    private static final Map<String, Name> SIGNATURE_TO_JVM_REPRESENTATION_NAME;

    static {
        String desc = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc, "JvmPrimitiveType.INT.desc");
        REMOVE_AT_NAME_AND_SIGNATURE = SpecialBuiltinMembers.method("java/util/List", "removeAt", desc, "Ljava/lang/Object;");
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        String strJavaLang = signatureBuildingComponents.javaLang("Number");
        String desc2 = JvmPrimitiveType.BYTE.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc2, "JvmPrimitiveType.BYTE.desc");
        String strJavaLang2 = signatureBuildingComponents.javaLang("Number");
        String desc3 = JvmPrimitiveType.SHORT.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc3, "JvmPrimitiveType.SHORT.desc");
        String strJavaLang3 = signatureBuildingComponents.javaLang("Number");
        String desc4 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc4, "JvmPrimitiveType.INT.desc");
        String strJavaLang4 = signatureBuildingComponents.javaLang("Number");
        String desc5 = JvmPrimitiveType.LONG.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc5, "JvmPrimitiveType.LONG.desc");
        String strJavaLang5 = signatureBuildingComponents.javaLang("Number");
        String desc6 = JvmPrimitiveType.FLOAT.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc6, "JvmPrimitiveType.FLOAT.desc");
        String strJavaLang6 = signatureBuildingComponents.javaLang("Number");
        String desc7 = JvmPrimitiveType.DOUBLE.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc7, "JvmPrimitiveType.DOUBLE.desc");
        String strJavaLang7 = signatureBuildingComponents.javaLang("CharSequence");
        String desc8 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc8, "JvmPrimitiveType.INT.desc");
        String desc9 = JvmPrimitiveType.CHAR.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc9, "JvmPrimitiveType.CHAR.desc");
        Map<NameAndSignature, Name> mapMapOf = MapsKt.mapOf(TuplesKt.to(SpecialBuiltinMembers.method(strJavaLang, "toByte", "", desc2), Name.identifier("byteValue")), TuplesKt.to(SpecialBuiltinMembers.method(strJavaLang2, "toShort", "", desc3), Name.identifier("shortValue")), TuplesKt.to(SpecialBuiltinMembers.method(strJavaLang3, "toInt", "", desc4), Name.identifier("intValue")), TuplesKt.to(SpecialBuiltinMembers.method(strJavaLang4, "toLong", "", desc5), Name.identifier("longValue")), TuplesKt.to(SpecialBuiltinMembers.method(strJavaLang5, "toFloat", "", desc6), Name.identifier("floatValue")), TuplesKt.to(SpecialBuiltinMembers.method(strJavaLang6, "toDouble", "", desc7), Name.identifier("doubleValue")), TuplesKt.to(REMOVE_AT_NAME_AND_SIGNATURE, Name.identifier("remove")), TuplesKt.to(SpecialBuiltinMembers.method(strJavaLang7, "get", desc8, desc9), Name.identifier("charAt")));
        NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP = mapMapOf;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(mapMapOf.size()));
        Iterator<T> it = mapMapOf.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(((NameAndSignature) entry.getKey()).getSignature(), entry.getValue());
        }
        SIGNATURE_TO_JVM_REPRESENTATION_NAME = linkedHashMap;
        Set<NameAndSignature> setKeySet = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.keySet();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setKeySet, 10));
        Iterator<T> it2 = setKeySet.iterator();
        while (it2.hasNext()) {
            arrayList.add(((NameAndSignature) it2.next()).getName());
        }
        ORIGINAL_SHORT_NAMES = arrayList;
        Set<Map.Entry<NameAndSignature, Name>> setEntrySet = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.entrySet();
        ArrayList<Pair> arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(setEntrySet, 10));
        Iterator<T> it3 = setEntrySet.iterator();
        while (it3.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it3.next();
            arrayList2.add(new Pair(((NameAndSignature) entry2.getKey()).getName(), entry2.getValue()));
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Pair pair : arrayList2) {
            Name name = (Name) pair.getSecond();
            Object obj = linkedHashMap2.get(name);
            if (obj == null) {
                obj = (List) new ArrayList();
                linkedHashMap2.put(name, obj);
            }
            ((List) obj).add((Name) pair.getFirst());
        }
        JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP = linkedHashMap2;
    }

    private BuiltinMethodsWithDifferentJvmName() {
    }

    public final Map<String, Name> getSIGNATURE_TO_JVM_REPRESENTATION_NAME() {
        return SIGNATURE_TO_JVM_REPRESENTATION_NAME;
    }

    public final List<Name> getORIGINAL_SHORT_NAMES() {
        return ORIGINAL_SHORT_NAMES;
    }

    public final boolean getSameAsRenamedInJvmBuiltin(Name sameAsRenamedInJvmBuiltin) {
        Intrinsics.checkNotNullParameter(sameAsRenamedInJvmBuiltin, "$this$sameAsRenamedInJvmBuiltin");
        return ORIGINAL_SHORT_NAMES.contains(sameAsRenamedInJvmBuiltin);
    }

    public final Name getJvmName(SimpleFunctionDescriptor functionDescriptor) {
        Intrinsics.checkNotNullParameter(functionDescriptor, "functionDescriptor");
        Map<String, Name> map = SIGNATURE_TO_JVM_REPRESENTATION_NAME;
        String strComputeJvmSignature = MethodSignatureMappingKt.computeJvmSignature(functionDescriptor);
        if (strComputeJvmSignature != null) {
            return map.get(strComputeJvmSignature);
        }
        return null;
    }

    public final boolean isBuiltinFunctionWithDifferentNameInJvm(final SimpleFunctionDescriptor functionDescriptor) {
        Intrinsics.checkNotNullParameter(functionDescriptor, "functionDescriptor");
        return KotlinBuiltIns.isBuiltIn(functionDescriptor) && DescriptorUtilsKt.firstOverridden$default(functionDescriptor, false, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithDifferentJvmName.isBuiltinFunctionWithDifferentNameInJvm.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return Boolean.valueOf(invoke2(callableMemberDescriptor));
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(CallableMemberDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Map<String, Name> signature_to_jvm_representation_name = BuiltinMethodsWithDifferentJvmName.INSTANCE.getSIGNATURE_TO_JVM_REPRESENTATION_NAME();
                String strComputeJvmSignature = MethodSignatureMappingKt.computeJvmSignature(functionDescriptor);
                if (signature_to_jvm_representation_name != null) {
                    return signature_to_jvm_representation_name.containsKey(strComputeJvmSignature);
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
            }
        }, 1, null) != null;
    }

    public final List<Name> getBuiltinFunctionNamesByJvmName(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List<Name> list = JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP.get(name);
        return list != null ? list : CollectionsKt.emptyList();
    }

    public final boolean isRemoveAtByIndex(SimpleFunctionDescriptor isRemoveAtByIndex) {
        Intrinsics.checkNotNullParameter(isRemoveAtByIndex, "$this$isRemoveAtByIndex");
        return Intrinsics.areEqual(isRemoveAtByIndex.getName().asString(), "removeAt") && Intrinsics.areEqual(MethodSignatureMappingKt.computeJvmSignature(isRemoveAtByIndex), REMOVE_AT_NAME_AND_SIGNATURE.getSignature());
    }
}
