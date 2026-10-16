package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbt;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzgr implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final long zza = ((Long) zzlc.zzf().zza(zzoi.zzdf)).longValue();
    private final Context zzb;
    private Application zzc;
    private final WindowManager zzd;
    private final PowerManager zze;
    private final KeyguardManager zzf;
    private BroadcastReceiver zzg;
    private WeakReference<ViewTreeObserver> zzh;
    private WeakReference<View> zzi;
    private zzgw zzj;
    private zzake zzk = new zzake(zza);
    private boolean zzl = false;
    private int zzm = -1;
    private final HashSet<zzgv> zzn = new HashSet<>();
    private final DisplayMetrics zzo;
    private final Rect zzp;

    public zzgr(Context context, View view) {
        this.zzb = context.getApplicationContext();
        this.zzd = (WindowManager) context.getSystemService("window");
        this.zze = (PowerManager) this.zzb.getSystemService("power");
        this.zzf = (KeyguardManager) context.getSystemService("keyguard");
        Context context2 = this.zzb;
        if (context2 instanceof Application) {
            this.zzc = (Application) context2;
            this.zzj = new zzgw((Application) this.zzb, this);
        }
        this.zzo = context.getResources().getDisplayMetrics();
        Rect rect = new Rect();
        this.zzp = rect;
        rect.right = this.zzd.getDefaultDisplay().getWidth();
        this.zzp.bottom = this.zzd.getDefaultDisplay().getHeight();
        WeakReference<View> weakReference = this.zzi;
        View view2 = weakReference != null ? weakReference.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzb(view2);
        }
        this.zzi = new WeakReference<>(view);
        if (view != null) {
            if (zzbt.zzg().zza(view)) {
                zza(view);
            }
            view.addOnAttachStateChangeListener(this);
        }
    }

    private final Rect zza(Rect rect) {
        return new Rect(zzb(rect.left), zzb(rect.top), zzb(rect.right), zzb(rect.bottom));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        WeakReference<View> weakReference;
        boolean z;
        boolean z2;
        if (this.zzn.size() == 0 || (weakReference = this.zzi) == null) {
            return;
        }
        View view = weakReference.get();
        boolean z3 = i == 1;
        boolean z4 = view == null;
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        Rect rect3 = new Rect();
        Rect rect4 = new Rect();
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        if (view != null) {
            boolean globalVisibleRect = view.getGlobalVisibleRect(rect2);
            boolean localVisibleRect = view.getLocalVisibleRect(rect3);
            view.getHitRect(rect4);
            try {
                view.getLocationOnScreen(iArr);
                view.getLocationInWindow(iArr2);
            } catch (Exception e) {
                zzahw.zzb("Failure getting view location.", e);
            }
            rect.left = iArr[0];
            rect.top = iArr[1];
            rect.right = rect.left + view.getWidth();
            rect.bottom = rect.top + view.getHeight();
            z = globalVisibleRect;
            z2 = localVisibleRect;
        } else {
            z = false;
            z2 = false;
        }
        int windowVisibility = view != null ? view.getWindowVisibility() : 8;
        int i2 = this.zzm;
        if (i2 != -1) {
            windowVisibility = i2;
        }
        boolean z5 = !z4 && zzbt.zze().zza(view, this.zze, this.zzf) && z && z2 && windowVisibility == 0;
        if (z3 && !this.zzk.zza() && z5 == this.zzl) {
            return;
        }
        if (z5 || this.zzl || i != 1) {
            zzgu zzguVar = new zzgu(zzbt.zzl().zzb(), this.zze.isScreenOn(), view != null ? zzbt.zzg().zza(view) : false, view != null ? view.getWindowVisibility() : 8, zza(this.zzp), zza(rect), zza(rect2), z, zza(rect3), z2, zza(rect4), this.zzo.density, z5);
            Iterator<zzgv> it = this.zzn.iterator();
            while (it.hasNext()) {
                it.next().zza(zzguVar);
            }
            this.zzl = z5;
        }
    }

    private final void zza(Activity activity, int i) {
        Window window;
        if (this.zzi == null || (window = activity.getWindow()) == null) {
            return;
        }
        View viewPeekDecorView = window.peekDecorView();
        View view = this.zzi.get();
        if (view == null || viewPeekDecorView == null || view.getRootView() != viewPeekDecorView.getRootView()) {
            return;
        }
        this.zzm = i;
    }

    private final void zza(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzh = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzg == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzg = new zzgt(this);
            zzbt.zzad().zza(this.zzb, this.zzg, intentFilter);
        }
        Application application = this.zzc;
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(this.zzj);
            } catch (Exception e) {
                zzahw.zzb("Error registering activity lifecycle callbacks.", e);
            }
        }
    }

    private final int zzb(int i) {
        return (int) (i / this.zzo.density);
    }

    private final void zzb() {
        zzbt.zze();
        zzaij.zza.post(new zzgs(this));
    }

    private final void zzb(View view) {
        try {
            if (this.zzh != null) {
                ViewTreeObserver viewTreeObserver = this.zzh.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnScrollChangedListener(this);
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                this.zzh = null;
            }
        } catch (Exception e) {
            zzahw.zzb("Error while unregistering listeners from the last ViewTreeObserver.", e);
        }
        try {
            ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            if (viewTreeObserver2.isAlive()) {
                viewTreeObserver2.removeOnScrollChangedListener(this);
                viewTreeObserver2.removeGlobalOnLayoutListener(this);
            }
        } catch (Exception e2) {
            zzahw.zzb("Error while unregistering listeners from the ViewTreeObserver.", e2);
        }
        if (this.zzg != null) {
            try {
                zzbt.zzad().zza(this.zzb, this.zzg);
            } catch (IllegalStateException e3) {
                zzahw.zzb("Failed trying to unregister the receiver", e3);
            } catch (Exception e4) {
                zzbt.zzi().zza(e4, "ActiveViewUnit.stopScreenStatusMonitoring");
            }
            this.zzg = null;
        }
        Application application = this.zzc;
        if (application != null) {
            try {
                application.unregisterActivityLifecycleCallbacks(this.zzj);
            } catch (Exception e5) {
                zzahw.zzb("Error registering activity lifecycle callbacks.", e5);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zza(3);
        zzb();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        zza(3);
        zzb();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zza(3);
        zzb();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zza(3);
        zzb();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zza(3);
        zzb();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zza(3);
        zzb();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        zza(3);
        zzb();
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        zza(2);
        zzb();
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        zza(1);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.zzm = -1;
        zza(view);
        zza(3);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.zzm = -1;
        zza(3);
        zzb();
        zzb(view);
    }

    public final void zza() {
        zza(4);
    }

    public final void zza(zzgv zzgvVar) {
        this.zzn.add(zzgvVar);
        zza(3);
    }

    public final void zzb(zzgv zzgvVar) {
        this.zzn.remove(zzgvVar);
    }
}
