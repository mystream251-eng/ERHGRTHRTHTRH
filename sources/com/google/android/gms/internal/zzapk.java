package com.google.android.gms.internal;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzapk extends zzapm {
    public zzapk(zzaof zzaofVar, boolean z) {
        super(zzaofVar, z);
    }

    @Override // com.google.android.gms.internal.zzaog, android.webkit.WebViewClient
    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zza(webView, str, (Map<String, String>) null);
    }
}
