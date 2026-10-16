package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ModalityUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class ModalityUtilsKt {
    public static final boolean isFinalClass(ClassDescriptor isFinalClass) {
        Intrinsics.checkNotNullParameter(isFinalClass, "$this$isFinalClass");
        return isFinalClass.getModality() == Modality.FINAL && isFinalClass.getKind() != ClassKind.ENUM_CLASS;
    }
}
