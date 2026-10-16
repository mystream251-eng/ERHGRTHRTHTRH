package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public final class zzcx {
    private static Cipher zzb;
    private static final Object zzc = new Object();
    private static final Object zzd = new Object();
    private final SecureRandom zza = null;

    public zzcx(SecureRandom secureRandom) {
    }

    private static Cipher zza() throws NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher;
        synchronized (zzd) {
            if (zzb == null) {
                zzb = Cipher.getInstance("AES/CBC/PKCS5Padding");
            }
            cipher = zzb;
        }
        return cipher;
    }

    public final String zza(byte[] bArr, byte[] bArr2) throws zzcy {
        byte[] bArrDoFinal;
        byte[] iv;
        if (bArr.length != 16) {
            throw new zzcy(this);
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            synchronized (zzc) {
                zza().init(1, secretKeySpec, (SecureRandom) null);
                bArrDoFinal = zza().doFinal(bArr2);
                iv = zza().getIV();
            }
            int length = bArrDoFinal.length + iv.length;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
            byteBufferAllocate.put(iv).put(bArrDoFinal);
            byteBufferAllocate.flip();
            byte[] bArr3 = new byte[length];
            byteBufferAllocate.get(bArr3);
            return zzbv.zza(bArr3, false);
        } catch (InvalidKeyException e) {
            throw new zzcy(this, e);
        } catch (NoSuchAlgorithmException e2) {
            throw new zzcy(this, e2);
        } catch (BadPaddingException e3) {
            throw new zzcy(this, e3);
        } catch (IllegalBlockSizeException e4) {
            throw new zzcy(this, e4);
        } catch (NoSuchPaddingException e5) {
            throw new zzcy(this, e5);
        }
    }

    public final byte[] zza(String str) throws zzcy {
        try {
            byte[] bArrZza = zzbv.zza(str, false);
            if (bArrZza.length != 32) {
                throw new zzcy(this);
            }
            byte[] bArr = new byte[16];
            ByteBuffer.wrap(bArrZza, 4, 16).get(bArr);
            for (int i = 0; i < 16; i++) {
                bArr[i] = (byte) (bArr[i] ^ 68);
            }
            return bArr;
        } catch (IllegalArgumentException e) {
            throw new zzcy(this, e);
        }
    }

    public final byte[] zza(byte[] bArr, String str) throws zzcy {
        byte[] bArrDoFinal;
        if (bArr.length != 16) {
            throw new zzcy(this);
        }
        try {
            byte[] bArrZza = zzbv.zza(str, false);
            if (bArrZza.length <= 16) {
                throw new zzcy(this);
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArrZza.length);
            byteBufferAllocate.put(bArrZza);
            byteBufferAllocate.flip();
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[bArrZza.length - 16];
            byteBufferAllocate.get(bArr2);
            byteBufferAllocate.get(bArr3);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            synchronized (zzc) {
                zza().init(2, secretKeySpec, new IvParameterSpec(bArr2));
                bArrDoFinal = zza().doFinal(bArr3);
            }
            return bArrDoFinal;
        } catch (IllegalArgumentException e) {
            throw new zzcy(this, e);
        } catch (InvalidAlgorithmParameterException e2) {
            throw new zzcy(this, e2);
        } catch (InvalidKeyException e3) {
            throw new zzcy(this, e3);
        } catch (NoSuchAlgorithmException e4) {
            throw new zzcy(this, e4);
        } catch (BadPaddingException e5) {
            throw new zzcy(this, e5);
        } catch (IllegalBlockSizeException e6) {
            throw new zzcy(this, e6);
        } catch (NoSuchPaddingException e7) {
            throw new zzcy(this, e7);
        }
    }
}
