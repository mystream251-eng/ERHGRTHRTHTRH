package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;

/* JADX INFO: loaded from: classes2.dex */
abstract class zzdxk extends zzdym {
    private static final byte[] zzc = new byte[16];

    zzdxk(byte[] bArr, int i) throws InvalidKeyException {
        super(bArr, i);
    }

    private static void zza(int[] iArr, int i, int i2, int i3, int i4) {
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = zza(iArr[i4] ^ iArr[i], 16);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = zza(iArr[i2] ^ iArr[i3], 12);
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = zza(iArr[i] ^ iArr[i4], 8);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = zza(iArr[i2] ^ iArr[i3], 7);
    }

    abstract int[] zza(byte[] bArr, int i);

    @Override // com.google.android.gms.internal.zzdym
    final ByteBuffer zzb(byte[] bArr, int i) {
        int[] iArrZza = zza(bArr, i);
        int[] iArr = (int[]) iArrZza.clone();
        for (int i2 = 0; i2 < 10; i2++) {
            zza(iArr, 0, 4, 8, 12);
            zza(iArr, 1, 5, 9, 13);
            zza(iArr, 2, 6, 10, 14);
            zza(iArr, 3, 7, 11, 15);
            zza(iArr, 0, 5, 10, 15);
            zza(iArr, 1, 6, 11, 12);
            zza(iArr, 2, 7, 8, 13);
            zza(iArr, 3, 4, 9, 14);
        }
        for (int i3 = 0; i3 < iArrZza.length; i3++) {
            iArrZza[i3] = iArrZza[i3] + iArr[i3];
        }
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.asIntBuffer().put(iArrZza, 0, 16);
        return byteBufferOrder;
    }
}
