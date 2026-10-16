package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.internal.zzc;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzanb extends FrameLayout implements zzamy {
    private final zzann zza;
    private final FrameLayout zzb;
    private final zzov zzc;
    private final zzanp zzd;
    private final long zze;
    private zzamz zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;
    private long zzk;
    private long zzl;
    private String zzm;
    private Bitmap zzn;
    private ImageView zzo;
    private boolean zzp;

    public zzanb(Context context, zzann zzannVar, int i, boolean z, zzov zzovVar, zzanm zzanmVar) {
        super(context);
        this.zza = zzannVar;
        this.zzc = zzovVar;
        FrameLayout frameLayout = new FrameLayout(context);
        this.zzb = frameLayout;
        addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
        zzc.zza(zzannVar.zze());
        zzamz zzamzVarZza = zzannVar.zze().zzb.zza(context, zzannVar, i, z, zzovVar, zzanmVar);
        this.zzf = zzamzVarZza;
        if (zzamzVarZza != null) {
            this.zzb.addView(zzamzVarZza, new FrameLayout.LayoutParams(-1, -1, 17));
            if (((Boolean) zzlc.zzf().zza(zzoi.zzs)).booleanValue()) {
                zzm();
            }
        }
        this.zzo = new ImageView(context);
        this.zze = ((Long) zzlc.zzf().zza(zzoi.zzw)).longValue();
        boolean zBooleanValue = ((Boolean) zzlc.zzf().zza(zzoi.zzu)).booleanValue();
        this.zzj = zBooleanValue;
        zzov zzovVar2 = this.zzc;
        if (zzovVar2 != null) {
            zzovVar2.zza("spinner_used", zBooleanValue ? LocalSearchProvider.LIVE_CONTENTS : LocalSearchProvider.NO_LIVE_CONTENTS);
        }
        this.zzd = new zzanp(this);
        zzamz zzamzVar = this.zzf;
        if (zzamzVar != null) {
            zzamzVar.zza(this);
        }
        if (this.zzf == null) {
            zza("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    public static void zza(zzann zzannVar) {
        HashMap map = new HashMap();
        map.put(NotificationCompat.CATEGORY_EVENT, "no_video_view");
        zzannVar.zza("onVideoEvent", map);
    }

    public static void zza(zzann zzannVar, String str) {
        HashMap map = new HashMap();
        map.put(NotificationCompat.CATEGORY_EVENT, "decoderProps");
        map.put("error", str);
        zzannVar.zza("onVideoEvent", map);
    }

    public static void zza(zzann zzannVar, Map<String, List<Map<String, Object>>> map) {
        HashMap map2 = new HashMap();
        map2.put(NotificationCompat.CATEGORY_EVENT, "decoderProps");
        map2.put("mimeTypes", map);
        zzannVar.zza("onVideoEvent", map2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(String str, String... strArr) {
        HashMap map = new HashMap();
        map.put(NotificationCompat.CATEGORY_EVENT, str);
        String str2 = null;
        for (String str3 : strArr) {
            if (str2 == null) {
                str2 = str3;
            } else {
                map.put(str2, str3);
                str2 = null;
            }
        }
        this.zza.zza("onVideoEvent", map);
    }

    private final boolean zzp() {
        return this.zzo.getParent() != null;
    }

    private final void zzq() {
        if (this.zza.zzd() == null || !this.zzh || this.zzi) {
            return;
        }
        this.zza.zzd().getWindow().clearFlags(128);
        this.zzh = false;
    }

    @Override // com.google.android.gms.internal.zzamy
    public final void zza() {
        this.zzd.zzb();
        zzaij.zza.post(new zzanc(this));
    }

    public final void zza(float f) {
        zzamz zzamzVar = this.zzf;
        if (zzamzVar == null) {
            return;
        }
        zzamzVar.zzb.zza(f);
        zzamzVar.zzi();
    }

    public final void zza(float f, float f2) {
        zzamz zzamzVar = this.zzf;
        if (zzamzVar != null) {
            zzamzVar.zza(f, f2);
        }
    }

    public final void zza(int i) {
        zzamz zzamzVar = this.zzf;
        if (zzamzVar == null) {
            return;
        }
        zzamzVar.zza(i);
    }

    @Override // com.google.android.gms.internal.zzamy
    public final void zza(int i, int i2) {
        if (this.zzj) {
            int iMax = Math.max(i / ((Integer) zzlc.zzf().zza(zzoi.zzv)).intValue(), 1);
            int iMax2 = Math.max(i2 / ((Integer) zzlc.zzf().zza(zzoi.zzv)).intValue(), 1);
            Bitmap bitmap = this.zzn;
            if (bitmap != null && bitmap.getWidth() == iMax && this.zzn.getHeight() == iMax2) {
                return;
            }
            this.zzn = Bitmap.createBitmap(iMax, iMax2, Bitmap.Config.ARGB_8888);
            this.zzp = false;
        }
    }

    public final void zza(int i, int i2, int i3, int i4) {
        if (i3 == 0 || i4 == 0) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i3, i4);
        layoutParams.setMargins(i, i2, 0, 0);
        this.zzb.setLayoutParams(layoutParams);
        requestLayout();
    }

    public final void zza(MotionEvent motionEvent) {
        zzamz zzamzVar = this.zzf;
        if (zzamzVar == null) {
            return;
        }
        zzamzVar.dispatchTouchEvent(motionEvent);
    }

    public final void zza(String str) {
        this.zzm = str;
    }

    @Override // com.google.android.gms.internal.zzamy
    public final void zza(String str, String str2) {
        zza("error", "what", str, "extra", str2);
    }

    @Override // com.google.android.gms.internal.zzamy
    public final void zzb() {
        zzamz zzamzVar = this.zzf;
        if (zzamzVar != null && this.zzl == 0) {
            zza("canplaythrough", "duration", String.valueOf(zzamzVar.zze() / 1000.0f), "videoWidth", String.valueOf(this.zzf.zzg()), "videoHeight", String.valueOf(this.zzf.zzh()));
        }
    }

    @Override // com.google.android.gms.internal.zzamy
    public final void zzc() {
        if (this.zza.zzd() != null && !this.zzh) {
            boolean z = (this.zza.zzd().getWindow().getAttributes().flags & 128) != 0;
            this.zzi = z;
            if (!z) {
                this.zza.zzd().getWindow().addFlags(128);
                this.zzh = true;
            }
        }
        this.zzg = true;
    }

    @Override // com.google.android.gms.internal.zzamy
    public final void zzd() {
        zza(ToolboxAnalyticsModuleKt.EVENT_PAUSE, new String[0]);
        zzq();
        this.zzg = false;
    }

    @Override // com.google.android.gms.internal.zzamy
    public final void zze() {
        zza(ToolboxAnalyticsModuleKt.EVENT_ENDED, new String[0]);
        zzq();
    }

    @Override // com.google.android.gms.internal.zzamy
    public final void zzf() {
        if (this.zzp && this.zzn != null && !zzp()) {
            this.zzo.setImageBitmap(this.zzn);
            this.zzo.invalidate();
            this.zzb.addView(this.zzo, new FrameLayout.LayoutParams(-1, -1));
            this.zzb.bringChildToFront(this.zzo);
        }
        this.zzd.zza();
        this.zzl = this.zzk;
        zzaij.zza.post(new zzand(this));
    }

    @Override // com.google.android.gms.internal.zzamy
    public final void zzg() {
        if (this.zzg && zzp()) {
            this.zzb.removeView(this.zzo);
        }
        if (this.zzn != null) {
            long jZzb = zzbt.zzl().zzb();
            if (this.zzf.getBitmap(this.zzn) != null) {
                this.zzp = true;
            }
            long jZzb2 = zzbt.zzl().zzb() - jZzb;
            if (zzahw.zza()) {
                StringBuilder sb = new StringBuilder(46);
                sb.append("Spinner frame grab took ");
                sb.append(jZzb2);
                sb.append("ms");
                zzahw.zza(sb.toString());
            }
            if (jZzb2 > this.zze) {
                zzahw.zze("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzj = false;
                this.zzn = null;
                zzov zzovVar = this.zzc;
                if (zzovVar != null) {
                    zzovVar.zza("spinner_jank", Long.toString(jZzb2));
                }
            }
        }
    }

    public final void zzh() {
        if (this.zzf == null) {
            return;
        }
        if (TextUtils.isEmpty(this.zzm)) {
            zza("no_src", new String[0]);
        } else {
            this.zzf.zza(this.zzm);
        }
    }

    public final void zzi() {
        zzamz zzamzVar = this.zzf;
        if (zzamzVar == null) {
            return;
        }
        zzamzVar.zzd();
    }

    public final void zzj() {
        zzamz zzamzVar = this.zzf;
        if (zzamzVar == null) {
            return;
        }
        zzamzVar.zzc();
    }

    public final void zzk() {
        zzamz zzamzVar = this.zzf;
        if (zzamzVar == null) {
            return;
        }
        zzamzVar.zzb.zza(true);
        zzamzVar.zzi();
    }

    public final void zzl() {
        zzamz zzamzVar = this.zzf;
        if (zzamzVar == null) {
            return;
        }
        zzamzVar.zzb.zza(false);
        zzamzVar.zzi();
    }

    public final void zzm() {
        zzamz zzamzVar = this.zzf;
        if (zzamzVar == null) {
            return;
        }
        TextView textView = new TextView(zzamzVar.getContext());
        String strValueOf = String.valueOf(this.zzf.zza());
        textView.setText(strValueOf.length() != 0 ? "AdMob - ".concat(strValueOf) : new String("AdMob - "));
        textView.setTextColor(SupportMenu.CATEGORY_MASK);
        textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
        this.zzb.addView(textView, new FrameLayout.LayoutParams(-2, -2, 17));
        this.zzb.bringChildToFront(textView);
    }

    public final void zzn() {
        this.zzd.zza();
        zzamz zzamzVar = this.zzf;
        if (zzamzVar != null) {
            zzamzVar.zzb();
        }
        zzq();
    }

    final void zzo() {
        zzamz zzamzVar = this.zzf;
        if (zzamzVar == null) {
            return;
        }
        long jZzf = zzamzVar.zzf();
        if (this.zzk == jZzf || jZzf <= 0) {
            return;
        }
        zza("timeupdate", "time", String.valueOf(jZzf / 1000.0f));
        this.zzk = jZzf;
    }
}
