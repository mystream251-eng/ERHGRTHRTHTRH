package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: signatureEnhancement.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class SignatureEnhancementKt {
    public static final JavaTypeQualifiers createJavaTypeQualifiers(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2) {
        if (!z2 || nullabilityQualifier != NullabilityQualifier.NOT_NULL) {
            return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, false, z);
        }
        return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, true, z);
    }

    public static final <T> T select(Set<? extends T> select, T low, T high, T t, boolean z) {
        Set<? extends T> set;
        T t2;
        Intrinsics.checkNotNullParameter(select, "$this$select");
        Intrinsics.checkNotNullParameter(low, "low");
        Intrinsics.checkNotNullParameter(high, "high");
        if (z) {
            if (select.contains(low)) {
                t2 = low;
            } else {
                t2 = select.contains(high) ? high : null;
            }
            if (Intrinsics.areEqual(t2, low) && Intrinsics.areEqual(t, high)) {
                return null;
            }
            return t != null ? t : t2;
        }
        if (t != null && (set = CollectionsKt.toSet(SetsKt.plus(select, t))) != null) {
            select = set;
        }
        return (T) CollectionsKt.singleOrNull(select);
    }

    public static final NullabilityQualifier select(Set<? extends NullabilityQualifier> select, NullabilityQualifier nullabilityQualifier, boolean z) {
        Intrinsics.checkNotNullParameter(select, "$this$select");
        if (nullabilityQualifier == NullabilityQualifier.FORCE_FLEXIBILITY) {
            return NullabilityQualifier.FORCE_FLEXIBILITY;
        }
        return (NullabilityQualifier) select(select, NullabilityQualifier.NOT_NULL, NullabilityQualifier.NULLABLE, nullabilityQualifier, z);
    }
}
