package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdxl extends zzdyn {
    public zzdxl(byte[] bArr) throws InvalidKeyException {
        super(bArr);
    }

    @Override // com.google.android.gms.internal.zzdyn
    final zzdym zza(byte[] bArr, int i) throws InvalidKeyException {
        return new zzdxj(bArr, i);
    }

    @Override // com.google.android.gms.internal.zzdyn, com.google.android.gms.internal.zzdsy
    public final /* bridge */ /* synthetic */ byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return super.zza(bArr, bArr2);
    }
}
