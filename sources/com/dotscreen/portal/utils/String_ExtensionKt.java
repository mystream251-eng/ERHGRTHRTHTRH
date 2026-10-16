package com.dotscreen.portal.utils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: String+Extension.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u001c\u0010\u0004\u001a\u00020\u00012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u001a\n\u0010\b\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\t\u001a\u00020\n*\u00020\u0001\u001a\n\u0010\u000b\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\f\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\r\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u000e\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0010\u001a\u00020\u0001*\u00020\u0001¨\u0006\u0011"}, d2 = {"convertMillisToDuration", "", "millis", "", "requestParamsToURLString", "params", "", "", "hash256", "isGif", "", "removeLineReturns", "toDb", "toDbm", "toMHz", "toPercent", "tokHz", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class String_ExtensionKt {
    public static final String toMHz(String toMHz) {
        Intrinsics.checkParameterIsNotNull(toMHz, "$this$toMHz");
        return toMHz + " MHz";
    }

    public static final String tokHz(String tokHz) {
        Intrinsics.checkParameterIsNotNull(tokHz, "$this$tokHz");
        return tokHz + " kHz";
    }

    public static final String toDb(String toDb) {
        Intrinsics.checkParameterIsNotNull(toDb, "$this$toDb");
        return toDb + " dB";
    }

    public static final String toDbm(String toDbm) {
        Intrinsics.checkParameterIsNotNull(toDbm, "$this$toDbm");
        return toDbm + " dBm";
    }

    public static final String toPercent(String toPercent) {
        Intrinsics.checkParameterIsNotNull(toPercent, "$this$toPercent");
        return toPercent + " %";
    }

    public static final String hash256(String hash256) {
        Intrinsics.checkParameterIsNotNull(hash256, "$this$hash256");
        byte[] bytes = hash256.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] digest = MessageDigest.getInstance("SHA-256").digest(bytes);
        Intrinsics.checkExpressionValueIsNotNull(digest, "digest");
        String string = "";
        for (byte b : digest) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
            sb.append(str);
            string = sb.toString();
        }
        return string;
    }

    public static final boolean isGif(String isGif) {
        Intrinsics.checkParameterIsNotNull(isGif, "$this$isGif");
        String lowerCase = isGif.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
        return StringsKt.endsWith$default(lowerCase, ".gif", false, 2, (Object) null);
    }

    public static final String requestParamsToURLString(Map<String, ? extends Object> map) {
        String str = "";
        if (map == null || map.isEmpty()) {
            return "";
        }
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            str = str + entry.getKey() + '=' + entry.getValue() + Typography.amp;
        }
        int length = str.length() - 1;
        if (str == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = str.substring(0, length);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static final String convertMillisToDuration(long j) {
        String str;
        long j2 = 1000;
        long j3 = j % j2;
        long j4 = 60;
        long j5 = (j / j2) % j4;
        long j6 = (j / ((long) 60000)) % j4;
        long j7 = (j / ((long) 3600000)) % ((long) 24);
        long j8 = j / ((long) 86400000);
        if (j3 > 0) {
            str = j3 + " milliseconds";
        } else {
            str = "";
        }
        if (j5 > 0) {
            str = (j5 + " seconds") + ' ' + str;
        }
        if (j6 > 0) {
            str = (j6 + " minutes") + ' ' + str;
        }
        if (j7 > 0) {
            str = (j7 + " hours") + ' ' + str;
        }
        if (j8 <= 0) {
            return str;
        }
        return (j8 + " days") + ' ' + str;
    }

    public static final String removeLineReturns(String removeLineReturns) {
        Intrinsics.checkParameterIsNotNull(removeLineReturns, "$this$removeLineReturns");
        return new Regex("\\R+").replace(removeLineReturns, " ");
    }
}
