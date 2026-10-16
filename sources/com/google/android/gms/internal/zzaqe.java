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
final class zzaqe extends zzaqh implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzaof {
    private final zzcv zza;
    private WeakReference<View.OnClickListener> zzaa;
    private zzd zzab;
    private boolean zzac;
    private zzakn zzad;
    private int zzae;
    private int zzaf;
    private int zzag;
    private int zzah;
    private float zzai;
    private Map<String, zzany> zzaj;
    private final WindowManager zzak;
    private final zzala zzb;
    private final zzbm zzc;
    private final zzv zzd;
    private zzaqb zze;
    private zzd zzf;
    private zzaqa zzg;
    private String zzh;
    private boolean zzi;
    private boolean zzj;
    private boolean zzk;
    private boolean zzl;
    private int zzm;
    private boolean zzn;
    private boolean zzo;
    private String zzp;
    private zzaou zzq;
    private boolean zzr;
    private boolean zzs;
    private zzpt zzt;
    private int zzu;
    private int zzv;
    private zzot zzw;
    private zzot zzx;
    private zzot zzy;
    private zzou zzz;

    private zzaqe(zzapz zzapzVar, zzaqa zzaqaVar, String str, boolean z, boolean z2, zzcv zzcvVar, zzala zzalaVar, zzov zzovVar, zzbm zzbmVar, zzv zzvVar, zziu zziuVar) {
        super(zzapzVar);
        this.zzn = true;
        this.zzo = false;
        this.zzp = "";
        this.zzae = -1;
        this.zzaf = -1;
        this.zzag = -1;
        this.zzah = -1;
        this.zzg = zzaqaVar;
        this.zzh = str;
        this.zzk = z;
        this.zzm = -1;
        this.zza = zzcvVar;
        this.zzb = zzalaVar;
        this.zzc = zzbmVar;
        this.zzd = zzvVar;
        this.zzak = (WindowManager) getContext().getSystemService("window");
        this.zzad = new zzakn(zzal().zza(), this, this, null);
        zzbt.zze().zza(zzapzVar, zzalaVar.zza, getSettings());
        setDownloadListener(this);
        this.zzai = zzal().getResources().getDisplayMetrics().density;
        zzaq();
        if (zzs.zzc()) {
            addJavascriptInterface(zzaox.zza(this), "googleAdsJsInterface");
        }
        zzav();
        zzou zzouVar = new zzou(new zzov(true, "make_wv", this.zzh));
        this.zzz = zzouVar;
        zzouVar.zza().zza(zzovVar);
        zzot zzotVarZza = zzoo.zza(this.zzz.zza());
        this.zzx = zzotVarZza;
        this.zzz.zza("native:view_create", zzotVarZza);
        this.zzy = null;
        this.zzw = null;
        zzbt.zzg().zzb(zzapzVar);
    }

    static zzaqe zza(Context context, zzaqa zzaqaVar, String str, boolean z, boolean z2, zzcv zzcvVar, zzala zzalaVar, zzov zzovVar, zzbm zzbmVar, zzv zzvVar, zziu zziuVar) {
        return new zzaqe(new zzapz(context), zzaqaVar, str, z, z2, zzcvVar, zzalaVar, zzovVar, zzbmVar, zzvVar, zziuVar);
    }

    private final boolean zzam() {
        int iZzb;
        int iZzb2;
        if (!this.zze.zzb() && !this.zze.zzc()) {
            return false;
        }
        zzbt.zze();
        DisplayMetrics displayMetricsZza = zzaij.zza(this.zzak);
        zzlc.zza();
        int iZzb3 = zzako.zzb(displayMetricsZza, displayMetricsZza.widthPixels);
        zzlc.zza();
        int iZzb4 = zzako.zzb(displayMetricsZza, displayMetricsZza.heightPixels);
        Activity activityZza = zzal().zza();
        if (activityZza == null || activityZza.getWindow() == null) {
            iZzb = iZzb3;
            iZzb2 = iZzb4;
        } else {
            zzbt.zze();
            int[] iArrZza = zzaij.zza(activityZza);
            zzlc.zza();
            iZzb = zzako.zzb(displayMetricsZza, iArrZza[0]);
            zzlc.zza();
            iZzb2 = zzako.zzb(displayMetricsZza, iArrZza[1]);
        }
        if (this.zzaf == iZzb3 && this.zzae == iZzb4 && this.zzag == iZzb && this.zzah == iZzb2) {
            return false;
        }
        boolean z = (this.zzaf == iZzb3 && this.zzae == iZzb4) ? false : true;
        this.zzaf = iZzb3;
        this.zzae = iZzb4;
        this.zzag = iZzb;
        this.zzah = iZzb2;
        new zzyn(this).zza(iZzb3, iZzb4, iZzb, iZzb2, displayMetricsZza.density, this.zzak.getDefaultDisplay().getRotation());
        return z;
    }

