package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdti {
    private static final CopyOnWriteArrayList<zzdth> zza = new CopyOnWriteArrayList<>();

    public static zzdth zza(String str) throws GeneralSecurityException {
        for (zzdth zzdthVar : zza) {
            if (zzdthVar.zza(str)) {
                return zzdthVar;
            }
        }
        String strValueOf = String.valueOf(str);
        throw new GeneralSecurityException(strValueOf.length() != 0 ? "No KMS client does support: ".concat(strValueOf) : new String("No KMS client does support: "));
    }
}
