package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdyb implements zzdxy<KeyFactory> {
    @Override // com.google.android.gms.internal.zzdxy
    public final /* synthetic */ KeyFactory zza(String str, Provider provider) throws GeneralSecurityException {
        return provider == null ? KeyFactory.getInstance(str) : KeyFactory.getInstance(str, provider);
    }
}
