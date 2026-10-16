package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;

/* JADX INFO: compiled from: ReflectJavaArrayType.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class ReflectJavaArrayType extends ReflectJavaType implements JavaArrayType {
    private final ReflectJavaType componentType;
    private final Type reflectType;

    public ReflectJavaArrayType(Type reflectType) {
        ReflectJavaType reflectJavaTypeCreate;
        Intrinsics.checkNotNullParameter(reflectType, "reflectType");
        this.reflectType = reflectType;
        Type reflectType2 = getReflectType();
        if (!(reflectType2 instanceof GenericArrayType)) {
            if (reflectType2 instanceof Class) {
                Class cls = (Class) reflectType2;
                if (cls.isArray()) {
                    ReflectJavaType.Factory factory = ReflectJavaType.Factory;
                    Class<?> componentType = cls.getComponentType();
                    Intrinsics.checkNotNullExpressionValue(componentType, "getComponentType()");
                    reflectJavaTypeCreate = factory.create(componentType);
                }
            }
            throw new IllegalArgumentException("Not an array type (" + getReflectType().getClass() + "): " + getReflectType());
        }
        ReflectJavaType.Factory factory2 = ReflectJavaType.Factory;
        Type genericComponentType = ((GenericArrayType) reflectType2).getGenericComponentType();
        Intrinsics.checkNotNullExpressionValue(genericComponentType, "genericComponentType");
        reflectJavaTypeCreate = factory2.create(genericComponentType);
        this.componentType = reflectJavaTypeCreate;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType
    protected Type getReflectType() {
        return this.reflectType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType
    public ReflectJavaType getComponentType() {
        return this.componentType;
    }
}
