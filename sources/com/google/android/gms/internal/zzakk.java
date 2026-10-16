package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzakk {
    private boolean zzd;
    private Context zze;
    private boolean zzc = false;
    private final Map<BroadcastReceiver, IntentFilter> zzb = new WeakHashMap();
    private final BroadcastReceiver zza = new zzakl(this);

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void zza(Context context, Intent intent) {
        for (Map.Entry<BroadcastReceiver, IntentFilter> entry : this.zzb.entrySet()) {
            if (entry.getValue().hasAction(intent.getAction())) {
                entry.getKey().onReceive(context, intent);
            }
        }
    }

    public final synchronized void zza(Context context) {
        if (this.zzc) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        this.zze = applicationContext;
        if (applicationContext == null) {
            this.zze = context;
        }
        zzoi.zza(this.zze);
        this.zzd = ((Boolean) zzlc.zzf().zza(zzoi.zzez)).booleanValue();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.zze.registerReceiver(this.zza, intentFilter);
        this.zzc = true;
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver) {
        if (this.zzd) {
            this.zzb.remove(broadcastReceiver);
        } else {
            context.unregisterReceiver(broadcastReceiver);
        }
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (this.zzd) {
            this.zzb.put(broadcastReceiver, intentFilter);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
    }
}
