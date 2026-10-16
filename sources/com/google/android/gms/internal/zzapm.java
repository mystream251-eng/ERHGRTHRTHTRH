package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzbt;
import com.viaccessorca.drm.impl.JniCDxJavaHttpAgent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzapm extends zzaog {
    public zzapm(zzaof zzaofVar, boolean z) {
        super(zzaofVar, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final WebResourceResponse zza(WebView webView, String str, Map<String, String> map) {
        zzny<String> zznyVar;
        if (!(webView instanceof zzaof)) {
            zzahw.zze("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return null;
        }
        zzaof zzaofVar = (zzaof) webView;
        if (this.zza != null) {
            this.zza.zza(str, map, 1);
        }
        if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
            return super.shouldInterceptRequest(webView, str);
        }
        if (zzaofVar.zzv() != null) {
            zzaofVar.zzv().zzm();
        }
        if (zzaofVar.zzt().zzd()) {
            zznyVar = zzoi.zzag;
        } else {
            zznyVar = zzaofVar.zzy() ? zzoi.zzaf : zzoi.zzae;
        }
        String str2 = (String) zzlc.zzf().zza(zznyVar);
        try {
            Context context = zzaofVar.getContext();
            String str3 = zzaofVar.zzi().zza;
            HashMap map2 = new HashMap();
            map2.put(JniCDxJavaHttpAgent.DX_USER_AGENT_HDR, zzbt.zze().zza(context, str3));
            map2.put("Cache-Control", "max-stale=3600");
            String str4 = new zzajr(context).zza(str2, map2).get(60L, TimeUnit.SECONDS);
            if (str4 == null) {
                return null;
            }
            return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(str4.getBytes("UTF-8")));
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            String strValueOf = String.valueOf(e.getMessage());
            zzahw.zze(strValueOf.length() != 0 ? "Could not fetch MRAID JS. ".concat(strValueOf) : new String("Could not fetch MRAID JS. "));
            return null;
        }
    }
}
