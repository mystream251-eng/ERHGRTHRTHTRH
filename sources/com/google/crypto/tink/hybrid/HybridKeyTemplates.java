package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadDemParams;
import com.google.crypto.tink.proto.EciesAeadHkdfKeyFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EciesHkdfKemParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class HybridKeyTemplates {
    private static final byte[] EMPTY_SALT = new byte[0];
    public static final KeyTemplate ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM = createEciesAeadHkdfKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, AeadKeyTemplates.AES128_GCM, OutputPrefixType.TINK, EMPTY_SALT);
    public static final KeyTemplate ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX = createEciesAeadHkdfKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, AeadKeyTemplates.AES128_GCM, OutputPrefixType.RAW, EMPTY_SALT);
    public static final KeyTemplate ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256 = createEciesAeadHkdfKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, AeadKeyTemplates.AES128_CTR_HMAC_SHA256, OutputPrefixType.TINK, EMPTY_SALT);

    public static KeyTemplate createEciesAeadHkdfKeyTemplate(EllipticCurveType curve, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate demKeyTemplate, OutputPrefixType outputPrefixType, byte[] salt) {
        return KeyTemplate.newBuilder().setTypeUrl(new EciesAeadHkdfPrivateKeyManager().getKeyType()).setOutputPrefixType(outputPrefixType).setValue(EciesAeadHkdfKeyFormat.newBuilder().setParams(createEciesAeadHkdfParams(curve, hashType, ecPointFormat, demKeyTemplate, salt)).build().toByteString()).build();
    }

    public static EciesAeadHkdfParams createEciesAeadHkdfParams(EllipticCurveType curve, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate demKeyTemplate, byte[] salt) {
        EciesHkdfKemParams eciesHkdfKemParamsBuild = EciesHkdfKemParams.newBuilder().setCurveType(curve).setHkdfHashType(hashType).setHkdfSalt(ByteString.copyFrom(salt)).build();
        return EciesAeadHkdfParams.newBuilder().setKemParams(eciesHkdfKemParamsBuild).setDemParams(EciesAeadDemParams.newBuilder().setAeadDem(demKeyTemplate).build()).setEcPointFormat(ecPointFormat).build();
    }
}
