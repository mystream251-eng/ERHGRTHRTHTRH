package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbm;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.zzs;
import com.viaccessorca.vodownloader.NanoHTTPD;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import tv.broadpeak.smartlib.ad.AdViewState;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzaor extends WebView implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzaof {
    private final zzapz zza;
    private zzot zzaa;
    private zzot zzab;
    private zzot zzac;
    private zzou zzad;
    private WeakReference<View.OnClickListener> zzae;
    private zzd zzaf;
    private boolean zzag;
    private zzakn zzah;
    private int zzai;
    private int zzaj;
    private int zzak;
    private int zzal;
    private Map<String, zzany> zzam;
    private final WindowManager zzan;
    private final zziu zzao;
    private final zzcv zzb;
    private final zzala zzc;
    private final zzbm zzd;
    private final zzv zze;
    private final DisplayMetrics zzf;
    private final float zzg;
    private zzaog zzh;
    private zzd zzi;
    private zzaqa zzj;
    private String zzk;
    private boolean zzl;
    private boolean zzm;
    private boolean zzn;
    private boolean zzo;
    private Boolean zzp;
    private int zzq;
    private boolean zzr;
    private boolean zzs;
    private String zzt;
    private zzaou zzu;
    private boolean zzv;
    private boolean zzw;
    private zzpt zzx;
    private int zzy;
    private int zzz;

    private zzaor(zzapz zzapzVar, zzaqa zzaqaVar, String str, boolean z, boolean z2, zzcv zzcvVar, zzala zzalaVar, zzov zzovVar, zzbm zzbmVar, zzv zzvVar, zziu zziuVar) {
        super(zzapzVar);
        this.zzr = true;
        this.zzs = false;
        this.zzt = "";
        this.zzai = -1;
        this.zzaj = -1;
        this.zzak = -1;
        this.zzal = -1;
        this.zza = zzapzVar;
        this.zzj = zzaqaVar;
        this.zzk = str;
        this.zzn = z;
        this.zzq = -1;
        this.zzb = zzcvVar;
        this.zzc = zzalaVar;
        this.zzd = zzbmVar;
        this.zze = zzvVar;
        this.zzan = (WindowManager) getContext().getSystemService("window");
        zzbt.zze();
        DisplayMetrics displayMetricsZza = zzaij.zza(this.zzan);
        this.zzf = displayMetricsZza;
        this.zzg = displayMetricsZza.density;
        this.zzao = zziuVar;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        try {
            settings.setJavaScriptEnabled(true);
        } catch (NullPointerException e) {
            zzahw.zzb("Unable to enable Javascript.", e);
        }
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzbt.zze().zza(zzapzVar, zzalaVar.zza, settings);
        zzbt.zzg().zza(getContext(), settings);
        setDownloadListener(this);
        zzar();
        if (zzs.zzc()) {
            addJavascriptInterface(zzaox.zza(this), "googleAdsJsInterface");
        }
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        this.zzah = new zzakn(this.zza.zza(), this, this, null);
        zzaw();
        zzou zzouVar = new zzou(new zzov(true, "make_wv", this.zzk));
        this.zzad = zzouVar;
        zzouVar.zza().zza(zzovVar);
        zzot zzotVarZza = zzoo.zza(this.zzad.zza());
        this.zzab = zzotVarZza;
        this.zzad.zza("native:view_create", zzotVarZza);
        this.zzac = null;
        this.zzaa = null;
        zzbt.zzg().zzb(zzapzVar);
    }

    static zzaor zza(Context context, zzaqa zzaqaVar, String str, boolean z, boolean z2, zzcv zzcvVar, zzala zzalaVar, zzov zzovVar, zzbm zzbmVar, zzv zzvVar, zziu zziuVar) {
        return new zzaor(new zzapz(context), zzaqaVar, str, z, z2, zzcvVar, zzalaVar, zzovVar, zzbmVar, zzvVar, zziuVar);
    }

    private final void zza(Boolean bool) {
        synchronized (this) {
            this.zzp = bool;
        }
        zzbt.zzi().zza(bool);
    }

    private final synchronized void zza(String str, ValueCallback<String> valueCallback) {
        if (zzaa()) {
            zzahw.zze("The webview is destroyed. Ignoring action.");
        } else {
            evaluateJavascript(str, null);
        }
    }

    private final boolean zzal() {
        int i;
        int iZzb;
        if (!this.zzh.zzb() && !this.zzh.zzc()) {
            return false;
        }
        zzlc.zza();
        DisplayMetrics displayMetrics = this.zzf;
        int iZzb2 = zzako.zzb(displayMetrics, displayMetrics.widthPixels);
        zzlc.zza();
        DisplayMetrics displayMetrics2 = this.zzf;
        int iZzb3 = zzako.zzb(displayMetrics2, displayMetrics2.heightPixels);
        Activity activityZza = this.zza.zza();
        if (activityZza == null || activityZza.getWindow() == null) {
            i = iZzb2;
            iZzb = iZzb3;
        } else {
            zzbt.zze();
            int[] iArrZza = zzaij.zza(activityZza);
            zzlc.zza();
            int iZzb4 = zzako.zzb(this.zzf, iArrZza[0]);
            zzlc.zza();
            iZzb = zzako.zzb(this.zzf, iArrZza[1]);
            i = iZzb4;
        }
        if (this.zzaj == iZzb2 && this.zzai == iZzb3 && this.zzak == i && this.zzal == iZzb) {
            return false;
        }
        boolean z = (this.zzaj == iZzb2 && this.zzai == iZzb3) ? false : true;
        this.zzaj = iZzb2;
        this.zzai = iZzb3;
        this.zzak = i;
        this.zzal = iZzb;
        new zzyn(this).zza(iZzb2, iZzb3, i, iZzb, this.zzf.density, this.zzan.getDefaultDisplay().getRotation());
        return z;
    }

    private final synchronized void zzam() {
        Boolean boolZzc = zzbt.zzi().zzc();
        this.zzp = boolZzc;
        if (boolZzc == null) {
            try {
                evaluateJavascript("(function(){})()", null);
                zza((Boolean) true);
            } catch (IllegalStateException unused) {
                zza((Boolean) false);
            }
        }
    }

    private final synchronized Boolean zzan() {
        return this.zzp;
    }

    private final void zzaq() {
        zzoo.zza(this.zzad.zza(), this.zzab, "aeh2");
    }

    private final synchronized void zzar() {
        if (!this.zzn && !this.zzj.zzd()) {
            if (Build.VERSION.SDK_INT < 18) {
                zzahw.zzb("Disabling hardware acceleration on an AdView.");
                zzas();
                return;
            } else {
                zzahw.zzb("Enabling hardware acceleration on an AdView.");
                zzat();
                return;
            }
        }
        zzahw.zzb("Enabling hardware acceleration on an overlay.");
        zzat();
    }

    private final synchronized void zzas() {
        if (!this.zzo) {
            zzbt.zzg().zzc((View) this);
        }
        this.zzo = true;
    }

    private final synchronized void zzat() {
        if (this.zzo) {
            zzbt.zzg().zzb((View) this);
        }
        this.zzo = false;
    }

    private final synchronized void zzau() {
        if (!this.zzag) {
            this.zzag = true;
            zzbt.zzi().zzj();
        }
    }

    private final synchronized void zzav() {
        this.zzam = null;
    }

    private final void zzaw() {
        zzov zzovVarZza;
        zzou zzouVar = this.zzad;
        if (zzouVar == null || (zzovVarZza = zzouVar.zza()) == null || zzbt.zzi().zzb() == null) {
            return;
        }
        zzbt.zzi().zzb().zza(zzovVarZza);
    }

    private final synchronized void zzb(String str) {
        if (zzaa()) {
            zzahw.zze("The webview is destroyed. Ignoring action.");
        } else {
            loadUrl(str);
        }
    }

    private final synchronized void zzc(String str) {
        try {
            super.loadUrl(str);
        } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError | UnsatisfiedLinkError e) {
            zzbt.zzi().zza(e, "AdWebViewImpl.loadUrlUnsafe");
            zzahw.zzc("Could not call loadUrl. ", e);
        }
    }

    private final void zzd(String str) {
        if (!zzs.zze()) {
            String strValueOf = String.valueOf(str);
            zzb(strValueOf.length() != 0 ? "javascript:".concat(strValueOf) : new String("javascript:"));
            return;
        }
        if (zzan() == null) {
            zzam();
        }
        if (zzan().booleanValue()) {
            zza(str, (ValueCallback<String>) null);
        } else {
            String strValueOf2 = String.valueOf(str);
            zzb(strValueOf2.length() != 0 ? "javascript:".concat(strValueOf2) : new String("javascript:"));
        }
    }

    private final void zzf(boolean z) {
        HashMap map = new HashMap();
        map.put("isVisible", z ? LocalSearchProvider.LIVE_CONTENTS : LocalSearchProvider.NO_LIVE_CONTENTS);
        zza("onAdVisibilityChanged", map);
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.zzaof
    public final synchronized void destroy() {
        zzaw();
        this.zzah.zzb();
        if (this.zzi != null) {
            this.zzi.zza();
            this.zzi.zzk();
            this.zzi = null;
        }
        this.zzh.zzk();
        if (this.zzm) {
            return;
        }
        zzbt.zzy();
        zzanx.zza(this);
        zzav();
        this.zzm = true;
        zzahw.zza("Initiating WebView self destruct sequence in 3...");
        zzahw.zza("Loading blank page in WebView, 2...");
        zzc("about:blank");
    }

    @Override // android.webkit.WebView
    public final synchronized void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (!zzaa()) {
            super.evaluateJavascript(str, valueCallback);
            return;
        }
        zzahw.zze("The webview is destroyed. Ignoring action.");
        if (valueCallback != null) {
            valueCallback.onReceiveValue(null);
        }
    }

    protected final void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.zzm) {
                    this.zzh.zzk();
                    zzbt.zzy();
                    zzanx.zza(this);
                    zzav();
                    zzau();
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.zzaof
    public final synchronized void loadData(String str, String str2, String str3) {
        if (zzaa()) {
            zzahw.zze("The webview is destroyed. Ignoring action.");
        } else {
            super.loadData(str, str2, str3);
        }
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.zzaof
    public final synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (zzaa()) {
            zzahw.zze("The webview is destroyed. Ignoring action.");
        } else {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        }
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.zzaof
    public final synchronized void loadUrl(String str) {
        if (zzaa()) {
            zzahw.zze("The webview is destroyed. Ignoring action.");
            return;
        }
        try {
            super.loadUrl(str);
        } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError e) {
            zzbt.zzi().zza(e, "AdWebViewImpl.loadUrl");
            zzahw.zzc("Could not call loadUrl. ", e);
        }
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    protected final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!zzaa()) {
            this.zzah.zzc();
        }
        boolean z = this.zzv;
        if (this.zzh != null && this.zzh.zzc()) {
            if (!this.zzw) {
                ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListenerZzd = this.zzh.zzd();
                if (onGlobalLayoutListenerZzd != null) {
                    zzbt.zzz();
                    zzaml.zza(this, onGlobalLayoutListenerZzd);
                }
                ViewTreeObserver.OnScrollChangedListener onScrollChangedListenerZze = this.zzh.zze();
                if (onScrollChangedListenerZze != null) {
                    zzbt.zzz();
                    zzaml.zza(this, onScrollChangedListenerZze);
                }
                this.zzw = true;
            }
            zzal();
            z = true;
        }
        zzf(z);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        synchronized (this) {
            if (!zzaa()) {
                this.zzah.zzd();
            }
            super.onDetachedFromWindow();
            if (this.zzw && this.zzh != null && this.zzh.zzc() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListenerZzd = this.zzh.zzd();
                if (onGlobalLayoutListenerZzd != null) {
                    zzbt.zzg().zza(getViewTreeObserver(), onGlobalLayoutListenerZzd);
                }
                ViewTreeObserver.OnScrollChangedListener onScrollChangedListenerZze = this.zzh.zze();
                if (onScrollChangedListenerZze != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(onScrollChangedListenerZze);
                }
                this.zzw = false;
            }
        }
        zzf(false);
    }

    @Override // android.webkit.DownloadListener
    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            zzbt.zze();
            zzaij.zza(getContext(), intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51 + String.valueOf(str4).length());
            sb.append("Couldn't find an Activity to view url/mimetype: ");
            sb.append(str);
            sb.append(" / ");
            sb.append(str4);
            zzahw.zzb(sb.toString());
        }
    }

    @Override // android.webkit.WebView, android.view.View
    protected final void onDraw(Canvas canvas) {
        if (zzaa()) {
            return;
        }
        if (Build.VERSION.SDK_INT == 21 && canvas.isHardwareAccelerated() && !isAttachedToWindow()) {
            return;
        }
        super.onDraw(canvas);
        zzaog zzaogVar = this.zzh;
        if (zzaogVar == null || zzaogVar.zzl() == null) {
            return;
        }
        this.zzh.zzl().zza();
    }

    @Override // android.webkit.WebView, android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (((Boolean) zzlc.zzf().zza(zzoi.zzbu)).booleanValue()) {
            float axisValue = motionEvent.getAxisValue(9);
            float axisValue2 = motionEvent.getAxisValue(10);
            if (motionEvent.getActionMasked() == 8) {
                if (axisValue > 0.0f && !canScrollVertically(-1)) {
                    return false;
                }
                if (axisValue < 0.0f && !canScrollVertically(1)) {
                    return false;
                }
                if (axisValue2 > 0.0f && !canScrollHorizontally(-1)) {
                    return false;
                }
                if (axisValue2 < 0.0f && !canScrollHorizontally(1)) {
                    return false;
                }
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        boolean zZzal = zzal();
        zzd zzdVarZzr = zzr();
        if (zzdVarZzr == null || !zZzal) {
            return;
        }
        zzdVarZzr.zzo();
    }

    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    protected final synchronized void onMeasure(int i, int i2) {
        if (zzaa()) {
            setMeasuredDimension(0, 0);
            return;
        }
        if (!isInEditMode() && !this.zzn && !this.zzj.zze()) {
            if (this.zzj.zzf()) {
                zzaou zzaouVarZzb = zzb();
                float fZzg = zzaouVarZzb != null ? zzaouVarZzb.zzg() : 0.0f;
                if (fZzg == 0.0f) {
                    super.onMeasure(i, i2);
                    return;
                }
                int size = View.MeasureSpec.getSize(i);
                int size2 = View.MeasureSpec.getSize(i2);
                int i3 = (int) (size2 * fZzg);
                int i4 = (int) (size / fZzg);
                if (size2 == 0 && i4 != 0) {
                    i3 = (int) (i4 * fZzg);
                    size2 = i4;
                } else if (size == 0 && i3 != 0) {
                    i4 = (int) (i3 / fZzg);
                    size = i3;
                }
                setMeasuredDimension(Math.min(i3, size), Math.min(i4, size2));
                return;
            }
            if (this.zzj.zzc()) {
                if (!((Boolean) zzlc.zzf().zza(zzoi.zzfc)).booleanValue() && zzs.zzc()) {
                    zza("/contentHeight", new zzaos(this));
                    zzd("(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();");
                    setMeasuredDimension(View.MeasureSpec.getSize(i), this.zzz != -1 ? (int) (this.zzz * this.zzf.density) : View.MeasureSpec.getSize(i2));
                    return;
                }
                super.onMeasure(i, i2);
                return;
            }
            if (this.zzj.zzd()) {
                setMeasuredDimension(this.zzf.widthPixels, this.zzf.heightPixels);
                return;
            }
            int mode = View.MeasureSpec.getMode(i);
            int size3 = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size4 = View.MeasureSpec.getSize(i2);
            int i5 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size3 : Integer.MAX_VALUE;
            int i6 = (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) ? size4 : Integer.MAX_VALUE;
            boolean z = true;
            boolean z2 = this.zzj.zzb > i5 || this.zzj.zza > i6;
            if (((Boolean) zzlc.zzf().zza(zzoi.zzgt)).booleanValue()) {
                if (this.zzj.zzb / this.zzg > i5 / this.zzg || this.zzj.zza / this.zzg > i6 / this.zzg) {
                    z = false;
                }
                if (z2) {
                    z2 = z;
                }
            }
            if (!z2) {
                if (getVisibility() != 8) {
                    setVisibility(0);
                }
                setMeasuredDimension(this.zzj.zzb, this.zzj.zza);
                return;
            }
            float f = this.zzf.density;
            int i7 = (int) (this.zzj.zzb / this.zzg);
            int i8 = (int) (this.zzj.zza / this.zzg);
            int i9 = (int) (size3 / this.zzg);
            int i10 = (int) (size4 / this.zzg);
            StringBuilder sb = new StringBuilder(103);
            sb.append("Not enough space to show ad. Needs ");
            sb.append(i7);
            sb.append("x");
            sb.append(i8);
            sb.append(" dp, but only has ");
            sb.append(i9);
            sb.append("x");
            sb.append(i10);
            sb.append(" dp.");
            zzahw.zze(sb.toString());
            if (getVisibility() != 8) {
                setVisibility(4);
            }
            setMeasuredDimension(0, 0);
            return;
        }
        super.onMeasure(i, i2);
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.zzaof
    public final void onPause() {
        if (zzaa()) {
            return;
        }
        try {
            super.onPause();
        } catch (Exception e) {
            zzahw.zzb("Could not pause webview.", e);
        }
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.zzaof
    public final void onResume() {
        if (zzaa()) {
            return;
        }
        try {
            super.onResume();
        } catch (Exception e) {
            zzahw.zzb("Could not resume webview.", e);
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.zzh.zzc()) {
            synchronized (this) {
                if (this.zzx != null) {
                    this.zzx.zza(motionEvent);
                }
            }
        } else {
            zzcv zzcvVar = this.zzb;
            if (zzcvVar != null) {
                zzcvVar.zza(motionEvent);
            }
        }
        if (zzaa()) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View, com.google.android.gms.internal.zzaof
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzae = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.zzaof
    public final void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzaog) {
            this.zzh = (zzaog) webViewClient;
        }
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.zzaof
    public final void stopLoading() {
        if (zzaa()) {
            return;
        }
        try {
            super.stopLoading();
        } catch (Exception e) {
            zzahw.zzb("Could not stop loading webview.", e);
        }
    }

    @Override // com.google.android.gms.internal.zzann
    public final zzane zza() {
        return null;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(int i) {
        if (i == 0) {
            zzoo.zza(this.zzad.zza(), this.zzab, "aebb2");
        }
        zzaq();
        if (this.zzad.zza() != null) {
            this.zzad.zza().zza("close_type", String.valueOf(i));
        }
        HashMap map = new HashMap(2);
        map.put("closetype", String.valueOf(i));
        map.put("version", this.zzc.zza);
        zza("onhide", map);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(Context context) {
        this.zza.setBaseContext(context);
        this.zzah.zza(this.zza.zza());
    }

    @Override // com.google.android.gms.internal.zzapo
    public final void zza(zzc zzcVar) {
        this.zzh.zza(zzcVar);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zza(zzd zzdVar) {
        this.zzi = zzdVar;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann
    public final synchronized void zza(zzaou zzaouVar) {
        if (this.zzu != null) {
            zzahw.zzc("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.zzu = zzaouVar;
        }
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zza(zzaqa zzaqaVar) {
        this.zzj = zzaqaVar;
        requestLayout();
    }

    @Override // com.google.android.gms.internal.zzgv
    public final void zza(zzgu zzguVar) {
        synchronized (this) {
            this.zzv = zzguVar.zza;
        }
        zzf(zzguVar.zza);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zza(zzpt zzptVar) {
        this.zzx = zzptVar;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zza(String str) {
        if (str == null) {
            str = "";
        }
        try {
            this.zzt = str;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(String str, zzt<? super zzaof> zztVar) {
        zzaog zzaogVar = this.zzh;
        if (zzaogVar != null) {
            zzaogVar.zza(str, zztVar);
        }
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zza(String str, String str2, String str3) {
        if (zzaa()) {
            zzahw.zze("The webview is destroyed. Ignoring action.");
            return;
        }
        if (((Boolean) zzlc.zzf().zza(zzoi.zzbw)).booleanValue()) {
            str2 = zzapp.zza(str2, zzapp.zza());
        }
        super.loadDataWithBaseURL(str, str2, NanoHTTPD.MIME_HTML, "UTF-8", str3);
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.ads.internal.js.zza
    public final void zza(String str, Map<String, ?> map) {
        try {
            zza(str, zzbt.zze().zza(map));
        } catch (JSONException unused) {
            zzahw.zze("Could not convert parameters to JSON.");
        }
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.ads.internal.js.zza
    public final void zza(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String string = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(string);
        sb.append(");");
        String strValueOf = String.valueOf(sb.toString());
        zzahw.zzb(strValueOf.length() != 0 ? "Dispatching AFMA event: ".concat(strValueOf) : new String("Dispatching AFMA event: "));
        zzd(sb.toString());
    }

    @Override // com.google.android.gms.internal.zzann
    public final void zza(boolean z) {
        this.zzh.zza(z);
    }

    @Override // com.google.android.gms.internal.zzapo
    public final void zza(boolean z, int i) {
        this.zzh.zza(z, i);
    }

    @Override // com.google.android.gms.internal.zzapo
    public final void zza(boolean z, int i, String str) {
        this.zzh.zza(z, i, str);
    }

    @Override // com.google.android.gms.internal.zzapo
    public final void zza(boolean z, int i, String str, String str2) {
        this.zzh.zza(z, i, str, str2);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized boolean zzaa() {
        return this.zzm;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zzab() {
        zzahw.zza("Destroying WebView!");
        zzau();
        zzaij.zza.post(new zzaot(this));
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized boolean zzac() {
        return this.zzr;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized boolean zzad() {
        return this.zzs;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized boolean zzae() {
        return this.zzy > 0;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzaf() {
        this.zzah.zza();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzag() {
        if (this.zzac == null) {
            zzot zzotVarZza = zzoo.zza(this.zzad.zza());
            this.zzac = zzotVarZza;
            this.zzad.zza("native:view_load", zzotVarZza);
        }
    }

    @Override // com.google.android.gms.internal.zzaof
    public final View.OnClickListener zzah() {
        return this.zzae.get();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized zzpt zzai() {
        return this.zzx;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzaj() {
        setBackgroundColor(0);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzak() {
        zzahw.zza("Cannot add text view to inner AdWebView");
    }

    @Override // com.google.android.gms.ads.internal.zzbm
    public final synchronized void zzao() {
        this.zzs = true;
        if (this.zzd != null) {
            this.zzd.zzao();
        }
    }

    @Override // com.google.android.gms.ads.internal.zzbm
    public final synchronized void zzap() {
        this.zzs = false;
        if (this.zzd != null) {
            this.zzd.zzap();
        }
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann
    public final synchronized zzaou zzb() {
        return this.zzu;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zzb(int i) {
        this.zzq = i;
        if (this.zzi != null) {
            this.zzi.zza(i);
        }
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zzb(zzd zzdVar) {
        this.zzaf = zzdVar;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzb(String str, zzt<? super zzaof> zztVar) {
        zzaog zzaogVar = this.zzh;
        if (zzaogVar != null) {
            zzaogVar.zzb(str, zztVar);
        }
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.ads.internal.js.zzm
    public final void zzb(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String string = jSONObject.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(string).length());
        sb.append(str);
        sb.append("(");
        sb.append(string);
        sb.append(");");
        zzd(sb.toString());
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zzb(boolean z) {
        boolean z2 = z != this.zzn;
        this.zzn = z;
        zzar();
        if (z2) {
            new zzyn(this).zzc(z ? AdViewState.EXPANDED : "default");
        }
    }

    @Override // com.google.android.gms.internal.zzann
    public final zzot zzc() {
        return this.zzab;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zzc(boolean z) {
        if (this.zzi != null) {
            this.zzi.zza(this.zzh.zzb(), z);
        } else {
            this.zzl = z;
        }
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann, com.google.android.gms.internal.zzapa
    public final Activity zzd() {
        return this.zza.zza();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zzd(boolean z) {
        this.zzr = z;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann
    public final zzv zze() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zze(boolean z) {
        int i = this.zzy + (z ? 1 : -1);
        this.zzy = i;
        if (i <= 0 && this.zzi != null) {
            this.zzi.zzq();
        }
    }

    @Override // com.google.android.gms.internal.zzann
    public final void zzf() {
        zzd zzdVarZzr = zzr();
        if (zzdVarZzr != null) {
            zzdVarZzr.zzp();
        }
    }

    @Override // com.google.android.gms.internal.zzann
    public final synchronized String zzg() {
        return this.zzt;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann
    public final zzou zzh() {
        return this.zzad;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann, com.google.android.gms.internal.zzaps
    public final zzala zzi() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.zzann
    public final int zzj() {
        return getMeasuredHeight();
    }

    @Override // com.google.android.gms.internal.zzann
    public final int zzk() {
        return getMeasuredWidth();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final WebView zzl() {
        return this;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzapt
    public final View zzm() {
        return this;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzn() {
        zzaq();
        HashMap map = new HashMap(1);
        map.put("version", this.zzc.zza);
        zza("onhide", map);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzo() {
        if (this.zzaa == null) {
            zzoo.zza(this.zzad.zza(), this.zzab, "aes2");
            zzot zzotVarZza = zzoo.zza(this.zzad.zza());
            this.zzaa = zzotVarZza;
            this.zzad.zza("native:view_show", zzotVarZza);
        }
        HashMap map = new HashMap(1);
        map.put("version", this.zzc.zza);
        zza("onshow", map);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzp() {
        HashMap map = new HashMap(3);
        map.put("app_muted", String.valueOf(zzbt.zzac().zzb()));
        map.put("app_volume", String.valueOf(zzbt.zzac().zza()));
        map.put("device_volume", String.valueOf(zzaja.zza(getContext())));
        zza("volume", map);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final Context zzq() {
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized zzd zzr() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized zzd zzs() {
        return this.zzaf;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzapq
    public final synchronized zzaqa zzt() {
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized String zzu() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final /* synthetic */ zzapu zzv() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized boolean zzw() {
        return this.zzl;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzapr
    public final zzcv zzx() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzapb
    public final synchronized boolean zzy() {
        return this.zzn;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized int zzz() {
        return this.zzq;
    }
}
