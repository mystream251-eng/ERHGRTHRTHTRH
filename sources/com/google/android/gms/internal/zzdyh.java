package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdyh {
    private final byte[] zza;

    private zzdyh(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.zza = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, i2);
    }

    public static zzdyh zza(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return new zzdyh(bArr, 0, bArr.length);
    }

    public final byte[] zza() {
        byte[] bArr = this.zza;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }
}
