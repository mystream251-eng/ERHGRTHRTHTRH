package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaw {
    private static zzaw zza;
    private String zzb = null;
    private Boolean zzc = null;
    private Boolean zzd = null;
    private final Queue<Intent> zze = new ArrayDeque();

    public static synchronized zzaw zza() {
        if (zza == null) {
            zza = new zzaw();
        }
        return zza;
    }

    private zzaw() {
    }

    public final Intent zzb() {
        return this.zze.poll();
    }

    public final int zza(Context context, Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Starting service");
        }
        this.zze.offer(intent);
        Intent intent2 = new Intent("com.google.firebase.MESSAGING_EVENT");
        intent2.setPackage(context.getPackageName());
        return zzb(context, intent2);
    }

    private final int zzb(Context context, Intent intent) {
        ComponentName componentNameStartService;
        String strZzc = zzc(context, intent);
        if (strZzc != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String strValueOf = String.valueOf(strZzc);
                Log.d("FirebaseInstanceId", strValueOf.length() != 0 ? "Restricting intent to a specific service: ".concat(strValueOf) : new String("Restricting intent to a specific service: "));
            }
            intent.setClassName(context.getPackageName(), strZzc);
        }
        try {
            if (zza(context)) {
                componentNameStartService = zzbd.zza(context, intent);
            } else {
                componentNameStartService = context.startService(intent);
                Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
            }
            if (componentNameStartService != null) {
                return -1;
            }
            Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
            return 404;
        } catch (IllegalStateException e) {
            String strValueOf2 = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf2).length() + 45);
            sb.append("Failed to start service while in background: ");
            sb.append(strValueOf2);
            Log.e("FirebaseInstanceId", sb.toString());
            return 402;
        } catch (SecurityException e2) {
            Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", e2);
            return 401;
        }
    }

    private final synchronized String zzc(Context context, Intent intent) {
        if (this.zzb != null) {
            return this.zzb;
        }
        ResolveInfo resolveInfoResolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveInfoResolveService != null && resolveInfoResolveService.serviceInfo != null) {
            ServiceInfo serviceInfo = resolveInfoResolveService.serviceInfo;
            if (context.getPackageName().equals(serviceInfo.packageName) && serviceInfo.name != null) {
                if (serviceInfo.name.startsWith(".")) {
                    String strValueOf = String.valueOf(context.getPackageName());
                    String strValueOf2 = String.valueOf(serviceInfo.name);
                    this.zzb = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
                } else {
                    this.zzb = serviceInfo.name;
                }
                return this.zzb;
            }
            String str = serviceInfo.packageName;
            String str2 = serviceInfo.name;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 94 + String.valueOf(str2).length());
            sb.append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ");
            sb.append(str);
            sb.append("/");
            sb.append(str2);
            Log.e("FirebaseInstanceId", sb.toString());
            return null;
        }
        Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
        return null;
    }

    final boolean zza(Context context) {
        if (this.zzc == null) {
            this.zzc = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
        }
        if (!this.zzc.booleanValue() && Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Missing Permission: android.permission.WAKE_LOCK this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return this.zzc.booleanValue();
    }

    final boolean zzb(Context context) {
        if (this.zzd == null) {
            this.zzd = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0);
        }
        if (!this.zzc.booleanValue() && Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Missing Permission: android.permission.ACCESS_NETWORK_STATE this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return this.zzd.booleanValue();
    }
}
