package com.google.android.gms.internal;

import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.AdChoicesView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzqj extends zzqx implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static String[] zza = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, "3010"};
    private final FrameLayout zzc;
    private FrameLayout zzd;
    private View zze;
    private final boolean zzf;
    private View zzh;
    private zzpv zzi;
    private final Object zzb = new Object();
    private Map<String, WeakReference<View>> zzg = Collections.synchronizedMap(new HashMap());
    private boolean zzj = false;
    private Point zzk = new Point();
    private Point zzl = new Point();
    private WeakReference<zzgr> zzm = new WeakReference<>(null);

    public zzqj(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzc = frameLayout;
        this.zzd = frameLayout2;
        zzbt.zzz();
        zzaml.zza((View) this.zzc, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzbt.zzz();
        zzaml.zza((View) this.zzc, (ViewTreeObserver.OnScrollChangedListener) this);
        this.zzc.setOnTouchListener(this);
        this.zzc.setOnClickListener(this);
        if (frameLayout2 != null && zzs.zzg()) {
            frameLayout2.setElevation(Float.MAX_VALUE);
        }
        zzoi.zza(this.zzc.getContext());
        this.zzf = ((Boolean) zzlc.zzf().zza(zzoi.zzfa)).booleanValue();
    }

    private final int zza(int i) {
        zzlc.zza();
        return zzako.zzb(this.zzi.zzi(), i);
    }

    private final void zza(View view) {
        zzpv zzpvVarZzd = this.zzi;
        if (zzpvVarZzd != null) {
            if (zzpvVarZzd instanceof zzpu) {
                zzpvVarZzd = ((zzpu) zzpvVarZzd).zzd();
            }
            if (zzpvVarZzd != null) {
                zzpvVarZzd.zzb(view);
            }
        }
    }

    private final View zzb() {
        if (this.zzg == null) {
            return null;
        }
        for (String str : zza) {
            WeakReference<View> weakReference = this.zzg.get(str);
            if (weakReference != null) {
                return weakReference.get();
            }
        }
        return null;
    }

    private final void zzc() {
        synchronized (this.zzb) {
            if (!this.zzf && this.zzj) {
                int measuredWidth = this.zzc.getMeasuredWidth();
                int measuredHeight = this.zzc.getMeasuredHeight();
                if (measuredWidth != 0 && measuredHeight != 0 && this.zzd != null) {
                    this.zzd.setLayoutParams(new FrameLayout.LayoutParams(measuredWidth, measuredHeight));
                    this.zzj = false;
                }
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        zzpv zzpvVarZzd;
        String str;
        Map<String, WeakReference<View>> map;
        FrameLayout frameLayout;
        synchronized (this.zzb) {
            if (this.zzi == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putFloat("x", zza(this.zzk.x));
            bundle.putFloat("y", zza(this.zzk.y));
            bundle.putFloat("start_x", zza(this.zzl.x));
            bundle.putFloat("start_y", zza(this.zzl.y));
            if (this.zzh == null || !this.zzh.equals(view)) {
                this.zzi.zza(view, this.zzg, bundle, this.zzc);
            } else {
                if (!(this.zzi instanceof zzpu)) {
                    zzpvVarZzd = this.zzi;
                    str = NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE;
                    map = this.zzg;
                    frameLayout = this.zzc;
                } else if (((zzpu) this.zzi).zzd() != null) {
                    zzpvVarZzd = ((zzpu) this.zzi).zzd();
                    str = NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE;
                    map = this.zzg;
                    frameLayout = this.zzc;
                }
                zzpvVarZzd.zza(view, str, bundle, map, frameLayout);
            }
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        synchronized (this.zzb) {
            zzc();
            if (this.zzi != null) {
                this.zzi.zzc(this.zzc, this.zzg);
            }
        }
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        synchronized (this.zzb) {
            if (this.zzi != null) {
                this.zzi.zzc(this.zzc, this.zzg);
            }
            zzc();
        }
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.zzb) {
            if (this.zzi == null) {
                return false;
            }
            int[] iArr = new int[2];
            this.zzc.getLocationOnScreen(iArr);
            Point point = new Point((int) (motionEvent.getRawX() - iArr[0]), (int) (motionEvent.getRawY() - iArr[1]));
            this.zzk = point;
            if (motionEvent.getAction() == 0) {
                this.zzl = point;
            }
            MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
            motionEventObtain.setLocation(point.x, point.y);
            this.zzi.zza(motionEventObtain);
            motionEventObtain.recycle();
            return false;
        }
    }

    @Override // com.google.android.gms.internal.zzqw
    public final IObjectWrapper zza(String str) {
        synchronized (this.zzb) {
            View view = null;
            if (this.zzg == null) {
                return null;
            }
            WeakReference<View> weakReference = this.zzg.get(str);
            if (weakReference != null) {
                view = weakReference.get();
            }
            return zzn.zza(view);
        }
    }

    @Override // com.google.android.gms.internal.zzqw
    public final void zza() {
        synchronized (this.zzb) {
            if (this.zzd != null) {
                this.zzd.removeAllViews();
            }
            this.zzd = null;
            this.zzg = null;
            this.zzh = null;
            this.zzi = null;
            this.zzk = null;
            this.zzl = null;
            this.zzm = null;
            this.zze = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:61:0x00f7  */
    @Override // com.google.android.gms.internal.zzqw
    public final void zza(IObjectWrapper iObjectWrapper) {
        ViewGroup viewGroup;
        zzpz zzpzVar;
        zzpz zzpzVar2;
        synchronized (this.zzb) {
            zzaof zzaofVarZze = null;
            zza((View) null);
            Object objZza = zzn.zza(iObjectWrapper);
            if (!(objZza instanceof zzpz)) {
                zzahw.zze("Not an instance of native engine. This is most likely a transient error");
                return;
            }
            if (!this.zzf && this.zzd != null) {
                this.zzd.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
                this.zzc.requestLayout();
            }
            boolean z = true;
            this.zzj = true;
            zzpz zzpzVar3 = (zzpz) objZza;
            if (this.zzi != null) {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzes)).booleanValue()) {
                    this.zzi.zzb(this.zzc, this.zzg);
                }
            }
            if ((this.zzi instanceof zzpz) && (zzpzVar2 = (zzpz) this.zzi) != null && zzpzVar2.zzi() != null && zzbt.zzaa().zzc(this.zzc.getContext())) {
                zzagt zzagtVarZzj = zzpzVar2.zzj();
                if (zzagtVarZzj != null) {
                    zzagtVarZzj.zza(false);
                }
                zzgr zzgrVar = this.zzm.get();
                if (zzgrVar != null && zzagtVarZzj != null) {
                    zzgrVar.zzb(zzagtVarZzj);
                }
            }
            if ((this.zzi instanceof zzpu) && ((zzpu) this.zzi).zzc()) {
                ((zzpu) this.zzi).zza(zzpzVar3);
            } else {
                this.zzi = zzpzVar3;
                if (zzpzVar3 instanceof zzpu) {
                    ((zzpu) zzpzVar3).zza((zzpv) null);
                }
            }
            if (this.zzd == null) {
                return;
            }
            if (((Boolean) zzlc.zzf().zza(zzoi.zzes)).booleanValue()) {
                this.zzd.setClickable(false);
            }
            this.zzd.removeAllViews();
            boolean zZza = zzpzVar3.zza();
            if (!zZza || this.zzg == null) {
                viewGroup = null;
            } else {
                WeakReference<View> weakReference = this.zzg.get(NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW);
                View view = weakReference != null ? weakReference.get() : null;
                if (view instanceof ViewGroup) {
                    viewGroup = (ViewGroup) view;
                } else {
                    viewGroup = null;
                }
            }
            if (!zZza || viewGroup == null) {
                z = false;
            }
            View viewZza = zzpzVar3.zza(this, z);
            this.zzh = viewZza;
            if (viewZza != null) {
                if (this.zzg != null) {
                    this.zzg.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference<>(this.zzh));
                }
                if (z) {
                    viewGroup.removeAllViews();
                    viewGroup.addView(this.zzh);
                } else {
                    AdChoicesView adChoicesView = new AdChoicesView(zzpzVar3.zzi());
                    adChoicesView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                    adChoicesView.addView(this.zzh);
                    if (this.zzd != null) {
                        this.zzd.addView(adChoicesView);
                    }
                }
            }
            zzpzVar3.zza(this.zzc, this.zzg, (Map<String, WeakReference<View>>) null, this, this);
            if (this.zzf) {
                if (this.zze == null) {
                    View view2 = new View(this.zzc.getContext());
                    this.zze = view2;
                    view2.setLayoutParams(new FrameLayout.LayoutParams(-1, 0));
                }
                if (this.zzc != this.zze.getParent()) {
                    this.zzc.addView(this.zze);
                }
            }
            try {
                zzaofVarZze = zzpzVar3.zze();
            } catch (Exception e) {
                zzbt.zzg();
                if (zzaip.zze()) {
                    zzahw.zze("Privileged processes cannot create HTML overlays.");
                } else {
                    zzahw.zzb("Error obtaining overlay.", e);
                }
            }
            if (zzaofVarZze != null && this.zzd != null) {
                this.zzd.addView(zzaofVarZze.zzm());
            }
            synchronized (this.zzb) {
                zzpzVar3.zza(this.zzg);
                View viewZzb = zzb();
                if (viewZzb instanceof FrameLayout) {
                    zzqk zzqkVar = new zzqk(this, viewZzb);
                    if (zzpzVar3 instanceof zzpu) {
                        zzpzVar3.zzb(viewZzb, zzqkVar);
                    } else {
                        zzpzVar3.zza(viewZzb, zzqkVar);
                    }
                } else {
                    zzpzVar3.zzg();
                }
            }
            zza(this.zzc);
            this.zzi.zza(this.zzc);
            if ((this.zzi instanceof zzpz) && (zzpzVar = (zzpz) this.zzi) != null && zzpzVar.zzi() != null && zzbt.zzaa().zzc(this.zzc.getContext())) {
                zzgr zzgrVar2 = this.zzm.get();
                if (zzgrVar2 == null) {
                    zzgrVar2 = new zzgr(this.zzc.getContext(), this.zzc);
                    this.zzm = new WeakReference<>(zzgrVar2);
                }
                zzgrVar2.zza(zzpzVar.zzj());
            }
        }
    }

    @Override // com.google.android.gms.internal.zzqw
    public final void zza(IObjectWrapper iObjectWrapper, int i) {
        WeakReference<zzgr> weakReference;
        zzgr zzgrVar;
        if (zzbt.zzaa().zzc(this.zzc.getContext()) && (weakReference = this.zzm) != null && (zzgrVar = weakReference.get()) != null) {
            zzgrVar.zza();
        }
        zzc();
    }

    @Override // com.google.android.gms.internal.zzqw
    public final void zza(String str, IObjectWrapper iObjectWrapper) {
        View view = (View) zzn.zza(iObjectWrapper);
        synchronized (this.zzb) {
            if (this.zzg == null) {
                return;
            }
            if (view == null) {
                this.zzg.remove(str);
            } else {
                this.zzg.put(str, new WeakReference<>(view));
                if (NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str)) {
                    return;
                }
                view.setOnTouchListener(this);
                view.setClickable(true);
                view.setOnClickListener(this);
            }
        }
    }
}
