package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

/* JADX INFO: compiled from: JavaDescriptorUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class JavaDescriptorUtilKt {
    public static final boolean isJavaField(PropertyDescriptor isJavaField) {
        Intrinsics.checkNotNullParameter(isJavaField, "$this$isJavaField");
        return isJavaField.getGetter() == null;
    }
}
