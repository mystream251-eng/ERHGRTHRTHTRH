package kotlin.reflect.jvm.internal.impl.name;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FqNamesUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class FqNamesUtilKt {

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[State.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[State.BEGINNING.ordinal()] = 1;
            $EnumSwitchMapping$0[State.AFTER_DOT.ordinal()] = 2;
            $EnumSwitchMapping$0[State.MIDDLE.ordinal()] = 3;
        }
    }

    public static final boolean isSubpackageOf(FqName isSubpackageOf, FqName packageName) {
        Intrinsics.checkNotNullParameter(isSubpackageOf, "$this$isSubpackageOf");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        if (Intrinsics.areEqual(isSubpackageOf, packageName) || packageName.isRoot()) {
            return true;
        }
        String strAsString = isSubpackageOf.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "this.asString()");
        String strAsString2 = packageName.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString2, "packageName.asString()");
        return isSubpackageOf(strAsString, strAsString2);
    }

    private static final boolean isSubpackageOf(String str, String str2) {
        return StringsKt.startsWith$default(str, str2, false, 2, (Object) null) && str.charAt(str2.length()) == '.';
    }

    public static final FqName tail(FqName tail, FqName prefix) {
        Intrinsics.checkNotNullParameter(tail, "$this$tail");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!isSubpackageOf(tail, prefix) || prefix.isRoot()) {
            return tail;
        }
        if (Intrinsics.areEqual(tail, prefix)) {
            FqName fqName = FqName.ROOT;
            Intrinsics.checkNotNullExpressionValue(fqName, "FqName.ROOT");
            return fqName;
        }
        String strAsString = tail.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString()");
        int length = prefix.asString().length() + 1;
        if (strAsString == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = strAsString.substring(length);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return new FqName(strSubstring);
    }

    public static final boolean isValidJavaFqName(String str) {
        if (str == null) {
            return false;
        }
        State state = State.BEGINNING;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            int i2 = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
            if (i2 == 1 || i2 == 2) {
                if (!Character.isJavaIdentifierPart(cCharAt)) {
                    return false;
                }
                state = State.MIDDLE;
            } else if (i2 != 3) {
                continue;
            } else if (cCharAt == '.') {
                state = State.AFTER_DOT;
            } else if (!Character.isJavaIdentifierPart(cCharAt)) {
                return false;
            }
        }
        return state != State.AFTER_DOT;
    }
}
