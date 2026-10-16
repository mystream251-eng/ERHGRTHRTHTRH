package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.ads.internal.zzbt;
import com.viaccessorca.analytics.fma.FmaDatabaseHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzfv implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    protected final zzft zza;
    private final WeakReference<zzahd> zzc;
    private final zzhf zze;
    private final Context zzf;
    private final WindowManager zzg;
    private final PowerManager zzh;
    private final KeyguardManager zzi;
    private final DisplayMetrics zzj;
    private zzgc zzk;
    private boolean zzl;
    private boolean zzp;
    private BroadcastReceiver zzr;
    private float zzx;
    private Object zzb = new Object();
    private boolean zzm = false;
    private boolean zzn = false;
    private final HashSet<zzfs> zzs = new HashSet<>();
    private final HashSet<zzgq> zzu = new HashSet<>();
    private final Rect zzv = new Rect();
    private WeakReference<ViewTreeObserver> zzd = new WeakReference<>(null);
    private boolean zzo = true;
    private boolean zzq = false;
    private zzake zzt = new zzake(200);
    private final zzfy zzw = new zzfy(this, new Handler());

    public zzfv(Context context, zzko zzkoVar, zzahd zzahdVar, zzala zzalaVar, zzhf zzhfVar) {
        this.zzc = new WeakReference<>(zzahdVar);
        this.zze = zzhfVar;
        this.zza = new zzft(UUID.randomUUID().toString(), zzalaVar, zzkoVar.zza, zzahdVar.zzj, zzahdVar.zza(), zzkoVar.zzh);
        this.zzg = (WindowManager) context.getSystemService("window");
        this.zzh = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzi = (KeyguardManager) context.getSystemService("keyguard");
        this.zzf = context;
        this.zzf.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.zzw);
        this.zzj = context.getResources().getDisplayMetrics();
        Display defaultDisplay = this.zzg.getDefaultDisplay();
        this.zzv.right = defaultDisplay.getWidth();
        this.zzv.bottom = defaultDisplay.getHeight();
        zza();
    }

    private static int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (i / displayMetrics.density);
    }

    private final JSONObject zza(View view, Boolean bool) throws JSONException {
        if (view == null) {
            return zzi().put("isAttachedToWindow", false).put("isScreenOn", zzj()).put("isVisible", false);
        }
        boolean zZza = zzbt.zzg().zza(view);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view.getLocationOnScreen(iArr);
            view.getLocationInWindow(iArr2);
        } catch (Exception e) {
            zzahw.zzb("Failure getting view location.", e);
        }
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect2, null);
        Rect rect3 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect3);
        Rect rect4 = new Rect();
        view.getHitRect(rect4);
        JSONObject jSONObjectZzi = zzi();
        jSONObjectZzi.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", zZza).put("viewBox", new JSONObject().put("top", zza(this.zzv.top, this.zzj)).put("bottom", zza(this.zzv.bottom, this.zzj)).put(TtmlNode.LEFT, zza(this.zzv.left, this.zzj)).put(TtmlNode.RIGHT, zza(this.zzv.right, this.zzj))).put("adBox", new JSONObject().put("top", zza(rect.top, this.zzj)).put("bottom", zza(rect.bottom, this.zzj)).put(TtmlNode.LEFT, zza(rect.left, this.zzj)).put(TtmlNode.RIGHT, zza(rect.right, this.zzj))).put("globalVisibleBox", new JSONObject().put("top", zza(rect2.top, this.zzj)).put("bottom", zza(rect2.bottom, this.zzj)).put(TtmlNode.LEFT, zza(rect2.left, this.zzj)).put(TtmlNode.RIGHT, zza(rect2.right, this.zzj))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", zza(rect3.top, this.zzj)).put("bottom", zza(rect3.bottom, this.zzj)).put(TtmlNode.LEFT, zza(rect3.left, this.zzj)).put(TtmlNode.RIGHT, zza(rect3.right, this.zzj))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put("top", zza(rect4.top, this.zzj)).put("bottom", zza(rect4.bottom, this.zzj)).put(TtmlNode.LEFT, zza(rect4.left, this.zzj)).put(TtmlNode.RIGHT, zza(rect4.right, this.zzj))).put("screenDensity", this.zzj.density);
        jSONObjectZzi.put("isVisible", (bool == null ? Boolean.valueOf(zzbt.zze().zza(view, this.zzh, this.zzi)) : bool).booleanValue());
        return jSONObjectZzi;
    }

    private static JSONObject zza(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }

    private final void zza(JSONObject jSONObject, boolean z) {
        try {
            JSONObject jSONObjectZza = zza(jSONObject);
            ArrayList arrayList = new ArrayList(this.zzu);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((zzgq) obj).zza(jSONObjectZza, z);
            }
        } catch (Throwable th) {
            zzahw.zzb("Skipping active view message.", th);
        }
    }

    private final void zzg() {
        zzgc zzgcVar = this.zzk;
        if (zzgcVar != null) {
            zzgcVar.zza(this);
        }
    }

    private final void zzh() {
        ViewTreeObserver viewTreeObserver = this.zzd.get();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            return;
        }
        viewTreeObserver.removeOnScrollChangedListener(this);
        viewTreeObserver.removeGlobalOnLayoutListener(this);
    }

    private final JSONObject zzi() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zza.zzb()).put("activeViewJSON", this.zza.zzc()).put(FmaDatabaseHelper.CONST_TIMESTAMP, zzbt.zzl().zzb()).put("adFormat", this.zza.zza()).put("hashCode", this.zza.zzd()).put("isMraid", this.zza.zze()).put("isStopped", this.zzn).put("isPaused", this.zzm).put("isNative", this.zza.zzf()).put("isScreenOn", zzj()).put("appMuted", zzbt.zzac().zzb()).put("appVolume", zzbt.zzac().zza()).put("deviceVolume", this.zzx);
        return jSONObject;
    }

    private final boolean zzj() {
        return Build.VERSION.SDK_INT >= 20 ? this.zzh.isInteractive() : this.zzh.isScreenOn();
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        zza(2);
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        zza(1);
    }

    public final void zza() {
        this.zzx = zzaja.zza(this.zzf);
    }

    protected final void zza(int i) {
        boolean z;
        ViewTreeObserver viewTreeObserver;
        ViewTreeObserver viewTreeObserver2;
        synchronized (this.zzb) {
            Iterator<zzgq> it = this.zzu.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (it.next().zza()) {
                    z = true;
                    break;
                }
            }
            if (z && this.zzo) {
                View viewZza = this.zze.zza();
                boolean z2 = viewZza != null && zzbt.zze().zza(viewZza, this.zzh, this.zzi);
                boolean z3 = viewZza != null && z2 && viewZza.getGlobalVisibleRect(new Rect(), null);
                if (this.zze.zzb()) {
                    zzb();
                    return;
                }
                if (i == 1 && !this.zzt.zza() && z3 == this.zzq) {
                    return;
                }
                if (z3 || this.zzq || i != 1) {
                    try {
                        zza(zza(viewZza, Boolean.valueOf(z2)), false);
                        this.zzq = z3;
                    } catch (RuntimeException | JSONException e) {
                        zzahw.zza("Active view update failed.", e);
                    }
                    View viewZza2 = this.zze.zzc().zza();
                    if (viewZza2 != null && (viewTreeObserver2 = viewZza2.getViewTreeObserver()) != (viewTreeObserver = this.zzd.get())) {
                        zzh();
                        if (!this.zzl || (viewTreeObserver != null && viewTreeObserver.isAlive())) {
                            this.zzl = true;
                            viewTreeObserver2.addOnScrollChangedListener(this);
                            viewTreeObserver2.addOnGlobalLayoutListener(this);
                        }
                        this.zzd = new WeakReference<>(viewTreeObserver2);
                    }
                    zzg();
                }
            }
        }
    }

    public final void zza(zzgc zzgcVar) {
        synchronized (this.zzb) {
            this.zzk = zzgcVar;
        }
    }

    public final void zza(zzgq zzgqVar) {
        if (this.zzu.isEmpty()) {
            synchronized (this.zzb) {
                if (this.zzr == null) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.SCREEN_ON");
                    intentFilter.addAction("android.intent.action.SCREEN_OFF");
                    this.zzr = new zzfw(this);
                    zzbt.zzad().zza(this.zzf, this.zzr, intentFilter);
                }
            }
            zza(3);
        }
        this.zzu.add(zzgqVar);
        try {
            zzgqVar.zza(zza(zza(this.zze.zza(), (Boolean) null)), false);
        } catch (JSONException e) {
            zzahw.zzb("Skipping measurement update for new client.", e);
        }
    }

    final void zza(zzgq zzgqVar, Map<String, String> map) {
        String strValueOf = String.valueOf(this.zza.zzd());
        zzahw.zzb(strValueOf.length() != 0 ? "Received request to untrack: ".concat(strValueOf) : new String("Received request to untrack: "));
        zzb(zzgqVar);
    }

    final boolean zza(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(this.zza.zzd());
    }

    public final void zzb() {
        String str;
        synchronized (this.zzb) {
            if (this.zzo) {
                this.zzp = true;
                try {
                    JSONObject jSONObjectZzi = zzi();
                    jSONObjectZzi.put("doneReasonCode", "u");
                    zza(jSONObjectZzi, true);
                } catch (RuntimeException e) {
                    e = e;
                    str = "Failure while processing active view data.";
                    zzahw.zzb(str, e);
                } catch (JSONException e2) {
                    e = e2;
                    str = "JSON failure while processing active view data.";
                    zzahw.zzb(str, e);
                }
                String strValueOf = String.valueOf(this.zza.zzd());
                zzahw.zzb(strValueOf.length() != 0 ? "Untracking ad unit: ".concat(strValueOf) : new String("Untracking ad unit: "));
            }
        }
    }

    public final void zzb(zzgq zzgqVar) {
        this.zzu.remove(zzgqVar);
        zzgqVar.zzb();
        if (this.zzu.isEmpty()) {
            synchronized (this.zzb) {
                zzh();
                synchronized (this.zzb) {
                    if (this.zzr != null) {
                        try {
                            zzbt.zzad().zza(this.zzf, this.zzr);
                        } catch (IllegalStateException e) {
                            zzahw.zzb("Failed trying to unregister the receiver", e);
                        } catch (Exception e2) {
                            zzbt.zzi().zza(e2, "ActiveViewUnit.stopScreenStatusMonitoring");
                        }
                        this.zzr = null;
                    }
                }
                this.zzf.getContentResolver().unregisterContentObserver(this.zzw);
                int i = 0;
                this.zzo = false;
                zzg();
                ArrayList arrayList = new ArrayList(this.zzu);
                int size = arrayList.size();
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    zzb((zzgq) obj);
                }
            }
        }
    }

    final void zzb(Map<String, String> map) {
        zza(3);
    }

    final void zzc(Map<String, String> map) {
        if (map.containsKey("isVisible")) {
            boolean z = LocalSearchProvider.LIVE_CONTENTS.equals(map.get("isVisible")) || "true".equals(map.get("isVisible"));
            Iterator<zzfs> it = this.zzs.iterator();
            while (it.hasNext()) {
                it.next().zza(this, z);
            }
        }
    }

    public final boolean zzc() {
        boolean z;
        synchronized (this.zzb) {
            z = this.zzo;
        }
        return z;
    }

    public final void zzd() {
        synchronized (this.zzb) {
            this.zzn = true;
            zza(3);
        }
    }

    public final void zze() {
        synchronized (this.zzb) {
            this.zzm = true;
            zza(3);
        }
    }

    public final void zzf() {
        synchronized (this.zzb) {
            this.zzm = false;
            zza(3);
        }
    }
}
