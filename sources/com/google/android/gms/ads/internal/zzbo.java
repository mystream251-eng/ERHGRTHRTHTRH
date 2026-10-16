package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;

/* JADX INFO: loaded from: classes2.dex */
final class zzbo extends WebViewClient {
    private /* synthetic */ zzbn zza;

    zzbo(zzbn zzbnVar) {
        this.zza = zzbnVar;
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        if (this.zza.zzg != null) {
            try {
                this.zza.zzg.zza(0);
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdFailedToLoad().", e);
            }
        }
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith(this.zza.zzf())) {
            return false;
        }
        if (str.startsWith((String) zzlc.zzf().zza(zzoi.zzfk))) {
            if (this.zza.zzg != null) {
                try {
                    this.zza.zzg.zza(3);
                } catch (RemoteException e) {
                    zzahw.zzc("Could not call AdListener.onAdFailedToLoad().", e);
                }
            }
            this.zza.zza(0);
            return true;
        }
        if (str.startsWith((String) zzlc.zzf().zza(zzoi.zzfl))) {
            if (this.zza.zzg != null) {
                try {
                    this.zza.zzg.zza(0);
                } catch (RemoteException e2) {
                    zzahw.zzc("Could not call AdListener.onAdFailedToLoad().", e2);
                }
            }
            this.zza.zza(0);
            return true;
        }
        if (str.startsWith((String) zzlc.zzf().zza(zzoi.zzfm))) {
            if (this.zza.zzg != null) {
                try {
                    this.zza.zzg.zzc();
                } catch (RemoteException e3) {
                    zzahw.zzc("Could not call AdListener.onAdLoaded().", e3);
                }
            }
            this.zza.zza(this.zza.zzb(str));
            return true;
        }
        if (str.startsWith("gmsg://")) {
            return true;
        }
        if (this.zza.zzg != null) {
            try {
                this.zza.zzg.zzb();
            } catch (RemoteException e4) {
                zzahw.zzc("Could not call AdListener.onAdLeftApplication().", e4);
            }
        }
        this.zza.zzd(this.zza.zzc(str));
        return true;
    }
}
