package com.google.android.gms.internal;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzf;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzyd extends zzyn {
    private static Set<String> zza = zzf.zza(new String[]{"top-left", "top-right", "top-center", TtmlNode.CENTER, "bottom-left", "bottom-right", "bottom-center"});
    private String zzb;
    private boolean zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private final Object zzj;
    private final zzaof zzk;
    private final Activity zzl;
    private zzaqa zzm;
    private ImageView zzn;
    private LinearLayout zzo;
    private zzyo zzp;
    private PopupWindow zzq;
    private RelativeLayout zzr;
    private ViewGroup zzs;

    public zzyd(zzaof zzaofVar, zzyo zzyoVar) {
        super(zzaofVar, "resize");
        this.zzb = "top-right";
        this.zzc = true;
        this.zzd = 0;
        this.zze = 0;
        this.zzf = -1;
        this.zzg = 0;
        this.zzh = 0;
        this.zzi = -1;
        this.zzj = new Object();
        this.zzk = zzaofVar;
        this.zzl = zzaofVar.zzd();
        this.zzp = zzyoVar;
    }

    private final void zzb(int i, int i2) {
        zza(i, i2 - zzbt.zze().zzc(this.zzl)[0], this.zzi, this.zzf);
    }

    /* JADX WARN: Code duplicated, block: B:66:0x0102 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:68:0x0104  */
    /* JADX WARN: Code duplicated, block: B:70:0x0108  */
    /* JADX WARN: Code duplicated, block: B:72:0x0119  */
    /* JADX WARN: Code duplicated, block: B:74:0x013b  */
    /* JADX WARN: Code duplicated, block: B:75:0x013d  */
    /* JADX WARN: Code duplicated, block: B:77:0x0143  */
    /* JADX WARN: Code duplicated, block: B:80:0x0149  */
    /* JADX WARN: Code duplicated, block: B:81:0x014c  */
    /* JADX WARN: Code duplicated, block: B:83:0x0154  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final int[] zzb() {
        String str;
        boolean z;
        int[] iArrZzc;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int[] iArrZzb = zzbt.zze().zzb(this.zzl);
        int[] iArrZzc2 = zzbt.zze().zzc(this.zzl);
        int i8 = iArrZzb[0];
        int i9 = iArrZzb[1];
        int i10 = this.zzi;
        if (i10 < 50 || i10 > i8) {
            str = "Width is too small or too large.";
        } else {
            int i11 = this.zzf;
            if (i11 >= 50 && i11 <= i9) {
                if (i11 == i9 && i10 == i8) {
                    str = "Cannot resize to a full-screen ad.";
                } else {
                    if (this.zzc) {
                        String str2 = this.zzb;
                        byte b = -1;
                        switch (str2.hashCode()) {
                            case -1364013995:
                                if (str2.equals(TtmlNode.CENTER)) {
                                    b = 2;
                                }
                                break;
                            case -1012429441:
                                if (str2.equals("top-left")) {
                                    b = 0;
                                }
                                break;
                            case -655373719:
                                if (str2.equals("bottom-left")) {
                                    b = 3;
                                }
                                break;
                            case 1163912186:
                                if (str2.equals("bottom-right")) {
                                    b = 5;
                                }
                                break;
                            case 1288627767:
                                if (str2.equals("bottom-center")) {
                                    b = 4;
                                }
                                break;
                            case 1755462605:
                                if (str2.equals("top-center")) {
                                    b = 1;
                                }
                                break;
                        }
                        if (b == 0) {
                            i6 = this.zzd + this.zzg;
                        } else if (b != 1) {
                            if (b != 2) {
                                if (b != 3) {
                                    int i12 = this.zzd;
                                    if (b == 4) {
                                        i6 = ((i12 + this.zzg) + (this.zzi / 2)) - 25;
                                    } else if (b != 5) {
                                        i6 = ((i12 + this.zzg) + this.zzi) - 50;
                                    } else {
                                        i6 = ((i12 + this.zzg) + this.zzi) - 50;
                                    }
                                } else {
                                    i6 = this.zzd + this.zzg;
                                }
                                i7 = ((this.zze + this.zzh) + this.zzf) - 50;
                            } else {
                                i6 = ((this.zzd + this.zzg) + (this.zzi / 2)) - 25;
                                i7 = ((this.zze + this.zzh) + (this.zzf / 2)) - 25;
                            }
                            z = i6 < 0 && i6 + 50 <= i8 && i7 >= iArrZzc2[0] && i7 + 50 <= iArrZzc2[1];
                        } else {
                            i6 = ((this.zzd + this.zzg) + (this.zzi / 2)) - 25;
                        }
                        i7 = this.zze + this.zzh;
                        if (i6 < 0) {
                        }
                    }
                }
                if (!z) {
                    return null;
                }
                if (this.zzc) {
                    return new int[]{this.zzd + this.zzg, this.zze + this.zzh};
                }
                int[] iArrZzb2 = zzbt.zze().zzb(this.zzl);
                iArrZzc = zzbt.zze().zzc(this.zzl);
                i = iArrZzb2[0];
                i2 = this.zzd + this.zzg;
                i3 = this.zze + this.zzh;
                if (i2 < 0) {
                    i2 = 0;
                } else {
                    i4 = this.zzi;
                    if (i2 + i4 > i) {
                        i2 = i - i4;
                    }
                }
                if (i3 < iArrZzc[0]) {
                    i3 = iArrZzc[0];
                } else {
                    i5 = this.zzf;
                    if (i3 + i5 > iArrZzc[1]) {
                        i3 = iArrZzc[1] - i5;
                    }
                }
                return new int[]{i2, i3};
            }
            str = "Height is too small or too large.";
        }
        zzahw.zze(str);
        if (!z) {
            return null;
        }
        if (this.zzc) {
            return new int[]{this.zzd + this.zzg, this.zze + this.zzh};
        }
        int[] iArrZzb3 = zzbt.zze().zzb(this.zzl);
        iArrZzc = zzbt.zze().zzc(this.zzl);
        i = iArrZzb3[0];
        i2 = this.zzd + this.zzg;
        i3 = this.zze + this.zzh;
        if (i2 < 0) {
            i2 = 0;
        } else {
            i4 = this.zzi;
            if (i2 + i4 > i) {
                i2 = i - i4;
            }
        }
        if (i3 < iArrZzc[0]) {
            i3 = iArrZzc[0];
        } else {
            i5 = this.zzf;
            if (i3 + i5 > iArrZzc[1]) {
                i3 = iArrZzc[1] - i5;
            }
        }
        return new int[]{i2, i3};
    }

    public final void zza(int i, int i2) {
        this.zzd = i;
        this.zze = i2;
    }

    public final void zza(int i, int i2, boolean z) {
        synchronized (this.zzj) {
            this.zzd = i;
            this.zze = i2;
            if (this.zzq != null && z) {
                int[] iArrZzb = zzb();
                if (iArrZzb != null) {
                    PopupWindow popupWindow = this.zzq;
                    zzlc.zza();
                    int iZza = zzako.zza(this.zzl, iArrZzb[0]);
                    zzlc.zza();
                    popupWindow.update(iZza, zzako.zza(this.zzl, iArrZzb[1]), this.zzq.getWidth(), this.zzq.getHeight());
                    zzb(iArrZzb[0], iArrZzb[1]);
                } else {
                    zza(true);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:118:0x02ad A[Catch: all -> 0x0324, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:7:0x0010, B:9:0x0012, B:11:0x001a, B:12:0x001f, B:14:0x0021, B:16:0x002d, B:17:0x0032, B:19:0x0034, B:21:0x003c, B:22:0x0041, B:24:0x0043, B:26:0x0051, B:27:0x0062, B:29:0x0070, B:30:0x0081, B:32:0x008f, B:33:0x00a0, B:35:0x00ae, B:36:0x00bf, B:38:0x00cd, B:39:0x00db, B:41:0x00e9, B:42:0x00eb, B:44:0x00f1, B:49:0x00fa, B:50:0x00ff, B:52:0x0101, B:54:0x0109, B:57:0x0111, B:59:0x0117, B:60:0x011c, B:62:0x011e, B:64:0x0140, B:66:0x0144, B:68:0x0154, B:70:0x0186, B:74:0x01b8, B:75:0x01f3, B:105:0x0248, B:106:0x024b, B:114:0x026c, B:115:0x0284, B:116:0x02a5, B:118:0x02ad, B:119:0x02b6, B:120:0x02cc, B:123:0x02cf, B:125:0x02df, B:127:0x02e9, B:129:0x02fb, B:130:0x0314, B:126:0x02e4, B:107:0x024f, B:108:0x0253, B:109:0x0257, B:110:0x025a, B:111:0x025e, B:112:0x0264, B:113:0x0268, B:77:0x01f7, B:80:0x0201, B:83:0x020b, B:86:0x0215, B:89:0x021f, B:92:0x0229, B:69:0x0181, B:132:0x0316, B:133:0x031b, B:135:0x031d, B:136:0x0322), top: B:141:0x0007, inners: #1 }] */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void zza(Map<String, String> map) {
        int i;
        int i2;
        synchronized (this.zzj) {
            if (this.zzl == null) {
                zza("Not an activity context. Cannot resize.");
                return;
            }
            if (this.zzk.zzt() == null) {
                zza("Webview is not yet available, size is not set.");
                return;
            }
            if (this.zzk.zzt().zzd()) {
                zza("Is interstitial. Cannot resize an interstitial.");
                return;
            }
            if (this.zzk.zzy()) {
                zza("Cannot resize an expanded banner.");
                return;
            }
            if (!TextUtils.isEmpty(map.get(ToolboxAnalyticsModuleKt.M_WIDTH))) {
                zzbt.zze();
                this.zzi = zzaij.zzb(map.get(ToolboxAnalyticsModuleKt.M_WIDTH));
            }
            if (!TextUtils.isEmpty(map.get(ToolboxAnalyticsModuleKt.M_HEIGHT))) {
                zzbt.zze();
                this.zzf = zzaij.zzb(map.get(ToolboxAnalyticsModuleKt.M_HEIGHT));
            }
            if (!TextUtils.isEmpty(map.get("offsetX"))) {
                zzbt.zze();
                this.zzg = zzaij.zzb(map.get("offsetX"));
            }
            if (!TextUtils.isEmpty(map.get("offsetY"))) {
                zzbt.zze();
                this.zzh = zzaij.zzb(map.get("offsetY"));
            }
            if (!TextUtils.isEmpty(map.get("allowOffscreen"))) {
                this.zzc = Boolean.parseBoolean(map.get("allowOffscreen"));
            }
            String str = map.get("customClosePosition");
            if (!TextUtils.isEmpty(str)) {
                this.zzb = str;
            }
            if (!(this.zzi >= 0 && this.zzf >= 0)) {
                zza("Invalid width and height options. Cannot resize.");
                return;
            }
            Window window = this.zzl.getWindow();
            if (window != null && window.getDecorView() != null) {
                int[] iArrZzb = zzb();
                if (iArrZzb == null) {
                    zza("Resize location out of screen or close button is not visible.");
                    return;
                }
                zzlc.zza();
                int iZza = zzako.zza(this.zzl, this.zzi);
                zzlc.zza();
                int iZza2 = zzako.zza(this.zzl, this.zzf);
                ViewParent parent = this.zzk.zzm().getParent();
                if (parent == null || !(parent instanceof ViewGroup)) {
                    zza("Webview is detached, probably in the middle of a resize or expand.");
                    return;
                }
                ((ViewGroup) parent).removeView(this.zzk.zzm());
                if (this.zzq == null) {
                    this.zzs = (ViewGroup) parent;
                    zzbt.zze();
                    Bitmap bitmapZza = zzaij.zza(this.zzk.zzm());
                    ImageView imageView = new ImageView(this.zzl);
                    this.zzn = imageView;
                    imageView.setImageBitmap(bitmapZza);
                    this.zzm = this.zzk.zzt();
                    this.zzs.addView(this.zzn);
                } else {
                    this.zzq.dismiss();
                }
                RelativeLayout relativeLayout = new RelativeLayout(this.zzl);
                this.zzr = relativeLayout;
                relativeLayout.setBackgroundColor(0);
                this.zzr.setLayoutParams(new ViewGroup.LayoutParams(iZza, iZza2));
                zzbt.zze();
                PopupWindow popupWindowZza = zzaij.zza((View) this.zzr, iZza, iZza2, false);
                this.zzq = popupWindowZza;
                popupWindowZza.setOutsideTouchable(true);
                this.zzq.setTouchable(true);
                this.zzq.setClippingEnabled(!this.zzc);
                byte b = -1;
                this.zzr.addView(this.zzk.zzm(), -1, -1);
                this.zzo = new LinearLayout(this.zzl);
                zzlc.zza();
                int iZza3 = zzako.zza(this.zzl, 50);
                zzlc.zza();
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iZza3, zzako.zza(this.zzl, 50));
                String str2 = this.zzb;
                switch (str2.hashCode()) {
                    case -1364013995:
                        if (str2.equals(TtmlNode.CENTER)) {
                            b = 2;
                        }
                        break;
                    case -1012429441:
                        if (str2.equals("top-left")) {
                            b = 0;
                        }
                        break;
                    case -655373719:
                        if (str2.equals("bottom-left")) {
                            b = 3;
                        }
                        break;
                    case 1163912186:
                        if (str2.equals("bottom-right")) {
                            b = 5;
                        }
                        break;
                    case 1288627767:
                        if (str2.equals("bottom-center")) {
                            b = 4;
                        }
                        break;
                    case 1755462605:
                        if (str2.equals("top-center")) {
                            b = 1;
                        }
                        break;
                }
                try {
                    if (b != 0) {
                        int i3 = 14;
                        if (b != 1) {
                            if (b == 2) {
                                layoutParams.addRule(13);
                            } else if (b == 3) {
                                layoutParams.addRule(12);
                            } else if (b != 4) {
                                i3 = 11;
                                if (b != 5) {
                                    layoutParams.addRule(10);
                                } else {
                                    layoutParams.addRule(12);
                                }
                            } else {
                                layoutParams.addRule(12);
                            }
                            this.zzo.setOnClickListener(new zzye(this));
                            this.zzo.setContentDescription("Close button");
                            this.zzr.addView(this.zzo, layoutParams);
                            PopupWindow popupWindow = this.zzq;
                            View decorView = window.getDecorView();
                            zzlc.zza();
                            int iZza4 = zzako.zza(this.zzl, iArrZzb[0]);
                            zzlc.zza();
                            popupWindow.showAtLocation(decorView, 0, iZza4, zzako.zza(this.zzl, iArrZzb[1]));
                            i = iArrZzb[0];
                            i2 = iArrZzb[1];
                            if (this.zzp != null) {
                                this.zzp.zza(i, i2, this.zzi, this.zzf);
                            }
                            this.zzk.zza(zzaqa.zza(iZza, iZza2));
                            zzb(iArrZzb[0], iArrZzb[1]);
                            zzc("resized");
                            return;
                        }
                        layoutParams.addRule(10);
                        layoutParams.addRule(i3);
                        this.zzo.setOnClickListener(new zzye(this));
                        this.zzo.setContentDescription("Close button");
                        this.zzr.addView(this.zzo, layoutParams);
                        PopupWindow popupWindow2 = this.zzq;
                        View decorView2 = window.getDecorView();
                        zzlc.zza();
                        int iZza5 = zzako.zza(this.zzl, iArrZzb[0]);
                        zzlc.zza();
                        popupWindow2.showAtLocation(decorView2, 0, iZza5, zzako.zza(this.zzl, iArrZzb[1]));
                        i = iArrZzb[0];
                        i2 = iArrZzb[1];
                        if (this.zzp != null) {
                            this.zzp.zza(i, i2, this.zzi, this.zzf);
                        }
                        this.zzk.zza(zzaqa.zza(iZza, iZza2));
                        zzb(iArrZzb[0], iArrZzb[1]);
                        zzc("resized");
                        return;
                    }
                    layoutParams.addRule(10);
                    PopupWindow popupWindow3 = this.zzq;
                    View decorView3 = window.getDecorView();
                    zzlc.zza();
                    int iZza6 = zzako.zza(this.zzl, iArrZzb[0]);
                    zzlc.zza();
                    popupWindow3.showAtLocation(decorView3, 0, iZza6, zzako.zza(this.zzl, iArrZzb[1]));
                    i = iArrZzb[0];
                    i2 = iArrZzb[1];
                    if (this.zzp != null) {
                        this.zzp.zza(i, i2, this.zzi, this.zzf);
                    }
                    this.zzk.zza(zzaqa.zza(iZza, iZza2));
                    zzb(iArrZzb[0], iArrZzb[1]);
                    zzc("resized");
                    return;
                } catch (RuntimeException e) {
                    String strValueOf = String.valueOf(e.getMessage());
                    zza(strValueOf.length() != 0 ? "Cannot show popup window: ".concat(strValueOf) : new String("Cannot show popup window: "));
                    this.zzr.removeView(this.zzk.zzm());
                    if (this.zzs != null) {
                        this.zzs.removeView(this.zzn);
                        this.zzs.addView(this.zzk.zzm());
                        this.zzk.zza(this.zzm);
                    }
                    return;
                }
                layoutParams.addRule(9);
                this.zzo.setOnClickListener(new zzye(this));
                this.zzo.setContentDescription("Close button");
                this.zzr.addView(this.zzo, layoutParams);
            }
            zza("Activity context is not ready, cannot get window or decor view.");
        }
    }

    public final void zza(boolean z) {
        synchronized (this.zzj) {
            if (this.zzq != null) {
                this.zzq.dismiss();
                this.zzr.removeView(this.zzk.zzm());
                if (this.zzs != null) {
                    this.zzs.removeView(this.zzn);
                    this.zzs.addView(this.zzk.zzm());
                    this.zzk.zza(this.zzm);
                }
                if (z) {
                    zzc("default");
                    if (this.zzp != null) {
                        this.zzp.zzas();
                    }
                }
                this.zzq = null;
                this.zzr = null;
                this.zzs = null;
                this.zzo = null;
            }
        }
    }

    public final boolean zza() {
        boolean z;
        synchronized (this.zzj) {
            z = this.zzq != null;
        }
        return z;
    }
}
