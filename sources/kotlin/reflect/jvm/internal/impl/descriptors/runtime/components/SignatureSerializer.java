package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;

/* JADX INFO: compiled from: ReflectKotlinClass.kt */
/* JADX INFO: loaded from: classes3.dex */
final class SignatureSerializer {
    public static final SignatureSerializer INSTANCE = new SignatureSerializer();

    private SignatureSerializer() {
    }

    public final String methodDesc(Method method) {
        Intrinsics.checkNotNullParameter(method, "method");
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Class<?> cls : method.getParameterTypes()) {
            sb.append(ReflectClassUtilKt.getDesc(cls));
        }
        sb.append(")");
        sb.append(ReflectClassUtilKt.getDesc(method.getReturnType()));
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }

    public final String constructorDesc(Constructor<?> constructor) {
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Class<?> cls : constructor.getParameterTypes()) {
            sb.append(ReflectClassUtilKt.getDesc(cls));
        }
        sb.append(")V");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }

    public final String fieldDesc(Field field) {
        Intrinsics.checkNotNullParameter(field, "field");
        return ReflectClassUtilKt.getDesc(field.getType());
    }
}
