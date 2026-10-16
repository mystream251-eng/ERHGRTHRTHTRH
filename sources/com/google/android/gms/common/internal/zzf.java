package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import androidx.work.PeriodicWorkRequest;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzf extends GmsClientSupervisor {
    private final Context zzb;
    private final Handler zzc;
    private final HashMap<GmsClientSupervisor.zza, zzh> zza = new HashMap<>();
    private final ConnectionTracker zzd = ConnectionTracker.getInstance();
    private final long zze = 5000;
    private final long zzf = PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS;

    zzf(Context context) {
        this.zzb = context.getApplicationContext();
        this.zzc = new com.google.android.gms.internal.common.zzi(context.getMainLooper(), new zzg(this));
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final boolean zza(GmsClientSupervisor.zza zzaVar, ServiceConnection serviceConnection, String str) {
        boolean zZza;
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zza) {
            zzh zzhVar = this.zza.get(zzaVar);
            if (zzhVar == null) {
                zzhVar = new zzh(this, zzaVar);
                zzhVar.zza(serviceConnection, serviceConnection, str);
                zzhVar.zza(str);
                this.zza.put(zzaVar, zzhVar);
            } else {
                this.zzc.removeMessages(0, zzaVar);
                if (zzhVar.zza(serviceConnection)) {
                    String strValueOf = String.valueOf(zzaVar);
                    StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 81);
                    sb.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
                    sb.append(strValueOf);
                    throw new IllegalStateException(sb.toString());
                }
                zzhVar.zza(serviceConnection, serviceConnection, str);
                int iZzb = zzhVar.zzb();
                if (iZzb == 1) {
                    serviceConnection.onServiceConnected(zzhVar.zze(), zzhVar.zzd());
                } else if (iZzb == 2) {
                    zzhVar.zza(str);
                }
            }
            zZza = zzhVar.zza();
        }
        return zZza;
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final void zzb(GmsClientSupervisor.zza zzaVar, ServiceConnection serviceConnection, String str) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zza) {
            zzh zzhVar = this.zza.get(zzaVar);
            if (zzhVar == null) {
                String strValueOf = String.valueOf(zzaVar);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 50);
                sb.append("Nonexistent connection status for service config: ");
                sb.append(strValueOf);
                throw new IllegalStateException(sb.toString());
            }
            if (!zzhVar.zza(serviceConnection)) {
                String strValueOf2 = String.valueOf(zzaVar);
                StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 76);
                sb2.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                sb2.append(strValueOf2);
                throw new IllegalStateException(sb2.toString());
            }
            zzhVar.zza(serviceConnection, str);
            if (zzhVar.zzc()) {
                this.zzc.sendMessageDelayed(this.zzc.obtainMessage(0, zzaVar), this.zze);
            }
        }
    }
}
