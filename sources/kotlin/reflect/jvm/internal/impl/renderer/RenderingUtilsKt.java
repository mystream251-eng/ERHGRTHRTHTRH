package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: compiled from: RenderingUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class RenderingUtilsKt {
    public static final String render(Name render) {
        Intrinsics.checkNotNullParameter(render, "$this$render");
        if (!shouldBeEscaped(render)) {
            String strAsString = render.asString();
            Intrinsics.checkNotNullExpressionValue(strAsString, "asString()");
            return strAsString;
        }
        StringBuilder sb = new StringBuilder();
        String strAsString2 = render.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString2, "asString()");
        sb.append(String.valueOf('`') + strAsString2);
        sb.append('`');
        return sb.toString();
    }

    private static final boolean shouldBeEscaped(Name name) {
        boolean z;
        if (name.isSpecial()) {
            return false;
        }
        String strAsString = name.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString()");
        if (!KeywordStringsGenerated.KEYWORDS.contains(strAsString)) {
            String str = strAsString;
            int i = 0;
            while (true) {
                if (i >= str.length()) {
                    z = false;
                    break;
                }
                char cCharAt = str.charAt(i);
                if ((Character.isLetterOrDigit(cCharAt) || cCharAt == '_') ? false : true) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public static final String render(FqNameUnsafe render) {
        Intrinsics.checkNotNullParameter(render, "$this$render");
        List<Name> listPathSegments = render.pathSegments();
        Intrinsics.checkNotNullExpressionValue(listPathSegments, "pathSegments()");
        return renderFqName(listPathSegments);
    }

    public static final String renderFqName(List<Name> pathSegments) {
        Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
        StringBuilder sb = new StringBuilder();
        for (Name name : pathSegments) {
            if (sb.length() > 0) {
                sb.append(".");
            }
            sb.append(render(name));
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
