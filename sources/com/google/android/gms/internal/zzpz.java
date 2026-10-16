package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import tv.broadpeak.smartlib.ad.AdInteractionType;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzpz implements zzpv {
    boolean zza;
    private final zzpw zzc;
    private final Context zzd;
    private final JSONObject zze;
    private final zzaan zzf;
    private final zzpx zzg;
    private final zzcv zzh;
    private final zzala zzi;
    private String zzj;
    private zzagt zzk;
    private final Object zzb = new Object();
    private WeakReference<View> zzl = null;

    public zzpz(Context context, zzpw zzpwVar, zzaan zzaanVar, zzcv zzcvVar, JSONObject jSONObject, zzpx zzpxVar, zzala zzalaVar, String str) {
        this.zzd = context;
        this.zzc = zzpwVar;
        this.zzf = zzaanVar;
        this.zzh = zzcvVar;
        this.zze = jSONObject;
        this.zzg = zzpxVar;
        this.zzi = zzalaVar;
        this.zzj = str;
    }

    private final int zza(int i) {
        zzlc.zza();
        return zzako.zzb(this.zzd, i);
    }

    private final JSONObject zza(Rect rect) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ToolboxAnalyticsModuleKt.M_WIDTH, zza(rect.right - rect.left));
        jSONObject.put(ToolboxAnalyticsModuleKt.M_HEIGHT, zza(rect.bottom - rect.top));
        jSONObject.put("x", zza(rect.left));
        jSONObject.put("y", zza(rect.top));
        jSONObject.put("relative_to", "self");
        return jSONObject;
    }

    private final JSONObject zza(Map<String, WeakReference<View>> map, View view) {
        JSONObject jSONObjectZza;
        JSONObject jSONObject = new JSONObject();
        if (map == null || view == null) {
            return jSONObject;
        }
        int[] iArrZzd = zzd(view);
        synchronized (map) {
            for (Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                View view2 = entry.getValue().get();
                if (view2 != null) {
                    int[] iArrZzd2 = zzd(view2);
                    JSONObject jSONObject2 = new JSONObject();
                    JSONObject jSONObject3 = new JSONObject();
                    try {
                        jSONObject3.put(ToolboxAnalyticsModuleKt.M_WIDTH, zza(view2.getMeasuredWidth()));
                        jSONObject3.put(ToolboxAnalyticsModuleKt.M_HEIGHT, zza(view2.getMeasuredHeight()));
                        jSONObject3.put("x", zza(iArrZzd2[0] - iArrZzd[0]));
                        jSONObject3.put("y", zza(iArrZzd2[1] - iArrZzd[1]));
                        jSONObject3.put("relative_to", "ad_view");
                        jSONObject2.put("frame", jSONObject3);
                        Rect rect = new Rect();
                        if (view2.getLocalVisibleRect(rect)) {
                            jSONObjectZza = zza(rect);
                        } else {
                            JSONObject jSONObject4 = new JSONObject();
                            jSONObject4.put(ToolboxAnalyticsModuleKt.M_WIDTH, 0);
                            jSONObject4.put(ToolboxAnalyticsModuleKt.M_HEIGHT, 0);
                            jSONObject4.put("x", zza(iArrZzd2[0] - iArrZzd[0]));
                            jSONObject4.put("y", zza(iArrZzd2[1] - iArrZzd[1]));
                            jSONObject4.put("relative_to", "ad_view");
                            jSONObjectZza = jSONObject4;
                        }
                        jSONObject2.put("visible_bounds", jSONObjectZza);
                        if (view2 instanceof TextView) {
                            TextView textView = (TextView) view2;
                            jSONObject2.put("text_color", textView.getCurrentTextColor());
                            jSONObject2.put("font_size", textView.getTextSize());
                            jSONObject2.put("text", textView.getText());
                        }
                        jSONObject.put(entry.getKey(), jSONObject2);
                    } catch (JSONException unused) {
                        zzahw.zze("Unable to get asset views information");
                    }
                }
            }
        }
        return jSONObject;
    }

    private final void zza(View view, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, String str, JSONObject jSONObject5, JSONObject jSONObject6) {
        zzbq.zzb("performClick must be called on the main UI thread.");
        try {
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("ad", this.zze);
            if (jSONObject2 != null) {
                jSONObject7.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject7.put("ad_view_signal", jSONObject);
            }
            if (jSONObject5 != null) {
                jSONObject7.put("click_signal", jSONObject5);
            }
            if (jSONObject3 != null) {
                jSONObject7.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject7.put("lock_screen_signal", jSONObject4);
            }
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("asset_id", str);
            jSONObject8.put("template", this.zzg.zzk());
            zzbt.zzg();
            jSONObject8.put("is_privileged_process", zzaip.zze());
            boolean z = true;
            jSONObject8.put("has_custom_click_handler", this.zzc.zzb(this.zzg.zzl()) != null);
            if (this.zzc.zzb(this.zzg.zzl()) == null) {
                z = false;
            }
            jSONObject7.put("has_custom_click_handler", z);
            try {
                JSONObject jSONObjectOptJSONObject = this.zze.optJSONObject("tracking_urls_and_actions");
                if (jSONObjectOptJSONObject == null) {
                    jSONObjectOptJSONObject = new JSONObject();
                }
                jSONObject8.put("click_signals", this.zzh.zza().zza(this.zzd, jSONObjectOptJSONObject.optString("click_string"), view));
            } catch (Exception e) {
                zzahw.zzb("Exception obtaining click signals", e);
            }
            jSONObject7.put(AdInteractionType.CLICK, jSONObject8);
            if (jSONObject6 != null) {
                jSONObject7.put("provided_signals", jSONObject6);
            }
            jSONObject7.put("ads_id", this.zzj);
            zzalg.zza(this.zzf.zzb(jSONObject7), "NativeAdEngineImpl.performClick");
        } catch (JSONException e2) {
            zzahw.zzb("Unable to create click JSON.", e2);
        }
    }

    private final boolean zza(String str) {
        JSONObject jSONObject = this.zze;
        JSONObject jSONObjectOptJSONObject = jSONObject == null ? null : jSONObject.optJSONObject("allow_pub_event_reporting");
        if (jSONObjectOptJSONObject == null) {
            return false;
        }
        return jSONObjectOptJSONObject.optBoolean(str, false);
    }

    private final boolean zza(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5) {
        zzbq.zzb("recordImpression must be called on the main UI thread.");
        if (this.zza) {
            return true;
        }
        this.zza = true;
        try {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("ad", this.zze);
            jSONObject6.put("ads_id", this.zzj);
            if (jSONObject2 != null) {
                jSONObject6.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject6.put("ad_view_signal", jSONObject);
            }
            if (jSONObject3 != null) {
                jSONObject6.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject6.put("lock_screen_signal", jSONObject4);
            }
            if (jSONObject5 != null) {
                jSONObject6.put("provided_signals", jSONObject5);
            }
            zzalg.zza(this.zzf.zzc(jSONObject6), "NativeAdEngineImpl.recordImpression");
            this.zzc.zza(this);
            this.zzc.zzu();
            return true;
        } catch (JSONException e) {
            zzahw.zzb("Unable to create impression JSON.", e);
            return false;
        }
    }

    private static boolean zzc(View view) {
        return view.isShown() && view.getGlobalVisibleRect(new Rect(), null);
    }

    private static int[] zzd(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr;
    }

    private final JSONObject zze(View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (view == null) {
            return jSONObject2;
        }
        try {
            int[] iArrZzd = zzd(view);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(ToolboxAnalyticsModuleKt.M_WIDTH, zza(view.getMeasuredWidth()));
            jSONObject3.put(ToolboxAnalyticsModuleKt.M_HEIGHT, zza(view.getMeasuredHeight()));
            jSONObject3.put("x", zza(iArrZzd[0]));
            jSONObject3.put("y", zza(iArrZzd[1]));
            jSONObject3.put("relative_to", "window");
            jSONObject2.put("frame", jSONObject3);
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                jSONObject = zza(rect);
            } else {
                jSONObject = new JSONObject();
                jSONObject.put(ToolboxAnalyticsModuleKt.M_WIDTH, 0);
                jSONObject.put(ToolboxAnalyticsModuleKt.M_HEIGHT, 0);
                jSONObject.put("x", zza(iArrZzd[0]));
                jSONObject.put("y", zza(iArrZzd[1]));
                jSONObject.put("relative_to", "window");
            }
            jSONObject2.put("visible_bounds", jSONObject);
        } catch (Exception unused) {
            zzahw.zze("Unable to get native ad view bounding box");
        }
        return jSONObject2;
    }

    private static JSONObject zzf(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            zzbt.zze();
            jSONObject.put("contained_in_scroll_view", zzaij.zzd(view) != -1);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private final JSONObject zzg(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            zzbt.zze();
            jSONObject.put("can_show_on_lock_screen", zzaij.zzc(view));
            zzbt.zze();
            jSONObject.put("is_keyguard_locked", zzaij.zzj(this.zzd));
        } catch (JSONException unused) {
            zzahw.zze("Unable to get lock screen information");
        }
        return jSONObject;
    }

    @Override // com.google.android.gms.internal.zzpv
    public View zza(View.OnClickListener onClickListener, boolean z) {
        zzph zzphVarZzm = this.zzg.zzm();
        if (zzphVarZzm == null) {
            return null;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (!z) {
            int iZzh = zzphVarZzm.zzh();
            if (iZzh != 0) {
                if (iZzh == 2) {
                    layoutParams.addRule(12);
                } else if (iZzh != 3) {
                    layoutParams.addRule(10);
                } else {
                    layoutParams.addRule(12);
                }
                layoutParams.addRule(11);
            } else {
                layoutParams.addRule(10);
            }
            layoutParams.addRule(9);
        }
        zzpi zzpiVar = new zzpi(this.zzd, zzphVarZzm, layoutParams);
        zzpiVar.setOnClickListener(onClickListener);
        zzpiVar.setContentDescription((CharSequence) zzlc.zzf().zza(zzoi.zzew));
        return zzpiVar;
    }

    @Override // com.google.android.gms.internal.zzpv
    public final void zza(MotionEvent motionEvent) {
        this.zzh.zza(motionEvent);
    }

    @Override // com.google.android.gms.internal.zzpv
    public final void zza(View view) {
        zzcv zzcvVar;
        zzcr zzcrVarZza;
        if (!((Boolean) zzlc.zzf().zza(zzoi.zzdz)).booleanValue() || (zzcvVar = this.zzh) == null || (zzcrVarZza = zzcvVar.zza()) == null) {
            return;
        }
        zzcrVarZza.zza(view);
    }

    @Override // com.google.android.gms.internal.zzpv
    public final void zza(View view, zzpt zzptVar) {
        if (zzb(view, zzptVar)) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        ((FrameLayout) view).removeAllViews();
        zzpx zzpxVar = this.zzg;
        if (zzpxVar instanceof zzpy) {
            zzpy zzpyVar = (zzpy) zzpxVar;
            if (zzpyVar.zzb() == null || zzpyVar.zzb().size() <= 0) {
                return;
            }
            Object obj = zzpyVar.zzb().get(0);
            zzqs zzqsVarZza = obj instanceof IBinder ? zzqt.zza((IBinder) obj) : null;
            if (zzqsVarZza != null) {
                try {
                    IObjectWrapper iObjectWrapperZza = zzqsVarZza.zza();
                    if (iObjectWrapperZza != null) {
                        Drawable drawable = (Drawable) zzn.zza(iObjectWrapperZza);
                        ImageView imageView = new ImageView(this.zzd);
                        imageView.setImageDrawable(drawable);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        ((FrameLayout) view).addView(imageView, layoutParams);
                    }
                } catch (RemoteException unused) {
                    zzahw.zze("Could not get drawable from image");
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.zzpv
    public final void zza(View view, String str, Bundle bundle, Map<String, WeakReference<View>> map, View view2) {
        JSONObject jSONObject;
        JSONObject jSONObjectZza = zza(map, view2);
        JSONObject jSONObjectZze = zze(view2);
        JSONObject jSONObjectZzf = zzf(view2);
        JSONObject jSONObjectZzg = zzg(view2);
        JSONObject jSONObject2 = null;
        try {
            JSONObject jSONObjectZza2 = zzbt.zze().zza(bundle, (JSONObject) null);
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("click_point", jSONObjectZza2);
                jSONObject3.put("asset_id", str);
                jSONObject = jSONObject3;
            } catch (Exception e) {
                e = e;
                jSONObject2 = jSONObject3;
                zzahw.zzb("Error occurred while grabbing click signals.", e);
                jSONObject = jSONObject2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        zza(view, jSONObjectZze, jSONObjectZza, jSONObjectZzf, jSONObjectZzg, str, jSONObject, null);
    }

    @Override // com.google.android.gms.internal.zzpv
    public void zza(View view, Map<String, WeakReference<View>> map) {
        zza(zze(view), zza(map, view), zzf(view), zzg(view), (JSONObject) null);
    }

    @Override // com.google.android.gms.internal.zzpv
    public void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        String str;
        zzbq.zzb("performClick must be called on the main UI thread.");
        if (map != null) {
            synchronized (map) {
                for (Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                    if (view.equals(entry.getValue().get())) {
                        zza(view, entry.getKey(), bundle, map, view2);
                        return;
                    }
                }
            }
        }
        if ("6".equals(this.zzg.zzk())) {
            str = "3099";
        } else {
            if (!ExifInterface.GPS_MEASUREMENT_2D.equals(this.zzg.zzk())) {
                if (LocalSearchProvider.LIVE_CONTENTS.equals(this.zzg.zzk())) {
                    zza(view, "1099", bundle, map, view2);
                    return;
                }
                return;
            }
            str = "2099";
        }
        zza(view, str, bundle, map, view2);
    }

    public void zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        if (((Boolean) zzlc.zzf().zza(zzoi.zzeu)).booleanValue()) {
            view.setOnTouchListener(onTouchListener);
            view.setClickable(true);
            view.setOnClickListener(onClickListener);
            if (map != null) {
                synchronized (map) {
                    Iterator<Map.Entry<String, WeakReference<View>>> it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        View view2 = it.next().getValue().get();
                        if (view2 != null) {
                            view2.setOnTouchListener(onTouchListener);
                            view2.setClickable(true);
                            view2.setOnClickListener(onClickListener);
                        }
                    }
                }
            }
            if (map2 != null) {
                synchronized (map2) {
                    Iterator<Map.Entry<String, WeakReference<View>>> it2 = map2.entrySet().iterator();
                    while (it2.hasNext()) {
                        View view3 = it2.next().getValue().get();
                        if (view3 != null) {
                            view3.setOnTouchListener(onTouchListener);
                        }
                    }
                }
            }
        }
    }

    public final void zza(Map<String, WeakReference<View>> map) {
        if (this.zzg.zzo() != null) {
            if (ExifInterface.GPS_MEASUREMENT_2D.equals(this.zzg.zzk())) {
                zzbt.zzi().zzl().zza(this.zzc.zzv(), this.zzg.zzk(), map.containsKey(NativeAppInstallAd.ASSET_MEDIA_VIDEO));
            } else if (LocalSearchProvider.LIVE_CONTENTS.equals(this.zzg.zzk())) {
                zzbt.zzi().zzl().zza(this.zzc.zzv(), this.zzg.zzk(), map.containsKey(NativeContentAd.ASSET_MEDIA_VIDEO));
            }
        }
    }

    @Override // com.google.android.gms.internal.zzpv
    public boolean zza() {
        zzph zzphVarZzm = this.zzg.zzm();
        return zzphVarZzm != null && zzphVarZzm.zzi();
    }

    @Override // com.google.android.gms.internal.zzpv
    public final boolean zza(Bundle bundle) {
        if (zza("impression_reporting")) {
            return zza((JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, zzbt.zze().zza(bundle, (JSONObject) null));
        }
        zzahw.zzc("The ad slot cannot handle external impression events. You must be whitelisted to whitelisted to be able to report your impression events.");
        return false;
    }

    @Override // com.google.android.gms.internal.zzpv
    public final void zzb(Bundle bundle) {
        if (bundle == null) {
            zzahw.zzb("Click data is null. No click is reported.");
        } else if (zza("click_reporting")) {
            zza(null, null, null, null, null, bundle.getBundle("click_signal").getString("asset_id"), null, zzbt.zze().zza(bundle, (JSONObject) null));
        } else {
            zzahw.zzc("The ad slot cannot handle external click events. You must be whitelisted to be able to report your click events.");
        }
    }

    @Override // com.google.android.gms.internal.zzpv
    public final void zzb(View view) {
        this.zzl = new WeakReference<>(view);
    }

    @Override // com.google.android.gms.internal.zzpv
    public void zzb(View view, Map<String, WeakReference<View>> map) {
        if (((Boolean) zzlc.zzf().zza(zzoi.zzet)).booleanValue()) {
            return;
        }
        view.setOnTouchListener(null);
        view.setClickable(false);
        view.setOnClickListener(null);
        if (map == null) {
            return;
        }
        synchronized (map) {
            Iterator<Map.Entry<String, WeakReference<View>>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                View view2 = it.next().getValue().get();
                if (view2 != null) {
                    view2.setOnTouchListener(null);
                    view2.setClickable(false);
                    view2.setOnClickListener(null);
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.zzpv
    public boolean zzb() {
        JSONObject jSONObject = this.zze;
        return jSONObject != null && jSONObject.optBoolean("allow_pub_owned_ad_view", false);
    }

    public final boolean zzb(View view, zzpt zzptVar) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
        View viewZzo = this.zzg.zzo();
        if (viewZzo == null) {
            return false;
        }
        ViewParent parent = viewZzo.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(viewZzo);
        }
        FrameLayout frameLayout = (FrameLayout) view;
        frameLayout.removeAllViews();
        frameLayout.addView(viewZzo, layoutParams);
        this.zzc.zza(zzptVar);
        return true;
    }

    @Override // com.google.android.gms.internal.zzpv
    public final void zzc(Bundle bundle) {
        if (bundle == null) {
            zzahw.zzb("Touch event data is null. No touch event is reported.");
            return;
        }
        if (!zza("touch_reporting")) {
            zzahw.zzc("The ad slot cannot handle external touch events. You must be whitelisted to be able to report your touch events.");
            return;
        }
        this.zzh.zza().zza((int) bundle.getFloat("x"), (int) bundle.getFloat("y"), bundle.getInt("duration_ms"));
    }

    @Override // com.google.android.gms.internal.zzpv
    public final void zzc(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.zzb) {
            if (this.zza) {
                return;
            }
            if (zzc(view)) {
                zza(view, map);
                return;
            }
            if (((Boolean) zzlc.zzf().zza(zzoi.zzfb)).booleanValue() && map != null) {
                synchronized (map) {
                    Iterator<Map.Entry<String, WeakReference<View>>> it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        View view2 = it.next().getValue().get();
                        if (view2 != null && zzc(view2)) {
                            zza(view, map);
                            return;
                        }
                    }
                }
            }
        }
    }

    public zzaof zze() throws zzaop {
        JSONObject jSONObject = this.zze;
        if (jSONObject == null || jSONObject.optJSONObject("overlay") == null) {
            return null;
        }
        zzaol zzaolVarZzf = zzbt.zzf();
        Context context = this.zzd;
        zzko zzkoVarZza = zzko.zza(context);
        zzaof zzaofVarZza = zzaolVarZzf.zza(context, zzaqa.zza(zzkoVarZza), zzkoVarZza.zza, false, false, this.zzh, this.zzi, null, null, null, zziu.zza());
        if (zzaofVarZza != null) {
            zzaofVarZza.zzm().setVisibility(8);
            new zzqb(zzaofVarZza).zza(this.zzf);
        }
        return zzaofVarZza;
    }

    @Override // com.google.android.gms.internal.zzpv
    public void zzf() {
        this.zzf.zza();
    }

    @Override // com.google.android.gms.internal.zzpv
    public void zzg() {
        this.zzc.zzaq();
    }

    @Override // com.google.android.gms.internal.zzpv
    public final View zzh() {
        WeakReference<View> weakReference = this.zzl;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzpv
    public final Context zzi() {
        return this.zzd;
    }

    public final zzagt zzj() {
        if (!zzbt.zzaa().zzc(this.zzd)) {
            return null;
        }
        if (this.zzk == null) {
            this.zzk = new zzagt(this.zzd, this.zzc.zzv());
        }
        return this.zzk;
    }
}
