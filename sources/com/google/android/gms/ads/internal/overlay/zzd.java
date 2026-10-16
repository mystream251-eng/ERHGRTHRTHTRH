package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzaip;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzapu;
import com.google.android.gms.internal.zziu;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzyn;
import com.google.android.gms.internal.zzyr;
import com.viaccessorca.vodownloader.NanoHTTPD;
import java.util.Collections;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzd extends zzyr implements zzw {
    private static int zzd = Color.argb(0, 0, 0, 0);
    protected final Activity zza;
    AdOverlayInfoParcel zzb;
    private zzaof zze;
    private zzi zzf;
    private zzo zzg;
    private FrameLayout zzi;
    private WebChromeClient.CustomViewCallback zzj;
    private zzh zzm;
    private Runnable zzp;
    private boolean zzq;
    private boolean zzr;
    private boolean zzh = false;
    private boolean zzk = false;
    private boolean zzl = false;
    private boolean zzn = false;
    int zzc = 0;
    private final Object zzo = new Object();
    private boolean zzs = false;
    private boolean zzt = false;
    private boolean zzu = true;

    public zzd(Activity activity) {
        this.zza = activity;
    }

    private final void zza(boolean z) {
        int iIntValue = ((Integer) zzlc.zzf().zza(zzoi.zzgo)).intValue();
        zzp zzpVar = new zzp();
        zzpVar.zze = 50;
        zzpVar.zza = z ? iIntValue : 0;
        zzpVar.zzb = z ? 0 : iIntValue;
        zzpVar.zzc = 0;
        zzpVar.zzd = iIntValue;
        this.zzg = new zzo(this.zza, zzpVar, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        zza(z, this.zzb.zzg);
        this.zzm.addView(this.zzg, layoutParams);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x003e  */
    /* JADX WARN: Code duplicated, block: B:45:0x00c6  */
    private final void zzb(boolean z) throws zzg {
        boolean zZza;
        zzh zzhVar;
        int i;
        if (!this.zzr) {
            this.zza.requestWindowFeature(1);
        }
        Window window = this.zza.getWindow();
        if (window == null) {
            throw new zzg("Invalid activity, no window available.");
        }
        if (com.google.android.gms.common.util.zzs.zzh()) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzgm)).booleanValue()) {
                zzbt.zze();
                Activity activity = this.zza;
                zZza = zzaij.zza(activity, activity.getResources().getConfiguration());
            } else {
                zZza = true;
            }
        } else {
            zZza = true;
        }
        boolean z2 = false;
        boolean z3 = this.zzb.zzo != null && this.zzb.zzo.zzb;
        if ((!this.zzl || z3) && zZza) {
            window.setFlags(1024, 1024);
            if (((Boolean) zzlc.zzf().zza(zzoi.zzcj)).booleanValue() && com.google.android.gms.common.util.zzs.zze() && this.zzb.zzo != null && this.zzb.zzo.zzf) {
                window.getDecorView().setSystemUiVisibility(InputDeviceCompat.SOURCE_TOUCHSCREEN);
            }
        }
        zzapu zzapuVarZzv = this.zzb.zzd != null ? this.zzb.zzd.zzv() : null;
        boolean zZzb = zzapuVarZzv != null ? zzapuVarZzv.zzb() : false;
        this.zzn = false;
        if (zZzb) {
            if (this.zzb.zzj == zzbt.zzg().zza()) {
                if (this.zza.getResources().getConfiguration().orientation == 1) {
                    z2 = true;
                }
                this.zzn = z2;
            } else if (this.zzb.zzj == zzbt.zzg().zzb()) {
                if (this.zza.getResources().getConfiguration().orientation == 2) {
                    z2 = true;
                }
                this.zzn = z2;
            }
        }
        boolean z4 = this.zzn;
        StringBuilder sb = new StringBuilder(46);
        sb.append("Delay onShow to next orientation change: ");
        sb.append(z4);
        zzahw.zzb(sb.toString());
        zza(this.zzb.zzj);
        if (zzbt.zzg().zza(window)) {
            zzahw.zzb("Hardware acceleration on the AdActivity window enabled.");
        }
        if (this.zzl) {
            zzhVar = this.zzm;
            i = zzd;
        } else {
            zzhVar = this.zzm;
            i = ViewCompat.MEASURED_STATE_MASK;
        }
        zzhVar.setBackgroundColor(i);
        this.zza.setContentView(this.zzm);
        this.zzr = true;
        if (z) {
            try {
                zzaof zzaofVarZza = zzbt.zzf().zza(this.zza, this.zzb.zzd != null ? this.zzb.zzd.zzt() : null, this.zzb.zzd != null ? this.zzb.zzd.zzu() : null, true, zZzb, null, this.zzb.zzm, null, null, this.zzb.zzd != null ? this.zzb.zzd.zze() : null, zziu.zza());
                this.zze = zzaofVarZza;
                zzaofVarZza.zzv().zza(null, null, this.zzb.zze, this.zzb.zzi, true, null, this.zzb.zzd != null ? this.zzb.zzd.zzv().zza() : null, null, null);
                this.zze.zzv().zza(zze.zza);
                if (this.zzb.zzl != null) {
                    this.zze.loadUrl(this.zzb.zzl);
                } else {
                    if (this.zzb.zzh == null) {
                        throw new zzg("No URL or HTML to display in ad overlay.");
                    }
                    this.zze.loadDataWithBaseURL(this.zzb.zzf, this.zzb.zzh, NanoHTTPD.MIME_HTML, "UTF-8", null);
                }
                if (this.zzb.zzd != null) {
                    this.zzb.zzd.zzb(this);
                }
            } catch (Exception e) {
                zzahw.zzb("Error obtaining webview.", e);
                throw new zzg("Could not obtain webview for the overlay.");
            }
        } else {
            zzaof zzaofVar = this.zzb.zzd;
            this.zze = zzaofVar;
            zzaofVar.zza(this.zza);
        }
        this.zze.zza(this);
        ViewParent parent = this.zze.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.zze.zzm());
        }
        if (this.zzl) {
            this.zze.zzaj();
        }
        this.zzm.addView(this.zze.zzm(), -1, -1);
        if (!z && !this.zzn) {
            zzs();
        }
        zza(zZzb);
        if (this.zze.zzw()) {
            zza(zZzb, true);
        }
    }

    private final void zzr() {
        if (!this.zza.isFinishing() || this.zzs) {
            return;
        }
        this.zzs = true;
        zzaof zzaofVar = this.zze;
        if (zzaofVar != null) {
            zzaofVar.zza(this.zzc);
            synchronized (this.zzo) {
                if (!this.zzq && this.zze.zzae()) {
                    this.zzp = new zzf(this);
                    zzaij.zza.postDelayed(this.zzp, ((Long) zzlc.zzf().zza(zzoi.zzci)).longValue());
                    return;
                }
            }
        }
        zzn();
    }

    private final void zzs() {
        this.zze.zzo();
    }

    public final void zza() {
        this.zzc = 2;
        this.zza.finish();
    }

    public final void zza(int i) {
        if (this.zza.getApplicationInfo().targetSdkVersion >= ((Integer) zzlc.zzf().zza(zzoi.zzgz)).intValue()) {
            if (this.zza.getApplicationInfo().targetSdkVersion <= ((Integer) zzlc.zzf().zza(zzoi.zzha)).intValue()) {
                if (Build.VERSION.SDK_INT >= ((Integer) zzlc.zzf().zza(zzoi.zzhb)).intValue()) {
                    if (Build.VERSION.SDK_INT <= ((Integer) zzlc.zzf().zza(zzoi.zzhc)).intValue()) {
                        return;
                    }
                }
            }
        }
        this.zza.setRequestedOrientation(i);
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zza(int i, int i2, Intent intent) {
    }

    @Override // com.google.android.gms.internal.zzyq
    public void zza(Bundle bundle) {
        this.zza.requestWindowFeature(1);
        this.zzk = bundle != null ? bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false) : false;
        try {
            AdOverlayInfoParcel adOverlayInfoParcelZza = AdOverlayInfoParcel.zza(this.zza.getIntent());
            this.zzb = adOverlayInfoParcelZza;
            if (adOverlayInfoParcelZza == null) {
                throw new zzg("Could not get info for ad overlay.");
            }
            if (adOverlayInfoParcelZza.zzm.zzc > 7500000) {
                this.zzc = 3;
            }
            if (this.zza.getIntent() != null) {
                this.zzu = this.zza.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
            }
            if (this.zzb.zzo != null) {
                this.zzl = this.zzb.zzo.zza;
            } else {
                this.zzl = false;
            }
            if (((Boolean) zzlc.zzf().zza(zzoi.zzek)).booleanValue() && this.zzl && this.zzb.zzo.zze != -1) {
                new zzj(this, null).zzh();
            }
            if (bundle == null) {
                if (this.zzb.zzc != null && this.zzu) {
                    this.zzb.zzc.zzaa();
                }
                if (this.zzb.zzk != 1 && this.zzb.zzb != null) {
                    this.zzb.zzb.onAdClicked();
                }
            }
            zzh zzhVar = new zzh(this.zza, this.zzb.zzn, this.zzb.zzm.zza);
            this.zzm = zzhVar;
            zzhVar.setId(1000);
            int i = this.zzb.zzk;
            if (i == 1) {
                zzb(false);
                return;
            }
            if (i == 2) {
                this.zzf = new zzi(this.zzb.zzd);
                zzb(false);
            } else {
                if (i != 3) {
                    throw new zzg("Could not determine ad overlay type.");
                }
                zzb(true);
            }
        } catch (zzg e) {
            zzahw.zze(e.getMessage());
            this.zzc = 3;
            this.zza.finish();
        }
    }

    public final void zza(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        FrameLayout frameLayout = new FrameLayout(this.zza);
        this.zzi = frameLayout;
        frameLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.zzi.addView(view, -1, -1);
        this.zza.setContentView(this.zzi);
        this.zzr = true;
        this.zzj = customViewCallback;
        this.zzh = true;
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zza(IObjectWrapper iObjectWrapper) {
        if (((Boolean) zzlc.zzf().zza(zzoi.zzgm)).booleanValue() && com.google.android.gms.common.util.zzs.zzh()) {
            Configuration configuration = (Configuration) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper);
            zzbt.zze();
            if (zzaij.zza(this.zza, configuration)) {
                this.zza.getWindow().addFlags(1024);
                this.zza.getWindow().clearFlags(2048);
            } else {
                this.zza.getWindow().addFlags(2048);
                this.zza.getWindow().clearFlags(1024);
            }
        }
    }

    public final void zza(boolean z, boolean z2) {
        AdOverlayInfoParcel adOverlayInfoParcel;
        boolean z3 = ((Boolean) zzlc.zzf().zza(zzoi.zzck)).booleanValue() && (adOverlayInfoParcel = this.zzb) != null && adOverlayInfoParcel.zzo != null && this.zzb.zzo.zzg;
        if (z && z2 && z3) {
            new zzyn(this.zze, "useCustomClose").zza("Custom close has been disabled for interstitial ads in this ad slot.");
        }
        zzo zzoVar = this.zzg;
        if (zzoVar != null) {
            zzoVar.zza(z, z2 && !z3);
        }
    }

    public final void zzb() {
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzb;
        if (adOverlayInfoParcel != null && this.zzh) {
            zza(adOverlayInfoParcel.zzj);
        }
        if (this.zzi != null) {
            this.zza.setContentView(this.zzm);
            this.zzr = true;
            this.zzi.removeAllViews();
            this.zzi = null;
        }
        WebChromeClient.CustomViewCallback customViewCallback = this.zzj;
        if (customViewCallback != null) {
            customViewCallback.onCustomViewHidden();
            this.zzj = null;
        }
        this.zzh = false;
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzb(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzk);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzw
    public final void zzc() {
        this.zzc = 1;
        this.zza.finish();
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzd() {
        this.zzc = 0;
    }

    @Override // com.google.android.gms.internal.zzyq
    public final boolean zze() {
        this.zzc = 0;
        zzaof zzaofVar = this.zze;
        if (zzaofVar == null) {
            return true;
        }
        boolean zZzac = zzaofVar.zzac();
        if (!zZzac) {
            this.zze.zza("onbackblocked", Collections.emptyMap());
        }
        return zZzac;
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzf() {
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzg() {
        if (((Boolean) zzlc.zzf().zza(zzoi.zzgn)).booleanValue()) {
            zzaof zzaofVar = this.zze;
            if (zzaofVar == null || zzaofVar.zzaa()) {
                zzahw.zze("The webview does not exist. Ignoring action.");
            } else {
                zzbt.zzg();
                zzaip.zzb(this.zze);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzh() {
        if (this.zzb.zzc != null) {
            this.zzb.zzc.zzac();
        }
        if (((Boolean) zzlc.zzf().zza(zzoi.zzgn)).booleanValue()) {
            return;
        }
        zzaof zzaofVar = this.zze;
        if (zzaofVar == null || zzaofVar.zzaa()) {
            zzahw.zze("The webview does not exist. Ignoring action.");
        } else {
            zzbt.zzg();
            zzaip.zzb(this.zze);
        }
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzi() {
        zzb();
        if (this.zzb.zzc != null) {
            this.zzb.zzc.zzab();
        }
        if (!((Boolean) zzlc.zzf().zza(zzoi.zzgn)).booleanValue() && this.zze != null && (!this.zza.isFinishing() || this.zzf == null)) {
            zzbt.zzg();
            zzaip.zza(this.zze);
        }
        zzr();
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzj() {
        if (((Boolean) zzlc.zzf().zza(zzoi.zzgn)).booleanValue() && this.zze != null && (!this.zza.isFinishing() || this.zzf == null)) {
            zzbt.zzg();
            zzaip.zza(this.zze);
        }
        zzr();
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzk() {
        zzaof zzaofVar = this.zze;
        if (zzaofVar != null) {
            this.zzm.removeView(zzaofVar.zzm());
        }
        zzr();
    }

    @Override // com.google.android.gms.internal.zzyq
    public final void zzl() {
        this.zzr = true;
    }

    public final void zzm() {
        this.zzm.removeView(this.zzg);
        zza(true);
    }

    final void zzn() {
        if (this.zzt) {
            return;
        }
        this.zzt = true;
        zzaof zzaofVar = this.zze;
        if (zzaofVar != null) {
            this.zzm.removeView(zzaofVar.zzm());
            zzi zziVar = this.zzf;
            if (zziVar != null) {
                this.zze.zza(zziVar.zzd);
                this.zze.zzb(false);
                this.zzf.zzc.addView(this.zze.zzm(), this.zzf.zza, this.zzf.zzb);
                this.zzf = null;
            } else if (this.zza.getApplicationContext() != null) {
                this.zze.zza(this.zza.getApplicationContext());
            }
            this.zze = null;
        }
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzb;
        if (adOverlayInfoParcel == null || adOverlayInfoParcel.zzc == null) {
            return;
        }
        this.zzb.zzc.zzz();
    }

    public final void zzo() {
        if (this.zzn) {
            this.zzn = false;
            zzs();
        }
    }

    public final void zzp() {
        this.zzm.zza = true;
    }

    public final void zzq() {
        synchronized (this.zzo) {
            this.zzq = true;
            if (this.zzp != null) {
                zzaij.zza.removeCallbacks(this.zzp);
                zzaij.zza.post(this.zzp);
            }
        }
    }
}
