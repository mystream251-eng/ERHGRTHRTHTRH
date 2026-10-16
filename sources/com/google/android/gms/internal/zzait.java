package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zzait implements Callable<Boolean> {
    private /* synthetic */ Context zza;
    private /* synthetic */ WebSettings zzb;

    zzait(zzais zzaisVar, Context context, WebSettings webSettings) {
        this.zza = context;
        this.zzb = webSettings;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Boolean call() throws Exception {
        if (this.zza.getCacheDir() != null) {
            this.zzb.setAppCachePath(this.zza.getCacheDir().getAbsolutePath());
            this.zzb.setAppCacheMaxSize(0L);
            this.zzb.setAppCacheEnabled(true);
        }
        this.zzb.setDatabasePath(this.zza.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
        this.zzb.setDatabaseEnabled(true);
        this.zzb.setDomStorageEnabled(true);
        this.zzb.setDisplayZoomControls(false);
        this.zzb.setBuiltInZoomControls(true);
        this.zzb.setSupportZoom(true);
        this.zzb.setAllowContentAccess(false);
        return true;
    }
}
