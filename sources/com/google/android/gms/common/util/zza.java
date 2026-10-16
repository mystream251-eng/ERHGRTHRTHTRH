package com.google.android.gms.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zza {
    private static long zzb;
    private static final IntentFilter zza = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static float zzc = Float.NaN;

    public static int zza(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent intentRegisterReceiver = context.getApplicationContext().registerReceiver(null, zza);
        int i = ((intentRegisterReceiver == null ? 0 : intentRegisterReceiver.getIntExtra("plugged", 0)) & (PlatformVersion.isAtLeastJellyBeanMR1() ? 7 : 3)) != 0 ? 1 : 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        return (PlatformVersion.isAtLeastKitKatWatch() ? powerManager.isInteractive() : powerManager.isScreenOn() ? 2 : 0) | i;
    }

    public static synchronized float zzb(Context context) {
        if (SystemClock.elapsedRealtime() - zzb < DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS && !Float.isNaN(zzc)) {
            return zzc;
        }
        Intent intentRegisterReceiver = context.getApplicationContext().registerReceiver(null, zza);
        if (intentRegisterReceiver != null) {
            zzc = intentRegisterReceiver.getIntExtra("level", -1) / intentRegisterReceiver.getIntExtra("scale", -1);
        }
        zzb = SystemClock.elapsedRealtime();
        return zzc;
    }
}