    private final void zzan() {
        zzoo.zza(this.zzz.zza(), this.zzx, "aeh2");
    }

    private final synchronized void zzaq() {
        if (!this.zzk && !this.zzg.zzd()) {
            if (Build.VERSION.SDK_INT < 18) {
                zzahw.zzb("Disabling hardware acceleration on an AdView.");
                zzar();
                return;
            } else {
                zzahw.zzb("Enabling hardware acceleration on an AdView.");
                zzas();
                return;
            }
        }
        zzahw.zzb("Enabling hardware acceleration on an overlay.");
        zzas();
    }

    private final synchronized void zzar() {
        if (!this.zzl) {
            zzbt.zzg().zzc((View) this);
        }
        this.zzl = true;
    }

    private final synchronized void zzas() {
        if (this.zzl) {
            zzbt.zzg().zzb((View) this);
        }
        this.zzl = false;
    }

    private final synchronized void zzat() {
        if (!this.zzac) {
            this.zzac = true;
            zzbt.zzi().zzj();
        }
    }

    private final synchronized void zzau() {
        this.zzaj = null;
    }

    private final void zzav() {
        zzov zzovVarZza;
        zzou zzouVar = this.zzz;
        if (zzouVar == null || (zzovVarZza = zzouVar.zza()) == null || zzbt.zzi().zzb() == null) {
            return;
        }
        zzbt.zzi().zzb().zza(zzovVarZza);
    }

