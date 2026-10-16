package com.google.android.gms.internal;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaho {
    private BigInteger zza = BigInteger.ONE;

    public final synchronized String zza() {
        String string;
        string = this.zza.toString();
        this.zza = this.zza.add(BigInteger.ONE);
        return string;
    }
}
