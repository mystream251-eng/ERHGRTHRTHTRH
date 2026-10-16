package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnnotationUseSiteTarget.kt */
/* JADX INFO: loaded from: classes3.dex */
public enum AnnotationUseSiteTarget {
    FIELD(null, 1, null),
    FILE(null, 1, null),
    PROPERTY(0 == true ? 1 : 0, 1, null),
    PROPERTY_GETTER("get"),
    PROPERTY_SETTER("set"),
    RECEIVER(0 == true ? 1 : 0, 1, null),
    CONSTRUCTOR_PARAMETER("param"),
    SETTER_PARAMETER("setparam"),
    PROPERTY_DELEGATE_FIELD("delegate");

    private final String renderName;

    AnnotationUseSiteTarget(String str) {
        if (str == null) {
            String strName = name();
            if (strName == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            str = strName.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.String).toLowerCase()");
        }
        this.renderName = str;
    }

    /* synthetic */ AnnotationUseSiteTarget(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (String) null : str);
    }

    public final String getRenderName() {
        return this.renderName;
    }
}
