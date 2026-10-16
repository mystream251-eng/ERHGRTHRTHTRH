package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzagu {
    private final AtomicReference<ThreadPoolExecutor> zza = new AtomicReference<>(null);
    private final Object zzb = new Object();
    private String zzc = null;
    private AtomicBoolean zzd = new AtomicBoolean(false);
    private final AtomicInteger zze = new AtomicInteger(-1);
    private final AtomicReference<Object> zzf = new AtomicReference<>(null);
    private final AtomicReference<Object> zzg = new AtomicReference<>(null);
    private ConcurrentMap<String, Method> zzh = new ConcurrentHashMap(9);

    private static Bundle zza(Context context, String str, boolean z) {
        Bundle bundle = new Bundle();
        try {
            bundle.putLong("_aeid", Long.parseLong(str));
        } catch (NullPointerException | NumberFormatException e) {
            String strValueOf = String.valueOf(str);
            zzahw.zzb(strValueOf.length() != 0 ? "Invalid event ID: ".concat(strValueOf) : new String("Invalid event ID: "), e);
        }
        if (z) {
            bundle.putInt("_r", 1);
        }
        return bundle;
    }

    private final Object zza(String str, Context context) {
        if (!zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzf, true)) {
            return null;
        }
        try {
            return zzh(context, str).invoke(this.zzf.get(), new Object[0]);
        } catch (Exception e) {
            zza(e, str, true);
            return null;
        }
    }

    private final void zza(Context context, String str, Bundle bundle) {
        if (zza(context) && zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzf, true)) {
            try {
                zzl(context).invoke(this.zzf.get(), "am", str, bundle);
            } catch (Exception e) {
                zza(e, "logEventInternal", true);
            }
        }
    }

    private final void zza(Exception exc, String str, boolean z) {
        if (this.zzd.get()) {
            return;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 30);
        sb.append("Invoke Firebase method ");
        sb.append(str);
        sb.append(" error.");
        zzahw.zze(sb.toString());
        if (z) {
            zzahw.zze("The Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires the latest Firebase SDK jar, but Firebase SDK is either missing or out of date");
            this.zzd.set(true);
        }
    }

    private final boolean zza(Context context, String str, AtomicReference<Object> atomicReference, boolean z) {
        if (atomicReference.get() == null) {
            try {
                atomicReference.compareAndSet(null, context.getClassLoader().loadClass(str).getDeclaredMethod("getInstance", Context.class).invoke(null, context));
            } catch (Exception e) {
                zza(e, "getInstance", z);
                return false;
            }
        }
        return true;
    }

    private final void zzb(Context context, String str, String str2) {
        if (zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzf, true)) {
            try {
                zzg(context, str2).invoke(this.zzf.get(), str);
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 37 + String.valueOf(str).length());
                sb.append("Invoke Firebase method ");
                sb.append(str2);
                sb.append(", Ad Unit Id: ");
                sb.append(str);
                zzahw.zza(sb.toString());
            } catch (Exception e) {
                zza(e, str2, false);
            }
        }
    }

    private final Method zzg(Context context, String str) {
        Method method = this.zzh.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, String.class);
            this.zzh.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final Method zzh(Context context, String str) {
        Method method = this.zzh.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[0]);
            this.zzh.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final Method zzi(Context context, String str) {
        Method method = this.zzh.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics").getDeclaredMethod(str, Activity.class, String.class, String.class);
            this.zzh.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final Method zzl(Context context) {
        Method method = this.zzh.get("logEventInternal");
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod("logEventInternal", String.class, String.class, Bundle.class);
            this.zzh.put("logEventInternal", declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, "logEventInternal", true);
            return null;
        }
    }

    public final void zza(Context context, String str) {
        if (zza(context)) {
            zzb(context, str, "beginAdUnitExposure");
        }
    }

    public final void zza(Context context, String str, String str2) {
        if (zza(context)) {
            zza(context, str, zza(context, str2, "_ac".equals(str)));
        }
    }

    public final void zza(Context context, String str, String str2, String str3, int i) {
        if (zza(context)) {
            Bundle bundleZza = zza(context, str, false);
            bundleZza.putString("_ai", str2);
            bundleZza.putString("type", str3);
            bundleZza.putInt("value", i);
            zza(context, "_ar", bundleZza);
            StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 75);
            sb.append("Log a Firebase reward video event, reward type: ");
            sb.append(str3);
            sb.append(", reward value: ");
            sb.append(i);
            zzahw.zza(sb.toString());
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0043  */
    public final boolean zza(Context context) {
        if (((Boolean) zzlc.zzf().zza(zzoi.zzbh)).booleanValue() && !this.zzd.get()) {
            if (this.zze.get() == -1) {
                zzlc.zza();
                if (zzako.zzc(context)) {
                    this.zze.set(1);
                } else {
                    zzlc.zza();
                    if (zzako.zzf(context)) {
                        zzahw.zze("Google Play Service is out of date, the Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires updated Google Play Service.");
                        this.zze.set(0);
                    } else {
                        this.zze.set(1);
                    }
                }
            }
            if (this.zze.get() == 1) {
                return true;
            }
        }
        return false;
    }

    public final void zzb(Context context, String str) {
        if (zza(context)) {
            zzb(context, str, "endAdUnitExposure");
        }
    }

    public final boolean zzb(Context context) {
        return ((Boolean) zzlc.zzf().zza(zzoi.zzbi)).booleanValue() && zza(context);
    }

    public final void zzc(Context context, String str) {
        if (zza(context) && (context instanceof Activity) && zza(context, "com.google.firebase.analytics.FirebaseAnalytics", this.zzg, false)) {
            try {
                zzi(context, "setCurrentScreen").invoke(this.zzg.get(), (Activity) context, str, context.getPackageName());
            } catch (Exception e) {
                zza(e, "setCurrentScreen", false);
            }
        }
    }

    public final boolean zzc(Context context) {
        return ((Boolean) zzlc.zzf().zza(zzoi.zzbj)).booleanValue() && zza(context);
    }

    public final void zzd(Context context, String str) {
        zza(context, "_ac", str);
    }

    public final boolean zzd(Context context) {
        return ((Boolean) zzlc.zzf().zza(zzoi.zzbk)).booleanValue() && zza(context);
    }

    public final void zze(Context context, String str) {
        zza(context, "_ai", str);
    }

    public final boolean zze(Context context) {
        return ((Boolean) zzlc.zzf().zza(zzoi.zzbl)).booleanValue() && zza(context);
    }

    public final void zzf(Context context, String str) {
        zza(context, "_aq", str);
    }

    public final boolean zzf(Context context) {
        return ((Boolean) zzlc.zzf().zza(zzoi.zzbo)).booleanValue() && zza(context);
    }

    public final String zzg(Context context) {
        if (!zza(context) || !zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzf, true)) {
            return "";
        }
        try {
            String str = (String) zzh(context, "getCurrentScreenName").invoke(this.zzf.get(), new Object[0]);
            if (str == null) {
                str = (String) zzh(context, "getCurrentScreenClass").invoke(this.zzf.get(), new Object[0]);
            }
            return str != null ? str : "";
        } catch (Exception e) {
            zza(e, "getCurrentScreenName", false);
            return "";
        }
    }

    public final String zzh(Context context) {
        if (!zza(context)) {
            return null;
        }
        synchronized (this.zzb) {
            if (this.zzc != null) {
                return this.zzc;
            }
            String str = (String) zza("getGmpAppId", context);
            this.zzc = str;
            return str;
        }
    }

    public final String zzi(final Context context) {
        if (!zza(context)) {
            return null;
        }
        long jLongValue = ((Long) zzlc.zzf().zza(zzoi.zzbr)).longValue();
        if (jLongValue < 0) {
            return (String) zza("getAppInstanceId", context);
        }
        if (this.zza.get() == null) {
            this.zza.compareAndSet(null, new ThreadPoolExecutor(((Integer) zzlc.zzf().zza(zzoi.zzbs)).intValue(), ((Integer) zzlc.zzf().zza(zzoi.zzbs)).intValue(), 1L, TimeUnit.MINUTES, new LinkedBlockingQueue(), new zzagw(this)));
        }
        Future futureSubmit = this.zza.get().submit(new Callable(this, context) { // from class: com.google.android.gms.internal.zzagv
            private final zzagu zza;
            private final Context zzb;

            {
                this.zza = this;
                this.zzb = context;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zza.zzk(this.zzb);
            }
        });
        try {
            return (String) futureSubmit.get(jLongValue, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            futureSubmit.cancel(true);
            if (e instanceof TimeoutException) {
                return "TIME_OUT";
            }
            return null;
        }
    }

    public final String zzj(Context context) {
        Object objZza;
        if (zza(context) && (objZza = zza("generateEventId", context)) != null) {
            return objZza.toString();
        }
        return null;
    }

    final /* synthetic */ String zzk(Context context) throws Exception {
        return (String) zza("getAppInstanceId", context);
    }
}
