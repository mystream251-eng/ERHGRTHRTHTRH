package com.google.android.gms.internal;

import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzql extends zzrc implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    static final String[] zza = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO};
    private final WeakReference<View> zzc;
    private zzpv zzg;
    private View zzh;
    private final Object zzb = new Object();
    private final Map<String, WeakReference<View>> zzd = new HashMap();
    private final Map<String, WeakReference<View>> zze = new HashMap();
    private final Map<String, WeakReference<View>> zzf = new HashMap();
    private Point zzi = new Point();
    private Point zzj = new Point();
    private WeakReference<zzgr> zzk = new WeakReference<>(null);

    public zzql(View view, HashMap<String, View> map, HashMap<String, View> map2) {
        zzbt.zzz();
        zzaml.zza(view, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzbt.zzz();
        zzaml.zza(view, (ViewTreeObserver.OnScrollChangedListener) this);
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        this.zzc = new WeakReference<>(view);
        zza(map);
        this.zzf.putAll(this.zzd);
        zzb(map2);
        this.zzf.putAll(this.zze);
        zzoi.zza(view.getContext());
    }

    private final int zza(int i) {
        int iZzb;
        synchronized (this.zzb) {
            zzlc.zza();
            iZzb = zzako.zzb(this.zzg.zzi(), i);
        }
        return iZzb;
    }

    private final void zza(View view) {
        synchronized (this.zzb) {
            if (this.zzg != null) {
                zzpv zzpvVarZzd = this.zzg instanceof zzpu ? ((zzpu) this.zzg).zzd() : this.zzg;
                if (zzpvVarZzd != null) {
                    zzpvVarZzd.zzb(view);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzpz zzpzVar) {
        View view;
        synchronized (this.zzb) {
            String[] strArr = zza;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    view = null;
                    break;
                }
                WeakReference<View> weakReference = this.zzf.get(strArr[i]);
                if (weakReference != null) {
                    view = weakReference.get();
                    break;
                }
                i++;
            }
            if (!(view instanceof FrameLayout)) {
                zzpzVar.zzg();
                return;
            }
            zzqn zzqnVar = new zzqn(this, view);
            if (zzpzVar instanceof zzpu) {
                zzpzVar.zzb(view, zzqnVar);
            } else {
                zzpzVar.zza(view, zzqnVar);
            }
        }
    }

    private final void zza(Map<String, View> map) {
        for (Map.Entry<String, View> entry : map.entrySet()) {
            String key = entry.getKey();
            View value = entry.getValue();
            if (value != null) {
                this.zzd.put(key, new WeakReference<>(value));
                if (!NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(key)) {
                    value.setOnTouchListener(this);
                    value.setClickable(true);
                    value.setOnClickListener(this);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zza(String[] strArr) {
        for (String str : strArr) {
            if (this.zzd.get(str) != null) {
                return true;
            }
        }
        for (String str2 : strArr) {
            if (this.zze.get(str2) != null) {
                return false;
            }
        }
        return false;
    }

    private final void zzb(Map<String, View> map) {
        for (Map.Entry<String, View> entry : map.entrySet()) {
            View value = entry.getValue();
            if (value != null) {
                this.zze.put(entry.getKey(), new WeakReference<>(value));
                value.setOnTouchListener(this);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        zzpv zzpvVarZzd;
        String str;
        Map<String, WeakReference<View>> map;
        synchronized (this.zzb) {
            if (this.zzg == null) {
                return;
            }
            View view2 = this.zzc.get();
            if (view2 == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putFloat("x", zza(this.zzi.x));
            bundle.putFloat("y", zza(this.zzi.y));
            bundle.putFloat("start_x", zza(this.zzj.x));
            bundle.putFloat("start_y", zza(this.zzj.y));
            if (this.zzh == null || !this.zzh.equals(view)) {
                this.zzg.zza(view, this.zzf, bundle, view2);
            } else {
                if (!(this.zzg instanceof zzpu)) {
                    zzpvVarZzd = this.zzg;
                    str = NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE;
                    map = this.zzf;
                } else if (((zzpu) this.zzg).zzd() != null) {
                    zzpvVarZzd = ((zzpu) this.zzg).zzd();
                    str = NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE;
                    map = this.zzf;
                }
                zzpvVarZzd.zza(view, str, bundle, map, view2);
            }
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        View view;
        synchronized (this.zzb) {
            if (this.zzg != null && (view = this.zzc.get()) != null) {
                this.zzg.zzc(view, this.zzf);
            }
        }
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        View view;
        synchronized (this.zzb) {
            if (this.zzg != null && (view = this.zzc.get()) != null) {
                this.zzg.zzc(view, this.zzf);
            }
        }
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.zzb) {
            if (this.zzg == null) {
                return false;
            }
            View view2 = this.zzc.get();
            if (view2 == null) {
                return false;
            }
            int[] iArr = new int[2];
            view2.getLocationOnScreen(iArr);
            Point point = new Point((int) (motionEvent.getRawX() - iArr[0]), (int) (motionEvent.getRawY() - iArr[1]));
            this.zzi = point;
            if (motionEvent.getAction() == 0) {
                this.zzj = point;
            }
            MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
            motionEventObtain.setLocation(point.x, point.y);
            this.zzg.zza(motionEventObtain);
            motionEventObtain.recycle();
            return false;
        }
    }

    @Override // com.google.android.gms.internal.zzrb
    public final void zza() {
        synchronized (this.zzb) {
            this.zzh = null;
            this.zzg = null;
            this.zzi = null;
            this.zzj = null;
        }
    }

    @Override // com.google.android.gms.internal.zzrb
    public final void zza(IObjectWrapper iObjectWrapper) {
        synchronized (this.zzb) {
            zza((View) null);
            Object objZza = zzn.zza(iObjectWrapper);
            if (!(objZza instanceof zzpz)) {
                zzahw.zze("Not an instance of native engine. This is most likely a transient error");
                return;
            }
            zzpz zzpzVar = (zzpz) objZza;
            if (!zzpzVar.zzb()) {
                zzahw.zzc("Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account.");
                return;
            }
            View view = this.zzc.get();
            if (this.zzg != null && view != null) {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzes)).booleanValue()) {
                    this.zzg.zzb(view, this.zzf);
                }
            }
            synchronized (this.zzb) {
                if (this.zzg instanceof zzpz) {
                    zzpz zzpzVar2 = (zzpz) this.zzg;
                    View view2 = this.zzc.get();
                    if (zzpzVar2 != null && zzpzVar2.zzi() != null && view2 != null && zzbt.zzaa().zzc(view2.getContext())) {
                        zzagt zzagtVarZzj = zzpzVar2.zzj();
                        if (zzagtVarZzj != null) {
                            zzagtVarZzj.zza(false);
                        }
                        zzgr zzgrVar = this.zzk.get();
                        if (zzgrVar != null && zzagtVarZzj != null) {
                            zzgrVar.zzb(zzagtVarZzj);
                        }
                    }
                }
            }
            if ((this.zzg instanceof zzpu) && ((zzpu) this.zzg).zzc()) {
                ((zzpu) this.zzg).zza(zzpzVar);
            } else {
                this.zzg = zzpzVar;
                if (zzpzVar instanceof zzpu) {
                    ((zzpu) zzpzVar).zza((zzpv) null);
                }
            }
            WeakReference<View> weakReference = this.zzf.get(NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW);
            if (weakReference == null) {
                zzahw.zze("Ad choices asset view is not provided.");
            } else {
                View view3 = weakReference.get();
                ViewGroup viewGroup = view3 instanceof ViewGroup ? (ViewGroup) view3 : null;
                if (viewGroup != null) {
                    View viewZza = zzpzVar.zza((View.OnClickListener) this, true);
                    this.zzh = viewZza;
                    if (viewZza != null) {
                        this.zzf.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference<>(this.zzh));
                        this.zzd.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference<>(this.zzh));
                        viewGroup.removeAllViews();
                        viewGroup.addView(this.zzh);
                    }
                }
            }
            zzpzVar.zza(view, this.zzd, this.zze, this, this);
            zzaij.zza.post(new zzqm(this, zzpzVar));
            zza(view);
            this.zzg.zza(view);
            synchronized (this.zzb) {
                if (this.zzg instanceof zzpz) {
                    zzpz zzpzVar3 = (zzpz) this.zzg;
                    View view4 = this.zzc.get();
                    if (zzpzVar3 != null && zzpzVar3.zzi() != null && view4 != null && zzbt.zzaa().zzc(view4.getContext())) {
                        zzgr zzgrVar2 = this.zzk.get();
                        if (zzgrVar2 == null) {
                            zzgrVar2 = new zzgr(view4.getContext(), view4);
                            this.zzk = new WeakReference<>(zzgrVar2);
                        }
                        zzgrVar2.zza(zzpzVar3.zzj());
                    }
                }
            }
        }
    }
}
