package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Signature;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdyf implements zzdxy<Signature> {
    @Override // com.google.android.gms.internal.zzdxy
    public final /* synthetic */ Signature zza(String str, Provider provider) throws GeneralSecurityException {
        return provider == null ? Signature.getInstance(str) : Signature.getInstance(str, provider);
    }
}