    private final void zzf(boolean z) {
        HashMap map = new HashMap();
        map.put("isVisible", z ? LocalSearchProvider.LIVE_CONTENTS : LocalSearchProvider.NO_LIVE_CONTENTS);
        zza("onAdVisibilityChanged", map);
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.zzaof
    public final synchronized void destroy() {
        zzav();
        this.zzad.zzb();
        if (this.zzf != null) {
            this.zzf.zza();
            this.zzf.zzk();
            this.zzf = null;
        }
        zzaqb.zzk();
        if (this.zzj) {
            return;
        }
        zzbt.zzy();
        zzanx.zza(this);
        zzau();
        this.zzj = true;
        zzahw.zza("Initiating WebView self destruct sequence in 3...");
        zzahw.zza("Loading blank page in WebView, 2...");
        try {
            super.loadUrl("about:blank");
        } catch (UnsatisfiedLinkError e) {
            zzbt.zzi().zza(e, "AdWebViewImpl.loadUrlUnsafe");
            zzahw.zzc("Could not call loadUrl. ", e);
        }
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
                if (!this.zzj) {
                    zzaqb.zzk();
                    zzbt.zzy();
                    zzanx.zza(this);
                    zzau();
                    zzat();
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

    @Override // com.google.android.gms.internal.zzaqh, android.webkit.WebView, com.google.android.gms.internal.zzaof
    public final synchronized void loadUrl(String str) {
        if (zzaa()) {
            zzahw.zze("The webview is destroyed. Ignoring action.");
        } else {
            super.loadUrl(str);
        }
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    protected final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!zzaa()) {
            this.zzad.zzc();
        }
        boolean z = this.zzr;
        if (this.zze != null && this.zze.zzc()) {
            if (!this.zzs) {
                ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListenerZzd = this.zze.zzd();
                if (onGlobalLayoutListenerZzd != null) {
                    zzbt.zzz();
                    zzaml.zza(this, onGlobalLayoutListenerZzd);
                }
                ViewTreeObserver.OnScrollChangedListener onScrollChangedListenerZze = this.zze.zze();
                if (onScrollChangedListenerZze != null) {
                    zzbt.zzz();
                    zzaml.zza(this, onScrollChangedListenerZze);
                }
                this.zzs = true;
            }
            zzam();
            z = true;
        }
        zzf(z);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        synchronized (this) {
            if (!zzaa()) {
                this.zzad.zzd();
            }
            super.onDetachedFromWindow();
            if (this.zzs && this.zze != null && this.zze.zzc() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListenerZzd = this.zze.zzd();
                if (onGlobalLayoutListenerZzd != null) {
                    zzbt.zzg().zza(getViewTreeObserver(), onGlobalLayoutListenerZzd);
                }
                ViewTreeObserver.OnScrollChangedListener onScrollChangedListenerZze = this.zze.zze();
                if (onScrollChangedListenerZze != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(onScrollChangedListenerZze);
                }
                this.zzs = false;
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
        zzaqb zzaqbVar = this.zze;
        if (zzaqbVar == null || zzaqbVar.zzl() == null) {
            return;
        }
        this.zze.zzl().zza();
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
        boolean zZzam = zzam();
        zzd zzdVarZzr = zzr();
        if (zzdVarZzr == null || !zZzam) {
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
        if (!isInEditMode() && !this.zzk && !this.zzg.zze()) {
            if (this.zzg.zzf()) {
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
            if (this.zzg.zzc()) {
                if (!((Boolean) zzlc.zzf().zza(zzoi.zzfc)).booleanValue() && zzs.zzc()) {
                    zza("/contentHeight", new zzaqf(this));
                    zzb("(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();");
                    setMeasuredDimension(View.MeasureSpec.getSize(i), this.zzv != -1 ? (int) (this.zzv * this.zzai) : View.MeasureSpec.getSize(i2));
                    return;
                }
                super.onMeasure(i, i2);
                return;
            }
            if (this.zzg.zzd()) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                this.zzak.getDefaultDisplay().getMetrics(displayMetrics);
                setMeasuredDimension(displayMetrics.widthPixels, displayMetrics.heightPixels);
                return;
            }
            int mode = View.MeasureSpec.getMode(i);
            int size3 = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size4 = View.MeasureSpec.getSize(i2);
            int i5 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size3 : Integer.MAX_VALUE;
            int i6 = (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) ? size4 : Integer.MAX_VALUE;
            boolean z = true;
            boolean z2 = this.zzg.zzb > i5 || this.zzg.zza > i6;
            if (((Boolean) zzlc.zzf().zza(zzoi.zzgt)).booleanValue()) {
                if (this.zzg.zzb / this.zzai > i5 / this.zzai || this.zzg.zza / this.zzai > i6 / this.zzai) {
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
                setMeasuredDimension(this.zzg.zzb, this.zzg.zza);
                return;
            }
            int i7 = (int) (this.zzg.zzb / this.zzai);
            int i8 = (int) (this.zzg.zza / this.zzai);
            int i9 = (int) (size3 / this.zzai);
            int i10 = (int) (size4 / this.zzai);
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
        if (this.zze.zzc()) {
            synchronized (this) {
                if (this.zzt != null) {
                    this.zzt.zza(motionEvent);
                }
            }
        } else {
            zzcv zzcvVar = this.zza;
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
        this.zzaa = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.zzaof
    public final void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzaqb) {
            this.zze = (zzaqb) webViewClient;
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
            zzoo.zza(this.zzz.zza(), this.zzx, "aebb2");
        }
        zzan();
        if (this.zzz.zza() != null) {
            this.zzz.zza().zza("close_type", String.valueOf(i));
        }
        HashMap map = new HashMap(2);
        map.put("closetype", String.valueOf(i));
        map.put("version", this.zzb.zza);
        zza("onhide", map);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(Context context) {
        zzal().setBaseContext(context);
        this.zzad.zza(zzal().zza());
    }

    @Override // com.google.android.gms.internal.zzapo
    public final void zza(zzc zzcVar) {
        zzaqb.zza(zzcVar);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zza(zzd zzdVar) {
        this.zzf = zzdVar;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann
    public final synchronized void zza(zzaou zzaouVar) {
        if (this.zzq != null) {
            zzahw.zzc("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.zzq = zzaouVar;
        }
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zza(zzaqa zzaqaVar) {
        this.zzg = zzaqaVar;
        requestLayout();
    }

    @Override // com.google.android.gms.internal.zzgv
    public final void zza(zzgu zzguVar) {
        synchronized (this) {
            this.zzr = zzguVar.zza;
        }
        zzf(zzguVar.zza);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zza(zzpt zzptVar) {
        this.zzt = zzptVar;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zza(String str) {
        if (str == null) {
            str = "";
        }
        try {
            this.zzp = str;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(String str, zzt<? super zzaof> zztVar) {
        zzaqb zzaqbVar = this.zze;
        if (zzaqbVar != null) {
            zzaqbVar.zza(str, zztVar);
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
        zzb(sb.toString());
    }

    @Override // com.google.android.gms.internal.zzann
    public final void zza(boolean z) {
        this.zze.zza(z);
    }

    @Override // com.google.android.gms.internal.zzapo
    public final void zza(boolean z, int i) {
        zzaqb.zza(z, i);
    }

    @Override // com.google.android.gms.internal.zzapo
    public final void zza(boolean z, int i, String str) {
        zzaqb.zza(z, i, str);
    }

    @Override // com.google.android.gms.internal.zzapo
    public final void zza(boolean z, int i, String str, String str2) {
        zzaqb.zza(z, i, str, str2);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized boolean zzaa() {
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zzab() {
        zzahw.zza("Destroying WebView!");
        zzat();
        zzaij.zza.post(new zzaqg(this));
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized boolean zzac() {
        return this.zzn;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized boolean zzad() {
        return this.zzo;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized boolean zzae() {
        return this.zzu > 0;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzaf() {
        this.zzad.zza();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzag() {
        if (this.zzy == null) {
            zzot zzotVarZza = zzoo.zza(this.zzz.zza());
            this.zzy = zzotVarZza;
            this.zzz.zza("native:view_load", zzotVarZza);
        }
    }

    @Override // com.google.android.gms.internal.zzaof
    public final View.OnClickListener zzah() {
        return this.zzaa.get();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized zzpt zzai() {
        return this.zzt;
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
        this.zzo = true;
        if (this.zzc != null) {
            this.zzc.zzao();
        }
    }

    @Override // com.google.android.gms.ads.internal.zzbm
    public final synchronized void zzap() {
        this.zzo = false;
        if (this.zzc != null) {
            this.zzc.zzap();
        }
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann
    public final synchronized zzaou zzb() {
        return this.zzq;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zzb(int i) {
        this.zzm = i;
        if (this.zzf != null) {
            this.zzf.zza(i);
        }
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zzb(zzd zzdVar) {
        this.zzab = zzdVar;
    }

    @Override // com.google.android.gms.internal.zzaqh
    protected final synchronized void zzb(String str) {
        if (zzaa()) {
            zzahw.zze("The webview is destroyed. Ignoring action.");
        } else {
            super.zzb(str);
        }
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzb(String str, zzt<? super zzaof> zztVar) {
        if (this.zze != null) {
            zzaqb.zzb(str, zztVar);
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
        zzb(sb.toString());
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zzb(boolean z) {
        boolean z2 = z != this.zzk;
        this.zzk = z;
        zzaq();
        if (z2) {
            new zzyn(this).zzc(z ? AdViewState.EXPANDED : "default");
        }
    }

    @Override // com.google.android.gms.internal.zzann
    public final zzot zzc() {
        return this.zzx;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zzc(boolean z) {
        if (this.zzf != null) {
            this.zzf.zza(this.zze.zzb(), z);
        } else {
            this.zzi = z;
        }
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann, com.google.android.gms.internal.zzapa
    public final Activity zzd() {
        return zzal().zza();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zzd(boolean z) {
        this.zzn = z;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann
    public final zzv zze() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized void zze(boolean z) {
        int i = this.zzu + (z ? 1 : -1);
        this.zzu = i;
        if (i <= 0 && this.zzf != null) {
            this.zzf.zzq();
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
        return this.zzp;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann
    public final zzou zzh() {
        return this.zzz;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann, com.google.android.gms.internal.zzaps
    public final zzala zzi() {
        return this.zzb;
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
        zzan();
        HashMap map = new HashMap(1);
        map.put("version", this.zzb.zza);
        zza("onhide", map);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzo() {
        if (this.zzw == null) {
            zzoo.zza(this.zzz.zza(), this.zzx, "aes2");
            zzot zzotVarZza = zzoo.zza(this.zzz.zza());
            this.zzw = zzotVarZza;
            this.zzz.zza("native:view_show", zzotVarZza);
        }
        HashMap map = new HashMap(1);
        map.put("version", this.zzb.zza);
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
        return zzal().zzb();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized zzd zzr() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized zzd zzs() {
        return this.zzab;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzapq
    public final synchronized zzaqa zzt() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized String zzu() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final zzapu zzv() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized boolean zzw() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzapr
    public final zzcv zzx() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzapb
    public final synchronized boolean zzy() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final synchronized int zzz() {
        return this.zzm;
    }
}
