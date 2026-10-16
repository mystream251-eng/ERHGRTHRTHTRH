package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.ConditionVariable;
import com.google.android.gms.common.zzs;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzog {
    private final Object zza = new Object();
    private final ConditionVariable zzb = new ConditionVariable();
    private volatile boolean zzc = false;
    private SharedPreferences zzd = null;
    private Context zze;

    public final <T> T zza(zzny<T> zznyVar) {
        if (!this.zzb.block(5000L)) {
            throw new IllegalStateException("Flags.initialize() was not called!");
        }
        if (!this.zzc || this.zzd == null) {
            synchronized (this.zza) {
                if (this.zzc && this.zzd != null) {
                }
                return zznyVar.zzb();
            }
        }
        return (T) zzakg.zza(this.zze, new zzoh(this, zznyVar));
    }

    public final void zza(Context context) {
        if (this.zzc) {
            return;
        }
        synchronized (this.zza) {
            if (this.zzc) {
                return;
            }
            this.zze = context.getApplicationContext() == null ? context : context.getApplicationContext();
            try {
                Context remoteContext = zzs.getRemoteContext(context);
                if (remoteContext == null && context != null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                    remoteContext = context;
                }
                if (remoteContext == null) {
                    this.zzb.open();
                    return;
                }
                zzlc.zzd();
                this.zzd = remoteContext.getSharedPreferences("google_ads_flags", 0);
                this.zzc = true;
                this.zzb.open();
            } catch (Throwable th) {
                this.zzb.open();
                throw th;
            }
        }
    }
}
