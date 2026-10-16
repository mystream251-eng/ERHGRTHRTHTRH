package com.google.android.gms.ads.internal.gmsg;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.google.android.exoplayer2.C;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class zzac {
    private final Context zza;
    private final zzcv zzb;
    private final View zzc;

    public zzac(Context context, zzcv zzcvVar, View view) {
        this.zza = context;
        this.zzb = zzcvVar;
        this.zzc = view;
    }

    private static Intent zza(Intent intent, ResolveInfo resolveInfo) {
        Intent intent2 = new Intent(intent);
        intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        return intent2;
    }

    private static Intent zza(Uri uri) {
        if (uri == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        intent.setData(uri);
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }

    private final ResolveInfo zza(Intent intent) {
        return zza(intent, new ArrayList<>());
    }

    private final ResolveInfo zza(Intent intent, ArrayList<ResolveInfo> arrayList) {
        ResolveInfo resolveInfo = null;
        try {
            PackageManager packageManager = this.zza.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 65536);
            if (listQueryIntentActivities != null && resolveInfoResolveActivity != null) {
                for (int i = 0; i < listQueryIntentActivities.size(); i++) {
                    ResolveInfo resolveInfo2 = listQueryIntentActivities.get(i);
                    if (resolveInfoResolveActivity != null && resolveInfoResolveActivity.activityInfo.name.equals(resolveInfo2.activityInfo.name)) {
                        resolveInfo = resolveInfoResolveActivity;
                        break;
                    }
                }
            }
            arrayList.addAll(listQueryIntentActivities);
        } catch (Throwable th) {
            zzbt.zzi().zza(th, "OpenSystemBrowserHandler.getDefaultBrowserResolverForIntent");
        }
        return resolveInfo;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0064  */
    /* JADX WARN: Code duplicated, block: B:21:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:24:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:26:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:31:0x00cf A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:34:0x00d6 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:35:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:41:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:44:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:49:0x0116  */
    /* JADX WARN: Code duplicated, block: B:51:0x0121 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:53:0x010f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:55:? A[LOOP:1: B:42:0x00f7->B:55:?, LOOP_END, SYNTHETIC] */
    public final Intent zza(Map<String, String> map) {
        boolean z;
        Uri.Builder builderScheme;
        ArrayList<ResolveInfo> arrayList;
        Intent intentZza;
        Intent intentZza2;
        ResolveInfo resolveInfoZza;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ArrayList<ResolveInfo> arrayList2;
        int size;
        int i;
        ResolveInfo resolveInfo;
        Iterator<ActivityManager.RunningAppProcessInfo> it;
        ResolveInfo resolveInfoZza2;
        Intent intentZza3;
        ActivityManager activityManager = (ActivityManager) this.zza.getSystemService("activity");
        String str = map.get("u");
        Uri uriBuild = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri uri = Uri.parse(zzab.zza(this.zza, this.zzb, str, this.zzc, null));
        boolean z2 = Boolean.parseBoolean(map.get("use_first_package"));
        boolean z3 = Boolean.parseBoolean(map.get("use_running_process"));
        if (Boolean.parseBoolean(map.get("use_custom_tabs"))) {
            z = true;
        } else if (((Boolean) zzlc.zzf().zza(zzoi.zzgc)).booleanValue()) {
            z = true;
        } else {
            z = false;
        }
        if (!"http".equalsIgnoreCase(uri.getScheme())) {
            if ("https".equalsIgnoreCase(uri.getScheme())) {
                builderScheme = uri.buildUpon().scheme("http");
            }
            arrayList = new ArrayList<>();
            intentZza = zza(uri);
            intentZza2 = zza(uriBuild);
            if (z) {
                zzbt.zze();
                zzaij.zzb(this.zza, intentZza);
                zzbt.zze();
                zzaij.zzb(this.zza, intentZza2);
            }
            resolveInfoZza = zza(intentZza, arrayList);
            if (resolveInfoZza != null) {
                return zza(intentZza, resolveInfoZza);
            }
            if (intentZza2 != null && (resolveInfoZza2 = zza(intentZza2)) != null) {
                intentZza3 = zza(intentZza, resolveInfoZza2);
                if (zza(intentZza3) != null) {
                    return intentZza3;
                }
            }
            if (arrayList.size() == 0) {
                return intentZza;
            }
            if (z3 && activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
                arrayList2 = arrayList;
                size = arrayList2.size();
                i = 0;
                while (i < size) {
                    ResolveInfo resolveInfo2 = arrayList2.get(i);
                    i++;
                    resolveInfo = resolveInfo2;
                    it = runningAppProcesses.iterator();
                    while (it.hasNext()) {
                        if (it.next().processName.equals(resolveInfo.activityInfo.packageName)) {
                            return zza(intentZza, resolveInfo);
                        }
                    }
                }
            }
            if (z2) {
                return zza(intentZza, arrayList.get(0));
            }
            return intentZza;
        }
        builderScheme = uri.buildUpon().scheme("https");
        uriBuild = builderScheme.build();
        arrayList = new ArrayList<>();
        intentZza = zza(uri);
        intentZza2 = zza(uriBuild);
        if (z) {
            zzbt.zze();
            zzaij.zzb(this.zza, intentZza);
            zzbt.zze();
            zzaij.zzb(this.zza, intentZza2);
        }
        resolveInfoZza = zza(intentZza, arrayList);
        if (resolveInfoZza != null) {
            return zza(intentZza, resolveInfoZza);
        }
        if (intentZza2 != null) {
            intentZza3 = zza(intentZza, resolveInfoZza2);
            if (zza(intentZza3) != null) {
                return intentZza3;
            }
        }
        if (arrayList.size() == 0) {
            return intentZza;
        }
        if (z3) {
            arrayList2 = arrayList;
            size = arrayList2.size();
            i = 0;
            while (i < size) {
                ResolveInfo resolveInfo3 = arrayList2.get(i);
                i++;
                resolveInfo = resolveInfo3;
                it = runningAppProcesses.iterator();
                while (it.hasNext()) {
                    if (it.next().processName.equals(resolveInfo.activityInfo.packageName)) {
                        return zza(intentZza, resolveInfo);
                    }
                }
            }
        }
        if (z2) {
            return zza(intentZza, arrayList.get(0));
        }
        return intentZza;
    }
}
