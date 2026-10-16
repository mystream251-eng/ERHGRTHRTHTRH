package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TypeWithEnhancement.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class TypeWithEnhancementKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final KotlinType getEnhancement(KotlinType getEnhancement) {
        Intrinsics.checkNotNullParameter(getEnhancement, "$this$getEnhancement");
        if (getEnhancement instanceof TypeWithEnhancement) {
            return ((TypeWithEnhancement) getEnhancement).getEnhancement();
        }
        return null;
    }

    public static final KotlinType unwrapEnhancement(KotlinType unwrapEnhancement) {
        Intrinsics.checkNotNullParameter(unwrapEnhancement, "$this$unwrapEnhancement");
        KotlinType enhancement = getEnhancement(unwrapEnhancement);
        return enhancement != null ? enhancement : unwrapEnhancement;
    }

    public static final UnwrappedType inheritEnhancement(UnwrappedType inheritEnhancement, KotlinType origin) {
        Intrinsics.checkNotNullParameter(inheritEnhancement, "$this$inheritEnhancement");
        Intrinsics.checkNotNullParameter(origin, "origin");
        return wrapEnhancement(inheritEnhancement, getEnhancement(origin));
    }

    public static final UnwrappedType wrapEnhancement(UnwrappedType wrapEnhancement, KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(wrapEnhancement, "$this$wrapEnhancement");
        if (kotlinType == null) {
            return wrapEnhancement;
        }
        if (wrapEnhancement instanceof SimpleType) {
            return new SimpleTypeWithEnhancement((SimpleType) wrapEnhancement, kotlinType);
        }
        if (wrapEnhancement instanceof FlexibleType) {
            return new FlexibleTypeWithEnhancement((FlexibleType) wrapEnhancement, kotlinType);
        }
        throw new NoWhenBranchMatchedException();
    }
}
