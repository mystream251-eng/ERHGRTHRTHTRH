package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;

/* JADX INFO: compiled from: ReflectJavaWildcardType.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class ReflectJavaWildcardType extends ReflectJavaType implements JavaWildcardType {
    private final WildcardType reflectType;

    public ReflectJavaWildcardType(WildcardType reflectType) {
        Intrinsics.checkNotNullParameter(reflectType, "reflectType");
        this.reflectType = reflectType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType
    public WildcardType getReflectType() {
        return this.reflectType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType
    public ReflectJavaType getBound() {
        Type[] upperBounds = getReflectType().getUpperBounds();
        Type[] lowerBounds = getReflectType().getLowerBounds();
        if (upperBounds.length > 1 || lowerBounds.length > 1) {
            throw new UnsupportedOperationException("Wildcard types with many bounds are not yet supported: " + getReflectType());
        }
        if (lowerBounds.length == 1) {
            ReflectJavaType.Factory factory = ReflectJavaType.Factory;
            Intrinsics.checkNotNullExpressionValue(lowerBounds, "lowerBounds");
            Object objSingle = ArraysKt.single(lowerBounds);
            Intrinsics.checkNotNullExpressionValue(objSingle, "lowerBounds.single()");
            return factory.create((Type) objSingle);
        }
        if (upperBounds.length != 1) {
            return null;
        }
        Intrinsics.checkNotNullExpressionValue(upperBounds, "upperBounds");
        Type ub = (Type) ArraysKt.single(upperBounds);
        if (!(!Intrinsics.areEqual(ub, Object.class))) {
            return null;
        }
        ReflectJavaType.Factory factory2 = ReflectJavaType.Factory;
        Intrinsics.checkNotNullExpressionValue(ub, "ub");
        return factory2.create(ub);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType
    public boolean isExtends() {
        Type[] upperBounds = getReflectType().getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "reflectType.upperBounds");
        return !Intrinsics.areEqual((Type) ArraysKt.firstOrNull(upperBounds), Object.class);
    }
}
