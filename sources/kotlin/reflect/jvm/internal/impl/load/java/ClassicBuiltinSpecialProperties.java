package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Collection;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

/* JADX INFO: compiled from: ClassicBuiltinSpecialProperties.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class ClassicBuiltinSpecialProperties {
    public static final ClassicBuiltinSpecialProperties INSTANCE = new ClassicBuiltinSpecialProperties();

    private ClassicBuiltinSpecialProperties() {
    }

    public final String getBuiltinSpecialPropertyGetterName(CallableMemberDescriptor getBuiltinSpecialPropertyGetterName) {
        Name name;
        Intrinsics.checkNotNullParameter(getBuiltinSpecialPropertyGetterName, "$this$getBuiltinSpecialPropertyGetterName");
        boolean zIsBuiltIn = KotlinBuiltIns.isBuiltIn(getBuiltinSpecialPropertyGetterName);
        if (!_Assertions.ENABLED || zIsBuiltIn) {
            CallableMemberDescriptor callableMemberDescriptorFirstOverridden$default = DescriptorUtilsKt.firstOverridden$default(DescriptorUtilsKt.getPropertyIfAccessor(getBuiltinSpecialPropertyGetterName), false, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.ClassicBuiltinSpecialProperties$getBuiltinSpecialPropertyGetterName$descriptor$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                    return Boolean.valueOf(invoke2(callableMemberDescriptor));
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(CallableMemberDescriptor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ClassicBuiltinSpecialProperties.INSTANCE.hasBuiltinSpecialPropertyFqName(it);
                }
            }, 1, null);
            if (callableMemberDescriptorFirstOverridden$default == null || (name = BuiltinSpecialProperties.INSTANCE.getPROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP().get(DescriptorUtilsKt.getFqNameSafe(callableMemberDescriptorFirstOverridden$default))) == null) {
                return null;
            }
            return name.asString();
        }
        throw new AssertionError("This method is defined only for builtin members, but " + getBuiltinSpecialPropertyGetterName + " found");
    }

    public final boolean hasBuiltinSpecialPropertyFqName(CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "callableMemberDescriptor");
        if (BuiltinSpecialProperties.INSTANCE.getSPECIAL_SHORT_NAMES().contains(callableMemberDescriptor.getName())) {
            return hasBuiltinSpecialPropertyFqNameImpl(callableMemberDescriptor);
        }
        return false;
    }

    private final boolean hasBuiltinSpecialPropertyFqNameImpl(CallableMemberDescriptor callableMemberDescriptor) {
        CallableMemberDescriptor callableMemberDescriptor2 = callableMemberDescriptor;
        if (CollectionsKt.contains(BuiltinSpecialProperties.INSTANCE.getSPECIAL_FQ_NAMES(), DescriptorUtilsKt.fqNameOrNull(callableMemberDescriptor2)) && callableMemberDescriptor.getValueParameters().isEmpty()) {
            return true;
        }
        if (!KotlinBuiltIns.isBuiltIn(callableMemberDescriptor2)) {
            return false;
        }
        Collection<? extends CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        Intrinsics.checkNotNullExpressionValue(overriddenDescriptors, "overriddenDescriptors");
        Collection<? extends CallableMemberDescriptor> collection = overriddenDescriptors;
        if (!(collection instanceof Collection) || !collection.isEmpty()) {
            for (CallableMemberDescriptor it : collection) {
                ClassicBuiltinSpecialProperties classicBuiltinSpecialProperties = INSTANCE;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                if (classicBuiltinSpecialProperties.hasBuiltinSpecialPropertyFqName(it)) {
                    return true;
                }
            }
        }
        return false;
    }
}
