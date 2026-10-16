package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

/* JADX INFO: compiled from: PrimitiveTypeUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class PrimitiveTypeUtilKt {
    public static final Collection<KotlinType> getAllSignedLiteralTypes(ModuleDescriptor allSignedLiteralTypes) {
        Intrinsics.checkNotNullParameter(allSignedLiteralTypes, "$this$allSignedLiteralTypes");
        return CollectionsKt.listOf((Object[]) new SimpleType[]{allSignedLiteralTypes.getBuiltIns().getIntType(), allSignedLiteralTypes.getBuiltIns().getLongType(), allSignedLiteralTypes.getBuiltIns().getByteType(), allSignedLiteralTypes.getBuiltIns().getShortType()});
    }
}
