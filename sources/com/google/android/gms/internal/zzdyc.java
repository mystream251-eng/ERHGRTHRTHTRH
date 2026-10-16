package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.security.KeyPairGenerator;
import java.security.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdyc implements zzdxy<KeyPairGenerator> {
    @Override // com.google.android.gms.internal.zzdxy
    public final /* synthetic */ KeyPairGenerator zza(String str, Provider provider) throws GeneralSecurityException {
        return provider == null ? KeyPairGenerator.getInstance(str) : KeyPairGenerator.getInstance(str, provider);
    }
}
