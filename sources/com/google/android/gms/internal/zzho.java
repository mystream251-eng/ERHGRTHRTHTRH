package com.google.android.gms.internal;

import android.webkit.ValueCallback;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes2.dex */
final class zzho implements Runnable {
    final /* synthetic */ zzhg zza;
    final /* synthetic */ WebView zzb;
    final /* synthetic */ boolean zzc;
    final /* synthetic */ zzhm zzd;
    private ValueCallback<String> zze = new zzhp(this);

    zzho(zzhm zzhmVar, zzhg zzhgVar, WebView webView, boolean z) {
        this.zzd = zzhmVar;
        this.zza = zzhgVar;
        this.zzb = webView;
        this.zzc = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzb.getSettings().getJavaScriptEnabled()) {
            try {
                this.zzb.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zze);
            } catch (Throwable unused) {
                this.zze.onReceiveValue("");
            }
        }
    }
}
