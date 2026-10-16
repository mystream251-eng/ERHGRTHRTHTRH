package com.google.android.gms.internal;

import android.content.ComponentName;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfxc extends CustomTabsServiceConnection {
    private WeakReference<zzfxd> zza;

    public zzfxc(zzfxd zzfxdVar) {
        this.zza = new WeakReference<>(zzfxdVar);
    }

    public final void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzfxd zzfxdVar = this.zza.get();
        if (zzfxdVar != null) {
            zzfxdVar.zza(customTabsClient);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzfxd zzfxdVar = this.zza.get();
        if (zzfxdVar != null) {
            zzfxdVar.zza();
        }
    }
}
