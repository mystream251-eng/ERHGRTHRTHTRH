package com.google.android.gms.internal;

import com.squareup.moshi.MsgpackFormat;
import java.util.Arrays;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
final class zzdyk {
    private static long zza(byte[] bArr, int i) {
        return ((long) (((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16))) & MsgpackFormat.SIZE_32;
    }

    private static long zza(byte[] bArr, int i, int i2) {
        return (zza(bArr, i) >> i2) & 67108863;
    }

    private static void zza(byte[] bArr, long j, int i) {
        int i2 = 0;
        while (i2 < 4) {
            bArr[i + i2] = (byte) (255 & j);
            i2++;
            j >>= 8;
        }
    }

    static byte[] zza(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("The key length in bytes must be 32.");
        }
        long jZza = zza(bArr, 0, 0) & 67108863;
        int i = 3;
        long jZza2 = zza(bArr, 3, 2) & 67108611;
        long jZza3 = zza(bArr, 6, 4) & 67092735;
        long jZza4 = zza(bArr, 9, 6) & 66076671;
        long jZza5 = zza(bArr, 12, 8) & 1048575;
        long j = jZza2 * 5;
        long j2 = jZza3 * 5;
        long j3 = jZza4 * 5;
        long j4 = jZza5 * 5;
        int i2 = 17;
        byte[] bArr3 = new byte[17];
        long j5 = 0;
        int i3 = 0;
        long j6 = 0;
        long j7 = 0;
        long j8 = 0;
        long j9 = 0;
        while (i3 < bArr2.length) {
            int iMin = Math.min(16, bArr2.length - i3);
            System.arraycopy(bArr2, i3, bArr3, 0, iMin);
            bArr3[iMin] = 1;
            if (iMin != 16) {
                Arrays.fill(bArr3, iMin + 1, i2, (byte) 0);
            }
            long jZza6 = j9 + zza(bArr3, 0, 0);
            long jZza7 = j5 + zza(bArr3, i, 2);
            long jZza8 = j6 + zza(bArr3, 6, 4);
            long jZza9 = j7 + zza(bArr3, 9, 6);
            long jZza10 = j8 + (zza(bArr3, 12, 8) | ((long) (bArr3[16] << 24)));
            long j10 = (jZza6 * jZza) + (jZza7 * j4) + (jZza8 * j3) + (jZza9 * j2) + (jZza10 * j);
            long j11 = (jZza6 * jZza2) + (jZza7 * jZza) + (jZza8 * j4) + (jZza9 * j3) + (jZza10 * j2);
            long j12 = (jZza6 * jZza3) + (jZza7 * jZza2) + (jZza8 * jZza) + (jZza9 * j4) + (jZza10 * j3);
            long j13 = (jZza6 * jZza4) + (jZza7 * jZza3) + (jZza8 * jZza2) + (jZza9 * jZza) + (jZza10 * j4);
            long j14 = j11 + (j10 >> 26);
            long j15 = j12 + (j14 >> 26);
            long j16 = j13 + (j15 >> 26);
            long j17 = (jZza6 * jZza5) + (jZza7 * jZza4) + (jZza8 * jZza3) + (jZza9 * jZza2) + (jZza10 * jZza) + (j16 >> 26);
            long j18 = (j10 & 67108863) + ((j17 >> 26) * 5);
            j5 = (j14 & 67108863) + (j18 >> 26);
            i3 += 16;
            j6 = j15 & 67108863;
            j7 = j16 & 67108863;
            j8 = j17 & 67108863;
            i2 = 17;
            i = 3;
            j9 = j18 & 67108863;
        }
        long j19 = j6 + (j5 >> 26);
        long j20 = j19 & 67108863;
        long j21 = j7 + (j19 >> 26);
        long j22 = j21 & 67108863;
        long j23 = j8 + (j21 >> 26);
        long j24 = j23 & 67108863;
        long j25 = j9 + ((j23 >> 26) * 5);
        long j26 = j25 & 67108863;
        long j27 = (j5 & 67108863) + (j25 >> 26);
        long j28 = j26 + 5;
        long j29 = j28 & 67108863;
        long j30 = (j28 >> 26) + j27;
        long j31 = j20 + (j30 >> 26);
        long j32 = j22 + (j31 >> 26);
        long j33 = (j24 + (j32 >> 26)) - 67108864;
        long j34 = j33 >> 63;
        long j35 = ~j34;
        long j36 = (j27 & j34) | (j30 & 67108863 & j35);
        long j37 = (j20 & j34) | (j31 & 67108863 & j35);
        long j38 = (j22 & j34) | (j32 & 67108863 & j35);
        long j39 = ((j36 << 26) | (j26 & j34) | (j29 & j35)) & MsgpackFormat.SIZE_32;
        long j40 = ((j36 >> 6) | (j37 << 20)) & MsgpackFormat.SIZE_32;
        long j41 = ((j37 >> 12) | (j38 << 14)) & MsgpackFormat.SIZE_32;
        long j42 = ((((j33 & j35) | (j24 & j34)) << 8) | (j38 >> 18)) & MsgpackFormat.SIZE_32;
        long jZza11 = j39 + zza(bArr, 16);
        long j43 = jZza11 & MsgpackFormat.SIZE_32;
        long jZza12 = j40 + zza(bArr, 20) + (jZza11 >> 32);
        long j44 = jZza12 & MsgpackFormat.SIZE_32;
        long jZza13 = j41 + zza(bArr, 24) + (jZza12 >> 32);
        long j45 = jZza13 & MsgpackFormat.SIZE_32;
        long jZza14 = (j42 + zza(bArr, 28) + (jZza13 >> 32)) & MsgpackFormat.SIZE_32;
        byte[] bArr4 = new byte[16];
        zza(bArr4, j43, 0);
        zza(bArr4, j44, 4);
        zza(bArr4, j45, 8);
        zza(bArr4, jZza14, 12);
        return bArr4;
    }
}
