package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.security.Provider;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdxz implements zzdxy<Cipher> {
    @Override // com.google.android.gms.internal.zzdxy
    public final /* synthetic */ Cipher zza(String str, Provider provider) throws GeneralSecurityException {
        return provider == null ? Cipher.getInstance(str) : Cipher.getInstance(str, provider);
    }
}
