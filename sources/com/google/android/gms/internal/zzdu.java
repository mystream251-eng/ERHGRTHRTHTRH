package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdu implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final Handler zza = new Handler(Looper.getMainLooper());
    private final Context zzb;
    private Application zzc;
    private final PowerManager zzd;
    private final KeyguardManager zze;
    private final zzdm zzf;
    private BroadcastReceiver zzg;
    private WeakReference<ViewTreeObserver> zzh;
    private WeakReference<View> zzi;
    private zzda zzj;
    private boolean zzk = false;
    private int zzl = -1;
    private long zzm = -3;

    public zzdu(zzdm zzdmVar, View view) {
        this.zzf = zzdmVar;
        Context context = zzdmVar.zza;
        this.zzb = context;
        this.zzd = (PowerManager) context.getSystemService("power");
        this.zze = (KeyguardManager) this.zzb.getSystemService("keyguard");
        Context context2 = this.zzb;
        if (context2 instanceof Application) {
            this.zzc = (Application) context2;
            this.zzj = new zzda((Application) this.zzb, this);
        }
        zza(view);
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
        this.zzl = i;
    }

    private final void zzb() {
        zza.post(new zzdv(this));
    }

    private final void zzb(View view) {
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
            zzdw zzdwVar = new zzdw(this);
            this.zzg = zzdwVar;
            this.zzb.registerReceiver(zzdwVar, intentFilter);
        }
        Application application = this.zzc;
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(this.zzj);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:23:0x0055  */
    /* JADX WARN: Code duplicated, block: B:27:0x005b  */
    public final void zzc() {
        boolean z;
        boolean z2;
        WeakReference<View> weakReference = this.zzi;
        if (weakReference == null) {
            return;
        }
        View view = weakReference.get();
        boolean z3 = false;
        if (view == null) {
            this.zzm = -3L;
            this.zzk = false;
            return;
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        boolean localVisibleRect = view.getLocalVisibleRect(new Rect());
        if (this.zzf.zzj()) {
            z = true;
        } else {
            if (this.zze.inKeyguardRestrictedInputMode()) {
                Activity activityZza = zzds.zza(view);
                if (activityZza == null) {
                    z2 = false;
                } else {
                    Window window = activityZza.getWindow();
                    WindowManager.LayoutParams attributes = window == null ? null : window.getAttributes();
                    if (attributes == null || (attributes.flags & 524288) == 0) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    z = true;
                }
            }
            z = false;
        }
        int windowVisibility = view.getWindowVisibility();
        int i = this.zzl;
        if (i != -1) {
            windowVisibility = i;
        }
        if (view.getVisibility() == 0 && view.isShown() && this.zzd.isScreenOn() && z && localVisibleRect && globalVisibleRect && windowVisibility == 0) {
            z3 = true;
        }
        if (this.zzk != z3) {
            this.zzm = z3 ? SystemClock.elapsedRealtime() : -2L;
            this.zzk = z3;
        }
    }

    private final void zzc(View view) {
        try {
            if (this.zzh != null) {
                ViewTreeObserver viewTreeObserver = this.zzh.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnScrollChangedListener(this);
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                this.zzh = null;
            }
        } catch (Exception unused) {
        }
        try {
            ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            if (viewTreeObserver2.isAlive()) {
                viewTreeObserver2.removeOnScrollChangedListener(this);
                viewTreeObserver2.removeGlobalOnLayoutListener(this);
            }
        } catch (Exception unused2) {
        }
        BroadcastReceiver broadcastReceiver = this.zzg;
        if (broadcastReceiver != null) {
            try {
                this.zzb.unregisterReceiver(broadcastReceiver);
            } catch (Exception unused3) {
            }
            this.zzg = null;
        }
        Application application = this.zzc;
        if (application != null) {
            try {
                application.unregisterActivityLifecycleCallbacks(this.zzj);
            } catch (Exception unused4) {
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzc();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        zzc();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzc();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzc();
        zzb();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzc();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzc();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        zzc();
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        zzc();
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        zzc();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.zzl = -1;
        zzb(view);
        zzc();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.zzl = -1;
        zzc();
        zzb();
        zzc(view);
    }

    public final long zza() {
        if (this.zzm == -2 && this.zzi.get() == null) {
            this.zzm = -3L;
        }
        return this.zzm;
    }

    final void zza(View view) {
        long j;
        WeakReference<View> weakReference = this.zzi;
        View view2 = weakReference != null ? weakReference.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzc(view2);
        }
        this.zzi = new WeakReference<>(view);
        if (view != null) {
            if ((view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true) {
                zzb(view);
            }
            view.addOnAttachStateChangeListener(this);
            j = -2;
        } else {
            j = -3;
        }
        this.zzm = j;
    }
}
