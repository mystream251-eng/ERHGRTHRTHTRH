package kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: capitalizeDecapitalize.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class CapitalizeDecapitalizeKt {
    public static final String decapitalizeSmartForCompiler(String decapitalizeSmartForCompiler, boolean z) {
        Integer next;
        Intrinsics.checkNotNullParameter(decapitalizeSmartForCompiler, "$this$decapitalizeSmartForCompiler");
        String str = decapitalizeSmartForCompiler;
        if ((str.length() == 0) || !isUpperCaseCharAt(decapitalizeSmartForCompiler, 0, z)) {
            return decapitalizeSmartForCompiler;
        }
        if (decapitalizeSmartForCompiler.length() == 1 || !isUpperCaseCharAt(decapitalizeSmartForCompiler, 1, z)) {
            return z ? decapitalizeAsciiOnly(decapitalizeSmartForCompiler) : StringsKt.decapitalize(decapitalizeSmartForCompiler);
        }
        Iterator<Integer> it = StringsKt.getIndices(str).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(!isUpperCaseCharAt(decapitalizeSmartForCompiler, next.intValue(), z)));
        Integer num = next;
        if (num == null) {
            return toLowerCase(decapitalizeSmartForCompiler, z);
        }
        int iIntValue = num.intValue() - 1;
        StringBuilder sb = new StringBuilder();
        String strSubstring = decapitalizeSmartForCompiler.substring(0, iIntValue);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        sb.append(toLowerCase(strSubstring, z));
        String strSubstring2 = decapitalizeSmartForCompiler.substring(iIntValue);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
        sb.append(strSubstring2);
        return sb.toString();
    }

    private static final boolean isUpperCaseCharAt(String str, int i, boolean z) {
        char cCharAt = str.charAt(i);
        if (z) {
            return 'A' <= cCharAt && 'Z' >= cCharAt;
        }
        return Character.isUpperCase(cCharAt);
    }

    private static final String toLowerCase(String str, boolean z) {
        if (z) {
            return toLowerCaseAsciiOnly(str);
        }
        if (str == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = str.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
        return lowerCase;
    }

    public static final String capitalizeAsciiOnly(String capitalizeAsciiOnly) {
        char cCharAt;
        Intrinsics.checkNotNullParameter(capitalizeAsciiOnly, "$this$capitalizeAsciiOnly");
        if ((capitalizeAsciiOnly.length() == 0) || 'a' > (cCharAt = capitalizeAsciiOnly.charAt(0)) || 'z' < cCharAt) {
            return capitalizeAsciiOnly;
        }
        char upperCase = Character.toUpperCase(cCharAt);
        String strSubstring = capitalizeAsciiOnly.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return String.valueOf(upperCase) + strSubstring;
    }

    public static final String decapitalizeAsciiOnly(String decapitalizeAsciiOnly) {
        char cCharAt;
        Intrinsics.checkNotNullParameter(decapitalizeAsciiOnly, "$this$decapitalizeAsciiOnly");
        if ((decapitalizeAsciiOnly.length() == 0) || 'A' > (cCharAt = decapitalizeAsciiOnly.charAt(0)) || 'Z' < cCharAt) {
            return decapitalizeAsciiOnly;
        }
        char lowerCase = Character.toLowerCase(cCharAt);
        String strSubstring = decapitalizeAsciiOnly.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return String.valueOf(lowerCase) + strSubstring;
    }

    public static final String toLowerCaseAsciiOnly(String toLowerCaseAsciiOnly) {
        Intrinsics.checkNotNullParameter(toLowerCaseAsciiOnly, "$this$toLowerCaseAsciiOnly");
        StringBuilder sb = new StringBuilder(toLowerCaseAsciiOnly.length());
        int length = toLowerCaseAsciiOnly.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = toLowerCaseAsciiOnly.charAt(i);
            if ('A' <= cCharAt && 'Z' >= cCharAt) {
                cCharAt = Character.toLowerCase(cCharAt);
            }
            sb.append(cCharAt);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "builder.toString()");
        return string;
    }
}
