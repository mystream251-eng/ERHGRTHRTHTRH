package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzhw {
    public static int zza(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        int length = bytes.length;
        int i = 0;
        int i2 = (length & (-4)) + 0;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4 += 4) {
            int i5 = ((bytes[i4] & UByte.MAX_VALUE) | ((bytes[i4 + 1] & UByte.MAX_VALUE) << 8) | ((bytes[i4 + 2] & UByte.MAX_VALUE) << 16) | (bytes[i4 + 3] << 24)) * (-862048943);
            int i6 = i3 ^ (((i5 << 15) | (i5 >>> 17)) * 461845907);
            i3 = (((i6 >>> 19) | (i6 << 13)) * 5) - 430675100;
        }
        int i7 = length & 3;
        if (i7 == 1) {
            int i8 = ((bytes[i2] & UByte.MAX_VALUE) | i) * (-862048943);
            i3 ^= ((i8 >>> 17) | (i8 << 15)) * 461845907;
        } else {
            if (i7 != 2) {
                i = i7 == 3 ? (bytes[i2 + 2] & UByte.MAX_VALUE) << 16 : 0;
            }
            i |= (bytes[i2 + 1] & UByte.MAX_VALUE) << 8;
            int i9 = ((bytes[i2] & UByte.MAX_VALUE) | i) * (-862048943);
            i3 ^= ((i9 >>> 17) | (i9 << 15)) * 461845907;
        }
        int i10 = i3 ^ length;
        int i11 = (i10 ^ (i10 >>> 16)) * (-2048144789);
        int i12 = (i11 ^ (i11 >>> 13)) * (-1028477387);
        return i12 ^ (i12 >>> 16);
    }

    /* JADX WARN: Code duplicated, block: B:53:0x0083  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d3  */
    public static String[] zza(String str, boolean z) {
        boolean z2;
        String str2;
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        char[] charArray = str.toCharArray();
        int length = str.length();
        int i = 0;
        boolean z3 = false;
        int i2 = 0;
        while (i < length) {
            int iCodePointAt = Character.codePointAt(charArray, i);
            int iCharCount = Character.charCount(iCodePointAt);
            if (Character.isLetter(iCodePointAt)) {
                Character.UnicodeBlock unicodeBlockOf = Character.UnicodeBlock.of(iCodePointAt);
                if (!(unicodeBlockOf == Character.UnicodeBlock.BOPOMOFO || unicodeBlockOf == Character.UnicodeBlock.BOPOMOFO_EXTENDED || unicodeBlockOf == Character.UnicodeBlock.CJK_COMPATIBILITY || unicodeBlockOf == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || unicodeBlockOf == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT || unicodeBlockOf == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || unicodeBlockOf == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || unicodeBlockOf == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || unicodeBlockOf == Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS || unicodeBlockOf == Character.UnicodeBlock.HANGUL_JAMO || unicodeBlockOf == Character.UnicodeBlock.HANGUL_SYLLABLES || unicodeBlockOf == Character.UnicodeBlock.HIRAGANA || unicodeBlockOf == Character.UnicodeBlock.KATAKANA || unicodeBlockOf == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS)) {
                    if (!((iCodePointAt >= 65382 && iCodePointAt <= 65437) || (iCodePointAt >= 65441 && iCodePointAt <= 65500))) {
                        z2 = false;
                    }
                }
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                if (z3) {
                    arrayList.add(new String(charArray, i2, i - i2));
                }
                str2 = new String(charArray, i, iCharCount);
            } else {
                if (Character.isLetterOrDigit(iCodePointAt) || Character.getType(iCodePointAt) == 6 || Character.getType(iCodePointAt) == 8) {
                    if (!z3) {
                        i2 = i;
                    }
                    z3 = true;
                } else if (z && Character.charCount(iCodePointAt) == 1 && Character.toChars(iCodePointAt)[0] == '\'') {
                    if (!z3) {
                        i2 = i;
                    }
                    z3 = true;
                } else if (z3) {
                    str2 = new String(charArray, i2, i - i2);
                }
                i += iCharCount;
            }
            arrayList.add(str2);
            z3 = false;
            i += iCharCount;
        }
        if (z3) {
            arrayList.add(new String(charArray, i2, i - i2));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
