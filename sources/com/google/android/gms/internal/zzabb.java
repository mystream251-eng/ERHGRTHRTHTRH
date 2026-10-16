package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import androidx.core.os.EnvironmentCompat;
import io.sentry.protocol.Device;
import io.sentry.protocol.OperatingSystem;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzabb implements zzabf {
    private static final Object zza = new Object();
    private static zzabf zzb = null;
    private static zzabf zzc = null;
    private final Object zzd;
    private final Context zze;
    private final WeakHashMap<Thread, Boolean> zzf;
    private final ExecutorService zzg;
    private final zzala zzh;

    private zzabb(Context context) {
        this(context, zzala.zza());
    }

    private zzabb(Context context, zzala zzalaVar) {
        this.zzd = new Object();
        this.zzf = new WeakHashMap<>();
        this.zzg = Executors.newCachedThreadPool();
        this.zze = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzh = zzalaVar;
    }

    private final Uri.Builder zza(String str, String str2, String str3, int i) {
        boolean zZza;
        String packageName;
        try {
            zZza = zzbih.zza(this.zze).zza();
        } catch (Throwable th) {
            zzaky.zzb("Error fetching instant app info", th);
            zZza = false;
        }
        try {
            packageName = this.zze.getPackageName();
        } catch (Throwable unused) {
            zzaky.zze("Cannot obtain package name, proceeding.");
            packageName = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        Uri.Builder builderAppendQueryParameter = new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("is_aia", Boolean.toString(zZza)).appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter(OperatingSystem.TYPE, Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT));
        String str4 = Build.MANUFACTURER;
        String string = Build.MODEL;
        if (!string.startsWith(str4)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str4).length() + 1 + String.valueOf(string).length());
            sb.append(str4);
            sb.append(" ");
            sb.append(string);
            string = sb.toString();
        }
        return builderAppendQueryParameter.appendQueryParameter(Device.TYPE, string).appendQueryParameter("js", this.zzh.zza).appendQueryParameter("appid", packageName).appendQueryParameter("exceptiontype", str).appendQueryParameter("stacktrace", str2).appendQueryParameter("eids", TextUtils.join(",", zzoi.zza())).appendQueryParameter("exceptionkey", str3).appendQueryParameter("cl", "189396757").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", zzlc.zzc()).appendQueryParameter("sampling_rate", Integer.toString(1)).appendQueryParameter("pb_tm", String.valueOf(zzlc.zzf().zza(zzoi.zzgv)));
    }

    public static zzabf zza(Context context) {
        synchronized (zza) {
            if (zzb == null) {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzb)).booleanValue()) {
                    zzb = new zzabb(context);
                } else {
                    zzb = new zzabg();
                }
            }
        }
        return zzb;
    }

    public static zzabf zza(Context context, zzala zzalaVar) {
        synchronized (zza) {
            if (zzc == null) {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzb)).booleanValue()) {
                    zzabb zzabbVar = new zzabb(context, zzalaVar);
                    Thread thread = Looper.getMainLooper().getThread();
                    if (thread != null) {
                        synchronized (zzabbVar.zzd) {
                            zzabbVar.zzf.put(thread, true);
                        }
                        thread.setUncaughtExceptionHandler(new zzabd(zzabbVar, thread.getUncaughtExceptionHandler()));
                    }
                    Thread.setDefaultUncaughtExceptionHandler(new zzabc(zzabbVar, Thread.getDefaultUncaughtExceptionHandler()));
                    zzc = zzabbVar;
                } else {
                    zzc = new zzabg();
                }
            }
        }
        return zzc;
    }

    private final void zza(List<String> list) {
        for (String str : list) {
            this.zzg.submit(new zzabe(this, new zzakz(), str));
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x003f  */
    protected final void zza(Thread thread, Throwable th) {
        boolean z;
        if (th != null) {
            boolean z2 = false;
            boolean z3 = false;
            for (Throwable cause = th; cause != null; cause = cause.getCause()) {
                for (StackTraceElement stackTraceElement : cause.getStackTrace()) {
                    if (zzako.zzb(stackTraceElement.getClassName())) {
                        z2 = true;
                    }
                    if (getClass().getName().equals(stackTraceElement.getClassName())) {
                        z3 = true;
                    }
                }
            }
            if (!z2 || z3) {
                z = false;
            } else {
                z = true;
            }
        } else {
            z = false;
        }
        if (!z || zzako.zza(th) == null) {
            return;
        }
        String name = th.getClass().getName();
        StringWriter stringWriter = new StringWriter();
        zzdyq.zza(th, new PrintWriter(stringWriter));
        String string = stringWriter.toString();
        if (Math.random() < 1.0d) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(zza(name, string, "", 1).toString());
            zza(arrayList);
        }
    }

    @Override // com.google.android.gms.internal.zzabf
    public final void zza(Throwable th, String str) {
        if (zzako.zza(th) != null) {
            String name = th.getClass().getName();
            StringWriter stringWriter = new StringWriter();
            zzdyq.zza(th, new PrintWriter(stringWriter));
            String string = stringWriter.toString();
            if (Math.random() < 1.0d) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(zza(name, string, str, 1).toString());
                zza(arrayList);
            }
        }
    }
}
