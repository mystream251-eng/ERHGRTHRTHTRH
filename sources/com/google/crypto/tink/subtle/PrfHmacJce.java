package com.google.crypto.tink.subtle;

import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public final class PrfHmacJce implements Prf {
    static final int MIN_KEY_SIZE_IN_BYTES = 16;
    private final String algorithm;
    private final Key key;
    private final ThreadLocal<Mac> localMac = new ThreadLocal<Mac>() { // from class: com.google.crypto.tink.subtle.PrfHmacJce.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Mac initialValue() {
            try {
                Mac engineFactory = EngineFactory.MAC.getInstance(PrfHmacJce.this.algorithm);
                engineFactory.init(PrfHmacJce.this.key);
                return engineFactory;
            } catch (GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        }
    };
    private final int maxOutputLength;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public PrfHmacJce(String algorithm, Key key) throws GeneralSecurityException {
        this.algorithm = algorithm;
        this.key = key;
        if (key.getEncoded().length < 16) {
            throw new InvalidAlgorithmParameterException("key size too small, need at least 16 bytes");
        }
        byte b = -1;
        switch (algorithm.hashCode()) {
            case -1823053428:
                if (algorithm.equals("HMACSHA1")) {
                    b = 0;
                }
                break;
            case 392315118:
                if (algorithm.equals("HMACSHA256")) {
                    b = 1;
                }
                break;
            case 392316170:
                if (algorithm.equals("HMACSHA384")) {
                    b = 2;
                }
                break;
            case 392317873:
                if (algorithm.equals("HMACSHA512")) {
                    b = 3;
                }
                break;
        }
        if (b == 0) {
            this.maxOutputLength = 20;
        } else if (b == 1) {
            this.maxOutputLength = 32;
        } else if (b == 2) {
            this.maxOutputLength = 48;
        } else if (b == 3) {
            this.maxOutputLength = 64;
        } else {
            throw new NoSuchAlgorithmException("unknown Hmac algorithm: " + algorithm);
        }
        this.localMac.get();
    }

    @Override // com.google.crypto.tink.prf.Prf
    public byte[] compute(byte[] data, int outputLength) throws GeneralSecurityException {
        if (outputLength > this.maxOutputLength) {
            throw new InvalidAlgorithmParameterException("tag size too big");
        }
        this.localMac.get().update(data);
        return Arrays.copyOf(this.localMac.get().doFinal(), outputLength);
    }

    public int getMaxOutputLength() {
        return this.maxOutputLength;
    }
}
