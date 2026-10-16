package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsCallback;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzpf implements zzfxd {
    private CustomTabsSession zza;
    private CustomTabsClient zzb;
    private CustomTabsServiceConnection zzc;
    private zzpg zzd;

    public static boolean zza(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 0);
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (listQueryIntentActivities != null && resolveInfoResolveActivity != null) {
            for (int i = 0; i < listQueryIntentActivities.size(); i++) {
                if (resolveInfoResolveActivity.activityInfo.name.equals(listQueryIntentActivities.get(i).activityInfo.name)) {
                    return resolveInfoResolveActivity.activityInfo.packageName.equals(zzfxb.zza(context));
                }
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.zzfxd
    public final void zza() {
        this.zzb = null;
        this.zza = null;
        zzpg zzpgVar = this.zzd;
        if (zzpgVar != null) {
            zzpgVar.zzb();
        }
    }

    public final void zza(Activity activity) {
        CustomTabsServiceConnection customTabsServiceConnection = this.zzc;
        if (customTabsServiceConnection == null) {
            return;
        }
        activity.unbindService(customTabsServiceConnection);
        this.zzb = null;
        this.zza = null;
        this.zzc = null;
    }

    @Override // com.google.android.gms.internal.zzfxd
    public final void zza(CustomTabsClient customTabsClient) {
        this.zzb = customTabsClient;
        customTabsClient.warmup(0L);
        zzpg zzpgVar = this.zzd;
        if (zzpgVar != null) {
            zzpgVar.zza();
        }
    }

    public final void zza(zzpg zzpgVar) {
        this.zzd = zzpgVar;
    }

    public final boolean zza(Uri uri, Bundle bundle, List<Bundle> list) {
        CustomTabsClient customTabsClient = this.zzb;
        if (customTabsClient == null) {
            return false;
        }
        if (customTabsClient == null) {
            this.zza = null;
        } else if (this.zza == null) {
            this.zza = customTabsClient.newSession((CustomTabsCallback) null);
        }
        CustomTabsSession customTabsSession = this.zza;
        if (customTabsSession == null) {
            return false;
        }
        return customTabsSession.mayLaunchUrl(uri, (Bundle) null, (List) null);
    }

    public final void zzb(Activity activity) {
        String strZza;
        if (this.zzb == null && (strZza = zzfxb.zza(activity)) != null) {
            zzfxc zzfxcVar = new zzfxc(this);
            this.zzc = zzfxcVar;
            CustomTabsClient.bindCustomTabsService(activity, strZza, zzfxcVar);
        }
    }
}
