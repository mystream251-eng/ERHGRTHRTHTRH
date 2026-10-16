package com.google.android.gms.internal;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class zzais extends zzair {
    @Override // com.google.android.gms.internal.zzaip
    public zzaog zza(zzaof zzaofVar, boolean z) {
        return new zzapk(zzaofVar, z);
    }

    @Override // com.google.android.gms.internal.zzaip
    public final Set<String> zza(Uri uri) {
        return uri.getQueryParameterNames();
    }

    @Override // com.google.android.gms.internal.zzair, com.google.android.gms.internal.zzaip
    public final boolean zza(DownloadManager.Request request) {
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(1);
        return true;
    }

    @Override // com.google.android.gms.internal.zzaip
    public boolean zza(Context context, WebSettings webSettings) {
        super.zza(context, webSettings);
        return ((Boolean) zzakg.zza(context, new zzait(this, context, webSettings))).booleanValue();
    }

    @Override // com.google.android.gms.internal.zzaip
    public final boolean zza(Window window) {
        window.setFlags(16777216, 16777216);
        return true;
    }

    @Override // com.google.android.gms.internal.zzaip
    public final boolean zzb(View view) {
        view.setLayerType(0, null);
        return true;
    }

    @Override // com.google.android.gms.internal.zzaip
    public WebChromeClient zzc(zzaof zzaofVar) {
        return new zzapc(zzaofVar);
    }

    @Override // com.google.android.gms.internal.zzaip
    public final boolean zzc(View view) {
        view.setLayerType(1, null);
        return true;
    }
}
