package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdxq {
    private ECPublicKey zza;

    public zzdxq(ECPublicKey eCPublicKey) {
        this.zza = eCPublicKey;
    }

    public final zzdxr zza(String str, byte[] bArr, byte[] bArr2, int i, zzdxv zzdxvVar) throws GeneralSecurityException {
        byte[] bArr3;
        KeyPair keyPairZza = zzdxs.zza(this.zza.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) keyPairZza.getPublic();
        ECPrivateKey eCPrivateKey = (ECPrivateKey) keyPairZza.getPrivate();
        ECPublicKey eCPublicKey2 = this.zza;
        ECParameterSpec params = eCPublicKey2.getParams();
        ECParameterSpec params2 = eCPrivateKey.getParams();
        if (!params.getCurve().equals(params2.getCurve()) || !params.getGenerator().equals(params2.getGenerator()) || !params.getOrder().equals(params2.getOrder()) || params.getCofactor() != params2.getCofactor()) {
            throw new GeneralSecurityException("invalid public key spec");
        }
        byte[] bArrZza = zzdxs.zza(eCPrivateKey, eCPublicKey2.getW());
        EllipticCurve curve = eCPublicKey.getParams().getCurve();
        ECPoint w = eCPublicKey.getW();
        zzdxs.zza(w, curve);
        int iZza = zzdxs.zza(curve);
        int i2 = zzdxt.zza[zzdxvVar.ordinal()];
        if (i2 == 1) {
            int i3 = (iZza * 2) + 1;
            byte[] bArr4 = new byte[i3];
            byte[] byteArray = w.getAffineX().toByteArray();
            byte[] byteArray2 = w.getAffineY().toByteArray();
            System.arraycopy(byteArray2, 0, bArr4, i3 - byteArray2.length, byteArray2.length);
            System.arraycopy(byteArray, 0, bArr4, (iZza + 1) - byteArray.length, byteArray.length);
            bArr4[0] = 4;
            bArr3 = bArr4;
        } else {
            if (i2 != 2) {
                String strValueOf = String.valueOf(zzdxvVar);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 15);
                sb.append("invalid format:");
                sb.append(strValueOf);
                throw new GeneralSecurityException(sb.toString());
            }
            int i4 = iZza + 1;
            bArr3 = new byte[i4];
            byte[] byteArray3 = w.getAffineX().toByteArray();
            System.arraycopy(byteArray3, 0, bArr3, i4 - byteArray3.length, byteArray3.length);
            bArr3[0] = (byte) (w.getAffineY().testBit(0) ? 3 : 2);
        }
        return new zzdxr(bArr3, zzdyg.zza(str, zzdxi.zza(bArr3, bArrZza), bArr, bArr2, i));
    }
}
