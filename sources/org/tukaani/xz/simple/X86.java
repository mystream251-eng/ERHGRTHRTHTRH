package org.tukaani.xz.simple;

import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public final class X86 implements SimpleFilter {
    private static final boolean[] MASK_TO_ALLOWED_STATUS = {true, true, true, false, true, false, false, false};
    private static final int[] MASK_TO_BIT_NUMBER = {0, 1, 2, 2, 3, 3, 3, 3};
    private final boolean isEncoder;
    private int pos;
    private int prevMask = 0;

    public X86(boolean z, int i) {
        this.isEncoder = z;
        this.pos = i + 5;
    }

    private static boolean test86MSByte(byte b) {
        int i = b & UByte.MAX_VALUE;
        return i == 0 || i == 255;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x00ae  */
    @Override // org.tukaani.xz.simple.SimpleFilter
    public int code(byte[] bArr, int i, int i2) {
        int i3;
        int i4 = i - 1;
        int i5 = (i2 + i) - 5;
        int i6 = i;
        while (true) {
            if (i6 > i5) {
                break;
            }
            if ((bArr[i6] & 254) == 232) {
                int i7 = i6 - i4;
                if ((i7 & (-4)) != 0) {
                    this.prevMask = 0;
                } else {
                    int i8 = (this.prevMask << (i7 - 1)) & 7;
                    this.prevMask = i8;
                    if (i8 != 0 && (!MASK_TO_ALLOWED_STATUS[i8] || test86MSByte(bArr[(i6 + 4) - MASK_TO_BIT_NUMBER[i8]]))) {
                        this.prevMask = (this.prevMask << 1) | 1;
                        i4 = i6;
                    }
                }
                int i9 = i6 + 4;
                if (test86MSByte(bArr[i9])) {
                    int i10 = i6 + 1;
                    int i11 = i6 + 2;
                    int i12 = i6 + 3;
                    int i13 = (bArr[i10] & 255) | ((bArr[i11] & 255) << 8) | ((bArr[i12] & 255) << 16) | ((bArr[i9] & 255) << 24);
                    while (true) {
                        i3 = this.isEncoder ? i13 + ((this.pos + i6) - i) : i13 - ((this.pos + i6) - i);
                        int i14 = this.prevMask;
                        if (i14 == 0) {
                            break;
                        }
                        int i15 = MASK_TO_BIT_NUMBER[i14] * 8;
                        if (!test86MSByte((byte) (i3 >>> (24 - i15)))) {
                            break;
                        }
                        i13 = i3 ^ ((1 << (32 - i15)) - 1);
                    }
                    bArr[i10] = (byte) i3;
                    bArr[i11] = (byte) (i3 >>> 8);
                    bArr[i12] = (byte) (i3 >>> 16);
                    bArr[i9] = (byte) (~(((i3 >>> 24) & 1) - 1));
                    int i16 = i6;
                    i6 = i9;
                    i4 = i16;
                } else {
                    this.prevMask = (this.prevMask << 1) | 1;
                    i4 = i6;
                }
            }
            i6++;
        }
        int i17 = i6 - i4;
        this.prevMask = (i17 & (-4)) == 0 ? this.prevMask << (i17 - 1) : 0;
        int i18 = i6 - i;
        this.pos += i18;
        return i18;
    }
}
