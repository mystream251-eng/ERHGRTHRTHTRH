package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.gms.ads.internal.gmsg.zza;
import com.google.android.gms.ads.internal.gmsg.zzaa;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.gmsg.zzx;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzw;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzaog extends WebViewClient implements zzapu {
    private static final String[] zzb = {"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzc = {"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    protected zzagq zza;
    private boolean zzaa;
    private int zzab;
    private View.OnAttachStateChangeListener zzac;
    private zzaof zzd;
    private final HashMap<String, List<zzt<? super zzaof>>> zze;
    private final Object zzf;
    private zzkf zzg;
    private zzn zzh;
    private zzapv zzi;
    private zzapw zzj;
    private zzb zzk;
    private zzapx zzl;
    private boolean zzm;
    private zzx zzn;
    private boolean zzo;
    private boolean zzp;
    private ViewTreeObserver.OnGlobalLayoutListener zzq;
    private ViewTreeObserver.OnScrollChangedListener zzr;
    private boolean zzs;
    private com.google.android.gms.ads.internal.overlay.zzt zzt;
    private final zzym zzu;
    private zzw zzv;
    private zzyd zzw;
    private zzyo zzx;
    private zzapy zzy;
    private boolean zzz;

    public zzaog(zzaof zzaofVar, boolean z) {
        this(zzaofVar, z, new zzym(zzaofVar, zzaofVar.zzq(), new zznu(zzaofVar.getContext())), null);
    }

    private zzaog(zzaof zzaofVar, boolean z, zzym zzymVar, zzyd zzydVar) {
        this.zze = new HashMap<>();
        this.zzf = new Object();
        this.zzm = false;
        this.zzd = zzaofVar;
        this.zzo = z;
        this.zzu = zzymVar;
        this.zzw = null;
    }

    private final WebResourceResponse zza(String str) throws IOException {
        URL url = new URL(str);
        int i = 0;
        while (true) {
            i++;
            if (i > 20) {
                StringBuilder sb = new StringBuilder(32);
                sb.append("Too many redirects (20)");
                throw new IOException(sb.toString());
            }
            URLConnection uRLConnectionOpenConnection = url.openConnection();
            uRLConnectionOpenConnection.setConnectTimeout(10000);
            uRLConnectionOpenConnection.setReadTimeout(10000);
            if (!(uRLConnectionOpenConnection instanceof HttpURLConnection)) {
                throw new IOException("Invalid protocol.");
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            zzbt.zze().zza(this.zzd.getContext(), this.zzd.zzi().zza, false, httpURLConnection);
            zzaks zzaksVar = new zzaks();
            zzaksVar.zza(httpURLConnection, (byte[]) null);
            int responseCode = httpURLConnection.getResponseCode();
            zzbt.zze();
            String strZzf = zzaij.zzf(httpURLConnection.getContentType());
            zzbt.zze();
            String strZzg = zzaij.zzg(httpURLConnection.getContentType());
            zzaksVar.zza(httpURLConnection, responseCode);
            if (responseCode < 300 || responseCode >= 400) {
                return new WebResourceResponse(strZzf, strZzg, httpURLConnection.getInputStream());
            }
            String headerField = httpURLConnection.getHeaderField("Location");
            if (headerField == null) {
                throw new IOException("Missing Location header in redirect");
            }
            URL url2 = new URL(url, headerField);
            String protocol = url2.getProtocol();
            if (protocol == null) {
                zzahw.zze("Protocol is null");
                return null;
            }
            if (!protocol.equals("http") && !protocol.equals("https")) {
                String strValueOf = String.valueOf(protocol);
                zzahw.zze(strValueOf.length() != 0 ? "Unsupported scheme: ".concat(strValueOf) : new String("Unsupported scheme: "));
                return null;
            }
            String strValueOf2 = String.valueOf(headerField);
            zzahw.zzb(strValueOf2.length() != 0 ? "Redirecting to ".concat(strValueOf2) : new String("Redirecting to "));
            httpURLConnection.disconnect();
            url = url2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0037  */
    private final void zza(Context context, String str, String str2, String str3) {
        String host;
        if (((Boolean) zzlc.zzf().zza(zzoi.zzdk)).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompat.CATEGORY_ERROR, str);
            bundle.putString("code", str2);
            if (TextUtils.isEmpty(str3)) {
                host = "";
            } else {
                Uri uri = Uri.parse(str3);
                if (uri.getHost() != null) {
                    host = uri.getHost();
                } else {
                    host = "";
                }
            }
            bundle.putString("host", host);
            zzbt.zze().zza(context, this.zzd.zzi().zza, "gmob-apps", bundle, true);
        }
    }

    private final void zza(Uri uri) {
        String path = uri.getPath();
        List<zzt<? super zzaof>> list = this.zze.get(path);
        if (list == null) {
            String strValueOf = String.valueOf(uri);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 32);
            sb.append("No GMSG handler found for GMSG: ");
            sb.append(strValueOf);
            zzahw.zza(sb.toString());
            return;
        }
        zzbt.zze();
        Map<String, String> mapZza = zzaij.zza(uri);
        if (zzahw.zza(2)) {
            String strValueOf2 = String.valueOf(path);
            zzahw.zza(strValueOf2.length() != 0 ? "Received GMSG: ".concat(strValueOf2) : new String("Received GMSG: "));
            for (String str : mapZza.keySet()) {
                String str2 = mapZza.get(str);
                StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 4 + String.valueOf(str2).length());
                sb2.append("  ");
                sb2.append(str);
                sb2.append(": ");
                sb2.append(str2);
                zzahw.zza(sb2.toString());
            }
        }
        Iterator<zzt<? super zzaof>> it = list.iterator();
        while (it.hasNext()) {
            it.next().zza(this.zzd, mapZza);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(View view, zzagq zzagqVar, int i) {
        if (!zzagqVar.zzb() || i <= 0) {
            return;
        }
        zzagqVar.zza(view);
        if (zzagqVar.zzb()) {
            zzaij.zza.postDelayed(new zzaoi(this, view, zzagqVar, i), 100L);
        }
    }

    private final void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        zzyd zzydVar = this.zzw;
        boolean zZza = zzydVar != null ? zzydVar.zza() : false;
        zzbt.zzc();
        zzl.zza(this.zzd.getContext(), adOverlayInfoParcel, !zZza);
        if (this.zza != null) {
            String str = adOverlayInfoParcel.zzl;
            if (str == null && adOverlayInfoParcel.zza != null) {
                str = adOverlayInfoParcel.zza.zza;
            }
            this.zza.zza(str);
        }
    }

    private final void zzo() {
        if (this.zzac == null) {
            return;
        }
        this.zzd.zzm().removeOnAttachStateChangeListener(this.zzac);
    }

    private final void zzp() {
        if (this.zzi != null && ((this.zzz && this.zzab <= 0) || this.zzaa)) {
            this.zzi.zza(this.zzd, !this.zzaa);
            this.zzi = null;
        }
        this.zzd.zzag();
    }

    @Override // android.webkit.WebViewClient
    public final void onLoadResource(WebView webView, String str) {
        String strValueOf = String.valueOf(str);
        zzahw.zza(strValueOf.length() != 0 ? "Loading resource: ".concat(strValueOf) : new String("Loading resource: "));
        Uri uri = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(uri.getScheme()) && "mobileads.google.com".equalsIgnoreCase(uri.getHost())) {
            zza(uri);
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        synchronized (this.zzf) {
            if (this.zzd.zzaa()) {
                zzahw.zza("Blank page loaded, 1...");
                this.zzd.zzab();
                return;
            }
            this.zzz = true;
            zzapw zzapwVar = this.zzj;
            if (zzapwVar != null) {
                zzapwVar.zza(this.zzd);
                this.zzj = null;
            }
            zzp();
        }
    }

    /* JADX WARN: Code duplicated, block: B:6:0x000d  */
    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        String strValueOf;
        if (i < 0) {
            int i2 = (-i) - 1;
            String[] strArr = zzb;
            if (i2 < strArr.length) {
                strValueOf = strArr[i2];
            } else {
                strValueOf = String.valueOf(i);
            }
        } else {
            strValueOf = String.valueOf(i);
        }
        zza(this.zzd.getContext(), "http_err", strValueOf, str2);
        super.onReceivedError(webView, i, str, str2);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0010  */
    @Override // android.webkit.WebViewClient
    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        String strValueOf;
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            if (primaryError >= 0) {
                String[] strArr = zzc;
                if (primaryError < strArr.length) {
                    strValueOf = strArr[primaryError];
                } else {
                    strValueOf = String.valueOf(primaryError);
                }
            } else {
                strValueOf = String.valueOf(primaryError);
            }
            zza(this.zzd.getContext(), "ssl_err", strValueOf, zzbt.zzg().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        zzik zzikVarZza;
        try {
            String strZza = zzagx.zza(str, this.zzd.getContext());
            if (!strZza.equals(str)) {
                return zza(strZza);
            }
            zzin zzinVarZza = zzin.zza(str);
            if (zzinVarZza != null && (zzikVarZza = zzbt.zzk().zza(zzinVarZza)) != null && zzikVarZza.zza()) {
                return new WebResourceResponse("", "", zzikVarZza.zzb());
            }
            if (zzaks.zzc()) {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzda)).booleanValue()) {
                    return zza(str);
                }
            }
            return null;
        } catch (Exception | NoClassDefFoundError e) {
            zzbt.zzi().zza(e, "AdWebViewClient.interceptRequest");
            return null;
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 79 || keyCode == 222) {
            return true;
        }
        switch (keyCode) {
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
                return true;
            default:
                switch (keyCode) {
                    case 126:
                    case 127:
                    case 128:
                    case TsExtractor.TS_STREAM_TYPE_AC3 /* 129 */:
                    case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                        return true;
                    default:
                        return false;
                }
        }
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) throws zzcw {
        String strValueOf = String.valueOf(str);
        zzahw.zza(strValueOf.length() != 0 ? "AdWebView shouldOverrideUrlLoading: ".concat(strValueOf) : new String("AdWebView shouldOverrideUrlLoading: "));
        Uri uriZza = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(uriZza.getScheme()) && "mobileads.google.com".equalsIgnoreCase(uriZza.getHost())) {
            zza(uriZza);
        } else {
            if (this.zzm && webView == this.zzd.zzl()) {
                String scheme = uriZza.getScheme();
                if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                    if (this.zzg != null) {
                        if (((Boolean) zzlc.zzf().zza(zzoi.zzbf)).booleanValue()) {
                            this.zzg.onAdClicked();
                            zzagq zzagqVar = this.zza;
                            if (zzagqVar != null) {
                                zzagqVar.zza(str);
                            }
                            this.zzg = null;
                        }
                    }
                    return super.shouldOverrideUrlLoading(webView, str);
                }
            }
            if (this.zzd.zzl().willNotDraw()) {
                String strValueOf2 = String.valueOf(str);
                zzahw.zze(strValueOf2.length() != 0 ? "AdWebView unable to handle URL: ".concat(strValueOf2) : new String("AdWebView unable to handle URL: "));
            } else {
                try {
                    zzcv zzcvVarZzx = this.zzd.zzx();
                    if (zzcvVarZzx != null && zzcvVarZzx.zza(uriZza)) {
                        uriZza = zzcvVarZzx.zza(uriZza, this.zzd.getContext(), this.zzd.zzm(), this.zzd.zzd());
                    }
                } catch (zzcw unused) {
                    String strValueOf3 = String.valueOf(str);
                    zzahw.zze(strValueOf3.length() != 0 ? "Unable to append parameter to URL: ".concat(strValueOf3) : new String("Unable to append parameter to URL: "));
                }
                zzw zzwVar = this.zzv;
                if (zzwVar == null || zzwVar.zzb()) {
                    zza(new zzc("android.intent.action.VIEW", uriZza.toString(), null, null, null, null, null));
                } else {
                    this.zzv.zza(str);
                }
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final zzw zza() {
        return this.zzv;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zza(int i, int i2) {
        zzyd zzydVar = this.zzw;
        if (zzydVar != null) {
            zzydVar.zza(i, i2);
        }
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zza(int i, int i2, boolean z) {
        this.zzu.zza(i, i2);
        zzyd zzydVar = this.zzw;
        if (zzydVar != null) {
            zzydVar.zza(i, i2, z);
        }
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zza(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        synchronized (this.zzf) {
            this.zzp = true;
            this.zzd.zzaf();
            this.zzq = onGlobalLayoutListener;
            this.zzr = onScrollChangedListener;
        }
    }

    public final void zza(zzc zzcVar) {
        boolean zZzy = this.zzd.zzy();
        zza(new AdOverlayInfoParcel(zzcVar, (!zZzy || this.zzd.zzt().zzd()) ? this.zzg : null, zZzy ? null : this.zzh, this.zzt, this.zzd.zzi()));
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zza(zzaof zzaofVar) {
        this.zzd = zzaofVar;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zza(zzapv zzapvVar) {
        this.zzi = zzapvVar;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zza(zzapw zzapwVar) {
        this.zzj = zzapwVar;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zza(zzapx zzapxVar) {
        this.zzl = zzapxVar;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zza(zzapy zzapyVar) {
        this.zzy = zzapyVar;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zza(zzkf zzkfVar, zzn zznVar, zzb zzbVar, com.google.android.gms.ads.internal.overlay.zzt zztVar, boolean z, zzx zzxVar, zzw zzwVar, zzyo zzyoVar, zzagq zzagqVar) {
        zzw zzwVar2 = zzwVar == null ? new zzw(this.zzd.getContext(), zzagqVar, null) : zzwVar;
        this.zzw = new zzyd(this.zzd, zzyoVar);
        this.zza = zzagqVar;
        zza("/appEvent", new zza(zzbVar));
        zza("/backButton", zzd.zzj);
        zza("/refresh", zzd.zzk);
        zza("/canOpenURLs", zzd.zza);
        zza("/canOpenIntents", zzd.zzb);
        zza("/click", zzd.zzc);
        zza("/close", zzd.zzd);
        zza("/customClose", zzd.zze);
        zza("/instrument", zzd.zzn);
        zza("/delayPageLoaded", zzd.zzp);
        zza("/delayPageClosed", zzd.zzq);
        zza("/getLocationInfo", zzd.zzr);
        zza("/httpTrack", zzd.zzf);
        zza("/log", zzd.zzg);
        zza("/mraid", new zzaa(zzwVar2, this.zzw, zzyoVar));
        zza("/mraidLoaded", this.zzu);
        zza("/open", new zzab(this.zzd.getContext(), this.zzd.zzi(), this.zzd.zzx(), zztVar, zzkfVar, zzbVar, zznVar, zzwVar2, this.zzw));
        zza("/precache", new zzaoc());
        zza("/touch", zzd.zzi);
        zza("/video", zzd.zzl);
        zza("/videoMeta", zzd.zzm);
        if (zzbt.zzaa().zza(this.zzd.getContext())) {
            zza("/logScionEvent", new zzz(this.zzd.getContext()));
        }
        if (zzxVar != null) {
            zza("/setInterstitialProperties", new com.google.android.gms.ads.internal.gmsg.zzw(zzxVar));
        }
        this.zzg = zzkfVar;
        this.zzh = zznVar;
        this.zzk = zzbVar;
        this.zzt = zztVar;
        this.zzv = zzwVar2;
        this.zzx = zzyoVar;
        this.zzn = zzxVar;
        this.zzm = z;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zza(String str, zzt<? super zzaof> zztVar) {
        synchronized (this.zzf) {
            List<zzt<? super zzaof>> copyOnWriteArrayList = this.zze.get(str);
            if (copyOnWriteArrayList == null) {
                copyOnWriteArrayList = new CopyOnWriteArrayList<>();
                this.zze.put(str, copyOnWriteArrayList);
            }
            copyOnWriteArrayList.add(zztVar);
        }
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zza(String str, com.google.android.gms.common.util.zzt<zzt<? super zzaof>> zztVar) {
        synchronized (this.zzf) {
            List<zzt<? super zzaof>> list = this.zze.get(str);
            if (list == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (zzt<? super zzaof> zztVar2 : list) {
                if (zztVar.zza(zztVar2)) {
                    arrayList.add(zztVar2);
                }
            }
            list.removeAll(arrayList);
        }
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zza(boolean z) {
        this.zzm = z;
    }

    public final void zza(boolean z, int i) {
        zzkf zzkfVar = (!this.zzd.zzy() || this.zzd.zzt().zzd()) ? this.zzg : null;
        zzn zznVar = this.zzh;
        com.google.android.gms.ads.internal.overlay.zzt zztVar = this.zzt;
        zzaof zzaofVar = this.zzd;
        zza(new AdOverlayInfoParcel(zzkfVar, zznVar, zztVar, zzaofVar, z, i, zzaofVar.zzi()));
    }

    public final void zza(boolean z, int i, String str) {
        boolean zZzy = this.zzd.zzy();
        zzkf zzkfVar = (!zZzy || this.zzd.zzt().zzd()) ? this.zzg : null;
        zzaok zzaokVar = zZzy ? null : new zzaok(this.zzd, this.zzh);
        zzb zzbVar = this.zzk;
        com.google.android.gms.ads.internal.overlay.zzt zztVar = this.zzt;
        zzaof zzaofVar = this.zzd;
        zza(new AdOverlayInfoParcel(zzkfVar, zzaokVar, zzbVar, zztVar, zzaofVar, z, i, str, zzaofVar.zzi()));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean zZzy = this.zzd.zzy();
        zzkf zzkfVar = (!zZzy || this.zzd.zzt().zzd()) ? this.zzg : null;
        zzaok zzaokVar = zZzy ? null : new zzaok(this.zzd, this.zzh);
        zzb zzbVar = this.zzk;
        com.google.android.gms.ads.internal.overlay.zzt zztVar = this.zzt;
        zzaof zzaofVar = this.zzd;
        zza(new AdOverlayInfoParcel(zzkfVar, zzaokVar, zzbVar, zztVar, zzaofVar, z, i, str, str2, zzaofVar.zzi()));
    }

    public final void zzb(String str, zzt<? super zzaof> zztVar) {
        synchronized (this.zzf) {
            List<zzt<? super zzaof>> list = this.zze.get(str);
            if (list == null) {
                return;
            }
            list.remove(zztVar);
        }
    }

    @Override // com.google.android.gms.internal.zzapu
    public final boolean zzb() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzo;
        }
        return z;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final boolean zzc() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzp;
        }
        return z;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final ViewTreeObserver.OnGlobalLayoutListener zzd() {
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
        synchronized (this.zzf) {
            onGlobalLayoutListener = this.zzq;
        }
        return onGlobalLayoutListener;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final ViewTreeObserver.OnScrollChangedListener zze() {
        ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
        synchronized (this.zzf) {
            onScrollChangedListener = this.zzr;
        }
        return onScrollChangedListener;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final boolean zzf() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzs;
        }
        return z;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zzg() {
        zzagq zzagqVar = this.zza;
        if (zzagqVar != null) {
            WebView webViewZzl = this.zzd.zzl();
            if (ViewCompat.isAttachedToWindow(webViewZzl)) {
                zza(webViewZzl, zzagqVar, 10);
                return;
            }
            zzo();
            this.zzac = new zzaoj(this, zzagqVar);
            this.zzd.zzm().addOnAttachStateChangeListener(this.zzac);
        }
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zzh() {
        synchronized (this.zzf) {
            this.zzs = true;
        }
        this.zzab++;
        zzp();
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zzi() {
        this.zzab--;
        zzp();
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zzj() {
        this.zzaa = true;
        zzp();
    }

    public final void zzk() {
        zzagq zzagqVar = this.zza;
        if (zzagqVar != null) {
            zzagqVar.zzd();
            this.zza = null;
        }
        zzo();
        synchronized (this.zzf) {
            this.zze.clear();
            this.zzg = null;
            this.zzh = null;
            this.zzi = null;
            this.zzj = null;
            this.zzk = null;
            this.zzm = false;
            this.zzo = false;
            this.zzp = false;
            this.zzs = false;
            this.zzt = null;
            this.zzl = null;
            if (this.zzw != null) {
                this.zzw.zza(true);
                this.zzw = null;
            }
        }
    }

    @Override // com.google.android.gms.internal.zzapu
    public final zzapy zzl() {
        return this.zzy;
    }

    @Override // com.google.android.gms.internal.zzapu
    public final void zzm() {
        synchronized (this.zzf) {
            this.zzm = false;
            this.zzo = true;
            zzaly.zza.execute(new Runnable(this) { // from class: com.google.android.gms.internal.zzaoh
                private final zzaog zza;

                {
                    this.zza = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zzn();
                }
            });
        }
    }

    final /* synthetic */ void zzn() {
        this.zzd.zzaf();
        com.google.android.gms.ads.internal.overlay.zzd zzdVarZzr = this.zzd.zzr();
        if (zzdVarZzr != null) {
            zzdVarZzr.zzm();
        }
        zzapx zzapxVar = this.zzl;
        if (zzapxVar != null) {
            zzapxVar.zza();
            this.zzl = null;
        }
    }
}
