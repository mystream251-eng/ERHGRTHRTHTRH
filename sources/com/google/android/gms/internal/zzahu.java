package com.google.android.gms.internal;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzahu {
    public static void zza(Context context) {
        if (zzaks.zza(context) && !zzaks.zzb()) {
            zzalt zzaltVar = (zzalt) new zzahv(context).zzc();
            zzahw.zzd("Updating ad debug logging enablement.");
            zzalg.zza(zzaltVar, "AdDebugLogUpdater.updateEnablement");
        }
    }
}
