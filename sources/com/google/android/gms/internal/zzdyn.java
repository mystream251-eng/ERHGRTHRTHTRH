package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* JADX INFO: loaded from: classes2.dex */
abstract class zzdyn implements zzdsy {
    private final byte[] zza;
    private final zzdym zzb;
    private final zzdym zzc;

    zzdyn(byte[] bArr) throws InvalidKeyException {
        this.zza = (byte[]) bArr.clone();
        this.zzb = zza(bArr, 1);
        this.zzc = zza(bArr, 0);
    }

    abstract zzdym zza(byte[] bArr, int i) throws InvalidKeyException;

    @Override // com.google.android.gms.internal.zzdsy
    public byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        this.zzb.zza();
        if (length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length + this.zzb.zza() + 16);
        if (byteBufferAllocate.remaining() < bArr.length + this.zzb.zza() + 16) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        int iPosition = byteBufferAllocate.position();
        this.zzb.zza(byteBufferAllocate, bArr);
        byteBufferAllocate.position(iPosition);
        byte[] bArr3 = new byte[this.zzb.zza()];
        byteBufferAllocate.get(bArr3);
        byteBufferAllocate.limit(byteBufferAllocate.limit() - 16);
        byte[] bArr4 = new byte[32];
        this.zzc.zzb(bArr3, 0).get(bArr4);
        int length2 = bArr2.length % 16 == 0 ? bArr2.length : (bArr2.length + 16) - (bArr2.length % 16);
        int iRemaining = byteBufferAllocate.remaining();
        int i = iRemaining % 16;
        int i2 = (i == 0 ? iRemaining : (iRemaining + 16) - i) + length2;
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(i2 + 16).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.put(bArr2);
        byteBufferOrder.position(length2);
        byteBufferOrder.put(byteBufferAllocate);
        byteBufferOrder.position(i2);
        byteBufferOrder.putLong(bArr2.length);
        byteBufferOrder.putLong(iRemaining);
        byte[] bArrZza = zzdyk.zza(bArr4, byteBufferOrder.array());
        byteBufferAllocate.limit(byteBufferAllocate.limit() + 16);
        byteBufferAllocate.put(bArrZza);
        return byteBufferAllocate.array();
    }
}
