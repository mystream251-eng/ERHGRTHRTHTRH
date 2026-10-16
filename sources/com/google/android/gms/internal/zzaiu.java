package com.google.android.gms.internal;

import android.net.http.SslError;
import android.webkit.WebChromeClient;

/* JADX INFO: loaded from: classes2.dex */
public class zzaiu extends zzais {
    @Override // com.google.android.gms.internal.zzaip
    public final String zza(SslError sslError) {
        return sslError.getUrl();
    }

    @Override // com.google.android.gms.internal.zzais, com.google.android.gms.internal.zzaip
    public final WebChromeClient zzc(zzaof zzaofVar) {
        return new zzapl(zzaofVar);
    }

    @Override // com.google.android.gms.internal.zzaip
    public int zzf() {
        return 1;
    }
}
