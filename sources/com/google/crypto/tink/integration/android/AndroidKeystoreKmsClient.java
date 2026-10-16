package com.google.crypto.tink.integration.android;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import com.google.android.gms.stats.CodePackage;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KmsClient;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.KeyGenerator;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidKeystoreKmsClient implements KmsClient {
    public static final String PREFIX = "android-keystore://";
    private static final String TAG = AndroidKeystoreKmsClient.class.getSimpleName();
    private static final int WAIT_TIME_MILLISECONDS_BEFORE_RETRY = 20;
    private KeyStore keyStore;
    private final String keyUri;

    public AndroidKeystoreKmsClient() throws GeneralSecurityException {
        this(new Builder());
    }

    @Deprecated
    public AndroidKeystoreKmsClient(String uri) {
        this(new Builder().setKeyUri(uri));
    }

    private AndroidKeystoreKmsClient(Builder builder) {
        this.keyUri = builder.keyUri;
        this.keyStore = builder.keyStore;
    }

    public static final class Builder {
        KeyStore keyStore;
        String keyUri = null;

        public Builder() {
            this.keyStore = null;
            if (!AndroidKeystoreKmsClient.isAtLeastM()) {
                throw new IllegalStateException("need Android Keystore on Android M or newer");
            }
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.keyStore = keyStore;
                keyStore.load(null);
            } catch (IOException | GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        }

        public Builder setKeyUri(String val) {
            if (val == null || !val.toLowerCase(Locale.US).startsWith(AndroidKeystoreKmsClient.PREFIX)) {
                throw new IllegalArgumentException("val must start with android-keystore://");
            }
            this.keyUri = val;
            return this;
        }

        public Builder setKeyStore(KeyStore val) {
            if (val == null) {
                throw new IllegalArgumentException("val cannot be null");
            }
            this.keyStore = val;
            return this;
        }

        public AndroidKeystoreKmsClient build() {
            return new AndroidKeystoreKmsClient(this);
        }
    }

    @Override // com.google.crypto.tink.KmsClient
    public synchronized boolean doesSupport(String uri) {
        if (this.keyUri == null || !this.keyUri.equals(uri)) {
            return this.keyUri == null && uri.toLowerCase(Locale.US).startsWith(PREFIX);
        }
        return true;
    }

    @Override // com.google.crypto.tink.KmsClient
    public KmsClient withCredentials(String unused) throws GeneralSecurityException {
        return new AndroidKeystoreKmsClient();
    }

    @Override // com.google.crypto.tink.KmsClient
    public KmsClient withDefaultCredentials() throws GeneralSecurityException {
        return new AndroidKeystoreKmsClient();
    }

    @Override // com.google.crypto.tink.KmsClient
    public synchronized Aead getAead(String uri) throws GeneralSecurityException {
        if (this.keyUri != null && !this.keyUri.equals(uri)) {
            throw new GeneralSecurityException(String.format("this client is bound to %s, cannot load keys bound to %s", this.keyUri, uri));
        }
        return validateAead(new AndroidKeystoreAesGcm(Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, uri), this.keyStore));
    }

    public synchronized void deleteKey(String keyUri) throws GeneralSecurityException {
        this.keyStore.deleteEntry(Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, keyUri));
    }

    synchronized boolean hasKey(String keyUri) throws GeneralSecurityException {
        String strValidateKmsKeyUriAndRemovePrefix;
        strValidateKmsKeyUriAndRemovePrefix = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, keyUri);
        try {
        } catch (NullPointerException unused) {
            Log.w(TAG, "Keystore is temporarily unavailable, wait 20ms, reinitialize Keystore and try again.");
            try {
                Thread.sleep(20L);
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.keyStore = keyStore;
                keyStore.load(null);
            } catch (IOException e) {
                throw new GeneralSecurityException(e);
            } catch (InterruptedException unused2) {
            }
            return this.keyStore.containsAlias(strValidateKmsKeyUriAndRemovePrefix);
        }
        return this.keyStore.containsAlias(strValidateKmsKeyUriAndRemovePrefix);
    }

    public static Aead getOrGenerateNewAeadKey(String keyUri) throws GeneralSecurityException, IOException {
        AndroidKeystoreKmsClient androidKeystoreKmsClient = new AndroidKeystoreKmsClient();
        if (!androidKeystoreKmsClient.hasKey(keyUri)) {
            Log.w(TAG, String.format("key URI %s doesn't exist, generating a new one", keyUri));
            generateNewAeadKey(keyUri);
        }
        return androidKeystoreKmsClient.getAead(keyUri);
    }

    public static void generateNewAeadKey(String keyUri) throws GeneralSecurityException {
        if (new AndroidKeystoreKmsClient().hasKey(keyUri)) {
            throw new IllegalArgumentException(String.format("cannot generate a new key %s because it already exists; please delete it with deleteKey() and try again", keyUri));
        }
        String strValidateKmsKeyUriAndRemovePrefix = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, keyUri);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        keyGenerator.init(new KeyGenParameterSpec.Builder(strValidateKmsKeyUriAndRemovePrefix, 3).setKeySize(256).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").build());
        keyGenerator.generateKey();
    }

    private static Aead validateAead(Aead aead) throws GeneralSecurityException {
        byte[] bArrRandBytes = Random.randBytes(10);
        byte[] bArr = new byte[0];
        if (Arrays.equals(bArrRandBytes, aead.decrypt(aead.encrypt(bArrRandBytes, bArr), bArr))) {
            return aead;
        }
        throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isAtLeastM() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
