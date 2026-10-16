package com.daveanthonythomas.moshipack;

import androidx.exifinterface.media.ExifInterface;
import com.squareup.moshi.Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import sun.reflect.generics.reflectiveObjects.WildcardTypeImpl;

/* JADX INFO: compiled from: TypeReference.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/daveanthonythomas/moshipack/TypeReference;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "type", "Ljava/lang/reflect/Type;", "getType", "()Ljava/lang/reflect/Type;", "forceBounds", "moshipack"}, k = 1, mv = {1, 1, 13})
public abstract class TypeReference<T> {
    private final Type type;

    public TypeReference() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        if (genericSuperclass == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.reflect.ParameterizedType");
        }
        Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        Intrinsics.checkExpressionValueIsNotNull(type, "args[0]");
        this.type = forceBounds(type);
    }

    public final Type getType() {
        return this.type;
    }

    private final Type forceBounds(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return type;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Intrinsics.checkExpressionValueIsNotNull(actualTypeArguments, "type.actualTypeArguments");
        ArrayList arrayList = new ArrayList(actualTypeArguments.length);
        for (Type type2 : actualTypeArguments) {
            if (type2 instanceof WildcardTypeImpl) {
                WildcardTypeImpl wildcardTypeImpl = (WildcardTypeImpl) type2;
                Type[] upperBounds = wildcardTypeImpl.getUpperBounds();
                if ((upperBounds != null ? Intrinsics.compare(upperBounds.length, 0) : 0) > 0) {
                    type2 = wildcardTypeImpl.getUpperBounds()[0];
                }
            }
            Intrinsics.checkExpressionValueIsNotNull(type2, "if (it is WildcardTypeIm… it\n            } else it");
            arrayList.add(forceBounds(type2));
        }
        Type rawType = parameterizedType.getRawType();
        Object[] array = arrayList.toArray(new Type[0]);
        if (array != null) {
            Type[] typeArr = (Type[]) array;
            ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(rawType, (Type[]) Arrays.copyOf(typeArr, typeArr.length));
            Intrinsics.checkExpressionValueIsNotNull(parameterizedTypeNewParameterizedType, "Types.newParameterizedTy… *bounded.toTypedArray())");
            return parameterizedTypeNewParameterizedType;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
