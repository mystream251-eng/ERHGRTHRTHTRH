package com.google.firebase.messaging;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.iid.zzao;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzu {
    private static final long zza = TimeUnit.HOURS.toSeconds(8);
    private static final Pattern zzb = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    private final FirebaseInstanceId zzc;
    private final Context zzd;
    private final zzao zze;
    private final com.google.firebase.iid.zzt zzf;
    private final ScheduledExecutorService zzg;
    private boolean zzh;
    private int zzi;
    private final Map<Integer, TaskCompletionSource<Void>> zzj;
    private final zzv zzk;

    zzu(FirebaseApp firebaseApp, FirebaseInstanceId firebaseInstanceId, zzao zzaoVar, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi, Context context, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        this(firebaseInstanceId, zzaoVar, new zzv(context), new com.google.firebase.iid.zzt(firebaseApp, zzaoVar, executor, userAgentPublisher, heartBeatInfo, firebaseInstallationsApi), context, scheduledExecutorService);
    }

    private zzu(FirebaseInstanceId firebaseInstanceId, zzao zzaoVar, zzv zzvVar, com.google.firebase.iid.zzt zztVar, Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzh = false;
        this.zzi = 0;
        this.zzj = new ArrayMap();
        this.zzc = firebaseInstanceId;
        this.zze = zzaoVar;
        this.zzk = zzvVar;
        this.zzf = zztVar;
        this.zzd = context;
        this.zzg = scheduledExecutorService;
    }

    final Task<Void> zza(String str) {
        String strValueOf = String.valueOf(zza(str, "subscribeToTopic"));
        Task<Void> taskZzc = zzc(strValueOf.length() != 0 ? "S!".concat(strValueOf) : new String("S!"));
        zza();
        return taskZzc;
    }

    final Task<Void> zzb(String str) {
        String strValueOf = String.valueOf(zza(str, "unsubscribeFromTopic"));
        Task<Void> taskZzc = zzc(strValueOf.length() != 0 ? "U!".concat(strValueOf) : new String("U!"));
        zza();
        return taskZzc;
    }

    private final synchronized Task<Void> zzc(String str) {
        String strZza;
        TaskCompletionSource<Void> taskCompletionSource;
        synchronized (this.zzk) {
            strZza = this.zzk.zza();
            zzv zzvVar = this.zzk;
            StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 1 + String.valueOf(str).length());
            sb.append(strZza);
            sb.append(",");
            sb.append(str);
            zzvVar.zza(sb.toString());
        }
        taskCompletionSource = new TaskCompletionSource<>();
        this.zzj.put(Integer.valueOf(this.zzi + (TextUtils.isEmpty(strZza) ? 0 : strZza.split(",").length - 1)), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    private final synchronized boolean zzd() {
        return zzf() != null;
    }

    private static String zza(String str, String str2) {
        if (str != null && str.startsWith("/topics/")) {
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 78);
            sb.append("Format /topics/topic-name is deprecated. Only 'topic-name' should be used in ");
            sb.append(str2);
            sb.append(".");
            Log.w("FirebaseMessaging", sb.toString());
            str = str.substring(8);
        }
        if (str != null && zzb.matcher(str).matches()) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 79);
        sb2.append("Invalid topic name: ");
        sb2.append(str);
        sb2.append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}.");
        throw new IllegalArgumentException(sb2.toString());
    }

    final synchronized void zza() {
        if (zzd()) {
            zze();
        }
    }

    private final synchronized void zze() {
        if (!this.zzh) {
            zza(0L);
        }
    }

    final synchronized void zza(long j) {
        zza(new zzx(this, this.zzd, this.zze, Math.min(Math.max(30L, j << 1), zza)), j);
        this.zzh = true;
    }

    final synchronized void zza(Runnable runnable, long j) {
        this.zzg.schedule(runnable, j, TimeUnit.SECONDS);
    }

    final boolean zzb() throws IOException {
        TaskCompletionSource<Void> taskCompletionSourceRemove;
        while (true) {
            synchronized (this) {
                String strZzf = zzf();
                if (strZzf == null) {
                    if (zzc()) {
                        Log.d("FirebaseMessaging", "topic sync succeeded");
                    }
                    return true;
                }
                if (!zzd(strZzf)) {
                    return false;
                }
                synchronized (this) {
                    taskCompletionSourceRemove = this.zzj.remove(Integer.valueOf(this.zzi));
                    synchronized (this.zzk) {
                        this.zzk.zzb(strZzf);
                    }
                    this.zzi++;
                }
                if (taskCompletionSourceRemove != null) {
                    taskCompletionSourceRemove.setResult(null);
                }
            }
        }
    }

    private final String zzf() {
        String strZza;
        synchronized (this.zzk) {
            strZza = this.zzk.zza();
        }
        if (TextUtils.isEmpty(strZza)) {
            return null;
        }
        String[] strArrSplit = strZza.split(",");
        if (strArrSplit.length <= 1 || TextUtils.isEmpty(strArrSplit[1])) {
            return null;
        }
        return strArrSplit[1];
    }

    private final boolean zzd(String str) throws IOException {
        String[] strArrSplit = str.split("!");
        if (strArrSplit.length == 2) {
            String str2 = strArrSplit[0];
            String str3 = strArrSplit[1];
            byte b = -1;
            try {
                int iHashCode = str2.hashCode();
                if (iHashCode != 83) {
                    if (iHashCode == 85 && str2.equals("U")) {
                        b = 1;
                    }
                } else if (str2.equals(ExifInterface.LATITUDE_SOUTH)) {
                    b = 0;
                }
                if (b == 0) {
                    InstanceIdResult instanceIdResult = (InstanceIdResult) zza(this.zzc.getInstanceId());
                    zza(this.zzf.zzc(instanceIdResult.getId(), instanceIdResult.getToken(), str3));
                    if (zzc()) {
                        StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 31);
                        sb.append("Subscribe to topic: ");
                        sb.append(str3);
                        sb.append(" succeeded.");
                        Log.d("FirebaseMessaging", sb.toString());
                    }
                } else if (b == 1) {
                    InstanceIdResult instanceIdResult2 = (InstanceIdResult) zza(this.zzc.getInstanceId());
                    zza(this.zzf.zzd(instanceIdResult2.getId(), instanceIdResult2.getToken(), str3));
                    if (zzc()) {
                        StringBuilder sb2 = new StringBuilder(String.valueOf(str3).length() + 35);
                        sb2.append("Unsubscribe from topic: ");
                        sb2.append(str3);
                        sb2.append(" succeeded.");
                        Log.d("FirebaseMessaging", sb2.toString());
                    }
                } else if (zzc()) {
                    StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 24);
                    sb3.append("Unknown topic operation");
                    sb3.append(str);
                    sb3.append(".");
                    Log.d("FirebaseMessaging", sb3.toString());
                }
            } catch (IOException e) {
                if ("SERVICE_NOT_AVAILABLE".equals(e.getMessage()) || "INTERNAL_SERVER_ERROR".equals(e.getMessage())) {
                    String message = e.getMessage();
                    StringBuilder sb4 = new StringBuilder(String.valueOf(message).length() + 53);
                    sb4.append("Topic operation failed: ");
                    sb4.append(message);
                    sb4.append(". Will retry Topic operation.");
                    Log.e("FirebaseMessaging", sb4.toString());
                    return false;
                }
                if (e.getMessage() == null) {
                    Log.e("FirebaseMessaging", "Topic operation failed without exception message. Will retry Topic operation.");
                    return false;
                }
                throw e;
            }
        }
        return true;
    }

    private static <T> T zza(Task<T> task) throws IOException {
        try {
            return (T) Tasks.await(task, 30L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e = e;
            throw new IOException("SERVICE_NOT_AVAILABLE", e);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            }
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new IOException(e2);
        } catch (TimeoutException e3) {
            e = e3;
            throw new IOException("SERVICE_NOT_AVAILABLE", e);
        }
    }

    final synchronized void zza(boolean z) {
        this.zzh = z;
    }

    static boolean zzc() {
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            return true;
        }
        return Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseMessaging", 3);
    }
}
