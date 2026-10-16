package com.google.crypto.tink.subtle;

import java.lang.reflect.Array;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes2.dex */
final class Ed25519Constants {
    static final Ed25519.CachedXYT[] B2;
    static final Ed25519.CachedXYT[][] B_TABLE;
    static final long[] D;
    static final long[] D2;
    static final long[] SQRTM1;
    private static final BigInteger P_BI = BigInteger.valueOf(2).pow(255).subtract(BigInteger.valueOf(19));
    private static final BigInteger D_BI = BigInteger.valueOf(-121665).multiply(BigInteger.valueOf(121666).modInverse(P_BI)).mod(P_BI);
    private static final BigInteger D2_BI = BigInteger.valueOf(2).multiply(D_BI).mod(P_BI);
    private static final BigInteger SQRTM1_BI = BigInteger.valueOf(2).modPow(P_BI.subtract(BigInteger.ONE).divide(BigInteger.valueOf(4)), P_BI);

    Ed25519Constants() {
    }

    static {
        Point point = new Point();
        point.y = BigInteger.valueOf(4L).multiply(BigInteger.valueOf(5L).modInverse(P_BI)).mod(P_BI);
        point.x = recoverX(point.y);
        D = Field25519.expand(toLittleEndian(D_BI));
        D2 = Field25519.expand(toLittleEndian(D2_BI));
        SQRTM1 = Field25519.expand(toLittleEndian(SQRTM1_BI));
        B_TABLE = (Ed25519.CachedXYT[][]) Array.newInstance((Class<?>) Ed25519.CachedXYT.class, 32, 8);
        Point pointEdwards = point;
        for (int i = 0; i < 32; i++) {
            Point pointEdwards2 = pointEdwards;
            for (int i2 = 0; i2 < 8; i2++) {
                B_TABLE[i][i2] = getCachedXYT(pointEdwards2);
                pointEdwards2 = edwards(pointEdwards2, pointEdwards);
            }
            for (int i3 = 0; i3 < 8; i3++) {
                pointEdwards = edwards(pointEdwards, pointEdwards);
            }
        }
        Point pointEdwards3 = edwards(point, point);
        B2 = new Ed25519.CachedXYT[8];
        for (int i4 = 0; i4 < 8; i4++) {
            B2[i4] = getCachedXYT(point);
            point = edwards(point, pointEdwards3);
        }
    }

    private static class Point {
        private BigInteger x;
        private BigInteger y;

        private Point() {
        }
    }

    private static BigInteger recoverX(BigInteger y) {
        BigInteger bigIntegerMultiply = y.pow(2).subtract(BigInteger.ONE).multiply(D_BI.multiply(y.pow(2)).add(BigInteger.ONE).modInverse(P_BI));
        BigInteger bigIntegerModPow = bigIntegerMultiply.modPow(P_BI.add(BigInteger.valueOf(3L)).divide(BigInteger.valueOf(8L)), P_BI);
        if (!bigIntegerModPow.pow(2).subtract(bigIntegerMultiply).mod(P_BI).equals(BigInteger.ZERO)) {
            bigIntegerModPow = bigIntegerModPow.multiply(SQRTM1_BI).mod(P_BI);
        }
        return bigIntegerModPow.testBit(0) ? P_BI.subtract(bigIntegerModPow) : bigIntegerModPow;
    }

    private static Point edwards(Point a2, Point b) {
        Point point = new Point();
        BigInteger bigIntegerMod = D_BI.multiply(a2.x.multiply(b.x).multiply(a2.y).multiply(b.y)).mod(P_BI);
        point.x = a2.x.multiply(b.y).add(b.x.multiply(a2.y)).multiply(BigInteger.ONE.add(bigIntegerMod).modInverse(P_BI)).mod(P_BI);
        point.y = a2.y.multiply(b.y).add(a2.x.multiply(b.x)).multiply(BigInteger.ONE.subtract(bigIntegerMod).modInverse(P_BI)).mod(P_BI);
        return point;
    }

    private static byte[] toLittleEndian(BigInteger n) {
        byte[] bArr = new byte[32];
        byte[] byteArray = n.toByteArray();
        System.arraycopy(byteArray, 0, bArr, 32 - byteArray.length, byteArray.length);
        for (int i = 0; i < 16; i++) {
            byte b = bArr[i];
            int i2 = (32 - i) - 1;
            bArr[i] = bArr[i2];
            bArr[i2] = b;
        }
        return bArr;
    }

    private static Ed25519.CachedXYT getCachedXYT(Point p) {
        return new Ed25519.CachedXYT(Field25519.expand(toLittleEndian(p.y.add(p.x).mod(P_BI))), Field25519.expand(toLittleEndian(p.y.subtract(p.x).mod(P_BI))), Field25519.expand(toLittleEndian(D2_BI.multiply(p.x).multiply(p.y).mod(P_BI))));
    }
}
