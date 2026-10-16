package com.google.android.gms.internal;

import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzaqh extends WebView {
    private final zzapz zza;

    public zzaqh(zzapz zzapzVar) {
        super(zzapzVar);
        this.zza = zzapzVar;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzbt.zzg().zza(getContext(), settings);
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        try {
            getSettings().setJavaScriptEnabled(true);
        } catch (NullPointerException e) {
            zzahw.zzb("Unable to enable Javascript.", e);
        }
        setLayerType(1, null);
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.zzaof
    public void loadUrl(String str) {
        try {
            super.loadUrl(str);
        } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError e) {
            zzbt.zzi().zza(e, "CoreWebView.loadUrl");
            zzahw.zzc("Could not call loadUrl. ", e);
        }
    }

    protected final zzapz zzal() {
        return this.zza;
    }

    protected void zzb(String str) {
        zzaqi.zza(this, str);
    }
}
