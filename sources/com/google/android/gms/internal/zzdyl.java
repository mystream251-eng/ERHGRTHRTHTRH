package com.google.android.gms.internal;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdyl {
    private static final SecureRandom zza = new SecureRandom();

    public static byte[] zza(int i) {
        byte[] bArr = new byte[i];
        zza.nextBytes(bArr);
        return bArr;
    }
}
