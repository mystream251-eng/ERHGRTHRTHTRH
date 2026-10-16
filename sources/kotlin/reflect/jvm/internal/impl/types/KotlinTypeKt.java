package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinType.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class KotlinTypeKt {
    public static final boolean isNullable(KotlinType isNullable) {
        Intrinsics.checkNotNullParameter(isNullable, "$this$isNullable");
        return TypeUtils.isNullableType(isNullable);
    }

    public static final boolean isError(KotlinType isError) {
        Intrinsics.checkNotNullParameter(isError, "$this$isError");
        UnwrappedType unwrappedTypeUnwrap = isError.unwrap();
        return (unwrappedTypeUnwrap instanceof ErrorType) || ((unwrappedTypeUnwrap instanceof FlexibleType) && (((FlexibleType) unwrappedTypeUnwrap).getDelegate() instanceof ErrorType));
    }
}
