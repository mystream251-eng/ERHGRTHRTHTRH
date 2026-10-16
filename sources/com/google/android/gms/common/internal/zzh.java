package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzh implements ServiceConnection, zzj {
    private final Map<ServiceConnection, ServiceConnection> zza = new HashMap();
    private int zzb = 2;
    private boolean zzc;
    private IBinder zzd;
    private final GmsClientSupervisor.zza zze;
    private ComponentName zzf;
    private final /* synthetic */ zzf zzg;

    public zzh(zzf zzfVar, GmsClientSupervisor.zza zzaVar) {
        this.zzg = zzfVar;
        this.zze = zzaVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.zzg.zza) {
            this.zzg.zzc.removeMessages(1, this.zze);
            this.zzd = iBinder;
            this.zzf = componentName;
            Iterator<ServiceConnection> it = this.zza.values().iterator();
            while (it.hasNext()) {
                it.next().onServiceConnected(componentName, iBinder);
            }
            this.zzb = 1;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.zzg.zza) {
            this.zzg.zzc.removeMessages(1, this.zze);
            this.zzd = null;
            this.zzf = componentName;
            Iterator<ServiceConnection> it = this.zza.values().iterator();
            while (it.hasNext()) {
                it.next().onServiceDisconnected(componentName);
            }
            this.zzb = 2;
        }
    }

    public final void zza(String str) {
        this.zzb = 3;
        boolean zZza = this.zzg.zzd.zza(this.zzg.zzb, str, this.zze.zza(this.zzg.zzb), this, this.zze.zzc());
        this.zzc = zZza;
        if (!zZza) {
            this.zzb = 2;
            try {
                this.zzg.zzd.unbindService(this.zzg.zzb, this);
            } catch (IllegalArgumentException unused) {
            }
        } else {
            this.zzg.zzc.sendMessageDelayed(this.zzg.zzc.obtainMessage(1, this.zze), this.zzg.zzf);
        }
    }

    public final void zzb(String str) {
        this.zzg.zzc.removeMessages(1, this.zze);
        this.zzg.zzd.unbindService(this.zzg.zzb, this);
        this.zzc = false;
        this.zzb = 2;
    }

    public final void zza(ServiceConnection serviceConnection, ServiceConnection serviceConnection2, String str) {
        this.zza.put(serviceConnection, serviceConnection2);
    }

    public final void zza(ServiceConnection serviceConnection, String str) {
        this.zza.remove(serviceConnection);
    }

    public final boolean zza() {
        return this.zzc;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final boolean zza(ServiceConnection serviceConnection) {
        return this.zza.containsKey(serviceConnection);
    }

    public final boolean zzc() {
        return this.zza.isEmpty();
    }

    public final IBinder zzd() {
        return this.zzd;
    }

    public final ComponentName zze() {
        return this.zzf;
    }
}
