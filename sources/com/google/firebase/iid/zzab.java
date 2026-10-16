package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzab {
    private static zzab zza;
    private final Context zzb;
    private final ScheduledExecutorService zzc;
    private zzac zzd = new zzac(this);
    private int zze = 1;

    public static synchronized zzab zza(Context context) {
        if (zza == null) {
            zza = new zzab(context, com.google.android.gms.internal.firebase_messaging.zza.zza().zza(1, new NamedThreadFactory("MessengerIpcClient"), com.google.android.gms.internal.firebase_messaging.zzf.zzb));
        }
        return zza;
    }

    private zzab(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzc = scheduledExecutorService;
        this.zzb = context.getApplicationContext();
    }

    public final Task<Void> zza(int i, Bundle bundle) {
        return zza(new zzak(zza(), 2, bundle));
    }

    public final Task<Bundle> zzb(int i, Bundle bundle) {
        return zza(new zzap(zza(), 1, bundle));
    }

    private final synchronized <T> Task<T> zza(zzan<T> zzanVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(zzanVar);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 9);
            sb.append("Queueing ");
            sb.append(strValueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.zzd.zza((zzan<?>) zzanVar)) {
            zzac zzacVar = new zzac(this);
            this.zzd = zzacVar;
            zzacVar.zza((zzan<?>) zzanVar);
        }
        return zzanVar.zzb.getTask();
    }

    private final synchronized int zza() {
        int i;
        i = this.zze;
        this.zze = i + 1;
        return i;
    }
}
