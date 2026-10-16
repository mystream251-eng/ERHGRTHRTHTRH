package com.google.android.gms.internal;

import android.webkit.ValueCallback;

/* JADX INFO: loaded from: classes2.dex */
final class zzhp implements ValueCallback<String> {
    private /* synthetic */ zzho zza;

    zzhp(zzho zzhoVar) {
        this.zza = zzhoVar;
    }

    @Override // android.webkit.ValueCallback
    public final /* synthetic */ void onReceiveValue(String str) {
        this.zza.zzd.zza(this.zza.zza, this.zza.zzb, str, this.zza.zzc);
    }
}
