package com.google.android.gms.internal;

import android.webkit.WebView;
import com.google.android.gms.common.util.zzs;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzaqi {
    private static Boolean zza;

    private zzaqi() {
    }

    static void zza(WebView webView, String str) {
        if (zzs.zze() && zza(webView)) {
            webView.evaluateJavascript(str, null);
        } else {
            String strValueOf = String.valueOf(str);
            webView.loadUrl(strValueOf.length() != 0 ? "javascript:".concat(strValueOf) : new String("javascript:"));
        }
    }

    private static boolean zza(WebView webView) {
        boolean zBooleanValue;
        synchronized (zzaqi.class) {
            if (zza == null) {
                try {
                    webView.evaluateJavascript("(function(){})()", null);
                    zza = true;
                } catch (IllegalStateException unused) {
                    zza = false;
                }
                zBooleanValue = zza.booleanValue();
            } else {
                zBooleanValue = zza.booleanValue();
            }
            throw th;
        }
        return zBooleanValue;
    }
}
