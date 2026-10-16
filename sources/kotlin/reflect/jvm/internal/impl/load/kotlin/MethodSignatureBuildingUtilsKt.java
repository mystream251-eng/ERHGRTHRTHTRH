package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;

/* JADX INFO: compiled from: methodSignatureBuildingUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class MethodSignatureBuildingUtilsKt {
    public static final String signature(SignatureBuildingComponents signature, ClassDescriptor classDescriptor, String jvmDescriptor) {
        Intrinsics.checkNotNullParameter(signature, "$this$signature");
        Intrinsics.checkNotNullParameter(classDescriptor, "classDescriptor");
        Intrinsics.checkNotNullParameter(jvmDescriptor, "jvmDescriptor");
        return signature.signature(MethodSignatureMappingKt.getInternalName(classDescriptor), jvmDescriptor);
    }
}
