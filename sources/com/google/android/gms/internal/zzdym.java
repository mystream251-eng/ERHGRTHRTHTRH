package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* JADX INFO: loaded from: classes2.dex */
abstract class zzdym implements zzdyi {
    static final int[] zza = zza(ByteBuffer.wrap(new byte[]{101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107}));
    final zzdyh zzb;
    private final int zzc;

    zzdym(byte[] bArr, int i) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.zzb = zzdyh.zza(bArr);
        this.zzc = i;
    }

    static int zza(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    private final void zza(byte[] bArr, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws GeneralSecurityException {
        int iRemaining = byteBuffer2.remaining();
        int i = (iRemaining / 64) + 1;
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer byteBufferZzb = zzb(bArr, this.zzc + i2);
            if (i2 == i - 1) {
                zzdxi.zza(byteBuffer, byteBuffer2, byteBufferZzb, iRemaining % 64);
            } else {
                zzdxi.zza(byteBuffer, byteBuffer2, byteBufferZzb, 64);
            }
        }
    }

    static int[] zza(ByteBuffer byteBuffer) {
        IntBuffer intBufferAsIntBuffer = byteBuffer.order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] iArr = new int[intBufferAsIntBuffer.remaining()];
        intBufferAsIntBuffer.get(iArr);
        return iArr;
    }

    abstract int zza();

    final void zza(ByteBuffer byteBuffer, byte[] bArr) throws GeneralSecurityException {
        if (byteBuffer.remaining() - zza() < bArr.length) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        byte[] bArrZza = zzdyl.zza(zza());
        byteBuffer.put(bArrZza);
        zza(bArrZza, byteBuffer, ByteBuffer.wrap(bArr));
    }

    @Override // com.google.android.gms.internal.zzdyi
    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        zza();
        if (length > 2147483635) {
            throw new GeneralSecurityException("plaintext too long");
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(zza() + bArr.length);
        zza(byteBufferAllocate, bArr);
        return byteBufferAllocate.array();
    }

    abstract ByteBuffer zzb(byte[] bArr, int i);
}
