package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzhx extends zzhs {
    private MessageDigest zzb;

    @Override // com.google.android.gms.internal.zzhs
    public final byte[] zza(String str) {
        byte[] bArr;
        byte[] bArrArray;
        String[] strArrSplit = str.split(" ");
        int length = 4;
        if (strArrSplit.length == 1) {
            int iZza = zzhw.zza(strArrSplit[0]);
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate.putInt(iZza);
            bArrArray = byteBufferAllocate.array();
        } else {
            if (strArrSplit.length < 5) {
                bArr = new byte[strArrSplit.length << 1];
                for (int i = 0; i < strArrSplit.length; i++) {
                    int iZza2 = zzhw.zza(strArrSplit[i]);
                    int i2 = (iZza2 >> 16) ^ (65535 & iZza2);
                    byte[] bArr2 = {(byte) i2, (byte) (i2 >> 8)};
                    int i3 = i << 1;
                    bArr[i3] = bArr2[0];
                    bArr[i3 + 1] = bArr2[1];
                }
            } else {
                bArr = new byte[strArrSplit.length];
                for (int i4 = 0; i4 < strArrSplit.length; i4++) {
                    int iZza3 = zzhw.zza(strArrSplit[i4]);
                    bArr[i4] = (byte) ((iZza3 >> 24) ^ (((iZza3 & 255) ^ ((iZza3 >> 8) & 255)) ^ ((iZza3 >> 16) & 255)));
                }
            }
            bArrArray = bArr;
        }
        this.zzb = zza();
        synchronized (this.zza) {
            if (this.zzb == null) {
                return new byte[0];
            }
            this.zzb.reset();
            this.zzb.update(bArrArray);
            byte[] bArrDigest = this.zzb.digest();
            if (bArrDigest.length <= 4) {
                length = bArrDigest.length;
            }
            byte[] bArr3 = new byte[length];
            System.arraycopy(bArrDigest, 0, bArr3, 0, length);
            return bArr3;
        }
    }
}
