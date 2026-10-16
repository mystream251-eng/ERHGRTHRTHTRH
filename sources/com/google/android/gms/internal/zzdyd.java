package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.security.Provider;
import javax.crypto.Mac;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdyd implements zzdxy<Mac> {
    @Override // com.google.android.gms.internal.zzdxy
    public final /* synthetic */ Mac zza(String str, Provider provider) throws GeneralSecurityException {
        return provider == null ? Mac.getInstance(str) : Mac.getInstance(str, provider);
    }
}
