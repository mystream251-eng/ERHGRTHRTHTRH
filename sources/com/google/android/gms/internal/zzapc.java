package com.google.android.gms.internal;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzw;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzapc extends WebChromeClient {
    private final zzaof zza;

    public zzapc(zzaof zzaofVar) {
        this.zza = zzaofVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Context zza(WebView webView) {
        if (!(webView instanceof zzaof)) {
            return webView.getContext();
        }
        zzaof zzaofVar = (zzaof) webView;
        Activity activityZzd = zzaofVar.zzd();
        return activityZzd != null ? activityZzd : zzaofVar.getContext();
    }

    private final boolean zza(Context context, String str, String str2, String str3, String str4, JsResult jsResult, JsPromptResult jsPromptResult, boolean z) {
        AlertDialog alertDialogCreate;
        zzw zzwVarZza;
        try {
            if (this.zza != null && this.zza.zzv() != null && this.zza.zzv().zza() != null && (zzwVarZza = this.zza.zzv().zza()) != null && !zzwVarZza.zzb()) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 11 + String.valueOf(str3).length());
                sb.append("window.");
                sb.append(str);
                sb.append("('");
                sb.append(str3);
                sb.append("')");
                zzwVarZza.zza(sb.toString());
                return false;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(str2);
            if (z) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                TextView textView = new TextView(context);
                textView.setText(str3);
                EditText editText = new EditText(context);
                editText.setText(str4);
                linearLayout.addView(textView);
                linearLayout.addView(editText);
                alertDialogCreate = builder.setView(linearLayout).setPositiveButton(R.string.ok, new zzapi(jsPromptResult, editText)).setNegativeButton(R.string.cancel, new zzaph(jsPromptResult)).setOnCancelListener(new zzapg(jsPromptResult)).create();
            } else {
                alertDialogCreate = builder.setMessage(str3).setPositiveButton(R.string.ok, new zzapf(jsResult)).setNegativeButton(R.string.cancel, new zzape(jsResult)).setOnCancelListener(new zzapd(jsResult)).create();
            }
            alertDialogCreate.show();
            return true;
        } catch (WindowManager.BadTokenException e) {
            zzahw.zzc("Fail to display Dialog.", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.webkit.WebChromeClient
    public final void onCloseWindow(WebView webView) {
        String str;
        if (webView instanceof zzaof) {
            zzd zzdVarZzr = ((zzaof) webView).zzr();
            if (zzdVarZzr != null) {
                zzdVarZzr.zza();
                return;
            }
            str = "Tried to close an AdWebView not associated with an overlay.";
        } else {
            str = "Tried to close a WebView that wasn't an AdWebView.";
        }
        zzahw.zze(str);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String strMessage = consoleMessage.message();
        String strSourceId = consoleMessage.sourceId();
        int iLineNumber = consoleMessage.lineNumber();
        StringBuilder sb = new StringBuilder(String.valueOf(strMessage).length() + 19 + String.valueOf(strSourceId).length());
        sb.append("JS: ");
        sb.append(strMessage);
        sb.append(" (");
        sb.append(strSourceId);
        sb.append(":");
        sb.append(iLineNumber);
        sb.append(")");
        String string = sb.toString();
        if (string.contains("Application Cache")) {
            return super.onConsoleMessage(consoleMessage);
        }
        int i = zzapj.zza[consoleMessage.messageLevel().ordinal()];
        if (i == 1) {
            zzahw.zzc(string);
        } else if (i == 2) {
            zzahw.zze(string);
        } else if (i == 3 || i == 4 || i != 5) {
            zzahw.zzd(string);
        } else {
            zzahw.zzb(string);
        }
        return super.onConsoleMessage(consoleMessage);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebView.WebViewTransport webViewTransport = (WebView.WebViewTransport) message.obj;
        WebView webView2 = new WebView(webView.getContext());
        if (this.zza.zzv() instanceof WebViewClient) {
            webView2.setWebViewClient((WebViewClient) this.zza.zzv());
        }
        webViewTransport.setWebView(webView2);
        message.sendToTarget();
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public final void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        long j4 = 5242880 - j3;
        if (j4 <= 0) {
            quotaUpdater.updateQuota(j);
            return;
        }
        if (j != 0) {
            if (j2 == 0) {
                j = Math.min(j + Math.min(PlaybackStateCompat.ACTION_PREPARE_FROM_URI, j4), PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
            } else if (j2 <= Math.min(PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED - j, j4)) {
                j += j2;
            }
            j2 = j;
        } else if (j2 > j4 || j2 > PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            j2 = 0;
        }
        quotaUpdater.updateQuota(j2);
    }

    /* JADX WARN: Code duplicated, block: B:9:0x003c  */
    @Override // android.webkit.WebChromeClient
    public final void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        boolean z;
        if (callback != null) {
            zzbt.zze();
            if (zzaij.zza(this.zza.getContext(), this.zza.getContext().getPackageName(), "android.permission.ACCESS_FINE_LOCATION")) {
                z = true;
            } else {
                zzbt.zze();
                if (zzaij.zza(this.zza.getContext(), this.zza.getContext().getPackageName(), "android.permission.ACCESS_COARSE_LOCATION")) {
                    z = true;
                } else {
                    z = false;
                }
            }
            callback.invoke(str, z, true);
        }
    }

    @Override // android.webkit.WebChromeClient
    public final void onHideCustomView() {
        zzd zzdVarZzr = this.zza.zzr();
        if (zzdVarZzr == null) {
            zzahw.zze("Could not get ad overlay when hiding custom view.");
        } else {
            zzdVarZzr.zzb();
        }
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "alert", str, str2, null, jsResult, null, false);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "onBeforeUnload", str, str2, null, jsResult, null, false);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "confirm", str, str2, null, jsResult, null, false);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return zza(zza(webView), "prompt", str, str2, str3, null, jsPromptResult, true);
    }

    public final void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        long j3 = 5242880 - j2;
        long j4 = j + PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        if (j3 < j4) {
            quotaUpdater.updateQuota(0L);
        } else {
            quotaUpdater.updateQuota(j4);
        }
    }

    @Override // android.webkit.WebChromeClient
    public final void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        zza(view, -1, customViewCallback);
    }

    protected final void zza(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        zzd zzdVarZzr = this.zza.zzr();
        if (zzdVarZzr == null) {
            zzahw.zze("Could not get ad overlay when showing custom view.");
            customViewCallback.onCustomViewHidden();
        } else {
            zzdVarZzr.zza(view, customViewCallback);
            zzdVarZzr.zza(i);
        }
    }
}
