package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class zzhj implements Application.ActivityLifecycleCallbacks {
    private Activity zza;
    private Context zzb;
    private Runnable zzh;
    private long zzj;
    private final Object zzc = new Object();
    private boolean zzd = true;
    private boolean zze = false;
    private final List<zzhl> zzf = new ArrayList();
    private final List<zzhy> zzg = new ArrayList();
    private boolean zzi = false;

    zzhj() {
    }

    private final void zza(Activity activity) {
        synchronized (this.zzc) {
            if (!activity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.zza = activity;
            }
        }
    }

    static /* synthetic */ boolean zza(zzhj zzhjVar, boolean z) {
        zzhjVar.zzd = false;
        return false;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        synchronized (this.zzc) {
            if (this.zza == null) {
                return;
            }
            if (this.zza.equals(activity)) {
                this.zza = null;
            }
            Iterator<zzhy> it = this.zzg.iterator();
            while (it.hasNext()) {
                try {
                    if (it.next().zzc(activity)) {
                        it.remove();
                    }
                } catch (Exception e) {
                    zzbt.zzi().zza(e, "AppActivityTracker.ActivityListener.onActivityDestroyed");
                    zzahw.zzb("onActivityStateChangedListener threw exception.", e);
                }
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        zza(activity);
        synchronized (this.zzc) {
            Iterator<zzhy> it = this.zzg.iterator();
            while (it.hasNext()) {
                try {
                    it.next().zzb(activity);
                } catch (Exception e) {
                    zzbt.zzi().zza(e, "AppActivityTracker.ActivityListener.onActivityPaused");
                    zzahw.zzb("onActivityStateChangedListener threw exception.", e);
                }
            }
        }
        this.zze = true;
        if (this.zzh != null) {
            zzaij.zza.removeCallbacks(this.zzh);
        }
        Handler handler = zzaij.zza;
        zzhk zzhkVar = new zzhk(this);
        this.zzh = zzhkVar;
        handler.postDelayed(zzhkVar, this.zzj);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zza(activity);
        this.zze = false;
        boolean z = !this.zzd;
        this.zzd = true;
        if (this.zzh != null) {
            zzaij.zza.removeCallbacks(this.zzh);
        }
        synchronized (this.zzc) {
            Iterator<zzhy> it = this.zzg.iterator();
            while (it.hasNext()) {
                try {
                    it.next().zza(activity);
                } catch (Exception e) {
                    zzbt.zzi().zza(e, "AppActivityTracker.ActivityListener.onActivityResumed");
                    zzahw.zzb("onActivityStateChangedListener threw exception.", e);
                }
            }
            if (z) {
                Iterator<zzhl> it2 = this.zzf.iterator();
                while (it2.hasNext()) {
                    try {
                        it2.next().zza(true);
                    } catch (Exception e2) {
                        zzahw.zzb("OnForegroundStateChangedListener threw exception.", e2);
                    }
                }
            } else {
                zzahw.zzb("App is still foreground.");
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        zza(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    public final Activity zza() {
        return this.zza;
    }

    public final void zza(Application application, Context context) {
        if (this.zzi) {
            return;
        }
        application.registerActivityLifecycleCallbacks(this);
        if (context instanceof Activity) {
            zza((Activity) context);
        }
        this.zzb = application;
        this.zzj = ((Long) zzlc.zzf().zza(zzoi.zzca)).longValue();
        this.zzi = true;
    }

    public final void zza(zzhl zzhlVar) {
        synchronized (this.zzc) {
            this.zzf.add(zzhlVar);
        }
    }

    public final Context zzb() {
        return this.zzb;
    }
}
