package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: flexibleTypes.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class FlexibleTypesKt {
    public static final boolean isFlexible(KotlinType isFlexible) {
        Intrinsics.checkNotNullParameter(isFlexible, "$this$isFlexible");
        return isFlexible.unwrap() instanceof FlexibleType;
    }

    public static final FlexibleType asFlexibleType(KotlinType asFlexibleType) {
        Intrinsics.checkNotNullParameter(asFlexibleType, "$this$asFlexibleType");
        UnwrappedType unwrappedTypeUnwrap = asFlexibleType.unwrap();
        if (unwrappedTypeUnwrap != null) {
            return (FlexibleType) unwrappedTypeUnwrap;
        }
        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.types.FlexibleType");
    }

    public static final SimpleType lowerIfFlexible(KotlinType lowerIfFlexible) {
        Intrinsics.checkNotNullParameter(lowerIfFlexible, "$this$lowerIfFlexible");
        UnwrappedType unwrappedTypeUnwrap = lowerIfFlexible.unwrap();
        if (unwrappedTypeUnwrap instanceof FlexibleType) {
            return ((FlexibleType) unwrappedTypeUnwrap).getLowerBound();
        }
        if (unwrappedTypeUnwrap instanceof SimpleType) {
            return (SimpleType) unwrappedTypeUnwrap;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final SimpleType upperIfFlexible(KotlinType upperIfFlexible) {
        Intrinsics.checkNotNullParameter(upperIfFlexible, "$this$upperIfFlexible");
        UnwrappedType unwrappedTypeUnwrap = upperIfFlexible.unwrap();
        if (unwrappedTypeUnwrap instanceof FlexibleType) {
            return ((FlexibleType) unwrappedTypeUnwrap).getUpperBound();
        }
        if (unwrappedTypeUnwrap instanceof SimpleType) {
            return (SimpleType) unwrappedTypeUnwrap;
        }
        throw new NoWhenBranchMatchedException();
    }
}
