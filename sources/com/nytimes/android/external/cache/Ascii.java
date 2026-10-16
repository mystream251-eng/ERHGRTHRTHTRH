package com.nytimes.android.external.cache;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class Ascii {
    public static boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private Ascii() {
    }

    @Nonnull
    public static String toLowerCase(@Nonnull String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (isUpperCase(str.charAt(i))) {
                char[] charArray = str.toCharArray();
                while (i < length) {
                    char c = charArray[i];
                    if (isUpperCase(c)) {
                        charArray[i] = (char) (c ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(charArray);
            }
            i++;
        }
        return str;
    }
}
