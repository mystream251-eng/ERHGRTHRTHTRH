package kotlin.reflect.jvm.internal.impl.platform;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: platformUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class PlatformUtilKt {
    public static final String getPresentableDescription(TargetPlatform presentableDescription) {
        Intrinsics.checkNotNullParameter(presentableDescription, "$this$presentableDescription");
        return CollectionsKt.joinToString$default(presentableDescription.getComponentPlatforms(), "/", null, null, 0, null, null, 62, null);
    }
}
