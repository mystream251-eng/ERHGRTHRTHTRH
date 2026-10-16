package com.google.android.gms.common;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzg extends zzd {
    private final byte[] zza;

    zzg(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.zza = bArr;
    }

    @Override // com.google.android.gms.common.zzd
    final byte[] zza() {
        return this.zza;
    }
}
