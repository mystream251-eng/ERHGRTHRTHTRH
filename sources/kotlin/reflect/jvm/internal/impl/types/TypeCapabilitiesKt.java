package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TypeCapabilities.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class TypeCapabilitiesKt {
    public static final boolean isCustomTypeVariable(KotlinType isCustomTypeVariable) {
        Intrinsics.checkNotNullParameter(isCustomTypeVariable, "$this$isCustomTypeVariable");
        Object objUnwrap = isCustomTypeVariable.unwrap();
        if (!(objUnwrap instanceof CustomTypeVariable)) {
            objUnwrap = null;
        }
        CustomTypeVariable customTypeVariable = (CustomTypeVariable) objUnwrap;
        if (customTypeVariable != null) {
            return customTypeVariable.isTypeVariable();
        }
        return false;
    }

    public static final CustomTypeVariable getCustomTypeVariable(KotlinType getCustomTypeVariable) {
        Intrinsics.checkNotNullParameter(getCustomTypeVariable, "$this$getCustomTypeVariable");
        Object objUnwrap = getCustomTypeVariable.unwrap();
        if (!(objUnwrap instanceof CustomTypeVariable)) {
            objUnwrap = null;
        }
        CustomTypeVariable customTypeVariable = (CustomTypeVariable) objUnwrap;
        if (customTypeVariable == null || !customTypeVariable.isTypeVariable()) {
            return null;
        }
        return customTypeVariable;
    }
}
