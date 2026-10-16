package com.google.android.gms.internal;

import androidx.exifinterface.media.ExifInterface;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECField;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import javax.crypto.KeyAgreement;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdxs {
    public static int zza(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        return (zzb(ellipticCurve).subtract(BigInteger.ONE).bitLength() + 7) / 8;
    }

    private static BigInteger zza(BigInteger bigInteger, boolean z, EllipticCurve ellipticCurve) throws GeneralSecurityException {
        BigInteger bigInteger2;
        BigInteger bigIntegerZzb = zzb(ellipticCurve);
        BigInteger bigIntegerMod = bigInteger.multiply(bigInteger).add(ellipticCurve.getA()).multiply(bigInteger).add(ellipticCurve.getB()).mod(bigIntegerZzb);
        if (bigIntegerZzb.signum() != 1) {
            throw new InvalidAlgorithmParameterException("p must be positive");
        }
        BigInteger bigIntegerMod2 = bigIntegerMod.mod(bigIntegerZzb);
        BigInteger bigIntegerAdd = null;
        if (bigIntegerMod2.equals(BigInteger.ZERO)) {
            bigInteger2 = BigInteger.ZERO;
        } else {
            if (bigIntegerZzb.testBit(0) && bigIntegerZzb.testBit(1)) {
                bigIntegerAdd = bigIntegerMod2.modPow(bigIntegerZzb.add(BigInteger.ONE).shiftRight(2), bigIntegerZzb);
            } else {
                if (bigIntegerZzb.testBit(0) && !bigIntegerZzb.testBit(1)) {
                    bigIntegerAdd = BigInteger.ONE;
                    BigInteger bigIntegerShiftRight = bigIntegerZzb.subtract(BigInteger.ONE).shiftRight(1);
                    int i = 0;
                    while (true) {
                        BigInteger bigIntegerMod3 = bigIntegerAdd.multiply(bigIntegerAdd).subtract(bigIntegerMod2).mod(bigIntegerZzb);
                        if (!bigIntegerMod3.equals(BigInteger.ZERO)) {
                            BigInteger bigIntegerModPow = bigIntegerMod3.modPow(bigIntegerShiftRight, bigIntegerZzb);
                            if (bigIntegerModPow.add(BigInteger.ONE).equals(bigIntegerZzb)) {
                                BigInteger bigIntegerShiftRight2 = bigIntegerZzb.add(BigInteger.ONE).shiftRight(1);
                                BigInteger bigIntegerMod4 = BigInteger.ONE;
                                BigInteger bigIntegerMod5 = bigIntegerAdd;
                                for (int iBitLength = bigIntegerShiftRight2.bitLength() - 2; iBitLength >= 0; iBitLength--) {
                                    BigInteger bigIntegerMultiply = bigIntegerMod5.multiply(bigIntegerMod4);
                                    bigIntegerMod5 = bigIntegerMod5.multiply(bigIntegerMod5).add(bigIntegerMod4.multiply(bigIntegerMod4).mod(bigIntegerZzb).multiply(bigIntegerMod3)).mod(bigIntegerZzb);
                                    bigIntegerMod4 = bigIntegerMultiply.add(bigIntegerMultiply).mod(bigIntegerZzb);
                                    if (bigIntegerShiftRight2.testBit(iBitLength)) {
                                        BigInteger bigIntegerMod6 = bigIntegerMod5.multiply(bigIntegerAdd).add(bigIntegerMod4.multiply(bigIntegerMod3)).mod(bigIntegerZzb);
                                        bigIntegerMod4 = bigIntegerAdd.multiply(bigIntegerMod4).add(bigIntegerMod5).mod(bigIntegerZzb);
                                        bigIntegerMod5 = bigIntegerMod6;
                                    }
                                }
                                bigIntegerAdd = bigIntegerMod5;
                            } else {
                                if (!bigIntegerModPow.equals(BigInteger.ONE)) {
                                    throw new InvalidAlgorithmParameterException("p is not prime");
                                }
                                bigIntegerAdd = bigIntegerAdd.add(BigInteger.ONE);
                                i++;
                                if (i == 128 && !bigIntegerZzb.isProbablePrime(80)) {
                                    throw new InvalidAlgorithmParameterException("p is not prime");
                                }
                            }
                        }
                    }
                }
                bigInteger2 = bigIntegerAdd;
            }
            if (bigIntegerAdd != null && bigIntegerAdd.multiply(bigIntegerAdd).mod(bigIntegerZzb).compareTo(bigIntegerMod2) != 0) {
                throw new GeneralSecurityException("Could not find a modular square root");
            }
            bigInteger2 = bigIntegerAdd;
        }
        return true != bigInteger2.testBit(0) ? bigIntegerZzb.subtract(bigInteger2).mod(bigIntegerZzb) : bigInteger2;
    }

    public static KeyPair zza(ECParameterSpec eCParameterSpec) throws GeneralSecurityException {
        KeyPairGenerator keyPairGeneratorZza = zzdxx.zzd.zza("EC");
        keyPairGeneratorZza.initialize(eCParameterSpec);
        return keyPairGeneratorZza.generateKeyPair();
    }

    public static ECPublicKey zza(zzdxu zzdxuVar, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        ECParameterSpec eCParameterSpecZza = zza(zzdxuVar);
        ECPoint eCPoint = new ECPoint(new BigInteger(1, bArr), new BigInteger(1, bArr2));
        zza(eCPoint, eCParameterSpecZza.getCurve());
        return (ECPublicKey) zzdxx.zze.zza("EC").generatePublic(new ECPublicKeySpec(eCPoint, eCParameterSpecZza));
    }

    public static ECParameterSpec zza(zzdxu zzdxuVar) throws NoSuchAlgorithmException {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        int i = zzdxt.zzb[zzdxuVar.ordinal()];
        if (i == 1) {
            str = "115792089210356248762697446949407573530086143415290314195533631308867097853951";
            str2 = "115792089210356248762697446949407573529996955224135760342422259061068512044369";
            str3 = "5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b";
            str4 = "6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296";
            str5 = "4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5";
        } else if (i == 2) {
            str = "39402006196394479212279040100143613805079739270465446667948293404245721771496870329047266088258938001861606973112319";
            str2 = "39402006196394479212279040100143613805079739270465446667946905279627659399113263569398956308152294913554433653942643";
            str3 = "b3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef";
            str4 = "aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7";
            str5 = "3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f";
        } else {
            if (i != 3) {
                String strValueOf = String.valueOf(zzdxuVar);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 22);
                sb.append("curve not implemented:");
                sb.append(strValueOf);
                throw new NoSuchAlgorithmException(sb.toString());
            }
            str = "6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151";
            str2 = "6864797660130609714981900799081393217269435300143305409394463459185543183397655394245057746333217197532963996371363321113864768612440380340372808892707005449";
            str3 = "051953eb9618e1c9a1f929a21a0b68540eea2da725b99b315f3b8b489918ef109e156193951ec7e937b1652c0bd3bb1bf073573df883d2c34f1ef451fd46b503f00";
            str4 = "c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66";
            str5 = "11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650";
        }
        return zza(str, str2, str3, str4, str5);
    }

    private static ECParameterSpec zza(String str, String str2, String str3, String str4, String str5) {
        BigInteger bigInteger = new BigInteger(str);
        return new ECParameterSpec(new EllipticCurve(new ECFieldFp(bigInteger), bigInteger.subtract(new BigInteger(ExifInterface.GPS_MEASUREMENT_3D)), new BigInteger(str3, 16)), new ECPoint(new BigInteger(str4, 16), new BigInteger(str5, 16)), new BigInteger(str2), 1);
    }

    static void zza(ECPoint eCPoint, EllipticCurve ellipticCurve) throws GeneralSecurityException {
        BigInteger bigIntegerZzb = zzb(ellipticCurve);
        BigInteger affineX = eCPoint.getAffineX();
        BigInteger affineY = eCPoint.getAffineY();
        if (affineX == null || affineY == null) {
            throw new GeneralSecurityException("point is at infinity");
        }
        if (affineX.signum() == -1 || affineX.compareTo(bigIntegerZzb) != -1) {
            throw new GeneralSecurityException("x is out of range");
        }
        if (affineY.signum() == -1 || affineY.compareTo(bigIntegerZzb) != -1) {
            throw new GeneralSecurityException("y is out of range");
        }
        if (!affineY.multiply(affineY).mod(bigIntegerZzb).equals(affineX.multiply(affineX).add(ellipticCurve.getA()).multiply(affineX).add(ellipticCurve.getB()).mod(bigIntegerZzb))) {
            throw new GeneralSecurityException("Point is not on curve");
        }
    }

    public static byte[] zza(ECPrivateKey eCPrivateKey, ECPoint eCPoint) throws GeneralSecurityException {
        zza(eCPoint, eCPrivateKey.getParams().getCurve());
        ECParameterSpec params = eCPrivateKey.getParams();
        params.getCurve();
        PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("EC").generatePublic(new ECPublicKeySpec(eCPoint, params));
        KeyAgreement keyAgreementZza = zzdxx.zzc.zza("ECDH");
        keyAgreementZza.init(eCPrivateKey);
        keyAgreementZza.doPhase(publicKeyGeneratePublic, true);
        byte[] bArrGenerateSecret = keyAgreementZza.generateSecret();
        EllipticCurve curve = eCPrivateKey.getParams().getCurve();
        BigInteger bigInteger = new BigInteger(1, bArrGenerateSecret);
        if (bigInteger.signum() == -1 || bigInteger.compareTo(zzb(curve)) != -1) {
            throw new GeneralSecurityException("shared secret is out of range");
        }
        zza(bigInteger, true, curve);
        return bArrGenerateSecret;
    }

    private static BigInteger zzb(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        ECField field = ellipticCurve.getField();
        if (field instanceof ECFieldFp) {
            return ((ECFieldFp) field).getP();
        }
        throw new GeneralSecurityException("Only curves over prime order fields are supported");
    }
}
