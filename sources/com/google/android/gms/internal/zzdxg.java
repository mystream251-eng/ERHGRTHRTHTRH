package com.google.android.gms.internal;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdxg implements zzdsy {
    private final byte[] zza;
    private final byte[] zzb;
    private final SecretKeySpec zzc;
    private final int zzd;

    public zzdxg(byte[] bArr, int i) throws GeneralSecurityException {
        if (i != 12 && i != 16) {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
        this.zzd = i;
        this.zzc = new SecretKeySpec(bArr, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NOPADDING");
        cipher.init(1, this.zzc);
        byte[] bArrZza = zza(cipher.doFinal(new byte[16]));
        this.zza = bArrZza;
        this.zzb = zza(bArrZza);
    }

    private final byte[] zza(Cipher cipher, int i, byte[] bArr, int i2, int i3) throws BadPaddingException, IllegalBlockSizeException {
        byte[] bArrZzb;
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) i;
        if (i3 == 0) {
            return cipher.doFinal(zzb(bArr2, this.zza));
        }
        byte[] bArrDoFinal = cipher.doFinal(bArr2);
        int i4 = 0;
        while (i3 - i4 > 16) {
            for (int i5 = 0; i5 < 16; i5++) {
                bArrDoFinal[i5] = (byte) (bArrDoFinal[i5] ^ bArr[(i2 + i4) + i5]);
            }
            bArrDoFinal = cipher.doFinal(bArrDoFinal);
            i4 += 16;
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i4 + i2, i2 + i3);
        if (bArrCopyOfRange.length == 16) {
            bArrZzb = zzb(bArrCopyOfRange, this.zza);
        } else {
            byte[] bArrCopyOf = Arrays.copyOf(this.zzb, 16);
            for (int i6 = 0; i6 < bArrCopyOfRange.length; i6++) {
                bArrCopyOf[i6] = (byte) (bArrCopyOf[i6] ^ bArrCopyOfRange[i6]);
            }
            bArrCopyOf[bArrCopyOfRange.length] = (byte) (bArrCopyOf[bArrCopyOfRange.length] ^ ByteCompanionObject.MIN_VALUE);
            bArrZzb = bArrCopyOf;
        }
        return cipher.doFinal(zzb(bArrDoFinal, bArrZzb));
    }

    private static byte[] zza(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i = 0;
        while (i < 15) {
            int i2 = i + 1;
            bArr2[i] = (byte) ((bArr[i] << 1) ^ ((bArr[i2] & UByte.MAX_VALUE) >>> 7));
            i = i2;
        }
        bArr2[15] = (byte) ((bArr[15] << 1) ^ ((bArr[0] & ByteCompanionObject.MIN_VALUE) != 0 ? TsExtractor.TS_STREAM_TYPE_E_AC3 : 0));
        return bArr2;
    }

    private static byte[] zzb(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    @Override // com.google.android.gms.internal.zzdsy
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.zzd;
        if (length > (Integer.MAX_VALUE - i) - 16) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] bArr3 = new byte[bArr.length + i + 16];
        byte[] bArrZza = zzdyl.zza(i);
        System.arraycopy(bArrZza, 0, bArr3, 0, this.zzd);
        Cipher cipher = Cipher.getInstance("AES/ECB/NOPADDING");
        cipher.init(1, this.zzc);
        byte[] bArrZza2 = zza(cipher, 0, bArrZza, 0, bArrZza.length);
        byte[] bArrZza3 = zza(cipher, 1, bArr2, 0, bArr2.length);
        Cipher cipher2 = Cipher.getInstance("AES/CTR/NOPADDING");
        cipher2.init(1, this.zzc, new IvParameterSpec(bArrZza2));
        cipher2.doFinal(bArr, 0, bArr.length, bArr3, this.zzd);
        byte[] bArrZza4 = zza(cipher, 2, bArr3, this.zzd, bArr.length);
        int length2 = bArr.length + this.zzd;
        for (int i2 = 0; i2 < 16; i2++) {
            bArr3[length2 + i2] = (byte) ((bArrZza3[i2] ^ bArrZza2[i2]) ^ bArrZza4[i2]);
        }
        return bArr3;
    }
}
